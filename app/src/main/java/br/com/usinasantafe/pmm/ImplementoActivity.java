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
import br.com.usinasantafe.pmm.to.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.estaticas.TurnoTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.ImpleMMTO;

public class ImplementoActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ConfigTO configTO;
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
                            progressBar.setMessage("Atualizando Implemento...");
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

                Long impl = 0L;
                boolean verif = true;

                List implementoList;

                if (!editTextPadrao.getText().toString().equals("")) {

                    impl = Long.parseLong(editTextPadrao.getText().toString());

                    ArrayList listaPesq = new ArrayList();
                    EquipSegTO equipSegTO = new EquipSegTO();

                    EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
                    pesquisa1.setCampo("nroEquip");
                    pesquisa1.setValor(impl);
                    pesquisa1.setTipo(1);
                    listaPesq.add(pesquisa1);

                    EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                    pesquisa2.setCampo("tipoEquip");
                    pesquisa2.setValor(3L);
                    pesquisa2.setTipo(1);
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
                        pesq1.setCampo("codEquipImpleMM");
                        pesq1.setValor(impl);
                        pesq1.setTipo(1);
                        listaPesq2.add(pesq1);

                        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
                        pesq2.setCampo("idApontImpleMM");
                        pesq2.setValor(0);
                        pesq2.setTipo(1);
                        listaPesq2.add(pesq2);

                        ImpleMMTO impleMMTO = new ImpleMMTO();
                        implementoList = impleMMTO.get(listaPesq2);
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
                            pesq1.setCampo("posImpleMM");
                            pesq1.setValor(1L);
                            pesq1.setTipo(1);
                            listaPesq.add(pesq1);

                            EspecificaPesquisa pesq2 = new EspecificaPesquisa();
                            pesq2.setCampo("idApontImpleMM");
                            pesq2.setValor(0);
                            pesq2.setTipo(1);
                            listaPesq.add(pesq2);

                            ImpleMMTO implemento1TO = new ImpleMMTO();
                            implementoList = implemento1TO.get(listaPesq);

                            for (int i = 0; i < implementoList.size(); i++) {
                                implemento1TO = (ImpleMMTO) implementoList.get(i);
                                implemento1TO.delete();
                            }
                            implementoList.clear();
                            listaPesq.clear();

                            implemento1TO.setPosImpleMM(1L);
                            implemento1TO.setCodEquipImpleMM(impl);
                            implemento1TO.setIdApontImpleMM(0L);
                            implemento1TO.insert();

                            if (impl > 0) {
                                pmmContext.setContImplemento(pmmContext.getContImplemento() + 1);
                                it = new Intent(ImplementoActivity.this, ImplementoActivity.class);
                                startActivity(it);
                                finish();
                            } else {
                                finalImpl();
                            }

                            break;

                        case 2:

                            EspecificaPesquisa pesq3 = new EspecificaPesquisa();
                            pesq3.setCampo("posImpleMM");
                            pesq3.setValor(2L);
                            pesq3.setTipo(1);
                            listaPesq.add(pesq3);

                            EspecificaPesquisa pesq4 = new EspecificaPesquisa();
                            pesq4.setCampo("idApontImpleMM");
                            pesq4.setValor(0);
                            pesq4.setTipo(1);
                            listaPesq.add(pesq4);

                            ImpleMMTO implemento2TO = new ImpleMMTO();
                            implementoList = implemento2TO.get(listaPesq);

                            for (int i = 0; i < implementoList.size(); i++) {
                                implemento2TO = (ImpleMMTO) implementoList.get(i);
                                implemento2TO.delete();
                            }
                            implementoList.clear();
                            listaPesq.clear();

                            implemento2TO.setPosImpleMM(2L);
                            implemento2TO.setCodEquipImpleMM(impl);
                            implemento2TO.setIdApontImpleMM(0L);
                            implemento2TO.insert();

                            finalImpl();

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

    public void finalImpl() {

        if (pmmContext.getVerPosTela() == 1) {

            configTO = new ConfigTO();
            List listConfigTO = configTO.all();
            configTO = (ConfigTO) listConfigTO.get(0);
            listConfigTO.clear();

            equipTO = new EquipTO();
            List listEquipTO = equipTO.get("idEquip", pmmContext.getBoletimMMTO().getIdEquipBolMM());
            equipTO = (EquipTO) listEquipTO.get(0);
            listEquipTO.clear();

            TurnoTO turnoTO = new TurnoTO();
            List turnoList = turnoTO.get("idTurno", pmmContext.getBoletimMMTO().getIdTurnoBolMM());
            turnoTO = (TurnoTO) turnoList.get(0);

            if ((equipTO.getIdCheckList() > 0) &&
                    ((configTO.getUltTurnoCLConfig() != turnoTO.getIdTurno())
                            || ((configTO.getUltTurnoCLConfig() == turnoTO.getIdTurno())
                            && (!configTO.getDtUltCLConfig().equals(Tempo.getInstance().dataSHora()))))) {

                pmmContext.getBoletimMMTO().setStatusBolMM(1L);
                ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), true, 0D, 0D);
                ManipDadosEnvio.getInstance().envioDadosPrinc();

                ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                List itemCheckList = itemCheckListTO.get("idCheckList", equipTO.getIdCheckList());
                Long qtde = (long) itemCheckList.size();
                itemCheckList.clear();

                CabecCLTO cabecCLTO = new CabecCLTO();
                cabecCLTO.setDtCabCL(Tempo.getInstance().datahora());
                EquipTO equipTO = new EquipTO();
                List equipList = equipTO.get("idEquip", configTO.getEquipConfig());
                equipTO = (EquipTO) equipList.get(0);
                equipList.clear();
                cabecCLTO.setEquipCabCL(equipTO.getNroEquip());
                cabecCLTO.setFuncCabCL(pmmContext.getBoletimMMTO().getMatricFuncBolMM());
                cabecCLTO.setTurnoCabCL(pmmContext.getBoletimMMTO().getIdTurnoBolMM());
                cabecCLTO.setQtdeItemCabCL(qtde);
                cabecCLTO.setStatusCabCL(1L);
                cabecCLTO.setDtAtualCabCL("0");
                cabecCLTO.insert();

                pmmContext.setPosCheckList(1L);
                Intent it = new Intent(ImplementoActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();

            } else {

                configTO.setHorimetroConfig(pmmContext.getBoletimMMTO().getHodometroInicialBolMM());
                configTO.update();
                configTO.commit();
                pmmContext.getBoletimMMTO().setStatusBolMM(1L);
                ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), false, 0D, 0D);
                ManipDadosEnvio.getInstance().envioDadosPrinc();

                Intent it = new Intent(ImplementoActivity.this, EsperaDadosOperActivity.class);
                startActivity(it);
                finish();


            }

        } else {

            Intent it = new Intent(ImplementoActivity.this, EsperaDadosOperActivity.class);
            startActivity(it);
            finish();

        }

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
