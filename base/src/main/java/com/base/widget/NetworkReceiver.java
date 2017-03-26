package com.base.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 俞智威
 * on 2015-09-09
 * 14:39
 * Procedure Explain:
 */
public class NetworkReceiver extends BroadcastReceiver {

    public static final String TAG = "NetworkReceiver";


    public static final List<Observable> observableList = new ArrayList<Observable>();

    public static void registerObservable(Observable observable){

        observableList.add(observable);

    }

    public static void unregisterObservable(Observable observable) {

        observableList.remove(observable);

    }


    public static enum NETWORK{

        WIFI,MOBILE,NONE

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        State wifiState = null;
        State mobileState = null;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        NETWORK network = null;

        if (wifiState != null && mobileState != null && State.CONNECTED != wifiState && State.CONNECTED == mobileState) {

            network = NETWORK.MOBILE;

        } else if (wifiState != null && mobileState != null&& State.CONNECTED != wifiState && State.CONNECTED != mobileState) {

            network = NETWORK.NONE;

        } else if (wifiState != null && State.CONNECTED == wifiState) {

            network = NETWORK.WIFI;

        }

        for (Observable observable : observableList)
            observable.onNetworkChange(network);



    }


    public static interface Observable {

        void  onNetworkChange(NETWORK type);

    }
}
