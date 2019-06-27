package br.com.usinasantafe.pmm;

import android.os.Handler;
import android.os.Bundle;

public class EsperaGrafActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private final int interval = 2000; // 1 Second
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_graf);

        pmmContext = (PMMContext) getApplication();

//        ManipDadosVerif.getInstance().

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
//                Intent it = new Intent( EsperaGrafActivity.this, GrafProdActivity.class);
//                startActivity(it);
//                finish();
//            }
//            else{
//
//                Intent it = new Intent(EsperaGrafActivity.this, MenuPrincNormalActivity.class);
//                startActivity(it);
//                finish();
//
//            }
//        }
//    };

}
