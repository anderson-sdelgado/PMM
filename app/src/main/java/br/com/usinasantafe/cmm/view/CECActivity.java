package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class CECActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cec);

        cmmContext = (CMMContext) getApplication();

        TextView textViewBoletim = findViewById(R.id.textViewBoletim);
        Button buttonOkBoletim = findViewById(R.id.buttonOkBoletim);

        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCecCTR().delCEC();\n" +
                "        String boletim = visCEC(pmmContext.getCecCTR().getCEC());\n" +
                "        textViewBoletim.setText(boletim);", getLocalClassName());

        cmmContext.getCecCTR().delCEC();

        String boletim = visCEC(cmmContext.getCecCTR().getCEC());
        textViewBoletim.setText(boletim);

        buttonOkBoletim.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkBoletim.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(CECActivity.this, MenuPrincECMActivity.class);", getLocalClassName());

            Intent it = new Intent(CECActivity.this, MenuPrincECMActivity.class);
            startActivity(it);
            finish();

            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(null, null, null, \"OS\", 2, getLocalClassName());", getLocalClassName());
            cmmContext.getMotoMecFertCTR().atualDados("OS", 4, getLocalClassName());

        });

    }

    public String visCEC(CECBean cecBean){

        String retorno = "";

        int analisar = (int) cecBean.getPossuiSorteioCEC().longValue();

        LogProcessoDAO.getInstance().insertLogProcesso("    public String visCEC(CECBean cecBean){\n" +
                "        String retorno = \"\";\n" +
                "        int analisar = (int) cecBean.getPossuiSorteioCEC().longValue();", getLocalClassName());

        if(analisar == 0){

            retorno = retorno + "Liberado sem análise\n";
            retorno = retorno + "Frente: " + cecBean.getCodFrenteCEC() + "\n";
            retorno = retorno + "Peso Liquido Total: " + cecBean.getPesoLiquidoCEC() + "\n";
            retorno = retorno + "Data: " + cecBean.getDthrEntradaCEC() + " \n";

        }
        else if(analisar == 1){

            retorno = retorno + "Sorteado(s) para análise\n";

            if(cecBean.getUnidadeSorteada1CEC() != 0){
                retorno = retorno + "Unidade de Carga: " + cecBean.getUnidadeSorteada1CEC() + "\n" +
                        "Certificado = " + cecBean.getCecSorteado1CEC() + "\n";
            }

            if(cecBean.getUnidadeSorteada2CEC() != 0){
                retorno = retorno + "Unidade de Carga: " + cecBean.getUnidadeSorteada2CEC() + "\n" +
                        "Certificado = " + cecBean.getCecSorteado2CEC() + "\n";
            }

            if(cecBean.getUnidadeSorteada3CEC() != 0){
                retorno = retorno + "Unidade de Carga: " + cecBean.getUnidadeSorteada3CEC() + "\n" +
                        "Certificado = " + cecBean.getCecSorteado3CEC() + "\n";
            }

            retorno = retorno + "Frente: " + cecBean.getCodFrenteCEC() + "\n";
            retorno = retorno + "Peso Liquido Total: " + cecBean.getPesoLiquidoCEC() + "\n";
            retorno = retorno + "Data: " + cecBean.getDthrEntradaCEC() + " \n";

        }

        return retorno;

    }

    public void onBackPressed()  {
    }

}