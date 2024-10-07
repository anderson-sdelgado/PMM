package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.VerifDadosServ;

public class OSActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkOS = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = findViewById(R.id.buttonCancPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        if ((cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)
            || (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L)){
            LogProcessoDAO.getInstance().insertLogProcesso("if ((" + cmmContext.getConfigCTR().getConfig().getPosicaoTela() + " == 1L)\n" +
                    "            || (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L)){\n" +
                    "editText.setText(\"\");", getLocalClassName());
            editText.setText("");
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
            if(cmmContext.getConfigCTR().getConfig().getNroOSConfig() == 0L){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getNroOSConfig() == 0L){\n" +
                        "                editText.setText(\"\");", getLocalClassName());
                editText.setText("");
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                editText.setText(String.valueOf(pmmContext.getConfigCTR().getConfig().getNroOSConfig()));", getLocalClassName());
                editText.setText(String.valueOf(cmmContext.getConfigCTR().getConfig().getNroOSConfig()));
            }
        }

        buttonOkOS.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkOS.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso(" if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "Long nroOS = Long.parseLong(" + editTextPadrao.getText().toString() + ");\n" +
                        "                    pmmContext.getConfigCTR().setOsConfig(nroOS);", getLocalClassName());
                Long nroOS = Long.parseLong(editTextPadrao.getText().toString());
                cmmContext.getConfigCTR().setNroOSConfig(nroOS);

                LogProcessoDAO.getInstance().insertLogProcesso("else {", getLocalClassName());
                if (cmmContext.getConfigCTR().verOS(nroOS)) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().verROSAtiv(nroOS)) {\n" +
                            "if (connectNetwork) {\n" +
                            "                                pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                            "                            }\n" +
                            "                            else{\n" +
                            "                                pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                            "                            }", getLocalClassName());
                    if (connectNetwork) {
                        cmmContext.getConfigCTR().setStatusConConfig(1L);
                    }
                    else{
                        cmmContext.getConfigCTR().setStatusConConfig(0L);
                    }

                    LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                    Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    if(BuildConfig.FLAVOR.equals("ecm")) {

                        LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                                "AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);\n" +
                                "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                            alerta.setMessage(\"ORDEM SERVIÇO INCORRETA! POR FAVOR, VERIFIQUE A NUMERAÇÃO DIGITADA DA ORDEM SERVIÇO.\");\n" +
                                "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                                @Override\n" +
                                "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                "                                }\n" +
                                "                            });\n" +
                                "                            alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("ORDEM SERVIÇO INCORRETA! POR FAVOR, VERIFIQUE A NUMERAÇÃO DIGITADA DA ORDEM SERVIÇO.");
                        alerta.setPositiveButton("OK", (dialog, which) -> {});
                        alerta.show();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "progressBar = new ProgressDialog(v.getContext());\n" +
                                    "                                progressBar.setCancelable(true);\n" +
                                    "                                progressBar.setMessage(\"PESQUISANDO OS...\");\n" +
                                    "                                progressBar.show();\n" +
                                    "                                customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("PESQUISANDO OS...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().verOS(" + editTextPadrao.getText().toString() + "\n" +
                                    "                                        , OSActivity.this, ListaAtividadeActivity.class, progressBar);", getLocalClassName());
                            cmmContext.getMotoMecFertCTR().verOS(editTextPadrao.getText().toString()
                                    , OSActivity.this, ListaAtividadeActivity.class, progressBar, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                    "Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);", getLocalClassName());

                            cmmContext.getConfigCTR().setStatusConConfig(0L);
                            Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                }

            }
        });

        buttonCancOS.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancOS.setOnClickListener(new View.OnClickListener() {\n" +
                    "\n" +
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
        LogProcessoDAO.getInstance().insertLogProcesso("onBackPressed()", getLocalClassName());
        if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {", getLocalClassName());
            if(BuildConfig.FLAVOR.equals("pcomp")){
                LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 3){\n" +
                        "                Intent it = new Intent(OSActivity.this, ListaFuncaoPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(OSActivity.this, ListaFuncaoCompActivity.class);
                startActivity(it);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "Intent it = new Intent(OSActivity.this, ListaTurnoActivity.class);", getLocalClassName());
                Intent it = new Intent(OSActivity.this, ListaTurnoActivity.class);
                startActivity(it);
            }
            finish();
        } else if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L) {
            LogProcessoDAO.getInstance().insertLogProcesso("} else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L) {", getLocalClassName());
            if((cmmContext.getConfigCTR().getEquip().getCodClasseEquip() == 1L) && (cmmContext.getMotoMecFertCTR().qtdeCarreta() == 0)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if((pmmContext.getConfigCTR().getEquip().getCodClasseEquip() == 1L) && (pmmContext.getMotoMecFertCTR().qtdeCarreta() == 0)) {\n" +
                        "                Intent it = new Intent(OSActivity.this, EquipActivity.class);", getLocalClassName());
                Intent it = new Intent(OSActivity.this, EquipActivity.class);
                startActivity(it);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                Intent it = new Intent(OSActivity.this, CarretaActivity.class);", getLocalClassName());
                Intent it = new Intent(OSActivity.this, CarretaActivity.class);
                startActivity(it);
            }
            finish();
        } else if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 29L) {
            LogProcessoDAO.getInstance().insertLogProcesso("} else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 29L) {\n" +
                    "Intent it = new Intent(OSActivity.this, ListaFuncaoPCOMPActivity.class);", getLocalClassName());
            Intent it = new Intent(OSActivity.this, ListaFuncaoCompActivity.class);
            startActivity(it);
            finish();
        } else if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 32L) {
            LogProcessoDAO.getInstance().insertLogProcesso("} else if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 33L) {\n" +
                    "            Intent it = new Intent(OSActivity.this, CarretelActivity.class);", getLocalClassName());
            Intent it = new Intent(OSActivity.this, CarretelActivity.class);
            startActivity(it);
            finish();
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
            if(BuildConfig.FLAVOR.equals("pmm")){
                LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                        "Intent it = new Intent(OSActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(OSActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "Intent it = new Intent(OSActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(OSActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
            }
            finish();
        }
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

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                        "                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                cmmContext.getConfigCTR().setStatusConConfig(0L);
                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

}
