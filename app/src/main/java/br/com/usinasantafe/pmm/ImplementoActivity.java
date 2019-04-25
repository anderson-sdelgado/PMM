package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.TurnoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ImplementoTO;

public class ImplementoActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ConfiguracaoTO configTO;
    private EquipTO equipTO;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implemento);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkImplemento = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancImplemento = (Button) findViewById(R.id.buttonCancPadrao);
        TextView textViewImplemento = (TextView) findViewById(R.id.textViewImplemento);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ImplementoActivity.this)) {

                            progressBar = new ProgressDialog(ImplementoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Operador...");
                            progressBar.show();

                            ManipDadosVerif.getInstance().verDados("", "EquipSeg"
                                    , ImplementoActivity.this, ImplementoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);
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

        textViewImplemento.setText("IMPLEMENTO " + pmmContext.getContImplemento() + ":");

        buttonOkImplemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Long impl = 0L;
                boolean verif = true;

                List implementoList;

                if (!editTextPadrao.getText().toString().equals("")) {

                    impl = Long.parseLong(editTextPadrao.getText().toString());

                    ArrayList listaPesq = new ArrayList();
                    EquipSegTO equipSegTO = new EquipSegTO();

                    EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                    pesquisa.setCampo("codEquip");
                    pesquisa.setValor(impl);
                    listaPesq.add(pesquisa);

                    EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                    pesquisa2.setCampo("tipoEquip");
                    pesquisa2.setValor(3L);
                    listaPesq.add(pesquisa2);

                    List equipSegList = equipSegTO.get(listaPesq);
                    listaPesq.clear();

                    if (equipSegList.size() == 0) {

                        verif = false;
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);
                        alerta.setTitle("ATENCAO");
                        alerta.setMessage("NUMERAÇÃO DE IMPLEMENTO INCORRETA. FAVOR, VERIFICAR A NUMERAÇÃO OU ATUALIZAR A BASE DE DADOS NOVAMENTE!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    } else {

                        ArrayList listaPesq2 = new ArrayList();

                        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
                        pesq1.setCampo("codEquipImplemento");
                        pesq1.setValor(impl);
                        listaPesq2.add(pesq1);

                        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
                        pesq2.setCampo("idApontImplemento");
                        pesq2.setValor(0);
                        listaPesq2.add(pesq2);

                        ImplementoTO implementoTO = new ImplementoTO();
                        implementoList = implementoTO.get(listaPesq2);
                        listaPesq2.clear();

                        if (implementoList.size() != 0) {
                            verif = false;
                            AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);
                            alerta.setTitle("ATENCAO");
                            alerta.setMessage("NUMERAÇÃO DE IMPLEMENTO REPETIDA! FAVOR, DIGITE A NUMERAÇÃO CORRETA DO 2º IMPLEMENTO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();
                        }

                    }

                }

                if (verif) {

                    Intent it;
                    ArrayList listaPesq = new ArrayList();
                    switch (pmmContext.getContImplemento()) {
                        case 1:

                            EspecificaPesquisa pesq1 = new EspecificaPesquisa();
                            pesq1.setCampo("posImplemento");
                            pesq1.setValor(1L);
                            listaPesq.add(pesq1);

                            EspecificaPesquisa pesq2 = new EspecificaPesquisa();
                            pesq2.setCampo("idApontImplemento");
                            pesq2.setValor(0);
                            listaPesq.add(pesq2);

                            ImplementoTO implemento1TO = new ImplementoTO();
                            implementoList = implemento1TO.get(listaPesq);

                            for (int i = 0; i < implementoList.size(); i++) {
                                implemento1TO = (ImplementoTO) implementoList.get(i);
                                implemento1TO.delete();
                            }
                            implementoList.clear();
                            listaPesq.clear();

                            implemento1TO.setPosImplemento(1L);
                            implemento1TO.setCodEquipImplemento(impl);
                            implemento1TO.setIdApontImplemento(0L);
                            implemento1TO.insert();

                            pmmContext.setContImplemento(pmmContext.getContImplemento() + 1);
                            it = new Intent(ImplementoActivity.this, ImplementoActivity.class);
                            startActivity(it);
                            finish();
                            break;

                        case 2:

                            EspecificaPesquisa pesq3 = new EspecificaPesquisa();
                            pesq3.setCampo("posImplemento");
                            pesq3.setValor(2L);
                            listaPesq.add(pesq3);

                            EspecificaPesquisa pesq4 = new EspecificaPesquisa();
                            pesq4.setCampo("idApontImplemento");
                            pesq4.setValor(0);
                            listaPesq.add(pesq4);

                            ImplementoTO implemento2TO = new ImplementoTO();
                            implementoList = implemento2TO.get(listaPesq);

                            for (int i = 0; i < implementoList.size(); i++) {
                                implemento2TO = (ImplementoTO) implementoList.get(i);
                                implemento2TO.delete();
                            }
                            implementoList.clear();
                            listaPesq.clear();

                            implemento2TO.setPosImplemento(2L);
                            implemento2TO.setCodEquipImplemento(impl);
                            implemento2TO.setIdApontImplemento(0L);
                            implemento2TO.insert();

                            if (pmmContext.getVerPosTela() == 1) {

                                configTO = new ConfiguracaoTO();
                                List listConfigTO = configTO.all();
                                configTO = (ConfiguracaoTO) listConfigTO.get(0);
                                listConfigTO.clear();

                                equipTO = new EquipTO();
                                List listEquipTO = equipTO.get("idEquip", pmmContext.getBoletimMMTO().getCodEquipBoletim());
                                equipTO = (EquipTO) listEquipTO.get(0);
                                listEquipTO.clear();

                                TurnoTO turnoTO = new TurnoTO();
                                List turnoList = turnoTO.get("idTurno", pmmContext.getBoletimMMTO().getCodTurnoBoletim());
                                turnoTO = (TurnoTO) turnoList.get(0);

                                if ((equipTO.getIdChecklist() > 0) &&
                                        ((configTO.getUltTurnoCLConfig() != turnoTO.getIdTurno())
                                                || ((configTO.getUltTurnoCLConfig() == turnoTO.getIdTurno()) && (!configTO.getDtUltCLConfig().equals(Tempo.getInstance().dataSHora()))))) {

                                    pmmContext.getBoletimMMTO().setStatusBoletim(1L);
                                    ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), true, getLatitude(), getLongitude());
                                    ManipDadosEnvio.getInstance().envioDadosPrinc();

                                    ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                                    List itemCheckList =  itemCheckListTO.get("idChecklist", equipTO.getIdChecklist());
                                    Long qtde = (long) itemCheckList.size();
                                    itemCheckList.clear();

                                    CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
                                    cabecCheckListTO.setDtCab(Tempo.getInstance().data());
                                    cabecCheckListTO.setEquipCab(configTO.getEquipConfig());
                                    cabecCheckListTO.setFuncCab(pmmContext.getBoletimMMTO().getCodMotoBoletim());
                                    cabecCheckListTO.setTurnoCab(pmmContext.getBoletimMMTO().getCodTurnoBoletim());
                                    cabecCheckListTO.setQtdeItemCab(qtde);
                                    cabecCheckListTO.setStatusCab(1L);
                                    cabecCheckListTO.setDtAtualCab("0");
                                    cabecCheckListTO.insert();

                                    pmmContext.setPosChecklist(1L);
                                    it = new Intent(ImplementoActivity.this, ItemChecklistActivity.class);
                                    startActivity(it);
                                    finish();

                                }
                                else{

                                    configTO.setHorimetroConfig(pmmContext.getBoletimMMTO().getHodometroInicialBoletim());
                                    configTO.update();
                                    configTO.commit();
                                    pmmContext.getBoletimMMTO().setStatusBoletim(1L);
                                    ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), false, getLatitude(), getLongitude());
                                    ManipDadosEnvio.getInstance().envioDadosPrinc();

//                                    GRAFICO
//                                    it = new Intent(ImplementoActivity.this, EsperaGrafActivity.class);
//                                    startActivity(it);
//                                    finish();

//                                    ANTIGO SEM GRAFICO
                                    it = new Intent(ImplementoActivity.this, MenuPrincNormalActivity.class);
                                    startActivity(it);
                                    finish();

                                }

                            } else {

//                                    GRAFICO
//                                    it = new Intent(ImplementoActivity.this, EsperaGrafActivity.class);
//                                    startActivity(it);
//                                    finish();

//                                ANTIGO SEM GRAFICO
                                it = new Intent(ImplementoActivity.this, MenuPrincNormalActivity.class);
                                startActivity(it);
                                finish();

                            }

                            break;

                    }


                }

            }
        });

        buttonCancImplemento.setOnClickListener(new View.OnClickListener() {

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
        if (pmmContext.getContImplemento() == 1) {
            if (pmmContext.getVerPosTela() == 1) {
                Intent it = new Intent(ImplementoActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            } else if (pmmContext.getVerPosTela() == 19) {
                Intent it = new Intent(ImplementoActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        } else {
            pmmContext.setContImplemento(pmmContext.getContImplemento() - 1);
            Intent it = new Intent(ImplementoActivity.this, ImplementoActivity.class);
            startActivity(it);
            finish();
        }
    }

}
