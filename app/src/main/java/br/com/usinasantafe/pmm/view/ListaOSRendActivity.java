package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class ListaOSRendActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os_rend);

        Button buttonRetOSRend = findViewById(R.id.buttonRetOSRend);

        pmmContext = (PMMContext) getApplication();

        LogProcessoDAO.getInstance().insert("ListView listaOSRend = findViewById(R.id.listaOSRend);\n" +
                "        AdapterListRend adapterListRend = new AdapterListRend(this, pmmContext.getMotoMecFertCTR().rendList());\n" +
                "        listaOSRend.setAdapter(adapterListRend);", getLocalClassName());

        ListView listaOSRend = findViewById(R.id.listaOSRend);
        AdapterListRend adapterListRend = new AdapterListRend(this, pmmContext.getMotoMecFertCTR().rendList());
        listaOSRend.setAdapter(adapterListRend);

        listaOSRend.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insert("listaOSRend.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                pmmContext.getMotoMecFertCTR().setPosRend(position);\n" +
                        "                Intent it = new Intent(ListaOSRendActivity.this, RendimentoActivity.class);", getLocalClassName());
                pmmContext.getMotoMecFertCTR().setPosRend(position);
                Intent it = new Intent(ListaOSRendActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetOSRend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insert("buttonRetOSRend.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaOSRendActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaOSRendActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
