package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class BackupPreCECActivity extends ActivityGeneric {

    private int contador;
    private List precCECList;
    private TextView textViewBkpViagemCana;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_pre_cec);

        pmmContext = (PMMContext) getApplication();

        textViewBkpViagemCana = findViewById(R.id.textViewBkpViagemCana);
        Button buttonAntBkpViagemCana = findViewById(R.id.buttonAntBkpViagemCana);
        Button buttonProxBkpViagemCana = findViewById(R.id.buttonProxBkpViagemCana);
        Button buttonRetornarBkpViagemCana = findViewById(R.id.buttonRetornarBkpViagemCana);

        precCECList = pmmContext.getCecCTR().preCECTerminadoList();

        LogProcessoDAO.getInstance().insertLogProcesso("contador = precCECList.size() - 1;\n" +
                "        PreCECBean preCECBean = (PreCECBean) precCECList.get(contador);\n" +
                "        textViewBkpViagemCana.setText(exibirPreCEC(preCECBean));", getLocalClassName());

        contador = precCECList.size() - 1;

        PreCECBean preCECBean = (PreCECBean) precCECList.get(contador);
        textViewBkpViagemCana.setText(exibirPreCEC(preCECBean));

        buttonAntBkpViagemCana.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("                if(contador < precCECList.size() - 1){\n" +
                    "                    contador = contador + 1;\n" +
                    "                }\n" +
                    "                PreCECBean preCECBean = (PreCECBean) precCECList.get(contador);\n" +
                    "                textViewBkpViagemCana.setText(exibirPreCEC(preCECBean));", getLocalClassName());

            if(contador < precCECList.size() - 1){
                contador = contador + 1;
            }

            PreCECBean preCECBean1 = (PreCECBean) precCECList.get(contador);
            textViewBkpViagemCana.setText(exibirPreCEC(preCECBean1));

        });

        buttonProxBkpViagemCana.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("                if(contador < precCECList.size() - 1){\n" +
                    "                    contador = contador + 1;\n" +
                    "                }\n" +
                    "                PreCECBean preCECBean = (PreCECBean) precCECList.get(contador);\n" +
                    "                textViewBkpViagemCana.setText(exibirPreCEC(preCECBean));", getLocalClassName());

            if(contador > 0){
                contador = contador - 1;
            }

            PreCECBean preCECBean12 = (PreCECBean) precCECList.get(contador);
            textViewBkpViagemCana.setText(exibirPreCEC(preCECBean12));

        });

        buttonRetornarBkpViagemCana.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetornarBkpViagemCana.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(BackupPreCECActivity.this, MenuCertifActivity.class);", getLocalClassName());
            Intent it = new Intent(BackupPreCECActivity.this, MenuCertifActivity.class);
            startActivity(it);
            finish();

        });

    }

    public String exibirPreCEC(PreCECBean preCECBean){

        LogProcessoDAO.getInstance().insertLogProcesso("public String exibirPreCEC(PreCECBean preCECBean){", getLocalClassName());

        String retorno = "";

        retorno = retorno + "    VIAGEM    \n";
        retorno = retorno + "MOTORISTA = " + preCECBean.getMoto() + "\n";
        if(preCECBean.getCarr1() != 0){
            retorno = retorno + "CARRETA 1 = " + preCECBean.getCarr1() + "\n";
        }
        if(preCECBean.getCarr2() != 0){
            retorno = retorno + "CARRETA 2 = " + preCECBean.getCarr2() + "\n";
        }
        if(preCECBean.getCarr3() != 0){
            retorno = retorno + "CARRETA 3 = " + preCECBean.getCarr3() + "\n";
        }
        retorno = retorno + "SAÍDA DO CAMPO = " + preCECBean.getDataSaidaCampo() + "\n";

        return retorno;

    }

    public void onBackPressed()  {
    }

}