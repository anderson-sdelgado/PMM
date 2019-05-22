package br.com.usinasantafe.pmm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.tb.estaticas.REquipPneuTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimPneuTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ItemMedPneuTO;

public class ListaPosPneuActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pos_pneu);

        pmmContext = (PMMContext) getApplication();

        Button buttonAtualPosPneu = (Button) findViewById(R.id.buttonAtualPosPneu);

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
        List configList = configuracaoTO.all();
        configuracaoTO = (ConfiguracaoTO) configList.get(0);
        configList.clear();

        buttonAtualPosPneu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ArrayList<String> itens = new ArrayList<String>();

        REquipPneuTO rEquipPneuTO = new REquipPneuTO();
        List rEquipPneuList = rEquipPneuTO.all();

        BoletimPneuTO boletimPneuTO = new BoletimPneuTO();
        List boletimPneuList = boletimPneuTO.get("statusBolPneu", 1L);

        if(boletimPneuList.size() == 0){

            ApontaMMTO apontaMMTO = new ApontaMMTO();
            List apontaMMList = apontaMMTO.get("statusAponta", 1L);
            apontaMMTO = (ApontaMMTO) apontaMMList.get(0);
            apontaMMList.clear();

            boletimPneuTO.setIdApontBolPneu(apontaMMTO.getIdAponta());
            boletimPneuTO.setEquipBolPneu(configuracaoTO.getEquipConfig());
            boletimPneuTO.setFuncBolPneu(pmmContext.getBoletimMMTO().getCodMotoBoletim());
            boletimPneuTO.setDthrBolPneu(Tempo.getInstance().datahora());
            boletimPneuTO.setStatusBolPneu(1L);
            boletimPneuTO.insert();

            for(int i = 0; i < rEquipPneuList.size(); i++){
                rEquipPneuTO = (REquipPneuTO) rEquipPneuList.get(i);
                itens.add(rEquipPneuTO.getPosPneu());
            }

        }
        else{
            boletimPneuTO = (BoletimPneuTO) boletimPneuList.get(0);
            ItemMedPneuTO itemMedPneuTO = new ItemMedPneuTO();
            List itemMedPneuList = itemMedPneuTO.get("idBolItemMedPneu", boletimPneuTO.getIdBolPneu());
            boolean verCad;
            for(int i = 0; i < rEquipPneuList.size(); i++){
                rEquipPneuTO = (REquipPneuTO) rEquipPneuList.get(i);
                verCad = true;
                for(int j = 0; j < itemMedPneuList.size(); j++) {
                    itemMedPneuTO = (ItemMedPneuTO) itemMedPneuList.get(j);
                    if(rEquipPneuTO.getIdPosConfPneu() == itemMedPneuTO.getPosItemMedPneu()){
                        verCad = false;
                    }
                }
                if(verCad) {
                    itens.add(rEquipPneuTO.getPosPneu());
                }
            }
            itemMedPneuList.clear();
        }

        rEquipPneuList.clear();
        boletimPneuList.clear();

        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, R.layout.activity_item_lista, R.id.textViewItemList, itens);
        lista = (ListView) findViewById(R.id.listaPosPneu);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String posPneu = textView.getText().toString();

                REquipPneuTO rEquipPneuTO = new REquipPneuTO();
                List rEquipPneuList = rEquipPneuTO.get("posPneu", posPneu);
                rEquipPneuTO = (REquipPneuTO) rEquipPneuList.get(0);
                pmmContext.getItemMedPneuTO().setPosItemMedPneu(rEquipPneuTO.getIdPosConfPneu());
                rEquipPneuList.clear();

                Intent it = new Intent(ListaPosPneuActivity.this, PneuActivity.class);
                startActivity(it);

            }

        });

    }

    public void onBackPressed() {
    }

}