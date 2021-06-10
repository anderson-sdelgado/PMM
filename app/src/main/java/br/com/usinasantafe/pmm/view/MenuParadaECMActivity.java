package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pmm.util.ConexaoWeb;
import br.com.usinasantafe.pmm.util.Tempo;

public class MenuParadaECMActivity extends ActivityGeneric {

    private ListView paradaListView;
    private PMMContext pmmContext;
    private List<MotoMecBean> paradaList;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_parada_ecm);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);

        ArrayList<String> itens = new ArrayList<String>();
        paradaList = pmmContext.getMotoMecFertCTR().paradaList();
        for(MotoMecBean motoMecBean : paradaList){
            itens.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        paradaListView = (ListView) findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(adapterList);

        paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                posicao = position;
                MotoMecBean motoMecBean = paradaList.get(posicao);
//                pmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);

                if (pmmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
                    Toast.makeText(MenuParadaECMActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 17)) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaECMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Long statusCon;
                                ConexaoWeb conexaoWeb = new ConexaoWeb();
                                if (conexaoWeb.verificaConexao(MenuParadaECMActivity.this)) {
                                    statusCon = 1L;
                                } else {
                                    statusCon = 0L;
                                }
//                                pmmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
                                paradaListView.setSelection(posicao + 1);
                            }
                        });

                        alerta.show();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 11) { //DESENGATE

                        if (pmmContext.getMotoMecFertCTR().hasElemCarreta()) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");

                            alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    pmmContext.setVerPosTela(6);
                                    Intent it = new Intent(MenuParadaECMActivity.this, DesengCarretaActivity.class);
                                    MotoMecBean motoMec = (MotoMecBean) paradaList.get(posicao);
                                    startActivity(it);
                                    finish();

                                }
                            });

                            alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();

                        }

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 12) { //ENGATE

                        if (!pmmContext.getMotoMecFertCTR().hasElemCarreta()) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("DESEJA REALMENTE ENGATAR AS CARRETAS?");

                            alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    pmmContext.setVerPosTela(7);
                                    Intent it = new Intent(MenuParadaECMActivity.this, MsgNumCarretaActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                            });

                            alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });

                            alerta.show();

                        }

                    }

                }

            }

        });

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pmmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
                    Toast.makeText(MenuParadaECMActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Long statusCon;
                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                    if (conexaoWeb.verificaConexao(MenuParadaECMActivity.this)) {
                        statusCon = 1L;
                    } else {
                        statusCon = 0L;
                    }

//                    pmmContext.getMotoMecFertCTR().setAtivApont(pmmContext.getConfigCTR().getConfig().getAtivConfig());
//                    pmmContext.getMotoMecFertCTR().insApontMM(getLongitude(), getLatitude(), statusCon);

                    Intent it = new Intent(MenuParadaECMActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }
}