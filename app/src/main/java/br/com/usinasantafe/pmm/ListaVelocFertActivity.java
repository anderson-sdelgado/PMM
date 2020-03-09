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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.PressaoBocalBean;
import br.com.usinasantafe.pmm.util.ConexaoWeb;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;

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
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pmmContext.getBoletimCTR().atualDadosPressao(ListaVelocFertActivity.this, ListaVelocFertActivity.class, progressBar);

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


        PressaoBocalBean pressaoBocalBean = new PressaoBocalBean();

        ArrayList pesqList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBocal");
        pesquisa.setValor(pmmContext.getApontCTR().getBocalApontFert());
        pesquisa.setTipo(1);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("valorPressao");
        pesquisa2.setValor(pmmContext.getApontCTR().getPressaoApontFert());
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        velocList = pressaoBocalBean.getAndOrderBy(pesqList, "valorVeloc", true);

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < velocList.size(); i++){
            pressaoBocalBean = (PressaoBocalBean) velocList.get(i);
            itens.add("" + pressaoBocalBean.getValorVeloc());
        }

        HashSet<String> hashSet = new HashSet<String>(itens);
        itens.clear();
        itens.addAll(hashSet);
        Collections.sort(itens);

        AdapterList adapterList = new AdapterList(this, itens);
        velocListView = (ListView) findViewById(R.id.listVelocidade);
        velocListView.setAdapter(adapterList);

        velocListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                pmmContext.getApontCTR().setVelocApont(Long.parseLong(textView.getText().toString()));
                velocList.clear();

                pmmContext.getApontCTR().salvarApont(1L, getLongitude(), getLatitude());

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
