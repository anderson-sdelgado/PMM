package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ListaOSRecolhActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private List<RecolhFertBean> recolhFertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os_recolh);

        Button buttonRetRecMang = findViewById(R.id.buttonRetRecMang);

        cmmContext = (CMMContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("recolhFertList = cmmContext.getMotoMecFertCTR().recolhList();\n" +
                "        ListView listaRecMang = findViewById(R.id.listaRecMang);\n" +
                "        AdapterListOSRecolh adapterListOSRecolh = new AdapterListOSRecolh(this, recolhFertList);\n" +
                "        listaRecMang.setAdapter(adapterListOSRecolh);", getLocalClassName());
        recolhFertList = cmmContext.getMotoMecFertCTR().recolhList();
        ListView listaRecMang = findViewById(R.id.listaRecMang);
        AdapterListOSRecolh adapterListOSRecolh = new AdapterListOSRecolh(this, recolhFertList);
        listaRecMang.setAdapter(adapterListOSRecolh);

        listaRecMang.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("listaRecMang.setOnItemClickListener((l, v, position, id) -> {\n" +
                    "            cmmContext.getMotoMecFertCTR().setIdRecolh(recolhFertList.get(position).getIdRecolhFert());\n" +
                    "            Intent it = new Intent(ListaOSRecolhActivity.this, RecolhimentoActivity.class);", getLocalClassName());
            cmmContext.getMotoMecFertCTR().setIdRecolh(recolhFertList.get(position).getIdRecolhFert());
            Intent it = new Intent(ListaOSRecolhActivity.this, RecolhimentoActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetRecMang.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetRecMang.setOnClickListener(v -> {\n" +
                    "                Intent it = new Intent(ListaOSRecolhActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaOSRecolhActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
            finish();

        });

    }

    public void onBackPressed() {
    }

}
