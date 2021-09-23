package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class ListaVelocFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView velocListView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veloc_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetVelocidade = findViewById(R.id.buttonRetVelocidade);
        Button buttonAtualVelocidade = findViewById(R.id.buttonAtualVelocidade);

        buttonAtualVelocidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insert("buttonAtualVelocidade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaVelocFertActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaVelocFertActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogProcessoDAO.getInstance().insert("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insert("if (connectNetwork) {\n" +
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

                            LogProcessoDAO.getInstance().insert("pmmContext.getMotoMecFertCTR().atualDados(ListaVelocFertActivity.this, ListaVelocFertActivity.class, progressBar, \"Pressao\", 1, getLocalClassName());", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().atualDados(ListaVelocFertActivity.this, ListaVelocFertActivity.class, progressBar, "Pressao", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insert("AlertDialog.Builder alerta = new AlertDialog.Builder( ListaVelocFertActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaVelocFertActivity.this);
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
                        LogProcessoDAO.getInstance().insert("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });

                alerta.show();

            }

        });

        LogProcessoDAO.getInstance().insert("AdapterList adapterList = new AdapterList(this, pmmContext.getMotoMecFertCTR().velocArrayList());\n" +
                "        velocListView = findViewById(R.id.listVelocidade);\n" +
                "        velocListView.setAdapter(adapterList);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, pmmContext.getMotoMecFertCTR().velocArrayList());
        velocListView = findViewById(R.id.listVelocidade);
        velocListView.setAdapter(adapterList);

        velocListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insert("velocListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);\n" +
                        "                pmmContext.getConfigCTR().setVelocConfig(Long.parseLong(textView.getText().toString()));", getLocalClassName());

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                pmmContext.getConfigCTR().setVelocConfig(Long.parseLong(textView.getText().toString()));

                if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {

                    LogProcessoDAO.getInstance().insert("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.\");", getLocalClassName());

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insert("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                            Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                            Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);
                            startActivity(it);
                            finish();
                        }
                    });
                    alerta.show();

                } else {

                    LogProcessoDAO.getInstance().insert("} else {", getLocalClassName());
                    if (pmmContext.getMotoMecFertCTR().verifBackupApont(0L)) {

                        LogProcessoDAO.getInstance().insert("if (pmmContext.getMotoMecFertCTR().verifBackupApont(0L)) {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!\");", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insert("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                            }
                        });

                        alerta.show();

                    } else {

                        LogProcessoDAO.getInstance().insert("List<RFuncaoAtivParBean> rFuncaoAtivParList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());\n" +
                                "                        boolean recolhimento = false;\n" +
                                "                        for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtivParList) {\n" +
                                "                            if (rFuncaoAtivParBean.getCodFuncao() == 4) {\n" +
                                "                                recolhimento = true;\n" +
                                "                            }\n" +
                                "                        }\n" +
                                "                        rFuncaoAtivParList.clear();", getLocalClassName());

                        List<RFuncaoAtivParBean> rFuncaoAtivParList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());
                        boolean recolhimento = false;
                        for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtivParList) {
                            if (rFuncaoAtivParBean.getCodFuncao() == 4) {
                                recolhimento = true;
                            }
                        }
                        rFuncaoAtivParList.clear();

                        LogProcessoDAO.getInstance().insert("pmmContext.getMotoMecFertCTR().salvarApont( 0L, 0L, getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                        pmmContext.getMotoMecFertCTR().salvarApont( 0L, 0L, getLongitude(), getLatitude(), getLocalClassName());

                        if (recolhimento) {
                            LogProcessoDAO.getInstance().insert("if (recolhimento) {\n" +
                                    "                            pmmContext.getMotoMecFertCTR().insRecolh();", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().insRecolh(getLocalClassName());
                        }

                        LogProcessoDAO.getInstance().insert("Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                        Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }

        });

        buttonRetVelocidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insert("buttonRetVelocidade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaVelocFertActivity.this, ListaPressaoFertActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaVelocFertActivity.this, ListaPressaoFertActivity.class);
                startActivity(it);
                finish();
            }
        });


    }

    public void onBackPressed()  {
    }

}
