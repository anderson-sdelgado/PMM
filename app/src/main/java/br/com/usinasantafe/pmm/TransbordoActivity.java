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
import br.com.usinasantafe.pmm.to.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.RFuncaoAtivParTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.RendMMTO;
import br.com.usinasantafe.pmm.to.variaveis.TransbMMTO;

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
                            progressBar.setMessage("Atualizando Transbordo...");
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

                if (!editTextPadrao.getText().toString().equals("")) {

                    ArrayList listaPesq = new ArrayList();
                    EquipSegTO equipSegTO = new EquipSegTO();

                    EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                    pesquisa.setCampo("nroEquip");
                    pesquisa.setValor(Long.parseLong(editTextPadrao.getText().toString()));
                    pesquisa.setTipo(1);
                    listaPesq.add(pesquisa);

                    EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                    pesquisa2.setCampo("tipoEquip");
                    pesquisa2.setValor(2L);
                    pesquisa2.setTipo(1);
                    listaPesq.add(pesquisa2);

                    List equipSegList = equipSegTO.get(listaPesq);

                    if(equipSegList.size() > 0){

                        TransbMMTO transbMMTO = new TransbMMTO();
                        transbMMTO.setCodEquipTransbMM(Long.parseLong(editTextPadrao.getText().toString()));
                        transbMMTO.deleteAll();
                        transbMMTO.insert();

                        if(pmmContext.getVerPosTela() == 2) {

                            pmmContext.getApontMMTO().setTransbApontMM(Long.parseLong(editTextPadrao.getText().toString()));

                            pmmContext.getApontMMTO().setLatitudeApontMM(0D);
                            pmmContext.getApontMMTO().setLongitudeApontMM(0D);
                            ManipDadosEnvio.getInstance().salvaApontaMM(pmmContext.getApontMMTO());

                            ConfigTO configTO = new ConfigTO();
                            List listConfigTO = configTO.all();
                            configTO = (ConfigTO) listConfigTO.get(0);
                            listConfigTO.clear();
                            configTO.setDtUltApontConfig(Tempo.getInstance().datahora());
                            configTO.update();

                            AtividadeTO atividadeTO = new AtividadeTO();
                            List atividadeList = atividadeTO.get("idAtiv", pmmContext.getApontMMTO().getAtivApontMM());
                            atividadeTO = (AtividadeTO) atividadeList.get(0);

                            RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
                            ArrayList pesqList = new ArrayList();

                            EspecificaPesquisa pesq1 = new EspecificaPesquisa();
                            pesq1.setCampo("idAtivPar");
                            pesq1.setValor(atividadeTO.getIdAtiv());
                            pesq1.setTipo(1);
                            pesqList.add(pesq1);

                            EspecificaPesquisa pesq2 = new EspecificaPesquisa();
                            pesq2.setCampo("tipoFuncao");
                            pesq2.setValor(1L);
                            pesq2.setTipo(1);
                            pesqList.add(pesq2);

                            List rFuncaoAtivParList = rFuncaoAtivParTO.get(pesqList);

                            boolean rendimento = false;

                            for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                                rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(i);
                                if(rFuncaoAtivParTO.getCodFuncao() == 1){
                                    rendimento = true;
                                }
                            }
                            rFuncaoAtivParList.clear();

                            if (rendimento) {

                                BoletimMMTO boletimMMTO = new BoletimMMTO();
                                List listBoletim = boletimMMTO.get("statusBolMM", 1L);

                                if (listBoletim.size() > 0) {
                                    RendMMTO rendMMTO = new RendMMTO();
                                    List rendList = rendMMTO.get("nroOSRendMM", pmmContext.getApontMMTO().getOsApontMM());
                                    if (rendList.size() == 0) {
                                        boletimMMTO = (BoletimMMTO) listBoletim.get(0);
                                        rendMMTO.setIdBolRendMM(boletimMMTO.getIdBolMM());
                                        rendMMTO.setIdExtBolRendMM(boletimMMTO.getIdExtBolMM());
                                        rendMMTO.setNroOSRendMM(pmmContext.getApontMMTO().getOsApontMM());
                                        rendMMTO.setValorRendMM(0D);
                                        rendMMTO.insert();
                                        rendMMTO.commit();
                                    }

                                }

                            }

                            Intent it = new Intent(TransbordoActivity.this, MenuPrincNormalActivity.class);
                            startActivity(it);

                        }
                        else {

                            BackupApontaTO backupApontaTO = new BackupApontaTO();
                            List bkpApontaList = backupApontaTO.all();
                            backupApontaTO = (BackupApontaTO) bkpApontaList.get(bkpApontaList.size() - 1);

                            if(backupApontaTO.getTransbAponta().equals(Long.parseLong(editTextPadrao.getText().toString()))){

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

                                ConfigTO configTO = new ConfigTO();
                                List listConfigTO = configTO.all();
                                configTO = (ConfigTO) listConfigTO.get(0);
                                listConfigTO.clear();
                                configTO.setDtUltApontConfig(Tempo.getInstance().datahora());
                                configTO.update();

                                ApontMMTO apontMMTO = new ApontMMTO();
                                apontMMTO.setOsApontMM(backupApontaTO.getOsAponta());
                                apontMMTO.setAtivApontMM(backupApontaTO.getAtividadeAponta());
                                apontMMTO.setParadaApontMM(backupApontaTO.getParadaAponta());
                                apontMMTO.setTransbApontMM(Long.parseLong(editTextPadrao.getText().toString()));
                                ManipDadosEnvio.getInstance().salvaApontaMM(apontMMTO);

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
