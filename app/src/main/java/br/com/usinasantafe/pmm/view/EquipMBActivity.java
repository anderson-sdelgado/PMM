package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pmm.BuildConfig;
import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class EquipMBActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_bomba);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkMotoBomba = findViewById(R.id.buttonOkPadrao);
        Button buttonCancMotoBomba = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(EquipMBActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());

                AlertDialog.Builder alerta = new AlertDialog.Builder(EquipMBActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {\n" +
                                "                        if (connectNetwork) {", getLocalClassName());

                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("progressBar = new ProgressDialog(EquipMBActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();", getLocalClassName());

                            progressBar = new ProgressDialog(EquipMBActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(EquipMBActivity.this, EquipMBActivity.class, progressBar, \"EquipSeg\", 1);", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().atualDados(EquipMBActivity.this, EquipMBActivity.class, progressBar, "EquipSeg", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(EquipMBActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(EquipMBActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();

                        }

                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });
                alerta.show();

            }

        });

        buttonOkMotoBomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkMotoBomba.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "                    Long motoBomba = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                    Long motoBomba = Long.parseLong(editTextPadrao.getText().toString());

                    if (pmmContext.getMotoMecFertCTR().verMotoBomba(motoBomba)) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verMotoBomba(motoBomba)) {\n" +
                                "                        pmmContext.getConfigCTR().setIdEquipBombaBolConfig(pmmContext.getMotoMecFertCTR().getEquipSeg(motoBomba).getIdEquip());\n" +
                                "                        salvarBoletimAberto();", getLocalClassName());
                        pmmContext.getConfigCTR().setIdEquipBombaBolConfig(pmmContext.getMotoMecFertCTR().getEquipSeg(motoBomba).getIdEquip());
                        salvarBoletimAberto();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(EquipMBActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENCAO\");\n" +
                                "                        alerta.setMessage(\"NUMERAÇÃO DA MOTO BOMBA INCORRETA. FAVOR, VERIFICAR A NUMERAÇÃO OU ATUALIZAR A BASE DE DADOS NOVAMENTE!\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(EquipMBActivity.this);
                        alerta.setTitle("ATENCAO");
                        alerta.setMessage("NUMERAÇÃO DA MOTO BOMBA INCORRETA. FAVOR, VERIFICAR A NUMERAÇÃO OU ATUALIZAR A BASE DE DADOS NOVAMENTE!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();

                    }

                }


            }
        });

        buttonCancMotoBomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancMotoBomba.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "           if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                        "                }", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void salvarBoletimAberto() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBoletimAberto() {\n" +
                "        pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());", getLocalClassName());
        pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());
        if(pmmContext.getCheckListCTR().verAberturaCheckList(pmmContext.getConfigCTR().getConfig().getIdTurnoConfig())){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getCheckListCTR().verAberturaCheckList(pmmContext.getConfigCTR().getConfig().getIdTurnoConfig())){\n" +
                    "            pmmContext.getMotoMecFertCTR().inserirParadaCheckList(getLocalClassName());\n" +
                    "            pmmContext.getCheckListCTR().setPosCheckList(1);\n" +
                    "            pmmContext.getCheckListCTR().createCabecAberto(getLocalClassName());", getLocalClassName());
            pmmContext.getMotoMecFertCTR().inserirParadaCheckList(getLocalClassName());
            pmmContext.getCheckListCTR().setPosCheckList(1);
            pmmContext.getCheckListCTR().createCabecAberto(getLocalClassName());
            if (pmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {\n" +
                        "                Intent it = new Intent(EquipMBActivity.this, PergAtualCheckListActivity.class);", getLocalClassName());
                Intent it = new Intent(EquipMBActivity.this, PergAtualCheckListActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                Intent it = new Intent(EquipMBActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                Intent it = new Intent(EquipMBActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();
            }
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{", getLocalClassName());
            if(BuildConfig.FLAVOR.equals("pmm")){
                LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                        "                Intent it = new Intent(EquipMBActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(EquipMBActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if(BuildConfig.FLAVOR.equals("ecm")){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                        "                Intent it = new Intent(EquipMBActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(EquipMBActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else if(BuildConfig.FLAVOR.equals("pcomp")){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                        "                Intent it = new Intent(EquipMBActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(EquipMBActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        }
    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(EquipMBActivity.this, HorimetroActivity.class);", getLocalClassName());
        Intent it = new Intent(EquipMBActivity.this, HorimetroActivity.class);
        startActivity(it);
        finish();
    }

}
