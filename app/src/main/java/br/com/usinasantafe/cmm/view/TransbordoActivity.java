package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.cmm.control.ConfigCTR;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class TransbordoActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transbordo);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkTransbordo = findViewById(R.id.buttonOkPadrao);
        Button buttonCancTransbordo = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                            progressBar = new ProgressDialog(TransbordoActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                            progressBar.setProgress(0);\n" +
                            "                            progressBar.setMax(100);\n" +
                            "                            progressBar.show();", getLocalClassName());

                    progressBar = new ProgressDialog(TransbordoActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(TransbordoActivity.this, TransbordoActivity.class, progressBar, \"EquipSeg\", 1, getLocalClassName());", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().atualDados(TransbordoActivity.this, TransbordoActivity.class, progressBar, "EquipSeg", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                            "                                }\n" +
                            "                            });\n" +
                            "                            alerta.show();", getLocalClassName());
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(TransbordoActivity.this);
                    alertDialog.setTitle("ATENÇÃO");
                    alertDialog.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alertDialog.setPositiveButton("OK", (dialog1, which1) -> {
                    });
                    alertDialog.show();

                }


            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });


        buttonOkTransbordo.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkTransbordo.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    Long idTransb = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                Long idTransb = Long.parseLong(editTextPadrao.getText().toString());
                if(cmmContext.getMotoMecFertCTR().verTransb(idTransb)) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().verTransb(idTransb)) {", getLocalClassName());
                    if (cmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().verTransb(idTransb)) {\n" +
                                "                            AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);\n" +
                                "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                            alerta.setMessage(\"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\");\n" +
                                "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                                @Override\n" +
                                "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                "                                    Intent it = new Intent(TransbordoActivity.this, MenuPrincPMMActivity.class);\n" +
                                "                                    startActivity(it);\n" +
                                "                                    finish();\n" +
                                "                                }\n" +
                                "                            });\n" +
                                "                            alerta.show();", getLocalClassName());

                        AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.");
                        alerta.setPositiveButton("OK", (dialog, which) -> {
                            Intent it = new Intent(TransbordoActivity.this, MenuPrincPMMActivity.class);
                            startActivity(it);
                            finish();
                        });
                        alerta.show();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                        if (cmmContext.getMotoMecFertCTR().verifBackupApontTransb(0L, idTransb)) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verifBackupApontTransb(0L, idTransb)) {\n" +
                                    "AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"NUMERAÇÃO DE TRANSBORDO COM MESMO VALOR DO APONTAMENTO ANTERIOR. FAVOR, VERIFICAR A NUMERAÇÃO DIGITADA!\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());

                            AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("NUMERAÇÃO DE TRANSBORDO COM MESMO VALOR DO APONTAMENTO ANTERIOR. FAVOR, VERIFICAR A NUMERAÇÃO DIGITADA!");
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });
                            alerta.show();

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                            if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 2L) {
                                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(0L, " + idTransb + ", getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                                cmmContext.getMotoMecFertCTR().salvarApont(cmmContext, 0L, idTransb, getLongitude(), getLatitude(), getLocalClassName());
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().inserirApontTransb(" + idTransb + ", getLocalClassName());", getLocalClassName());
                                cmmContext.getMotoMecFertCTR().inserirApontTransb(cmmContext, idTransb, getLocalClassName());
                            }

                            LogProcessoDAO.getInstance().insertLogProcesso("List<RFuncaoAtivParBean> rFuncaoAtividadeList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());", getLocalClassName());
                            List<RFuncaoAtivParBean> rFuncaoAtividadeList = cmmContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());

                            boolean rendimento = false;

                            for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {
                                LogProcessoDAO.getInstance().insertLogProcesso("for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {", getLocalClassName());
                                if (rFuncaoAtivParBean.getCodFuncao() == 1) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("if (rFuncaoAtivParBean.getCodFuncao() == 1) {\n" +
                                            "                                        rendimento = true;", getLocalClassName());
                                    rendimento = true;
                                }
                            }
                            rFuncaoAtividadeList.clear();

                            if (rendimento) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if (rendimento) {\n" +
                                        "                                    ConfigCTR configCTR = new ConfigCTR();\n" +
                                        "                                    pmmContext.getMotoMecFertCTR().insRendBD(configCTR.getConfig().getNroOSConfig(), getLocalClassName());", getLocalClassName());
                                ConfigCTR configCTR = new ConfigCTR();
                                cmmContext.getMotoMecFertCTR().insRendBD(configCTR.getConfig().getNroOSConfig(), getLocalClassName());
                            }

                            LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(TransbordoActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                            Intent it = new Intent(TransbordoActivity.this, MenuPrincPMMActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENCAO\");\n" +
                            "                        alerta.setMessage(\"NUMERAÇÃO DE TRANSBORDO INCORRETA. FAVOR, VERIFICAR A NUMERAÇÃO OU ATUALIZAR A BASE DE DADOS NOVAMENTE!\");\n" +
                            "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            }\n" +
                            "                        });\n" +
                            "                        alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);
                    alerta.setTitle("ATENCAO");
                    alerta.setMessage("NUMERAÇÃO DE TRANSBORDO INCORRETA. FAVOR, VERIFICAR A NUMERAÇÃO OU ATUALIZAR A BASE DE DADOS NOVAMENTE!");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();
                }

            }
        });

        buttonCancTransbordo.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancTransbordo.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "if (editTextPadrao.getText().toString().length() > 0) {\n" +
                    "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                    "                }", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {", getLocalClassName());
        if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 2L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 2L) {\n" +
                    "            Intent it = new Intent(TransbordoActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
            Intent it = new Intent(TransbordoActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            Intent it = new Intent(TransbordoActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(TransbordoActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
        }
        finish();
    }

}
