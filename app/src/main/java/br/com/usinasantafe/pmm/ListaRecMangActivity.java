package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pmm.to.tb.variaveis.RecolMangTO;

public class ListaRecMangActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_rec_mang);

        Button buttonRetRecMang = (Button) findViewById(R.id.buttonRetRecMang);

        pmmContext = (PMMContext) getApplication();

        RecolMangTO recolMangTO = new RecolMangTO();

        ListView listaRecMang = (ListView) findViewById(R.id.listaRecMang);
        AdapterListMangRec adapterListMangRec = new AdapterListMangRec(this, recolMangTO.orderBy("idRendMangRecol", true));
        listaRecMang.setAdapter(adapterListMangRec);

        listaRecMang.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                pmmContext.setPosRendimento(position);
                Intent it = new Intent(ListaRecMangActivity.this, RecolMangFertActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetRecMang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaRecMangActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}
