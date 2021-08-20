package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        Button buttonRetMenuParada = findViewById(R.id.buttonRetMenuParada);

        ArrayList<String> itens = new ArrayList<String>();
        paradaList = pmmContext.getMotoMecFertCTR().paradaList();
        for(MotoMecBean motoMecBean : paradaList){
            itens.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        paradaListView = findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(adapterList);

        paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                posicao = position;
                MotoMecBean motoMecBean = paradaList.get(posicao);
                pmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);

                Log.i("ECM", "Funcao = " + motoMecBean.getCodFuncaoOperMotoMec());

                if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                    Toast.makeText(MenuParadaECMActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    if (pmmContext.getMotoMecFertCTR().verifBackupApont()) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaECMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        alerta.show();

                    } else {

                        if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)
                                || (motoMecBean.getCodFuncaoOperMotoMec() == 17)) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (connectNetwork) {
                                        pmmContext.getConfigCTR().setStatusConConfig(1L);
                                    } else {
                                        pmmContext.getConfigCTR().setStatusConConfig(0L);
                                    }
                                    pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude());
                                    paradaListView.setSelection(posicao + 1);
                                }
                            });

                            alerta.show();

                        } else if (motoMecBean.getCodFuncaoOperMotoMec() == 15) { //DESENGATE

                            if (pmmContext.getMotoMecFertCTR().hasElemCarreta()) {

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaECMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");

                                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        pmmContext.getConfigCTR().setPosicaoTela(21L);
                                        Intent it = new Intent(MenuParadaECMActivity.this, DesengCarretaActivity.class);
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

                            } else {

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaECMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("POR FAVOR! ENGATE CARRETA(S) PARA DEPOIS DESENGATÁ-LAS.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();

                            }

                        } else if (motoMecBean.getCodFuncaoOperMotoMec() == 16) { //ENGATE

                            if (!pmmContext.getMotoMecFertCTR().hasElemCarreta()) {

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaECMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("DESEJA REALMENTE ENGATAR AS CARRETAS?");

                                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        pmmContext.getConfigCTR().setPosicaoTela(22L);
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

                            } else {

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuParadaECMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("POR FAVOR! DESENGATE CARRETA(S) PARA DEPOIS ENGATÁ-LAS.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();

                            }

                        }

                    }

                }

            }

        });

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                    Toast.makeText(MenuParadaECMActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    if (connectNetwork) {
                        pmmContext.getConfigCTR().setStatusConConfig(1L);
                    }
                    else{
                        pmmContext.getConfigCTR().setStatusConConfig(0L);
                    }
                    pmmContext.getMotoMecFertCTR().salvarApont(0L, 0L, getLongitude(), getLatitude());

                    Intent it = new Intent(MenuParadaECMActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }
}