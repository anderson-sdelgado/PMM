package br.com.usinasantafe.pmm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;

public class PneuActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pneu);

        Button buttonOkPneu = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancPneu = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( PneuActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(PneuActivity.this)) {

                            progressBar = new ProgressDialog(PneuActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Operador...");
                            progressBar.show();

                            ManipDadosVerif.getInstance().verDados("", "Pneu"
                                    , PneuActivity.this, PneuActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( PneuActivity.this);
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

        buttonOkPneu.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

//                    MotoristaTO motoristaBD = new MotoristaTO();
//                    List listaMotorista = motoristaBD.get("codMotorista", Long.parseLong(editTextPadrao.getText().toString()));
//
//                    if (listaMotorista.size() > 0) {
//
//                        motoristaBD = (MotoristaTO) listaMotorista.get(0);
//                        pmmContext.getBoletimMMTO().setCodMotoBoletim(motoristaBD.getCodMotorista());
//                        listaMotorista.clear();
//                        Intent it = new Intent(OperadorActivity.this, EquipActivity.class);
//                        startActivity(it);
//                        finish();
//
//                    } else {
//
//                        AlertDialog.Builder alerta = new AlertDialog.Builder(OperadorActivity.this);
//                        alerta.setTitle("ATENÇÃO");
//                        alerta.setMessage("NUMERAÇÃO DO OPERADOR INEXISTENTE! FAVOR VERIFICA A MESMA.");
//                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//
//                        alerta.show();
//
//                    }
                }

            }

        });

        buttonCancPneu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(PneuActivity.this, ListaPosPneuActivity.class);
        startActivity(it);
        finish();
    }

}
