package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.bean.variaveis.InfColheitaTO;

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

        InfColheitaTO infColheitaTO = new InfColheitaTO();
        List infoColheitaList = infColheitaTO.all();
        infColheitaTO = (InfColheitaTO) infoColheitaList.get(0);
        infoColheitaList.clear();

        textViewTituloPerda.setText("DADOS DE PERDAS\n" + infColheitaTO.getDthrPerda());
        textViewTituloPerda.setTextColor(Color.GREEN);
        textViewToleteDadoPerda.setText(String.valueOf(infColheitaTO.getToletePerda()).replace(".", ","));
        textViewLascaDadoPerda.setText(String.valueOf(infColheitaTO.getLascaPerda()).replace(".", ","));
        textViewTocoDadoPerda.setText(String.valueOf(infColheitaTO.getTocoPerda()).replace(".", ","));
        textViewPonteiroDadoPerda.setText(String.valueOf(infColheitaTO.getPonteiroPerda()).replace(".", ","));
        textViewCanaInteiraDadoPerda.setText(String.valueOf(infColheitaTO.getCanaInteiraPerda()).replace(".", ","));
        textViewPedacoDadoPerda.setText(String.valueOf(infColheitaTO.getPedacoPerda()).replace(".", ","));
        textViewRepiqueDadoPerda.setText(String.valueOf(infColheitaTO.getRepiquePerda()).replace(".", ","));
        textViewSoqueiraDadoPerda.setText(String.valueOf(infColheitaTO.getSoqueiraPerda()).replace(".", ","));
        textViewNroSoqueiraDadoPerda.setText(String.valueOf(infColheitaTO.getNroSoqueiraPerda()).replace(".", ","));
        textViewTotalDadoPerda.setText(String.valueOf(infColheitaTO.getTotalPerda()).replace(".", ","));

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
