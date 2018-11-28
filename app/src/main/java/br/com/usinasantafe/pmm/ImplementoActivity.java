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

                if (!editTextPadrao.getText().toString().equals("")) {
                    impl = Long.parseLong(editTextPadrao.getText().toString());

                    ArrayList listaPesq = new ArrayList();
                    EquipSegTO equipSegTO = new EquipSegTO();

                    EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                    pesquisa.setCampo("codEquip");
                    pesquisa.setValor(Long.parseLong(editTextPadrao.getText().toString()));
                    listaPesq.add(pesquisa);

                    EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                    pesquisa2.setCampo("tipoEquip");
                    pesquisa2.setValor(3L);
                    listaPesq.add(pesquisa2);

                    List equipSegList = equipSegTO.get(listaPesq);
                    if(equipSegList.size() == 0){

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

                    }

                }

                if(verif) {

                    Intent it;
                    ImplementoTO implementoTO = new ImplementoTO();
                    List implementoList;
                    ArrayList listaPesq = new ArrayList();
                    switch (pmmContext.getContImplemento()) {
                        case 1:

                            EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                            pesquisa.setCampo("posImplemento");
                            pesquisa.setValor(1L);
                            listaPesq.add(pesquisa);

                            EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                            pesquisa2.setCampo("idApontImplemento");
                            pesquisa2.setValor(0);
                            listaPesq.add(pesquisa2);

                            implementoList = implementoTO.get(listaPesq);

                            for (int i = 0; i < implementoList.size(); i++) {
                                implementoTO = (ImplementoTO) implementoList.get(i);
                                implementoTO.delete();
                            }
                            implementoList.clear();
                            listaPesq.clear();

                            implementoTO.setPosImplemento(1L);
                            implementoTO.setCodEquipImplemento(impl);
                            implementoTO.setIdApontImplemento(0L);
                            implementoTO.insert();

                            pmmContext.setContImplemento(pmmContext.getContImplemento() + 1);
                            it = new Intent(ImplementoActivity.this, ImplementoActivity.class);
                            startActivity(it);
                            finish();
                            break;

                        case 2:

                            EspecificaPesquisa pesq = new EspecificaPesquisa();
                            pesq.setCampo("posImplemento");
                            pesq.setValor(1L);
                            listaPesq.add(pesq);

                            EspecificaPesquisa pesq2 = new EspecificaPesquisa();
                            pesq2.setCampo("idApontImplemento");
                            pesq2.setValor(0);
                            listaPesq.add(pesq2);

                            implementoList = implementoTO.get(listaPesq);
                            implementoTO = (ImplementoTO) implementoList.get(0);
                            implementoList.clear();
                            listaPesq.clear();

                            if(!impl.equals(implementoTO.getCodEquipImplemento())){

                                EspecificaPesquisa pesquisa3 = new EspecificaPesquisa();
                                pesquisa3.setCampo("posImplemento");
                                pesquisa3.setValor(2L);
                                listaPesq.add(pesquisa3);

                                EspecificaPesquisa pesquisa4 = new EspecificaPesquisa();
                                pesquisa4.setCampo("idApontImplemento");
                                pesquisa4.setValor(0);
                                listaPesq.add(pesquisa4);

                                implementoList = implementoTO.get(listaPesq);

                                for (int i = 0; i < implementoList.size(); i++) {
                                    implementoTO = (ImplementoTO) implementoList.get(i);
                                    implementoTO.delete();
                                }
                                implementoList.clear();
                                listaPesq.clear();

                                implementoTO.setPosImplemento(2L);
                                implementoTO.setCodEquipImplemento(impl);
                                implementoTO.setIdApontImplemento(0L);
                                implementoTO.insert();

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

                                    if ((equipTO.getIdChecklist() > 0) && (configTO.getUltTurnoCLConfig() != turnoTO.getIdTurno())) {

                                        pmmContext.getBoletimMMTO().setStatusBoletim(1L);
                                        ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), true);
                                        ManipDadosEnvio.getInstance().envioDadosPrinc();

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
                                        ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), false);
                                        ManipDadosEnvio.getInstance().envioDadosPrinc();
                                        it = new Intent(ImplementoActivity.this, MenuPrincNormalActivity.class);
                                        startActivity(it);
                                        finish();

                                    }


                                } else {

                                    it = new Intent(ImplementoActivity.this, MenuPrincNormalActivity.class);
                                    startActivity(it);
                                    finish();


                                }

                            }
                            else{

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

    public void onBackPressed()  {
        if (pmmContext.getContImplemento() == 1) {
            if(pmmContext.getVerPosTela() == 1){
                Intent it = new Intent(ImplementoActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            }
            else if(pmmContext.getContImplemento() == 3){
                Intent it = new Intent(ImplementoActivity.this, ListaParadaActivity.class);
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
