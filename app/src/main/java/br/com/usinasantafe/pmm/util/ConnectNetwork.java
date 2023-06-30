package br.com.usinasantafe.pmm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectNetwork {

	public ConnectNetwork() {
	}

	public static boolean isConnected(Context context){
		ConnectivityManager connectivityManager = (ConnectivityManager)
				context.getSystemService(context.CONNECTIVITY_SERVICE);
		if(connectivityManager != null){
			NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
			if(info != null){
				for(int i = 0; i < info.length; i++){
					if(info[i].getState() == NetworkInfo.State.CONNECTED){
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
