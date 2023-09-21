package br.com.usinasantafe.cmm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class OpcaoDesengateEngateActivity extends AppCompatActivity {

    private ListView opcaoDesengateEngateListView;
    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcao_desengate_engate);

        cmmContext = (CMMContext) getApplication();

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
                cmmContext.getConfigCTR().setPosicaoTela(21L);
                Intent it = new Intent(OpcaoDesengateEngateActivity.this, DesengCarretaActivity.class);
                startActivity(it);
                finish();
            } else if (text.equals("ENGATAR CARRETA(S)")) {
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"ENGATAR CARRETA(S)\")) {\n" +
                        "                    pmmContext.getConfigCTR().setPosicaoTela(22L);\n" +
                        "                    Intent it = new Intent(OpcaoDesengateEngateActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                cmmContext.getConfigCTR().setPosicaoTela(22L);
                Intent it = new Intent(OpcaoDesengateEngateActivity.this, MsgNumCarretaActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}