package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.util.ConexaoWeb;
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

        Log.i("PMM", "posicao tela = " + pmmContext.getConfigCTR().getConfig().getPosicaoTela());

        if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){

            ConexaoWeb conexaoWeb = new ConexaoWeb();
            if (conexaoWeb.verificaConexao(this)) {
                if(PMMContext.aplic == 1){
                    pmmContext.getInformativoCTR().verInfor(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMDAO().getBoletimMMBean().getIdEquipBolMMFert()), EsperaInforActivity.this, MenuPrincPMMActivity.class);
                }
                else if(PMMContext.aplic == 2){
                    pmmContext.getInformativoCTR().verInfor(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMDAO().getBoletimMMBean().getIdEquipBolMMFert()), EsperaInforActivity.this, MenuPrincECMActivity.class);
                }
                else if(PMMContext.aplic == 3){
                    pmmContext.getInformativoCTR().verInfor(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMDAO().getBoletimMMBean().getIdEquipBolMMFert()), EsperaInforActivity.this, MenuPrincPCOMPActivity.class);
                }
                customHandler.postDelayed(runnable, 10000);
            }
            else{
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
        else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 13L){
            textEspInfor.setText("BUSCANDO ORD. CARREGAMENTO...");
            pmmContext.getCompostoCTR().pesqCarregProduto(this, InformacaoActivity.class);
        }
        else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 14L){
            textEspInfor.setText("PESQUISANDO CARREGAMENTO...");
            pmmContext.getCompostoCTR().pesqCarregComposto(this, InformacaoActivity.class);
        }



    }

    private Runnable runnable = new Runnable(){
        public void run() {
            if(!VerifDadosServ.getInstance().isVerTerm()) {
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
