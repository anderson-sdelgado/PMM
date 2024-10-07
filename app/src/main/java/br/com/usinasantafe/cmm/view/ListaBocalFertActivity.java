package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ListaBocalFertActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private ListView bocalListView;
    private List<BocalBean> bocalList;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bocal_fert);

        cmmContext = (CMMContext) getApplication();

        Button buttonRetBocal = findViewById(R.id.buttonRetBocal);
        Button buttonAtualBocal = findViewById(R.id.buttonAtualBocal);

        buttonAtualBocal.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualBocal.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaBocalFertActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaBocalFertActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("                alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
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

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(ListaBocalFertActivity.this, ListaBocalFertActivity.class, progressBar, \"Bocal\", 1, getLocalClassName());", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().atualDados(ListaBocalFertActivity.this, ListaBocalFertActivity.class, progressBar, "Bocal", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( ListaBocalFertActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                            "                                }\n" +
                            "                            });\n" +
                            "                            alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( ListaBocalFertActivity.this);
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

        LogProcessoDAO.getInstance().insertLogProcesso("bocalList = pmmContext.getMotoMecFertCTR().bocalList();\n" +
                "        ArrayList<String> itens = new ArrayList<>();\n" +
                "        for(BocalBean bocalBean : bocalList){\n" +
                "            itens.add(bocalBean.getCodBocal() + \" - \" + bocalBean.getDescrBocal());\n" +
                "        }", getLocalClassName());

        bocalList = cmmContext.getMotoMecFertCTR().bocalList();

        ArrayList<String> itens = new ArrayList<>();

        for(BocalBean bocalBean : bocalList){
            itens.add(bocalBean.getCodBocal() + " - " + bocalBean.getDescrBocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        bocalListView = findViewById(R.id.listBocal);
        bocalListView.setAdapter(adapterList);

        bocalListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("BocalBean bocalBean = (BocalBean) bocalList.get(position);\n" +
                    "                pmmContext.getConfigCTR().setBocalConfig(bocalBean.getIdBocal());\n" +
                    "                bocalList.clear();\n" +
                    "                Intent it = new Intent(ListaBocalFertActivity.this, ListaPressaoFertActivity.class);\n" +
                    "                startActivity(it);\n" +
                    "                finish();", getLocalClassName());

            BocalBean bocalBean = bocalList.get(position);
            cmmContext.getConfigCTR().setBocalConfig(bocalBean.getIdBocal());
            bocalList.clear();

            Intent it = new Intent(ListaBocalFertActivity.this, ListaPressaoFertActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetBocal.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetBocal.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(ListaBocalFertActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaBocalFertActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
