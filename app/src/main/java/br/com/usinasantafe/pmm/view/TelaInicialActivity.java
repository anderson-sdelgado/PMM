package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

import br.com.usinasantafe.pmm.BuildConfig;
import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.control.MotoMecFertCTR;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.model.dao.RendimentoMMDAO;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class TelaInicialActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        pmmContext = (PMMContext) getApplication();
        customHandler.postDelayed(excluirBDThread, 0);

//        String dados = pmmContext.getMotoMecFertCTR().dadosEnvioBolFechadoMMFert();
//        Log.i("Envio", dados);

    }

    private Runnable excluirBDThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("clearBD();", getLocalClassName());
            clearBD();

            if(EnvioDadosServ.getInstance().verifDadosEnvio()){
                LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().verifDadosEnvio()", getLocalClassName());
                if(connectNetwork){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(connectNetwork){\n" +
                            "EnvioDadosServ.getInstance().envioDados()", getLocalClassName());
                    EnvioDadosServ.getInstance().envioDados(getLocalClassName());
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                EnvioDadosServ.status = 1;", getLocalClassName());
                    EnvioDadosServ.status = 1;
                }
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "            EnvioDadosServ.status = 3;", getLocalClassName());
                EnvioDadosServ.status = 3;
            }

            LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.status = 3;", getLocalClassName());
            VerifDadosServ.status = 3;

            LogProcessoDAO.getInstance().insertLogProcesso("atualizarAplic()", getLocalClassName());
            atualizarAplic();

        }
    };

    public void clearBD() {
        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCheckListCTR().deleteChecklist();\n" +
                "        pmmContext.getMotoMecFertCTR().deleteBolEnviado();\n" +
                "        pmmContext.getConfigCTR().deleteLogs();", getLocalClassName());
        pmmContext.getCheckListCTR().deleteChecklist();
        pmmContext.getMotoMecFertCTR().deleteBolEnviado();
        pmmContext.getConfigCTR().deleteLogs();
        if(PMMContext.aplic == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().impleMMDelAll();\n" +
                    "            pmmContext.getConfigCTR().osDelAll();\n" +
                    "            pmmContext.getConfigCTR().rOSAtivDelAll();", getLocalClassName());
            pmmContext.getMotoMecFertCTR().impleMMDelAll();
            pmmContext.getConfigCTR().osDelAll();
            pmmContext.getConfigCTR().rOSAtivDelAll();
        }
    }

    public void atualizarAplic(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void atualizarAplic(){", getLocalClassName());
        if (connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {", getLocalClassName());
            if (pmmContext.getConfigCTR().hasElemConfig()) {
                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().hasElemConfig()\n" +
                        "                customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                customHandler.postDelayed(encerraAtualThread, 10000);
                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().verAtualAplic(pmmContext.versaoAplic, this, getLocalClassName());", getLocalClassName());
                pmmContext.getConfigCTR().verAtualAplic(BuildConfig.VERSION_NAME, this, getLocalClassName());
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

    private Runnable encerraAtualThread = new Runnable() {

        public void run() {
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
        }
    };

    public void goMenuInicial(){

        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.removeCallbacks(updateTimerThread);", getLocalClassName());
        customHandler.removeCallbacks(encerraAtualThread);
        if(pmmContext.getMotoMecFertCTR().verBolAberto()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().verBolAberto()){", getLocalClassName());
            if(!pmmContext.getCheckListCTR().verCabecAberto()){
                LogProcessoDAO.getInstance().insertLogProcesso("if(!pmmContext.getCheckListCTR().verCabecAberto()){", getLocalClassName());
                if(!pmmContext.getMotoMecFertCTR().verifBoletimPneuAberto()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(!pmmContext.getMotoMecFertCTR().verifBoletimPneuAberto()){\n" +
                            "                pmmContext.getConfigCTR().setPosicaoTela(8L);", getLocalClassName());
                    pmmContext.getConfigCTR().setPosicaoTela(8L);

                    if(PMMContext.aplic == 1){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                                "Intent it = new Intent(TelaInicialActivity.this, MenuPrincPMMActivity.class)", getLocalClassName());
                        Intent it = new Intent(TelaInicialActivity.this, MenuPrincPMMActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else if(PMMContext.aplic == 2){
                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){", getLocalClassName());
                        if(pmmContext.getCecCTR().verPreCECAberto()){
                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCecCTR().verPreCECAberto()\n" +
                                    "clearPreCECAberto()", getLocalClassName());
                            pmmContext.getCecCTR().clearPreCECAberto(false);
                        }
                        LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(TelaInicialActivity.this, MenuPrincECMActivity.class)", getLocalClassName());
                        Intent it = new Intent(TelaInicialActivity.this, MenuPrincECMActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else if(PMMContext.aplic == 3){
                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                                "Intent it = new Intent(TelaInicialActivity.this, MenuPrincPCOMPActivity.class)", getLocalClassName());
                        Intent it = new Intent(TelaInicialActivity.this, MenuPrincPCOMPActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                            "        else{\n" +
                            "Intent it = new Intent(TelaInicialActivity.this, ListaPosPneuActivity.class);", getLocalClassName());
                    Intent it = new Intent(TelaInicialActivity.this, ListaPosPneuActivity.class);
                    startActivity(it);
                    finish();
                }
            }
            else {
                LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                        "pmmContext.getCheckListCTR().clearRespCabecAberto();\n" +
                        "                pmmContext.getCheckListCTR().setPosCheckList(1);\n" +
                        "                Intent it = new Intent(TelaInicialActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                pmmContext.getCheckListCTR().clearRespCabecAberto();
                pmmContext.getCheckListCTR().setPosCheckList(1);
                Intent it = new Intent(TelaInicialActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();
            }
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                    "        else{\n" +
                    "Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        }

    }

}