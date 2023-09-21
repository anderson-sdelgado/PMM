package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.VerifDadosServ;

public class PneuActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pneu);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkPneu = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPneu = findViewById(R.id.buttonCancPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().verItemMedPneuBolAberto()){\n" +
                "            editText.setText(pmmContext.getMotoMecFertCTR().getItemMedPneuBolAberto().getNroPneuItemMedPneu());\n" +
                "        }\n" +
                "        else{\n" +
                "            editText.setText(\"\");\n" +
                "        }", getLocalClassName());
        if(cmmContext.getMotoMecFertCTR().verItemMedPneuBolAberto()){
            editText.setText(cmmContext.getMotoMecFertCTR().getItemMedPneuBolAberto().getNroPneuItemCalibPneu());
        } else {
            editText.setText("");
        }

        buttonOkPneu.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkPneu.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    String nroPneu = editTextPadrao.getText().toString();\n" +
                        "                    pmmContext.getMotoMecFertCTR().getItemMedPneuDAO().getItemMedPneuBean().setNroPneuItemMedPneu(nroPneu);", getLocalClassName());
                String nroPneu = editTextPadrao.getText().toString();
                cmmContext.getMotoMecFertCTR().getItemMedPneuDAO().getItemMedPneuBean().setNroPneuItemCalibPneu(nroPneu);

                if(!cmmContext.getMotoMecFertCTR().verItemMedPneuNroPneuRepetido(nroPneu)){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(!pmmContext.getMotoMecFertCTR().verItemMedPneuNroPneuRepetido(nroPneu)){", getLocalClassName());
                    if (cmmContext.getMotoMecFertCTR().verPneuNro(nroPneu)) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verPneuNro(nroPneu)) {\n" +
                                "                        Intent it = new Intent(PneuActivity.this, PressaoEncPneuActivity.class);", getLocalClassName());
                        Intent it = new Intent(PneuActivity.this, PressaoEncPneuActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "progressBar = new ProgressDialog(v.getContext());\n" +
                                    "                                progressBar.setCancelable(true);\n" +
                                    "                                progressBar.setMessage(\"PESQUISANDO PNEU...\");\n" +
                                    "                                progressBar.show();\n" +
                                    "                                customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("PESQUISANDO PNEU...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().verOS(" + editTextPadrao.getText().toString() + "\n" +
                                    "                                        , OSActivity.this, ListaAtividadeActivity.class, progressBar);", getLocalClassName());
                            cmmContext.getMotoMecFertCTR().verPneu(editTextPadrao.getText().toString()
                                    , PneuActivity.this, PressaoEncPneuActivity.class, progressBar, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                alerta.setMessage(\"FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.\");\n" +
                                    "                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                    @Override\n" +
                                    "                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                    }\n" +
                                    "                });\n" +
                                    "                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(PneuActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA DE PESQUISA DE PNEU! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });
                            alerta.show();

                        }
                    }
                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                            "                alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                alerta.setMessage(\"FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.\");\n" +
                            "                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                    @Override\n" +
                            "                    public void onClick(DialogInterface dialog, int which) {\n" +
                            "                    }\n" +
                            "                });\n" +
                            "                alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(PneuActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("PNEU REPETIDO! POR FAVOR, VERIFIQUE A NUMERAÇÃO DO PNEU.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();

                }

            }

        });

        buttonCancPneu.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancPneu.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    @Override
    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(PneuActivity.this, ListaPosPneuActivity.class);", getLocalClassName());
        Intent it = new Intent(PneuActivity.this, ListaPosPneuActivity.class);
        startActivity(it);
        finish();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            if(VerifDadosServ.status < 3) {

                LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().cancel();\n" +
                        "VerifDadosServ.getInstance().cancel();", getLocalClassName());
                VerifDadosServ.getInstance().cancel();

                if (progressBar.isShowing()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (progressBar.isShowing()) {\n" +
                            "progressBar.dismiss();", getLocalClassName());
                    progressBar.dismiss();
                }

            }

        }
    };

}