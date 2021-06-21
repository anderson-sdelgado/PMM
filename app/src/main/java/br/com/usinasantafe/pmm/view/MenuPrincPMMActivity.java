package br.com.usinasantafe.pmm.view;

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

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.util.ConexaoWeb;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;

public class MenuPrincPMMActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView listViewAtiv;
    private ProgressDialog progressBar;

    private TextView textViewProcessoNormal;
    private TextView textViewDataHora;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_pmm);

        pmmContext = (PMMContext) getApplication();
        textViewProcessoNormal = findViewById(R.id.textViewProcessoNormal);
        textViewDataHora = findViewById(R.id.textViewDataHora);

        customHandler.postDelayed(updateTimerThread, 0);

        ArrayList<String> itens = new ArrayList<String>();

        if (Tempo.getInstance().verDthrServ(pmmContext.getConfigCTR().getConfig().getDtServConfig())) {
            pmmContext.getConfigCTR().setDifDthrConfig(0L);
        }
        else {
            if ((pmmContext.getConfigCTR().getConfig().getDifDthrConfig() == 0) && (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 8L)) {
                pmmContext.setContDataHora(1);
                pmmContext.getConfigCTR().setPosicaoTela(5L);
                Intent it = new Intent(MenuPrincPMMActivity.this, MsgDataHoraActivity.class);
                startActivity(it);
                finish();
            }
        }

        itens.add("TRABALHANDO");
        itens.add("PARADO");

        if(pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){

            List<RFuncaoAtivParBean> rFuncaoAtividadeList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList();

            for (int i = 0; i < rFuncaoAtividadeList.size(); i++) {
                RFuncaoAtivParBean rFuncaoAtivParBean = (RFuncaoAtivParBean) rFuncaoAtividadeList.get(i);
                if(rFuncaoAtivParBean.getCodFuncao() == 2){
                    itens.add("NOVO TRANSBORDO");
                }
                if(rFuncaoAtivParBean.getCodFuncao() == 1){
                    itens.add("RENDIMENTO");
                }
                if(rFuncaoAtivParBean.getCodFuncao() == 3){
                    itens.add("TROCAR IMPLEMENTO");
                }
            }
            rFuncaoAtividadeList.clear();
        }
        else{
            itens.add("RECOLHIMENTO MANGUEIRA");
        }

        itens.add("FINALIZAR BOLETIM");
        itens.add("HISTORICO");
        itens.add("REENVIO DE DADOS");
        itens.add("DATA/HORA");

        AdapterList adapterList = new AdapterList(this, itens);
        listViewAtiv = (ListView) findViewById(R.id.listViewMenuPrinc);
        listViewAtiv.setAdapter(adapterList);

        listViewAtiv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("TRABALHANDO")) {
                    if (pmmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        pmmContext.getConfigCTR().setPosicaoTela(2L);
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuPrincPMMActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("PARADO")) {
                    if (pmmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        pmmContext.getConfigCTR().setPosicaoTela(3L);
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuPrincPMMActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("FINALIZAR BOLETIM")) {
                    if (pmmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals("")) {
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! INSIRA OS APONTAMENTOS AO BOLETIM!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        pmmContext.getConfigCTR().setPosicaoTela(4L);
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuPrincPMMActivity.this, HorimetroActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("HISTORICO")) {
                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaHistApontActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("NOVO TRANSBORDO")) {
                    int ver = pmmContext.getMotoMecFertCTR().verTrocaTransb();
                    if (ver == 1) {
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! APONTE UMA ATIVIDADE ANTES DE TROCAR DE TRANSBORDO.",
                                Toast.LENGTH_LONG).show();
                    } else if (ver == 2) {
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! ESPERE 10 MINUTO PARA TROCAR DE TRANSBORDO.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        pmmContext.getConfigCTR().setPosicaoTela(6L);
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuPrincPMMActivity.this, TransbordoActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("RENDIMENTO")) {
                    pmmContext.getConfigCTR().setPosicaoTela(7L);
                    customHandler.removeCallbacks(updateTimerThread);
                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaOSRendActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("ATUALIZAR DADOS")) {
                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                    if (conexaoWeb.verificaConexao(MenuPrincPMMActivity.this)) {
                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();

                        pmmContext.getConfigCTR().atualTodasTabelas(MenuPrincPMMActivity.this, progressBar);
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPMMActivity.this);
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
                    pmmContext.getConfigCTR().setPosicaoTela(9L);
                    customHandler.removeCallbacks(updateTimerThread);
                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaOSRecolhActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("REENVIO DE DADOS")) {
                    EnvioDadosServ.getInstance().envioDados(MenuPrincPMMActivity.this);
                } else if (text.equals("TROCAR IMPLEMENTO")) {
                    if (pmmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals("")) {
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! FAÇA ALGUM APONTAMENTO ANTES DE REALIZAR A TROCA DO(S) IMPLEMENTO(S)!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        if (pmmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
                            Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            pmmContext.getMotoMecFertCTR().inserirParadaImplemento();
                            pmmContext.getConfigCTR().setPosicaoTela(10L);
                            pmmContext.setContImplemento(1L);
                            customHandler.removeCallbacks(updateTimerThread);
                            Intent it = new Intent(MenuPrincPMMActivity.this, ImplementoActivity.class);
                            startActivity(it);
                            finish();
                        }

                    }
                }
                else if (text.equals("DATA/HORA")) {
                    if(Tempo.getInstance().dif() == 0){
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPMMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("A DATA/HORA FOI ADQUIRIDA AUTOMATICAMENTO. O SISTEMA NÃO DEIXA ALTERA A MESMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();
                    }
                    else{
                        pmmContext.setContDataHora(1);
                        pmmContext.getConfigCTR().setPosicaoTela(5L);
                        Intent it = new Intent(MenuPrincPMMActivity.this, DataHoraActivity.class);
                        startActivity(it);
                        finish();
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

            textViewDataHora.setText(Tempo.getInstance().dataComHoraSTZ());
            if(Tempo.getInstance().dif() == 0){
                textViewDataHora.setTextColor(Color.GREEN);
            }
            else{
                textViewDataHora.setTextColor(Color.RED);
            }

            if(pmmContext.getConfigCTR().getConfig().getVerInforConfig() == 2){
                Intent it = new Intent( MenuPrincPMMActivity.this, DadosColheitaActivity.class);
                startActivity(it);
                finish();
            }

            customHandler.postDelayed(this, 10000);
        }
    };

}
