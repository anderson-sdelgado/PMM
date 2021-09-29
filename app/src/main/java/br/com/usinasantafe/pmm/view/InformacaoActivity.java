package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class InformacaoActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao);

        pmmContext = (PMMContext) getApplication();

        TextView textViewDescrInfor = findViewById(R.id.textViewDescrInfor);
        Button buttonRetMenuPesq = findViewById(R.id.buttonRetMenuPesq);

        if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 13L){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 13L){\n" +
                    "            CarregCompBean carregCompBean = pmmContext.getCompostoCTR().getOrdCarreg();\n" +
                    "            textViewDescrInfor.setText(\"COD. ORD. CARREG. = \" + carregCompBean.getIdOrdCarreg() + \"\\n\" +\n" +
                    "                    \"PESO ENTRADA = \" + carregCompBean.getPesoEntradaCarreg() + \"\\n\" +\n" +
                    "                    \"PESO SAÍDA = \" + carregCompBean.getPesoSaidaCarreg() + \"\\n\" +\n" +
                    "                    \"PESO LÍQUIDO = \" + carregCompBean.getPesoLiquidoCarreg() + \"\\n\");", getLocalClassName());
            CarregCompBean carregCompBean = pmmContext.getCompostoCTR().getOrdCarreg();
            textViewDescrInfor.setText("COD. ORD. CARREG. = " + carregCompBean.getIdOrdCarreg() + "\n" +
                    "PESO ENTRADA = " + carregCompBean.getPesoEntradaCarreg() + "\n" +
                    "PESO SAÍDA = " + carregCompBean.getPesoSaidaCarreg() + "\n" +
                    "PESO LÍQUIDO = " + carregCompBean.getPesoLiquidoCarreg() + "\n");
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "            CarregCompBean carregCompBean = pmmContext.getCompostoCTR().getOrdCarreg();\n" +
                    "            textViewDescrInfor.setText(\"COD. ORD. CARREG. = \" + carregCompBean.getIdOrdCarreg() + \"\\n\" +\n" +
                    "                    \"PESO ENTRADA = \" + carregCompBean.getPesoEntradaCarreg() + \"\\n\" +\n" +
                    "                    \"PESO SAÍDA = \" + carregCompBean.getPesoSaidaCarreg() + \"\\n\" +\n" +
                    "                    \"PESO LÍQUIDO = \" + carregCompBean.getPesoLiquidoCarreg() + \"\\n\");", getLocalClassName());
            CarregCompBean carregCompBean = pmmContext.getCompostoCTR().getOrdCarreg();
            textViewDescrInfor.setText("COD. ORD. CARREG. = " + carregCompBean.getIdOrdCarreg() + "\n" +
                    "PESO ENTRADA = " + carregCompBean.getPesoEntradaCarreg() + "\n" +
                    "PESO SAÍDA = " + carregCompBean.getPesoSaidaCarreg() + "\n" +
                    "PESO LÍQUIDO = " + carregCompBean.getPesoLiquidoCarreg() + "\n");
        }

        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCompostoCTR().setVerTelaLeira(false);", getLocalClassName());
        pmmContext.getCompostoCTR().setVerTelaLeira(false);

        buttonRetMenuPesq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetMenuPesq.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(InformacaoActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(InformacaoActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();

            }
        });

    }
}