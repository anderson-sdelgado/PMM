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
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RespItemCheckListTO;

public class ItemChecklistActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private RespItemCheckListTO respItemCheckListTO;
    private ItemCheckListTO itemCheckListTO;
    private CabecCheckListTO cabecCheckListTO;

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

        cabecCheckListTO = new CabecCheckListTO();
        List cabecCheckListLista = cabecCheckListTO.get("statusCab", 1L);
        cabecCheckListTO = (CabecCheckListTO) cabecCheckListLista.get(0);
        cabecCheckListLista.clear();

        EquipTO equipTO = new EquipTO();
        List equipList = equipTO.get("idEquip", pmmContext.getBoletimMMTO().getCodEquipBoletim());
        equipTO = (EquipTO) equipList.get(0);
        equipList.clear();

        itemCheckListTO = new ItemCheckListTO();
        ArrayList itemListPesq = new ArrayList();

        EspecificaPesquisa pesq3 = new EspecificaPesquisa();
        pesq3.setCampo("seqItemChecklist");
        pesq3.setValor(pmmContext.getPosChecklist());
        itemListPesq.add(pesq3);

        EspecificaPesquisa pesq4 = new EspecificaPesquisa();
        pesq4.setCampo("idChecklist");
        pesq4.setValor(equipTO.getIdChecklist());
        itemListPesq.add(pesq4);

        List itemCheckListLista = itemCheckListTO.get(itemListPesq);
        itemCheckListTO = (ItemCheckListTO) itemCheckListLista.get(0);
        itemCheckListLista.clear();

        ArrayList respPesq = new ArrayList();
        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("idItBDIt");
        pesq1.setValor(itemCheckListTO.getIdItemChecklist());
        respPesq.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("idCabIt");
        pesq2.setValor(cabecCheckListTO.getIdCab());
        respPesq.add(pesq2);

        respItemCheckListTO = new RespItemCheckListTO();
        List respList = respItemCheckListTO.get(respPesq);
        if(respList.size() > 0){
            respItemCheckListTO = (RespItemCheckListTO) respList.get(0);
            respItemCheckListTO.delete();
        }

        textViewItemChecklist.setText(itemCheckListTO.getSeqItemChecklist() + " - " + itemCheckListTO.getDescrItemChecklist());

        buttonConforme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                proximaTela(1L);

            }

        });

        buttonNaoConforme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                proximaTela(2L);
            }

        });

        buttonReparo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
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

        respItemCheckListTO.setIdCabIt(cabecCheckListTO.getIdCab());
        respItemCheckListTO.setIdItBDIt(itemCheckListTO.getIdItemChecklist());
        respItemCheckListTO.setOpIt(opcao);
        respItemCheckListTO.insert();

        if(cabecCheckListTO.getQtdeItemCab() == pmmContext.getPosChecklist()){

            ConfiguracaoTO configTO = new ConfiguracaoTO();
            List listConfigTO = configTO.all();
            configTO = (ConfiguracaoTO) listConfigTO.get(0);
            listConfigTO.clear();
            configTO.setHorimetroConfig(pmmContext.getBoletimMMTO().getHodometroInicialBoletim());
            configTO.update();
            configTO.commit();

//            GRAFICO
//            Intent it = new Intent(ItemChecklistActivity.this, EsperaGrafActivity.class);
//            startActivity(it);
//            finish();

//            ANTIGO SEM GRAFICO
            Intent it = new Intent(ItemChecklistActivity.this, MenuPrincNormalActivity.class);
            startActivity(it);
            finish();

            ManipDadosEnvio.getInstance().salvaCheckList();

        }
        else{

            pmmContext.setPosChecklist(pmmContext.getPosChecklist() + 1);
            Intent it = new Intent(ItemChecklistActivity.this, ItemChecklistActivity.class);
            startActivity(it);
            finish();

        }

    }

    public void retornoTela(){

        if(pmmContext.getPosChecklist() > 1){
            pmmContext.setPosChecklist(pmmContext.getPosChecklist() - 1);
            Intent it = new Intent(ItemChecklistActivity.this, ItemChecklistActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void onBackPressed()  {
    }

}
