package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class MsgSaidaCampoActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_saida_campo);

        pmmContext = (PMMContext) getApplication();

        Button buttonSimSaidaCampo = findViewById(R.id.buttonSimSaidaCampo);
        Button buttonNaoSaidaCampo = findViewById(R.id.buttonNaoSaidaCampo);

        buttonSimSaidaCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insert("buttonSimSaidaCampo.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                if (connectNetwork) {\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                        "                }\n" +
                        "                else{\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                        "                }", getLocalClassName());
                if (connectNetwork) {
                    pmmContext.getConfigCTR().setStatusConConfig(1L);
                }
                else{
                    pmmContext.getConfigCTR().setStatusConConfig(0L);
                }

                LogProcessoDAO.getInstance().insert("pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());\n" +
                        "                pmmContext.getCecCTR().fechaPreCEC();", getLocalClassName());
                pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());
                pmmContext.getCecCTR().fechaPreCEC();

                LogProcessoDAO.getInstance().insert("Intent it = new Intent(MsgSaidaCampoActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgSaidaCampoActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonNaoSaidaCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insert("buttonNaoSaidaCampo.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
            }
        });

    }

    public void onBackPressed()  {
    }

}