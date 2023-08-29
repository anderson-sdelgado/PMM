package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class DesengCarretaActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private TextView textViewMsgDesengCarreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deseng_carreta);

        pmmContext = (PMMContext) getApplication();
        textViewMsgDesengCarreta = findViewById(R.id.textViewMsgDesengCarreta);

        Button buttonSimDesengate = findViewById(R.id.buttonSimDesengate);
        Button buttonNaoDesengate = findViewById(R.id.buttonNaoDesengate);

        LogProcessoDAO.getInstance().insertLogProcesso("textViewMsgDesengCarreta.setText(pmmContext.getMotoMecFertCTR().getDescrCarreta());", getLocalClassName());
        textViewMsgDesengCarreta.setText(pmmContext.getMotoMecFertCTR().getDescrCarreta());

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

            pmmContext.getMotoMecFertCTR().delCarreta();

            if (connectNetwork) {
                pmmContext.getConfigCTR().setStatusConConfig(1L);
            }
            else{
                pmmContext.getConfigCTR().setStatusConConfig(0L);
            }

            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude());", getLocalClassName());
            pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());

            if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){
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
            if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){
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