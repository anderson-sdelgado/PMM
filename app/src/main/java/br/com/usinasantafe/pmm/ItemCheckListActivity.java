package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.RespItemCLTO;

public class ItemCheckListActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private RespItemCLTO respItemCLTO;
    private ItemCheckListTO itemCheckListTO;
    private CabecCLTO cabecCLTO;

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

        cabecCLTO = new CabecCLTO();
        List cabecCheckListLista = cabecCLTO.get("statusCabCL", 1L);
        cabecCLTO = (CabecCLTO) cabecCheckListLista.get(0);
        cabecCheckListLista.clear();

        EquipTO equipTO = new EquipTO();
        List equipList;
        if(pmmContext.getTipoEquip() == 1) {
            equipList = equipTO.get("idEquip", pmmContext.getBoletimMMTO().getIdEquipBolMM());
        }
        else{
            equipList = equipTO.get("idEquip", pmmContext.getBoletimFertTO().getIdEquipBolFert());
        }
        equipTO = (EquipTO) equipList.get(0);
        equipList.clear();

        itemCheckListTO = new ItemCheckListTO();
        ArrayList itemListPesq = new ArrayList();

        EspecificaPesquisa pesq3 = new EspecificaPesquisa();
        pesq3.setCampo("seqItemCheckList");
        pesq3.setValor(pmmContext.getPosCheckList());
        pesq3.setTipo(1);
        itemListPesq.add(pesq3);

        EspecificaPesquisa pesq4 = new EspecificaPesquisa();
        pesq4.setCampo("idCheckList");
        pesq4.setValor(equipTO.getIdCheckList());
        pesq4.setTipo(1);
        itemListPesq.add(pesq4);

        List itemCheckListLista = itemCheckListTO.get(itemListPesq);
        itemCheckListTO = (ItemCheckListTO) itemCheckListLista.get(0);
        itemCheckListLista.clear();

        ArrayList respPesq = new ArrayList();
        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("idItBDItCL");
        pesq1.setValor(itemCheckListTO.getIdItemCheckList());
        pesq1.setTipo(1);
        respPesq.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("idCabItCL");
        pesq2.setValor(cabecCLTO.getIdCabCL());
        pesq2.setTipo(1);
        respPesq.add(pesq2);

        respItemCLTO = new RespItemCLTO();
        List respList = respItemCLTO.get(respPesq);
        if(respList.size() > 0){
            respItemCLTO = (RespItemCLTO) respList.get(0);
            respItemCLTO.delete();
        }

        textViewItemChecklist.setText(itemCheckListTO.getSeqItemCheckList() + " - " + itemCheckListTO.getDescrItemCheckList());

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

        respItemCLTO.setIdCabItCL(cabecCLTO.getIdCabCL());
        respItemCLTO.setIdItBDItCL(itemCheckListTO.getIdItemCheckList());
        respItemCLTO.setOpItCL(opcao);
        respItemCLTO.insert();

        if(cabecCLTO.getQtdeItemCabCL() == pmmContext.getPosCheckList()){

            ManipDadosEnvio.getInstance().salvaCheckList();

//            GRAFICO
            Intent it = new Intent(ItemCheckListActivity.this, EsperaDadosOperActivity.class);
            startActivity(it);
            finish();

//            ANTIGO SEM GRAFICO
//            Intent it = new Intent(ItemCheckListActivity.this, MenuPrincNormalActivity.class);
//            startActivity(it);
//            finish();

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
