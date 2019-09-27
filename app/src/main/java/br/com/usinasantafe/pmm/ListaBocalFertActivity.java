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
import br.com.usinasantafe.pmm.to.estaticas.BocalTO;

public class ListaBocalFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView bocalListView;
    private List bocalList;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bocal_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetBocal = (Button) findViewById(R.id.buttonRetBocal);
        Button buttonAtualBocal = (Button) findViewById(R.id.buttonAtualBocal);

        buttonAtualBocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaBocalFertActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaBocalFertActivity.this)) {

                            progressBar = new ProgressDialog(ListaBocalFertActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pmmContext.getBoletimCTR().atualDadosBocal(ListaBocalFertActivity.this, ListaBocalFertActivity.class, progressBar);

                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaBocalFertActivity.this);
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

        BocalTO bocalTO = new BocalTO();
        bocalList = bocalTO.orderBy("codBocal", true);

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < bocalList.size(); i++){
            bocalTO = (BocalTO) bocalList.get(i);
            itens.add(bocalTO.getCodBocal() + " - " + bocalTO.getDescrBocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        bocalListView = (ListView) findViewById(R.id.listBocal);
        bocalListView.setAdapter(adapterList);

        bocalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                BocalTO bocalTO = (BocalTO)  bocalList.get(position);
                pmmContext.getApontMMMovLeiraCTR().setBocal(bocalTO.getIdBocal());
                bocalList.clear();

                Intent it = new Intent(ListaBocalFertActivity.this, ListaPressaoFertActivity.class);
                startActivity(it);

            }

        });

        buttonRetBocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaBocalFertActivity.this, ListaPressaoFertActivity.class);
                startActivity(it);
            }
        });

    }

    public void onBackPressed()  {
    }

}
