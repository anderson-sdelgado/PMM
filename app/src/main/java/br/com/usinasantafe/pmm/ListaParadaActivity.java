package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.estaticas.RAtivParadaTO;
import br.com.usinasantafe.pmm.to.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;

public class ListaParadaActivity extends ActivityGeneric {

    private ListView paradaListView;
    private ConfigTO configTO;
    private PMMContext pmmContext;
    private List paradaList;
    private ProgressDialog progressBar;
    private ArrayAdapter<String> adapter;
    private String paradaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada);

        pmmContext = (PMMContext) getApplication();

        Button buttonAtualParada = (Button) findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);
        EditText editPesqListParada = (EditText) findViewById(R.id.editPesqListParada);

        configTO = new ConfigTO();
        List listConfigTO = configTO.all();
        configTO = (ConfigTO) listConfigTO.get(0);

        Long ativ;
        if(pmmContext.getTipoEquip() == 1){
            ativ = pmmContext.getApontMMTO().getAtivApontMM();
        } else {
            ativ = pmmContext.getApontFertTO().getAtivApontFert();
        }

        RAtivParadaTO rAtivParadaTO = new RAtivParadaTO();
        List rAtivParadaList = rAtivParadaTO.get("idAtiv", ativ);

        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < rAtivParadaList.size(); i++) {
            rAtivParadaTO = (RAtivParadaTO) rAtivParadaList.get(i);
            rLista.add(rAtivParadaTO.getIdParada());
        }

        rAtivParadaList.clear();

        ParadaTO paradaTO = new ParadaTO();
        paradaList = paradaTO.inAndOrderBy("idParada", rLista, "codParada", true);

        String itens[] = new String[paradaList.size()];

        for (int i = 0; i < paradaList.size(); i++) {
            paradaTO = (ParadaTO) paradaList.get(i);
            itens[i] = paradaTO.getCodParada() + " - " + paradaTO.getDescrParada();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.activity_item_lista, R.id.textViewItemList, itens);
        paradaListView = (ListView) findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(adapter);

        editPesqListParada.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                ListaParadaActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

        buttonAtualParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaParadaActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO PARADAS...");
                    progressBar.show();

                    ConfigTO configTO = new ConfigTO();
                    List configList = configTO.all();
                    configTO = (ConfigTO) configList.get(0);

                    ManipDadosVerif.getInstance().verDados("", "AtualParada"
                            , ListaParadaActivity.this, ListaParadaActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
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

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(ListaParadaActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }
        });

        paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                paradaString = textView.getText().toString();

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                alerta.setTitle("ATENÇÃO");

                String label = "DESEJA REALMENTE REALIZAR A PARADA '" + paradaString + "' ?";

                alerta.setMessage(label);

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ParadaTO paradaTO = new ParadaTO();
                        List paradaList = paradaTO.get("codParada", paradaString.substring(0, paradaString.indexOf('-')).trim());
                        paradaTO = (ParadaTO) paradaList.get(0);

                        if(pmmContext.getTipoEquip() == 1) {
                            pmmContext.getApontMMTO().setTransbApontMM(0L);
                            pmmContext.getApontMMTO().setParadaApontMM(paradaTO.getIdParada());
                        }
                        else{
                            pmmContext.getApontFertTO().setBocalApontFert(0L);
                            pmmContext.getApontFertTO().setPressaoApontFert(0D);
                            pmmContext.getApontFertTO().setVelocApontFert(0L);
                            pmmContext.getApontFertTO().setParadaApontFert(paradaTO.getIdParada());
                        }

                        if (verifBackup()) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("PARADA JÁ APONTADA PARA O EQUIPAMENTO!");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            });

                            alerta.show();

                        } else {

                            if(pmmContext.getTipoEquip() == 1) {
                                pmmContext.getApontMMTO().setLatitudeApontMM(0D);
                                pmmContext.getApontMMTO().setLongitudeApontMM(0D);
                            }
                            else{
                                pmmContext.getApontFertTO().setLatitudeApontFert(0D);
                                pmmContext.getApontFertTO().setLongitudeApontFert(0D);
                            }

                            configTO.setDtUltApontConfig(Tempo.getInstance().datahora());
                            configTO.update();

                            if(pmmContext.getTipoEquip() == 1) {
                                ManipDadosEnvio.getInstance().salvaApontaMM(pmmContext.getApontMMTO());
                            }
                            else{
                                ManipDadosEnvio.getInstance().salvaApontaFert(pmmContext.getApontFertTO());
                            }

                            Intent it = new Intent(ListaParadaActivity.this, MenuPrincNormalActivity.class);
                            startActivity(it);
                            finish();

                            paradaList.clear();

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
