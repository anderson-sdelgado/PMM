package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class CarretelActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carretel);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkCarretel = findViewById(R.id.buttonOkPadrao);
        Button buttonCancCarretel = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(v -> {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(CarretelActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(CarretelActivity.this);
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
                            "                                    , CarretelActivity.this, CarretelActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(CarretelActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Equipamento...");
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("cmmContext.getMotoMecFertCTR().verCarretel(CarretelActivity.this, CarretelActivity.class, progressBar, getLocalClassName());", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().verCarretel(CarretelActivity.this, CarretelActivity.class, progressBar, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                            AlertDialog.Builder alerta = new AlertDialog.Builder(CarretelActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( CarretelActivity.this);
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

        buttonOkCarretel.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkCarretel.setOnClickListener(v -> {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                Long nroEquip = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                Long nroEquip = Long.parseLong(editTextPadrao.getText().toString());

                if(cmmContext.getConfigCTR().verifEquip(nroEquip) && cmmContext.getMotoMecFertCTR().verCarretel(nroEquip)){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(cmmContext.getConfigCTR().verifEquip(nroEquip) && cmmContext.getMotoMecFertCTR().verCarretel(nroEquip)){\n" +
                            "                    cmmContext.getConfigCTR().setEquipConfig(nroEquip);", getLocalClassName());
                    cmmContext.getConfigCTR().setIdEquipApontConfigNro(nroEquip);
                    Intent it = new Intent(CarretelActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(CarretelActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"ORDEM SERVIÇO INCORRETA! POR FAVOR, VERIFIQUE A NUMERAÇÃO DIGITADA DA ORDEM SERVIÇO.\");\n" +
                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                            "                                }\n" +
                            "                            });\n" +
                            "                            alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(CarretelActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NUMERAÇÃO DE CARRETEL INEXISTENTE! POR FAVOR, VERIFIQUE A NUMERAÇÃO DIGITADA OU ATUALIZE A BASE DE DADOS.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {});
                    alerta.show();
                }

            }
        });

        buttonCancCarretel.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancCarretel.setOnClickListener(v -> {\n" +
                    "            if(editTextPadrao.getText().toString().length() > 0){\n" +
                    "                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                    "            }", getLocalClassName());
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });
    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(CarretelActivity.this, ListaCarretelActivity.class);", getLocalClassName());
        Intent it = new Intent(CarretelActivity.this, ListaCarretelActivity.class);
        startActivity(it);
        finish();
    }

}