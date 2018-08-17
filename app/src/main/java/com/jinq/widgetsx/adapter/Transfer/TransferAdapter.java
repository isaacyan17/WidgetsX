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

    private onButtonClickListener mListener;

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

    public void setOnButtonClickListener(onButtonClickListener listener) {
        this.mListener = listener;
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
        } else {
            switch (transferModelBean.getFiletype()) {
                case TXT:
                    holder.mIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_txt));
                    break;
                case DOC:
                    holder.mIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_doc));
                    break;
                case MP3:
                    holder.mIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_mp3));
                    break;
                case PNG:
                case JPG:
                    holder.mIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_img));
                    break;
                case OTHER:
                    break;
                default:
                    break;
            }
        }
        if (!StringUtil.isEmpty(transferModelBean.getName())) {
            holder.mFileName.setText(transferModelBean.getName());
        }
        if (!StringUtil.isEmpty(transferModelBean.getPath())) {
            holder.mFilePath.setText(transferModelBean.getPath());
        }
        if (!StringUtil.isEmpty(transferModelBean.getSize())) {
            holder.mFileSize.setText(transferModelBean.getSize());
        }
        if (transferModelBean.getFiletype() == TransferModelBean.FILETYPE.APK) {
            holder.mFileUninstall.setVisibility(View.VISIBLE);
            holder.mFileInstall.setText(mContext.getResources().getString(R.string.transfer_install));
        } else {
            holder.mFileUninstall.setVisibility(View.GONE);
            holder.mFileInstall.setText(mContext.getResources().getString(R.string.transfer_open));
        }

        holder.mFileInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (transferModelBean.getFiletype() == TransferModelBean.FILETYPE.APK && mListener != null) {
                    mListener.onInstallClick(transferModelBean);
                } else if (mListener != null) {
                    mListener.onFileOpenClick(transferModelBean);
                }
            }
        });
        holder.mFileUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onUninstallClick(transferModelBean);
                }
            }
        });

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

    public interface onButtonClickListener {
        void onInstallClick(TransferModelBean transferModelBean);

        void onUninstallClick(TransferModelBean transferModelBean);

        void onFileOpenClick(TransferModelBean transferModelBean);
    }
}
