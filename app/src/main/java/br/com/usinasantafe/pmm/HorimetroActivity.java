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
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RecolMangTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RendimentoTO;

public class HorimetroActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ConfiguracaoTO configTO;
    private Double horimetroNum;
    private AtividadeTO atividadeTO;

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
                if(!editTextPadrao.getText().toString().equals("")){

                    String horimetro = editTextPadrao.getText().toString();
                    horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                    Long ativPrinc = pmmContext.getBoletimMMTO().getAtivPrincBoletim();

                    atividadeTO = new AtividadeTO();
                    List lAtiv = atividadeTO.get("idAtiv", ativPrinc);
                    atividadeTO = (AtividadeTO) lAtiv.get(0);
                    lAtiv.clear();

                    configTO = new ConfiguracaoTO();
                    List listConfigTO = configTO.all();
                    configTO = (ConfiguracaoTO) listConfigTO.get(0);
                    listConfigTO.clear();

                    if(pmmContext.getVerPosTela() == 1) {

                        if (horimetroNum >= configTO.getHorimetroConfig()) {

                            salvarBoletim();

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("O HORIMETRO DIGITADO " + horimetroNum + " É MENOR QUE O HORIMETRO ANTERIOR DA MAQUINA " + configTO.getHorimetroConfig() + ". DESEJA MANTER ESSE VALOR?");

                            alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    salvarBoletim();
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
                    else if(pmmContext.getVerPosTela() == 4) {

                        if (horimetroNum >= configTO.getHorimetroConfig()) {

                            BoletimMMTO boletimMMTO = new BoletimMMTO();
                            List listBoletim = boletimMMTO.get("statusBoletim", 1L);
                            boletimMMTO = (BoletimMMTO) listBoletim.get(0);
                            listBoletim.clear();

                            boletimMMTO.setHodometroFinalBoletim(horimetroNum);
                            boletimMMTO.update();

                            configTO.setHorimetroConfig(horimetroNum);
                            configTO.setDtUltApontConfig("");
                            configTO.update();
                            configTO.commit();

                            RendimentoTO rendimentoTO = new RendimentoTO();
                            List rendimentoList = rendimentoTO.get("idBolRendimento", boletimMMTO.getIdBoletim());

                            if (rendimentoList.size() > 0) {

                                pmmContext.setContRendimento(1);
                                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);
                                startActivity(it);
                                finish();

                            } else {

                                ManipDadosEnvio.getInstance().salvaBoletimFechado();
                                ManipDadosEnvio.getInstance().envioDadosPrinc();
                                Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                                startActivity(it);
                                finish();

                            }

                        }
                        else{


                            AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("O VALOR DO HORIMETRO DIGITADO " + horimetroNum + " É MENOR DO QUE O INICIAL " + configTO.getHorimetroConfig() + ". DESEJA MANTER ESSE VALOR?");

                            alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    BoletimMMTO boletimMMTO = new BoletimMMTO();
                                    List listBoletim = boletimMMTO.get("statusBoletim", 1L);
                                    boletimMMTO = (BoletimMMTO) listBoletim.get(0);
                                    listBoletim.clear();

                                    boletimMMTO.setHodometroFinalBoletim(horimetroNum);
                                    boletimMMTO.update();

                                    configTO.setHorimetroConfig(horimetroNum);
                                    configTO.setDtUltApontConfig("");
                                    configTO.update();
                                    configTO.commit();

                                    RendimentoTO rendimentoTO = new RendimentoTO();
                                    List rendimentoList = rendimentoTO.get("idBolRendimento", boletimMMTO.getIdBoletim());

                                    if (rendimentoList.size() > 0) {

                                        pmmContext.setContRendimento(1);
                                        Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);
                                        startActivity(it);
                                        finish();

                                    } else {

                                        ManipDadosEnvio.getInstance().salvaBoletimFechado();
                                        ManipDadosEnvio.getInstance().envioDadosPrinc();
                                        Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                                        startActivity(it);
                                        finish();

                                    }

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
                    else  if(pmmContext.getVerPosTela() == 10) {

                        BoletimMMTO boletimMMTO = new BoletimMMTO();
                        List listBoletim = boletimMMTO.get("statusBoletim", 1L);
                        boletimMMTO = (BoletimMMTO) listBoletim.get(0);
                        listBoletim.clear();

                        if (horimetroNum >= configTO.getHorimetroConfig()) {

                            boletimMMTO.setHodometroFinalBoletim(horimetroNum);
                            boletimMMTO.update();

                            configTO.setHorimetroConfig(horimetroNum);
                            configTO.setDtUltApontConfig("");
                            configTO.update();
                            configTO.commit();

                            RecolMangTO recolMangTO = new RecolMangTO();
                            List rendMangRecolList = recolMangTO.get("statusRendMangRecol", 1L);

                            if (rendMangRecolList.size() > 0) {

                                pmmContext.setContRecolMangFert(1);
                                Intent it = new Intent(HorimetroActivity.this, RecolMangFertActivity.class);
                                startActivity(it);
                                finish();

                            } else {

                                ManipDadosEnvio.getInstance().salvaBoletimFechado();
                                ManipDadosEnvio.getInstance().envioDadosPrinc();
                                Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                                startActivity(it);
                                finish();

                            }

                        }
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("O VALOR DO HORIMETRO DIGITADO " + horimetroNum + " É MENOR DO QUE O INICIAL " + configTO.getHorimetroConfig() + "! POR FAVOR, CORRIJA O VALOR DIGITADO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void salvarBoletim(){

        pmmContext.getBoletimMMTO().setHodometroInicialBoletim(horimetroNum);
        pmmContext.getBoletimMMTO().setHodometroFinalBoletim(0D);
        pmmContext.getBoletimMMTO().setIdExtBoletim(0L);

        if (atividadeTO.getFlagImplemento() == 1) {

            pmmContext.setContImplemento(1);
            Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);
            startActivity(it);
            finish();

        } else {

            EquipTO equipTO = new EquipTO();
            List equipList = equipTO.get("idEquip", pmmContext.getBoletimMMTO().getCodEquipBoletim());
            equipTO = (EquipTO) equipList.get(0);
            equipList.clear();

            if (equipTO.getTipoEquipFert() > 2) {

                Intent it = new Intent(HorimetroActivity.this, ListaAcoplaCarretelActivity.class);
                startActivity(it);
                finish();

            } else {

                TurnoTO turnoTO = new TurnoTO();
                List turnoList = turnoTO.get("idTurno", pmmContext.getBoletimMMTO().getCodTurnoBoletim());
                turnoTO = (TurnoTO) turnoList.get(0);

                if ((equipTO.getIdChecklist() > 0) && (configTO.getUltTurnoCLConfig() != turnoTO.getIdTurno())) {

                    pmmContext.getBoletimMMTO().setStatusBoletim(1L);
                    ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), true);
                    ManipDadosEnvio.getInstance().envioDadosPrinc();

                    pmmContext.setPosChecklist(1L);

                    if(pmmContext.getVerAtualCL().equals("N_AC")) {

                        Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                        List itemCheckList =  itemCheckListTO.get("idChecklist", equipTO.getIdChecklist());
                        Long qtde = (long) itemCheckList.size();
                        itemCheckList.clear();

                        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
                        cabecCheckListTO.setDtCabecCheckList(Tempo.getInstance().data());
                        cabecCheckListTO.setEquipCabecCheckList(configTO.getEquipConfig());
                        cabecCheckListTO.setFuncCabecCheckList(pmmContext.getBoletimMMTO().getCodMotoBoletim());
                        cabecCheckListTO.setTurnoCabecCheckList(pmmContext.getBoletimMMTO().getCodTurnoBoletim());
                        cabecCheckListTO.setQtdeItemCabecCheckList(qtde);
                        cabecCheckListTO.setStatusCabecCheckList(1L);
                        cabecCheckListTO.setDtAtualCheckList("0");
                        cabecCheckListTO.insert();

                        Intent it = new Intent(HorimetroActivity.this, ItemChecklistActivity.class);
                        startActivity(it);
                        finish();

                    }

                }
                else{

                    configTO.setHorimetroConfig(horimetroNum);
                    configTO.update();
                    configTO.commit();
                    pmmContext.getBoletimMMTO().setStatusBoletim(1L);
                    ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), false);
                    ManipDadosEnvio.getInstance().envioDadosPrinc();
                    Intent it = new Intent(HorimetroActivity.this, MenuPrincNormalActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        }

    }

    public void onBackPressed()  {
        if(pmmContext.getVerPosTela() == 1){
            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        }
        else{
            Intent it = new Intent(HorimetroActivity.this, MenuPrincNormalActivity.class);
            startActivity(it);
            finish();
        }
    }

}
