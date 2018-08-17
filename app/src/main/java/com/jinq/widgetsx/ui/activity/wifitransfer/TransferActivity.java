package com.jinq.widgetsx.ui.activity.wifitransfer;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jinq.widgetsx.R;
import com.jinq.widgetsx.adapter.Transfer.TransferAdapter;
import com.jinq.widgetsx.base.BaseActivity;
import com.jinq.widgetsx.base.BasePresenter;
import com.jinq.widgetsx.bean.TransferBean.TransferModelBean;
import com.jinq.widgetsx.constant.AppConstant;
import com.jinq.widgetsx.constant.transfer.TransferConstant;
import com.jinq.widgetsx.event.TransferEvent;
import com.jinq.widgetsx.service.WebService;
import com.jinq.widgetsx.widget.PopupMenuDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class TransferActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener,
        Animator.AnimatorListener, EasyPermissions.PermissionCallbacks {
    private static final int PERMISSION_READ_AND_WRITE_EXTERNAL_STORAGE = 1001;
    /**
     * title
     */
    @BindView(R.id.mTitleBackIv)
    ImageView mBack;
    @BindView(R.id.mTitleTv)
    TextView mTitle;

    @BindView(R.id.mNullLy)
    LinearLayout mNullLy;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private List<TransferModelBean> mApps = new ArrayList<>();
    private TransferAdapter mAdapter;


    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);
        mTitle.setText(getResources().getString(R.string.transfer_title));
        initSwipeToLoadLayout();
        initRecyclerView();
        EventBus.getDefault().register(this);
        initPermission();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void dismissData() {
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.mTitleBackIv, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mTitleBackIv:
                finish();
                break;
            case R.id.fab:
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mFab, "translationY", 0, mFab.getHeight() * 2).setDuration(200L);
                objectAnimator.setInterpolator(new AccelerateInterpolator());
                objectAnimator.addListener(this);
                objectAnimator.start();
                break;

        }
    }

    /**
     * 权限检查
     *
     * @return
     */
    private boolean hasPermission() {
        return EasyPermissions.hasPermissions(self, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);
    }

    @AfterPermissionGranted(PERMISSION_READ_AND_WRITE_EXTERNAL_STORAGE)
    public void initPermission() {
        if (!hasPermission()) {
            EasyPermissions.requestPermissions(self, getString(R.string.permission_read_and_write), PERMISSION_READ_AND_WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    public void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(self, LinearLayoutManager.VERTICAL, false);
        swipeTarget.setLayoutManager(layoutManager);

        swipeTarget.setHasFixedSize(true);
        swipeTarget.setNestedScrollingEnabled(false);
        mAdapter = new TransferAdapter(self);
        mAdapter.setOnButtonClickListener(new TransferAdapter.onButtonClickListener() {
            @Override
            public void onInstallClick(TransferModelBean transferModelBean) {
                installApkFile(TransferActivity.this, new File(transferModelBean.getPath()));
            }

            @Override
            public void onUninstallClick(TransferModelBean transferModelBean) {
                delete(TransferActivity.this, transferModelBean.getPackageName());
            }

            @Override
            public void onFileOpenClick(TransferModelBean transferModelBean) {
                //TODO
            }
        });
        swipeTarget.setAdapter(mAdapter);
        swipeTarget.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }

    private void initSwipeToLoadLayout() {
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setLoadMoreEnabled(false);
    }

    //获取apk信息
    private void handleApk(String path, long length, List<TransferModelBean> list) {
        TransferModelBean infoModel = new TransferModelBean();
        String archiveFilePath = "";
        archiveFilePath = path;
        PackageManager pm = getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(archiveFilePath, 0);

        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appInfo.sourceDir = archiveFilePath;
            appInfo.publicSourceDir = archiveFilePath;
            String packageName = appInfo.packageName;  //得到安装包名称
            String version = info.versionName;       //得到版本信息
            Drawable icon = pm.getApplicationIcon(appInfo);
            String appName = pm.getApplicationLabel(appInfo).toString();
            if (TextUtils.isEmpty(appName)) {
                appName = getApplicationName(packageName);
            }
            if (icon == null) {
                icon = getIconFromPackageName(packageName, this); // 获得应用程序图标
            }
            infoModel.setName(appName);
            infoModel.setPackageName(packageName);
            infoModel.setPath(path);
            infoModel.setSize(getFileSize(length));
            infoModel.setVersion(version);
            infoModel.setIcon(icon);
            infoModel.setFiletype(TransferModelBean.FILETYPE.APK);
            infoModel.setInstalled(isAvilible(this, packageName));
            if (list == null)
                mApps.add(infoModel);
            else
                list.add(infoModel);
        }
    }

    public synchronized static Drawable getIconFromPackageName(String packageName, Context context) {
        PackageManager pm = context.getPackageManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            try {
                PackageInfo pi = pm.getPackageInfo(packageName, 0);
                Context otherAppCtx = context.createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY);
                int displayMetrics[] = {DisplayMetrics.DENSITY_XXXHIGH, DisplayMetrics.DENSITY_XXHIGH, DisplayMetrics.DENSITY_XHIGH, DisplayMetrics.DENSITY_HIGH, DisplayMetrics.DENSITY_TV};
                for (int displayMetric : displayMetrics) {
                    try {
                        Drawable d = otherAppCtx.getResources().getDrawableForDensity(pi.applicationInfo.icon, displayMetric);
                        if (d != null) {
                            return d;
                        }
                    } catch (Resources.NotFoundException e) {
                        continue;
                    }
                }
            } catch (Exception e) {
                // Handle Error here
            }
        }
        ApplicationInfo appInfo = null;
        try {
            appInfo = pm.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
        return appInfo.loadIcon(pm);
    }

    public String getApplicationName(String packageName) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        if (packageManager != null && applicationInfo != null) {
            String applicationName =
                    (String) packageManager.getApplicationLabel(applicationInfo);
            return applicationName;
        }
        return packageName;
    }

    private String getFileSize(long length) {
        DecimalFormat df = new DecimalFormat("######0.00");
        double d1 = 3.23456;
        double d2 = 0.0;
        double d3 = 2.0;
        df.format(d1);
        df.format(d2);
        df.format(d3);
        long l = length / 1000;//KB
        if (l < 1024) {
            return df.format(l) + "KB";
        } else if (l < 1024 * 1024.f) {
            return df.format((l / 1024.f)) + "MB";
        }
        return df.format(l / 1024.f / 1024.f) + "GB";
    }

    /**
     * 判断相对应的APP是否存在
     *
     * @param context
     * @param packageName(包名)(若想判断QQ，则改为com.tencent.mobileqq，若想判断微信，则改为com.tencent.mm)
     * @return
     */
    public boolean isAvilible(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();

        //获取手机系统的所有APP包名，然后进行一一比较
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (((PackageInfo) pinfo.get(i)).packageName
                    .equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    //安装
    public static void installApkFile(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //兼容7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            context.startActivity(intent);
        }
    }

    //卸载
    public static void delete(Context context, String packageName) {
        Uri uri = Uri.fromParts("package", packageName, null);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        context.startActivity(intent);
    }

    //删除所有文件
    private void deleteAll() {
        File dir = TransferConstant.DIR;
        if (dir.exists() && dir.isDirectory()) {
            File[] fileNames = dir.listFiles();
            if (fileNames != null) {
                for (File fileName : fileNames) {
                    fileName.delete();
                }
            }
        }
        TransferEvent event = new TransferEvent();
        event.setType(TransferConstant.RxBusEventType.LOAD_BOOK_LIST);
        event.setIntState(0);
        EventBus.getDefault().post(event);
    }


    @Override
    public void onRefresh() {
        checkFileList();
        swipeToLoadLayout.setRefreshing(true);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onAnimationStart(Animator animator) {
        WebService.start(this);
        new PopupMenuDialog(this).builder().setCancelable(false)
                .setCanceledOnTouchOutside(false).show();
    }

    @Override
    public void onAnimationEnd(Animator animator) {

    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    public void checkFileList() {
        if(!mApps.isEmpty()){
            mApps.clear();
        }
        File dir = TransferConstant.DIR;
        if (dir.exists() && dir.isDirectory()) {
            File[] fileNames = dir.listFiles();
            if (fileNames != null) {
                for (File fileName : fileNames) {
                    if(fileName.getAbsolutePath().endsWith(".apk")) {
                        handleApk(fileName.getAbsolutePath(), fileName.length(), mApps);
                    }else{
                        //非APK类型的文件，检索出list bean类型的数据
                        handleOtherFiles(fileName,fileName.length(), mApps);
                    }
                }
            }
        }
        swipeToLoadLayout.setRefreshing(false);
        if(!mApps.isEmpty()){
            mAdapter.addList(mApps);
            mNullLy.setVisibility(View.GONE);
            swipeTarget.setVisibility(View.VISIBLE);
        }else{
            mNullLy.setVisibility(View.VISIBLE);
            swipeTarget.setVisibility(View.GONE);
        }

    }

    private void handleOtherFiles(File file,long length, List<TransferModelBean> list){
        TransferModelBean infoModel = new TransferModelBean();
        infoModel.setPath(file.getAbsolutePath());
        infoModel.setName(file.getName());
        infoModel.setSize(getFileSize(length));
        TransferModelBean.FILETYPE type ;
        if(file.getAbsolutePath().endsWith(".png")){
            type = TransferModelBean.FILETYPE.PNG;
        }else if(file.getAbsolutePath().endsWith(".jpg")){
            type = TransferModelBean.FILETYPE.JPG;
        }else if(file.getAbsolutePath().endsWith(".doc")){
            type = TransferModelBean.FILETYPE.DOC;
        }else if(file.getAbsolutePath().endsWith(".txt")){
            type = TransferModelBean.FILETYPE.TXT;
        }else if(file.getAbsolutePath().endsWith(".mp3")){
            type = TransferModelBean.FILETYPE.MP3;
        }else {
            type = TransferModelBean.FILETYPE.OTHER;
        }
        infoModel.setFiletype(type);
        list.add(infoModel);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(TransferEvent event) {
        Log.e("transfer", "回调onEvent");
        if (event != null) {
            if (event.getType().equals(TransferConstant.RxBusEventType.POPUP_MENU_DIALOG_SHOW_DISMISS)) {
                if (event.getIntState() == TransferConstant.MSG_DIALOG_DISMISS) {
                    WebService.stop(this);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mFab, "translationY", mFab.getHeight() * 2, 0).setDuration(200L);
                    objectAnimator.setInterpolator(new AccelerateInterpolator());
                    objectAnimator.start();
                }
            } else if (event.getType().equals(TransferConstant.RxBusEventType.LOAD_BOOK_LIST)) {
                checkFileList();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
