package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.util.ConexaoWeb;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.bean.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.bean.estaticas.RFuncaoAtivParTO;

public class ListaAtividadeActivity extends ActivityGeneric {

    private ListView atividadeListView;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private ArrayList ativArrayList;
    private Long nroOS = 0L;
    private ConfigCTR configCTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade);

        pmmContext = (PMMContext) getApplication();

        Button buttonAtualAtividade = (Button) findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = (Button) findViewById(R.id.buttonRetAtividade);
        TextView textViewTituloAtividade = (TextView) findViewById(R.id.textViewTituloAtividade);

        configCTR = new ConfigCTR();
        nroOS =  configCTR.getConfig().getOsConfig();

        buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaAtividadeActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Atividades...");
                    progressBar.show();

                    pmmContext.getBoletimCTR().verAtiv( String.valueOf(nroOS), ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);

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

        ativArrayList = pmmContext.getBoletimCTR().retAtivFiltrArrayList(nroOS);

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < ativArrayList.size(); i++) {
            AtividadeTO atividadeTO = (AtividadeTO) ativArrayList.get(i);
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

                AtividadeTO atividadeTO = (AtividadeTO) ativArrayList.get(position);
                ativArrayList.clear();

                if (pmmContext.getVerPosTela() == 1) {

                    pmmContext.getBoletimCTR().setAtivBol(atividadeTO.getIdAtiv());
                    pmmContext.setTextoHorimetro("HORÍMETRO INICIAL:");

                    Intent it = new Intent(ListaAtividadeActivity.this, HorimetroActivity.class);
                    startActivity(it);
                    finish();

                } else if ((pmmContext.getVerPosTela() == 2)) {

                    pmmContext.getApontMMMovLeiraCTR().setOSApont(nroOS);
                    pmmContext.getApontMMMovLeiraCTR().setAtivApont(atividadeTO.getIdAtiv());

                    if (pmmContext.getApontMMMovLeiraCTR().verifBackupApont()) {

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

                        if (configCTR.getEquip().getTipo() == 1) {

                            List rFuncaoAtivParList = pmmContext.getBoletimCTR().retFuncaoAtivParList(atividadeTO.getIdAtiv());

                            boolean transbordo = false;
                            boolean rendimento = false;

                            for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                                RFuncaoAtivParTO rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(i);
                                if(rFuncaoAtivParTO.getCodFuncao() == 2){
                                    transbordo = true;
                                }
                                if(rFuncaoAtivParTO.getCodFuncao() == 1){
                                    rendimento = true;
                                }
                            }

                            if(transbordo){
                                Intent it = new Intent(ListaAtividadeActivity.this, TransbordoActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{

                                pmmContext.getApontMMMovLeiraCTR().salvarApont();
                                if (rendimento) {
                                    pmmContext.getBoletimCTR().insRend(nroOS);
                                }

                                Intent it = new Intent(ListaAtividadeActivity.this, MenuPrincNormalActivity.class);
                                startActivity(it);
                                finish();

                            }

                        }else{
                            Intent it = new Intent(ListaAtividadeActivity.this, ListaBocalFertActivity.class);
                            startActivity(it);
                            finish();
                        }

                    }

                } else if(pmmContext.getVerPosTela() == 3) {

                    pmmContext.getApontMMMovLeiraCTR().setAtivApont(atividadeTO.getIdAtiv());

                    Intent it = new Intent(ListaAtividadeActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

    }

    public void onBackPressed() {
    }

}
