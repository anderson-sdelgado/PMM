package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.PropriedadeBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class MsgPropriedadeActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_propriedade);

        pmmContext = (PMMContext) getApplication();

        Button buttonMsgPropriedadeOk = findViewById(R.id.buttonMsgPropriedadeOk);
        Button buttonMsgPropriedadeCanc = findViewById(R.id.buttonMsgPropriedadeCanc);
        TextView textViewMsgDescrPropriedade = findViewById(R.id.textViewMsgDescrPropriedade);

        LogProcessoDAO.getInstance().insertLogProcesso("PropriedadeBean propriedadeBean = pmmContext.getConfigCTR().getPropriedade();", getLocalClassName());
        PropriedadeBean propriedadeBean = pmmContext.getConfigCTR().getIdPropriedade();

        textViewMsgDescrPropriedade.setText(propriedadeBean.getIdPropriedade() + " - " + propriedadeBean.getDescrPropriedade());

        buttonMsgPropriedadeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgPropriedadeOk.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pmmContext.getConfigCTR().setFrentePropriedade();\n" +
                        "                pmmContext.getConfigCTR().clearOSAtiv();\n" +
                        "                pmmContext.getCecCTR().salvarPrecCECAberto();", getLocalClassName());

                pmmContext.getConfigCTR().setFrentePropriedade();
                pmmContext.getConfigCTR().clearOSAtiv();

                pmmContext.getCecCTR().salvarPrecCECAberto();

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude(), getLocalClassName());

                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MsgPropriedadeActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgPropriedadeActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonMsgPropriedadeCanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgPropriedadeCanc.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MsgPropriedadeActivity.this, PropriedadeActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgPropriedadeActivity.this, PropriedadeActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }
}