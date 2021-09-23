package br.com.usinasantafe.pmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.pmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.ConnectNetwork;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.util.VerifDadosServ;
import br.com.usinasantafe.pmm.view.ActivityGeneric;

public class NetworkChangeListerner extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(ConnectNetwork.isConnected(context)){
            ActivityGeneric.connectNetwork = true;
            LogProcessoDAO.getInstance().insert("if(ConnectNetwork.isConnected(context)){\n" +
                    "            ActivityGeneric.connectNetwork = true;\n" +
                    "Tempo.getInstance().zerarDifTempo()", context.getClass().getName());
            Tempo.getInstance().zerarDifTempo();
            if(VerifDadosServ.status == 1){
                LogProcessoDAO.getInstance().insert("if(VerifDadosServ.status == 1){\n" +
                        "VerifDadosServ.getInstance().reenvioVerif(context.getClass().getName());", context.getClass().getName());
                VerifDadosServ.getInstance().reenvioVerif(context.getClass().getName());
            }
            if (EnvioDadosServ.status == 1) {
                LogProcessoDAO.getInstance().insert("if (EnvioDadosServ.status == 1) {\n" +
                        "EnvioDadosServ.getInstance().envioDados(context.getClass().getName());", context.getClass().getName());
                EnvioDadosServ.getInstance().envioDados(context.getClass().getName());
            }
        }
        else{
            LogProcessoDAO.getInstance().insert("else{\n" +
                    "            ActivityGeneric.connectNetwork = false;", context.getClass().getName());
            ActivityGeneric.connectNetwork = false;
        }
    }

}
