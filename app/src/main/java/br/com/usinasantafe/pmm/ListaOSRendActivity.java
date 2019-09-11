package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.variaveis.RendMMTO;

public class ListaOSRendActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os_rend);

        Button buttonRetOSRend = (Button) findViewById(R.id.buttonRetOSRend);

        pmmContext = (PMMContext) getApplication();

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List listBoletim = boletimMMTO.get("statusBolMM", 1L);
        boletimMMTO = (BoletimMMTO) listBoletim.get(0);
        listBoletim.clear();

        RendMMTO rendMMTO = new RendMMTO();
        ListView listaOSRend = (ListView) findViewById(R.id.listaOSRend);
        AdapterListRend adapterListRend = new AdapterListRend(this, rendMMTO.getAndOrderBy("idBolRendMM", boletimMMTO.getIdBolMM(), "idRendMM", true));
        listaOSRend.setAdapter(adapterListRend);

        listaOSRend.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pmmContext.setPosRend(position);
                Intent it = new Intent(ListaOSRendActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetOSRend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaOSRendActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
