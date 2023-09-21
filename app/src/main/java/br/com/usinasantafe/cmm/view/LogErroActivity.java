package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class LogErroActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_erro);

        cmmContext = (CMMContext) getApplication();

        Button buttonRetLogErro = findViewById(R.id.buttonRetLogErro);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaHistorico = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pmmContext.getConfigCTR().logProcessoList());\n" +
                "        listaHistorico.setAdapter(adapterListHistorico);", getLocalClassName());
        ListView listViewLogErro = findViewById(R.id.listViewLogErro);
        AdapterListErro adapterListErro = new AdapterListErro(this, cmmContext.getConfigCTR().logErroList());
        listViewLogErro.setAdapter(adapterListErro);

        buttonRetLogErro.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "Intent it = new Intent(LogErroActivity.this, LogBaseDadoActivity.class);", getLocalClassName());
            Intent it = new Intent(LogErroActivity.this, LogBaseDadoActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}