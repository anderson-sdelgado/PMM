package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RecolMangTO;

public class RaioFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raio_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    String raio = editTextPadrao.getText().toString();
                    Double raioNum = Double.valueOf(raio.replace(",", "."));
                    pmmContext.getApontaAplicFertTO().setRaioApontaAplicFert(raioNum);

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                    List configList = configuracaoTO.all();
                    configuracaoTO = (ConfiguracaoTO) configList.get(0);
                    configuracaoTO.setDtUltApontConfig(Tempo.getInstance().data());
                    configuracaoTO.update();

                    BoletimMMTO boletimMMTO = new BoletimMMTO();
                    List listBoletim = boletimMMTO.get("statusBoletim", 1L);

                    if (listBoletim.size() > 0) {

                        ArrayList listaPesq = new ArrayList();
                        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                        pesquisa.setCampo("nroOSRendMangRecol");
                        pesquisa.setValor(pmmContext.getApontaAplicFertTO().getOsApontaAplicFert());
                        listaPesq.add(pesquisa);

                        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                        pesquisa2.setCampo("equipRendMangRecol");
                        pesquisa2.setValor(pmmContext.getApontaAplicFertTO().getEquipApontaAplicFert());
                        listaPesq.add(pesquisa2);

                        RecolMangTO recolMangTO = new RecolMangTO();
                        List recolMangList = recolMangTO.get(listaPesq);

                        if (recolMangList.size() == 0) {
                            boletimMMTO = (BoletimMMTO) listBoletim.get(0);
                            recolMangTO.setIdBolRendMangRecol(boletimMMTO.getIdBoletim());
                            recolMangTO.setIdExtBolRendMangRecol(boletimMMTO.getIdExtBoletim());
                            recolMangTO.setNroOSRendMangRecol(pmmContext.getApontaAplicFertTO().getOsApontaAplicFert());
                            recolMangTO.setEquipRendMangRecol(pmmContext.getApontaAplicFertTO().getEquipApontaAplicFert());
                            recolMangTO.setValorRendMangRecol(0L);
                            recolMangTO.setStatusRendMangRecol(1L);
                            recolMangTO.insert();
                            recolMangTO.commit();
                        }

                    }

                    if(pmmContext.getVerPosTela() == 15){
                        pmmContext.getApontaAplicFertTO().setEquipApontaAplicFert(configuracaoTO.getEquipConfig());
                    }

                    ManipDadosEnvio.getInstance().salvaApontaAplicFert(pmmContext.getApontaAplicFertTO());
                    Intent it = new Intent(RaioFertActivity.this, ListaEquipFertActivity.class);
                    startActivity(it);
                    finish();

                }
            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
//                else {
//                    Intent it = new Intent(RaioFertActivity.this,  VelocidadeFertActivity.class);
//                    startActivity(it);
//                    finish();
//                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(RaioFertActivity.this,  VelocidadeFertActivity.class);
        startActivity(it);
        finish();
    }

}
