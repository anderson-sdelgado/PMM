package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;

public class ListaOSRendActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os_rend);

        Button buttonRetOSRend = findViewById(R.id.buttonRetOSRend);

        pmmContext = (PMMContext) getApplication();

        ListView listaOSRend = findViewById(R.id.listaOSRend);
        AdapterListRend adapterListRend = new AdapterListRend(this, pmmContext.getMotoMecFertCTR().rendList());
        listaOSRend.setAdapter(adapterListRend);

        listaOSRend.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pmmContext.getMotoMecFertCTR().setPosRend(position);
                Intent it = new Intent(ListaOSRendActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetOSRend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaOSRendActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
