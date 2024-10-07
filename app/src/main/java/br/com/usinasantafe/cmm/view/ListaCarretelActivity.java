package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ListaCarretelActivity extends ActivityGeneric {

    private ListView carretelListView;
    private CMMContext cmmContext;
    private List<BoletimMMFertBean> boletimMMFertBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carretel);

        cmmContext = (CMMContext) getApplication();

        Button buttonInsCarretel = findViewById(R.id.buttonInsCarretel);
        Button buttonRetCarretel = findViewById(R.id.buttonRetCarretel);

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<>();\n" +
                "        boletimMMFertBeanList = cmmContext.getMotoMecFertCTR().boletimMMFertListSeg();\n" +
                "        for (BoletimMMFertBean boletimMMFertBean : boletimMMFertBeanList) {\n" +
                "            EquipBean equipBean = cmmContext.getConfigCTR().getEquip(boletimMMFertBean.getIdEquipBolMMFert());\n" +
                "            itens.add(\"\" + equipBean.getNroEquip());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        carretelListView = findViewById(R.id.listViewCarretel);\n" +
                "        carretelListView.setAdapter(adapterList);", getLocalClassName());

        ArrayList<String> itens = new ArrayList<>();
        boletimMMFertBeanList = cmmContext.getMotoMecFertCTR().boletimMMFertListSeg();
        for (BoletimMMFertBean boletimMMFertBean : boletimMMFertBeanList) {
            EquipBean equipBean = cmmContext.getConfigCTR().getEquip(boletimMMFertBean.getIdEquipBolMMFert());
            itens.add("" + equipBean.getNroEquip());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        carretelListView = findViewById(R.id.listViewCarretel);
        carretelListView.setAdapter(adapterList);
        carretelListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("carretelListView.setOnItemClickListener((l, v, position, id) -> {\n" +
                    "            cmmContext.getConfigCTR().setPosicaoTela(33L);\n" +
                    "            cmmContext.getConfigCTR().setIdEquipApontConfigId(boletimMMFertBeanList.get(position).getIdEquipBolMMFert());\n" +
                    "            Intent it = new Intent(ListaCarretelActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            cmmContext.getConfigCTR().setPosicaoTela(33L);
            cmmContext.getConfigCTR().setIdEquipApontConfigId(boletimMMFertBeanList.get(position).getIdEquipBolMMFert());
            Intent it = new Intent(ListaCarretelActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
            finish();
            
        });

        buttonInsCarretel.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonInsCarretel.setOnClickListener(v -> {\n" +
                    "            cmmContext.getConfigCTR().setPosicaoTela(32L);\n" +
                    "            Intent it = new Intent(ListaCarretelActivity.this, CarretelActivity.class);", getLocalClassName());
            cmmContext.getConfigCTR().setPosicaoTela(32L);
            Intent it = new Intent(ListaCarretelActivity.this, CarretelActivity.class);
            startActivity(it);
            finish();
        });

        buttonRetCarretel.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetCarretel.setOnClickListener(v -> {\n" +
                    "            cmmContext.getConfigCTR().setIdEquipApontConfig();\n" +
                    "            Intent it = new Intent(ListaCarretelActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            cmmContext.getConfigCTR().setIdEquipApontConfig();
            Intent it = new Intent( ListaCarretelActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
            finish();
        });

    }
}