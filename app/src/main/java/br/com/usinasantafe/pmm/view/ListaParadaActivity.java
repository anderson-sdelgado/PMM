package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pmm.util.ConexaoWeb;

public class ListaParadaActivity extends ActivityGeneric {

    private ListView paradaListView;
    private PMMContext pmmContext;
    private List paradaList;
    private ProgressDialog progressBar;
    private ArrayAdapter<String> adapter;
    private String paradaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada);

        pmmContext = (PMMContext) getApplication();

        Button buttonAtualParada = findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = findViewById(R.id.buttonRetMenuParada);
        EditText editPesqListParada = findViewById(R.id.editPesqListParada);

        paradaList = pmmContext.getMotoMecFertCTR().getListParada();

        String itens[] = new String[paradaList.size()];

        for (int i = 0; i < paradaList.size(); i++) {
            ParadaBean paradaBean = (ParadaBean) paradaList.get(i);
            itens[i] = paradaBean.getCodParada() + " - " + paradaBean.getDescrParada();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.activity_item_lista, R.id.textViewItemList, itens);
        paradaListView = findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(adapter);

        editPesqListParada.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                ListaParadaActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

        buttonAtualParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaParadaActivity.this)) {

                    progressBar = new ProgressDialog(ListaParadaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pmmContext.getMotoMecFertCTR().atualDadosParada(ListaParadaActivity.this, ListaParadaActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
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

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaParadaActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }
        });

        paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                paradaString = textView.getText().toString();

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                alerta.setTitle("ATENÇÃO");

                String label = "DESEJA REALMENTE REALIZAR A PARADA '" + paradaString + "' ?";

                alerta.setMessage(label);

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (pmmContext.getMotoMecFertCTR().verDataHoraParaInserirApont()) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(ListaParadaActivity.this, MenuPrincPMMActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                            });
                            alerta.show();

                        } else {

                            if (pmmContext.getMotoMecFertCTR().verifBackupApont(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada())) {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("PARADA JÁ APONTADA PARA O EQUIPAMENTO!");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                alerta.show();
                            } else {

                                pmmContext.getConfigCTR().clearDadosFert();
                                pmmContext.getMotoMecFertCTR().salvarApont(pmmContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude());
                                Intent it = new Intent(ListaParadaActivity.this, MenuPrincPMMActivity.class);
                                startActivity(it);
                                finish();

                            }

                        }

                        paradaList.clear();

                    }

                });


                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });

                alerta.show();

            }

        });

    }

    public void onBackPressed() {
    }

}
