package br.com.usinasantafe.pmm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;

public class ListaTipoCompActivity extends ActivityGeneric {

    private ListView tipoCompListView;
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
        tipoCompListView = (ListView) findViewById(R.id.listTipoComp);
        tipoCompListView.setAdapter(adapterList);

        tipoCompListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pmmContext.setTipoMovComp(position + 1);

                Intent it = new Intent(ListaTipoCompActivity.this, ListaLeiraActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetTipoComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaTipoCompActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}