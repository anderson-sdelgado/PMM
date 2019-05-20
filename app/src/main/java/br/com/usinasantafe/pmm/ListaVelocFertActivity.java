package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.tb.estaticas.PressaoBocalTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RecolhimentoTO;

public class ListaVelocFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView velocListView;
    private List velocList;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veloc_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetVelocidade = (Button) findViewById(R.id.buttonRetVelocidade);
        Button buttonAtualVelocidade = (Button) findViewById(R.id.buttonAtualVelocidade);

        buttonAtualVelocidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaVelocFertActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaVelocFertActivity.this)) {

                            progressBar = new ProgressDialog(ListaVelocFertActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Velocidade...");
                            progressBar.show();

                            ManipDadosVerif.getInstance().verDados("", "pressaobocal"
                                    , ListaVelocFertActivity.this, ListaVelocFertActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaVelocFertActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });


        PressaoBocalTO pressaoBocalTO = new PressaoBocalTO();

        ArrayList listaPesq = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBocal");
        pesquisa.setValor(pmmContext.getApontaFertTO().getBocalApontaFert());
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("valorPressao");
        pesquisa2.setValor(pmmContext.getApontaFertTO().getVelocApontaFert());
        listaPesq.add(pesquisa2);

        velocList = pressaoBocalTO.getAndOrderBy(listaPesq, "valorVeloc", true);

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < velocList.size(); i++){
            pressaoBocalTO = (PressaoBocalTO) velocList.get(i);
            itens.add("" + pressaoBocalTO.getValorVeloc());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        velocListView = (ListView) findViewById(R.id.listPressao);
        velocListView.setAdapter(adapterList);

        velocListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                PressaoBocalTO pressaoBocalTO = (PressaoBocalTO)  velocList.get(position);
                pmmContext.getApontaFertTO().setVelocApontaFert(pressaoBocalTO.getValorVeloc());

                BoletimFertTO boletimFertTO = new BoletimFertTO();
                List boletimList = boletimFertTO.get("statusBolFert", 1L);

                if (boletimList.size() > 0) {

                    boletimFertTO = (BoletimFertTO) boletimList.get(0);

                    RecolhimentoTO recolhimentoTO = new RecolhimentoTO();
                    ArrayList pesqList = new ArrayList();

                    EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                    pesquisa.setCampo("idBolRecol");
                    pesquisa.setValor(boletimFertTO.getIdBolFert());
                    pesqList.add(pesquisa);

                    EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                    pesquisa2.setCampo("nroOSRecol");
                    pesquisa2.setValor(pmmContext.getApontaFertTO().getOsApontaFert());
                    pesqList.add(pesquisa2);

                    List rendList = recolhimentoTO.get(pesqList);

                    if (rendList.size() == 0) {
                        recolhimentoTO.setIdBolRecol(boletimFertTO.getIdBolFert());
                        recolhimentoTO.setIdExtBolRecol(boletimFertTO.getIdExtBolFert());
                        recolhimentoTO.setNroOSRecol(pmmContext.getApontaFertTO().getOsApontaFert());
                        recolhimentoTO.setValorRecol(0L);
                        recolhimentoTO.insert();
                        recolhimentoTO.commit();
                    }

                }

                boletimList.clear();

                ManipDadosEnvio.getInstance().salvaApontaFert(pmmContext.getApontaFertTO(), 2L);
                Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetVelocidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaVelocFertActivity.this, ListaPressaoFertActivity.class);
                startActivity(it);
            }
        });


    }

    public void onBackPressed()  {
    }

}
