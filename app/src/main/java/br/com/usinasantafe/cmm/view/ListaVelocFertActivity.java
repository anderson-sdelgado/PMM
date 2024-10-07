package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ListaVelocFertActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private ListView velocListView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veloc_fert);

        cmmContext = (CMMContext) getApplication();

        Button buttonRetVelocidade = findViewById(R.id.buttonRetVelocidade);
        Button buttonAtualVelocidade = findViewById(R.id.buttonAtualVelocidade);

        buttonAtualVelocidade.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualVelocidade.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaVelocFertActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());

            AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaVelocFertActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                            progressBar = new ProgressDialog(ListaVelocFertActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                            progressBar.setProgress(0);\n" +
                            "                            progressBar.setMax(100);\n" +
                            "                            progressBar.show();", getLocalClassName());

                    progressBar = new ProgressDialog(ListaVelocFertActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(ListaVelocFertActivity.this, ListaVelocFertActivity.class, progressBar, \"Pressao\", 1, getLocalClassName());", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().atualDados(ListaVelocFertActivity.this, ListaVelocFertActivity.class, progressBar, "Pressao", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( ListaVelocFertActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                            "                                }\n" +
                            "                            });\n" +
                            "                            alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( ListaVelocFertActivity.this);
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

        LogProcessoDAO.getInstance().insertLogProcesso("AdapterList adapterList = new AdapterList(this, pmmContext.getMotoMecFertCTR().velocArrayList());\n" +
                "        velocListView = findViewById(R.id.listVelocidade);\n" +
                "        velocListView.setAdapter(adapterList);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, cmmContext.getMotoMecFertCTR().velocArrayList());
        velocListView = findViewById(R.id.listVelocidade);
        velocListView.setAdapter(adapterList);

        velocListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("velocListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);\n" +
                    "                pmmContext.getConfigCTR().setVelocConfig(Long.parseLong(textView.getText().toString()));", getLocalClassName());

            TextView textView = v.findViewById(R.id.textViewItemList);
            cmmContext.getConfigCTR().setVelocConfig(Long.parseLong(textView.getText().toString()));

            if (cmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\");", getLocalClassName());

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                    LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                });
                alerta.show();

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                if (cmmContext.getMotoMecFertCTR().verifBackupApont(0L)) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verifBackupApont(0L)) {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                    alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                    alerta.show();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("List<RFuncaoAtivParBean> rFuncaoAtivParList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());\n" +
                            "                        boolean recolhimento = false;\n" +
                            "                        for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtivParList) {\n" +
                            "                            if (rFuncaoAtivParBean.getCodFuncao() == 4) {\n" +
                            "                                recolhimento = true;\n" +
                            "                            }\n" +
                            "                        }\n" +
                            "                        rFuncaoAtivParList.clear();", getLocalClassName());

                    List<RFuncaoAtivParBean> rFuncaoAtivParList = cmmContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());
                    boolean recolhimento = false;
                    for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtivParList) {
                        if (rFuncaoAtivParBean.getCodFuncao() == 4) {
                            recolhimento = true;
                            break;
                        }
                    }
                    rFuncaoAtivParList.clear();

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont( 0L, 0L, getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().salvarApont(cmmContext, 0L, 0L, getLongitude(), getLatitude(), getLocalClassName());

                    if (recolhimento) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (recolhimento) {\n" +
                                "                            pmmContext.getMotoMecFertCTR().insRecolh();", getLocalClassName());
                        cmmContext.getMotoMecFertCTR().insRecolh(getLocalClassName());
                    }

                    LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

        buttonRetVelocidade.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetVelocidade.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaVelocFertActivity.this, ListaPressaoFertActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaVelocFertActivity.this, ListaPressaoFertActivity.class);
            startActivity(it);
            finish();
        });


    }

    public void onBackPressed()  {
    }

}
