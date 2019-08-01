package br.com.usinasantafe.pmm;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.ManipDadosReceb;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.MotoristaTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.OSTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.PneuTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ROSAtivTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.AtualAplicTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ImplementoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RendimentoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RespItemCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.TransbordoTO;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView lista;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private ConfigTO configTO;
    private EquipTO equipTO;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pmmContext = (PMMContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        teste();

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        configTO = new ConfigTO();
        List configList = configTO.all();

        if (configList.size() > 0) {

            configTO = (ConfigTO) configList.get(0);

            equipTO = new EquipTO();
            List listEquipTO = equipTO.get("idEquip", configTO.getEquipConfig());
            equipTO = (EquipTO) listEquipTO.get(0);
            listEquipTO.clear();

            if ((equipTO.getTipoEquipFert() == 1) || (equipTO.getTipoEquipFert() == 2)) {
                pmmContext.setTipoEquip(2);
            } else {
                pmmContext.setTipoEquip(1);
            }

            progressBar = new ProgressDialog(this);

            ConexaoWeb conexaoWeb = new ConexaoWeb();
            if (conexaoWeb.verificaConexao(this)) {

                if (configList.size() > 0) {

                    progressBar.setCancelable(true);
                    progressBar.setMessage("Buscando Atualização...");
                    progressBar.show();

                    AtualAplicTO atualAplicTO = new AtualAplicTO();
                    atualAplicTO.setVersaoAtual(pmmContext.versaoAplic);

                    atualAplicTO.setIdEquipAtualizacao(equipTO.getNroEquip());
                    atualAplicTO.setIdCheckList(equipTO.getIdChecklist());

                    ManipDadosVerif.getInstance().verAtualizacao(atualAplicTO, this, progressBar);
                }

            } else {
                if (configList.size() > 0) {
                    startTimer("N_NAC");
                }
            }

            configList.clear();

            List boletimList;
            if (pmmContext.getTipoEquip() == 1) {
                BoletimMMTO boletimMMTO = new BoletimMMTO();
                boletimList = boletimMMTO.get("statusBoletim", 1L);
            } else {
                BoletimFertTO boletimFertTO = new BoletimFertTO();
                boletimList = boletimFertTO.get("statusBolFert", 1L);
            }

            if (boletimList.size() > 0) {

                CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
                List cabecList = cabecCheckListTO.get("statusCab", 1L);

                if (pmmContext.getTipoEquip() == 1) {
                    pmmContext.setBoletimMMTO((BoletimMMTO) boletimList.get(0));
                } else {
                    pmmContext.setBoletimFertTO((BoletimFertTO) boletimList.get(0));
                }

                if (cabecList.size() == 0) {

                    if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }

                    Intent it = new Intent(MenuInicialActivity.this, MenuPrincNormalActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();

                    if (respItemCheckListTO.hasElements()) {
                        cabecCheckListTO = (CabecCheckListTO) cabecList.get(0);
                        List respList = respItemCheckListTO.get("idCabIt", cabecCheckListTO.getIdCab());
                        for (int i = 0; i < respList.size(); i++) {
                            respItemCheckListTO = (RespItemCheckListTO) respList.get(i);
                            respItemCheckListTO.delete();
                        }
                    }

                    if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }

                    pmmContext.setPosChecklist(1L);
                    Intent it = new Intent(MenuInicialActivity.this, ItemCheckListActivity.class);
                    startActivity(it);
                    finish();

                }

                cabecList.clear();

            }

            boletimList.clear();

        }

        listarMenuInicial();

    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("BOLETIM");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");
        itens.add("REENVIO DE DADOS");
        itens.add("ATUALIZAR APLICATIVO");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaMenuInicial);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("BOLETIM")) {
                    MotoristaTO motoristaTO = new MotoristaTO();
                    if (motoristaTO.hasElements() && configTO.hasElements()) {
                        pmmContext.setVerPosTela(1);
                        clearBD();
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuInicialActivity.this, OperadorActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("CONFIGURAÇÃO")) {
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("SAIR")) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if (text.equals("ATUALIZAR DADOS")) {

                    ConexaoWeb conexaoWeb = new ConexaoWeb();

                    if (conexaoWeb.verificaConexao(MenuInicialActivity.this)) {
                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();
                        ManipDadosReceb.getInstance().atualizarBD(progressBar);
                        ManipDadosReceb.getInstance().setContext(MenuInicialActivity.this);
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }

                } else if (text.equals("REENVIO DE DADOS")) {

                    ManipDadosEnvio.getInstance().envioDados(MenuInicialActivity.this);

                } else if (text.equals("ATUALIZAR APLICATIVO")) {

                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                    if (conexaoWeb.verificaConexao(v.getContext())) {

                        if (configTO.hasElements()) {

                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Buscando Atualização...");
                            progressBar.show();

                            AtualAplicTO atualAplicTO = new AtualAplicTO();
                            atualAplicTO.setIdEquipAtualizacao(configTO.getEquipConfig());
                            atualAplicTO.setVersaoAtual(pmmContext.versaoAplic);
                            ManipDadosVerif.getInstance().verAtualizacao(atualAplicTO, MenuInicialActivity.this, progressBar);
                        }
                    }

                }

            }

        });

    }

    public void startTimer(String verAtual) {

        Log.i("PMM", "VERATUAL = " + verAtual);

        String verAtualCL;
        if(verAtual.equals("N_NAC")){
            verAtualCL = verAtual;
        }
        else{
            int pos1 = verAtual.indexOf("#") + 1;
            verAtualCL = verAtual.substring(0, (pos1 - 1));
            String dthr = verAtual.substring(pos1);
            configTO = new ConfigTO();
            List configList = configTO.all();
            configTO = (ConfigTO) configList.get(0);
            configList.clear();
            configTO.setDtServConfig(dthr);
            configTO.update();
        }

        pmmContext.setVerAtualCL(verAtualCL);

        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }

        if (alarmeAtivo) {

            Log.i("PMM", "NOVO TIMER");

            Intent intent = new Intent("EXECUTAR_ALARME");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        } else {
            Log.i("PMM", "TIMER já ativo");
        }
    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }


    public void onBackPressed() {
    }


    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            ConfigTO configTO = new ConfigTO();
            List configList = configTO.all();
            if (configList.size() > 0) {
                if (ManipDadosEnvio.getInstance().getStatusEnvio() == 1) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 2) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            } else {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Aparelho sem Equipamento");
            }
            customHandler.postDelayed(this, 10000);
        }
    };


    public void teste() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = boletimMMTO.all();

        Log.i("PMM", "AKI");

        for (int i = 0; i < boletimMMList.size(); i++) {

            boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            Log.i("PMM", "BOLETIM MM");
            Log.i("PMM", "idBoletim = " + boletimMMTO.getIdBoletim());
            Log.i("PMM", "idExtBoletim = " + boletimMMTO.getIdExtBoletim());
            Log.i("PMM", "codMotoBoletim = " + boletimMMTO.getCodMotoBoletim());
            Log.i("PMM", "codEquipBoletim = " + boletimMMTO.getCodEquipBoletim());
            Log.i("PMM", "codTurnoBoletim = " + boletimMMTO.getCodTurnoBoletim());
            Log.i("PMM", "hodometroInicialBoletim = " + boletimMMTO.getHodometroInicialBoletim());
            Log.i("PMM", "hodometroFinalBoletim = " + boletimMMTO.getHodometroFinalBoletim());
            Log.i("PMM", "osBoletim = " + boletimMMTO.getOsBoletim());
            Log.i("PMM", "ativPrincBoletim = " + boletimMMTO.getAtivPrincBoletim());
            Log.i("PMM", "dthrInicioBoletim = " + boletimMMTO.getDthrInicioBoletim());
            Log.i("PMM", "dthrFimBoletim = " + boletimMMTO.getDthrFimBoletim());
            Log.i("PMM", "statusBoletim = " + boletimMMTO.getStatusBoletim());

        }

        ApontaMMTO apontaMMTO = new ApontaMMTO();
        List apontaMMList = apontaMMTO.all();

        for (int i = 0; i < apontaMMList.size(); i++) {

            apontaMMTO = (ApontaMMTO) apontaMMList.get(i);
            Log.i("PMM", "APONTAMENTO MM");
            Log.i("PMM", "idAponta = " + apontaMMTO.getIdAponta());
            Log.i("PMM", "idBolAponta = " + apontaMMTO.getIdBolAponta());
            Log.i("PMM", "idExtBolAponta = " + apontaMMTO.getIdExtBolAponta());
            Log.i("PMM", "osAponta = " + apontaMMTO.getOsAponta());
            Log.i("PMM", "atividadeAponta = " + apontaMMTO.getAtividadeAponta());
            Log.i("PMM", "paradaAponta = " + apontaMMTO.getParadaAponta());
            Log.i("PMM", "transbordoAponta = " + apontaMMTO.getTransbordoAponta());
            Log.i("PMM", "dthrAponta = " + apontaMMTO.getDthrAponta());

        }

        ImplementoTO implementoTO = new ImplementoTO();
        List implementoList = implementoTO.all();

        for (int l = 0; l < implementoList.size(); l++) {
            implementoTO = (ImplementoTO) implementoList.get(l);
            Log.i("PMM", "IMPLEMENTO");
            Log.i("PMM", "idImplemento = " + implementoTO.getIdImplemento());
            Log.i("PMM", "idApontImplemento = " + implementoTO.getIdApontImplemento());
            Log.i("PMM", "posImplemento = " + implementoTO.getPosImplemento());
            Log.i("PMM", "codEquipImplemento = " + implementoTO.getCodEquipImplemento());
        }

        RendimentoTO rendimentoTO = new RendimentoTO();
        List rendimentoList = rendimentoTO.all();

        for (int j = 0; j < rendimentoList.size(); j++) {
            rendimentoTO = (RendimentoTO) rendimentoList.get(j);
            Log.i("PMM", "RENDIMENTO");
            Log.i("PMM", "idRendimento = " + rendimentoTO.getIdRendimento());
            Log.i("PMM", "idBolRendimento = " + rendimentoTO.getIdBolRendimento());
            Log.i("PMM", "idExtBolRendimento = " + rendimentoTO.getIdExtBolRendimento());
            Log.i("PMM", "nroOSRendimento = " + rendimentoTO.getNroOSRendimento());
            Log.i("PMM", "valorRendimento = " + rendimentoTO.getValorRendimento());
            Log.i("PMM", "dthrRendimento = " + rendimentoTO.getDthrRendimento());
        }

        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.all();

        Log.i("PMM", "AKI");

        for (int i = 0; i < boletimFertList.size(); i++) {

            boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
            Log.i("PMM", "BOLETIM FERT");
            Log.i("PMM", "idBoletim = " + boletimFertTO.getIdBolFert());
            Log.i("PMM", "idExtBoletim = " + boletimFertTO.getIdExtBolFert());
            Log.i("PMM", "codMotoBoletim = " + boletimFertTO.getCodMotoBolFert());
            Log.i("PMM", "codEquipBoletim = " + boletimFertTO.getCodEquipBolFert());
            Log.i("PMM", "codTurnoBoletim = " + boletimFertTO.getCodTurnoBolFert());
            Log.i("PMM", "hodometroInicialBoletim = " + boletimFertTO.getHodometroInicialBolFert());
            Log.i("PMM", "hodometroFinalBoletim = " + boletimFertTO.getHodometroFinalBolFert());
            Log.i("PMM", "osBoletim = " + boletimFertTO.getOsBolFert());
            Log.i("PMM", "ativPrincBoletim = " + boletimFertTO.getAtivPrincBolFert());
            Log.i("PMM", "dthrInicioBoletim = " + boletimFertTO.getDthrInicioBolFert());
            Log.i("PMM", "dthrFimBoletim = " + boletimFertTO.getDthrFimBolFert());
            Log.i("PMM", "statusBoletim = " + boletimFertTO.getStatusBolFert());

        }

        ApontaFertTO apontaFertTO = new ApontaFertTO();
        List apontaAplicFertList = apontaFertTO.all();

        for (int j = 0; j < apontaAplicFertList.size(); j++) {
            apontaFertTO = (ApontaFertTO) apontaAplicFertList.get(j);

            Log.i("PMM", "APONTA FERT");
            Log.i("PMM", "idApontaAplicFert = " + apontaFertTO.getIdApontaFert());
            Log.i("PMM", "idBolApontaAplicFert = " + apontaFertTO.getIdBolApontaFert());
            Log.i("PMM", "idExtBolApontaAplicFert = " + apontaFertTO.getIdExtBolApontaFert());
            Log.i("PMM", "osApontaAplicFert = " + apontaFertTO.getOsApontaFert());
            Log.i("PMM", "ativApontaAplicFert = " + apontaFertTO.getAtivApontaFert());
            Log.i("PMM", "paradaApontaAplicFert = " + apontaFertTO.getParadaApontaFert());
            Log.i("PMM", "dthrApontaAplicFert = " + apontaFertTO.getDthrApontaFert());
            Log.i("PMM", "pressaoApontaAplicFert= " + apontaFertTO.getPressaoApontaFert());
            Log.i("PMM", "velocApontaAplicFert = " + apontaFertTO.getVelocApontaFert());
            Log.i("PMM", "bocalApontaAplicFert = " + apontaFertTO.getBocalApontaFert());

        }

        ConfigTO configTO = new ConfigTO();
        List configList = configTO.all();

        for (int j = 0; j < configList.size(); j++) {

            configTO = (ConfigTO) configList.get(j);

            Log.i("PMM", "Config");
            Log.i("PMM", "equipConfig = " + configTO.getEquipConfig());
            Log.i("PMM", "classeEquipConfig = " + configTO.getClasseEquipConfig());
            Log.i("PMM", "senhaConfig = " + configTO.getSenhaConfig());
            Log.i("PMM", "ultTurnoCLConfig = " + configTO.getUltTurnoCLConfig());
            Log.i("PMM", "dtUltApontConfig = " + configTO.getDtUltApontConfig());
            Log.i("PMM", "osConfig = " + configTO.getOsConfig());
            Log.i("PMM", "horimetroConfig = " + configTO.getHorimetroConfig());

        }

        EquipTO equipTO = new EquipTO();
        List equipTOList = equipTO.all();

        for (int j = 0; j < equipTOList.size(); j++) {

            equipTO = (EquipTO) equipTOList.get(j);

            Log.i("PMM", "Equipamento");
            Log.i("PMM", "idEquip = " + equipTO.getIdEquip());
            Log.i("PMM", "codEquip = " + equipTO.getNroEquip());
            Log.i("PMM", "codTurno = " + equipTO.getCodTurno());
            Log.i("PMM", "idChecklist = " + equipTO.getIdChecklist());
            Log.i("PMM", "tipoEquipFert = " + equipTO.getTipoEquipFert());

        }

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        List cabecList = cabecCheckListTO.all();

        for (int j = 0; j < cabecList.size(); j++) {

            cabecCheckListTO = (CabecCheckListTO) cabecList.get(j);

            Log.i("PMM", "CabecCheckList");
            Log.i("PMM", "IdCabecCheck = " + cabecCheckListTO.getIdCab());
            Log.i("PMM", "EquipCabecCheckList = " + cabecCheckListTO.getEquipCab());
            Log.i("PMM", "DtCabecCheckList = " + cabecCheckListTO.getDtCab());
            Log.i("PMM", "FuncCabecCheckList = " + cabecCheckListTO.getFuncCab());
            Log.i("PMM", "TurnoCabecCheckList = " + cabecCheckListTO.getTurnoCab());
            Log.i("PMM", "StatusCabecCheckList = " + cabecCheckListTO.getStatusCab());
            Log.i("PMM", "QtdeItemCabecCheckList = " + cabecCheckListTO.getQtdeItemCab());
            Log.i("PMM", "DtAtualCheckList = " + cabecCheckListTO.getDtAtualCab());

        }

        RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();
        List respItemList = respItemCheckListTO.all();

        for (int j = 0; j < respItemList.size(); j++) {

            respItemCheckListTO = (RespItemCheckListTO) respItemList.get(j);

            Log.i("PMM", "RespItemCheckList");
            Log.i("PMM", "IdItemCheckList = " + respItemCheckListTO.getIdIt());
            Log.i("PMM", "IdItItemCheckList = " + respItemCheckListTO.getIdItBDIt());
            Log.i("PMM", "IdCabecItemCheckList = " + respItemCheckListTO.getIdCabIt());
            Log.i("PMM", "OpcaoItemCheckList = " + respItemCheckListTO.getOpIt());

        }


        Log.i("PMM", "versão = " + PMMContext.versaoAplic);

    }

    public void clearBD() {

        TransbordoTO transbordoTO = new TransbordoTO();
        transbordoTO.deleteAll();

        ImplementoTO implementoTO = new ImplementoTO();
        List implementoList = implementoTO.get("idApontImplemento", 0L);

        for (int i = 0; i < implementoList.size(); i++) {
            implementoTO = (ImplementoTO) implementoList.get(i);
            implementoTO.delete();
        }

        OSTO osto = new OSTO();
        osto.deleteAll();

        ROSAtivTO rosAtivTO = new ROSAtivTO();
        rosAtivTO.deleteAll();

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        backupApontaTO.deleteAll();

    }

}
