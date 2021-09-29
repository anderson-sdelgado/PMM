package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

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

        LogProcessoDAO.getInstance().insertLogProcesso("InfColheitaBean infColheitaBean = pmmContext.getInformativoCTR().getInfColheita();", getLocalClassName());
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
                            "                    Intent it = new Intent(DadosColheitaActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(DadosColheitaActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 2){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                            "                    Intent it = new Intent(DadosColheitaActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(DadosColheitaActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(PMMContext.aplic == 3){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                            "                    Intent it = new Intent(DadosColheitaActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
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
