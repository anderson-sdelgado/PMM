package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;

public class ListaTipoMovLeiraActivity extends ActivityGeneric {

    private ListView tipoFuncaoLeiraListView;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipo_comp);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetTipoComp = (Button) findViewById(R.id.buttonRetTipoComp);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("INICIAR DESCARGA NA(S) LEIRA(S)");
        itens.add("FINALIZAR DESCARGA NA(S) LEIRA(S)");
        itens.add("INICIAR CARREGAMENTO NA(S) LEIRA(S)");
        itens.add("FINALIZAR CARREGAMENTO NA(S) LEIRA(S)");

        AdapterList adapterList = new AdapterList(this, itens);
        tipoFuncaoLeiraListView = (ListView) findViewById(R.id.listTipoComp);
        tipoFuncaoLeiraListView.setAdapter(adapterList);

        tipoFuncaoLeiraListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pmmContext.getCompostoCTR().setTipoMovLeira((long) (position + 1));

                Intent it = new Intent(ListaTipoMovLeiraActivity.this, ListaLeiraActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetTipoComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaTipoMovLeiraActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}