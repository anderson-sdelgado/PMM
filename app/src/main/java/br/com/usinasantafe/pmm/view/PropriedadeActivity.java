package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class PropriedadeActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propriedade);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkPropriedade = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPropriedade = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPropriedade = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPropriedade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insert("buttonAtualPropriedade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insert("if (connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(PropriedadeActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();", getLocalClassName());

                    progressBar = new ProgressDialog(PropriedadeActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insert("pmmContext.getMotoMecFertCTR().atualDados(PropriedadeActivity.this, PropriedadeActivity.class, progressBar, \"Propriedade\", 1, getLocalClassName());", getLocalClassName());
                    pmmContext.getMotoMecFertCTR().atualDados(PropriedadeActivity.this, PropriedadeActivity.class, progressBar, "Propriedade", 1, getLocalClassName());

                }
                else {

                    LogProcessoDAO.getInstance().insert("else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insert("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        }
                    });

                    alerta.show();

                }

            }
        });

        buttonOkPropriedade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insert("buttonOkPropriedade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insert("if (!editTextPadrao.getText().toString().equals(\"\")) {", getLocalClassName());
                    if (pmmContext.getConfigCTR().verPropriedade(Long.parseLong(editTextPadrao.getText().toString()))) {

                        LogProcessoDAO.getInstance().insert("if (pmmContext.getConfigCTR().verPropriedade(Long.parseLong(editTextPadrao.getText().toString()))) {\n" +
                                "                        pmmContext.getConfigCTR().setIdPropriedade(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                                "                        Intent it = new Intent(PropriedadeActivity.this, MsgPropriedadeActivity.class);", getLocalClassName());
                        pmmContext.getConfigCTR().setIdPropriedade(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(PropriedadeActivity.this, MsgPropriedadeActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        LogProcessoDAO.getInstance().insert("else{\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"PROPRIEDADE INCORRETA! POR FAVOR, VERIFIQUE O CÓDIGO DA PROPRIEDADE E DIGITE NOVAMENTE.\");", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("PROPRIEDADE INCORRETA! POR FAVOR, VERIFIQUE O CÓDIGO DA PROPRIEDADE E DIGITE NOVAMENTE.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insert("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                            }
                        });
                        alerta.show();

                    }

                }

            }
        });

        buttonCancPropriedade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insert("buttonCancPropriedade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                        "                }", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insert("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(PropriedadeActivity.this, FrenteActivity.class);", getLocalClassName());
        Intent it = new Intent(PropriedadeActivity.this, FrenteActivity.class);
        startActivity(it);
        finish();
    }
}