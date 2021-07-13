package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.variaveis.CarregCompBean;

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
            CarregCompBean carregCompBean = pmmContext.getCompostoCTR().getOrdCarreg();
            textViewDescrInfor.setText("COD. ORD. CARREG. = " + carregCompBean.getIdOrdCarreg() + "\n" +
                    "PESO ENTRADA = " + carregCompBean.getPesoEntradaCarreg() + "\n" +
                    "PESO SAÍDA = " + carregCompBean.getPesoSaidaCarreg() + "\n" +
                    "PESO LÍQUIDO = " + carregCompBean.getPesoLiquidoCarreg() + "\n");
        }
        else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 14L){
            CarregCompBean carregCompBean = pmmContext.getCompostoCTR().getOrdCarreg();
            textViewDescrInfor.setText("COD. ORD. CARREG. = " + carregCompBean.getIdOrdCarreg() + "\n" +
                    "PESO ENTRADA = " + carregCompBean.getPesoEntradaCarreg() + "\n" +
                    "PESO SAÍDA = " + carregCompBean.getPesoSaidaCarreg() + "\n" +
                    "PESO LÍQUIDO = " + carregCompBean.getPesoLiquidoCarreg() + "\n");
        }

        pmmContext.setVerTelaLeira(false);

        buttonRetMenuPesq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(InformacaoActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();

            }
        });

    }
}