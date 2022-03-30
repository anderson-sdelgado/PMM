package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class EsperaInforActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_infor);

        pmmContext = (PMMContext) getApplication();

        TextView textEspInfor = findViewById(R.id.textEspInfor);

        if (connectNetwork) {

            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {", getLocalClassName());
            if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){

                LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){\n" +
                        "                customHandler.postDelayed(runnable, 10000);", getLocalClassName());
                customHandler.postDelayed(runnable, 10000);

                if(PMMContext.aplic == 1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                            "pmmContext.getInformativoCTR().verifDadosInformativo(String.valueOf(" + pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFert().getMatricFuncBolMMFert() + "), EsperaInforActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    pmmContext.getInformativoCTR().verifDadosInformativo(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFert().getMatricFuncBolMMFert()), EsperaInforActivity.this, MenuPrincPMMActivity.class, getLocalClassName());
                }
                else if(PMMContext.aplic == 2){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 2){\n" +
                            "pmmContext.getInformativoCTR().verifDadosInformativo(String.valueOf(" + pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFert().getMatricFuncBolMMFert() + "), EsperaInforActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    pmmContext.getInformativoCTR().verifDadosInformativo(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFert().getMatricFuncBolMMFert()), EsperaInforActivity.this, MenuPrincECMActivity.class, getLocalClassName());
                }
                else if(PMMContext.aplic == 3){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 3){\n" +
                            "pmmContext.getInformativoCTR().verifDadosInformativo(String.valueOf(" + pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFert().getMatricFuncBolMMFert() + "), EsperaInforActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    pmmContext.getInformativoCTR().verifDadosInformativo(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFert().getMatricFuncBolMMFert()), EsperaInforActivity.this, MenuPrincPCOMPActivity.class, getLocalClassName());
                }

            }
            else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 13L){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 13L){\n" +
                        "                textEspInfor.setText(\"BUSCANDO ORD. CARREGAMENTO...\");\n" +
                        "                pmmContext.getCompostoCTR().verifDadosCarreg(this, InformacaoActivity.class, getLocalClassName());", getLocalClassName());
                textEspInfor.setText("BUSCANDO ORD. CARREGAMENTO...");
                pmmContext.getCompostoCTR().verifDadosCarreg(this, InforCarregCompActivity.class, getLocalClassName());
            }

        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            VerifDadosServ.status = 3;", getLocalClassName());
            VerifDadosServ.status = 3;
            if(PMMContext.aplic == 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                        "                Intent it = new Intent(EsperaInforActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(EsperaInforActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 2){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                        "                Intent it = new Intent(EsperaInforActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(EsperaInforActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 3){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                        "                Intent it = new Intent(EsperaInforActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(EsperaInforActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        }

    }

    public void onBackPressed()  {
    }

    private Runnable runnable = new Runnable(){
        public void run() {
            LogProcessoDAO.getInstance().insertLogProcesso("private Runnable runnable = new Runnable(){\n" +
                    "        public void run() {", getLocalClassName());
            if(VerifDadosServ.status < 3) {
                LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status < 3) {\n" +
                        "VerifDadosServ.status = 3;", getLocalClassName());
                VerifDadosServ.status = 3;
                if(PMMContext.aplic == 1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                            "                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 2){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                            "                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 3){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                            "                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        }
    };

}
