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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class ListaParadaECMActivity extends ActivityGeneric {

    private ListView paradaListView;
    private PMMContext pmmContext;
    private List<ParadaBean> paradaList;
    private ProgressDialog progressBar;
    private String paradaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada_ecm);

        pmmContext = (PMMContext) getApplication();

        Button buttonAtualParada = findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = findViewById(R.id.buttonRetMenuParada);

        LogProcessoDAO.getInstance().insertLogProcesso("paradaList = pmmContext.getMotoMecFertCTR().getListParada();\n" +
                "        ArrayList<String> itens = new ArrayList<String>();\n" +
                "        for (int i = 0; i < paradaList.size(); i++) {\n" +
                "            ParadaBean paradaBean = paradaList.get(i);\n" +
                "            itens.add(paradaBean.getCodParada() + \" - \" + paradaBean.getDescrParada());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        paradaListView = findViewById(R.id.listViewMotParada);\n" +
                "        paradaListView.setAdapter(adapterList);", getLocalClassName());
        paradaList = pmmContext.getMotoMecFertCTR().getListParada();
        ArrayList<String> itens = new ArrayList<>();
        for (int i = 0; i < paradaList.size(); i++) {
            ParadaBean paradaBean = paradaList.get(i);
            itens.add(paradaBean.getCodParada() + " - " + paradaBean.getDescrParada());
        }
        AdapterList adapterList = new AdapterList(this, itens);
        paradaListView = findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(adapterList);
        paradaListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);\n" +
                    "                paradaString = textView.getText().toString();\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                String label = \"DESEJA REALMENTE REALIZAR A PARADA '\" + paradaString + \"' ?\";\n" +
                    "                alerta.setMessage(label);", getLocalClassName());

            TextView textView = v.findViewById(R.id.textViewItemList);
            paradaString = textView.getText().toString();

            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaECMActivity.this);
            alerta.setTitle("ATENÇÃO");
            String label = "DESEJA REALMENTE REALIZAR A PARADA '" + paradaString + "' ?";
            alerta.setMessage(label);
            alerta.setPositiveButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                            "                            Toast.makeText(ListaParadaECMActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\",\n" +
                            "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                    Toast.makeText(ListaParadaECMActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.",
                    Toast.LENGTH_LONG).show();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if (pmmContext.getMotoMecFertCTR().verifBackupApont(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada())) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verifBackupApont(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada())) {\n" +
                                "                                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);\n" +
                                "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                                alerta.setMessage(\"PARADA JÁ APONTADA PARA O EQUIPAMENTO!\");", getLocalClassName());
                        AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaParadaECMActivity.this);
                        alerta1.setTitle("ATENÇÃO");
                        alerta1.setMessage("PARADA JÁ APONTADA PARA O EQUIPAMENTO!");
                        alerta1.setPositiveButton("OK", (dialog1, which1) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                                    @Override\n" +
                                "                                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
                        alerta1.show();
                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("boolean calibragem = false;\n" +
                                "                                boolean desengateEngate = false;\n" +
                                "                                List<RFuncaoAtivParBean> rFuncaoAtivParList = pmmContext.getMotoMecFertCTR().getFuncaoParadaList(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), getLocalClassName());\n" +
                                "                                for (int i = 0; i < rFuncaoAtivParList.size(); i++) {\n" +
                                "                                    RFuncaoAtivParBean rFuncaoAtivParBean = rFuncaoAtivParList.get(i);\n" +
                                "                                    if (rFuncaoAtivParBean.getCodFuncao() == 3) {\n" +
                                "                                        calibragem = true;\n" +
                                "                                    }\n" +
                                "                                    if (rFuncaoAtivParBean.getCodFuncao() == 5) {\n" +
                                "                                        desengateEngate = true;\n" +
                                "                                    }\n" +
                                "                                }\n" +
                                "                                rFuncaoAtivParList.clear();", getLocalClassName());
                        boolean calibragem = false;
                        boolean desengateEngate = false;
                        List<RFuncaoAtivParBean> rFuncaoAtivParList = pmmContext.getMotoMecFertCTR().getFuncaoParadaList(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), getLocalClassName());
                        for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                            RFuncaoAtivParBean rFuncaoAtivParBean = rFuncaoAtivParList.get(i);
                            if (rFuncaoAtivParBean.getCodFuncao() == 3) {
                                calibragem = true;
                            }
                            if (rFuncaoAtivParBean.getCodFuncao() == 5) {
                                desengateEngate = true;
                            }
                        }
                        rFuncaoAtivParList.clear();

                        if(calibragem){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(calibragem){\n" +
                                    "                                    pmmContext.getMotoMecFertCTR().salvarParadaCalibragem(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());\n" +
                                    "                                    pmmContext.getMotoMecFertCTR().salvarBoletimPneu();\n" +
                                    "                                    Intent it = new Intent(ListaParadaECMActivity.this, ListaPosPneuActivity.class);", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().salvarParadaCalibragem(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());
                            pmmContext.getMotoMecFertCTR().salvarBoletimPneu();
                            Intent it = new Intent(ListaParadaECMActivity.this, ListaPosPneuActivity.class);
                            startActivity(it);
                            finish();
                        } else if(desengateEngate){
                            LogProcessoDAO.getInstance().insertLogProcesso("} else if(desengateEngate){\n" +
                                    "                                    pmmContext.getMotoMecFertCTR().salvarApont(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());\n" +
                                    "                                    Intent it = new Intent(ListaParadaECMActivity.this, OpcaoDesengateEngateActivity.class);", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().salvarApont(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());
                            Intent it = new Intent(ListaParadaECMActivity.this, OpcaoDesengateEngateActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                                    pmmContext.getMotoMecFertCTR().salvarApont(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().salvarApont(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());
                        }

                    }

                }

                paradaList.clear();

            });

            alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

            alerta.show();

        });

        buttonAtualParada.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualParada.setOnClickListener(new View.OnClickListener() {\n" +
                    "\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (connectNetwork) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                        "                    progressBar = new ProgressDialog(ListaParadaECMActivity.this);\n" +
                        "                    progressBar.setCancelable(true);\n" +
                        "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                        "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                        "                    progressBar.setProgress(0);\n" +
                        "                    progressBar.setMax(100);\n" +
                        "                    progressBar.show();", getLocalClassName());

                progressBar = new ProgressDialog(ListaParadaECMActivity.this);
                progressBar.setCancelable(true);
                progressBar.setMessage("ATUALIZANDO ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(ListaParadaECMActivity.this, ListaParadaECMActivity.class, progressBar, \"Parada\", 1, getLocalClassName());", getLocalClassName());
                pmmContext.getMotoMecFertCTR().atualDados(ListaParadaECMActivity.this, ListaParadaECMActivity.class, progressBar, "Parada", 2, getLocalClassName());

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaECMActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaECMActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }

        });

        buttonRetMenuParada.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                        "                    Toast.makeText(MenuParadaECMActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\",\n" +
                        "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                Toast.makeText(ListaParadaECMActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.",
                        Toast.LENGTH_LONG).show();
            }
            else {
                LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                        "if (connectNetwork) {\n" +
                        "                        pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                        "                    }\n" +
                        "                    else{\n" +
                        "                        pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                        "                    }", getLocalClassName());
                if (connectNetwork) {
                    pmmContext.getConfigCTR().setStatusConConfig(1L);
                }
                else{
                    pmmContext.getConfigCTR().setStatusConConfig(0L);
                }

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());

                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MenuParadaECMActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaParadaECMActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}