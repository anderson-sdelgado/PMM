package br.com.usinasantafe.pmm.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.BuildConfig;
import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialListView;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private TextView textViewPrincipal;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pmmContext = (PMMContext) getApplication();
        textViewProcesso = findViewById(R.id.textViewProcesso);
        textViewPrincipal = findViewById(R.id.textViewPrincipal);

        textViewPrincipal.setText("PRINCIPAL - V " + BuildConfig.VERSION_NAME);

        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.postDelayed(updateTimerThread, 0);", getLocalClassName());
        customHandler.postDelayed(updateTimerThread, 0);

        LogProcessoDAO.getInstance().insertLogProcesso("        verifEnvio();\n" +
                "        progressBar = new ProgressDialog(this);\n" +
                "        ArrayList<String> itens = new ArrayList<>();\n" +
                "        itens.add(\"BOLETIM\");\n" +
                "        itens.add(\"CONFIGURAÇÃO\");\n" +
                "        itens.add(\"SAIR\");\n" +
                "        itens.add(\"REENVIO DE DADOS\");\n" +
                "        itens.add(\"ATUALIZAR APLICATIVO\");\n" +
                "        itens.add(\"LOG\");", getLocalClassName());

        progressBar = new ProgressDialog(this);

        ArrayList<String> itens = new ArrayList<>();

        itens.add("BOLETIM");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");
        itens.add("REENVIO DE DADOS");
        itens.add("ATUALIZAR DADOS");
        itens.add("LOG");

        LogProcessoDAO.getInstance().insertLogProcesso("AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        menuInicialListView = findViewById(R.id.listaMenuInicial);\n" +
                "        menuInicialListView.setAdapter(adapterList);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialListView = findViewById(R.id.listaMenuInicial);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                        "                String text = textView.getText().toString();", getLocalClassName());
                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("BOLETIM")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (text.equals(\"BOLETIM\")) {", getLocalClassName());
                    if (pmmContext.getMotoMecFertCTR().hasElemFunc()
                            && pmmContext.getConfigCTR().hasElemConfig()
                            && (VerifDadosServ.status == 3)) {
                        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().hasElemFunc()\n" +
                                "                            && pmmContext.getConfigCTR().hasElemConfig()\n" +
                                "                            && (VerifDadosServ.status == 3)\n" +
                                "pmmContext.getConfigCTR().setPosicaoTela(1L);", getLocalClassName());
                        pmmContext.getConfigCTR().setPosicaoTela(1L);
                        LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MenuInicialActivity.this, OperadorActivity.class)", getLocalClassName());
                        Intent it = new Intent(MenuInicialActivity.this, OperadorActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("CONFIGURAÇÃO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"CONFIGURAÇÃO\")) {\n" +
                            "                    if(pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                            "                        pmmContext.getConfigCTR().setPosicaoTela(11L);\n" +
                            "                    }\n" +
                            "                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);", getLocalClassName());
                    if(pmmContext.getConfigCTR().hasElemConfig()) {
                        pmmContext.getConfigCTR().setPosicaoTela(11L);
                    }
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("SAIR")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"SAIR\")) {\n" +
                            "Intent intent = new Intent(Intent.ACTION_MAIN);\n" +
                            "                    intent.addCategory(Intent.CATEGORY_HOME);\n" +
                            "                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);\n" +
                            "                    startActivity(intent);", getLocalClassName());
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if (text.equals("ATUALIZAR DADOS")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"ATUALIZAR DADOS\")) {", getLocalClassName());
                    if(pmmContext.getConfigCTR().hasElemConfig()) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().hasElemConfig()) {", getLocalClassName());
                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "progressBar = new ProgressDialog(v.getContext());\n" +
                                    "                        progressBar.setCancelable(true);\n" +
                                    "                        progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                        progressBar.setProgress(0);\n" +
                                    "                        progressBar.setMax(100);\n" +
                                    "                        progressBar.show()", getLocalClassName());
                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().verEquipConfig(MenuInicialActivity.this, MenuInicialActivity.class, progressBar, getLocalClassName(), 2);", getLocalClassName());
                            pmmContext.getConfigCTR().verEquipAtualTodosDadosConfig(MenuInicialActivity.this, MenuInicialActivity.class, progressBar, getLocalClassName(), 2);

                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {" +
                                    "AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);\n" +
                                    "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                        alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                            @Override\n" +
                                    "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                            }\n" +
                                    "                        });\n" +
                                    "                        alerta.show()", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
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


                }
                else if (text.equals("LOG")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"LOG\")) {", getLocalClassName());
                    if(pmmContext.getConfigCTR().hasElemConfig()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                                "                        pmmContext.getConfigCTR().setPosicaoTela(12L);\n" +
                                "                        Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);", getLocalClassName());
                        pmmContext.getConfigCTR().setPosicaoTela(12L);
                        Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                        startActivity(it);
                        finish();
                    }
                }

            }

        });

    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (pmmContext.getConfigCTR().hasElemConfig()) {
                LogProcessoDAO.getInstance().insertLogProcesso("        if (pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                        "            pmmContext.getConfigCTR().setStatusRetVerif(0L);\n" +
                        "EnvioDadosServ.status = " + EnvioDadosServ.status, getLocalClassName());
                if (EnvioDadosServ.status == 1) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.status == 2) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.status == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            } else {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Aparelho sem Equipamento");
            }

            LogProcessoDAO.getInstance().insertLogProcesso("if(EnvioDadosServ.status != 3){\n" +
                    "                customHandler.postDelayed(this, 10000);\n" +
                    "            }", getLocalClassName());
            if(EnvioDadosServ.status != 3){
                customHandler.postDelayed(this, 10000);
            }
        }
    };


    public void logProcesso(){
        LogProcessoBean logProcessoBean = new LogProcessoBean();
        List<LogProcessoBean> logProcessoList = logProcessoBean.all();
        for(LogProcessoBean logProcessoBeanBD : logProcessoList){
            Log.i("PMM", dadosProcesso(logProcessoBeanBD));
        }
    }

    private String dadosProcesso(LogProcessoBean logProcessoBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(logProcessoBean, logProcessoBean.getClass()).toString();
    }

}
