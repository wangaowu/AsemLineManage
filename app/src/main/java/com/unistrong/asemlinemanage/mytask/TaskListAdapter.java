package com.unistrong.asemlinemanage.mytask;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unistrong.asemlinemanage.R;
import com.unistrong.framwork.resp.TaskListResp;
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
            holder.tvStatus = convertView.findViewById(R.id.tv_status);
            holder.tvChangeStatus = convertView.findViewById(R.id.tv_change_status);
            holder.tvTaskAddress = convertView.findViewById(R.id.tv_task_address);
            holder.tvCreateTime = convertView.findViewById(R.id.tv_create_time);
            holder.tvBeginningTime = convertView.findViewById(R.id.tv_beginning_time);
            holder.tvEndTime = convertView.findViewById(R.id.tv_end_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setItemData(position, holder);
        //绑定录入点击事件
        holder.tvChangeStatus.setOnClickListener(v ->
                context.startRecordHouseInfoActivity(datas.get(position)));
        //绑定item点击事件
        convertView.setOnClickListener(v -> context.startHouseInfoActivity(datas.get(position), status));
        return convertView;
    }

    private void setItemData(int position, ViewHolder holder) {
        if (UndoingFragment.STATUS.equals(status)) {
            //未完成
            holder.tvStatus.setVisibility(View.GONE);
            holder.tvChangeStatus.setVisibility(View.VISIBLE);
        } else {
            //已完成
            holder.tvStatus.setVisibility(View.VISIBLE);
            holder.tvChangeStatus.setVisibility(View.INVISIBLE);
        }
        TaskListResp.ResultBean bean = datas.get(position);
        holder.tvTaskName.setText(getString(bean.getTaskName()));
        holder.tvTaskAddress.setText("房屋地址:" + getString(bean.getHouseAddress()));
        holder.tvBeginningTime.setText("开始时间:" + FilterDateUtil.filter(bean.getTaskStartTime()));
        holder.tvEndTime.setText("结束时间:" + FilterDateUtil.filter(bean.getTaskEndTime()));
        holder.tvCreateTime.setText("创建时间:" + FilterDateUtil.filter(bean.getTaskCreateTime()));
    }

    private String getString(String src) {
        return TextUtils.isEmpty(src) ? "-" : src;
    }

    class ViewHolder {
        public TextView tvStatus;
        public TextView tvChangeStatus;
        public TextView tvTaskAddress;
        public TextView tvCreateTime;
        public TextView tvBeginningTime;
        public TextView tvEndTime;
        public TextView tvTaskName;
    }
}
