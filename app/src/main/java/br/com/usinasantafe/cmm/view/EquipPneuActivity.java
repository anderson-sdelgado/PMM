package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class EquipPneuActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_pneu);

        Button buttonOkEquip = findViewById(R.id.buttonOkPadrao);
        Button buttonCancEquip = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualEquip = findViewById(R.id.buttonAtualPadrao);

        cmmContext = (CMMContext) getApplication();

        buttonAtualEquip.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualEquip.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(EquipPneuActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(EquipPneuActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                            progressBar = new ProgressDialog(EquipActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"Atualizando Colaborador...\");\n" +
                            "                            progressBar.show();\n" +
                            "                            VerifDadosServ.getInstance().verDados(\"\", \"Equip\"\n" +
                            "                                    , EquipActivity.this, EquipActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(EquipPneuActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Equipamento...");
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("cmmContext.getMotoMecFertCTR().atualDados(EquipPneuActivity.this, EquipPneuActivity.class, progressBar, \"EquipPneu\", 1, getLocalClassName());", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().atualDados(EquipPneuActivity.this, EquipPneuActivity.class, progressBar, "EquipPneu", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                            AlertDialog.Builder alerta = new AlertDialog.Builder( EquipPneuActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( EquipPneuActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                    alerta1.show();

                }

            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

        buttonOkEquip.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkEquip.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @SuppressWarnings(\"rawtypes\")\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {", getLocalClassName());
                if (cmmContext.getPneuCTR().verEquipPneuNro(Long.parseLong(editTextPadrao.getText().toString()))) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (cmmContext.getPneuCTR().verEquipPneuNro(Long.parseLong(editTextPadrao.getText().toString()))) {\n" +
                            "                    cmmContext.getPneuCTR().abrirBoletimPneu(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                            "                    Intent it = new Intent(EquipPneuActivity.this, ListaPosPneuActivity.class);", getLocalClassName());
                    cmmContext.getPneuCTR().abrirBolPneu(Long.parseLong(editTextPadrao.getText().toString()));
                    Intent it = new Intent(EquipPneuActivity.this, ListaPosPneuActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(EquipPneuActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"NUMERAÇÃO DO FUNCIONÁRIO INEXISTENTE! FAVOR VERIFICA A MESMA.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(EquipPneuActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NUMERAÇÃO DO EQUIPAMENTO INEXISTENTE! FAVOR VERIFICA A MESMA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                    alerta.show();

                }
            }

        });

        buttonCancEquip.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancEquip.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(EquipPneuActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
        Intent it = new Intent(EquipPneuActivity.this, MenuPrincPMMActivity.class);
        startActivity(it);
        finish();
    }

}