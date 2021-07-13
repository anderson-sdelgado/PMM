package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;

public class EquipActivity extends ActivityGeneric {

    private TextView textViewCodEquip;
    private TextView textViewDescEquip;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        pmmContext = (PMMContext) getApplication();

        textViewCodEquip = findViewById(R.id.textViewCodEquip);
        textViewDescEquip = findViewById(R.id.textViewDescEquip);
        Button buttonOkEquip = findViewById(R.id.buttonOkEquip);
        Button buttonCancEquip = findViewById(R.id.buttonCancEquip);

        textViewCodEquip.setText(String.valueOf(pmmContext.getConfigCTR().getEquip().getNroEquip()));
        textViewDescEquip.setText(pmmContext.getConfigCTR().getEquip().getDescrClasseEquip());

        buttonOkEquip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){

                    pmmContext.getMotoMecFertCTR().delCarreta();

                    if(pmmContext.getConfigCTR().getEquip().getCodClasseEquip() == 1L){
                        pmmContext.getCecCTR().setLib(pmmContext.getCecCTR().getOSTipoAtiv().getIdLibOS());
                    }

                    Intent it = new Intent(EquipActivity.this, MsgNumCarretaActivity.class);
                    startActivity(it);
                    finish();

                }
                else {

                    pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().setIdEquipBolMMFert(pmmContext.getConfigCTR().getEquip().getIdEquip());
                    Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancEquip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){
                    Intent it = new Intent(EquipActivity.this, ListaAtividadeActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(EquipActivity.this, OperadorActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }

    public void onBackPressed()  {
    }

}
