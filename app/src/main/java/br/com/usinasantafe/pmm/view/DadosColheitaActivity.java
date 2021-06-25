package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfColheitaBean;

public class DadosColheitaActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_colheita);

        pmmContext = (PMMContext) getApplication();

        TextView textViewTituloPerda = findViewById(R.id.textViewTituloPerda);
        TextView textViewToleteDadoPerda = findViewById(R.id.textViewToleteDadoPerda);
        TextView textViewLascaDadoPerda = findViewById(R.id.textViewLascaDadoPerda);
        TextView textViewTocoDadoPerda = findViewById(R.id.textViewTocoDadoPerda);
        TextView textViewPonteiroDadoPerda = findViewById(R.id.textViewPonteiroDadoPerda);
        TextView textViewCanaInteiraDadoPerda = findViewById(R.id.textViewCanaInteiraDadoPerda);
        TextView textViewPedacoDadoPerda = findViewById(R.id.textViewPedacoDadoPerda);
        TextView textViewRepiqueDadoPerda = findViewById(R.id.textViewRepiqueDadoPerda);
        TextView textViewSoqueiraDadoPerda = findViewById(R.id.textViewSoqueiraDadoPerda);
        TextView textViewNroSoqueiraDadoPerda = findViewById(R.id.textViewNroSoqueiraDadoPerda);
        TextView textViewTotalDadoPerda = findViewById(R.id.textViewTotalDadoPerda);
        Button buttonSair = findViewById(R.id.buttonSair);

        InfColheitaBean infColheitaBean = pmmContext.getInformativoCTR().getInfColheita();

        textViewTituloPerda.setText("DADOS DE PERDAS\n" + infColheitaBean.getDthrPerda());
        textViewTituloPerda.setTextColor(Color.GREEN);
        textViewToleteDadoPerda.setText(String.valueOf(infColheitaBean.getToletePerda()).replace(".", ","));
        textViewLascaDadoPerda.setText(String.valueOf(infColheitaBean.getLascaPerda()).replace(".", ","));
        textViewTocoDadoPerda.setText(String.valueOf(infColheitaBean.getTocoPerda()).replace(".", ","));
        textViewPonteiroDadoPerda.setText(String.valueOf(infColheitaBean.getPonteiroPerda()).replace(".", ","));
        textViewCanaInteiraDadoPerda.setText(String.valueOf(infColheitaBean.getCanaInteiraPerda()).replace(".", ","));
        textViewPedacoDadoPerda.setText(String.valueOf(infColheitaBean.getPedacoPerda()).replace(".", ","));
        textViewRepiqueDadoPerda.setText(String.valueOf(infColheitaBean.getRepiquePerda()).replace(".", ","));
        textViewSoqueiraDadoPerda.setText(String.valueOf(infColheitaBean.getSoqueiraPerda()).replace(".", ","));
        textViewNroSoqueiraDadoPerda.setText(String.valueOf(infColheitaBean.getNroSoqueiraPerda()).replace(".", ","));
        textViewTotalDadoPerda.setText(String.valueOf(infColheitaBean.getTotalPerda()).replace(".", ","));

        pmmContext.getConfigCTR().atualVerInforConfig(3L);

        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PMMContext.aplic == 1){
                    Intent it = new Intent(DadosColheitaActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 2){
                    Intent it = new Intent(DadosColheitaActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 3){
                    Intent it = new Intent(DadosColheitaActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed() {
    }
}
