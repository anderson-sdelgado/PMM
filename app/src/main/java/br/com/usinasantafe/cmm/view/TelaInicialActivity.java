package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.ConnectNetwork;
import br.com.usinasantafe.cmm.util.EnvioDadosServ;
import br.com.usinasantafe.cmm.util.VerifDadosServ;
import br.com.usinasantafe.cmm.util.workmanager.StartProcessEnvio;

public class TelaInicialActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        cmmContext = (CMMContext) getApplication();
        customHandler.postDelayed(excluirBDThread, 0);

    }

    private Runnable excluirBDThread = () -> {

        LogProcessoDAO.getInstance().insertLogProcesso("clearBD();", getLocalClassName());
        clearBD();

        LogProcessoDAO.getInstance().insertLogProcesso("StartProcessEnvio startProcessEnvio = new StartProcessEnvio();\n" +
                "        startProcessEnvio.startProcessEnvio(cmmContext);", getLocalClassName());
        StartProcessEnvio startProcessEnvio = new StartProcessEnvio();
        startProcessEnvio.startProcessEnvio(cmmContext);

        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.status = 3;\n" +
                "        atualizarAplic();", getLocalClassName());
        VerifDadosServ.status = 3;
        atualizarAplic();

    };

    public void clearBD() {
        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCheckListCTR().deleteChecklist();\n" +
                "        pmmContext.getMotoMecFertCTR().deleteBolEnviado();\n" +
                "        pmmContext.getConfigCTR().deleteLogs();", getLocalClassName());
        cmmContext.getCheckListCTR().deleteChecklist();
        cmmContext.getMotoMecFertCTR().deleteBolEnviado();
        cmmContext.getConfigCTR().deleteLogs();
        if(BuildConfig.FLAVOR.equals("pmm")){
            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().impleMMDelAll();\n" +
                    "            pmmContext.getConfigCTR().osDelAll();\n" +
                    "            pmmContext.getConfigCTR().rOSAtivDelAll();", getLocalClassName());
            cmmContext.getMotoMecFertCTR().impleMMDelAll();
            cmmContext.getConfigCTR().osDelAll();
            cmmContext.getConfigCTR().rOSAtivDelAll();
        }
    }

    public void atualizarAplic(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void atualizarAplic(){", getLocalClassName());
        if (ConnectNetwork.isConnected(this)) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {", getLocalClassName());
            if (cmmContext.getConfigCTR().hasElemConfig()) {
                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().hasElemConfig()\n" +
                        "                customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                customHandler.postDelayed(encerraAtualThread, 10000);
                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().verAtualAplic(pmmContext.versaoAplic, this, getLocalClassName());", getLocalClassName());
                cmmContext.getConfigCTR().verAtualAplic(BuildConfig.VERSION_NAME, this, getLocalClassName());
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                VerifDadosServ.status = 3;\n" +
                        "goMenuInicial();", getLocalClassName());
                VerifDadosServ.status = 3;
                goMenuInicial();
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "                VerifDadosServ.status = 3;\n" +
                    "goMenuInicial();", getLocalClassName());
            VerifDadosServ.status = 3;
            goMenuInicial();
        }
    }

    private Runnable encerraAtualThread = () -> {
        LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                "        public void run() {", getLocalClassName());
        LogProcessoDAO.getInstance().insertLogProcesso("verifEnvio();", getLocalClassName());
        if(VerifDadosServ.status < 3) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status < 3) {\n" +
                    "VerifDadosServ.getInstance().cancel();", getLocalClassName());
            VerifDadosServ.getInstance().cancel();
        }
        LogProcessoDAO.getInstance().insertLogProcesso("goMenuInicial();", getLocalClassName());
        goMenuInicial();
    };

    public void goMenuInicial(){

        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.removeCallbacks(updateTimerThread);", getLocalClassName());
        customHandler.removeCallbacks(encerraAtualThread);
        if(cmmContext.getMotoMecFertCTR().verBolAberto()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().verBolAberto()){", getLocalClassName());
            if(!cmmContext.getCheckListCTR().verCabecAberto()){
                LogProcessoDAO.getInstance().insertLogProcesso("if(!pmmContext.getCheckListCTR().verCabecAberto()){", getLocalClassName());
                if(!cmmContext.getPneuCTR().verifBolPneuAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(!pmmContext.getMotoMecFertCTR().verifBoletimPneuAberto()){\n" +
                            "                pmmContext.getConfigCTR().setPosicaoTela(8L);", getLocalClassName());
                    cmmContext.getConfigCTR().setPosicaoTela(8L);

                    Intent it;
                    switch (BuildConfig.FLAVOR){
                        case "pmm":
                            LogProcessoDAO.getInstance().insertLogProcesso("case \"pmm\":\n" +
                                    "it = new Intent(TelaInicialActivity.this, MenuPrincPMMActivity.class)", getLocalClassName());
                            cmmContext.getConfigCTR().setIdEquipApontConfig();
                            it = new Intent(TelaInicialActivity.this, MenuPrincPMMActivity.class);
                            break;
                        case "ecm":
                            LogProcessoDAO.getInstance().insertLogProcesso("case \"ecm\":", getLocalClassName());
                            if(cmmContext.getCecCTR().verPreCECAberto()){
                                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCecCTR().verPreCECAberto()\n" +
                                        "clearPreCECAberto()", getLocalClassName());
                                cmmContext.getCecCTR().clearPreCECAberto();
                            }
                            LogProcessoDAO.getInstance().insertLogProcesso("it = new Intent(TelaInicialActivity.this, MenuPrincECMActivity.class)", getLocalClassName());
                            it = new Intent(TelaInicialActivity.this, MenuPrincECMActivity.class);
                            break;
                        default:
                            LogProcessoDAO.getInstance().insertLogProcesso("default:\n" +
                                    "it = new Intent(TelaInicialActivity.this, MenuPrincPCOMPActivity.class)", getLocalClassName());
                            it = new Intent(TelaInicialActivity.this, MenuPrincPCOMPActivity.class);
                    }
                    startActivity(it);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "Intent it = new Intent(TelaInicialActivity.this, ListaPosPneuActivity.class);", getLocalClassName());
                    Intent it = new Intent(TelaInicialActivity.this, ListaPosPneuActivity.class);
                    startActivity(it);
                }
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "pmmContext.getCheckListCTR().clearRespCabecAberto();\n" +
                        "                pmmContext.getCheckListCTR().setPosCheckList(1);\n" +
                        "                Intent it = new Intent(TelaInicialActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                cmmContext.getCheckListCTR().clearRespCabecAberto();
                cmmContext.getCheckListCTR().setPosCheckList(1);
                Intent it = new Intent(TelaInicialActivity.this, ItemCheckListActivity.class);
                startActivity(it);
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
            startActivity(it);
        }
        finish();

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