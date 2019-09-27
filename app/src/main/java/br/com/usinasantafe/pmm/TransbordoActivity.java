package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.to.estaticas.RFuncaoAtivParTO;

public class TransbordoActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transbordo);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkTransbordo = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancTransbordo = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(TransbordoActivity.this)) {

                            progressBar = new ProgressDialog(TransbordoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pmmContext.getBoletimCTR().atualDadosEquipSeg(TransbordoActivity.this, TransbordoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);
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


        buttonOkTransbordo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    Long transb = Long.parseLong(editTextPadrao.getText().toString());
                    pmmContext.getApontMMMovLeiraCTR().setTransb(transb);

                    if(pmmContext.getBoletimCTR().verTransb(transb)){

                        if(pmmContext.getApontMMMovLeiraCTR().verifBackupApontTransb()){

                            AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("NUMERAÇÃO DE TRANSBORDO COM MESMO VALOR DO APONTAMENTO ANTERIOR. FAVOR, VERIFICAR A NUMERAÇÃO DIGITADA!");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();

                        }
                        else{

                            if(pmmContext.getVerPosTela() == 2) {
                                pmmContext.getApontMMMovLeiraCTR().salvarApont();
                            }
                            else {
                                pmmContext.getApontMMMovLeiraCTR().salvarApontTransb();
                            }

                            List rFuncaoAtivParList = pmmContext.getBoletimCTR().retFuncaoAtivParList(pmmContext.getApontMMMovLeiraCTR().getAtivApont());

                            boolean rendimento = false;

                            for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                                RFuncaoAtivParTO rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(i);
                                if(rFuncaoAtivParTO.getCodFuncao() == 1){
                                    rendimento = true;
                                }
                            }
                            rFuncaoAtivParList.clear();

                            if (rendimento) {
                                ConfigCTR configCTR = new ConfigCTR();
                                pmmContext.getBoletimCTR().insRend(configCTR.getConfig().getOsConfig());
                            }

                            Intent it = new Intent(TransbordoActivity.this, MenuPrincNormalActivity.class);
                            startActivity(it);

                        }

                    }
                    else{
                        AlertDialog.Builder alerta = new AlertDialog.Builder(TransbordoActivity.this);
                        alerta.setTitle("ATENCAO");
                        alerta.setMessage("NUMERAÇÃO DE TRANSBORDO INCORRETA. FAVOR, VERIFICAR A NUMERAÇÃO OU ATUALIZAR A BASE DE DADOS NOVAMENTE!");
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

        buttonCancTransbordo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        if(pmmContext.getVerPosTela() == 2) {
            Intent it = new Intent(TransbordoActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        }
        else{
            Intent it = new Intent(TransbordoActivity.this, MenuPrincNormalActivity.class);
            startActivity(it);
            finish();
        }
    }



}
