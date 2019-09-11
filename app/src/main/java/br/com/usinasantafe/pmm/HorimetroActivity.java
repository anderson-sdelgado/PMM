package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.estaticas.RFuncaoAtivParTO;
import br.com.usinasantafe.pmm.to.estaticas.TurnoTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.RecolhFertTO;
import br.com.usinasantafe.pmm.to.variaveis.RendMMTO;

public class HorimetroActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ConfigTO configTO;
    private Double horimetroNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horimetro);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkHorimetro = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancHorimetro = (Button) findViewById(R.id.buttonCancPadrao);

        TextView textViewHorimetro = (TextView) findViewById(R.id.textViewPadrao);

        textViewHorimetro.setText(pmmContext.getTextoHorimetro());

        buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String horimetro = editTextPadrao.getText().toString();
                    horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                    configTO = new ConfigTO();
                    List listConfigTO = configTO.all();
                    configTO = (ConfigTO) listConfigTO.get(0);
                    listConfigTO.clear();

                    if (pmmContext.getVerPosTela() == 1) {

                        if (horimetroNum >= configTO.getHorimetroConfig()) {

                            salvarBoletimAberto();

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("O HORIMETRO DIGITADO " + horimetroNum + " É MENOR QUE O HORIMETRO ANTERIOR DA MAQUINA " + configTO.getHorimetroConfig() + ". DESEJA MANTER ESSE VALOR?");

                            alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    salvarBoletimAberto();
                                }

                            });


                            alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }

                            });

                            alerta.show();

                        }

                    } else if (pmmContext.getVerPosTela() == 4) {

                        if (horimetroNum >= configTO.getHorimetroConfig()) {

                            salvarBoletimFechado();

                        } else {


                            AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("O VALOR DO HORIMETRO DIGITADO " + horimetroNum + " É MENOR DO QUE O INICIAL " + configTO.getHorimetroConfig() + ". DESEJA MANTER ESSE VALOR?");

                            alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    salvarBoletimFechado();

                                }

                            });


                            alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }

                            });

                            alerta.show();

                        }

                    }

                }

            }
        });

        buttonCancHorimetro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void salvarBoletimAberto() {

        Long ativPrinc;
        if(pmmContext.getTipoEquip() == 1) {
            ativPrinc = pmmContext.getBoletimMMTO().getAtivPrincBolMM();
            pmmContext.getBoletimMMTO().setHodometroInicialBolMM(horimetroNum);
            pmmContext.getBoletimMMTO().setHodometroFinalBolMM(0D);
            pmmContext.getBoletimMMTO().setIdExtBolMM(0L);
        }
        else{
            ativPrinc = pmmContext.getBoletimFertTO().getAtivPrincBolFert();
            pmmContext.getBoletimFertTO().setHodometroInicialBolFert(horimetroNum);
            pmmContext.getBoletimFertTO().setHodometroFinalBolFert(0D);
            pmmContext.getBoletimFertTO().setIdExtBolFert(0L);
        }

        AtividadeTO atividadeTO = new AtividadeTO();
        List lAtiv = atividadeTO.get("idAtiv", ativPrinc);
        atividadeTO = (AtividadeTO) lAtiv.get(0);
        lAtiv.clear();

        RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("idAtivPar");
        pesquisa1.setValor(atividadeTO.getIdAtiv());
        pesquisa1.setTipo(1);
        pesqList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("codFuncao");
        pesquisa2.setValor(3L);
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        EspecificaPesquisa pesquisa3 = new EspecificaPesquisa();
        pesquisa3.setCampo("tipoFuncao");
        pesquisa3.setValor(1L);
        pesquisa3.setTipo(1);
        pesqList.add(pesquisa3);

        List rFuncaoAtivParList = rFuncaoAtivParTO.get(pesqList);

        if (rFuncaoAtivParList.size() > 0) {

            pmmContext.setContImplemento(1);
            Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);
            startActivity(it);
            finish();

        } else {

            if(pmmContext.getTipoEquip() == 1) {

                configTO.setHorimetroConfig(horimetroNum);
                configTO.update();
                configTO.commit();

                pmmContext.getBoletimMMTO().setStatusBolMM(1L);

                Long equip;
                Long turno;
                if (pmmContext.getTipoEquip() == 1) {
                    equip = pmmContext.getBoletimMMTO().getIdEquipBolMM();
                    turno = pmmContext.getBoletimMMTO().getIdTurnoBolMM();
                } else {
                    equip = pmmContext.getBoletimFertTO().getIdEquipBolFert();
                    turno = pmmContext.getBoletimFertTO().getIdTurnoBolFert();
                }

                EquipTO equipTO = new EquipTO();
                List equipList = equipTO.get("idEquip", equip);
                equipTO = (EquipTO) equipList.get(0);
                equipList.clear();

                TurnoTO turnoTO = new TurnoTO();
                List turnoList = turnoTO.get("idTurno", turno);
                turnoTO = (TurnoTO) turnoList.get(0);

                if ((equipTO.getIdCheckList() > 0) &&
                        ((configTO.getUltTurnoCLConfig() != turnoTO.getIdTurno())
                                || ((configTO.getUltTurnoCLConfig() == turnoTO.getIdTurno())
                                && (!configTO.getDtUltCLConfig().equals(Tempo.getInstance().dataSHora()))))) {

                    ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), true, 0D, 0D);
                    ManipDadosEnvio.getInstance().envioDadosPrinc();

                    pmmContext.setPosCheckList(1L);

                    if (pmmContext.getVerAtualCL().equals("N_AC")) {

                        Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                        List itemCheckList = itemCheckListTO.get("idCheckList", equipTO.getIdCheckList());
                        Long qtde = (long) itemCheckList.size();
                        itemCheckList.clear();

                        CabecCLTO cabecCLTO = new CabecCLTO();
                        cabecCLTO.setDtCabCL(Tempo.getInstance().datahora());
                        cabecCLTO.setEquipCabCL(equipTO.getNroEquip());
                        cabecCLTO.setFuncCabCL(pmmContext.getBoletimMMTO().getMatricFuncBolMM());
                        cabecCLTO.setTurnoCabCL(pmmContext.getBoletimMMTO().getIdTurnoBolMM());
                        cabecCLTO.setQtdeItemCabCL(qtde);
                        cabecCLTO.setStatusCabCL(1L);
                        cabecCLTO.setDtAtualCabCL("0");
                        cabecCLTO.insert();

                        Intent it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);
                        startActivity(it);
                        finish();

                    }

                } else {

                    ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), false, 0D, 0D);
                    ManipDadosEnvio.getInstance().envioDadosPrinc();

                    Intent it = new Intent(HorimetroActivity.this, EsperaDadosOperActivity.class);
                    startActivity(it);
                    finish();

                }

            }
            else {

                Intent it = new Intent(HorimetroActivity.this, EquipMBActivity.class);
                startActivity(it);
                finish();

            }

        }

    }

    public void salvarBoletimFechado() {

        configTO.setHorimetroConfig(horimetroNum);
        configTO.setDtUltApontConfig("");
        configTO.update();
        configTO.commit();

        if(pmmContext.getTipoEquip() == 1) {

            BoletimMMTO boletimMMTO = new BoletimMMTO();
            List boletimList = boletimMMTO.get("statusBolMM", 1L);
            boletimMMTO = (BoletimMMTO) boletimList.get(0);
            boletimList.clear();

            boletimMMTO.setHodometroFinalBolMM(horimetroNum);
            boletimMMTO.update();

            RendMMTO rendMMTO = new RendMMTO();
            List rendimentoList = rendMMTO.get("idBolRendMM", boletimMMTO.getIdBolMM());

            if (rendimentoList.size() > 0) {
                pmmContext.setContRend(1);
                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                ManipDadosEnvio.getInstance().salvaBoletimFechadoMM();
                ManipDadosEnvio.getInstance().envioDadosPrinc();
                Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

            rendimentoList.clear();

        }
        else{

            BoletimFertTO boletimFertTO = new BoletimFertTO();
            List boletimList = boletimFertTO.get("statusBolFert", 1L);
            boletimFertTO = (BoletimFertTO) boletimList.get(0);
            boletimList.clear();

            boletimFertTO.setHodometroFinalBolFert(horimetroNum);
            boletimFertTO.update();

            RecolhFertTO recolhFertTO = new RecolhFertTO();
            List recolhimentoList = recolhFertTO.get("idBolRecolhMM", boletimFertTO.getIdBolFert());

            if (recolhimentoList.size() > 0) {
                pmmContext.setContRecolh(1);
                Intent it = new Intent(HorimetroActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                ManipDadosEnvio.getInstance().salvaBoletimFechadoFert();
                ManipDadosEnvio.getInstance().envioDadosPrinc();
                Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

            recolhimentoList.clear();

        }

    }

    public void onBackPressed() {
        if (pmmContext.getVerPosTela() == 1) {
            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        } else {
            Intent it = new Intent(HorimetroActivity.this, MenuPrincNormalActivity.class);
            startActivity(it);
            finish();
        }
    }

}
