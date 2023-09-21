package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.EnvioDadosServ;

public class MenuPrincPCOMPActivity extends ActivityGeneric {

    private ListView motoMecListView;
    private CMMContext cmmContext;
    private TextView textViewMotorista;
    private int posicao;
    private List<MotoMecBean> motoMecList;
    private TextView textViewProcessoNormal;
    private Handler customHandler = new Handler();
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_pcomp);

        cmmContext = (CMMContext) getApplication();

        Button buttonParadaMotoMec = findViewById(R.id.buttonParadaMotoMec);
        Button buttonRetMotoMec = findViewById(R.id.buttonRetMotoMec);
        Button buttonLogMotoMec = findViewById(R.id.buttonLogMotoMec);
        textViewProcessoNormal = findViewById(R.id.textViewProcessoNormal);

        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.postDelayed(updateTimerThread, 0);", getLocalClassName());
        customHandler.postDelayed(updateTimerThread, 0);

        textViewMotorista = findViewById(R.id.textViewMotorista);

        LogProcessoDAO.getInstance().insertLogProcesso("textViewMotorista.setText(pmmContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc() + \" - \" + pmmContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());\n" +
                "        ArrayList<String> motoMecArrayList = new ArrayList<String>();\n" +
                "        motoMecList = pmmContext.getMotoMecFertCTR().motoMecList();\n" +
                "        for (MotoMecBean motoMecBean : motoMecList) {\n" +
                "            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, motoMecArrayList);\n" +
                "        motoMecListView = findViewById(R.id.motoMecListView);\n" +
                "        motoMecListView.setAdapter(adapterList);", getLocalClassName());

        textViewMotorista.setText(cmmContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc() + " - " + cmmContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());

        ArrayList<String> motoMecArrayList = new ArrayList<>();
        motoMecList = cmmContext.getMotoMecFertCTR().motoMecList();
        for (MotoMecBean motoMecBean : motoMecList) {
            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, motoMecArrayList);
        motoMecListView = findViewById(R.id.motoMecListView);
        motoMecListView.setAdapter(adapterList);

        motoMecListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("motoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                posicao = position;\n" +
                    "                MotoMecBean motoMecBean = motoMecList.get(position);\n" +
                    "                pmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);", getLocalClassName());
            posicao = position;
            MotoMecBean motoMecBean = motoMecList.get(position);
            cmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);
            if (connectNetwork) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(1L);", getLocalClassName());
                cmmContext.getConfigCTR().setStatusConConfig(1L);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(0L);", getLocalClassName());
                cmmContext.getConfigCTR().setStatusConConfig(0L);
            }

            if (cmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                        "                    Toast.makeText(MenuPrincPCOMPActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\",\n" +
                        "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                Toast.makeText(MenuPrincPCOMPActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.",
                        Toast.LENGTH_LONG).show();
            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                if (cmmContext.getMotoMecFertCTR().verifBackupApont()) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verifBackupApont()) {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                    alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                    alerta.show();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 11)) {  // ATIVIDADES NORMAIS

                        LogProcessoDAO.getInstance().insertLogProcesso("if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)\n" +
                                "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 11)) {  // ATIVIDADES NORMAIS\n" +
                                "                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                            alerta.setMessage(\"INÍCIO DE ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec());", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("INÍCIO DE ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());
                        alerta.setPositiveButton("OK", (dialog, which) -> {

                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                    "                                    motoMecListView.setSelection(posicao + 1);", getLocalClassName());
                            cmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                            motoMecListView.setSelection(posicao + 1);

                        });

                        alerta.show();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 0) {\n" +
                                "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                                alerta.setMessage(\"INÍCIO DE ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec());", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("INÍCIO DE ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());
                        alerta.setPositiveButton("OK", (dialog, which) -> {

                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                        pmmContext.getConfigCTR().setPosFluxoCarregComposto(1L);\n" +
                                    "                                        pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                    "                                        motoMecListView.setSelection(posicao + 1);", getLocalClassName());

                            cmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                            motoMecListView.setSelection(posicao + 1);

                        });

                        alerta.show();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) {

                        LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) {\n" +
                                "                            pmmContext.getConfigCTR().setPosicaoTela(2L);\n" +
                                "                            Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);", getLocalClassName());
                        cmmContext.getConfigCTR().setPosicaoTela(2L);
                        Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) {

                        LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) {", getLocalClassName());

                        if (cmmContext.getConfigCTR().getConfig().getFuncaoComposto() == 2L) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getFuncaoCompostagem() == 2L) {n" +
                                    "                                    Intent it = new Intent(MenuPrincPCOMPActivity.this, ProdutoActivity.class);", getLocalClassName());
                            Intent it = new Intent(MenuPrincPCOMPActivity.this, ProdutoActivity.class);
                            startActivity(it);
                            finish();

                        } else if (cmmContext.getConfigCTR().getConfig().getFuncaoComposto() == 3L) {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else if (pmmContext.getConfigCTR().getConfig().getFuncaoCompostagem() == 3L) {\n" +
                                    "                                    pmmContext.getConfigCTR().setPosFluxoCarregComposto(2L);\n" +
                                    "                                    Intent it = new Intent(MenuPrincPCOMPActivity.this, LeiraActivity.class);", getLocalClassName());

                            Intent it = new Intent(MenuPrincPCOMPActivity.this, LeiraActivity.class);
                            startActivity(it);
                            finish();

                        }

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) {

                        LogProcessoDAO.getInstance().insertLogProcesso("progressBar = new ProgressDialog(v.getContext());\n" +
                                "                            progressBar.setCancelable(true);\n" +
                                "                            progressBar.setMessage(\"BUSCANDO BOLETIM...\");\n" +
                                "                            progressBar.show();\n" +
                                "                            pmmContext.getCecCTR().delPreCECAberto();\n" +
                                "                            pmmContext.getCecCTR().verCEC(MenuPrincECMActivity.this, CECActivity.class, progressBar);", getLocalClassName());
                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("BUSCANDO ORD. CARREGAMENTO...");
                        progressBar.show();

                        cmmContext.getCompostoCTR().verifDadosCarreg(MenuPrincPCOMPActivity.this, InforCarregCompActivity.class, progressBar, getLocalClassName());

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 7) {

                        LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 7) {\n" +
                                "                            pmmContext.getConfigCTR().setPosicaoTela(2L);\n" +
                                "                            Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);", getLocalClassName());
                        cmmContext.getConfigCTR().setPosicaoTela(2L);
                        Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 10) {

                        LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 10) {", getLocalClassName());
                        if(cmmContext.getCompostoCTR().verOrdemCarregComLeira()){

                            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getCompostoCTR().verOrdemCarreg()){\n" +
                                    "                            pmmContext.getConfigCTR().setPosicaoTela(15L);\n" +
                                    "                            Intent it = new Intent(MenuPrincPCOMPActivity.this, LeiraActivity.class);", getLocalClassName());
                            cmmContext.getConfigCTR().setPosicaoTela(15L);
                            Intent it = new Intent(MenuPrincPCOMPActivity.this, LeiraActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else{

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"POR FAVOR! REALIZE O PROCESSO DE CARREGAMENTO DE INSUMO PARA DEPOIS REALIZAR O DESCARREGAMENTO.\");" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR! REALIZE O PROCESSO DE CARREGAMENTO DE INSUMO PARA DEPOIS REALIZAR O DESCARREGAMENTO.");
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });
                            alerta.show();

                        }

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 5) {

                        LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 5) {", getLocalClassName());
                        if (cmmContext.getCompostoCTR().pesqLeiraExibir()) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getCompostoCTR().pesqLeiraExibir()) {\n" +
                                    "                                pmmContext.getConfigCTR().setPosicaoTela(5L);\n" +
                                    "                                Intent it = new Intent(MenuPrincPCOMPActivity.this, InformacaoActivity.class);", getLocalClassName());
                            cmmContext.getConfigCTR().setPosicaoTela(5L);
                            Intent it = new Intent(MenuPrincPCOMPActivity.this, InforCarregCompActivity.class);
                            startActivity(it);
                            finish();

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"NÃO CONTÉM NENHUMA LEIRA PARA CARREGAMENTO OU DESCARREGAMENTO.\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("NÃO CONTÉM NENHUMA LEIRA PARA CARREGAMENTO OU DESCARREGAMENTO.");
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });

                            alerta.show();

                        }

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 12) {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else if (motoMecBean.getCodFuncaoOperMotoMec() == 12) {\n" +
                                "                            pmmContext.getConfigCTR().setPosicaoTela(29L);\n" +
                                "                            Intent it = new Intent(MenuPrincPCOMPActivity.this, ListaFuncaoCompActivity.class);", getLocalClassName());
                        cmmContext.getConfigCTR().setPosicaoTela(29L);
                        Intent it = new Intent(MenuPrincPCOMPActivity.this, ListaFuncaoCompActivity.class);
                        startActivity(it);
                        finish();

                    }

                }
            }
        });

        buttonParadaMotoMec.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(MenuPrincPCOMPActivity.this, MenuParadaPCOMPActivity.class);", getLocalClassName());
            Intent it = new Intent(MenuPrincPCOMPActivity.this, ListaParadaPCOMPActivity.class);
            startActivity(it);
            finish();
        });

        buttonRetMotoMec.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().setPosicaoTela(26L);\n" +
                    "                pmmContext.getConfigCTR().setHodometroFinalConfig(0D);\n" +
                    "                pmmContext.getMotoMecFertCTR().salvarBolMMFertFechado(getLocalClassName());\n" +
                    "                Intent it = new Intent(MenuPrincPCOMPActivity.this, TelaInicialActivity.class);", getLocalClassName());
            cmmContext.getConfigCTR().setPosicaoTela(26L);
            cmmContext.getConfigCTR().setHodometroFinalConfig(0D);
            cmmContext.getMotoMecFertCTR().salvarBolMMFertFechado(getLocalClassName());
            Intent it = new Intent(MenuPrincPCOMPActivity.this, TelaInicialActivity.class);
            startActivity(it);
            finish();
        });

        buttonLogMotoMec.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonLogMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                pmmContext.getConfigCTR().setPosicaoTela(25L);\n" +
                    "                Intent it = new Intent(MenuPrincPCOMPActivity.this, SenhaActivity.class);", getLocalClassName());
            cmmContext.getConfigCTR().setPosicaoTela(25L);
            Intent it = new Intent(MenuPrincPCOMPActivity.this, SenhaActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            if (EnvioDadosServ.status == 1) {
                textViewProcessoNormal.setTextColor(Color.YELLOW);
                textViewProcessoNormal.setText("Enviando e recebendo de dados...");
            } else if (EnvioDadosServ.status == 2) {
                textViewProcessoNormal.setTextColor(Color.RED);
                textViewProcessoNormal.setText("Existem dados para serem enviados e recebidos");
            } else {
                textViewProcessoNormal.setTextColor(Color.GREEN);
                textViewProcessoNormal.setText("Todos os Dados já foram enviados e recebidos");
            }
            LogProcessoDAO.getInstance().insertLogProcesso("if(EnvioDadosServ.status != 3){\n" +
                    "                customHandler.postDelayed(this, 10000);\n" +
                    "            }", getLocalClassName());
            if(EnvioDadosServ.status != 3){
                customHandler.postDelayed(this, 10000);
            }
        }
    };

}