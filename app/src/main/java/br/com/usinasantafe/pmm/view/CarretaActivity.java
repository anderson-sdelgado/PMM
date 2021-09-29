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

public class CarretaActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreta);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkCarreta = findViewById(R.id.buttonOkPadrao);
        Button buttonCancCarreta = findViewById(R.id.buttonCancPadrao);

        buttonOkCarreta.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonOkCarreta.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @SuppressWarnings(\"rawtypes\")\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if(!editTextPadrao.getText().toString().equals("")){

                    LogProcessoDAO.getInstance().insertLogProcesso("                if(!editTextPadrao.getText().toString().equals(\"\")){\n" +
                            "                    int verCarreta = pmmContext.getMotoMecFertCTR().verCarreta(Long.parseLong(editTextPadrao.getText().toString()));", getLocalClassName());
                    int verCarreta = pmmContext.getMotoMecFertCTR().verCarreta(Long.parseLong(editTextPadrao.getText().toString()));

                    if(verCarreta == 1) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if(verCarreta == 1) {\n" +
                                "                        pmmContext.getMotoMecFertCTR().insCarreta(Long.parseLong(" + editTextPadrao.getText().toString() + "));", getLocalClassName());
                        pmmContext.getMotoMecFertCTR().insCarreta(Long.parseLong(editTextPadrao.getText().toString()));

                        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){

                            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){\n" +
                                    "                            pmmContext.getCecCTR().setCarr(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                                    "                            pmmContext.getCecCTR().setLib(pmmContext.getCecCTR().getOS().getIdLibOS());\n" +
                                    "                            int numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;", getLocalClassName());

                            pmmContext.getCecCTR().setCarr(Long.parseLong(editTextPadrao.getText().toString()));
                            pmmContext.getCecCTR().setLib(pmmContext.getCecCTR().getOS().getIdLibOS());

                            int numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;

                            if (numCarreta < 4) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if (numCarreta < 4) {\n" +
                                        "                                Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                                Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                        "                                Intent it = new Intent(CarretaActivity.this, PergFinalPreCECActivity.class);", getLocalClassName());
                                Intent it = new Intent(CarretaActivity.this, PergFinalPreCECActivity.class);
                                startActivity(it);
                                finish();
                            }

                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                            Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                    else{
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                        String msg = \"\";\n" +
                                "                        int numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;\n" +
                                "                        switch(verCarreta){", getLocalClassName());

                        String msg = "";
                        int numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;
                        switch(verCarreta){
                            case 2:
                                msg = "CARRETA INEXISTENTE NA BASE DE DADOS! POR FAVOR, ATUALIZE OS DADOS.";
                                break;
                            case 3:
                                msg = "ESSA CARRETA JÁ FOI INSERIDA. VERIFIQUE NOVAMENTE A NUMERAÇÃO DA CARRETA.";
                                break;
                            case 4:
                                msg = "A NUMERAÇÃO DIGITADA NÃO CORRESPONDE DA CARRETA " + numCarreta +". VERIFIQUE SE VOCÊ NÃO ESTA INVERTENDO AS CARRETAS.";
                                break;
                        }

                        LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(msg);\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                                editTextPadrao.setText(\"\");\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage(msg);
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editTextPadrao.setText("");
                            }
                        });
                        alerta.show();
                    }
                }

            }
        });

        buttonCancCarreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancCarreta.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                if(editTextPadrao.getText().toString().length() > 0){\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                        "                }", getLocalClassName());
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
        Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);
        startActivity(it);
        finish();
    }

}