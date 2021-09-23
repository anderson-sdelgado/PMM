package br.com.usinasantafe.pmm.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView listView;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pmmContext = (PMMContext) getApplication();
        textViewProcesso = findViewById(R.id.textViewProcesso);

        if (!checkPermission(Manifest.permission.CAMERA)) {
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        if(EnvioDadosServ.getInstance().verifDadosEnvio()){
            LogProcessoDAO.getInstance().insert("EnvioDadosServ.getInstance().verifDadosEnvio()", getLocalClassName());
            if(connectNetwork){
                LogProcessoDAO.getInstance().insert("if(connectNetwork){\n" +
                        "EnvioDadosServ.getInstance().envioDados()", getLocalClassName());
                EnvioDadosServ.getInstance().envioDados(getLocalClassName());
            }
            else{
                LogProcessoDAO.getInstance().insert("else{\n" +
                        "                EnvioDadosServ.status = 1;", getLocalClassName());
                EnvioDadosServ.status = 1;
            }
        }
        else{
            LogProcessoDAO.getInstance().insert("else{\n" +
                    "            EnvioDadosServ.status = 3;", getLocalClassName());
            EnvioDadosServ.status = 3;
        }

        LogProcessoDAO.getInstance().insert("VerifDadosServ.status = 3;\n" +
                "verifEnvio();", getLocalClassName());
        VerifDadosServ.status = 3;
        verifEnvio();

        progressBar = new ProgressDialog(this);

        if(pmmContext.getMotoMecFertCTR().verBolAberto()){
            LogProcessoDAO.getInstance().insert("pmmContext.getMotoMecFertCTR().verBolAberto()", getLocalClassName());
            if(pmmContext.getCheckListCTR().verCabecAberto()){
                LogProcessoDAO.getInstance().insert("pmmContext.getCheckListCTR().verCabecAberto()\n" +
                        "encerrarBarra()\n" +
                        "pmmContext.getCheckListCTR().clearRespCabecAberto();\n" +
                        "                pmmContext.getCheckListCTR().setPosCheckList(1);\n" +
                        "                Intent it = new Intent(MenuInicialActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                encerrarBarra();
                pmmContext.getCheckListCTR().clearRespCabecAberto();
                pmmContext.getCheckListCTR().setPosCheckList(1);
                Intent it = new Intent(MenuInicialActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();
            }
            else {
                LogProcessoDAO.getInstance().insert("else {\n" +
                        "encerrarBarra();\n" +
                        "                pmmContext.getConfigCTR().setPosicaoTela(8L);", getLocalClassName());
                encerrarBarra();
                pmmContext.getConfigCTR().setPosicaoTela(8L);
                if (connectNetwork) {
                    LogProcessoDAO.getInstance().insert("if (connectNetwork) {", getLocalClassName());
                    if (VerifDadosServ.getInstance().verifRecInformativo()) {
                        LogProcessoDAO.getInstance().insert("VerifDadosServ.getInstance().verifRecInformativo()\n" +
                                "pmmContext.getInformativoCTR().verifDadosInformativo", getLocalClassName());
                        pmmContext.getInformativoCTR().verifDadosInformativo(getLocalClassName());
                    } else {
                        LogProcessoDAO.getInstance().insert("} else {\n" +
                                "                        LogProcessoDAO.getInstance().insert(\"} else {\", getLocalClassName());\n" +
                                "                        VerifDadosServ.status = 1;", getLocalClassName());
                        LogProcessoDAO.getInstance().insert("} else {", getLocalClassName());
                        VerifDadosServ.status = 1;
                    }
                }

                if(PMMContext.aplic == 1){
                    LogProcessoDAO.getInstance().insert("if(PMMContext.aplic == 1){\n" +
                            "Intent it = new Intent(MenuInicialActivity.this, MenuPrincPMMActivity.class)", getLocalClassName());
                    Intent it = new Intent(MenuInicialActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 2){
                    LogProcessoDAO.getInstance().insert("else if(PMMContext.aplic == 2){", getLocalClassName());
                    if(pmmContext.getCecCTR().verPreCECAberto()){
                        LogProcessoDAO.getInstance().insert("pmmContext.getCecCTR().verPreCECAberto()\n" +
                                "clearPreCECAberto()", getLocalClassName());
                        pmmContext.getCecCTR().clearPreCECAberto();
                    }
                    LogProcessoDAO.getInstance().insert("Intent it = new Intent(MenuInicialActivity.this, MenuPrincECMActivity.class)", getLocalClassName());
                    Intent it = new Intent(MenuInicialActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 3){
                    LogProcessoDAO.getInstance().insert("else if(PMMContext.aplic == 3){\n" +
                            "Intent it = new Intent(MenuInicialActivity.this, MenuPrincPCOMPActivity.class)", getLocalClassName());
                    Intent it = new Intent(MenuInicialActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        }
        else{
            LogProcessoDAO.getInstance().insert("else{\n" +
                    "atualizarAplic()", getLocalClassName());
            atualizarAplic();
        }

        ArrayList<String> itens = new ArrayList<>();

        itens.add("BOLETIM");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");
        itens.add("REENVIO DE DADOS");
        itens.add("ATUALIZAR APLICATIVO");
        itens.add("LOG");

        LogProcessoDAO.getInstance().insert("AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        listView = findViewById(R.id.listaMenuInicial);\n" +
                "        listView.setAdapter(adapterList);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, itens);
        listView = findViewById(R.id.listaMenuInicial);
        listView.setAdapter(adapterList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insert("listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                        "                String text = textView.getText().toString();", getLocalClassName());
                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("BOLETIM")) {
                    LogProcessoDAO.getInstance().insert("if (text.equals(\"BOLETIM\")) {", getLocalClassName());
                    if (pmmContext.getMotoMecFertCTR().hasElemFunc()
                            && pmmContext.getConfigCTR().hasElemConfig()
                            && (VerifDadosServ.status == 3)) {
                        LogProcessoDAO.getInstance().insert("pmmContext.getMotoMecFertCTR().hasElemFunc()\n" +
                                "                            && pmmContext.getConfigCTR().hasElemConfig()\n" +
                                "                            && (VerifDadosServ.status == 3)\n" +
                                "pmmContext.getConfigCTR().setPosicaoTela(1L);\n" +
                                "clearBD()", getLocalClassName());
                        pmmContext.getConfigCTR().setPosicaoTela(1L);
                        clearBD();
                        LogProcessoDAO.getInstance().insert("customHandler.removeCallbacks(updateTimerThread)", getLocalClassName());
                        customHandler.removeCallbacks(updateTimerThread);
                        LogProcessoDAO.getInstance().insert("Intent it = new Intent(MenuInicialActivity.this, OperadorActivity.class)", getLocalClassName());
                        Intent it = new Intent(MenuInicialActivity.this, OperadorActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("CONFIGURAÇÃO")) {
                    LogProcessoDAO.getInstance().insert("} else if (text.equals(\"CONFIGURAÇÃO\")) {\n" +
                            "pmmContext.getConfigCTR().setPosicaoTela(11L);\n" +
                            "Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class)", getLocalClassName());
                    pmmContext.getConfigCTR().setPosicaoTela(11L);
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("SAIR")) {
                    LogProcessoDAO.getInstance().insert("} else if (text.equals(\"SAIR\")) {\n" +
                            "Intent intent = new Intent(Intent.ACTION_MAIN);\n" +
                            "                    intent.addCategory(Intent.CATEGORY_HOME);\n" +
                            "                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);\n" +
                            "                    startActivity(intent);", getLocalClassName());
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if (text.equals("ATUALIZAR DADOS")) {

                    LogProcessoDAO.getInstance().insert("} else if (text.equals(\"ATUALIZAR DADOS\")) {", getLocalClassName());
                    if (connectNetwork) {

                        LogProcessoDAO.getInstance().insert("if (connectNetwork) {\n" +
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

                        LogProcessoDAO.getInstance().insert("pmmContext.getConfigCTR().atualTodasTabelas(MenuInicialActivity.this, progressBar);", getLocalClassName());
                        pmmContext.getConfigCTR().atualTodasTabelas(MenuInicialActivity.this, progressBar, getLocalClassName());

                    } else {
                        LogProcessoDAO.getInstance().insert("} else {" +
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
                else if (text.equals("ATUALIZAR APLICATIVO")) {
                    LogProcessoDAO.getInstance().insert("else if (text.equals(\"ATUALIZAR APLICATIVO\")) {\n" +
                            "atualizarAplic()", getLocalClassName());
                    atualizarAplic();
                }
                else if (text.equals("LOG")) {
                    LogProcessoDAO.getInstance().insert("else if (text.equals(\"LOG\")) {\n" +
                            "pmmContext.getConfigCTR().setPosicaoTela(12L);\n" +
                            "Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);", getLocalClassName());
                    pmmContext.getConfigCTR().setPosicaoTela(12L);
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public void atualizarAplic(){
        LogProcessoDAO.getInstance().insert("public void atualizarAplic(){", getLocalClassName());
        if (connectNetwork) {
            LogProcessoDAO.getInstance().insert("if (connectNetwork) {", getLocalClassName());
            if (pmmContext.getConfigCTR().hasElemConfig()) {
                LogProcessoDAO.getInstance().insert("pmmContext.getConfigCTR().hasElemConfig()\n" +
                        "progressBar.setCancelable(true);\n" +
                        "                progressBar.setMessage(\"BUSCANDO ATUALIZAÇÃO...\");\n" +
                        "                progressBar.show();\n" +
                        "                customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                progressBar.setCancelable(true);
                progressBar.setMessage("BUSCANDO ATUALIZAÇÃO...");
                progressBar.show();
                customHandler.postDelayed(updateTimerThread, 10000);
                LogProcessoDAO.getInstance().insert("pmmContext.getConfigCTR().verAtualAplic(pmmContext.versaoAplic, this, progressBar);", getLocalClassName());
                pmmContext.getConfigCTR().verAtualAplic(pmmContext.versaoAplic, this, progressBar, getLocalClassName());
            }
            else{
                VerifDadosServ.status = 3;
                LogProcessoDAO.getInstance().insert("else{\n" +
                        "                VerifDadosServ.status = 3;\n" +
                        "encerrarBarra();", getLocalClassName());
                encerrarBarra();
            }
        } else {
            VerifDadosServ.status = 3;
            LogProcessoDAO.getInstance().insert("else{\n" +
                    "                VerifDadosServ.status = 3;", getLocalClassName());
        }
    }

    public void encerrarBarra() {
        LogProcessoDAO.getInstance().insert("public void encerrarBarra() {", getLocalClassName());
        if (progressBar.isShowing()) {
            LogProcessoDAO.getInstance().insert("progressBar.isShowing()", getLocalClassName());
            progressBar.dismiss();
        }
    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            LogProcessoDAO.getInstance().insert("    private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            LogProcessoDAO.getInstance().insert("verifEnvio();", getLocalClassName());
            verifEnvio();
            if(VerifDadosServ.status < 3) {
                LogProcessoDAO.getInstance().insert("if(VerifDadosServ.status < 3) {\n" +
                        "VerifDadosServ.getInstance().cancel();\n" +
                        "                encerrarBarra();", getLocalClassName());
                VerifDadosServ.getInstance().cancel();
                encerrarBarra();
            }
            LogProcessoDAO.getInstance().insert("customHandler.postDelayed(this, 10000);", getLocalClassName());
            customHandler.postDelayed(this, 10000);
        }
    };

    public void verifEnvio(){
        if (pmmContext.getConfigCTR().hasElemConfig()) {
            pmmContext.getConfigCTR().setStatusRetVerif(0L);
            LogProcessoDAO.getInstance().insert("        if (pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                    "            pmmContext.getConfigCTR().setStatusRetVerif(0L);", getLocalClassName());
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
    }

    public void clearBD() {
        if(PMMContext.aplic == 1){
            LogProcessoDAO.getInstance().insert("pmmContext.getMotoMecFertCTR().impleMMDelAll();\n" +
                    "            pmmContext.getConfigCTR().osDelAll();\n" +
                    "            pmmContext.getConfigCTR().rOSAtivDelAll();", getLocalClassName());
            pmmContext.getMotoMecFertCTR().impleMMDelAll();
            pmmContext.getConfigCTR().osDelAll();
            pmmContext.getConfigCTR().rOSAtivDelAll();
        }
    }

}
