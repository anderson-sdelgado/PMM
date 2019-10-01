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

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.bean.estaticas.TurnoTO;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView turnoListView;
    private List turnoList;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private ConfigCTR configCTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetTurno = (Button) findViewById(R.id.buttonRetTurno);
        Button buttonAtualTurno = (Button) findViewById(R.id.buttonAtualTurno);

        buttonAtualTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaTurnoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaTurnoActivity.this)) {

                            progressBar = new ProgressDialog(ListaTurnoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pmmContext.getBoletimCTR().atualDadosTurno(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaTurnoActivity.this);
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

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });

        configCTR = new ConfigCTR();

        TurnoTO turnoTO = new TurnoTO();
        turnoList = turnoTO.get("codTurno", configCTR.getEquip().getCodTurno());

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < turnoList.size(); i++){
            turnoTO = (TurnoTO) turnoList.get(i);
            itens.add(turnoTO.getDescTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = (ListView) findViewById(R.id.listaTurno);
        turnoListView.setAdapter(adapterList);

        turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TurnoTO turnoTO = (TurnoTO) turnoList.get(position);
                pmmContext.getBoletimCTR().setTurnoBol(turnoTO.getIdTurno());
                turnoList.clear();

//                if(Tempo.getInstance().verDthrServ(configTO.getDtServConfig())){
//                    configTO.setDifDthrConfig(0L);
//                    configTO.update();
                    Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
//                }
//                else{
//                    if(configTO.getDifDthrConfig() == 0){
//                        pmmContext.setContDataHora(1);
//                        Intent it = new Intent(ListaTurnoActivity.this, MsgDataHoraActivity.class);
//                        startActivity(it);
//                        finish();
//                    }
//                    else{
//                        Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);
//                        startActivity(it);
//                        finish();
//                    }
//                }

            }

        });

        buttonRetTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaTurnoActivity.this, EquipActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
