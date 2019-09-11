package br.com.usinasantafe.pmm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.PerdaTO;

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

        PerdaTO perdaTO = new PerdaTO();
        List perdaList = perdaTO.all();
        perdaTO = (PerdaTO) perdaList.get(0);
        perdaList.clear();

        textViewTituloPerda.setText("DADOS DE PERDAS\n" + perdaTO.getDthrPerda());
        textViewTituloPerda.setTextColor(Color.GREEN);
        textViewToleteDadoPerda.setText(String.valueOf(perdaTO.getToletePerda()).replace(".", ","));
        textViewLascaDadoPerda.setText(String.valueOf(perdaTO.getLascaPerda()).replace(".", ","));
        textViewTocoDadoPerda.setText(String.valueOf(perdaTO.getTocoPerda()).replace(".", ","));
        textViewPonteiroDadoPerda.setText(String.valueOf(perdaTO.getPonteiroPerda()).replace(".", ","));
        textViewCanaInteiraDadoPerda.setText(String.valueOf(perdaTO.getCanaInteiraPerda()).replace(".", ","));
        textViewPedacoDadoPerda.setText(String.valueOf(perdaTO.getPedacoPerda()).replace(".", ","));
        textViewRepiqueDadoPerda.setText(String.valueOf(perdaTO.getRepiquePerda()).replace(".", ","));
        textViewSoqueiraDadoPerda.setText(String.valueOf(perdaTO.getSoqueiraPerda()).replace(".", ","));
        textViewNroSoqueiraDadoPerda.setText(String.valueOf(perdaTO.getNroSoqueiraPerda()).replace(".", ","));
        textViewTotalDadoPerda.setText(String.valueOf(perdaTO.getTotalPerda()).replace(".", ","));

        ConfigTO configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);
        configList.clear();
        configTO.setVisDadosConfig(2L);
        configTO.update();

        ManipDadosVerif.getInstance().setVerTelaAtualPerda(4);

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
