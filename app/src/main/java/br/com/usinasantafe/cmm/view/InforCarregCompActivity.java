package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class InforCarregCompActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_carreg_comp);

        cmmContext = (CMMContext) getApplication();

        TextView textViewDescrInfor = findViewById(R.id.textViewDescrInfor);
        Button buttonRetMenuPesq = findViewById(R.id.buttonRetMenuPesq);

        LogProcessoDAO.getInstance().insertLogProcesso("CarregCompBean carregCompBean = pmmContext.getCompostoCTR().getOrdCarreg();", getLocalClassName());
        CarregCompBean carregCompBean = cmmContext.getCompostoCTR().getOrdCarreg();

        if(carregCompBean.getIdLeiraCarreg() == 0L){
            LogProcessoDAO.getInstance().insertLogProcesso("if(carregCompBean.getIdLeiraCarreg() == 0L){\n" +
                    "            textViewDescrInfor.setText(\"COD. ORD. CARREG. = \" + carregCompBean.getIdOrdCarreg() + \"\\n\" +\n" +
                    "                    \"PESO ENTRADA = \" + carregCompBean.getPesoEntradaCarreg() + \"\\n\" +\n" +
                    "                    \"PESO SAÍDA = \" + carregCompBean.getPesoSaidaCarreg() + \"\\n\" +\n" +
                    "                    \"PESO LÍQUIDO = \" + carregCompBean.getPesoLiquidoCarreg() + \"\\n\");", getLocalClassName());
            textViewDescrInfor.setText("COD. ORD. CARREG. = " + carregCompBean.getIdOrdCarreg() + "\n" + "PESO ENTRADA = " + carregCompBean.getPesoEntradaCarreg() + "\n" + "PESO SAÍDA = " + carregCompBean.getPesoSaidaCarreg() + "\n" + "PESO LÍQUIDO = " + carregCompBean.getPesoLiquidoCarreg() + "\n");

        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            textViewDescrInfor.setText(\"COD. ORD. CARREG. = \" + ((carregCompBean.getIdOrdCarreg() == 0L) ? \"\" : \"\" + carregCompBean.getIdOrdCarreg()) + \"\\n\" +\n" +
                    "                    \"PESO ENTRADA = \" + carregCompBean.getPesoEntradaCarreg() + \"\\n\" +\n" +
                    "                    \"PESO SAÍDA = \" + carregCompBean.getPesoSaidaCarreg() + \"\\n\" +\n" +
                    "                    \"PESO LÍQUIDO = \" + carregCompBean.getPesoLiquidoCarreg() + \"\\n\" +\n" +
                    "                    \"LEIRA = \" + pmmContext.getCompostoCTR().getLeiraId(carregCompBean.getIdLeiraCarreg()).getCodLeira() + \"\\n\");", getLocalClassName());
            textViewDescrInfor.setText("COD. ORD. CARREG. = " + ((carregCompBean.getIdOrdCarreg() == 0L) ? "" : "" + carregCompBean.getIdOrdCarreg()) + "\n" +
                    "PESO ENTRADA = " + carregCompBean.getPesoEntradaCarreg() + "\n" +
                    "PESO SAÍDA = " + carregCompBean.getPesoSaidaCarreg() + "\n" +
                    "PESO LÍQUIDO = " + carregCompBean.getPesoLiquidoCarreg() + "\n" +
                    "LEIRA = " + cmmContext.getCompostoCTR().getLeiraId(carregCompBean.getIdLeiraCarreg()).getCodLeira() + "\n");
        }

        buttonRetMenuPesq.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetMenuPesq.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(InformacaoActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
            Intent it = new Intent(InforCarregCompActivity.this, MenuPrincPCOMPActivity.class);
            startActivity(it);
            finish();

        });

    }
}