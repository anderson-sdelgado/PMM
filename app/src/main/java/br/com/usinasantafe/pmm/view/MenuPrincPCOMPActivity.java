package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
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
import br.com.usinasantafe.pmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class MenuPrincPCOMPActivity extends ActivityGeneric {

    private ListView motoMecListView;
    private PMMContext pmmContext;
    private TextView textViewMotorista;
    private int posicao;
    private List<MotoMecBean> motoMecList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_pcomp);

        pmmContext = (PMMContext) getApplication();
        pmmContext.getCompostoCTR().setVerTelaLeira(false);

        Button buttonParadaMotoMec = findViewById(R.id.buttonParadaMotoMec);
        Button buttonRetMotoMec = findViewById(R.id.buttonRetMotoMec);
        Button buttonLogMotoMec = findViewById(R.id.buttonLogMotoMec);

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

        textViewMotorista.setText(pmmContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc() + " - " + pmmContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());

        ArrayList<String> motoMecArrayList = new ArrayList<String>();
        motoMecList = pmmContext.getMotoMecFertCTR().motoMecList();
        for (MotoMecBean motoMecBean : motoMecList) {
            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, motoMecArrayList);
        motoMecListView = findViewById(R.id.motoMecListView);
        motoMecListView.setAdapter(adapterList);

        motoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("motoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                posicao = position;\n" +
                        "                MotoMecBean motoMecBean = motoMecList.get(position);\n" +
                        "                pmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);\n" +
                        "                if (connectNetwork) {\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                        "                }\n" +
                        "                else{\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                        "                }", getLocalClassName());

                posicao = position;
                MotoMecBean motoMecBean = motoMecList.get(position);
                pmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);

                if (connectNetwork) {
                    pmmContext.getConfigCTR().setStatusConConfig(1L);
                }
                else{
                    pmmContext.getConfigCTR().setStatusConConfig(0L);
                }

                if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                            "                    Toast.makeText(MenuPrincPCOMPActivity.this, \"POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.\",\n" +
                            "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                    Toast.makeText(MenuPrincPCOMPActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if (pmmContext.getMotoMecFertCTR().verifBackupApont()) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verifBackupApont()) {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        alerta.show();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                        if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)
                                || (motoMecBean.getCodFuncaoOperMotoMec() == 11)) {  // ATIVIDADES NORMAIS

                            LogProcessoDAO.getInstance().insertLogProcesso("if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)\n" +
                                    "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 11)) {  // ATIVIDADES NORMAIS\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FOI DADO ENTRADA NA ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec());", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                            "                                @Override\n" +
                                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                            "                                    pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                            "                                    motoMecListView.setSelection(posicao + 1);", getLocalClassName());
                                    pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                                    motoMecListView.setSelection(posicao + 1);

                                }
                            });

                            alerta.show();

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) {

                            LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) {", getLocalClassName());
                            if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 0) {

                                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 0) {\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"FOI DADO ENTRADA NA ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec());", getLocalClassName());
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                                "                                    @Override\n" +
                                                "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                                "                                        pmmContext.getConfigCTR().setPosFluxoCarregComposto(1L);\n" +
                                                "                                        pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                                "                                        motoMecListView.setSelection(posicao + 1);", getLocalClassName());
                                        pmmContext.getConfigCTR().setPosFluxoCarregComposto(1L);
                                        pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                                        motoMecListView.setSelection(posicao + 1);

                                    }
                                });

                                alerta.show();

                            } else {

                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "String msg = \"\";\n" +
                                        "                                if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 1) {\n" +
                                        "                                    msg = \"POR FAVOR, CARREGUE O EQUIPAMENTO E DEPOIS PASSE NA BALANÇA PARA FAZER A PESAGEM CARREGADO!\";\n" +
                                        "                                } else if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 2) {\n" +
                                        "                                    msg = \"POR FAVOR, PASSE NA BALANÇA PARA FAZER A PESAGEM DO EQUIPAMENTO CARREGADO!\";\n" +
                                        "                                }\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(msg);\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                        motoMecListView.setSelection(posicao + 1);\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());
                                String msg = "";

                                if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 1) {
                                    msg = "POR FAVOR, CARREGUE O EQUIPAMENTO E DEPOIS PASSE NA BALANÇA PARA FAZER A PESAGEM CARREGADO!";
                                } else if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 2) {
                                    msg = "POR FAVOR, PASSE NA BALANÇA PARA FAZER A PESAGEM DO EQUIPAMENTO CARREGADO!";
                                }

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage(msg);
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        motoMecListView.setSelection(posicao + 1);
                                    }
                                });

                                alerta.show();

                            }

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) {

                            LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) {\n" +
                                    "                            pmmContext.getConfigCTR().setPosicaoTela(2L);\n" +
                                    "                            Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);", getLocalClassName());
                            pmmContext.getConfigCTR().setPosicaoTela(2L);
                            Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) {

                            LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) {", getLocalClassName());
                            if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 1) {

                                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 1) {", getLocalClassName());
                                if (pmmContext.getConfigCTR().getOS().getTipoOS() == 0L) {

                                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getOS().getTipoOS() == 0L) {\n" +
                                            "                                    pmmContext.getConfigCTR().setPosFluxoCarregComposto(2L);\n" +
                                            "                                    Intent it = new Intent(MenuPrincPCOMPActivity.this, LeiraActivity.class);", getLocalClassName());
                                    pmmContext.getConfigCTR().setPosFluxoCarregComposto(2L);
                                    Intent it = new Intent(MenuPrincPCOMPActivity.this, LeiraActivity.class);
                                    startActivity(it);
                                    finish();

                                } else {

                                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                            "                                    Intent it = new Intent(MenuPrincPCOMPActivity.this, ProdutoActivity.class);", getLocalClassName());
                                    Intent it = new Intent(MenuPrincPCOMPActivity.this, ProdutoActivity.class);
                                    startActivity(it);
                                    finish();

                                }

                            } else {

                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                String msg = \"\";\n" +
                                        "                                if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 0) {\n" +
                                        "                                    msg = \"POR FAVOR, TIRE A PESAGEM TARA DO EQUIPAMENTO!\";\n" +
                                        "                                } else {\n" +
                                        "                                    msg = \"POR FAVOR, PASSE NA BALANÇA PARA FAZER A PESAGEM DO EQUIPAMENTO CARREGADO!\";\n" +
                                        "                                }\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(msg);\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                        motoMecListView.setSelection(posicao + 1);\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());

                                String msg = "";

                                if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 0) {
                                    msg = "POR FAVOR, TIRE A PESAGEM TARA DO EQUIPAMENTO!";
                                } else {
                                    msg = "POR FAVOR, PASSE NA BALANÇA PARA FAZER A PESAGEM DO EQUIPAMENTO CARREGADO!";
                                }

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage(msg);
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        motoMecListView.setSelection(posicao + 1);
                                    }
                                });

                                alerta.show();

                            }

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) {

                            LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) {", getLocalClassName());
                            if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 2) {

                                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 2) {\n" +
                                        "                                pmmContext.getConfigCTR().setPosicaoTela(13L);\n" +
                                        "                                pmmContext.getConfigCTR().setPosFluxoCarregComposto(0L);\n" +
                                        "                                Intent it = new Intent(MenuPrincPCOMPActivity.this, EsperaInforActivity.class);", getLocalClassName());
                                pmmContext.getConfigCTR().setPosicaoTela(13L);
                                pmmContext.getConfigCTR().setPosFluxoCarregComposto(0L);
                                Intent it = new Intent(MenuPrincPCOMPActivity.this, EsperaInforActivity.class);
                                startActivity(it);
                                finish();

                            } else {

                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                String msg = \"\";\n" +
                                        "                                if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 0) {\n" +
                                        "                                    msg = \"POR FAVOR, TIRE A PESAGEM TARA DO EQUIPAMENTO!\";\n" +
                                        "                                } else if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 1) {\n" +
                                        "                                    msg = \"POR FAVOR, CARREGUE O EQUIPAMENTO E DEPOIS PASSE NA BALANÇA PARA FAZER A PESAGEM CARREGADO!\";\n" +
                                        "                                }\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(msg);\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                        motoMecListView.setSelection(posicao + 1);\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());

                                String msg = "";

                                if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 0) {
                                    msg = "POR FAVOR, TIRE A PESAGEM TARA DO EQUIPAMENTO!";
                                } else if (pmmContext.getConfigCTR().getConfig().getPosFluxoCarregComposto() == 1) {
                                    msg = "POR FAVOR, CARREGUE O EQUIPAMENTO E DEPOIS PASSE NA BALANÇA PARA FAZER A PESAGEM CARREGADO!";
                                }

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage(msg);
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        motoMecListView.setSelection(posicao + 1);
                                    }
                                });

                                alerta.show();

                            }

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 7) {

                            LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 7) {\n" +
                                    "                            pmmContext.getConfigCTR().setPosicaoTela(2L);\n" +
                                    "                            Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);", getLocalClassName());
                            pmmContext.getConfigCTR().setPosicaoTela(2L);
                            Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 10) {

                            LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 10) {\n" +
                                    "                            pmmContext.getConfigCTR().setPosicaoTela(15L);\n" +
                                    "                            Intent it = new Intent(MenuPrincPCOMPActivity.this, LeiraActivity.class);", getLocalClassName());
                            pmmContext.getConfigCTR().setPosicaoTela(15L);
                            Intent it = new Intent(MenuPrincPCOMPActivity.this, LeiraActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 5) {

                            LogProcessoDAO.getInstance().insertLogProcesso("else if (motoMecBean.getCodFuncaoOperMotoMec() == 5) {", getLocalClassName());
                            if (pmmContext.getCompostoCTR().pesqLeiraExibir()) {

                                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getCompostoCTR().pesqLeiraExibir()) {\n" +
                                        "                                pmmContext.getConfigCTR().setPosicaoTela(5L);\n" +
                                        "                                Intent it = new Intent(MenuPrincPCOMPActivity.this, InformacaoActivity.class);", getLocalClassName());
                                pmmContext.getConfigCTR().setPosicaoTela(5L);
                                Intent it = new Intent(MenuPrincPCOMPActivity.this, InformacaoActivity.class);
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
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });

                                alerta.show();

                            }


                        }

                    }
                }
            }

        });

        buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MenuPrincPCOMPActivity.this, MenuParadaPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(MenuPrincPCOMPActivity.this, MenuParadaPCOMPActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonRetMotoMec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pmmContext.getConfigCTR().setPosicaoTela(8L);\n" +
                        "                Intent it = new Intent(MenuPrincPCOMPActivity.this, HorimetroActivity.class);", getLocalClassName());
                pmmContext.getConfigCTR().setPosicaoTela(8L);
                Intent it = new Intent(MenuPrincPCOMPActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonLogMotoMec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonLogMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pmmContext.getConfigCTR().setPosicaoTela(25L);\n" +
                        "                Intent it = new Intent(MenuPrincPCOMPActivity.this, SenhaActivity.class);", getLocalClassName());
                pmmContext.getConfigCTR().setPosicaoTela(25L);
                Intent it = new Intent(MenuPrincPCOMPActivity.this, SenhaActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}