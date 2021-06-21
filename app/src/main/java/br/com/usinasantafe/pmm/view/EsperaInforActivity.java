package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

        if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 11L){

            ConexaoWeb conexaoWeb = new ConexaoWeb();
            if (conexaoWeb.verificaConexao(this)) {
                if(pmmContext.getConfigCTR().getConfig().getAplic() == 1L){
                    pmmContext.getInformativoCTR().verInfor(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMDAO().getBoletimMMBean().getIdEquipBolMMFert()), EsperaInforActivity.this, MenuPrincPMMActivity.class);
                }
                else if(pmmContext.getConfigCTR().getConfig().getAplic() == 2L){
                    pmmContext.getInformativoCTR().verInfor(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMDAO().getBoletimMMBean().getIdEquipBolMMFert()), EsperaInforActivity.this, MenuPrincECMActivity.class);
                }
                else if(pmmContext.getConfigCTR().getConfig().getAplic() == 3L){
                    pmmContext.getInformativoCTR().verInfor(String.valueOf(pmmContext.getMotoMecFertCTR().getBoletimMMDAO().getBoletimMMBean().getIdEquipBolMMFert()), EsperaInforActivity.this, MenuPrincPCOMPActivity.class);
                }
                customHandler.postDelayed(runnable, 10000);
            }
            else{
                if(pmmContext.getConfigCTR().getConfig().getAplic() == 1L){
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pmmContext.getConfigCTR().getConfig().getAplic() == 2L){
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pmmContext.getConfigCTR().getConfig().getAplic() == 3L){
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
                if(pmmContext.getConfigCTR().getConfig().getAplic() == 1L){
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pmmContext.getConfigCTR().getConfig().getAplic() == 2L){
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pmmContext.getConfigCTR().getConfig().getAplic() == 3L){
                    Intent it = new Intent(EsperaInforActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        }
    };

}
