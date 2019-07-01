package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.to.tb.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.REquipAtivTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ROSAtivTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RendimentoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.TransbordoTO;

public class ListaAtividadeActivity extends ActivityGeneric {

    private ListView lista;
    private PMMContext pmmContext;
    private List listAtiv;
    private ProgressDialog progressBar;
    private ArrayList lAtivExib;
    private Long nroOS = 0L;
    private ConfigTO configTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade);

        pmmContext = (PMMContext) getApplication();

        Button buttonAtualAtividade = (Button) findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = (Button) findViewById(R.id.buttonRetAtividade);
        TextView textViewTituloAtividade = (TextView) findViewById(R.id.textViewTituloAtividade);

        configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);

        nroOS = configTO.getOsConfig();

        buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaAtividadeActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Atividades...");
                    progressBar.show();

                    EquipTO equipTO = new EquipTO();
                    List equipList = equipTO.get("idEquip", configTO.getEquipConfig());
                    equipTO = (EquipTO) equipList.get(0);
                    equipList.clear();

                    ManipDadosVerif.getInstance().verDados(nroOS + "_" + equipTO.getNroEquip(), "Atividade"
                            , ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
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

        buttonRetAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);
                startActivity(it);
            }
        });

        if (pmmContext.getVerPosTela() == 1) {
            textViewTituloAtividade.setText("ATIVIDADE PRINCIPAL");
        } else {
            textViewTituloAtividade.setText("ATIVIDADE");
        }

        AtividadeTO atividadeTO = new AtividadeTO();
        ArrayList<String> itens = new ArrayList<String>();

        REquipAtivTO rEquipAtivTO = new REquipAtivTO();
        Log.i("PMM", "configTO.getEquipConfig() = " + configTO.getEquipConfig());
        List lrea = rEquipAtivTO.get("idEquip", configTO.getEquipConfig());

        configList.clear();

        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < lrea.size(); i++) {
            rEquipAtivTO = (REquipAtivTO) lrea.get(i);
            rLista.add(rEquipAtivTO.getIdAtiv());
        }

        listAtiv = atividadeTO.in("idAtiv", rLista);

        lAtivExib = new ArrayList();

        ROSAtivTO rOSAtivTO = new ROSAtivTO();
        List lroa = rOSAtivTO.get("nroOS", nroOS);

        if (lroa.size() > 0) {

            for (int i = 0; i < listAtiv.size(); i++) {
                atividadeTO = (AtividadeTO) listAtiv.get(i);
                for (int j = 0; j < lroa.size(); j++) {
                    rOSAtivTO = (ROSAtivTO) lroa.get(j);
                    if (Objects.equals(atividadeTO.getIdAtiv(), rOSAtivTO.getIdAtiv())) {
                        lAtivExib.add(atividadeTO);
                    }
                }
            }

        } else {
            for (int i = 0; i < listAtiv.size(); i++) {
                atividadeTO = (AtividadeTO) listAtiv.get(i);
                lAtivExib.add(atividadeTO);
            }
        }

        for (int i = 0; i < lAtivExib.size(); i++) {
            atividadeTO = (AtividadeTO) lAtivExib.get(i);
            itens.add(atividadeTO.getCodAtiv() + " - " + atividadeTO.getDescrAtiv());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listAtividade);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                AtividadeTO atividadeTO = new AtividadeTO();
                atividadeTO = (AtividadeTO) lAtivExib.get(position);

                if (pmmContext.getVerPosTela() == 1) {

                    if(pmmContext.getTipoEquip() == 1) {
                        pmmContext.getBoletimMMTO().setAtivPrincBoletim(atividadeTO.getIdAtiv());
                        pmmContext.getBoletimMMTO().setStatusConBoletim(configTO.getStatusConConfig());
                    }
                    else {
                        pmmContext.getBoletimFertTO().setAtivPrincBolFert(atividadeTO.getIdAtiv());
                        pmmContext.getBoletimFertTO().setStatusConBolFert(configTO.getStatusConConfig());
                    }

                    pmmContext.setTextoHorimetro("HORÍMETRO INICIAL:");
                    Intent it = new Intent(ListaAtividadeActivity.this, HorimetroActivity.class);
                    startActivity(it);
                    finish();

                    listAtiv.clear();
                    lAtivExib.clear();

                } else if ((pmmContext.getVerPosTela() == 2)) {

                    if(pmmContext.getTipoEquip() == 1) {
                        pmmContext.getApontaMMTO().setAtividadeAponta(atividadeTO.getIdAtiv());
                        pmmContext.getApontaMMTO().setStatusConAponta(configTO.getStatusConConfig());
                        pmmContext.getApontaMMTO().setParadaAponta(0L);
                    }
                    else{
                        pmmContext.getApontaFertTO().setAtivApontaFert(atividadeTO.getIdAtiv());
                        pmmContext.getApontaFertTO().setStatusConApontaFert(configTO.getStatusConConfig());
                        pmmContext.getApontaFertTO().setParadaApontaFert(0L);
                    }

                    if (verifBackup()) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });

                        alerta.show();

                    } else {

                        if (atividadeTO.getFlagTransbordo() == 0) {

                            TransbordoTO transbordoTO = new TransbordoTO();
                            if (transbordoTO.hasElements()) {
                                List transbList = transbordoTO.all();
                                transbordoTO = (TransbordoTO) transbList.get(0);
                                pmmContext.getApontaMMTO().setTransbordoAponta(transbordoTO.getCodEquipTransbordo());
                            } else {
                                pmmContext.getApontaMMTO().setTransbordoAponta(0L);
                            }

                            if(pmmContext.getTipoEquip() == 1) {

                                pmmContext.getApontaMMTO().setLatitudeAponta(getLatitude());
                                pmmContext.getApontaMMTO().setLongitudeAponta(getLongitude());
                                ManipDadosEnvio.getInstance().salvaApontaMM(pmmContext.getApontaMMTO(), 2L);

                                if (atividadeTO.getFlagRendimento() == 1) {

                                    BoletimMMTO boletimMMTO = new BoletimMMTO();
                                    List boletimList = boletimMMTO.get("statusBoletim", 1L);

                                    if (boletimList.size() > 0) {

                                        boletimMMTO = (BoletimMMTO) boletimList.get(0);

                                        RendimentoTO rendimentoTO = new RendimentoTO();
                                        ArrayList listaPesq = new ArrayList();

                                        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                                        pesquisa.setCampo("idBolRendimento");
                                        pesquisa.setValor(boletimMMTO.getIdBoletim());
                                        pesquisa.setTipo(1);
                                        listaPesq.add(pesquisa);

                                        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                                        pesquisa2.setCampo("nroOSRendimento");
                                        pesquisa2.setValor(pmmContext.getApontaMMTO().getOsAponta());
                                        pesquisa2.setTipo(1);
                                        listaPesq.add(pesquisa2);

                                        List rendList = rendimentoTO.get(listaPesq);

                                        if (rendList.size() == 0) {
                                            rendimentoTO.setIdBolRendimento(boletimMMTO.getIdBoletim());
                                            rendimentoTO.setIdExtBolRendimento(boletimMMTO.getIdExtBoletim());
                                            rendimentoTO.setNroOSRendimento(pmmContext.getApontaMMTO().getOsAponta());
                                            rendimentoTO.setValorRendimento(0D);
                                            rendimentoTO.insert();
                                            rendimentoTO.commit();
                                        }

                                    }

                                    boletimList.clear();

                                }

                                configTO.setDtUltApontConfig(Tempo.getInstance().datahora());
                                configTO.update();

                                Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincNormalActivity.class);
                                startActivity(it);
                                finish();

                            }
                            else{

                                Intent it = new Intent(ListaAtividadeActivity.this, ListaBocalFertActivity.class);
                                startActivity(it);
                                finish();

                            }

                            listAtiv.clear();
                            lAtivExib.clear();

                        } else {

                            Intent it = new Intent(ListaAtividadeActivity.this, TransbordoActivity.class);
                            startActivity(it);
                            finish();

                            listAtiv.clear();
                            lAtivExib.clear();

                        }

                    }

                } else if ((pmmContext.getVerPosTela() == 3)) {

                    if(pmmContext.getTipoEquip() == 1) {
                        pmmContext.getApontaMMTO().setAtividadeAponta(atividadeTO.getIdAtiv());
                        pmmContext.getApontaMMTO().setStatusConAponta(configTO.getStatusConConfig());
                    }
                    else{
                        pmmContext.getApontaFertTO().setAtivApontaFert(atividadeTO.getIdAtiv());
                        pmmContext.getApontaFertTO().setStatusConApontaFert(configTO.getStatusConConfig());
                    }
                    Intent it = new Intent(ListaAtividadeActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();

                    listAtiv.clear();
                    lAtivExib.clear();

                }

            }

        });


    }

    public boolean verifBackup() {

        boolean v = false;

        BackupApontaTO backupApontaTO = new BackupApontaTO();
        List bkpApontaList = backupApontaTO.all();

        if (bkpApontaList.size() > 0) {
            backupApontaTO = (BackupApontaTO) bkpApontaList.get(bkpApontaList.size() - 1);
            if(pmmContext.getTipoEquip() == 1) {
                if ((pmmContext.getApontaMMTO().getOsAponta().equals(backupApontaTO.getOsAponta()))
                        && (pmmContext.getApontaMMTO().getAtividadeAponta().equals(backupApontaTO.getAtividadeAponta()))
                        && (pmmContext.getApontaMMTO().getParadaAponta().equals(backupApontaTO.getParadaAponta()))) {
                    v = true;
                }
            }
            else{
                if ((pmmContext.getApontaFertTO().getOsApontaFert().equals(backupApontaTO.getOsAponta()))
                        && (pmmContext.getApontaFertTO().getAtivApontaFert().equals(backupApontaTO.getAtividadeAponta()))
                        && (pmmContext.getApontaFertTO().getParadaApontaFert().equals(backupApontaTO.getParadaAponta()))) {
                    v = true;
                }
            }
        }

        return v;

    }

    public void onBackPressed() {
    }

}
