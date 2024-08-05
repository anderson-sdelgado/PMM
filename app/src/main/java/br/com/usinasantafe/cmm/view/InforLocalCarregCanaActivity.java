package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.variaveis.LocalCarregBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class InforLocalCarregCanaActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private LocalCarregBean localCarregBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_local_carreg_cana);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkLocalCarreg = findViewById(R.id.buttonOkLocalCarreg);
        TextView textViewFrente = findViewById(R.id.textViewFrente);
        TextView textViewSecao = findViewById(R.id.textViewSecao);
        TextView textViewCaminho = findViewById(R.id.textViewCaminho);

        localCarregBean = cmmContext.getMotoMecFertCTR().getLocalCarreg();
        textViewFrente.setText("FRENTE: " + localCarregBean.getCodFrente());
        textViewSecao.setText("SEÇÃO: " + localCarregBean.getCodPropriedade() + " - " + localCarregBean.getDescrPropriedade());
        textViewCaminho.setText("CAMINHO: " + localCarregBean.getDescrCaminho());

        if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 2) {
            cmmContext.getConfigCTR().setFrentePropriedade(localCarregBean.getCodFrente(), localCarregBean.getCodPropriedade());
            cmmContext.getConfigCTR().clearOSAtiv();
            cmmContext.getCecCTR().salvarPrecCECAberto();
            cmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
        }

        buttonOkLocalCarreg.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkLocalCarreg.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(InforCarregColheitaActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
            Intent it = new Intent(InforLocalCarregCanaActivity.this, MenuPrincECMActivity.class);
            startActivity(it);
            finish();

        });


    }
}