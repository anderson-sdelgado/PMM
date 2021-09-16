package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;

public class MsgSaidaCampoActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_saida_campo);

        pmmContext = (PMMContext) getApplication();

        Button buttonSimSaidaCampo = findViewById(R.id.buttonSimSaidaCampo);
        Button buttonNaoSaidaCampo = findViewById(R.id.buttonNaoSaidaCampo);

        buttonSimSaidaCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connectNetwork) {
                    pmmContext.getConfigCTR().setStatusConConfig(1L);
                }
                else{
                    pmmContext.getConfigCTR().setStatusConConfig(0L);
                }

                pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude());
                pmmContext.getCecCTR().fechaPreCEC();

                Intent it = new Intent(MsgSaidaCampoActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonNaoSaidaCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void onBackPressed()  {
    }

}