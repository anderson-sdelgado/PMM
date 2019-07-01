package br.com.usinasantafe.pmm;

import android.os.Handler;
import android.os.Bundle;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;

public class EsperaDadosOperActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private final int interval = 2000; // 1 Second
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_dados_oper);

        pmmContext = (PMMContext) getApplication();

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimList = boletimMMTO.get("statusBoletim", 1L);
        boletimMMTO = (BoletimMMTO) boletimList.get(0);

        ManipDadosVerif.getInstance().verDados(String.valueOf(boletimMMTO.getCodMotoBoletim()), "Perda", EsperaDadosOperActivity.this, DadosColheitaActivity.class);

//        ConfigTO configuracaoTO = new ConfigTO();
//        List configList = configuracaoTO.all();
//        configuracaoTO = (ConfigTO) configList.get(0);
//        configuracaoTO.setVerVisGrafConfig(0L);
//        configuracaoTO.update();
//
//        handler.postDelayed(runnable, interval);

    }

//    private Runnable runnable = new Runnable(){
//        public void run() {
//            if(new GrafProdPlantioTO().hasElements()){
//                Intent it = new Intent( EsperaDadosOperActivity.this, GraficoPlantioActivity.class);
//                startActivity(it);
//                finish();
//            }
//            else{
//
//                Intent it = new Intent(EsperaDadosOperActivity.this, MenuPrincNormalActivity.class);
//                startActivity(it);
//                finish();
//
//            }
//        }
//    };

}
