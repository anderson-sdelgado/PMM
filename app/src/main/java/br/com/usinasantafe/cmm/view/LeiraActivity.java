package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class LeiraActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leira);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkLeira = findViewById(R.id.buttonOkPadrao);
        Button buttonCancLeira = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());

            AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("                alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                            progressBar = new ProgressDialog(LeiraActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                            progressBar.setProgress(0);\n" +
                            "                            progressBar.setMax(100);\n" +
                            "                            progressBar.show();", getLocalClassName());

                    progressBar = new ProgressDialog(LeiraActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(LeiraActivity.this, LeiraActivity.class, progressBar, \"Leira\", 1, getLocalClassName());", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().atualDados(LeiraActivity.this, LeiraActivity.class, progressBar, "Leira", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                            "                                }\n" +
                            "                            });\n" +
                            "                            alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(LeiraActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> {
                    });
                    alerta1.show();

                }


            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

            alerta.show();

        });

        buttonOkLeira.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkLeira.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @SuppressWarnings(\"rawtypes\")\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {", getLocalClassName());
                if (cmmContext.getCompostoCTR().verLeira(Long.parseLong(editTextPadrao.getText().toString()))) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getCompostoCTR().verLeira(Long.parseLong(editTextPadrao.getText().toString()))){\n" +
                            " if (connectNetwork) {\n" +
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
                    cmmContext.getMotoMecFertCTR().salvarApontLeira(getLongitude(), getLatitude(), getLocalClassName());

                    if (cmmContext.getConfigCTR().getConfig().getFuncaoComposto() == 2L) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getFuncaoCompostagem() == 2L) {\n" +
                                "                            pmmContext.getCompostoCTR().salvarLeiraDescarreg(Long.parseLong(" + editTextPadrao.getText().toString() + "));", getLocalClassName());
                        cmmContext.getCompostoCTR().salvarLeiraDescarreg(Long.parseLong(editTextPadrao.getText().toString()));
                    } else if (cmmContext.getConfigCTR().getConfig().getFuncaoComposto() == 3L) {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else if (pmmContext.getConfigCTR().getConfig().getFuncaoCompostagem() == 3L) {\n" +
                                "                            pmmContext.getCompostoCTR().abrirCarregComposto(Long.parseLong(" + editTextPadrao.getText().toString() + "));", getLocalClassName());
                        cmmContext.getCompostoCTR().abrirCarregComposto(Long.parseLong(editTextPadrao.getText().toString()));
                    }

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().fecharApont();\n" +
                            "                        Intent it = new Intent(LeiraActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().fecharApont(getLocalClassName());
                    Intent it = new Intent(LeiraActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"NUMERAÇÃO DO LEIRA INEXISTENTE! FAVOR VERIFICA A MESMA.\");\n" +
                            "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            }\n" +
                            "                        });\n" +
                            "                        alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NUMERAÇÃO DO LEIRA INEXISTENTE! FAVOR VERIFICA A MESMA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();

                }
            }

        });

        buttonCancLeira.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancLeira.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                if (editTextPadrao.getText().toString().length() > 0) {\n" +
                    "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                    "                }", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(LeiraActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
        Intent it = new Intent(LeiraActivity.this, MenuPrincPCOMPActivity.class);
        startActivity(it);
        finish();
    }

}