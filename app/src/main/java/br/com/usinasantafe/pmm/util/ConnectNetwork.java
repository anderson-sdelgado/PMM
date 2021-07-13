package br.com.usinasantafe.pmm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConnectNetwork {

	public ConnectNetwork() {
	}
	
	public  boolean verificaConexao(Context context) {  
	    boolean conectado;  
	    ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); 
	    if (cm.getActiveNetworkInfo() != null  
	            && cm.getActiveNetworkInfo().isAvailable()  
	            && cm.getActiveNetworkInfo().isConnected()) {  
	        conectado = true;
	        Log.i("ECM", "CONECTA");
	    } else {  
	        conectado = false;  
	        Log.i("ECM", "NAO CONECTA");
	    }  
	    return conectado;  
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
