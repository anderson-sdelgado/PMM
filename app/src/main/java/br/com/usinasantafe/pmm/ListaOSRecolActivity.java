package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;

public class ListaOSRecolActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os_recolh);

        Button buttonRetRecMang = (Button) findViewById(R.id.buttonRetRecMang);

        pmmContext = (PMMContext) getApplication();

        RecolhFertBean recolhFertBean = new RecolhFertBean();

        ListView listaRecMang = (ListView) findViewById(R.id.listaRecMang);
        AdapterListRecolh adapterListRecolh = new AdapterListRecolh(this, recolhFertBean.getAndOrderBy("idBolRecolhFert",  pmmContext.getBoletimCTR().getIdBol(), "idRecolhFert", true));
        listaRecMang.setAdapter(adapterListRecolh);

        listaRecMang.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pmmContext.setContRecolh(position);
                Intent it = new Intent(ListaOSRecolActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetRecMang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaOSRecolActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}
