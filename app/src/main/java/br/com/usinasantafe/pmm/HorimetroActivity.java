package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.tb.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.TurnoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RecolhimentoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RendimentoTO;

public class HorimetroActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ConfiguracaoTO configTO;
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
                // TODO Auto-generated method stub
                if (!editTextPadrao.getText().toString().equals("")) {

                    String horimetro = editTextPadrao.getText().toString();
                    horimetroNum = Double.valueOf(horimetro.replace(",", "."));



                    configTO = new ConfiguracaoTO();
                    List listConfigTO = configTO.all();
                    configTO = (ConfiguracaoTO) listConfigTO.get(0);
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
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void salvarBoletimAberto() {

        Long ativPrinc;
        if(pmmContext.getTipoEquip() == 1) {
            ativPrinc = pmmContext.getBoletimMMTO().getAtivPrincBoletim();
            pmmContext.getBoletimMMTO().setHodometroInicialBoletim(horimetroNum);
            pmmContext.getBoletimMMTO().setHodometroFinalBoletim(0D);
            pmmContext.getBoletimMMTO().setIdExtBoletim(0L);
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

        if (atividadeTO.getFlagImplemento() == 1) {

            pmmContext.setContImplemento(1);
            Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);
            startActivity(it);
            finish();

        } else {

            configTO.setHorimetroConfig(horimetroNum);
            configTO.update();
            configTO.commit();

            if(pmmContext.getTipoEquip() == 1) {
                pmmContext.getBoletimMMTO().setStatusBoletim(1L);
                ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), true, getLatitude(), getLongitude());
            }
            else{
                pmmContext.getBoletimFertTO().setStatusBolFert(1L);
                ManipDadosEnvio.getInstance().salvaBoletimAbertoFert(pmmContext.getBoletimMMTO(), true, getLatitude(), getLongitude());
            }

            ManipDadosEnvio.getInstance().envioDadosPrinc();

            Long equip;
            Long turno;
            if(pmmContext.getTipoEquip() == 1) {
                equip = pmmContext.getBoletimMMTO().getCodEquipBoletim();
                turno = pmmContext.getBoletimMMTO().getCodTurnoBoletim();
            }
            else{
                equip = pmmContext.getBoletimFertTO().getCodEquipBolFert();
                turno = pmmContext.getBoletimFertTO().getCodTurnoBolFert();
            }

            EquipTO equipTO = new EquipTO();
            List equipList = equipTO.get("idEquip", equip);
            equipTO = (EquipTO) equipList.get(0);
            equipList.clear();

            TurnoTO turnoTO = new TurnoTO();
            List turnoList = turnoTO.get("idTurno", turno);
            turnoTO = (TurnoTO) turnoList.get(0);

            if ((equipTO.getIdChecklist() > 0) &&
                    ((configTO.getUltTurnoCLConfig() != turnoTO.getIdTurno())
                            || ((configTO.getUltTurnoCLConfig() == turnoTO.getIdTurno())
                            && (!configTO.getDtUltCLConfig().equals(Tempo.getInstance().dataSHora()))))) {

                pmmContext.setPosChecklist(1L);

                if (pmmContext.getVerAtualCL().equals("N_AC")) {

                    Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                    List itemCheckList = itemCheckListTO.get("idChecklist", equipTO.getIdChecklist());
                    Long qtde = (long) itemCheckList.size();
                    itemCheckList.clear();

                    CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
                    cabecCheckListTO.setDtCab(Tempo.getInstance().datahora());
                    cabecCheckListTO.setEquipCab(equipTO.getCodEquip());
                    cabecCheckListTO.setFuncCab(pmmContext.getBoletimMMTO().getCodMotoBoletim());
                    cabecCheckListTO.setTurnoCab(pmmContext.getBoletimMMTO().getCodTurnoBoletim());
                    cabecCheckListTO.setQtdeItemCab(qtde);
                    cabecCheckListTO.setStatusCab(1L);
                    cabecCheckListTO.setDtAtualCab("0");
                    cabecCheckListTO.insert();

                    Intent it = new Intent(HorimetroActivity.this, ItemChecklistActivity.class);
                    startActivity(it);
                    finish();

                }

            } else {

//                    GRAFICO
//                    Intent it = new Intent(HorimetroActivity.this, EsperaGrafActivity.class);
//                    startActivity(it);
//                    finish();

//                    ANTIGO SEM GRAFICO
                Intent it = new Intent(HorimetroActivity.this, MenuPrincNormalActivity.class);
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
            List boletimList = boletimMMTO.get("statusBoletim", 1L);
            boletimMMTO = (BoletimMMTO) boletimList.get(0);
            boletimList.clear();

            boletimMMTO.setHodometroFinalBoletim(horimetroNum);
            boletimMMTO.update();

            RendimentoTO rendimentoTO = new RendimentoTO();
            List rendimentoList = rendimentoTO.get("idBolRendimento", boletimMMTO.getIdBoletim());

            if (rendimentoList.size() > 0) {
                pmmContext.setContRendimento(1);
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

            RecolhimentoTO recolhimentoTO = new RecolhimentoTO();
            List recolhimentoList = recolhimentoTO.get("idBolRecol", boletimFertTO.getIdBolFert());

            if (recolhimentoList.size() > 0) {
                pmmContext.setContRecolhimento(1);
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
