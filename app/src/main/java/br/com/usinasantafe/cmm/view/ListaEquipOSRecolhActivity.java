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

public class ListaEquipOSRecolhActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private List<RecolhFertBean> recolhFertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equip_os_recolh);

        Button buttonRetRecMang = findViewById(R.id.buttonRetRecMang);
        Button buttonFinalBoletim = findViewById(R.id.buttonFinalBoletim);

        cmmContext = (CMMContext) getApplication();

        recolhFertList = cmmContext.getMotoMecFertCTR().recolhFinalList();
        ListView listaRecMang = findViewById(R.id.listaRecMang);
        AdapterListEquipOSRecolh adapterListOSRecolh = new AdapterListEquipOSRecolh(this, recolhFertList);
        listaRecMang.setAdapter(adapterListOSRecolh);

        listaRecMang.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("listaRecMang.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                cmmContext.getMotoMecFertCTR().setIdRecolh(recolhFertList.get(position).getIdRecolhFert());\n" +
                    "                Intent it = new Intent(ListaOSRecolhActivity.this, RecolhimentoActivity.class);", getLocalClassName());
            cmmContext.getMotoMecFertCTR().setIdRecolh(recolhFertList.get(position).getIdRecolhFert());
            Intent it = new Intent(ListaEquipOSRecolhActivity.this, RecolhimentoActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetRecMang.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetRecMang.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(ListaEquipOSRecolhActivity.this, HorimetroActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaEquipOSRecolhActivity.this, HorimetroActivity.class);
            startActivity(it);
            finish();
        });

        buttonFinalBoletim.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonFinalBoletim.setOnClickListener(v -> {\n" +
                    "            cmmContext.getMotoMecFertCTR().salvarBolMMFertFechado(cmmContext, getLocalClassName());\n" +
                    "            Intent it = new Intent(ListaEquipOSRecolhActivity.this, TelaInicialActivity.class);", getLocalClassName());
            cmmContext.getMotoMecFertCTR().salvarBolMMFertFechado(cmmContext, getLocalClassName());
            Intent it = new Intent(ListaEquipOSRecolhActivity.this, TelaInicialActivity.class);
            startActivity(it);
            finish();
        });

    }
}