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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pmm.util.ConexaoWeb;
import br.com.usinasantafe.pmm.model.bean.variaveis.ImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.VerifDadosServ;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BackupApontaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCLBean;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView listView;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private ConfigCTR configCTR;
    private CheckListCTR checkListCTR;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();
    private boolean verTela;

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

        progressBar = new ProgressDialog(this);
        checkListCTR = new CheckListCTR();
        configCTR = new ConfigCTR();

        if(pmmContext.getBoletimCTR().verBolAberto()){
            if(checkListCTR.verCabecAberto()){
                startTimer("N_NAC");
                checkListCTR.clearRespCabecAberto();
                pmmContext.setPosCheckList(1);
                Intent it = new Intent(MenuInicialActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();
            }
            else{
                if(pmmContext.getPneuCTR().verCalibAberto()){
                    startTimer("N_NAC");
                    Intent it = new Intent(MenuInicialActivity.this, ListaPosPneuActivity.class);
                    startActivity(it);
                    finish();
                }
                else {
                    verTela = true;
                    atualizarAplic();
                }
            }
        }
        else{
            verTela = false;
            atualizarAplic();
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
        listView = (ListView) findViewById(R.id.listaMenuInicial);
        listView.setAdapter(adapterList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("BOLETIM")) {
                    FuncBean funcBean = new FuncBean();
                    if (funcBean.hasElements() && configCTR.hasElements()) {
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

                        configCTR.atualTodasTabelas(MenuInicialActivity.this, progressBar);

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

                    EnvioDadosServ.getInstance().envioDados(MenuInicialActivity.this);

                } else if (text.equals("ATUALIZAR APLICATIVO")) {

                    atualizarAplic();

                }

            }

        });

    }

    public void atualizarAplic(){
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(this)) {
            if (configCTR.hasElements()) {
                progressBar.setCancelable(true);
                progressBar.setMessage("BUSCANDO ATUALIZAÇÃO...");
                progressBar.show();
                VerifDadosServ.getInstance().verAtualAplic(pmmContext.versaoAplic, this, progressBar);
            }
        } else {
            startTimer("N_NAC");
        }
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
            configCTR.setDtServConfig(dthr);
        }

        pmmContext.setVerAtualCL(verAtualCL);

        Intent intent = new Intent(this, ReceberAlarme.class);
        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null);

        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }

        if (alarmeAtivo) {

            Log.i("PMM", "NOVO TIMER");
            PendingIntent p = PendingIntent.getBroadcast(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        } else {
            Log.i("PMM", "TIMER já ativo");
        }

        if(verTela){
            Intent it = new Intent(MenuInicialActivity.this, MenuPrincNormalActivity.class);
            startActivity(it);
            finish();
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
            if (configCTR.hasElements()) {
                if (EnvioDadosServ.getInstance().getStatusEnvio() == 1) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 2) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 3) {
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

    public void clearBD() {

        ImpleMMBean impleMMBean = new ImpleMMBean();
        List implementoList = impleMMBean.all();

        for (int i = 0; i < implementoList.size(); i++) {
            impleMMBean = (ImpleMMBean) implementoList.get(i);
            impleMMBean.delete();
        }

        OSBean osTO = new OSBean();
        osTO.deleteAll();

        ROSAtivBean rosAtivBean = new ROSAtivBean();
        rosAtivBean.deleteAll();

        BackupApontaBean backupApontaBean = new BackupApontaBean();
        backupApontaBean.deleteAll();

    }

    public void teste() {

        BoletimMMBean boletimMMBean = new BoletimMMBean();
        List boletimMMList = boletimMMBean.all();

        Log.i("PMM", "AKI");

        for (int i = 0; i < boletimMMList.size(); i++) {

            boletimMMBean = (BoletimMMBean) boletimMMList.get(i);
            Log.i("PMM", "BOLETIM MM");
            Log.i("PMM", "idBoletim = " + boletimMMBean.getIdBolMM());
            Log.i("PMM", "idExtBoletim = " + boletimMMBean.getIdExtBolMM());
            Log.i("PMM", "codMotoBoletim = " + boletimMMBean.getMatricFuncBolMM());
            Log.i("PMM", "codEquipBoletim = " + boletimMMBean.getIdEquipBolMM());
            Log.i("PMM", "codTurnoBoletim = " + boletimMMBean.getIdTurnoBolMM());
            Log.i("PMM", "hodometroInicialBoletim = " + boletimMMBean.getHodometroInicialBolMM());
            Log.i("PMM", "hodometroFinalBoletim = " + boletimMMBean.getHodometroFinalBolMM());
            Log.i("PMM", "osBoletim = " + boletimMMBean.getOsBolMM());
            Log.i("PMM", "ativPrincBoletim = " + boletimMMBean.getAtivPrincBolMM());
            Log.i("PMM", "dthrInicioBoletim = " + boletimMMBean.getDthrInicialBolMM());
            Log.i("PMM", "dthrFimBoletim = " + boletimMMBean.getDthrFinalBolMM());
            Log.i("PMM", "statusBoletim = " + boletimMMBean.getStatusBolMM());
            Log.i("PMM", "qtdeApontBolMM = " + boletimMMBean.getQtdeApontBolMM());

        }

        ApontMMBean apontMMBean = new ApontMMBean();
        List apontaMMList = apontMMBean.all();

        for (int i = 0; i < apontaMMList.size(); i++) {

            apontMMBean = (ApontMMBean) apontaMMList.get(i);
            Log.i("PMM", "APONTAMENTO MM");
            Log.i("PMM", "idAponta = " + apontMMBean.getIdApontMM());
            Log.i("PMM", "idBolAponta = " + apontMMBean.getIdBolApontMM());
            Log.i("PMM", "idExtBolAponta = " + apontMMBean.getIdExtBolApontMM());
            Log.i("PMM", "osAponta = " + apontMMBean.getOsApontMM());
            Log.i("PMM", "atividadeAponta = " + apontMMBean.getAtivApontMM());
            Log.i("PMM", "paradaAponta = " + apontMMBean.getParadaApontMM());
            Log.i("PMM", "transbordoAponta = " + apontMMBean.getTransbApontMM());
            Log.i("PMM", "dthrAponta = " + apontMMBean.getDthrApontMM());

        }

        ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
        List apontImpleList = apontImpleMMBean.all();

        for (int l = 0; l < apontImpleList.size(); l++) {
            apontImpleMMBean = (ApontImpleMMBean) apontImpleList.get(l);
            Log.i("PMM", "IMPLEMENTO");
            Log.i("PMM", "idApontImplemento = " + apontImpleMMBean.getIdApontImpleMM());
            Log.i("PMM", "idApontMM = " + apontImpleMMBean.getIdApontMM());
            Log.i("PMM", "posImplemento = " + apontImpleMMBean.getPosImpleMM());
            Log.i("PMM", "codEquipImplemento = " + apontImpleMMBean.getCodEquipImpleMM());
        }

        RendMMBean rendMMBean = new RendMMBean();
        List rendimentoList = rendMMBean.all();

        for (int j = 0; j < rendimentoList.size(); j++) {
            rendMMBean = (RendMMBean) rendimentoList.get(j);
            Log.i("PMM", "RENDIMENTO");
            Log.i("PMM", "idRendimento = " + rendMMBean.getIdRendMM());
            Log.i("PMM", "idBolRendimento = " + rendMMBean.getIdBolRendMM());
            Log.i("PMM", "idExtBolRendimento = " + rendMMBean.getIdExtBolRendMM());
            Log.i("PMM", "nroOSRendimento = " + rendMMBean.getNroOSRendMM());
            Log.i("PMM", "valorRendimento = " + rendMMBean.getValorRendMM());
            Log.i("PMM", "dthrRendimento = " + rendMMBean.getDthrRendMM());
        }

        BoletimFertBean boletimFertBean = new BoletimFertBean();
        List boletimFertList = boletimFertBean.all();

        for (int i = 0; i < boletimFertList.size(); i++) {

            boletimFertBean = (BoletimFertBean) boletimFertList.get(i);
            Log.i("PMM", "BOLETIM FERT");
            Log.i("PMM", "idBoletim = " + boletimFertBean.getIdBolFert());
            Log.i("PMM", "idExtBoletim = " + boletimFertBean.getIdExtBolFert());
            Log.i("PMM", "codMotoBoletim = " + boletimFertBean.getMatricFuncBolFert());
            Log.i("PMM", "codEquipBoletim = " + boletimFertBean.getIdEquipBolFert());
            Log.i("PMM", "codTurnoBoletim = " + boletimFertBean.getIdTurnoBolFert());
            Log.i("PMM", "hodometroInicialBoletim = " + boletimFertBean.getHodometroInicialBolFert());
            Log.i("PMM", "hodometroFinalBoletim = " + boletimFertBean.getHodometroFinalBolFert());
            Log.i("PMM", "osBoletim = " + boletimFertBean.getOsBolFert());
            Log.i("PMM", "ativPrincBoletim = " + boletimFertBean.getAtivPrincBolFert());
            Log.i("PMM", "dthrInicioBoletim = " + boletimFertBean.getDthrInicialBolFert());
            Log.i("PMM", "dthrFimBoletim = " + boletimFertBean.getDthrFinalBolFert());
            Log.i("PMM", "statusBoletim = " + boletimFertBean.getStatusBolFert());

        }

        ApontFertBean apontFertBean = new ApontFertBean();
        List apontaAplicFertList = apontFertBean.all();

        for (int j = 0; j < apontaAplicFertList.size(); j++) {
            apontFertBean = (ApontFertBean) apontaAplicFertList.get(j);

            Log.i("PMM", "APONTA FERT");
            Log.i("PMM", "idApontaAplicFert = " + apontFertBean.getIdApontFert());
            Log.i("PMM", "idBolApontaAplicFert = " + apontFertBean.getIdBolApontFert());
            Log.i("PMM", "idExtBolApontaAplicFert = " + apontFertBean.getIdExtBolApontFert());
            Log.i("PMM", "osApontaAplicFert = " + apontFertBean.getOsApontFert());
            Log.i("PMM", "ativApontaAplicFert = " + apontFertBean.getAtivApontFert());
            Log.i("PMM", "paradaApontaAplicFert = " + apontFertBean.getParadaApontFert());
            Log.i("PMM", "dthrApontaAplicFert = " + apontFertBean.getDthrApontFert());
            Log.i("PMM", "pressaoApontaAplicFert= " + apontFertBean.getPressaoApontFert());
            Log.i("PMM", "velocApontaAplicFert = " + apontFertBean.getVelocApontFert());
            Log.i("PMM", "bocalApontaAplicFert = " + apontFertBean.getBocalApontFert());

        }

        ConfigBean configBean = new ConfigBean();
        List configList = configBean.all();

        for (int j = 0; j < configList.size(); j++) {

            configBean = (ConfigBean) configList.get(j);

            Log.i("PMM", "Config");
            Log.i("PMM", "equipConfig = " + configBean.getEquipConfig());
            Log.i("PMM", "senhaConfig = " + configBean.getSenhaConfig());
            Log.i("PMM", "ultTurnoCLConfig = " + configBean.getUltTurnoCLConfig());
            Log.i("PMM", "dtUltApontConfig = " + configBean.getDtUltApontConfig());
            Log.i("PMM", "osConfig = " + configBean.getOsConfig());
            Log.i("PMM", "horimetroConfig = " + configBean.getHorimetroConfig());

        }

        EquipBean equipBean = new EquipBean();
        List equipTOList = equipBean.all();

        for (int j = 0; j < equipTOList.size(); j++) {

            equipBean = (EquipBean) equipTOList.get(j);

            Log.i("PMM", "Equipamento");
            Log.i("PMM", "idEquip = " + equipBean.getIdEquip());
            Log.i("PMM", "codEquip = " + equipBean.getNroEquip());
            Log.i("PMM", "codTurno = " + equipBean.getCodTurno());
            Log.i("PMM", "idCheckList = " + equipBean.getIdCheckList());
            Log.i("PMM", "tipoEquipFert = " + equipBean.getTipoEquipFert());

        }

        CabecCLBean cabecCLBean = new CabecCLBean();
        List cabecList = cabecCLBean.all();

        for (int j = 0; j < cabecList.size(); j++) {

            cabecCLBean = (CabecCLBean) cabecList.get(j);

            Log.i("PMM", "CabecCheckList");
            Log.i("PMM", "IdCabecCheck = " + cabecCLBean.getIdCabCL());
            Log.i("PMM", "EquipCabecCheckList = " + cabecCLBean.getEquipCabCL());
            Log.i("PMM", "DtCabecCheckList = " + cabecCLBean.getDtCabCL());
            Log.i("PMM", "FuncCabecCheckList = " + cabecCLBean.getFuncCabCL());
            Log.i("PMM", "TurnoCabecCheckList = " + cabecCLBean.getTurnoCabCL());
            Log.i("PMM", "StatusCabecCheckList = " + cabecCLBean.getStatusCabCL());

        }

        RespItemCLBean respItemCLBean = new RespItemCLBean();
        List respItemList = respItemCLBean.all();

        for (int j = 0; j < respItemList.size(); j++) {

            respItemCLBean = (RespItemCLBean) respItemList.get(j);

            Log.i("PMM", "RespItemCheckList");
            Log.i("PMM", "IdItemCheckList = " + respItemCLBean.getIdItCL());
            Log.i("PMM", "IdItItemCheckList = " + respItemCLBean.getIdItBDItCL());
            Log.i("PMM", "IdCabecItemCheckList = " + respItemCLBean.getIdCabItCL());
            Log.i("PMM", "OpcaoItemCheckList = " + respItemCLBean.getOpItCL());

        }

        Log.i("PMM", "versão = " + PMMContext.versaoAplic);
    }

}
