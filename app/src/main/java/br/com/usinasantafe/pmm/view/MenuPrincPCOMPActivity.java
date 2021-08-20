package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pmm.util.ConnectNetwork;

public class MenuPrincPCOMPActivity extends ActivityGeneric {

    private ListView motoMecListView;
    private PMMContext pmmContext;
    private TextView textViewMotorista;
    private int posicao;
    private List<MotoMecBean> motoMecList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_pcomp);

        pmmContext = (PMMContext) getApplication();
        pmmContext.setVerTelaLeira(false);

        Button buttonParadaMotoMec = findViewById(R.id.buttonParadaMotoMec);
        Button buttonRetMotoMec = findViewById(R.id.buttonRetMotoMec);
        textViewMotorista = findViewById(R.id.textViewMotorista);

        textViewMotorista.setText(pmmContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc() + " - " + pmmContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());

        ArrayList<String> motoMecArrayList = new ArrayList<String>();
        motoMecList = pmmContext.getMotoMecFertCTR().motoMecList();
        for (MotoMecBean motoMecBean : motoMecList) {
            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, motoMecArrayList);
        motoMecListView = findViewById(R.id.motoMecListView);
        motoMecListView.setAdapter(adapterList);

        motoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                posicao = position;
                MotoMecBean motoMecBean = motoMecList.get(position);
                pmmContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);

                ConnectNetwork connectNetwork = new ConnectNetwork();
                if (connectNetwork.verificaConexao(MenuPrincPCOMPActivity.this)) {
                    pmmContext.getConfigCTR().setStatusConConfig(1L);
                }
                else{
                    pmmContext.getConfigCTR().setStatusConConfig(0L);
                }

                if (pmmContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                    Toast.makeText(MenuPrincPCOMPActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                } else {

                    if (pmmContext.getMotoMecFertCTR().verifBackupApont()) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
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
                                || (motoMecBean.getCodFuncaoOperMotoMec() == 11)) {  // ATIVIDADES NORMAIS

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude());
                                    motoMecListView.setSelection(posicao + 1);

                                }
                            });

                            alerta.show();

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) {

                            if (pmmContext.getConfigCTR().getConfig().getPosFluxoViagem() == 0) {

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());

                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        pmmContext.getConfigCTR().setPosFluxoViagem(1L);
                                        pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude());
                                        motoMecListView.setSelection(posicao + 1);

                                    }
                                });

                                alerta.show();

                            } else {

                                String msg = "";

                                if (pmmContext.getConfigCTR().getConfig().getPosFluxoViagem() == 1) {
                                    msg = "POR FAVOR, CARREGUE O EQUIPAMENTO E DEPOIS PASSE NA BALANÇA PARA FAZER A PESAGEM CARREGADO!";
                                } else if (pmmContext.getConfigCTR().getConfig().getPosFluxoViagem() == 2) {
                                    msg = "POR FAVOR, PASSE NA BALANÇA PARA FAZER A PESAGEM DO EQUIPAMENTO CARREGADO!";
                                }

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage(msg);
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        motoMecListView.setSelection(posicao + 1);
                                    }
                                });

                                alerta.show();

                            }

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) {

                            pmmContext.getConfigCTR().setPosicaoTela(2L);
                            Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) {

                            if (pmmContext.getConfigCTR().getConfig().getPosFluxoViagem() == 1) {

                                if (pmmContext.getConfigCTR().getOS().getTipoOS() == 0L) {

                                    pmmContext.getConfigCTR().setPosFluxoViagem(2L);
                                    Intent it = new Intent(MenuPrincPCOMPActivity.this, DigLeiraActivity.class);
                                    startActivity(it);
                                    finish();

                                } else if (pmmContext.getConfigCTR().getOS().getTipoOS() == 1L) {

                                    Intent it = new Intent(MenuPrincPCOMPActivity.this, ProdutoActivity.class);
                                    startActivity(it);
                                    finish();

                                }

                            } else {

                                String msg = "";

                                if (pmmContext.getConfigCTR().getConfig().getPosFluxoViagem() == 0) {
                                    msg = "POR FAVOR, TIRE A PESAGEM TARA DO EQUIPAMENTO!";
                                } else if (pmmContext.getConfigCTR().getConfig().getPosFluxoViagem() == 2) {
                                    msg = "POR FAVOR, PASSE NA BALANÇA PARA FAZER A PESAGEM DO EQUIPAMENTO CARREGADO!";
                                }

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage(msg);
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        motoMecListView.setSelection(posicao + 1);
                                    }
                                });

                                alerta.show();

                            }

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) {

                            if (pmmContext.getConfigCTR().getConfig().getPosFluxoViagem() == 2) {

                                pmmContext.getConfigCTR().setPosicaoTela(13L);
                                pmmContext.getConfigCTR().setPosFluxoViagem(0L);
                                Intent it = new Intent(MenuPrincPCOMPActivity.this, EsperaInforActivity.class);
                                startActivity(it);
                                finish();

                            } else {

                                String msg = "";

                                if (pmmContext.getConfigCTR().getConfig().getPosFluxoViagem() == 0) {
                                    msg = "POR FAVOR, TIRE A PESAGEM TARA DO EQUIPAMENTO!";
                                } else if (pmmContext.getConfigCTR().getConfig().getPosFluxoViagem() == 1) {
                                    msg = "POR FAVOR, CARREGUE O EQUIPAMENTO E DEPOIS PASSE NA BALANÇA PARA FAZER A PESAGEM CARREGADO!";
                                }

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage(msg);
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        motoMecListView.setSelection(posicao + 1);
                                    }
                                });

                                alerta.show();

                            }

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 7) {

                            pmmContext.getConfigCTR().setPosicaoTela(2L);
                            Intent it = new Intent(MenuPrincPCOMPActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 10) {

                            pmmContext.getConfigCTR().setPosicaoTela(15L);
                            Intent it = new Intent(MenuPrincPCOMPActivity.this, DigLeiraActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else if (motoMecBean.getCodFuncaoOperMotoMec() == 5) {

                            if (pmmContext.getCompostoCTR().pesqLeiraExibir()) {

                                pmmContext.getConfigCTR().setPosicaoTela(5L);
                                Intent it = new Intent(MenuPrincPCOMPActivity.this, InformacaoActivity.class);
                                startActivity(it);
                                finish();

                            } else {

                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPCOMPActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("NÃO CONTÉM NENHUMA LEIRA PARA CARREGAMENTO OU DESCARREGAMENTO.");
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

        buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincPCOMPActivity.this, MenuParadaPCOMPActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonRetMotoMec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pmmContext.getConfigCTR().setPosicaoTela(8L);
                Intent it = new Intent(MenuPrincPCOMPActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}