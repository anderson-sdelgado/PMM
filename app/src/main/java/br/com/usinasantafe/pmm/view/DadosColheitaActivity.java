package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfColheitaBean;

public class DadosColheitaActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_colheita);

        TextView textViewTituloPerda = (TextView) findViewById(R.id.textViewTituloPerda);
        TextView textViewToleteDadoPerda = (TextView) findViewById(R.id.textViewToleteDadoPerda);
        TextView textViewLascaDadoPerda = (TextView) findViewById(R.id.textViewLascaDadoPerda);
        TextView textViewTocoDadoPerda = (TextView) findViewById(R.id.textViewTocoDadoPerda);
        TextView textViewPonteiroDadoPerda = (TextView) findViewById(R.id.textViewPonteiroDadoPerda);
        TextView textViewCanaInteiraDadoPerda = (TextView) findViewById(R.id.textViewCanaInteiraDadoPerda);
        TextView textViewPedacoDadoPerda = (TextView) findViewById(R.id.textViewPedacoDadoPerda);
        TextView textViewRepiqueDadoPerda = (TextView) findViewById(R.id.textViewRepiqueDadoPerda);
        TextView textViewSoqueiraDadoPerda = (TextView) findViewById(R.id.textViewSoqueiraDadoPerda);
        TextView textViewNroSoqueiraDadoPerda = (TextView) findViewById(R.id.textViewNroSoqueiraDadoPerda);
        TextView textViewTotalDadoPerda = (TextView) findViewById(R.id.textViewTotalDadoPerda);
        Button buttonSair = (Button) findViewById(R.id.buttonSair);

        InfColheitaBean infColheitaBean = new InfColheitaBean();
        List infoColheitaList = infColheitaBean.all();
        infColheitaBean = (InfColheitaBean) infoColheitaList.get(0);
        infoColheitaList.clear();

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

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.atualVerInforConfig(3L);

        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DadosColheitaActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }
}
