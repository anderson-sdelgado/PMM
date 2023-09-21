package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.PropriedadeBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class MsgPropriedadeActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_propriedade);

        cmmContext = (CMMContext) getApplication();

        Button buttonMsgPropriedadeOk = findViewById(R.id.buttonMsgPropriedadeOk);
        Button buttonMsgPropriedadeCanc = findViewById(R.id.buttonMsgPropriedadeCanc);
        TextView textViewMsgDescrPropriedade = findViewById(R.id.textViewMsgDescrPropriedade);

        LogProcessoDAO.getInstance().insertLogProcesso("PropriedadeBean propriedadeBean = pmmContext.getConfigCTR().getPropriedade();", getLocalClassName());
        PropriedadeBean propriedadeBean = cmmContext.getConfigCTR().getPropriedade();
        textViewMsgDescrPropriedade.setText(propriedadeBean.getCodPropriedade() + " - " + propriedadeBean.getDescrPropriedade());

        buttonMsgPropriedadeOk.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgPropriedadeOk.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                pmmContext.getConfigCTR().setFrentePropriedade();\n" +
                    "                pmmContext.getConfigCTR().clearOSAtiv();\n" +
                    "                pmmContext.getCecCTR().salvarPrecCECAberto();", getLocalClassName());

            cmmContext.getConfigCTR().setFrentePropriedade();
            cmmContext.getConfigCTR().clearOSAtiv();

            cmmContext.getCecCTR().salvarPrecCECAberto();

            LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MsgPropriedadeActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
            Intent it = new Intent(MsgPropriedadeActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();

        });

        buttonMsgPropriedadeCanc.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgPropriedadeCanc.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(MsgPropriedadeActivity.this, PropriedadeActivity.class);", getLocalClassName());
            Intent it = new Intent(MsgPropriedadeActivity.this, PropriedadeActivity.class);
            startActivity(it);
            finish();

        });

    }

    public void onBackPressed()  {
    }
}