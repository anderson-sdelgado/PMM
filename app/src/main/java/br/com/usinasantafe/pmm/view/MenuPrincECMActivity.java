package br.com.usinasantafe.pmm.view;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pmm.util.ConexaoWeb;
import br.com.usinasantafe.pmm.util.Tempo;

public class MenuPrincECMActivity extends ActivityGeneric {

    private ListView motoMecListView;
    private PMMContext pmmContext;
    private TextView textViewMotorista;
    private TextView textViewCarreta;
    private TextView textViewUltimaViagem;
    private ProgressDialog progressBar;
    private List<MotoMecBean> motoMecList;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_ecm);

        pmmContext = (PMMContext) getApplication();

        Button buttonRetMotoMec = (Button) findViewById(R.id.buttonRetMotoMec);
        Button buttonParadaMotoMec = (Button) findViewById(R.id.buttonParadaMotoMec);
        textViewMotorista = (TextView) findViewById(R.id.textViewMotorista);
        textViewCarreta = (TextView) findViewById(R.id.textViewCarreta);
        textViewUltimaViagem = (TextView) findViewById(R.id.textViewUltimaViagem);

        listarMenu();

        buttonRetMotoMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ENCERRA O BOLETIM?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pmmContext.setVerPosTela(8);
                        Intent it = new Intent(MenuPrincECMActivity.this, HorimetroActivity.class);
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
        });

        buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincECMActivity.this, MenuParadaECMActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void listarMenu() {

        textViewMotorista.setText(pmmContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc() + " - " + pmmContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());
        textViewCarreta.setText(pmmContext.getMotoMecFertCTR().getDescrCarreta());
        textViewUltimaViagem.setText(pmmContext.getCecCTR().getDataSaidaUlt());

        ArrayList<String> motoMecArrayList = new ArrayList<String>();
        motoMecList = pmmContext.getMotoMecFertCTR().motoMecList();
        for (MotoMecBean motoMecBean : motoMecList) {
            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, motoMecArrayList);
        motoMecListView = (ListView) findViewById(R.id.motoMecListView);
        motoMecListView.setAdapter(adapterList);

        motoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                posicao = position;
                MotoMecBean motoMecBean = motoMecList.get(position);
//                ecmContext.getMotoMecCTR().setMotoMecBean(motoMecBean);

                if (pmmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
                    Toast.makeText(MenuPrincECMActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 11)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 12)){  // ATIVIDADES NORMAIS

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Long statusCon;
                                ConexaoWeb conexaoWeb = new ConexaoWeb();
                                if (conexaoWeb.verificaConexao(MenuPrincECMActivity.this)) {
                                    statusCon = 1L;
                                } else {
                                    statusCon = 0L;
                                }

//                                ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
                                motoMecListView.setSelection(posicao + 1);

                            }

                        });

                        alerta.show();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) { // CERTIFICADO

                        pmmContext.setVerPosTela(5);
                        Intent it = new Intent(MenuPrincECMActivity.this, MenuCertifActivity.class);
                        startActivity(it);
                        finish();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) { // SAIDA DA USINA

                        if (pmmContext.getCecCTR().verPreCECAberto()) {

                            String mensagem = "O HORÁRIO DE SAÍDA DA USINA JÁ FOI INSERIDO ANTERIORMENTE. " +
                                    "POR FAVOR TERMINE DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage(mensagem);

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    motoMecListView.setSelection(posicao + 1);
                                }
                            });
                            alerta.show();

                        } else {

                            pmmContext.setVerPosTela(2);
                            Intent it = new Intent(MenuPrincECMActivity.this, FrenteActivity.class);
                            startActivity(it);
                            finish();

                        }

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) { // CHEGADA CAMPO

                        String mensagem = "";

                        if (!pmmContext.getCecCTR().verPreCECAberto()) {
                            mensagem = "É NECESSÁRIO A INSERÇÃO DO HORÁRIO DE SAÍDA DA USINA.";
                        } else {
                            if (pmmContext.getCecCTR().getDataChegCampo().equals("")) {
                                mensagem = "FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec();
                            } else {
                                mensagem = "O HORÁRIO DE CHEGADA AO CAMPO JÁ FOI INSERIDO ANTERIORMENTE. " +
                                        "POR FAVOR TERMINEI DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";
                            }
                        }

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage(mensagem);

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (pmmContext.getCecCTR().verPreCECAberto()) {
                                    if (pmmContext.getCecCTR().getDataChegCampo().equals("")) {
                                        pmmContext.getCecCTR().setDataChegCampo();

                                        Long statusCon;
                                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                                        if (conexaoWeb.verificaConexao(MenuPrincECMActivity.this)) {
                                            statusCon = 1L;
                                        } else {
                                            statusCon = 0L;
                                        }
//                                        ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
                                    }
                                    motoMecListView.setSelection(posicao + 1);
                                }
                            }
                        });

                        alerta.show();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) { // PESAGEM

                        Long statusCon;
                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        if (conexaoWeb.verificaConexao(MenuPrincECMActivity.this)) {
                            statusCon = 1L;
                        } else {
                            statusCon = 0L;
                        }
//                        ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("BUSCANDO BOLETIM...");
                        progressBar.show();

                        pmmContext.getCecCTR().delPreCECAberto();
                        pmmContext.getCecCTR().verCECServ(MenuPrincECMActivity.this, CECActivity.class, progressBar);

                    } else if ((motoMecBean.getCodFuncaoOperMotoMec() == 8)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 19)) { // DESENGATE

                        if (pmmContext.getMotoMecFertCTR().hasElemCarreta()) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");

                            alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    pmmContext.setVerPosTela(3);
                                    Intent it = new Intent(MenuPrincECMActivity.this, DesengCarretaActivity.class);
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
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR! ENGATE CARRETA(S) PARA DEPOIS DESENGATÁ-LAS.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alerta.show();

                        }

                    } else if ((motoMecBean.getCodFuncaoOperMotoMec() == 9)
                            || (motoMecBean.getCodFuncaoOperMotoMec() == 20)) { // ENGATE

                        if (!pmmContext.getMotoMecFertCTR().hasElemCarreta()) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("DESEJA REALMENTE ENGATAR AS CARRETAS?");

                            alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    pmmContext.setVerPosTela(4);
                                    Intent it = new Intent(MenuPrincECMActivity.this, MsgNumCarretaActivity.class);
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
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
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

        });

    }

    public void onBackPressed() {
    }

}