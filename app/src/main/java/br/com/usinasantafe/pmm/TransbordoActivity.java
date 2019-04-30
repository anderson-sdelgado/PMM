package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.tb.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RendimentoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.TransbordoTO;

public class TransbordoActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private EquipTO equipTO;
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
                            progressBar.setMessage("Atualizando Operador...");
                            progressBar.show();

                            ManipDadosVerif.getInstance().verDados("", "EquipSeg"
                                    , TransbordoActivity.this, TransbordoActivity.class, progressBar);

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
                // TODO Auto-generated method stub
                if (!editTextPadrao.getText().toString().equals("")) {

                    ArrayList listaPesq = new ArrayList();
                    EquipSegTO equipSegTO = new EquipSegTO();

                    EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                    pesquisa.setCampo("codEquip");
                    pesquisa.setValor(Long.parseLong(editTextPadrao.getText().toString()));
                    listaPesq.add(pesquisa);

                    EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                    pesquisa2.setCampo("tipoEquip");
                    pesquisa2.setValor(2L);
                    listaPesq.add(pesquisa2);

                    List equipSegList = equipSegTO.get(listaPesq);

                    if(equipSegList.size() > 0){

                        TransbordoTO transbordoTO = new TransbordoTO();
                        transbordoTO.setCodEquipTransbordo(Long.parseLong(editTextPadrao.getText().toString()));
                        transbordoTO.deleteAll();
                        transbordoTO.insert();

                        if(pmmContext.getVerPosTela() == 2) {

                            pmmContext.getApontaMMTO().setTransbordoAponta(Long.parseLong(editTextPadrao.getText().toString()));

                            pmmContext.getApontaMMTO().setLatitudeAponta(getLatitude());
                            pmmContext.getApontaMMTO().setLongitudeAponta(getLongitude());
                            ManipDadosEnvio.getInstance().salvaApontaMM(pmmContext.getApontaMMTO());

                            ConfiguracaoTO configTO = new ConfiguracaoTO();
                            List listConfigTO = configTO.all();
                            configTO = (ConfiguracaoTO) listConfigTO.get(0);
                            listConfigTO.clear();
                            configTO.setDtUltApontConfig(Tempo.getInstance().datahora());
                            configTO.update();

                            AtividadeTO atividadeTO = new AtividadeTO();
                            List atividadeList = atividadeTO.get("idAtiv", pmmContext.getApontaMMTO().getAtividadeAponta());
                            atividadeTO = (AtividadeTO) atividadeList.get(0);

                            if (atividadeTO.getFlagRendimento() == 1) {

                                BoletimMMTO boletimMMTO = new BoletimMMTO();
                                List listBoletim = boletimMMTO.get("statusBoletim", 1L);

                                if (listBoletim.size() > 0) {
                                    RendimentoTO rendimentoTO = new RendimentoTO();
                                    List rendList = rendimentoTO.get("nroOSRendimento", pmmContext.getApontaMMTO().getOsAponta());
                                    if (rendList.size() == 0) {
                                        boletimMMTO = (BoletimMMTO) listBoletim.get(0);
                                        rendimentoTO.setIdBolRendimento(boletimMMTO.getIdBoletim());
                                        rendimentoTO.setIdExtBolRendimento(boletimMMTO.getIdExtBoletim());
                                        rendimentoTO.setNroOSRendimento(pmmContext.getApontaMMTO().getOsAponta());
                                        rendimentoTO.setValorRendimento(0D);
                                        rendimentoTO.insert();
                                        rendimentoTO.commit();
                                    }

                                }

                            }

                            Intent it = new Intent(TransbordoActivity.this, MenuPrincNormalActivity.class);
                            startActivity(it);

                        }
                        else {

                            BackupApontaMMTO backupApontaMMTO = new BackupApontaMMTO();
                            List bkpApontaList = backupApontaMMTO.all();
                            backupApontaMMTO = (BackupApontaMMTO) bkpApontaList.get(bkpApontaList.size() - 1);

                            if(backupApontaMMTO.getTransbordoAponta().equals(Long.parseLong(editTextPadrao.getText().toString()))){

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

                                ConfiguracaoTO configTO = new ConfiguracaoTO();
                                List listConfigTO = configTO.all();
                                configTO = (ConfiguracaoTO) listConfigTO.get(0);
                                listConfigTO.clear();
                                configTO.setDtUltApontConfig(Tempo.getInstance().datahora());
                                configTO.update();

                                ApontaMMTO apontaMMTO = new ApontaMMTO();
                                apontaMMTO.setOsAponta(backupApontaMMTO.getOsAponta());
                                apontaMMTO.setAtividadeAponta(backupApontaMMTO.getAtividadeAponta());
                                apontaMMTO.setParadaAponta(backupApontaMMTO.getParadaAponta());
                                apontaMMTO.setTransbordoAponta(Long.parseLong(editTextPadrao.getText().toString()));
                                ManipDadosEnvio.getInstance().salvaApontaMM(apontaMMTO);

                                Intent it = new Intent(TransbordoActivity.this, MenuPrincNormalActivity.class);
                                startActivity(it);
                                finish();

                            }

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
                // TODO Auto-generated method stub
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
