package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.PropriedadeBean;

public class MsgPropriedadeActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_propriedade);

        pmmContext = (PMMContext) getApplication();

        Button buttonMsgPropriedadeOk = findViewById(R.id.buttonMsgPropriedadeOk);
        Button buttonMsgPropriedadeCanc = findViewById(R.id.buttonMsgPropriedadeCanc);
        TextView textViewMsgDescrPropriedade = findViewById(R.id.textViewMsgDescrPropriedade);

        PropriedadeBean propriedadeBean = pmmContext.getConfigCTR().getPropriedade();

        textViewMsgDescrPropriedade.setText(propriedadeBean.getIdPropriedade() + " - " + propriedadeBean.getDescrPropriedade());

        buttonMsgPropriedadeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pmmContext.getConfigCTR().setFrentePropriedade();
                pmmContext.getConfigCTR().clearOSAtiv();

                pmmContext.getCecCTR().salvarPrecCECAberto();
                pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude());

                Intent it = new Intent(MsgPropriedadeActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonMsgPropriedadeCanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MsgPropriedadeActivity.this, PropriedadeActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }
}