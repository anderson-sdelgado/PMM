package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ListaOSRendActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os_rend);

        Button buttonRetOSRend = findViewById(R.id.buttonRetOSRend);

        cmmContext = (CMMContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaOSRend = findViewById(R.id.listaOSRend);\n" +
                "        AdapterListRend adapterListRend = new AdapterListRend(this, pmmContext.getMotoMecFertCTR().rendList());\n" +
                "        listaOSRend.setAdapter(adapterListRend);", getLocalClassName());

        ListView listaOSRend = findViewById(R.id.listaOSRend);
        AdapterListRend adapterListRend = new AdapterListRend(this, cmmContext.getMotoMecFertCTR().rendList());
        listaOSRend.setAdapter(adapterListRend);

        listaOSRend.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("listaOSRend.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                pmmContext.getMotoMecFertCTR().setPosRend(position);\n" +
                    "                Intent it = new Intent(ListaOSRendActivity.this, RendimentoActivity.class);", getLocalClassName());
            cmmContext.getMotoMecFertCTR().setPosRend(position);
            Intent it = new Intent(ListaOSRendActivity.this, RendimentoActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetOSRend.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetOSRend.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaOSRendActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaOSRendActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}
