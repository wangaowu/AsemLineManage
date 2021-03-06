package com.unistrong.asemlinemanage.mytask;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unistrong.asemlinemanage.R;
import com.unistrong.framwork.resp.TaskListResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.FilterDateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的任务列表adapter
 */
public class TaskListAdapter extends BaseAdapter {
    private MyTaskActivity context;
    private String status;
    private List<TaskListResp.ResultBean> datas = new ArrayList<>();

    public TaskListAdapter(Context context) {
        this.context = (MyTaskActivity) context;
    }

    public void setDatas(String status, List<TaskListResp.ResultBean> datas) {
        this.status = status;
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_task_list_layout, null);
            holder.tvTaskName = convertView.findViewById(R.id.tv_task_name);
            holder.tvChangeStatus = convertView.findViewById(R.id.tv_change_status);
            holder.tvTaskAddress = convertView.findViewById(R.id.tv_task_address);
            holder.tvTaskDesc = convertView.findViewById(R.id.tv_task_desc);
            holder.tvBeginningTime = convertView.findViewById(R.id.tv_beginning_time);
            holder.tvEndTime = convertView.findViewById(R.id.tv_end_time);
            holder.tvHistory = convertView.findViewById(R.id.tv_visit_history);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setItemData(position, holder);
        //绑定录入点击事件
        holder.tvChangeStatus.setOnClickListener(v ->
                context.startRecordHouseInfoActivity(datas.get(position)));
        //绑定历史事件
        holder.tvHistory.setOnClickListener(v ->
                context.requestVisitDetail(datas.get(position)));
        //绑定item点击事件
        convertView.setOnClickListener(v ->
                context.startHouseInfoActivity(datas.get(position), status));
        return convertView;
    }

    private void setItemData(int position, ViewHolder holder) {
        TaskListResp.ResultBean bean = datas.get(position);
        boolean isUndoing = UndoingFragment.STATUS.equals(status);
        holder.tvChangeStatus.setVisibility(isUndoing ? View.VISIBLE : View.GONE);
        if (isUndoing) {
            boolean isEmptyHistory = "0".equals(bean.getNum()) || null == bean.getNum();
            holder.tvHistory.setVisibility(isEmptyHistory ? View.GONE : View.VISIBLE);
        } else {
            holder.tvHistory.setVisibility(View.GONE);
        }
        holder.tvTaskName.setText(getString(bean.getTaskName()));
        if (Constant.Value.TYPE_HOUSE.equals(bean.getHouseType())) {
            holder.tvTaskAddress.setText("房屋地址:" + getString(bean.getHouseAddress()));
        } else {
            holder.tvTaskAddress.setText("单位地址:" + getString(bean.getHouseAddress()));
        }
        holder.tvBeginningTime.setText("开始时间:" + FilterDateUtil.filter(bean.getTaskStartTime()));
        holder.tvEndTime.setText("结束时间:" + FilterDateUtil.filter(bean.getSubtaskFinishTime()));
        holder.tvTaskDesc.setText("任务描述:" + getString(bean.getTaskDesc()));
    }

    private String getString(String src) {
        return TextUtils.isEmpty(src) ? "-" : src;
    }

    class ViewHolder {
        public TextView tvChangeStatus;
        public TextView tvTaskAddress;
        public TextView tvTaskDesc;
        public TextView tvBeginningTime;
        public TextView tvEndTime;
        public TextView tvTaskName;
        public TextView tvHistory;
    }
}
