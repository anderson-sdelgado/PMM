package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;

public class EquipActivity extends ActivityGeneric {

    private TextView textViewCodEquip;
    private TextView textViewDescEquip;
    private PMMContext pmmContext;
    private ConfigCTR configCTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        pmmContext = (PMMContext) getApplication();

        textViewCodEquip = (TextView) findViewById(R.id.textViewCodEquip);
        textViewDescEquip = (TextView) findViewById(R.id.textViewDescEquip);
        Button buttonOkEquip = (Button) findViewById(R.id.buttonOkEquip);
        Button buttonCancEquip = (Button) findViewById(R.id.buttonCancEquip);

        configCTR = new ConfigCTR();
        EquipBean equipBean = configCTR.getEquip();

        textViewCodEquip.setText(String.valueOf(equipBean.getNroEquip()));
        textViewDescEquip.setText(String.valueOf(equipBean.getDescrClasseEquip()));

        buttonOkEquip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pmmContext.getBoletimCTR().setEquipBol();
                Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonCancEquip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(EquipActivity.this, OperadorActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
