package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class CarretaActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreta);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkCarreta = findViewById(R.id.buttonOkPadrao);
        Button buttonCancCarreta = findViewById(R.id.buttonCancPadrao);

        buttonOkCarreta.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonOkCarreta.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @SuppressWarnings(\"rawtypes\")\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if(!editTextPadrao.getText().toString().equals("")){

                LogProcessoDAO.getInstance().insertLogProcesso("                if(!editTextPadrao.getText().toString().equals(\"\")){\n" +
                        "                    int verCarreta = pmmContext.getMotoMecFertCTR().verCarreta(Long.parseLong(editTextPadrao.getText().toString()));", getLocalClassName());
                int verCarreta = cmmContext.getMotoMecFertCTR().verCarreta(Long.parseLong(editTextPadrao.getText().toString()));

                if(verCarreta == 1) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if(verCarreta == 1) {", getLocalClassName());
                    if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){\n" +
                                "                            pmmContext.getConfigCTR().setCarreta(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                                "                            Intent it = new Intent(CarretaActivity.this, OSActivity.class);", getLocalClassName());
                        cmmContext.getConfigCTR().setCarreta(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(CarretaActivity.this, OSActivity.class);
                        startActivity(it);

                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                            pmmContext.getMotoMecFertCTR().insCarreta(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                                "                            Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                        cmmContext.getMotoMecFertCTR().insCarreta(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);
                        startActivity(it);
                    }
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                        String msg = \"\";\n" +
                            "                        int numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;\n" +
                            "                        switch(verCarreta){", getLocalClassName());

                    String msg = "";
                    int numCarreta = cmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;
                    String posCarreta = "";
                    switch(numCarreta){
                        case 1:
                            posCarreta = "PRIMEIRA";
                            break;
                        case 2:
                            posCarreta = "SEGUNDA";
                            break;
                        case 3:
                            posCarreta = "TERCEIRA";
                            break;
                        case 4:
                            posCarreta = "QUARTA";
                            break;
                    }

                    switch(verCarreta){
                        case 2:
                            msg = "CARRETA INEXISTENTE NA BASE DE DADOS! POR FAVOR, ATUALIZE OS DADOS.";
                            break;
                        case 3:
                            msg = "ESSA CARRETA JÁ FOI INSERIDA. VERIFIQUE NOVAMENTE A NUMERAÇÃO DA CARRETA.";
                            break;
                        case 4:
                            msg = "O EQUIPAMENTO REGISTRADO NÃO CORRESPONDE A " + posCarreta + " CARRETA DO CONJUNTO. VERIFIQUE SE A NUMERAÇÃO ESTÁ CORRETA E TENTE NOVAMENTE!";
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
                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();
                }
            }

        });

        buttonCancCarreta.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancCarreta.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                if(editTextPadrao.getText().toString().length() > 0){\n" +
                    "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                    "                }", getLocalClassName());
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
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