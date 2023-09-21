package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.REquipPneuBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ListaPosPneuActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private ArrayList<REquipPneuBean> posPneuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pos_pneu);

        cmmContext = (CMMContext) getApplication();

        Button buttonCancPosPneu = findViewById(R.id.buttonCancPosPneu);
        Button buttonFinalCalibragem = findViewById(R.id.buttonFinalCalibragem);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaHistorico = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pmmContext.getMotoMecFertCTR().apontList());\n" +
                "        listaHistorico.setAdapter(adapterListHistorico);", getLocalClassName());
        posPneuList = cmmContext.getMotoMecFertCTR().posPneuList();
        ListView listaPosPneu = findViewById(R.id.listaPosPneu);
        AdapterListPosPneu adapterListPosPneu = new AdapterListPosPneu(this, posPneuList);
        listaPosPneu.setAdapter(adapterListPosPneu);

        listaPosPneu.setOnItemClickListener((l, v, position, id) -> {
            LogProcessoDAO.getInstance().insertLogProcesso("listaPosPneu.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                pmmContext.getMotoMecFertCTR().getItemMedPneuDAO().setItemMedPneuBean();\n" +
                    "                pmmContext.getMotoMecFertCTR().getItemMedPneuDAO().getItemMedPneuBean().setIdPosConfPneu(posPneuList.get(position).getIdPosConfPneu());\n" +
                    "                posPneuList.clear();\n" +
                    "                Intent it = new Intent(ListaPosPneuActivity.this, PneuActivity.class);", getLocalClassName());
            cmmContext.getMotoMecFertCTR().getItemMedPneuDAO().setItemMedPneuBean();
            cmmContext.getMotoMecFertCTR().getItemMedPneuDAO().getItemMedPneuBean().setIdPosConfItemCalibPneu(posPneuList.get(position).getIdPosConfPneu());
            posPneuList.clear();
            Intent it = new Intent(ListaPosPneuActivity.this, PneuActivity.class);
            startActivity(it);
            finish();
        });

        buttonFinalCalibragem.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonFinalCalibragem.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(cmmContext.getMotoMecFertCTR().verifFinalItemPneuBoletimAberto()){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().verifFinalItemPneuBoletimAberto()){\n" +
                        "                    pmmContext.getMotoMecFertCTR().fecharBoletimPneu();\n" +
                        "                    pmmContext.getMotoMecFertCTR().fecharApont(getLocalClassName());", getLocalClassName());
                cmmContext.getMotoMecFertCTR().fecharBoletimPneu(getLocalClassName());
                if(BuildConfig.FLAVOR.equals("pmm")){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                            "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(BuildConfig.FLAVOR.equals("ecm")){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                            "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(BuildConfig.FLAVOR.equals("pcomp")){
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
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });

                alerta.show();
            }

        });

        buttonCancPosPneu.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancPosPneu.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPosPneuActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE CANCELAR O CALIBRAÇÃO DE PNEU?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPosPneuActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE CANCELAR O CALIBRAÇÃO DE PNEU?");
            alerta.setPositiveButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {\n" +
                        "                    pmmContext.getMotoMecFertCTR().deletePneuAberto();", getLocalClassName());
                cmmContext.getMotoMecFertCTR().deletePneuAberto();
                if(BuildConfig.FLAVOR.equals("pmm")){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                            "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(BuildConfig.FLAVOR.equals("ecm")){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                            "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(BuildConfig.FLAVOR.equals("pcomp")){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                            "Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }

            });

            alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });
    }

    @Override
    public void onBackPressed()  {
    }

}