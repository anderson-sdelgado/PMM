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
import br.com.usinasantafe.pmm.to.tb.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.RAtivParadaTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;

public class ListaParadaActivity extends ActivityGeneric {

    private ListView lista;
    private ConfiguracaoTO configTO;
    private PMMContext pmmContext;
    private List listParada;
    private ProgressDialog progressBar;
    private ArrayAdapter<String> adapter;
    private String textParada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada);

        pmmContext = (PMMContext) getApplication();

        Button buttonAtualParada = (Button) findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);
        EditText editPesqListParada = (EditText) findViewById(R.id.editPesqListParada);

        configTO = new ConfiguracaoTO();
        List listConfigTO = configTO.all();
        configTO = (ConfiguracaoTO) listConfigTO.get(0);

        RAtivParadaTO rAtivParadaTO = new RAtivParadaTO();
        List lrcp = rAtivParadaTO.get("idAtiv", pmmContext.getApontaMMTO().getAtividadeAponta());

        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < lrcp.size(); i++) {
            rAtivParadaTO = (RAtivParadaTO) lrcp.get(i);
            rLista.add(rAtivParadaTO.getIdParada());
        }

        lrcp.clear();

        ParadaTO paradaTO = new ParadaTO();
        listParada = paradaTO.inAndOrderBy("idParada", rLista, "codParada", true);

        String itens[] = new String[listParada.size()];

        for (int i = 0; i < listParada.size(); i++) {
            paradaTO = (ParadaTO) listParada.get(i);
            itens[i] = paradaTO.getCodParada() + " - " + paradaTO.getDescrParada();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.activity_item_lista, R.id.textViewItemList, itens);
        lista = (ListView) findViewById(R.id.listViewMotParada);
        lista.setAdapter(adapter);

        editPesqListParada.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                ListaParadaActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        buttonAtualParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaParadaActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Paradas...");
                    progressBar.show();

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                    List configList = configuracaoTO.all();
                    configuracaoTO = (ConfiguracaoTO) configList.get(0);

                    ManipDadosVerif.getInstance().verDados(String.valueOf(configuracaoTO.getEquipConfig()), "Parada"
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

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                textParada = textView.getText().toString();

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                alerta.setTitle("ATENÇÃO");

                String label = "DESEJA REALMENTE REALIZAR A PARADA '" + textParada + "' ?";

                alerta.setMessage(label);

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ParadaTO paradaTO = new ParadaTO();
                        List paradaList = paradaTO.get("codParada", textParada.substring(0, textParada.indexOf('-')).trim());
                        paradaTO = (ParadaTO) paradaList.get(0);

                        if(pmmContext.getTipoEquip() == 1) {
                            pmmContext.getApontaMMTO().setTransbordoAponta(0L);
                            pmmContext.getApontaMMTO().setParadaAponta(paradaTO.getIdParada());
                        }
                        else{
                            pmmContext.getApontaFertTO().setBocalApontaFert(0L);
                            pmmContext.getApontaFertTO().setPressaoApontaFert(0D);
                            pmmContext.getApontaFertTO().setVelocApontaFert(0L);
                            pmmContext.getApontaFertTO().setParadaApontaFert(paradaTO.getIdParada());
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
                                pmmContext.getApontaMMTO().setLatitudeAponta(getLatitude());
                                pmmContext.getApontaMMTO().setLongitudeAponta(getLongitude());
                            }
                            else{
                                pmmContext.getApontaFertTO().setLatitudeApontaFert(getLatitude());
                                pmmContext.getApontaFertTO().setLongitudeApontaFert(getLongitude());
                            }

                            configTO.setDtUltApontConfig(Tempo.getInstance().datahora());
                            configTO.update();

                            if (paradaTO.getFlagCalibragem() == 0L) {
                                if(pmmContext.getTipoEquip() == 1) {
                                    ManipDadosEnvio.getInstance().salvaApontaMM(pmmContext.getApontaMMTO(), 2L);
                                }
                                else{
                                    ManipDadosEnvio.getInstance().salvaApontaFert(pmmContext.getApontaFertTO(), 2L);
                                }
                                Intent it = new Intent(ListaParadaActivity.this, MenuPrincNormalActivity.class);
                                startActivity(it);
                                finish();
                            } else {
                                if(pmmContext.getTipoEquip() == 1) {
                                    ManipDadosEnvio.getInstance().salvaApontaMM(pmmContext.getApontaMMTO(), 1L);
                                }
                                else{
                                    ManipDadosEnvio.getInstance().salvaApontaFert(pmmContext.getApontaFertTO(), 1L);
                                }
                                Intent it = new Intent(ListaParadaActivity.this, ListaPosPneuActivity.class);
                                startActivity(it);
                                finish();
                            }

                            listParada.clear();
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
