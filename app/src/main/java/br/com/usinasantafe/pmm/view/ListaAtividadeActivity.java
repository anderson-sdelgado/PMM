package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class ListaAtividadeActivity extends ActivityGeneric {

    private ListView atividadeListView;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private ArrayList ativArrayList;
    private Long nroOS = 0L;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade);

        pmmContext = (PMMContext) getApplication();

        Button buttonAtualAtividade = findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = findViewById(R.id.buttonRetAtividade);
        TextView textViewTituloAtividade = findViewById(R.id.textViewTituloAtividade);

        nroOS =  pmmContext.getConfigCTR().getConfig().getNroOSConfig();

        buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (connectNetwork) {

                    if(PMMContext.aplic != 2) {

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("Atualizando Atividades...");
                        progressBar.show();

                        customHandler.postDelayed(updateTimerThread, 10000);

                        pmmContext.getMotoMecFertCTR().verAtiv(String.valueOf(nroOS), ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);

                    }
                    else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("A ATIVIDADES SÃO ATUALIZADAS AUTOMATICAMENTE APENAS DEPOIS DA PESAGEM NA BALANÇA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();

                    }

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
                finish();
            }
        });

        if ((pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)
                || (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L)) {
            textViewTituloAtividade.setText("ATIVIDADE PRINCIPAL");
        } else {
            textViewTituloAtividade.setText("ATIVIDADE");
        }

        ativArrayList = pmmContext.getMotoMecFertCTR().getAtivArrayList(nroOS);

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < ativArrayList.size(); i++) {
            AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(i);
            itens.add(atividadeBean.getCodAtiv() + " - " + atividadeBean.getDescrAtiv());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        atividadeListView = findViewById(R.id.listAtividade);
        atividadeListView.setAdapter(adapterList);

        atividadeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                if(ativArrayList.size() == 0){

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA SELEÇÃO DE ATIVIDADE. POR FAVOR, SELECIONE NOVAMENTE.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(ListaAtividadeActivity.this, ListaAtividadeActivity.class);
                            startActivity(it);
                            finish();
                        }
                    });
                    alerta.show();

                }
                else {

                    AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(position);
                    ativArrayList.clear();

                    pmmContext.getConfigCTR().setAtivConfig(atividadeBean.getIdAtiv());

                    if ((pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)
                            || (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L)) {

                        Intent it = new Intent(ListaAtividadeActivity.this, HorimetroActivity.class);
                        startActivity(it);
                        finish();

                    } else if ((pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 2L)) {

                        if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(PMMContext.aplic == 1){
                                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPMMActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else if(PMMContext.aplic == 2){
                                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincECMActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else if(PMMContext.aplic == 3){
                                        Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                }
                            });
                            alerta.show();

                        } else {

                            if (pmmContext.getMotoMecFertCTR().verifBackupApont(0L)) {

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

                                if (pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1) {

                                    List<RFuncaoAtivParBean> rFuncaoAtivParList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList();

                                    boolean transbordo = false;
                                    boolean rendimento = false;

                                    for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                                        RFuncaoAtivParBean rFuncaoAtivParBean = rFuncaoAtivParList.get(i);
                                        if (rFuncaoAtivParBean.getCodFuncao() == 2) {
                                            transbordo = true;
                                        }
                                        if (rFuncaoAtivParBean.getCodFuncao() == 1) {
                                            rendimento = true;
                                        }
                                    }
                                    rFuncaoAtivParList.clear();

                                    if (transbordo) {
                                        Intent it = new Intent(ListaAtividadeActivity.this, TransbordoActivity.class);
                                        startActivity(it);
                                        finish();
                                    } else {

                                        pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude());

                                        if (rendimento) {
                                            pmmContext.getMotoMecFertCTR().insRendBD(nroOS);
                                        }

                                        if(PMMContext.aplic == 1){
                                            Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPMMActivity.class);
                                            startActivity(it);
                                            finish();
                                        }
                                        else if(PMMContext.aplic == 3){
                                            Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincPCOMPActivity.class);
                                            startActivity(it);
                                            finish();
                                        }

                                    }

                                } else {
                                    Intent it = new Intent(ListaAtividadeActivity.this, ListaBocalFertActivity.class);
                                    startActivity(it);
                                    finish();
                                }

                            }

                        }

                    } else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 3L) {

                        Intent it = new Intent(ListaAtividadeActivity.this, ListaParadaActivity.class);
                        startActivity(it);
                        finish();

                    } else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L) {

                        pmmContext.getCecCTR().setAtivOS(pmmContext.getCecCTR().getOS().getIdAtivOS());
                        Intent it = new Intent(ListaAtividadeActivity.this, EquipActivity.class);
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

            if(VerifDadosServ.status < 3) {

                VerifDadosServ.getInstance().cancel();

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alerta.show();

            }

        }
    };

}
