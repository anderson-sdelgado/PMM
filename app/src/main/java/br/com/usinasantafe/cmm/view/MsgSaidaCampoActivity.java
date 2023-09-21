package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class MsgSaidaCampoActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_saida_campo);

        cmmContext = (CMMContext) getApplication();

        Button buttonSimSaidaCampo = findViewById(R.id.buttonSimSaidaCampo);
        Button buttonNaoSaidaCampo = findViewById(R.id.buttonNaoSaidaCampo);

        buttonSimSaidaCampo.setOnClickListener(v -> {

            if (cmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                        "                    Toast.makeText(MsgSaidaCampoActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR O APONTAMENTO DE SAÍDA DE CAMPO.\",\n" +
                        "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                Toast.makeText(MsgSaidaCampoActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR O APONTAMENTO DE SAÍDA DE CAMPO.",
                        Toast.LENGTH_LONG).show();

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                        "                else {\n" +
                        "buttonSimSaidaCampo.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                if (connectNetwork) {\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                        "                }\n" +
                        "                else{\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                        "                }", getLocalClassName());
                if (connectNetwork) {
                    cmmContext.getConfigCTR().setStatusConConfig(1L);
                } else {
                    cmmContext.getConfigCTR().setStatusConConfig(0L);
                }

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());\n" +
                        "                pmmContext.getCecCTR().fechaPreCEC();", getLocalClassName());
                cmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());
                cmmContext.getCecCTR().fechaPreCEC();

                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MsgSaidaCampoActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgSaidaCampoActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonNaoSaidaCampo.setOnClickListener(v -> LogProcessoDAO.getInstance().insertLogProcesso("buttonNaoSaidaCampo.setOnClickListener(new View.OnClickListener() {\n" +
                "            @Override\n" +
                "            public void onClick(View v) {", getLocalClassName()));

    }

    public void onBackPressed()  {
    }

}