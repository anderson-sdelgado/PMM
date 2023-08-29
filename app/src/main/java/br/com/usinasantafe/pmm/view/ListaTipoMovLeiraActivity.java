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
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

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

        LogProcessoDAO.getInstance().insertLogProcesso("AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        tipoFuncaoLeiraListView = (ListView) findViewById(R.id.listTipoComp);\n" +
                "        tipoFuncaoLeiraListView.setAdapter(adapterList);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, itens);
        tipoFuncaoLeiraListView = findViewById(R.id.listTipoComp);
        tipoFuncaoLeiraListView.setAdapter(adapterList);

        tipoFuncaoLeiraListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("tipoFuncaoLeiraListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                pmmContext.getCompostoCTR().setTipoMovLeira((long) (position + 1));\n" +
                    "                Intent it = new Intent(ListaTipoMovLeiraActivity.this, ListaLeiraActivity.class);", getLocalClassName());
            pmmContext.getCompostoCTR().setTipoMovLeira((long) (position + 1));
            Intent it = new Intent(ListaTipoMovLeiraActivity.this, ListaLeiraActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetTipoComp.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetTipoComp.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaTipoMovLeiraActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaTipoMovLeiraActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}