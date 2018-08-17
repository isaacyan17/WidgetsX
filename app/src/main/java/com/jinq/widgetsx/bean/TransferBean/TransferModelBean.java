package com.jinq.widgetsx.bean.TransferBean;

import android.graphics.drawable.Drawable;

public class TransferModelBean {
    private String path;
    private String version;
    private String size;
    private String name;
    private String packageName;
    private boolean installed;
    private Drawable icon;
    private FILETYPE filetype;

    public FILETYPE getFiletype() {
        return filetype;
    }

    public void setFiletype(FILETYPE filetype) {
        this.filetype = filetype;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public enum  FILETYPE{
        APK,
        MP3,
        TXT,
        DOC,
        PNG,
        JPG,
        OTHER
    }

}
