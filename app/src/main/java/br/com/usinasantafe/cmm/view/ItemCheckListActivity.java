package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.RespItemCheckListBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ItemCheckListActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private TextView textViewItemChecklist;
    private List itemCheckListList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_checklist);

        cmmContext = (CMMContext) getApplication();

        textViewItemChecklist = findViewById(R.id.textViewItemChecklist);
        Button buttonConforme = findViewById(R.id.buttonConforme);
        Button buttonNaoConforme = findViewById(R.id.buttonNaoConforme);
        Button buttonReparo = findViewById(R.id.buttonReparo);
        Button buttonCancChecklist = findViewById(R.id.buttonCancChecklist);

        LogProcessoDAO.getInstance().insertLogProcesso("        itemCheckListList = pmmContext.getCheckListCTR().getItemList();\n" +
                "        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                "        textViewItemChecklist.setText(pmmContext.getCheckListCTR().getPosCheckList() + \" - \" + itemCheckListBean.getDescrItemCheckList());", getLocalClassName());

        itemCheckListList = cmmContext.getCheckListCTR().getItemList();
        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(cmmContext.getCheckListCTR().getPosCheckList() - 1);
        textViewItemChecklist.setText(cmmContext.getCheckListCTR().getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());

        buttonConforme.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonConforme.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                proximaTela(1L);", getLocalClassName());
            proximaTela(1L);
        });

        buttonNaoConforme.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonNaoConforme.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                proximaTela(2L);", getLocalClassName());
            proximaTela(2L);
        });

        buttonReparo.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonReparo.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                proximaTela(3L);", getLocalClassName());
            proximaTela(3L);
        });

        buttonCancChecklist.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancChecklist.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                retornoTela();", getLocalClassName());
            retornoTela();
        });

    }

    public void proximaTela(Long opcao){

        LogProcessoDAO.getInstance().insertLogProcesso("ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getCheckListCTR().getPosCheckList() - 1);", getLocalClassName());

        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(cmmContext.getCheckListCTR().getPosCheckList() - 1);
        LogProcessoDAO.getInstance().insertLogProcesso("ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                "        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();\n" +
                "        respItemCheckListBean.setIdItBDItCL(" + itemCheckListBean.getIdItemCheckList() + ");\n" +
                "        respItemCheckListBean.setOpItCL(" + opcao + ");", getLocalClassName());

        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        respItemCheckListBean.setIdItBDItCL(itemCheckListBean.getIdItemCheckList());
        respItemCheckListBean.setOpItCL(opcao);

        if(cmmContext.getCheckListCTR().verCabecAberto()) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getCheckListCTR().verCabecAberto()) {\n" +
                    "            pmmContext.getCheckListCTR().setRespCheckList(respItemCheckListBean);", getLocalClassName());
            cmmContext.getCheckListCTR().setRespCheckList(respItemCheckListBean);
            if (cmmContext.getCheckListCTR().qtdeItemCheckList() == cmmContext.getCheckListCTR().getPosCheckList()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getCheckListCTR().qtdeItemCheckList() == pmmContext.getCheckListCTR().getPosCheckList()) {\n" +
                        "                pmmContext.getConfigCTR().setCheckListConfig(pmmContext.getConfigCTR().getConfig().getIdTurnoConfig());\n" +
                        "                pmmContext.getCheckListCTR().salvarBolFechado();", getLocalClassName());
                cmmContext.getConfigCTR().setCheckListConfig(cmmContext.getConfigCTR().getConfig().getIdTurnoConfig());
                cmmContext.getCheckListCTR().salvarBolFechado(getLocalClassName());
                if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {", getLocalClassName());
                    if(BuildConfig.FLAVOR.equals("pmm")){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                                "                Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                        Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPMMActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else if(BuildConfig.FLAVOR.equals("ecm")){
                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                                "                Intent it = new Intent(ItemCheckListActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                        Intent it = new Intent(ItemCheckListActivity.this, MenuPrincECMActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else if(BuildConfig.FLAVOR.equals("pcomp")){
                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                                "                Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                        Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPCOMPActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(ItemCheckListActivity.this, VerifOperadorActivity.class);", getLocalClassName());
                    Intent it = new Intent(ItemCheckListActivity.this, VerifOperadorActivity.class);
                    startActivity(it);
                    finish();
                }
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCheckListCTR().setPosCheckList(pmmContext.getCheckListCTR().getPosCheckList() + 1);\n" +
                        "                itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                        "                textViewItemChecklist.setText(pmmContext.getCheckListCTR().getPosCheckList() + \" - \" + itemCheckListBean.getDescrItemCheckList());", getLocalClassName());
                cmmContext.getCheckListCTR().setPosCheckList(cmmContext.getCheckListCTR().getPosCheckList() + 1);
                itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(cmmContext.getCheckListCTR().getPosCheckList() - 1);
                textViewItemChecklist.setText(cmmContext.getCheckListCTR().getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
            }
        }
        else{
            if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {", getLocalClassName());
                if(BuildConfig.FLAVOR.equals("pmm")){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                            "                Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(BuildConfig.FLAVOR.equals("ecm")){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                            "                Intent it = new Intent(ItemCheckListActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ItemCheckListActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(BuildConfig.FLAVOR.equals("pcomp")){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                            "                Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                Intent it = new Intent(ItemCheckListActivity.this, VerifOperadorActivity.class);", getLocalClassName());
                Intent it = new Intent(ItemCheckListActivity.this, VerifOperadorActivity.class);
                startActivity(it);
                finish();
            }
        }

    }

    public void retornoTela(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void retornoTela(){", getLocalClassName());
        if(cmmContext.getCheckListCTR().getPosCheckList() > 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getCheckListCTR().getPosCheckList() > 1){\n" +
                    "            pmmContext.getCheckListCTR().setPosCheckList(pmmContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                    "            ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                    "            textViewItemChecklist.setText(pmmContext.getCheckListCTR().getPosCheckList() + \" - \" + itemCheckListBean.getDescrItemCheckList());", getLocalClassName());
            cmmContext.getCheckListCTR().setPosCheckList(cmmContext.getCheckListCTR().getPosCheckList() - 1);
            ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(cmmContext.getCheckListCTR().getPosCheckList() - 1);
            textViewItemChecklist.setText(cmmContext.getCheckListCTR().getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
        }
    }

    public void onBackPressed()  {
    }

}
