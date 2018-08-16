package com.jinq.widgetsx.adapter.Transfer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jinq.widgetsx.R;
import com.jinq.widgetsx.bean.TransferBean.TransferModelBean;
import com.jinq.widgetsx.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.ViewHolder> {

    private List<TransferModelBean> mList = new ArrayList<>();
    private Context mContext;

    //    private setOnItemClickListener mListener;

    public TransferAdapter(Context context) {
        this.mContext = context;
    }

    public void setList(List<TransferModelBean> list) {
        if (!mList.isEmpty()) {
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();

    }

    public void addList(List<TransferModelBean> list) {
        if (!mList.isEmpty()) {
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transfer, parent, false);
        return new TransferAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TransferModelBean transferModelBean = mList.get(position);
        if (transferModelBean.getIcon() != null) {
            holder.mIcon.setImageDrawable(transferModelBean.getIcon());
        }
        if (!StringUtil.isEmpty(transferModelBean.getName())) {
            holder.mFileName.setText(transferModelBean.getName());
        }
        if (!StringUtil.isEmpty(transferModelBean.getPath())) {
            holder.mFilePath.setText(transferModelBean.getPath());
        }
        if(!StringUtil.isEmpty(transferModelBean.getSize())){
            holder.mFileSize.setText(transferModelBean.getSize());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.fileName)
        TextView mFileName;
        @BindView(R.id.filePath)
        TextView mFilePath;
        @BindView(R.id.fileSize)
        TextView mFileSize;
        @BindView(R.id.fileInstall)
        TextView mFileInstall;
        @BindView(R.id.fileUninstall)
        TextView mFileUninstall;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
