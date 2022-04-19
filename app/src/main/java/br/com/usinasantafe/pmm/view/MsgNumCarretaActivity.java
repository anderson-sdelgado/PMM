package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class MsgNumCarretaActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private int numCarreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_num_carreta);

        pmmContext = (PMMContext) getApplication();

        TextView textViewMsgNumCarreta = findViewById(R.id.textViewMsgNumCarreta);

        LogProcessoDAO.getInstance().insertLogProcesso("numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;\n" +
                "        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){\n" +
                "            textViewMsgNumCarreta.setText(\"DESEJA INSERIR A CARRETA \" + numCarreta +\"?\");\n" +
                "        }\n" +
                "        else{\n" +
                "            textViewMsgNumCarreta.setText(\"DESEJA ENGATAR A CARRETA \" + numCarreta + \"?\");\n" +
                "        }", getLocalClassName());
        numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;
        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){
            textViewMsgNumCarreta.setText("DESEJA INSERIR A CARRETA " + numCarreta +"?");
        }
        else{
            textViewMsgNumCarreta.setText("DESEJA ENGATAR A CARRETA " + numCarreta + "?");
        }

        Button buttonOkMsgNumCarreta = findViewById(R.id.buttonOkMsgNumCarreta);
        Button buttonCancMsgNumCarreta = findViewById(R.id.buttonCancMsgNumCarreta);

        buttonOkMsgNumCarreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    alerta.show();

                }
            }
        });

        buttonCancMsgNumCarreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancMsgNumCarreta.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 20L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 20L){", getLocalClassName());
                    if(numCarreta < 1){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(numCarreta < 1){\n" +
                                "                        if (connectNetwork) {\n" +
                                "                            pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                "                        }\n" +
                                "                        else{\n" +
                                "                            pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                "                        }", getLocalClassName());
                        if (connectNetwork) {
                            pmmContext.getConfigCTR().setStatusConConfig(1L);
                        }
                        else{
                            pmmContext.getConfigCTR().setStatusConConfig(0L);
                        }
                        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                        pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                    }

                    LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MsgNumCarretaActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(MsgNumCarretaActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 22L){
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
                            pmmContext.getConfigCTR().setStatusConConfig(1L);
                        }
                        else{
                            pmmContext.getConfigCTR().setStatusConConfig(0L);
                        }
                        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                        pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                    }
                    LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MsgNumCarretaActivity.this, MenuParadaECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(MsgNumCarretaActivity.this, ListaParadaECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "Intent it = new Intent(MsgNumCarretaActivity.this, PergFinalPreCECActivity.class);", getLocalClassName());
                    Intent it = new Intent(MsgNumCarretaActivity.this, PergFinalPreCECActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}