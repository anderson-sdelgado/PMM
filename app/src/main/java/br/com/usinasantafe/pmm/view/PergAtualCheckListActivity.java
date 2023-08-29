package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class PergAtualCheckListActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perg_atual_check_list);

        pmmContext = (PMMContext) getApplication();

        Button buttonSimAtualCL = findViewById(R.id.buttonSimAtualCL);
        Button buttonNaoAtualCL = findViewById(R.id.buttonNaoAtualCL);

        buttonNaoAtualCL.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonNaoAtualCL.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                pmmContext.getCheckListCTR().setPosCheckList(1);\n" +
                    "                Intent it = new Intent(PergAtualCheckListActivity.this, ItemCheckListActivity.class);", getLocalClassName());
            pmmContext.getCheckListCTR().setPosCheckList(1);
            Intent it = new Intent(PergAtualCheckListActivity.this, ItemCheckListActivity.class);
            startActivity(it);
            finish();

        });

        buttonSimAtualCL.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonSimAtualCL.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (connectNetwork) {

                LogProcessoDAO.getInstance().insertLogProcesso("                if (connectNetwork) {\n" +
                        "                    progressBar = new ProgressDialog(PergAtualCheckListActivity.this);\n" +
                        "                    progressBar.setCancelable(true);\n" +
                        "                    progressBar.setMessage(\"ATUALIZANDO CHECKLIST...\");\n" +
                        "                    progressBar.show();\n" +
                        "                    customHandler.postDelayed(updateTimerThread, 10000);\n" +
                        "                    pmmContext.getCheckListCTR().atualCheckList(String.valueOf(pmmContext.getConfigCTR().getEquip().getNroEquip()), PergAtualCheckListActivity.this, ItemCheckListActivity.class, progressBar);", getLocalClassName());

                progressBar = new ProgressDialog(PergAtualCheckListActivity.this);
                progressBar.setCancelable(true);
                progressBar.setMessage("ATUALIZANDO CHECKLIST...");
                progressBar.show();

                customHandler.postDelayed(updateTimerThread, 10000);

                pmmContext.getCheckListCTR().atualCheckList( PergAtualCheckListActivity.this, ItemCheckListActivity.class, progressBar, getLocalClassName());

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder( PergAtualCheckListActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());

                AlertDialog.Builder alerta = new AlertDialog.Builder( PergAtualCheckListActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }


        });

    }

    public void onBackPressed()  {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            if(VerifDadosServ.status < 3) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status < 3) {\n" +
                        "                VerifDadosServ.getInstance().cancel();", getLocalClassName());
                VerifDadosServ.getInstance().cancel();

                if (progressBar.isShowing()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (progressBar.isShowing()) {\n" +
                            "                    progressBar.dismiss();", getLocalClassName());
                    progressBar.dismiss();
                }

                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(PergAtualCheckListActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"FALHA NA ATUALIZAÇÃO DE CHECKLIST! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.\");\n" +
                        "                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {\n" +
                        "                    }\n" +
                        "                });\n" +
                        "                alerta.show();", getLocalClassName());

                AlertDialog.Builder alerta = new AlertDialog.Builder(PergAtualCheckListActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA ATUALIZAÇÃO DE CHECKLIST! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }

        }
    };

}
