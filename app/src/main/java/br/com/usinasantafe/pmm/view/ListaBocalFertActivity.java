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

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class ListaBocalFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView bocalListView;
    private List<BocalBean> bocalList;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bocal_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetBocal = findViewById(R.id.buttonRetBocal);
        Button buttonAtualBocal = findViewById(R.id.buttonAtualBocal);

        buttonAtualBocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insert("buttonAtualBocal.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaBocalFertActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaBocalFertActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogProcessoDAO.getInstance().insert("                alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insert("if (connectNetwork) {\n" +
                                    "progressBar = new ProgressDialog(ListaBocalFertActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();", getLocalClassName());
                            progressBar = new ProgressDialog(ListaBocalFertActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insert("pmmContext.getMotoMecFertCTR().atualDados(ListaBocalFertActivity.this, ListaBocalFertActivity.class, progressBar, \"Bocal\", 1, getLocalClassName());", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().atualDados(ListaBocalFertActivity.this, ListaBocalFertActivity.class, progressBar, "Bocal", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insert("AlertDialog.Builder alerta = new AlertDialog.Builder( ListaBocalFertActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaBocalFertActivity.this);
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

        LogProcessoDAO.getInstance().insert("bocalList = pmmContext.getMotoMecFertCTR().bocalList();\n" +
                "        ArrayList<String> itens = new ArrayList<>();\n" +
                "        for(BocalBean bocalBean : bocalList){\n" +
                "            itens.add(bocalBean.getCodBocal() + \" - \" + bocalBean.getDescrBocal());\n" +
                "        }", getLocalClassName());

        bocalList = pmmContext.getMotoMecFertCTR().bocalList();

        ArrayList<String> itens = new ArrayList<>();

        for(BocalBean bocalBean : bocalList){
            itens.add(bocalBean.getCodBocal() + " - " + bocalBean.getDescrBocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        bocalListView = findViewById(R.id.listBocal);
        bocalListView.setAdapter(adapterList);

        bocalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insert("BocalBean bocalBean = (BocalBean) bocalList.get(position);\n" +
                        "                pmmContext.getConfigCTR().setBocalConfig(bocalBean.getIdBocal());\n" +
                        "                bocalList.clear();\n" +
                        "                Intent it = new Intent(ListaBocalFertActivity.this, ListaPressaoFertActivity.class);\n" +
                        "                startActivity(it);\n" +
                        "                finish();", getLocalClassName());

                BocalBean bocalBean = (BocalBean) bocalList.get(position);
                pmmContext.getConfigCTR().setBocalConfig(bocalBean.getIdBocal());
                bocalList.clear();

                Intent it = new Intent(ListaBocalFertActivity.this, ListaPressaoFertActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetBocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insert("buttonRetBocal.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaBocalFertActivity.this, ListaPressaoFertActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaBocalFertActivity.this, ListaPressaoFertActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
