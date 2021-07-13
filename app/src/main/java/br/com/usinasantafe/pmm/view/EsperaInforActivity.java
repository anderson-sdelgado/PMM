package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
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

            if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){

                customHandler.postDelayed(runnable, 10000);

                if(PMMContext.aplic == 1){
                    pmmContext.getInformativoCTR().verifDadosInformativo(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().getMatricFuncBolMMFert()), EsperaInforActivity.this, MenuPrincPMMActivity.class);
                }
                else if(PMMContext.aplic == 2){
                    pmmContext.getInformativoCTR().verifDadosInformativo(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().getMatricFuncBolMMFert()), EsperaInforActivity.this, MenuPrincECMActivity.class);
                }
                else if(PMMContext.aplic == 3){
                    pmmContext.getInformativoCTR().verifDadosInformativo(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().getMatricFuncBolMMFert()), EsperaInforActivity.this, MenuPrincPCOMPActivity.class);
                }

            }
            else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 13L){
                textEspInfor.setText("BUSCANDO ORD. CARREGAMENTO...");
                pmmContext.getCompostoCTR().verifDadosCarreg(this, InformacaoActivity.class);
            }

        }
        else{
            VerifDadosServ.status = 3;
            if(PMMContext.aplic == 1){
                Intent it = new Intent(EsperaInforActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 2){
                Intent it = new Intent(EsperaInforActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 3){
                Intent it = new Intent(EsperaInforActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        }

    }

    private Runnable runnable = new Runnable(){
        public void run() {
            if(VerifDadosServ.status < 3) {
                VerifDadosServ.status = 3;
                if(PMMContext.aplic == 1){
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 2){
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 3){
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        }
    };

}
