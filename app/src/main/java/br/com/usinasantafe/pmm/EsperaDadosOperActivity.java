package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfigTO;

public class EsperaDadosOperActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_dados_oper);

        pmmContext = (PMMContext) getApplication();

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimList = boletimMMTO.get("statusBoletim", 1L);
        boletimMMTO = (BoletimMMTO) boletimList.get(0);
        boletimList.clear();

        ConfigTO configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);
        configList.clear();
        configTO.setVisDadosConfig(0L);
        configTO.update();

        ManipDadosVerif.getInstance().setVerTelaAtualPerda(0);

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(this)) {
            ManipDadosVerif.getInstance().verDados(String.valueOf(boletimMMTO.getCodMotoBoletim()), "Perda", EsperaDadosOperActivity.this, DadosColheitaActivity.class,  MenuPrincNormalActivity.class);
            customHandler.postDelayed(runnable, 10000);
        }
        else{
            Intent it = new Intent(EsperaDadosOperActivity.this, MenuPrincNormalActivity.class);
            startActivity(it);
            finish();
        }

    }

    private Runnable runnable = new Runnable(){
        public void run() {
            if(!ManipDadosVerif.getInstance().isVerTerm()) {
                Intent it = new Intent(EsperaDadosOperActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        }
    };

}
