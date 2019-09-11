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
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.FuncionarioTO;
import br.com.usinasantafe.pmm.to.estaticas.OSTO;
import br.com.usinasantafe.pmm.to.estaticas.ROSAtivTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.AtualAplicTO;
import br.com.usinasantafe.pmm.to.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.ImpleMMTO;
import br.com.usinasantafe.pmm.to.variaveis.RendMMTO;
import br.com.usinasantafe.pmm.to.variaveis.RespItemCLTO;
import br.com.usinasantafe.pmm.to.variaveis.TransbMMTO;

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
                    progressBar.setMessage("BUSCANDO ATUALIZAÇÃO...");
                    progressBar.show();

                    AtualAplicTO atualAplicTO = new AtualAplicTO();
                    atualAplicTO.setVersaoAtual(pmmContext.versaoAplic);

                    atualAplicTO.setIdEquipAtualizacao(equipTO.getNroEquip());
                    atualAplicTO.setIdCheckList(equipTO.getIdCheckList());

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
                boletimList = boletimMMTO.get("statusBolMM", 1L);
            } else {
                BoletimFertTO boletimFertTO = new BoletimFertTO();
                boletimList = boletimFertTO.get("statusBolFert", 1L);
            }

            if (boletimList.size() > 0) {

                CabecCLTO cabecCLTO = new CabecCLTO();
                List cabecList = cabecCLTO.get("statusCabCL", 1L);

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

                    RespItemCLTO respItemCLTO = new RespItemCLTO();

                    if (respItemCLTO.hasElements()) {
                        cabecCLTO = (CabecCLTO) cabecList.get(0);
                        List respList = respItemCLTO.get("idCabItCL", cabecCLTO.getIdCabCL());
                        for (int i = 0; i < respList.size(); i++) {
                            respItemCLTO = (RespItemCLTO) respList.get(i);
                            respItemCLTO.delete();
                        }
                    }

                    if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }

                    pmmContext.setPosCheckList(1L);
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
                    FuncionarioTO funcionarioTO = new FuncionarioTO();
                    if (funcionarioTO.hasElements() && configTO.hasElements()) {
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
            Log.i("PMM", "idBoletim = " + boletimMMTO.getIdBolMM());
            Log.i("PMM", "idExtBoletim = " + boletimMMTO.getIdExtBolMM());
            Log.i("PMM", "codMotoBoletim = " + boletimMMTO.getMatricFuncBolMM());
            Log.i("PMM", "codEquipBoletim = " + boletimMMTO.getIdEquipBolMM());
            Log.i("PMM", "codTurnoBoletim = " + boletimMMTO.getIdTurnoBolMM());
            Log.i("PMM", "hodometroInicialBoletim = " + boletimMMTO.getHodometroInicialBolMM());
            Log.i("PMM", "hodometroFinalBoletim = " + boletimMMTO.getHodometroFinalBolMM());
            Log.i("PMM", "osBoletim = " + boletimMMTO.getOsBolMM());
            Log.i("PMM", "ativPrincBoletim = " + boletimMMTO.getAtivPrincBolMM());
            Log.i("PMM", "dthrInicioBoletim = " + boletimMMTO.getDthrInicialBolMM());
            Log.i("PMM", "dthrFimBoletim = " + boletimMMTO.getDthrFinalBolMM());
            Log.i("PMM", "statusBoletim = " + boletimMMTO.getStatusBolMM());

        }

        ApontMMTO apontMMTO = new ApontMMTO();
        List apontaMMList = apontMMTO.all();

        for (int i = 0; i < apontaMMList.size(); i++) {

            apontMMTO = (ApontMMTO) apontaMMList.get(i);
            Log.i("PMM", "APONTAMENTO MM");
            Log.i("PMM", "idAponta = " + apontMMTO.getIdApontMM());
            Log.i("PMM", "idBolAponta = " + apontMMTO.getIdBolApontMM());
            Log.i("PMM", "idExtBolAponta = " + apontMMTO.getIdExtBolApontMM());
            Log.i("PMM", "osAponta = " + apontMMTO.getOsApontMM());
            Log.i("PMM", "atividadeAponta = " + apontMMTO.getAtivApontMM());
            Log.i("PMM", "paradaAponta = " + apontMMTO.getParadaApontMM());
            Log.i("PMM", "transbordoAponta = " + apontMMTO.getTransbApontMM());
            Log.i("PMM", "dthrAponta = " + apontMMTO.getDthrApontMM());

        }

        ImpleMMTO impleMMTO = new ImpleMMTO();
        List implementoList = impleMMTO.all();

        for (int l = 0; l < implementoList.size(); l++) {
            impleMMTO = (ImpleMMTO) implementoList.get(l);
            Log.i("PMM", "IMPLEMENTO");
            Log.i("PMM", "idImplemento = " + impleMMTO.getIdImpleMM());
            Log.i("PMM", "idApontImplemento = " + impleMMTO.getIdApontImpleMM());
            Log.i("PMM", "posImplemento = " + impleMMTO.getPosImpleMM());
            Log.i("PMM", "codEquipImplemento = " + impleMMTO.getCodEquipImpleMM());
        }

        RendMMTO rendMMTO = new RendMMTO();
        List rendimentoList = rendMMTO.all();

        for (int j = 0; j < rendimentoList.size(); j++) {
            rendMMTO = (RendMMTO) rendimentoList.get(j);
            Log.i("PMM", "RENDIMENTO");
            Log.i("PMM", "idRendimento = " + rendMMTO.getIdRendMM());
            Log.i("PMM", "idBolRendimento = " + rendMMTO.getIdBolRendMM());
            Log.i("PMM", "idExtBolRendimento = " + rendMMTO.getIdExtBolRendMM());
            Log.i("PMM", "nroOSRendimento = " + rendMMTO.getNroOSRendMM());
            Log.i("PMM", "valorRendimento = " + rendMMTO.getValorRendMM());
            Log.i("PMM", "dthrRendimento = " + rendMMTO.getDthrRendMM());
        }

        BoletimFertTO boletimFertTO = new BoletimFertTO();
        List boletimFertList = boletimFertTO.all();

        Log.i("PMM", "AKI");

        for (int i = 0; i < boletimFertList.size(); i++) {

            boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
            Log.i("PMM", "BOLETIM FERT");
            Log.i("PMM", "idBoletim = " + boletimFertTO.getIdBolFert());
            Log.i("PMM", "idExtBoletim = " + boletimFertTO.getIdExtBolFert());
            Log.i("PMM", "codMotoBoletim = " + boletimFertTO.getMatricFuncBolFert());
            Log.i("PMM", "codEquipBoletim = " + boletimFertTO.getIdEquipBolFert());
            Log.i("PMM", "codTurnoBoletim = " + boletimFertTO.getIdTurnoBolFert());
            Log.i("PMM", "hodometroInicialBoletim = " + boletimFertTO.getHodometroInicialBolFert());
            Log.i("PMM", "hodometroFinalBoletim = " + boletimFertTO.getHodometroFinalBolFert());
            Log.i("PMM", "osBoletim = " + boletimFertTO.getOsBolFert());
            Log.i("PMM", "ativPrincBoletim = " + boletimFertTO.getAtivPrincBolFert());
            Log.i("PMM", "dthrInicioBoletim = " + boletimFertTO.getDthrInicialBolFert());
            Log.i("PMM", "dthrFimBoletim = " + boletimFertTO.getDthrFinalBolFert());
            Log.i("PMM", "statusBoletim = " + boletimFertTO.getStatusBolFert());

        }

        ApontFertTO apontFertTO = new ApontFertTO();
        List apontaAplicFertList = apontFertTO.all();

        for (int j = 0; j < apontaAplicFertList.size(); j++) {
            apontFertTO = (ApontFertTO) apontaAplicFertList.get(j);

            Log.i("PMM", "APONTA FERT");
            Log.i("PMM", "idApontaAplicFert = " + apontFertTO.getIdApontFert());
            Log.i("PMM", "idBolApontaAplicFert = " + apontFertTO.getIdBolApontFert());
            Log.i("PMM", "idExtBolApontaAplicFert = " + apontFertTO.getIdExtBolApontFert());
            Log.i("PMM", "osApontaAplicFert = " + apontFertTO.getOsApontFert());
            Log.i("PMM", "ativApontaAplicFert = " + apontFertTO.getAtivApontFert());
            Log.i("PMM", "paradaApontaAplicFert = " + apontFertTO.getParadaApontFert());
            Log.i("PMM", "dthrApontaAplicFert = " + apontFertTO.getDthrApontFert());
            Log.i("PMM", "pressaoApontaAplicFert= " + apontFertTO.getPressaoApontFert());
            Log.i("PMM", "velocApontaAplicFert = " + apontFertTO.getVelocApontFert());
            Log.i("PMM", "bocalApontaAplicFert = " + apontFertTO.getBocalApontFert());

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
            Log.i("PMM", "idCheckList = " + equipTO.getIdCheckList());
            Log.i("PMM", "tipoEquipFert = " + equipTO.getTipoEquipFert());

        }

        CabecCLTO cabecCLTO = new CabecCLTO();
        List cabecList = cabecCLTO.all();

        for (int j = 0; j < cabecList.size(); j++) {

            cabecCLTO = (CabecCLTO) cabecList.get(j);

            Log.i("PMM", "CabecCheckList");
            Log.i("PMM", "IdCabecCheck = " + cabecCLTO.getIdCabCL());
            Log.i("PMM", "EquipCabecCheckList = " + cabecCLTO.getEquipCabCL());
            Log.i("PMM", "DtCabecCheckList = " + cabecCLTO.getDtCabCL());
            Log.i("PMM", "FuncCabecCheckList = " + cabecCLTO.getFuncCabCL());
            Log.i("PMM", "TurnoCabecCheckList = " + cabecCLTO.getTurnoCabCL());
            Log.i("PMM", "StatusCabecCheckList = " + cabecCLTO.getStatusCabCL());
            Log.i("PMM", "QtdeItemCabecCheckList = " + cabecCLTO.getQtdeItemCabCL());
            Log.i("PMM", "DtAtualCheckList = " + cabecCLTO.getDtAtualCabCL());

        }

        RespItemCLTO respItemCLTO = new RespItemCLTO();
        List respItemList = respItemCLTO.all();

        for (int j = 0; j < respItemList.size(); j++) {

            respItemCLTO = (RespItemCLTO) respItemList.get(j);

            Log.i("PMM", "RespItemCheckList");
            Log.i("PMM", "IdItemCheckList = " + respItemCLTO.getIdItCL());
            Log.i("PMM", "IdItItemCheckList = " + respItemCLTO.getIdItBDItCL());
            Log.i("PMM", "IdCabecItemCheckList = " + respItemCLTO.getIdCabItCL());
            Log.i("PMM", "OpcaoItemCheckList = " + respItemCLTO.getOpItCL());

        }


        Log.i("PMM", "versão = " + PMMContext.versaoAplic);

    }

    public void clearBD() {

        TransbMMTO transbMMTO = new TransbMMTO();
        transbMMTO.deleteAll();

        ImpleMMTO impleMMTO = new ImpleMMTO();
        List implementoList = impleMMTO.get("idApontImpleMM", 0L);

        for (int i = 0; i < implementoList.size(); i++) {
            impleMMTO = (ImpleMMTO) implementoList.get(i);
            impleMMTO.delete();
        }

        OSTO osto = new OSTO();
        osto.deleteAll();

        ROSAtivTO rosAtivTO = new ROSAtivTO();
        rosAtivTO.deleteAll();

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        backupApontaTO.deleteAll();

    }

}
