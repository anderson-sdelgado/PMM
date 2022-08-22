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

            if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 13L){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 13L){\n" +
                        "                textEspInfor.setText(\"BUSCANDO ORD. CARREGAMENTO...\");\n" +
                        "                pmmContext.getCompostoCTR().verifDadosCarreg(this, InformacaoActivity.class, getLocalClassName());", getLocalClassName());
                textEspInfor.setText("BUSCANDO ORD. CARREGAMENTO...");
                pmmContext.getCompostoCTR().verifDadosCarreg(this, InforCarregCompActivity.class, getLocalClassName());
            }

        }
        else{

        }

    }

    public void onBackPressed()  {
    }

}
