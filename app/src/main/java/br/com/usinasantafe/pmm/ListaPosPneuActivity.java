package br.com.usinasantafe.pmm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.tb.estaticas.REquipPneuTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.TurnoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimPneuTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ItemMedPneuTO;

public class ListaPosPneuActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView lista;
    private List rEquipPneuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pos_pneu);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetPosPneu = (Button) findViewById(R.id.buttonRetPosPneu);
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
        rEquipPneuList = rEquipPneuTO.all();

        BoletimPneuTO boletimPneuTO = new BoletimPneuTO();
        List boletimPneuList = boletimPneuTO.get("statusBolPneu", 1L);

        if(boletimPneuList.size() == 0){
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
            List itemMedPneuList = itemMedPneuTO.get("", "");
        }

        boletimPneuList.clear();



        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaTurno);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                REquipPneuTO rEquipPneuTO = (REquipPneuTO) rEquipPneuList.get(position);
                pmmContext.getItemMedPneuTO().setPosItemMedPneu(rEquipPneuTO.getIdPosConfPneu());

                Intent it = new Intent(ListaPosPneuActivity.this, PneuActivity.class);
                startActivity(it);

            }

        });

        buttonRetPosPneu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaPosPneuActivity.this, ListaParadaActivity.class);
                startActivity(it);

            }
        });

    }
}
