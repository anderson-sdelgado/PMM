package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ListaOSRecolhActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os_recolh);

        Button buttonRetRecMang = findViewById(R.id.buttonRetRecMang);

        cmmContext = (CMMContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaRecMang = findViewById(R.id.listaRecMang);\n" +
                "        AdapterListRecolh adapterListRecolh = new AdapterListRecolh(this, pmmContext.getMotoMecFertCTR().recolhList());\n" +
                "        listaRecMang.setAdapter(adapterListRecolh);", getLocalClassName());

        ListView listaRecMang = findViewById(R.id.listaRecMang);
        AdapterListRecolh adapterListRecolh = new AdapterListRecolh(this, cmmContext.getMotoMecFertCTR().recolhList());
        listaRecMang.setAdapter(adapterListRecolh);

        listaRecMang.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("listaRecMang.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                pmmContext.getMotoMecFertCTR().setContRecolh(position);\n" +
                    "                Intent it = new Intent(ListaOSRecolhActivity.this, RecolhimentoActivity.class);", getLocalClassName());
            cmmContext.getMotoMecFertCTR().setContRecolh(position);
            Intent it = new Intent(ListaOSRecolhActivity.this, RecolhimentoActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetRecMang.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetRecMang.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaOSRecolhActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaOSRecolhActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}
