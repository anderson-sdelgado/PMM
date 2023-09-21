package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class DesengCarretaActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private TextView textViewMsgDesengCarreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deseng_carreta);

        cmmContext = (CMMContext) getApplication();
        textViewMsgDesengCarreta = findViewById(R.id.textViewMsgDesengCarreta);

        Button buttonSimDesengate = findViewById(R.id.buttonSimDesengate);
        Button buttonNaoDesengate = findViewById(R.id.buttonNaoDesengate);

        LogProcessoDAO.getInstance().insertLogProcesso("textViewMsgDesengCarreta.setText(pmmContext.getMotoMecFertCTR().getDescrCarreta());", getLocalClassName());
        textViewMsgDesengCarreta.setText(cmmContext.getMotoMecFertCTR().getDescrCarreta());

        buttonSimDesengate.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonSimDesengate.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                pmmContext.getMotoMecFertCTR().delCarreta();\n" +
                    "                if (connectNetwork) {\n" +
                    "                    pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                    "                }\n" +
                    "                else{\n" +
                    "                    pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                    "                }", getLocalClassName());

            cmmContext.getMotoMecFertCTR().delCarreta();

            if (connectNetwork) {
                cmmContext.getConfigCTR().setStatusConConfig(1L);
            }
            else{
                cmmContext.getConfigCTR().setStatusConConfig(0L);
            }

            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude());", getLocalClassName());
            cmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());

            if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){\n" +
                        "                    Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                    Intent it = new Intent(DesengCarretaActivity.this, MenuParadaECMActivity.class);", getLocalClassName());
                Intent it = new Intent(DesengCarretaActivity.this, ListaParadaECMActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonNaoDesengate.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonNaoDesengate.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){\n" +
                        "                    Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                    Intent it = new Intent(DesengCarretaActivity.this, MenuParadaECMActivity.class);", getLocalClassName());
                Intent it = new Intent(DesengCarretaActivity.this, ListaParadaECMActivity.class);
                startActivity(it);
            }
            finish();
        });

    }
}