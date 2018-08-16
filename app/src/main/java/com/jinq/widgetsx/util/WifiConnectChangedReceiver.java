package com.jinq.widgetsx.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;

import com.jinq.widgetsx.constant.transfer.TransferConstant;
import com.jinq.widgetsx.event.TransferEvent;

import org.greenrobot.eventbus.EventBus;

public class WifiConnectChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            Parcelable parcelableExtra = intent
                    .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (null != parcelableExtra) {
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                TransferEvent event = new TransferEvent();
                event.setState(networkInfo.getState());
                event.setType(TransferConstant.RxBusEventType.WIFI_CONNECT_CHANGE_EVENT);
                EventBus.getDefault().post(event);
//                RxBus.get().post(TransferConstant.RxBusEventType.WIFI_CONNECT_CHANGE_EVENT, networkInfo.getState());
            }
        }
    }
}
