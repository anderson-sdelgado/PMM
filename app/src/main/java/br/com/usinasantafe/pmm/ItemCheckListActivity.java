package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.bean.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.bean.variaveis.RespItemCLTO;

public class ItemCheckListActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ItemCheckListTO itemCheckListTO;
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
        itemCheckListTO = checkListCTR.getItemCheckList(pmmContext.getPosCheckList());

        textViewItemChecklist.setText(pmmContext.getPosCheckList() + " - " + itemCheckListTO.getDescrItemCheckList());

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

        RespItemCLTO respItemCLTO = new RespItemCLTO();
        respItemCLTO.setIdItBDItCL(itemCheckListTO.getIdItemCheckList());
        respItemCLTO.setOpItCL(opcao);
        checkListCTR.setRespCheckList(respItemCLTO);

        if(checkListCTR.qtdeItemCheckList() == pmmContext.getPosCheckList()){
            ConfigCTR configCTR = new ConfigCTR();
            configCTR.setCheckListConfig(pmmContext.getBoletimCTR().getTurno());
            checkListCTR.salvarBolFechado();
            Intent it = new Intent(ItemCheckListActivity.this, EsperaDadosOperActivity.class);
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
