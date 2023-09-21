package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        editTextSenha = findViewById(R.id.editTextSenha);
        Button buttonOkSenha = findViewById(R.id.buttonOkSenha);
        Button buttonCancSenha = findViewById(R.id.buttonCancSenha);

        cmmContext = (CMMContext) getApplication();

        buttonOkSenha.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("btOkSenha.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @SuppressWarnings(\"unchecked\")\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!cmmContext.getConfigCTR().hasElemConfig()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (!pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                        "Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 11L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 11L){", getLocalClassName());
                    if (cmmContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {\n" +
                                "Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                        startActivity(it);
                        finish();

                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if (editTextSenha.getText().toString().equals("fgbny946")) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (editTextSenha.getText().toString().equals(\"fgbny946\")) {\n" +
                                "Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }


        });

        buttonCancSenha.setOnClickListener(v -> {
            if(!cmmContext.getConfigCTR().hasElemConfig()){
                LogProcessoDAO.getInstance().insertLogProcesso("if(!pmmContext.getConfigCTR().getConfig().hasElements()){\n" +
                        "                    Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 11L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("btCancSenha.setOnClickListener(new View.OnClickListener() {\n" +
                            "            @Override\n" +
                            "            public void onClick(View v) {\n" +
                            "Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 12L){
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 12L){\n" +
                                "Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);
                        startActivity(it);
                        finish();
                    } else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){
                        LogProcessoDAO.getInstance().insertLogProcesso("} else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){\n" +
                                "Intent it = new Intent(SenhaActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, MenuPrincPMMActivity.class);
                        startActivity(it);
                        finish();
                    } else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 24L){
                        LogProcessoDAO.getInstance().insertLogProcesso("} else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){\n" +
                                "Intent it = new Intent(SenhaActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, MenuPrincECMActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "Intent it = new Intent(SenhaActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, MenuPrincPCOMPActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
