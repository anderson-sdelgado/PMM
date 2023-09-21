package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class LiberacaoActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liberacao);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkLiberacao = findViewById(R.id.buttonOkPadrao);
        Button buttonCancLiberacao = findViewById(R.id.buttonCancPadrao);

        buttonOkLiberacao.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkOS.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    Long idLib = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                Long idLib = Long.parseLong(editTextPadrao.getText().toString());

                if (cmmContext.getConfigCTR().verLib(idLib)) {
                    cmmContext.getCecCTR().setLib(idLib);
                    int numCarreta = cmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().verLib(idLib)) {\n" +
                            "                        pmmContext.getMotoMecFertCTR().insCarreta(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                            "                        pmmContext.getCecCTR().setLib(" + idLib + ");\n" +
                            "                        int numCarreta = pmmContext.getMotoMecFertCTR().qtdeCarreta() + 1;", getLocalClassName());
                    if (numCarreta < 4) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (numCarreta < 4) {\n" +
                                "                                Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                        Intent it = new Intent(LiberacaoActivity.this, MsgNumCarretaActivity.class);
                        startActivity(it);
                    }
                    else{
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                                Intent it = new Intent(CarretaActivity.this, PergFinalPreCECActivity.class);", getLocalClassName());
                        Intent it = new Intent(LiberacaoActivity.this, PergFinalPreCECActivity.class);
                        startActivity(it);
                    }
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(LiberacaoActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"ORDEM SERVIÇO INCORRETA! POR FAVOR, VERIFIQUE A NUMERAÇÃO DIGITADA DA ORDEM SERVIÇO.\");\n" +
                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                            "                                }\n" +
                            "                            });\n" +
                            "                            alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(LiberacaoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("LIBERAÇÃO INCORRETA! POR FAVOR, VERIFIQUE A NUMERAÇÃO DIGITADA DA ORDEM SERVIÇO E DA LIBERAÇÃO, SE AS DUAS ESTÃO CORRETOS.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }
            }
        });

        buttonCancLiberacao.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancOS.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });


    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(LiberacaoActivity.this, OSActivity.class);", getLocalClassName());
        Intent it = new Intent(LiberacaoActivity.this, OSActivity.class);
        startActivity(it);
        finish();
    }

}