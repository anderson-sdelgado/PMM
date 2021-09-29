package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class MenuParadaPCOMPActivity extends ActivityGeneric {

    private ListView paradaListView;
    private PMMContext pmmContext;
    private List<MotoMecBean> paradaList;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_parada_pcomp);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetMenuParada = findViewById(R.id.buttonRetMenuParada);

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<String>();\n" +
                "        paradaList = pmmContext.getMotoMecFertCTR().paradaList();\n" +
                "        for(MotoMecBean motoMecBean : paradaList){\n" +
                "            itens.add(motoMecBean.getDescrOperMotoMec());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        paradaListView = findViewById(R.id.listViewMotParada);\n" +
                "        paradaListView.setAdapter(adapterList);", getLocalClassName());
        ArrayList<String> itens = new ArrayList<String>();
        paradaList = pmmContext.getMotoMecFertCTR().paradaList();
        for(MotoMecBean motoMecBean : paradaList){
            itens.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        paradaListView = findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(adapterList);

        paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                posicao = position;\n" +
                        "                MotoMecBean motoMecBean = paradaList.get(posicao);\n" +
                        "                pmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);", getLocalClassName());

                posicao = position;
                MotoMecBean motoMecBean = paradaList.get(posicao);
                pmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);

                if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                            "                    Toast.makeText(MenuParadaPCOMPActivity.this, \"POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.\",\n" +
                            "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                    Toast.makeText(MenuParadaPCOMPActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    LogProcessoDAO.getInstance().insertLogProcesso("else {", getLocalClassName());
                    if (pmmContext.getMotoMecFertCTR().verifBackupApont()) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verifBackupApont()) {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaPCOMPActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaPCOMPActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        alerta.show();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaPCOMPActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"FOI DADO ENTRADA NA ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec());", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaPCOMPActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                if (connectNetwork) {\n" +
                                        "                                    pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                        "                                } else {\n" +
                                        "                                    pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                        "                                }", getLocalClassName());
                                if (connectNetwork) {
                                    pmmContext.getConfigCTR().setStatusConConfig(1L);
                                } else {
                                    pmmContext.getConfigCTR().setStatusConConfig(0L);
                                }
                                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                        "                                paradaListView.setSelection(posicao + 1);", getLocalClassName());
                                pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                                paradaListView.setSelection(posicao + 1);
                            }
                        });
                        alerta.show();

                    }

                }

            }

        });

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                if (connectNetwork) {\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                        "                }\n" +
                        "                else{\n" +
                        "                    pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                        "                }", getLocalClassName());
                if (connectNetwork) {
                    pmmContext.getConfigCTR().setStatusConConfig(1L);
                }
                else{
                    pmmContext.getConfigCTR().setStatusConConfig(0L);
                }

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());

                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MenuParadaPCOMPActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(MenuParadaPCOMPActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void onBackPressed()  {
    }

}