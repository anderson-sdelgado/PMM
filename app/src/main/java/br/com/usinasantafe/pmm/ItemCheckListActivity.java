package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCLBean;

public class ItemCheckListActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private TextView textViewItemChecklist;
    private List itemCheckListList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_checklist);

        pmmContext = (PMMContext) getApplication();

        textViewItemChecklist = (TextView) findViewById(R.id.textViewItemChecklist);
        Button buttonConforme = (Button) findViewById(R.id.buttonConforme);
        Button buttonNaoConforme = (Button) findViewById(R.id.buttonNaoConforme);
        Button buttonReparo = (Button) findViewById(R.id.buttonReparo);
        Button buttonCancChecklist = (Button) findViewById(R.id.buttonCancChecklist);

        itemCheckListList = pmmContext.getCheckListCTR().getItemList();
        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getPosCheckList() - 1);
        textViewItemChecklist.setText(pmmContext.getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());

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
                // TODO Auto-generated method stub
                retornoTela();
            }

        });

    }

    public void proximaTela(Long opcao){

        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getPosCheckList() - 1);
        RespItemCLBean respItemCLBean = new RespItemCLBean();
        respItemCLBean.setIdItBDItCL(itemCheckListBean.getIdItemCheckList());
        respItemCLBean.setOpItCL(opcao);
        pmmContext.getCheckListCTR().setRespCheckList(respItemCLBean);

        if(pmmContext.getCheckListCTR().qtdeItemCheckList() == pmmContext.getPosCheckList()){
            pmmContext.getConfigCTR().setCheckListConfig(pmmContext.getBoletimCTR().getTurno());
            pmmContext.getCheckListCTR().salvarBolFechado();
            Intent it = new Intent(ItemCheckListActivity.this, EsperaInforActivity.class);
            startActivity(it);
            finish();
        }
        else{
            pmmContext.setPosCheckList(pmmContext.getPosCheckList() + 1);
            itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getPosCheckList() - 1);
            textViewItemChecklist.setText(pmmContext.getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
        }

    }

    public void retornoTela(){
        if(pmmContext.getPosCheckList() > 1){
            pmmContext.setPosCheckList(pmmContext.getPosCheckList() - 1);
            ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pmmContext.getPosCheckList() - 1);
            textViewItemChecklist.setText(pmmContext.getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
        }
    }

    public void onBackPressed()  {
    }

}
