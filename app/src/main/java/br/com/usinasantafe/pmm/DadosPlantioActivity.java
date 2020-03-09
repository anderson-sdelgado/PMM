package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.pmm.control.ConfigCTR;

public class DadosPlantioActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_plantio);

        TextView textViewTituloPlantio = (TextView) findViewById(R.id.textViewTituloPlantio);
        TextView textViewValorDM = (TextView) findViewById(R.id.textViewValorDM);
        TextView textViewMetaAP = (TextView) findViewById(R.id.textViewMetaAP);
        TextView textViewValorAP = (TextView) findViewById(R.id.textViewValorAP);
        TextView textViewMetaMP = (TextView) findViewById(R.id.textViewMetaMP);
        TextView textViewValorMP = (TextView) findViewById(R.id.textViewValorMP);
        Button buttonSair = (Button) findViewById(R.id.buttonSair);

        InfPlantioBean infPlantioBean = new InfPlantioBean();
        List infPlantioList = infPlantioBean.all();
        infPlantioBean = (InfPlantioBean) infPlantioList.get(0);
        infPlantioList.clear();

        textViewTituloPlantio.setText("DADOS DE PLANTIO\n" + infPlantioBean.getDthrPlantio());
        textViewValorDM.setText(String.valueOf(infPlantioBean.getPorcDispon()).replace(".", ",") + "%");
        textViewMetaAP.setText(String.valueOf(infPlantioBean.getQtdeProdPlanej()).replace(".", ","));
        textViewValorAP.setText(String.valueOf(infPlantioBean.getQtdeProdReal()).replace(".", ","));
        textViewMetaMP.setText(String.valueOf(infPlantioBean.getMediaProdPlanej()).replace(".", ","));
        textViewValorMP.setText(String.valueOf(infPlantioBean.getMediaProdReal()).replace(".", ","));

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.atualVerInforConfig(3L);

        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DadosPlantioActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}
