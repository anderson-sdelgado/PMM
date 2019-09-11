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
import br.com.usinasantafe.pmm.to.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.REquipAtivTO;
import br.com.usinasantafe.pmm.to.estaticas.RFuncaoAtivParTO;
import br.com.usinasantafe.pmm.to.estaticas.ROSAtivTO;
import br.com.usinasantafe.pmm.to.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.RendMMTO;
import br.com.usinasantafe.pmm.to.variaveis.TransbMMTO;

public class ListaAtividadeActivity extends ActivityGeneric {

    private ListView atividadeListView;
    private PMMContext pmmContext;
    private List atividadeList;
    private ProgressDialog progressBar;
    private ArrayList atividadeArrayList;
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
        List rEquipAtivList = rEquipAtivTO.get("idEquip", configTO.getEquipConfig());

        configList.clear();

        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < rEquipAtivList.size(); i++) {
            rEquipAtivTO = (REquipAtivTO) rEquipAtivList.get(i);
            rLista.add(rEquipAtivTO.getIdAtiv());
        }

        atividadeList = atividadeTO.in("idAtiv", rLista);

        atividadeArrayList = new ArrayList();

        ROSAtivTO rOSAtivTO = new ROSAtivTO();
        List lroa = rOSAtivTO.get("nroOS", nroOS);

        if (lroa.size() > 0) {

            for (int i = 0; i < atividadeList.size(); i++) {
                atividadeTO = (AtividadeTO) atividadeList.get(i);
                for (int j = 0; j < lroa.size(); j++) {
                    rOSAtivTO = (ROSAtivTO) lroa.get(j);
                    if (Objects.equals(atividadeTO.getIdAtiv(), rOSAtivTO.getIdAtiv())) {
                        atividadeArrayList.add(atividadeTO);
                    }
                }
            }

        } else {
            for (int i = 0; i < atividadeList.size(); i++) {
                atividadeTO = (AtividadeTO) atividadeList.get(i);
                atividadeArrayList.add(atividadeTO);
            }
        }

        for (int i = 0; i < atividadeArrayList.size(); i++) {
            atividadeTO = (AtividadeTO) atividadeArrayList.get(i);
            itens.add(atividadeTO.getCodAtiv() + " - " + atividadeTO.getDescrAtiv());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        atividadeListView = (ListView) findViewById(R.id.listAtividade);
        atividadeListView.setAdapter(adapterList);

        atividadeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                AtividadeTO atividadeTO = new AtividadeTO();
                atividadeTO = (AtividadeTO) atividadeArrayList.get(position);

                if (pmmContext.getVerPosTela() == 1) {

                    if(pmmContext.getTipoEquip() == 1) {
                        pmmContext.getBoletimMMTO().setAtivPrincBolMM(atividadeTO.getIdAtiv());
                        pmmContext.getBoletimMMTO().setStatusConBolMM(configTO.getStatusConConfig());
                    }
                    else {
                        pmmContext.getBoletimFertTO().setAtivPrincBolFert(atividadeTO.getIdAtiv());
                        pmmContext.getBoletimFertTO().setStatusConBolFert(configTO.getStatusConConfig());
                    }

                    pmmContext.setTextoHorimetro("HORÍMETRO INICIAL:");
                    Intent it = new Intent(ListaAtividadeActivity.this, HorimetroActivity.class);
                    startActivity(it);
                    finish();

                    atividadeList.clear();
                    atividadeArrayList.clear();

                } else if ((pmmContext.getVerPosTela() == 2)) {

                    if(pmmContext.getTipoEquip() == 1) {
                        pmmContext.getApontMMTO().setAtivApontMM(atividadeTO.getIdAtiv());
                        pmmContext.getApontMMTO().setStatusConApontMM(configTO.getStatusConConfig());
                        pmmContext.getApontMMTO().setParadaApontMM(0L);
                    }
                    else{
                        pmmContext.getApontFertTO().setAtivApontFert(atividadeTO.getIdAtiv());
                        pmmContext.getApontFertTO().setStatusConApontFert(configTO.getStatusConConfig());
                        pmmContext.getApontFertTO().setParadaApontFert(0L);
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

                        for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                            rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(i);
                            if(rFuncaoAtivParTO.getCodFuncao() == 2){
                                transbordo = true;
                            }
                            if(rFuncaoAtivParTO.getCodFuncao() == 1){
                                rendimento = true;
                            }
                        }

                        if (transbordo) {

                            TransbMMTO transbMMTO = new TransbMMTO();
                            if (transbMMTO.hasElements()) {
                                List transbList = transbMMTO.all();
                                transbMMTO = (TransbMMTO) transbList.get(0);
                                pmmContext.getApontMMTO().setTransbApontMM(transbMMTO.getCodEquipTransbMM());
                            } else {
                                pmmContext.getApontMMTO().setTransbApontMM(0L);
                            }

                            if(pmmContext.getTipoEquip() == 1) {

                                pmmContext.getApontMMTO().setLatitudeApontMM(0D);
                                pmmContext.getApontMMTO().setLongitudeApontMM(0D);
                                ManipDadosEnvio.getInstance().salvaApontaMM(pmmContext.getApontMMTO());

                                if (rendimento) {

                                    BoletimMMTO boletimMMTO = new BoletimMMTO();
                                    List boletimList = boletimMMTO.get("statusBolMM", 1L);

                                    if (boletimList.size() > 0) {

                                        boletimMMTO = (BoletimMMTO) boletimList.get(0);

                                        RendMMTO rendMMTO = new RendMMTO();
                                        ArrayList listaPesq = new ArrayList();

                                        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
                                        pesq1.setCampo("idBolRendMM");
                                        pesq1.setValor(boletimMMTO.getIdBolMM());
                                        pesq1.setTipo(1);
                                        listaPesq.add(pesq1);

                                        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
                                        pesq2.setCampo("nroOSRendMM");
                                        pesq2.setValor(pmmContext.getApontMMTO().getOsApontMM());
                                        pesq2.setTipo(1);
                                        listaPesq.add(pesq2);

                                        List rendList = rendMMTO.get(listaPesq);

                                        if (rendList.size() == 0) {
                                            rendMMTO.setIdBolRendMM(boletimMMTO.getIdBolMM());
                                            rendMMTO.setIdExtBolRendMM(boletimMMTO.getIdExtBolMM());
                                            rendMMTO.setNroOSRendMM(pmmContext.getApontMMTO().getOsApontMM());
                                            rendMMTO.setValorRendMM(0D);
                                            rendMMTO.insert();
                                            rendMMTO.commit();
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

                            atividadeList.clear();
                            atividadeArrayList.clear();

                        } else {

                            Intent it = new Intent(ListaAtividadeActivity.this, TransbordoActivity.class);
                            startActivity(it);
                            finish();

                            atividadeList.clear();
                            atividadeArrayList.clear();

                        }

                    }

                } else if ((pmmContext.getVerPosTela() == 3)) {

                    if(pmmContext.getTipoEquip() == 1) {
                        pmmContext.getApontMMTO().setAtivApontMM(atividadeTO.getIdAtiv());
                        pmmContext.getApontMMTO().setStatusConApontMM(configTO.getStatusConConfig());
                    }
                    else{
                        pmmContext.getApontFertTO().setAtivApontFert(atividadeTO.getIdAtiv());
                        pmmContext.getApontFertTO().setStatusConApontFert(configTO.getStatusConConfig());
                    }
                    Intent it = new Intent(ListaAtividadeActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();

                    atividadeList.clear();
                    atividadeArrayList.clear();

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
                if ((pmmContext.getApontMMTO().getOsApontMM().equals(backupApontaTO.getOsAponta()))
                        && (pmmContext.getApontMMTO().getAtivApontMM().equals(backupApontaTO.getAtividadeAponta()))
                        && (pmmContext.getApontMMTO().getParadaApontMM().equals(backupApontaTO.getParadaAponta()))) {
                    v = true;
                }
            }
            else{
                if ((pmmContext.getApontFertTO().getOsApontFert().equals(backupApontaTO.getOsAponta()))
                        && (pmmContext.getApontFertTO().getAtivApontFert().equals(backupApontaTO.getAtividadeAponta()))
                        && (pmmContext.getApontFertTO().getParadaApontFert().equals(backupApontaTO.getParadaAponta()))) {
                    v = true;
                }
            }
        }

        return v;

    }

    public void onBackPressed() {
    }

}
