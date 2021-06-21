package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;

public class ListaHistApontActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hist_apont);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetHistorico = findViewById(R.id.buttonRetHistorico);

        ListView listaHistorico = findViewById(R.id.listaHistorico);
        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pmmContext.getMotoMecFertCTR().apontList());
        listaHistorico.setAdapter(adapterListHistorico);

        buttonRetHistorico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pmmContext.getConfigCTR().getConfig().getAplic() == 1L){
                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pmmContext.getConfigCTR().getConfig().getAplic() == 2L){
                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pmmContext.getConfigCTR().getConfig().getAplic() == 3L){
                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
