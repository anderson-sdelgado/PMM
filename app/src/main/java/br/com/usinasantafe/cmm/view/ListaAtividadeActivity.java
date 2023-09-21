package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.VerifDadosServ;

public class ListaAtividadeActivity extends ActivityGeneric {

    private ListView atividadeListView;
    private CMMContext cmmContext;
    private ProgressDialog progressBar;
    private ArrayList ativArrayList;
    private Long nroOS = 0L;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade);

        cmmContext = (CMMContext) getApplication();

        Button buttonAtualAtividade = findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = findViewById(R.id.buttonRetAtividade);
        TextView textViewTituloAtividade = findViewById(R.id.textViewTituloAtividade);

        LogProcessoDAO.getInstance().insertLogProcesso("nroOS =  pmmContext.getConfigCTR().getConfig().getNroOSConfig();", getLocalClassName());
        nroOS =  cmmContext.getConfigCTR().getConfig().getNroOSConfig();

        buttonAtualAtividade.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (connectNetwork) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                        "                    progressBar = new ProgressDialog(v.getContext());\n" +
                        "                    progressBar.setCancelable(true);\n" +
                        "                    progressBar.setMessage(\"Atualizando Atividades...\");\n" +
                        "                    progressBar.show();\n" +
                        "                    customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Atualizando Atividades...");
                progressBar.show();

                customHandler.postDelayed(updateTimerThread, 10000);

                if(cmmContext.getConfigCTR().getConfig().getNroOSConfig() == 0){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getNroOSConfig() == 0){\n" +
                            "                        pmmContext.getMotoMecFertCTR().verAtivECM(ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().verAtivECM(ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        pmmContext.getMotoMecFertCTR().verAtiv(String.valueOf(" + nroOS + "), ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().verAtiv(nroOS, ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);

                }

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }

        });

        buttonRetAtividade.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetAtividade.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(BuildConfig.FLAVOR.equals("ecm")){
                LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 2){", getLocalClassName());
                if ((cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)
                        || (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L)) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if ((pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)\n" +
                            "                            || (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L)) {\n" +
                            "Intent it = new Intent(ListaAtividadeActivity.this, ListaTurnoActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaAtividadeActivity.this, ListaTurnoActivity.class);
                    startActivity(it);
                    finish();
                } else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L) {\n" +
                            "                        Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "Intent it = new Intent(ListaAtividadeActivity.this, MsgPropriedadeActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaAtividadeActivity.this, MsgPropriedadeActivity.class);
                    startActivity(it);
                    finish();
                }
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);
                startActivity(it);
                finish();
            }


        });

        LogProcessoDAO.getInstance().insertLogProcesso("if ((pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)\n" +
                "                || (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L)) {\n" +
                "            textViewTituloAtividade.setText(\"ATIVIDADE PRINCIPAL\");\n" +
                "        } else {\n" +
                "            textViewTituloAtividade.setText(\"ATIVIDADE\");\n" +
                "        }\n" +
                "ativArrayList = pmmContext.getMotoMecFertCTR().getAtivArrayList("+ nroOS +");", getLocalClassName());
        if ((cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)
                || (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L)) {
            textViewTituloAtividade.setText("ATIVIDADE PRINCIPAL");
        } else {
            textViewTituloAtividade.setText("ATIVIDADE");
        }
        ativArrayList = cmmContext.getMotoMecFertCTR().getAtivArrayList(nroOS, getLocalClassName());

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<String>();\n" +
                "        for (int i = 0; i < ativArrayList.size(); i++) {\n" +
                "            AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(i);\n" +
                "            itens.add(atividadeBean.getCodAtiv() + \" - \" + atividadeBean.getDescrAtiv());\n" +
                "        }", getLocalClassName());
        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < ativArrayList.size(); i++) {
            AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(i);
            itens.add(atividadeBean.getCodAtiv() + " - " + atividadeBean.getDescrAtiv());
        }

        LogProcessoDAO.getInstance().insertLogProcesso("AdapterList adapterList = new AdapterList(this, itens);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, itens);
        atividadeListView = findViewById(R.id.listAtividade);
        atividadeListView.setAdapter(adapterList);

        atividadeListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("atividadeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {", getLocalClassName());

            if(ativArrayList.size() == 0){

                LogProcessoDAO.getInstance().insertLogProcesso("if(ativArrayList.size() == 0){\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA SELEÇÃO DE ATIVIDADE. POR FAVOR, SELECIONE NOVAMENTE.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                            Intent it = new Intent(ListaAtividadeActivity.this, ListaAtividadeActivity.class);\n" +
                        "                            startActivity(it);\n" +
                        "                            finish();\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA SELEÇÃO DE ATIVIDADE. POR FAVOR, SELECIONE NOVAMENTE.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                    Intent it = new Intent(ListaAtividadeActivity.this, ListaAtividadeActivity.class);
                    startActivity(it);
                    finish();
                });
                alerta.show();

            }
            else {

                LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                        "AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(position);\n" +
                        "                    ativArrayList.clear();", getLocalClassName());
                AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(position);
                ativArrayList.clear();

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().setAtivConfig(" + atividadeBean.getIdAtiv() + ");", getLocalClassName());
                cmmContext.getConfigCTR().setAtivConfig(atividadeBean.getIdAtiv());

                if ((cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)
                        || (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L)) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if ((pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)\n" +
                            "                            || (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L)) {", getLocalClassName());
                    if(!BuildConfig.FLAVOR.equals("pcomp")){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(!BuildConfig.FLAVOR.equals(\"pcomp\")){\n" +
                                "                            Intent it = new Intent(ListaAtividadeActivity.this, HorimetroActivity.class);", getLocalClassName());
                        Intent it = new Intent(ListaAtividadeActivity.this, HorimetroActivity.class);
                        startActivity(it);
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                            pmmContext.getConfigCTR().setHodometroInicialConfig(0D,  getLongitude(), getLatitude());\n" +
                                "                            pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());\n" +
                                "                            Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);\n" +
                                "                            startActivity(it);", getLocalClassName());
                        cmmContext.getConfigCTR().setHodometroInicialConfig(0D,  getLongitude(), getLatitude());
                        cmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());
                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);
                        startActivity(it);
                    }
                    finish();

                } else if ((cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 2L)) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if ((pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 2L)) {", getLocalClassName());
                    if (cmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                                "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                                "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                            alerta.setMessage(\"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\");\n" +
                                "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                @Override\n" +
                                        "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                                if(BuildConfig.FLAVOR.equals("pmm")){
                                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                                            "                                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                                    Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPMMActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(BuildConfig.FLAVOR.equals("ecm")){
                                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 2){\n" +
                                            "                                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                                    Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincECMActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(BuildConfig.FLAVOR.equals("pcomp")){
                                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 3){\n" +
                                            "                                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                                    Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                            }
                        });
                        alerta.show();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());

                        if (cmmContext.getMotoMecFertCTR().verifBackupApont(0L)) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verifBackupApont(0L)) {\n" +
                                    "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                                    "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                                alerta.setMessage(\"OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!\");\n" +
                                    "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                    @Override\n" +
                                    "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                    }\n" +
                                    "                                });\n" +
                                    "                                alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });

                            alerta.show();

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());

                            if (cmmContext.getConfigCTR().getEquip().getTipoEquip() == 1) {

                                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1) {\n" +
                                        "List<RFuncaoAtivParBean> rFuncaoAtivParList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList();", getLocalClassName());
                                List<RFuncaoAtivParBean> rFuncaoAtivParList = cmmContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());

                                boolean transbordo = false;
                                boolean rendimento = false;

                                LogProcessoDAO.getInstance().insertLogProcesso("for (int i = 0; i < rFuncaoAtivParList.size(); i++) {\n" +
                                        "                                        RFuncaoAtivParBean rFuncaoAtivParBean = rFuncaoAtivParList.get(i);\n" +
                                        "                                        if (rFuncaoAtivParBean.getCodFuncao() == 2) {\n" +
                                        "                                            transbordo = true;\n" +
                                        "                                        }\n" +
                                        "                                        if (rFuncaoAtivParBean.getCodFuncao() == 1) {\n" +
                                        "                                            rendimento = true;\n" +
                                        "                                        }\n" +
                                        "                                    }\n" +
                                        "                                    rFuncaoAtivParList.clear();", getLocalClassName());
                                for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                                    RFuncaoAtivParBean rFuncaoAtivParBean = rFuncaoAtivParList.get(i);
                                    if (rFuncaoAtivParBean.getCodFuncao() == 2) {
                                        transbordo = true;
                                    }
                                    if (rFuncaoAtivParBean.getCodFuncao() == 1) {
                                        rendimento = true;
                                    }
                                }
                                rFuncaoAtivParList.clear();

                                if (transbordo) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("if (transbordo) {\n" +
                                            "Intent it = new Intent(ListaAtividadeActivity.this, TransbordoActivity.class);", getLocalClassName());
                                    Intent it = new Intent(ListaAtividadeActivity.this, TransbordoActivity.class);
                                    startActivity(it);
                                    finish();
                                } else {
                                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                            "pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude());", getLocalClassName());
                                    cmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());

                                    if (rendimento) {
                                        LogProcessoDAO.getInstance().insertLogProcesso("if (rendimento) {\n" +
                                                "pmmContext.getMotoMecFertCTR().insRendBD(" + nroOS + ");", getLocalClassName());
                                        cmmContext.getMotoMecFertCTR().insRendBD(nroOS, getLocalClassName());
                                    }

                                    if(BuildConfig.FLAVOR.equals("pmm")){
                                        LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                                                "Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPMMActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else if(BuildConfig.FLAVOR.equals("ecm")){
                                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                                                "Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincECMActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else if(BuildConfig.FLAVOR.equals("pcomp")){
                                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                                                "Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);
                                        startActivity(it);
                                        finish();
                                    }

                                }

                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "Intent it = new Intent(ListaAtividadeActivity.this, ListaBocalFertActivity.class);", getLocalClassName());
                                Intent it = new Intent(ListaAtividadeActivity.this, ListaBocalFertActivity.class);
                                startActivity(it);
                                finish();
                            }

                        }

                    }

                } else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 3L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 3L) {\n" +
                            "Intent it = new Intent(ListaAtividadeActivity.this, ListaParadaActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaAtividadeActivity.this, ListaParadaPMMActivity.class);
                    startActivity(it);
                    finish();
                } else if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L) {\n" +
                            "                        Intent it = new Intent(ListaAtividadeActivity.this, EquipActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaAtividadeActivity.this, LiberacaoActivity.class);
                    startActivity(it);
                    finish();
                } else if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 29L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 29L) {\n" +
                            "                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            if(VerifDadosServ.status < 3) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status < 3) {\n" +
                        "VerifDadosServ.getInstance().cancel();", getLocalClassName());
                VerifDadosServ.getInstance().cancel();

                if (progressBar.isShowing()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (progressBar.isShowing()) {\n" +
                            "                    progressBar.dismiss();", getLocalClassName());
                    progressBar.dismiss();
                }

                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.\");\n" +
                        "                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {\n" +
                        "                    }\n" +
                        "                });\n" +
                        "                alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }

        }
    };

}
