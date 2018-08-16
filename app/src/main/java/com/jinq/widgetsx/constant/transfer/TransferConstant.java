package com.jinq.widgetsx.constant.transfer;

import android.os.Environment;

import java.io.File;

public class TransferConstant {
    public static final int HTTP_PORT = 9989;
    public static final String DIR_IN_SDCARD = "WidgetsX";
    public static final String DIR_IN_PACKAGE = "TransferFiles";
    public static final int MSG_DIALOG_DISMISS = 0;
    public static final File DIR = new File(Environment.getExternalStorageDirectory()
                                                + File.separator + TransferConstant.DIR_IN_SDCARD
                                                + File.separator + TransferConstant.DIR_IN_PACKAGE);

    public static final class RxBusEventType {
        public static final String POPUP_MENU_DIALOG_SHOW_DISMISS = "POPUP MENU DIALOG SHOW DISMISS";
        public static final String WIFI_CONNECT_CHANGE_EVENT = "WIFI_CONNECT_CHANGE_EVENT";
        public static final String LOAD_BOOK_LIST = "LOAD_BOOK_LIST";
    }
}
