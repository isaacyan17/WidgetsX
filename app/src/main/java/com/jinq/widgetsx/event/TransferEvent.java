package com.jinq.widgetsx.event;

import android.net.NetworkInfo;

public class TransferEvent {
   private String type;
   private NetworkInfo.State state;
   private int intState;

    public int getIntState() {
        return intState;
    }

    public void setIntState(int intState) {
        this.intState = intState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NetworkInfo.State getState() {
        return state;
    }

    public void setState(NetworkInfo.State state) {
        this.state = state;
    }
}
