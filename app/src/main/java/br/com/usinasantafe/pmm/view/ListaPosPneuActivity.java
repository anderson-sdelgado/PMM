package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.REquipPneuBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class ListaPosPneuActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ArrayList<REquipPneuBean> posPneuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pos_pneu);

        pmmContext = (PMMContext) getApplication();

        Button buttonCancPosPneu = findViewById(R.id.buttonCancPosPneu);
        Button buttonFinalCalibragem = findViewById(R.id.buttonFinalCalibragem);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaHistorico = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pmmContext.getMotoMecFertCTR().apontList());\n" +
                "        listaHistorico.setAdapter(adapterListHistorico);", getLocalClassName());
        posPneuList = pmmContext.getMotoMecFertCTR().posPneuList();
        ListView listaPosPneu = findViewById(R.id.listaPosPneu);
        AdapterListPosPneu adapterListPosPneu = new AdapterListPosPneu(this, pmmContext.getMotoMecFertCTR().posPneuList());
        listaPosPneu.setAdapter(adapterListPosPneu);

        listaPosPneu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("listaPosPneu.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                pmmContext.getMotoMecFertCTR().getItemMedPneuDAO().setItemMedPneuBean();\n" +
                        "                pmmContext.getMotoMecFertCTR().getItemMedPneuDAO().getItemMedPneuBean().setIdPosConfPneu(posPneuList.get(position).getIdPosConfPneu());\n" +
                        "                posPneuList.clear();\n" +
                        "                Intent it = new Intent(ListaPosPneuActivity.this, PneuActivity.class);", getLocalClassName());
                pmmContext.getMotoMecFertCTR().getItemMedPneuDAO().setItemMedPneuBean();
                pmmContext.getMotoMecFertCTR().getItemMedPneuDAO().getItemMedPneuBean().setIdPosConfItemCalibPneu(posPneuList.get(position).getIdPosConfPneu());
                posPneuList.clear();
                Intent it = new Intent(ListaPosPneuActivity.this, PneuActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonFinalCalibragem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonFinalCalibragem.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pmmContext.getMotoMecFertCTR().verifFinalItemPneuBoletimAberto()){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().verifFinalItemPneuBoletimAberto()){\n" +
                            "                    pmmContext.getMotoMecFertCTR().fecharBoletimPneu();\n" +
                            "                    pmmContext.getMotoMecFertCTR().fecharApont(getLocalClassName());", getLocalClassName());
                    pmmContext.getMotoMecFertCTR().fecharBoletimPneu();
                    pmmContext.getMotoMecFertCTR().fecharApont(getLocalClassName());
                    if(PMMContext.aplic == 1){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                                "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                        Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else if(PMMContext.aplic == 2){
                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                                "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                        Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincECMActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else if(PMMContext.aplic == 3){
                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                                "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                        Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPCOMPActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(PressaoEncPneuActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"POR FAVOR, TERMINE A CALIBRAGEM EM TODOS OS PNEU DO EQUIPAMENTO.\");\n" +
                            "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            }\n" +
                            "                        });\n" +
                            "                        alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPosPneuActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, TERMINE A CALIBRAGEM EM TODOS OS PNEU DO EQUIPAMENTO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();
                }

            }
        });

        buttonCancPosPneu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancPosPneu.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPosPneuActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE CANCELAR O CALIBRAÇÃO DE PNEU?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPosPneuActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE CANCELAR O CALIBRAÇÃO DE PNEU?");
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {\n" +
                                "                    pmmContext.getMotoMecFertCTR().deletePneuAberto();", getLocalClassName());
                        pmmContext.getMotoMecFertCTR().deletePneuAberto();
                        if(PMMContext.aplic == 1){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                                    "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                            Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else if(PMMContext.aplic == 2){
                            LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                                    "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                            Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincECMActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else if(PMMContext.aplic == 3){
                            LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                                    "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                            Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPCOMPActivity.class);
                            startActivity(it);
                            finish();
                        }

                    }

                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }

                });

                alerta.show();

            }
        });
    }

    @Override
    public void onBackPressed()  {
    }

}