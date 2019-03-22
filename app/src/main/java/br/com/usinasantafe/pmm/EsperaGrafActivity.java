package br.com.usinasantafe.pmm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafProdPlantioTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;

public class EsperaGrafActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private final int interval = 2000; // 1 Second
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_graf);

        pmmContext = (PMMContext) getApplication();

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
        List configList = configuracaoTO.all();
        configuracaoTO = (ConfiguracaoTO) configList.get(0);
        configuracaoTO.setVerVisGrafConfig(0L);
        configuracaoTO.update();

        handler.postDelayed(runnable, interval);

    }

    private Runnable runnable = new Runnable(){
        public void run() {
            if(new GrafProdPlantioTO().hasElements()){
                Intent it = new Intent( EsperaGrafActivity.this, GrafProdActivity.class);
                startActivity(it);
                finish();
            }
            else{

                Intent it = new Intent(EsperaGrafActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();

            }
        }
    };

}
