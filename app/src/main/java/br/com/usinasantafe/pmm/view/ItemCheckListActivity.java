package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCheckListBean;

public class ItemCheckListActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private TextView textViewItemChecklist;
    private List itemCheckListList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_checklist);

        pmmContext = (PMMContext) getApplication();

        textViewItemChecklist = findViewById(R.id.textViewItemChecklist);
        Button buttonConforme = findViewById(R.id.buttonConforme);
        Button buttonNaoConforme = findViewById(R.id.buttonNaoConforme);
        Button buttonReparo = findViewById(R.id.buttonReparo);
        Button buttonCancChecklist = findViewById(R.id.buttonCancChecklist);

        itemCheckListList = pmmContext.getCheckListCTR().getItemList();
        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getCheckListCTR().getPosCheckList() - 1);
        textViewItemChecklist.setText(pmmContext.getCheckListCTR().getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());

        buttonConforme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                proximaTela(1L);

            }

        });

        buttonNaoConforme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                proximaTela(2L);
            }

        });

        buttonReparo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                proximaTela(3L);
            }

        });

        buttonCancChecklist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                retornoTela();
            }

        });

    }

    public void proximaTela(Long opcao){

        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getCheckListCTR().getPosCheckList() - 1);
        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        respItemCheckListBean.setIdItBDItCL(itemCheckListBean.getIdItemCheckList());
        respItemCheckListBean.setOpItCL(opcao);

        if(pmmContext.getCheckListCTR().verCabecAberto()) {
            pmmContext.getCheckListCTR().setRespCheckList(respItemCheckListBean);

            if (pmmContext.getCheckListCTR().qtdeItemCheckList() == pmmContext.getCheckListCTR().getPosCheckList()) {
                pmmContext.getConfigCTR().setCheckListConfig(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().getIdTurnoBolMMFert());
                pmmContext.getCheckListCTR().salvarBolFechado();
                if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                    Intent it = new Intent(ItemCheckListActivity.this, EsperaInforActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    Intent it = new Intent(ItemCheckListActivity.this, VerifOperadorActivity.class);
                    startActivity(it);
                    finish();
                }
            } else {
                pmmContext.getCheckListCTR().setPosCheckList(pmmContext.getCheckListCTR().getPosCheckList() + 1);
                itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getCheckListCTR().getPosCheckList() - 1);
                textViewItemChecklist.setText(pmmContext.getCheckListCTR().getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
            }
        }
        else{
            if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                Intent it = new Intent(ItemCheckListActivity.this, EsperaInforActivity.class);
                startActivity(it);
                finish();
            } else {
                Intent it = new Intent(ItemCheckListActivity.this, VerifOperadorActivity.class);
                startActivity(it);
                finish();
            }
        }

    }

    public void retornoTela(){
        if(pmmContext.getCheckListCTR().getPosCheckList() > 1){
            pmmContext.getCheckListCTR().setPosCheckList(pmmContext.getCheckListCTR().getPosCheckList() - 1);
            ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getCheckListCTR().getPosCheckList() - 1);
            textViewItemChecklist.setText(pmmContext.getCheckListCTR().getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
        }
    }

    public void onBackPressed()  {
    }

}
