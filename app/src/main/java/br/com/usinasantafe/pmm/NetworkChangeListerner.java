package br.com.usinasantafe.pmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.pmm.util.ConnectNetwork;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.VerifDadosServ;
import br.com.usinasantafe.pmm.view.ActivityGeneric;

public class NetworkChangeListerner extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectNetwork.isConnected(context)){
            Log.i("PMM", "CONECTA RECEIVER - STATUS ENVIO = " + EnvioDadosServ.status + " - STATUS VERIF = " + VerifDadosServ.status);
            ActivityGeneric.connectNetwork = true;
            if(VerifDadosServ.status == 1){
                VerifDadosServ.getInstance().reenvioVerif();
            }
            if (EnvioDadosServ.status == 1) {
                Log.i("PMM", "ENVIANDO PELO TIMER");
			    EnvioDadosServ.getInstance().envioDados(1);
            }
        }
        else{
            Log.i("PMM", "NÃO CONECTA RECEIVER");
            ActivityGeneric.connectNetwork = false;
        }
    }
}
