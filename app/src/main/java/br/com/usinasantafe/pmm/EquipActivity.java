package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;

public class EquipActivity extends ActivityGeneric {

    private TextView textViewCodEquip;
    private TextView textViewDescEquip;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        pmmContext = (PMMContext) getApplication();

        textViewCodEquip = (TextView) findViewById(R.id.textViewCodEquip);
        textViewDescEquip = (TextView) findViewById(R.id.textViewDescEquip);
        Button buttonOkEquip = (Button) findViewById(R.id.buttonOkEquip);
        Button buttonCancEquip = (Button) findViewById(R.id.buttonCancEquip);

        ConfiguracaoTO configTO = new ConfiguracaoTO();
        List listConfigTO = configTO.all();
        configTO = (ConfiguracaoTO) listConfigTO.get(0);
        listConfigTO.clear();

        EquipTO equipTO = new EquipTO();
        List listEquipTO = equipTO.get("idEquip", configTO.getEquipConfig());
        equipTO = (EquipTO) listEquipTO.get(0);
        listEquipTO.clear();

        textViewCodEquip.setText(String.valueOf(equipTO.getCodEquip()));
        textViewDescEquip.setText(String.valueOf(equipTO.getDescrClasseEquip()));

        pmmContext.getBoletimMMTO().setCodEquipBoletim(equipTO.getIdEquip());

        buttonOkEquip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonCancEquip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(EquipActivity.this, OperadorActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
