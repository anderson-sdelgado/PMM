package br.com.usinasantafe.pmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.ConnectNetwork;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.util.VerifDadosServ;
import br.com.usinasantafe.pmm.view.ActivityGeneric;

public class NetworkChangeListerner extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        logConfig();
//        logProcesso();
//        logErro();
        if(ConnectNetwork.isConnected(context)){
            ActivityGeneric.connectNetwork = true;
            LogProcessoDAO.getInstance().insertLogProcesso("if(ConnectNetwork.isConnected(context)){\n" +
                    "            ActivityGeneric.connectNetwork = true;\n" +
                    "Tempo.getInstance().zerarDifTempo()", context.getClass().getName());
            Tempo.getInstance().zerarDifTempo();
            if(VerifDadosServ.status == 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status == 1){\n" +
                        "VerifDadosServ.getInstance().reenvioVerif(context.getClass().getName());", context.getClass().getName());
                VerifDadosServ.getInstance().reenvioVerif(context.getClass().getName());
            }
            if (EnvioDadosServ.status == 1) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (EnvioDadosServ.status == 1) {\n" +
                        "EnvioDadosServ.getInstance().envioDados(context.getClass().getName());", context.getClass().getName());
                EnvioDadosServ.getInstance().envioDados(context.getClass().getName());
            }
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            ActivityGeneric.connectNetwork = false;", context.getClass().getName());
            ActivityGeneric.connectNetwork = false;
        }
    }

    public void logConfig(){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.all();
        for(ConfigBean configBeanBD : configList){
            Log.i("PMM", dadosConfig(configBeanBD));
        }
    }

    private String dadosConfig(ConfigBean configBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(configBean, configBean.getClass()).toString();
    }

    public void logProcesso(){
        LogProcessoBean logProcessoBean = new LogProcessoBean();
        List<LogProcessoBean> logProcessoList = logProcessoBean.orderBy("idLogProcesso", false);
        for(LogProcessoBean logProcessoBeanBD : logProcessoList){
            Log.i("PMM", dadosProcesso(logProcessoBeanBD));
        }
    }

    private String dadosProcesso(LogProcessoBean logProcessoBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(logProcessoBean, logProcessoBean.getClass()).toString();
    }

    public void logErro(){
        LogErroBean logErroBean = new LogErroBean();
        List<LogErroBean> logErroList = logErroBean.orderBy("idLogErro", false);
        Log.i("PMM", "Log Erro");
        for(LogErroBean logErroBeanBD : logErroList){
            Log.i("PMM", dadosErro(logErroBeanBD));
        }
    }

    private String dadosErro(LogErroBean logErroBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(logErroBean, logErroBean.getClass()).toString();
    }

}
