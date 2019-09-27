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
import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.control.ApontMMMovLeiraCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.estaticas.RFuncaoAtivParTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;

public class MenuPrincNormalActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView listViewAtiv;
    private ProgressDialog progressBar;
    private ConfigCTR configCTR;

    private TextView textViewProcessoNormal;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_normal);

        pmmContext = (PMMContext) getApplication();
        textViewProcessoNormal = (TextView) findViewById(R.id.textViewProcessoNormal);

        configCTR = new ConfigCTR();

        customHandler.postDelayed(updateTimerThread, 0);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("TRABALHANDO");
        itens.add("PARADO");

        if(configCTR.getEquip().getTipo() == 1){

            List rFuncaoAtivParList = pmmContext.getBoletimCTR().retFuncaoAtivParList(pmmContext.getBoletimCTR().ultAtivMMMenu());

            boolean transbordo = false;
            boolean rendimento = false;
            boolean implemento = false;

            for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                RFuncaoAtivParTO rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(i);
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
        }
        else{
            itens.add("RECOLHIMENTO MANGUEIRA");
        }

        itens.add("FINALIZAR BOLETIM");
        itens.add("HISTORICO");
        itens.add("REENVIO DE DADOS");

        AdapterList adapterList = new AdapterList(this, itens);
        listViewAtiv = (ListView) findViewById(R.id.listViewMenuPrinc);
        listViewAtiv.setAdapter(adapterList);

        listViewAtiv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                ConfigTO configTO = configCTR.getConfig();

                if (text.equals("TRABALHANDO")) {
                    if (configTO.getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
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
                    if (configTO.getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
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
                    ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
                    int ver =  apontMMMovLeiraCTR.verTrocaTransb();
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

                        configCTR.atualTodasTabelas(MenuPrincNormalActivity.this, progressBar);
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
                    EnvioDadosServ.getInstance().envioDados(MenuPrincNormalActivity.this);
                } else if (text.equals("TROCAR IMPLEMENTO")) {
                    if (configTO.getDtUltApontConfig().equals("")) {
                        Toast.makeText(MenuPrincNormalActivity.this, "POR FAVOR! FAÇA ALGUM APONTAMENTO ANTES DE REALIZAR A TROCA DO(S) IMPLEMENTO(S)!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        if (configTO.getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
                            Toast.makeText(MenuPrincNormalActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
                            apontMMMovLeiraCTR.salvarParadaImple();
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

        });

    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (EnvioDadosServ.getInstance().getStatusEnvio() == 1) {
                textViewProcessoNormal.setTextColor(Color.YELLOW);
                textViewProcessoNormal.setText("Enviando e recebendo de dados...");
            } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 2) {
                textViewProcessoNormal.setTextColor(Color.RED);
                textViewProcessoNormal.setText("Existem dados para serem enviados e recebidos");
            } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 3) {
                textViewProcessoNormal.setTextColor(Color.GREEN);
                textViewProcessoNormal.setText("Todos os Dados já foram enviados e recebidos");
            }


            if(configCTR.getConfig().getVisDadosColhConfig() == 2){
                Intent it = new Intent( MenuPrincNormalActivity.this, DadosColheitaActivity.class);
                startActivity(it);
                finish();
            }

            customHandler.postDelayed(this, 10000);
        }
    };

}
