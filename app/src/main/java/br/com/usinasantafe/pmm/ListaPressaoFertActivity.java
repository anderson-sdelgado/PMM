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

public class ListaPressaoFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView pressaoBocalListView;
    private List pressaoBocalList;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pressao_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetPressao = (Button) findViewById(R.id.buttonRetPressao);
        Button buttonAtualPressao = (Button) findViewById(R.id.buttonAtualPressao);

        buttonAtualPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaPressaoFertActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaPressaoFertActivity.this)) {

                            progressBar = new ProgressDialog(ListaPressaoFertActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pmmContext.getBoletimCTR().atualDadosPressao(ListaPressaoFertActivity.this, ListaPressaoFertActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaPressaoFertActivity.this);
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
        pressaoBocalList = pressaoBocalBean.getAndOrderBy("idBocal", pmmContext.getApontCTR().getBocalApontFert(), "valorPressao", true);

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < pressaoBocalList.size(); i++){
            pressaoBocalBean = (PressaoBocalBean) pressaoBocalList.get(i);
            itens.add("" + pressaoBocalBean.getValorPressao());
        }

        HashSet<String> hashSet = new HashSet<String>(itens);
        itens.clear();
        itens.addAll(hashSet);
        Collections.sort(itens);

        AdapterList adapterList = new AdapterList(this, itens);
        pressaoBocalListView = (ListView) findViewById(R.id.listPressao);
        pressaoBocalListView.setAdapter(adapterList);

        pressaoBocalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                pmmContext.getApontCTR().setPressaoBocal(Double.parseDouble(textView.getText().toString()));
                pressaoBocalList.clear();

                Intent it = new Intent(ListaPressaoFertActivity.this, ListaVelocFertActivity.class);
                startActivity(it);

            }

        });

        buttonRetPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaPressaoFertActivity.this, ListaBocalFertActivity.class);
                startActivity(it);
            }
        });

    }

    public void onBackPressed() {
    }

}
