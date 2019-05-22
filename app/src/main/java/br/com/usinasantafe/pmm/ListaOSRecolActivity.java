package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RecolhimentoTO;

public class ListaOSRecolActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os_recolh);

        Button buttonRetRecMang = (Button) findViewById(R.id.buttonRetRecMang);

        pmmContext = (PMMContext) getApplication();

        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimList = boletimFertTO.get("statusBolFert", 1L);
        boletimFertTO = (BoletimFertTO) boletimList.get(0);
        boletimList.clear();

        RecolhimentoTO recolhimentoTO = new RecolhimentoTO();

        ListView listaRecMang = (ListView) findViewById(R.id.listaRecMang);
        AdapterListRecol adapterListRecol = new AdapterListRecol(this, recolhimentoTO.getAndOrderBy("idBolRecol", boletimFertTO.getIdBolFert(), "idRecol", true));
        listaRecMang.setAdapter(adapterListRecol);

        listaRecMang.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                pmmContext.setContRecolhimento(position);
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
