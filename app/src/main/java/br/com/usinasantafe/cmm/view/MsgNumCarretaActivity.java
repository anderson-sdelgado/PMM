package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class MsgNumCarretaActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private int numCarreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_num_carreta);

        cmmContext = (CMMContext) getApplication();

        TextView textViewMsgNumCarreta = findViewById(R.id.textViewMsgNumCarreta);

        LogProcessoDAO.getInstance().insertLogProcesso("numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;\n" +
                "        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){\n" +
                "            numCarreta = (int) (pmmContext.getConfigCTR().getConfig().getQtdeCarretaConfig() + 1);\n" +
                "            textViewMsgNumCarreta.setText(\"DESEJA INSERIR A CARRETA \" + numCarreta +\"?\");\n" +
                "        }\n" +
                "        else{\n" +
                "            numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;\n" +
                "            textViewMsgNumCarreta.setText(\"DESEJA ENGATAR A CARRETA \" + numCarreta + \"?\");\n" +
                "        }", getLocalClassName());

        numCarreta = cmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;
        if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){
            textViewMsgNumCarreta.setText("DESEJA INSERIR A CARRETA " + numCarreta +"?");
        }
        else{
            textViewMsgNumCarreta.setText("DESEJA ENGATAR A CARRETA " + numCarreta + "?");
        }

        Button buttonOkMsgNumCarreta = findViewById(R.id.buttonOkMsgNumCarreta);
        Button buttonCancMsgNumCarreta = findViewById(R.id.buttonCancMsgNumCarreta);

        buttonOkMsgNumCarreta.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkMsgNumCarreta.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (numCarreta < 4) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (numCarreta < 4) {\n" +
                        "                    Intent it = new Intent(MsgNumCarretaActivity.this, CarretaActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgNumCarretaActivity.this, CarretaActivity.class);
                startActivity(it);
                finish();

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder(MsgNumCarretaActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"PROIBIDO A INSERÇÃO DE MAIS DE 3 CARRETAS.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());

                AlertDialog.Builder alerta = new AlertDialog.Builder(MsgNumCarretaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("PROIBIDO A INSERÇÃO DE MAIS DE 3 CARRETAS.");

                alerta.setPositiveButton("OK", (dialog, which) -> {
                });

                alerta.show();

            }
        });

        buttonCancMsgNumCarreta.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancMsgNumCarreta.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 20L){
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 20L){", getLocalClassName());
                if(numCarreta > 1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(numCarreta < 1){\n" +
                            "                        if (connectNetwork) {\n" +
                            "                            pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                            "                        }\n" +
                            "                        else{\n" +
                            "                            pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                            "                        }", getLocalClassName());
                    if (connectNetwork) {
                        cmmContext.getConfigCTR().setStatusConConfig(1L);
                    }
                    else{
                        cmmContext.getConfigCTR().setStatusConConfig(0L);
                    }
                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().salvarApont(cmmContext, getLongitude(), getLatitude(), getLocalClassName());
                }

                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MsgNumCarretaActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgNumCarretaActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();

            } else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 22L){
                LogProcessoDAO.getInstance().insertLogProcesso("else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 22L){", getLocalClassName());
                if(numCarreta < 1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(numCarreta < 1){\n" +
                            "                        if (connectNetwork) {\n" +
                            "                            pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                            "                        }\n" +
                            "                        else{\n" +
                            "                            pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                            "                        }", getLocalClassName());
                    if (connectNetwork) {
                        cmmContext.getConfigCTR().setStatusConConfig(1L);
                    }
                    else{
                        cmmContext.getConfigCTR().setStatusConConfig(0L);
                    }
                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().salvarApont(cmmContext, getLongitude(), getLatitude(), getLocalClassName());
                }
                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MsgNumCarretaActivity.this, MenuParadaECMActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgNumCarretaActivity.this, ListaParadaECMActivity.class);
                startActivity(it);
                finish();
            }
            else{

                LogProcessoDAO.getInstance().insertLogProcesso("else{", getLocalClassName());
                if((cmmContext.getConfigCTR().getEquip().getCodClasseEquip() != 1L) && (numCarreta == 1)) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if((pmmContext.getConfigCTR().getEquip().getCodClasseEquip() != 1L) && (pmmContext.getMotoMecFertCTR().qtdeCarreta() == 0)) {\n" +
                            "                        Intent it = new Intent(MsgNumCarretaActivity.this, EquipActivity.class);", getLocalClassName());
                    Intent it = new Intent(MsgNumCarretaActivity.this, EquipActivity.class);
                    startActivity(it);

                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "Intent it = new Intent(MsgNumCarretaActivity.this, PergFinalPreCECActivity.class);", getLocalClassName());
                    Intent it = new Intent(MsgNumCarretaActivity.this, PergFinalPreCECActivity.class);
                    startActivity(it);
                }
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}