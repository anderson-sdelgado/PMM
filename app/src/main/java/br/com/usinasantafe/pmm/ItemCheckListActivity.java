package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCLBean;

public class ItemCheckListActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ItemCheckListBean itemCheckListBean;
    private CheckListCTR checkListCTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_checklist);

        pmmContext = (PMMContext) getApplication();

        TextView textViewItemChecklist = (TextView) findViewById(R.id.textViewItemChecklist);
        Button buttonConforme = (Button) findViewById(R.id.buttonConforme);
        Button buttonNaoConforme = (Button) findViewById(R.id.buttonNaoConforme);
        Button buttonReparo = (Button) findViewById(R.id.buttonReparo);
        Button buttonCancChecklist = (Button) findViewById(R.id.buttonCancChecklist);

        checkListCTR = new CheckListCTR();
        itemCheckListBean = checkListCTR.getItemCheckList(pmmContext.getPosCheckList());

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

        RespItemCLBean respItemCLBean = new RespItemCLBean();
        respItemCLBean.setIdItBDItCL(itemCheckListBean.getIdItemCheckList());
        respItemCLBean.setOpItCL(opcao);
        checkListCTR.setRespCheckList(respItemCLBean);

        if(checkListCTR.qtdeItemCheckList() == pmmContext.getPosCheckList()){
            ConfigCTR configCTR = new ConfigCTR();
            configCTR.setCheckListConfig(pmmContext.getBoletimCTR().getTurno());
            checkListCTR.salvarBolFechado();
            Intent it = new Intent(ItemCheckListActivity.this, EsperaInforActivity.class);
            startActivity(it);
            finish();
        }
        else{
            pmmContext.setPosCheckList(pmmContext.getPosCheckList() + 1);
            Intent it = new Intent(ItemCheckListActivity.this, ItemCheckListActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void retornoTela(){
        if(pmmContext.getPosCheckList() > 1){
            pmmContext.setPosCheckList(pmmContext.getPosCheckList() - 1);
            Intent it = new Intent(ItemCheckListActivity.this, ItemCheckListActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void onBackPressed()  {
    }

}
