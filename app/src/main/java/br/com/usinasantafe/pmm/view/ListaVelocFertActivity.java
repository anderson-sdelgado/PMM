package br.com.usinasantafe.pmm.view;

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

import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;

public class ListaVelocFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView velocListView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veloc_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetVelocidade = findViewById(R.id.buttonRetVelocidade);
        Button buttonAtualVelocidade = findViewById(R.id.buttonAtualVelocidade);

        buttonAtualVelocidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaVelocFertActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (connectNetwork) {

                            progressBar = new ProgressDialog(ListaVelocFertActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pmmContext.getMotoMecFertCTR().atualDados(ListaVelocFertActivity.this, ListaVelocFertActivity.class, progressBar, "Pressao");

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

        AdapterList adapterList = new AdapterList(this, pmmContext.getMotoMecFertCTR().velocArrayList());
        velocListView = findViewById(R.id.listVelocidade);
        velocListView.setAdapter(adapterList);

        velocListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                pmmContext.getConfigCTR().setVelocConfig(Long.parseLong(textView.getText().toString()));

                if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);
                            startActivity(it);
                            finish();
                        }
                    });
                    alerta.show();

                } else {

                    if (pmmContext.getMotoMecFertCTR().verifBackupApont(0L)) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaVelocFertActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        alerta.show();

                    } else {
                        List<RFuncaoAtivParBean> rFuncaoAtivParList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList();

                        boolean recolhimento = false;

                        for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                            RFuncaoAtivParBean rFuncaoAtivParBean = rFuncaoAtivParList.get(i);
                            if (rFuncaoAtivParBean.getCodFuncao() == 4) {
                                recolhimento = true;
                            }
                        }
                        rFuncaoAtivParList.clear();

                        pmmContext.getMotoMecFertCTR().salvarApont( 0L, 0L, getLongitude(), getLatitude());
                        if (recolhimento) {
                            pmmContext.getMotoMecFertCTR().insRecolh();
                        }

                        Intent it = new Intent(ListaVelocFertActivity.this, MenuPrincPMMActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

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
