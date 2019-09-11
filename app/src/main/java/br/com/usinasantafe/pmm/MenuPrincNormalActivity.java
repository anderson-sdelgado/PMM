package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.ManipDadosReceb;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.estaticas.RFuncaoAtivParTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;

public class MenuPrincNormalActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView listViewAtiv;
    private ProgressDialog progressBar;
    private EquipTO equipTO;
    private ConfigTO configTO;

    private TextView textViewProcessoNormal;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_normal);

        pmmContext = (PMMContext) getApplication();
        textViewProcessoNormal = (TextView) findViewById(R.id.textViewProcessoNormal);

        configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);

        if(configTO.getVisDadosConfig() == 1L){
            ManipDadosVerif.getInstance().setVerTelaAtualPerda(0);
        }

        equipTO = new EquipTO();
        List listEquipTO = equipTO.get("idEquip", configTO.getEquipConfig());
        equipTO = (EquipTO) listEquipTO.get(0);
        listEquipTO.clear();

        if ((equipTO.getTipoEquipFert() == 1) || (equipTO.getTipoEquipFert() == 2)) {
            pmmContext.setTipoEquip(2);
        } else {
            pmmContext.setTipoEquip(1);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("TRABALHANDO");
        itens.add("PARADO");

        AtividadeTO atividadeTO = new AtividadeTO();
        List ativList;

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        List bkpApontaList = backupApontaTO.all();

        Long equip;
        if(pmmContext.getTipoEquip() == 1){

            BoletimMMTO boletimMMTO = new BoletimMMTO();
            List boletimList = boletimMMTO.get("statusBolMM", 1);
            boletimMMTO = (BoletimMMTO) boletimList.get(0);

            equip = boletimMMTO.getIdEquipBolMM();

            if (bkpApontaList.size() == 0) {
                ativList = atividadeTO.get("idAtiv", boletimMMTO.getAtivPrincBolMM());
            } else {
                backupApontaTO = (BackupApontaTO) bkpApontaList.get(bkpApontaList.size() - 1);
                ativList = atividadeTO.get("idAtiv", backupApontaTO.getAtividadeAponta());
            }

            atividadeTO = (AtividadeTO) ativList.get(0);
            ativList.clear();

            RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
            ArrayList pesqList = new ArrayList();

            EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
            pesquisa1.setCampo("idAtivPar");
            pesquisa1.setValor(atividadeTO.getIdAtiv());
            pesquisa1.setTipo(1);
            pesqList.add(pesquisa1);

            EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
            pesquisa2.setCampo("tipoFuncao");
            pesquisa2.setValor(1L);
            pesquisa2.setTipo(1);
            pesqList.add(pesquisa2);

            List rFuncaoAtivParList = rFuncaoAtivParTO.get(pesqList);

            boolean transbordo = false;
            boolean rendimento = false;
            boolean implemento = false;

            for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(i);
                if(rFuncaoAtivParTO.getCodFuncao() == 2){
                    transbordo = true;
                }
                if(rFuncaoAtivParTO.getCodFuncao() == 1){
                    rendimento = true;
                }
                if(rFuncaoAtivParTO.getCodFuncao() == 3){
                    implemento = true;
                }
            }
            rFuncaoAtivParList.clear();

            if (transbordo) {
                itens.add("NOVO TRANSBORDO");
            }

            if (rendimento) {
                itens.add("RENDIMENTO");
            }

            if (implemento) {
                itens.add("TROCAR IMPLEMENTO");
            }

            boletimList.clear();

        }
        else{

            BoletimFertTO boletimFertTO = new BoletimFertTO();
            List boletimList = boletimFertTO.get("statusBolFert", 1);
            boletimFertTO = (BoletimFertTO) boletimList.get(0);

            equip = boletimFertTO.getIdEquipBolFert();

            itens.add("RECOLHIMENTO MANGUEIRA");

        }

        itens.add("FINALIZAR BOLETIM");
        itens.add("HISTORICO");
        itens.add("REENVIO DE DADOS");

        equipTO = new EquipTO();
        List equipList = equipTO.get("idEquip", equip);
        equipTO = (EquipTO) equipList.get(0);

        AdapterList adapterList = new AdapterList(this, itens);
        listViewAtiv = (ListView) findViewById(R.id.listViewMenuPrinc);
        listViewAtiv.setAdapter(adapterList);

        listViewAtiv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                boolean verDthr = true;

                if (verDthr){
                    if (text.equals("TRABALHANDO")) {
                        if (configTO.getDtUltApontConfig().equals(Tempo.getInstance().datahora())) {
                            Toast.makeText(MenuPrincNormalActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            pmmContext.setVerPosTela(2);
                            customHandler.removeCallbacks(updateTimerThread);
                            Intent it = new Intent(MenuPrincNormalActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();
                        }
                    } else if (text.equals("PARADO")) {
                        if (configTO.getDtUltApontConfig().equals(Tempo.getInstance().datahora())) {
                            Toast.makeText(MenuPrincNormalActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            pmmContext.setVerPosTela(3);
                            customHandler.removeCallbacks(updateTimerThread);
                            Intent it = new Intent(MenuPrincNormalActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();
                        }
                    } else if (text.equals("FINALIZAR BOLETIM")) {
                        if (configTO.getDtUltApontConfig().equals("")) {
                            Toast.makeText(MenuPrincNormalActivity.this, "POR FAVOR! INSIRA OS APONTAMENTOS AO BOLETIM!",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            pmmContext.setVerPosTela(4);
                            pmmContext.setTextoHorimetro("HORÍMETRO FINAL:");
                            customHandler.removeCallbacks(updateTimerThread);
                            Intent it = new Intent(MenuPrincNormalActivity.this, HorimetroActivity.class);
                            startActivity(it);
                            finish();
                        }
                    } else if (text.equals("HISTORICO")) {
                        Intent it = new Intent(MenuPrincNormalActivity.this, ListaHistApontaActivity.class);
                        startActivity(it);
                        finish();
                    } else if (text.equals("NOVO TRANSBORDO")) {
                        int ver = verTransbordo(configTO.getDtUltApontConfig());
                        if (ver == 1) {
                            Toast.makeText(MenuPrincNormalActivity.this, "POR FAVOR! APONTE UMA ATIVIDADE ANTES DE TROCAR DE TRANSBORDO.",
                                    Toast.LENGTH_LONG).show();
                        } else if (ver == 2) {
                            Toast.makeText(MenuPrincNormalActivity.this, "POR FAVOR! ESPERE 10 MINUTO PARA TROCAR DE TRANSBORDO.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            pmmContext.setVerPosTela(6);
                            customHandler.removeCallbacks(updateTimerThread);
                            Intent it = new Intent(MenuPrincNormalActivity.this, TransbordoActivity.class);
                            startActivity(it);
                            finish();
                        }
                    } else if (text.equals("RENDIMENTO")) {
                        pmmContext.setVerPosTela(7);
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuPrincNormalActivity.this, ListaOSRendActivity.class);
                        startActivity(it);
                        finish();
                    } else if (text.equals("ATUALIZAR DADOS")) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        if (conexaoWeb.verificaConexao(MenuPrincNormalActivity.this)) {
                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();
                            ManipDadosReceb.getInstance().atualizarBD(progressBar);
                            ManipDadosReceb.getInstance().setContext(MenuPrincNormalActivity.this);
                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincNormalActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();
                        }

                    } else if (text.equals("RECOLHIMENTO MANGUEIRA")) {
                        pmmContext.setVerPosTela(14);
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuPrincNormalActivity.this, ListaOSRecolActivity.class);
                        startActivity(it);
                        finish();
                    } else if (text.equals("REENVIO DE DADOS")) {
                        ManipDadosEnvio.getInstance().envioDados(MenuPrincNormalActivity.this);
                    } else if (text.equals("TROCAR IMPLEMENTO")) {
                        if (configTO.getDtUltApontConfig().equals("")) {
                            Toast.makeText(MenuPrincNormalActivity.this, "POR FAVOR! FAÇA ALGUM APONTAMENTO ANTES DE REALIZAR A TROCA DO(S) IMPLEMENTO(S)!",
                                    Toast.LENGTH_LONG).show();
                        } else {

                            if (configTO.getDtUltApontConfig().equals(Tempo.getInstance().datahora())) {
                                Toast.makeText(MenuPrincNormalActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                apontaParadaTrocaImpl();
                                pmmContext.setVerPosTela(19);
                                pmmContext.setContImplemento(1);
                                customHandler.removeCallbacks(updateTimerThread);
                                Intent it = new Intent(MenuPrincNormalActivity.this, ImplementoActivity.class);
                                startActivity(it);
                                finish();
                            }

                        }
                    }
                }

            }

        });

    }

    public void onBackPressed() {
    }

    public int verTransbordo(String data) {

        int retorno = 0;

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        List bkpApontaList = backupApontaTO.all();

        if (bkpApontaList.size() > 0) {
            backupApontaTO = (BackupApontaTO) bkpApontaList.get(bkpApontaList.size() - 1);
            if (backupApontaTO.getParadaAponta() != 0L) {
                retorno = 1;
            }
        }

        if (data.isEmpty()) {
            retorno = 1;
        }

        if (retorno == 0) {

            String dtStr = data;

            String diaStr = dtStr.substring(0, 2);
            String mesStr = dtStr.substring(3, 5);
            String anoStr = dtStr.substring(6, 10);
            String horaStr = dtStr.substring(11, 13);
            String minutoStr = dtStr.substring(14, 16);

            Calendar calBase = Calendar.getInstance();
            calBase.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            calBase.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            calBase.set(Calendar.YEAR, Integer.parseInt(anoStr));
            calBase.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            calBase.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            calBase.add(Calendar.MINUTE, +9);

            dtStr = Tempo.getInstance().datahora();

            diaStr = dtStr.substring(0, 2);
            mesStr = dtStr.substring(3, 5);
            anoStr = dtStr.substring(6, 10);
            horaStr = dtStr.substring(11, 13);
            minutoStr = dtStr.substring(14, 16);

            Calendar calTimer = Calendar.getInstance();
            calTimer.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            calTimer.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            calTimer.set(Calendar.YEAR, Integer.parseInt(anoStr));
            calTimer.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            calTimer.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            if (calBase.after(calTimer)) {
                retorno = 2;
            }

        }

        return retorno;

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (ManipDadosEnvio.getInstance().getStatusEnvio() == 1) {
                textViewProcessoNormal.setTextColor(Color.YELLOW);
                textViewProcessoNormal.setText("Enviando e recebendo de dados...");
            } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 2) {
                textViewProcessoNormal.setTextColor(Color.RED);
                textViewProcessoNormal.setText("Existem dados para serem enviados e recebidos");
            } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 3) {
                textViewProcessoNormal.setTextColor(Color.GREEN);
                textViewProcessoNormal.setText("Todos os Dados já foram enviados e recebidos");
            }

            if(ManipDadosVerif.getInstance().getVerTelaAtualPerda() == 3){
                Intent it = new Intent( MenuPrincNormalActivity.this, DadosColheitaActivity.class);
                startActivity(it);
                finish();
            }

            customHandler.postDelayed(this, 10000);
        }
    };

    public void apontaParadaTrocaImpl() {

        ConfigTO configTO = new ConfigTO();
        List listConfigTO = configTO.all();
        configTO = (ConfigTO) listConfigTO.get(0);
        listConfigTO.clear();
        configTO.setDtUltApontConfig(Tempo.getInstance().datahora());
        configTO.update();

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        List bkpApontaList = backupApontaTO.all();
        backupApontaTO = (BackupApontaTO) bkpApontaList.get(bkpApontaList.size() - 1);

        ApontMMTO apontMMTO = new ApontMMTO();
        apontMMTO.setOsApontMM(backupApontaTO.getOsAponta());
        apontMMTO.setAtivApontMM(backupApontaTO.getAtividadeAponta());

        RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("codFuncao");
        pesq1.setValor(2L);
        pesq1.setTipo(1);
        pesqList.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("tipoFuncao");
        pesq2.setValor(2L);
        pesq2.setTipo(1);
        pesqList.add(pesq2);

        List rFuncaoAtivParList = rFuncaoAtivParTO.get(pesqList);
        rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(0);
        rFuncaoAtivParList.clear();

        apontMMTO.setParadaApontMM(rFuncaoAtivParTO.getIdAtivPar());
        apontMMTO.setTransbApontMM(backupApontaTO.getTransbAponta());
        apontMMTO.setLatitudeApontMM(0D);
        apontMMTO.setLongitudeApontMM(0D);
        ManipDadosEnvio.getInstance().salvaApontaMM(apontMMTO);

    }

}
