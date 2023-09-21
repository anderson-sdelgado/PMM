package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class LogProcessoActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_processo);

        cmmContext = (CMMContext) getApplication();

        Button buttonAvancaLogProcesso = findViewById(R.id.buttonAvancaLogProcesso);
        Button buttonRetLogProcesso = findViewById(R.id.buttonRetLogProcesso);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaHistorico = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pmmContext.getConfigCTR().logProcessoList());\n" +
                "        listaHistorico.setAdapter(adapterListHistorico);", getLocalClassName());
        ListView listViewLogProcesso = findViewById(R.id.listViewLogProcesso);
        AdapterListProcesso adapterListProcesso = new AdapterListProcesso(this, cmmContext.getConfigCTR().logProcessoList());
        listViewLogProcesso.setAdapter(adapterListProcesso);

        buttonAvancaLogProcesso.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "Intent it = new Intent(LogProcessoActivity.this, LogBaseDadoActivity.class);", getLocalClassName());
            Intent it = new Intent(LogProcessoActivity.this, LogBaseDadoActivity.class);
            startActivity(it);
            finish();
        });

        buttonRetLogProcesso.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 12L){
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 12L){\n" +
                        "Intent it = new Intent(LogProcessoActivity.this, TelaInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(LogProcessoActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            }
            else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){
                LogProcessoDAO.getInstance().insertLogProcesso("else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){\n" +
                        "Intent it = new Intent(LogProcessoActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(LogProcessoActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 24L){
                LogProcessoDAO.getInstance().insertLogProcesso("else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){\n" +
                        "Intent it = new Intent(LogProcessoActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(LogProcessoActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else {
                LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                        "Intent it = new Intent(LogProcessoActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(LogProcessoActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}