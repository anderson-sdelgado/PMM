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

import java.util.ArrayList;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;

public class MenuCertifActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView menuCertifListView;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_certif);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetMenuInicialApont = findViewById(R.id.buttonRetMenuInicialApont);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAMENTO");
        itens.add("ATUALIZAR");
        itens.add("LOG VIAGEM");
        itens.add("LOG BOLETIM");

        AdapterList adapterList = new AdapterList(this, itens);
        menuCertifListView = findViewById(R.id.listViewMenuInicialApont);
        menuCertifListView.setAdapter(adapterList);

        menuCertifListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressWarnings("rawtypes")
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("APONTAMENTO")) {

                    if (pmmContext.getCecCTR().verPreCECAberto()) {

                        if (pmmContext.getCecCTR().verDataPreCEC()) {

                            Intent it = new Intent(MenuCertifActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("É NECESSÁRIO A INSERÇÃO DO HORARIO DE SAÍDA DA USINA E/OU DE CHEGADA AO CAMPO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(MenuCertifActivity.this, MenuPrincECMActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                            });

                            alerta.show();

                        }
                    }
                    else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("É NECESSÁRIO A INSERÇÃO DO HORARIO DE SAÍDA DA USINA E/OU DE CHEGADA AO CAMPO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent it = new Intent(MenuCertifActivity.this, MenuPrincECMActivity.class);
                                startActivity(it);
                                finish();
                            }
                        });

                        alerta.show();


                    }

                } else if (text.equals("ATUALIZAR")) {

                    if (connectNetwork) {

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();

                        pmmContext.getConfigCTR().atualTodasTabelas(MenuCertifActivity.this, progressBar);

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }

                } else if (text.equals("LOG VIAGEM")) {

                    if (pmmContext.getCecCTR().verPreCECTerminadoList()) {
                        Intent it = new Intent(MenuCertifActivity.this, BackupPreCECActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO TEM NENHUMA VIAGEM SALVA NA BASE DE DADOS.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();
                    }

                } else if (text.equals("LOG BOLETIM")) {

                    if (pmmContext.getCecCTR().hasElemCEC()) {
                        Intent it = new Intent(MenuCertifActivity.this, BackupCECActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO TEM NENHUM BOLETIM SALVO NA BASE DE DADOS.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();
                    }

                }

            }

        });

        buttonRetMenuInicialApont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MenuCertifActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();

            }
        });


    }

    public void onBackPressed() {
    }

}