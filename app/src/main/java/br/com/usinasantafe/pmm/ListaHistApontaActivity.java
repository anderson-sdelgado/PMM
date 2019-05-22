package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;

public class ListaHistApontaActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hist_aponta);

        Button buttonRetHistorico = (Button) findViewById(R.id.buttonRetHistorico);

        BackupApontaTO backupApontaTO = new BackupApontaTO();

        ConfiguracaoTO configTO = new ConfiguracaoTO();
        List configList = configTO.all();
        configTO = (ConfiguracaoTO) configList.get(0);
        configList.clear();

        EquipTO equipTO = new EquipTO();
        List equipTOList = equipTO.get("idEquip", configTO.getEquipConfig());
        equipTO = (EquipTO) equipTOList.get(0);
        equipTOList.clear();

        int tipoEquip;
        if ((equipTO.getTipoEquipFert() == 1) || (equipTO.getTipoEquipFert() == 2)) {
            tipoEquip = 2;
        } else {
            tipoEquip = 1;
        }

        ListView listaHistorico = (ListView) findViewById(R.id.listaHistorico);
        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, backupApontaTO.all(), tipoEquip);
        listaHistorico.setAdapter(adapterListHistorico);

        buttonRetHistorico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaHistApontaActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
