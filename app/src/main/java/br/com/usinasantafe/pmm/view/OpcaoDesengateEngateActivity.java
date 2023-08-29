package br.com.usinasantafe.pmm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class OpcaoDesengateEngateActivity extends AppCompatActivity {

    private ListView opcaoDesengateEngateListView;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcao_desengate_engate);

        pmmContext = (PMMContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<String>();\n" +
                "        itens.add(\"FINALIZAR CERTIFICADO\");\n" +
                "        itens.add(\"DESFAZER CERTIFICADO\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        finalizarListView = findViewById(R.id.listViewFinalizarPreCEC);\n" +
                "        finalizarListView.setAdapter(adapterList);", getLocalClassName());
        ArrayList<String> itens = new ArrayList<String>();

        itens.add("DESENGATAR CARRETA(S)");
        itens.add("ENGATAR CARRETA(S)");

        AdapterList adapterList = new AdapterList(this, itens);
        opcaoDesengateEngateListView = findViewById(R.id.listViewOpcaoDesengateEngate);
        opcaoDesengateEngateListView.setAdapter(adapterList);
        opcaoDesengateEngateListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("finalizarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                    "                String text = textView.getText().toString();", getLocalClassName());
            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            if (text.equals("DESENGATAR CARRETA(S)")) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (text.equals(\"DESENGATAR CARRETA(S)\")) {\n" +
                        "                    pmmContext.getConfigCTR().setPosicaoTela(21L);\n" +
                        "                    Intent it = new Intent(OpcaoDesengateEngateActivity.this, DesengCarretaActivity.class);", getLocalClassName());
                pmmContext.getConfigCTR().setPosicaoTela(21L);
                Intent it = new Intent(OpcaoDesengateEngateActivity.this, DesengCarretaActivity.class);
                startActivity(it);
                finish();
            } else if (text.equals("ENGATAR CARRETA(S)")) {
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"ENGATAR CARRETA(S)\")) {\n" +
                        "                    pmmContext.getConfigCTR().setPosicaoTela(22L);\n" +
                        "                    Intent it = new Intent(OpcaoDesengateEngateActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                pmmContext.getConfigCTR().setPosicaoTela(22L);
                Intent it = new Intent(OpcaoDesengateEngateActivity.this, MsgNumCarretaActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}