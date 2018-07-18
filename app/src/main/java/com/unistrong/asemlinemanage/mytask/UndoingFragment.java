package com.unistrong.asemlinemanage.mytask;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unistrong.baselibs.ui.LoadMoreListView;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.resp.TaskListResp;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 未完成页签
 */
public class UndoingFragment extends Fragment implements LoadMoreListView.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "UndoingFragment";
    public static final String STATUS = "proceding";

    public static final int START_INDEX = 1;
    private int currentIndex;
    private List<TaskListResp.ResultBean> datas = new ArrayList<>();

    private MyTaskActivity activity;
    private MyTaskPresenter presenter;
    private LoadMoreListView loadMoreListView;
    private TaskListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadMoreListView = new LoadMoreListView(getContext());
        swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        swipeRefreshLayout.addView(loadMoreListView);
        swipeRefreshLayout.setOnRefreshListener(this);
        initListView();
        return swipeRefreshLayout;
    }

    private void initListView() {
        loadMoreListView.setLoadMoreEnable(true);
        loadMoreListView.setOnLoadMoreListener(this);
        loadMoreListView.setDivider(new ColorDrawable(Color.TRANSPARENT));
        loadMoreListView.setDividerHeight(0);
        loadMoreListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        loadMoreListView.setAdapter(adapter = new TaskListAdapter(getActivity()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MyTaskActivity) context;
        presenter = new MyTaskPresenter(getContext());
        requestData(currentIndex = START_INDEX);
    }

    public void initRequest() {
        datas.clear();
        notifyAdapterRefresh();
        requestData(currentIndex = START_INDEX);
    }

    @Override
    public void onloadMore() {
        requestData(++currentIndex);
    }

    private void notifyAdapterRefresh() {
        adapter.setDatas(STATUS, datas);
    }

    public void requestData(int pageIndex) {
        activity.createLoadingDialog();
        presenter.requestTaskList(STATUS, pageIndex, activity.houseId, activity.houseType, new ResponseBody<TaskListResp>(TaskListResp.class) {
            @Override
            public void onFailure(String message) {
                activity.closeLoadingDialog();
                activity.setRefreshComplete(swipeRefreshLayout);
                boolean isBegining = currentIndex == START_INDEX;
                currentIndex = isBegining ? START_INDEX : --currentIndex;
                loadMoreListView.setLoadCompleted();
                IToast.toast(message);
            }

            @Override
            public void onSuccess(TaskListResp resp) {
                activity.closeLoadingDialog();
                activity.setRefreshComplete(swipeRefreshLayout);
                loadMoreListView.setLoadCompleted();
                List<TaskListResp.ResultBean> resultBeanList = resp.getResult();
                if (resultBeanList != null && !resultBeanList.isEmpty()) {
                    datas.addAll(resultBeanList);
                    notifyAdapterRefresh();
                } else {
                    boolean isBegining = currentIndex == START_INDEX;
                    currentIndex = isBegining ? START_INDEX : --currentIndex;
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        initRequest();
    }
}
