package br.com.usinasantafe.pmm.view;

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

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pmm.util.ConnectNetwork;
import br.com.usinasantafe.pmm.util.Tempo;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView turnoListView;
    private List<TurnoBean> turnoList;
    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetTurno = findViewById(R.id.buttonRetTurno);
        Button buttonAtualTurno = findViewById(R.id.buttonAtualTurno);

        buttonAtualTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaTurnoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConnectNetwork connectNetwork = new ConnectNetwork();

                        if (connectNetwork.verificaConexao(ListaTurnoActivity.this)) {

                            progressBar = new ProgressDialog(ListaTurnoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pmmContext.getMotoMecFertCTR().atualDadosTurno(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar);

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

        turnoList = pmmContext.getMotoMecFertCTR().getTurnoCodList();

        ArrayList<String> itens = new ArrayList<String>();

        for(TurnoBean turnoBean : turnoList){
            itens.add(turnoBean.getDescTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = findViewById(R.id.listaTurno);
        turnoListView.setAdapter(adapterList);

        turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TurnoBean turnoBean = turnoList.get(position);
                turnoList.clear();

                pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().setIdTurnoBolMMFert(turnoBean.getIdTurno());

                if(Tempo.getInstance().verDthrServ(pmmContext.getConfigCTR().getConfig().getDtServConfig())){
                    pmmContext.getConfigCTR().setDifDthrConfig(0L);
                    Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    if(pmmContext.getConfigCTR().getConfig().getDifDthrConfig() == 0){
                        pmmContext.setContDataHora(1);
                        Intent it = new Intent(ListaTurnoActivity.this, MsgDataHoraActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }
                }

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
