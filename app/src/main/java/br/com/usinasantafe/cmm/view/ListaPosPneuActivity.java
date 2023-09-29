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
        Button buttonFinalBoletim = findViewById(R.id.buttonFinalBoletim);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaHistorico = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pmmContext.getMotoMecFertCTR().apontList());\n" +
                "        listaHistorico.setAdapter(adapterListHistorico);", getLocalClassName());

        if(cmmContext.getPneuCTR().getBolPneuAberto().getTipoBolPneu() == 1L) {
            posPneuList = cmmContext.getPneuCTR().rEquipPneuCalibList();
        } else {
            posPneuList = cmmContext.getPneuCTR().rEquipPneuManutList();
        }

        ListView listViewPosPneu = findViewById(R.id.listViewPosPneu);
        AdapterListPosPneu adapterListPosPneu = new AdapterListPosPneu(this, posPneuList);
        listViewPosPneu.setAdapter(adapterListPosPneu);

        listViewPosPneu.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("listViewPosPneu.setOnItemClickListener((l, v, position, id) -> {", getLocalClassName());
            if(cmmContext.getPneuCTR().getBolPneuAberto().getTipoBolPneu() == 1L) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(pbmContext.getVerTela() == 3) {\n" +
                        "                    LogProcessoDAO.getInstance().insertLogProcesso(\"if(pbmContext.getVerTela() == 3) {\\n\" +\n" +
                        "                            \"                    pbmContext.getPneuCTR().setItemCalibPneuBean(new ItemCalibPneuBean());\\n\" +\n" +
                        "                            \"                    pbmContext.getPneuCTR().getItemCalibPneuBean().setIdPosItemCalibPneu(pbmContext.getPneuCTR().getREquipPneu(Long.parseLong(posPneu)).getIdPosConfPneu());\\n\" +\n" +
                        "                            \"                    Intent it = new Intent(ListaPosPneuActivity.this, PneuCalibActivity.class);\", getLocalClassName());\n" +
                        "                    pbmContext.getPneuCTR().setItemCalibPneuBean(new ItemCalibPneuBean());\n" +
                        "                    pbmContext.getPneuCTR().getItemCalibPneuBean().setIdPosItemCalibPneu(posPneuList.get(position).getIdPosConfPneu());\n" +
                        "                    posPneuList.clear();\n" +
                        "                    Intent it = new Intent(ListaPosPneuActivity.this, PneuCalibActivity.class);", getLocalClassName());
                cmmContext.getPneuCTR().setItemCalibPneuBean();
                cmmContext.getPneuCTR().getItemCalibPneuBean().setIdPosConfItemCalibPneu(posPneuList.get(position).getIdPosConfPneu());
                posPneuList.clear();
                Intent it = new Intent(ListaPosPneuActivity.this, PneuCalibActivity.class);
                startActivity(it);

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                cmmContext.getPneuCTR().setItemManutPneuBean();\n" +
                        "                cmmContext.getPneuCTR().getItemManutPneuBean().setIdPosConfItemManutPneu(posPneuList.get(position).getIdPosConfPneu());\n" +
                        "                posPneuList.clear();\n" +
                        "                Intent it = new Intent(ListaPosPneuActivity.this, PneuRetActivity.class);", getLocalClassName());
                cmmContext.getPneuCTR().setItemManutPneuBean();
                cmmContext.getPneuCTR().getItemManutPneuBean().setIdPosConfItemManutPneu(posPneuList.get(position).getIdPosConfPneu());
                posPneuList.clear();
                Intent it = new Intent(ListaPosPneuActivity.this, PneuRetActivity.class);
                startActivity(it);

            }
            finish();

        });

        buttonFinalBoletim.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonFinalBoletim.setOnClickListener(v -> {", getLocalClassName());
            if(cmmContext.getPneuCTR().getBolPneuAberto().getTipoBolPneu() == 1L) {
                LogProcessoDAO.getInstance().insertLogProcesso("if(cmmContext.getPneuCTR().getBolPneuAberto().getTipoBolPneu() == 1L) {", getLocalClassName());
                if(cmmContext.getPneuCTR().verifFinalItemCalibPneuBolAberto()){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(cmmContext.getPneuCTR().verifFinalItemPneuBolAberto()){\n" +
                            "                    cmmContext.getPneuCTR().fecharBolPneu(getLocalClassName());\n" +
                            "                    Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    cmmContext.getPneuCTR().fecharBolPneu(getLocalClassName());
                    Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();

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

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                if(cmmContext.getPneuCTR().hasItemPneuManutBolAberto()) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if(cmmContext.getPneuCTR().hasItemPneuManutBolAberto()) {\n" +
                            "                    cmmContext.getPneuCTR().fecharBolPneu(getLocalClassName());\n" +
                            "                    Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    cmmContext.getPneuCTR().fecharBolPneu(getLocalClassName());
                    Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPosPneuActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"NENHUM APONTAMENTO DE TROCA DE PNEU FOI REALIZADO! FAVOR REALIZAR ALGUMA TROCA PARA FINALIZAR O BOLETIM.\");\n" +
                            "                    alerta.setPositiveButton(\"OK\", (dialog, which) -> {\n" +
                            "                    });\n" +
                            "                    alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPosPneuActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NENHUM APONTAMENTO DE TROCA DE PNEU FOI REALIZADO! FAVOR REALIZAR ALGUMA TROCA PARA FINALIZAR O BOLETIM.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();

                }

            }

        });

        buttonCancPosPneu.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancPosPneu.setOnClickListener(v -> {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPosPneuActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE CANCELAR O CALIBRAÇÃO DE PNEU?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPosPneuActivity.this);
            alerta.setTitle("ATENÇÃO");
            String msg = "";
            if(cmmContext.getPneuCTR().getBolPneuAberto().getTipoBolPneu() == 1L) {
                msg = "DESEJA REALMENTE CANCELAR O CALIBRAÇÃO DE PNEU?";
            } else {
                msg = "DESEJA REALMENTE CANCELAR O MANUTENÇÃO DE PNEU?";
            }
            alerta.setMessage(msg);
            alerta.setPositiveButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", (dialog, which) -> {\n" +
                        "                cmmContext.getPneuCTR().deletePneuAberto();\n" +
                        "                Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                cmmContext.getPneuCTR().deleteBolPneuAberto();
                Intent it = new Intent(ListaPosPneuActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();

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