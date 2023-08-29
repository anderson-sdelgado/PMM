package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pmm.BuildConfig;
import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class ListaHistApontActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hist_apont);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetHistorico = findViewById(R.id.buttonRetHistorico);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaHistorico = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pmmContext.getMotoMecFertCTR().apontList());\n" +
                "        listaHistorico.setAdapter(adapterListHistorico);", getLocalClassName());
        ListView listaHistorico = findViewById(R.id.listaHistorico);
        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pmmContext.getMotoMecFertCTR().apontList());
        listaHistorico.setAdapter(adapterListHistorico);

        buttonRetHistorico.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetHistorico.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(BuildConfig.FLAVOR.equals("pmm")){
                LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                        "                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if(BuildConfig.FLAVOR.equals("ecm")){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                        "                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaHistApontActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else if(BuildConfig.FLAVOR.equals("pcomp")){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                        "                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
