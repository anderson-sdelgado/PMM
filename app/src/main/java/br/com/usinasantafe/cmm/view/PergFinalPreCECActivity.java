package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class PergFinalPreCECActivity extends ActivityGeneric {

    private ListView finalizarListView;
    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perg_final_pre_cec);

        cmmContext = (CMMContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<String>();\n" +
                "        itens.add(\"FINALIZAR CERTIFICADO\");\n" +
                "        itens.add(\"DESFAZER CERTIFICADO\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        finalizarListView = findViewById(R.id.listViewFinalizarPreCEC);\n" +
                "        finalizarListView.setAdapter(adapterList);", getLocalClassName());
        ArrayList<String> itens = new ArrayList<>();

        itens.add("FINALIZAR CERTIFICADO");
        itens.add("DESFAZER CERTIFICADO");

        AdapterList adapterList = new AdapterList(this, itens);
        finalizarListView = findViewById(R.id.listViewFinalizarPreCEC);
        finalizarListView.setAdapter(adapterList);
        finalizarListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("finalizarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                    "                String text = textView.getText().toString();", getLocalClassName());

            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            if (text.equals("FINALIZAR CERTIFICADO")) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (text.equals(\"FINALIZAR CERTIFICADO\")) {\n" +
                        "                    Intent it = new Intent(PergFinalPreCECActivity.this, VerifOperadorActivity.class);", getLocalClassName());
                Intent it = new Intent(PergFinalPreCECActivity.this, VerifOperadorActivity.class);
                startActivity(it);
                finish();
            }
            else if (text.equals("DESFAZER CERTIFICADO")) {
                LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"DESFAZER CERTIFICADO\")) {\n" +
                        "                    pmmContext.getCecCTR().clearPreCECAberto();\n" +
                        "                    Intent it = new Intent(PergFinalPreCECActivity.this, MenuCertifActivity.class);", getLocalClassName());
                cmmContext.getCecCTR().clearPreCECAberto();
                Intent it = new Intent(PergFinalPreCECActivity.this, MenuCertifActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}