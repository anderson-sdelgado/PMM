package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class DadosPlantioActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_plantio);

        pmmContext = (PMMContext) getApplication();

        TextView textViewTituloPlantio = findViewById(R.id.textViewTituloPlantio);
        TextView textViewValorDM = findViewById(R.id.textViewValorDM);
        TextView textViewMetaAP = findViewById(R.id.textViewMetaAP);
        TextView textViewValorAP = findViewById(R.id.textViewValorAP);
        TextView textViewMetaMP = findViewById(R.id.textViewMetaMP);
        TextView textViewValorMP = findViewById(R.id.textViewValorMP);
        Button buttonSair = findViewById(R.id.buttonSair);

        LogProcessoDAO.getInstance().insertLogProcesso("InfPlantioBean infPlantioBean = pmmContext.getInformativoCTR().getInfPlantio();", getLocalClassName());
        InfPlantioBean infPlantioBean = pmmContext.getInformativoCTR().getInfPlantio();

        textViewTituloPlantio.setText("DADOS DE PLANTIO\n" + infPlantioBean.getDthrPlantio());
        textViewValorDM.setText(String.valueOf(infPlantioBean.getPorcDispon()).replace(".", ",") + "%");
        textViewMetaAP.setText(String.valueOf(infPlantioBean.getQtdeProdPlanej()).replace(".", ","));
        textViewValorAP.setText(String.valueOf(infPlantioBean.getQtdeProdReal()).replace(".", ","));
        textViewMetaMP.setText(String.valueOf(infPlantioBean.getMediaProdPlanej()).replace(".", ","));
        textViewValorMP.setText(String.valueOf(infPlantioBean.getMediaProdReal()).replace(".", ","));

        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().setVerInforConfig(3L);", getLocalClassName());
        pmmContext.getConfigCTR().setVerInforConfig(3L);

        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonSair.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(PMMContext.aplic == 1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                            "                    Intent it = new Intent(DadosPlantioActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(DadosPlantioActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 2){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                            "                    Intent it = new Intent(DadosPlantioActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(DadosPlantioActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 3){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                            "                    Intent it = new Intent(DadosPlantioActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    Intent it = new Intent(DadosPlantioActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }
}
