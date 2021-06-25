package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.util.ConexaoWeb;

public class EquipMBActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_bomba);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkMotoBomba = findViewById(R.id.buttonOkPadrao);
        Button buttonCancMotoBomba = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(EquipMBActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(EquipMBActivity.this)) {

                            progressBar = new ProgressDialog(EquipMBActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pmmContext.getMotoMecFertCTR().atualDadosEquipSeg(EquipMBActivity.this, EquipMBActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(EquipMBActivity.this);
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

        buttonOkMotoBomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    Long motoBomba = Long.parseLong(editTextPadrao.getText().toString());

                    if (pmmContext.getMotoMecFertCTR().verMotoBomba(motoBomba)) {

                        pmmContext.getMotoMecFertCTR().getBoletimMMDAO().getBoletimMMBean().setIdEquipBombaBolMMFert(pmmContext.getMotoMecFertCTR().getEquipSeg(motoBomba).getIdEquip());
                        salvarBoletimAberto();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(EquipMBActivity.this);
                        alerta.setTitle("ATENCAO");
                        alerta.setMessage("NUMERAÇÃO DA MOTO BOMBA INCORRETA. FAVOR, VERIFICAR A NUMERAÇÃO OU ATUALIZAR A BASE DE DADOS NOVAMENTE!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();


                    }

                }


            }
        });

        buttonCancMotoBomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void salvarBoletimAberto() {
        pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto();
        if(pmmContext.getCheckListCTR().verAberturaCheckList(pmmContext.getMotoMecFertCTR().getBoletimMMDAO().getBoletimMMBean().getIdTurnoBolMMFert())){
            pmmContext.getMotoMecFertCTR().inserirParadaCheckList();
            pmmContext.setPosCheckList(1);
            pmmContext.getCheckListCTR().createCabecAberto();
            if (pmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {
                Intent it = new Intent(EquipMBActivity.this, PergAtualCheckListActivity.class);
                startActivity(it);
                finish();
            } else {
                Intent it = new Intent(EquipMBActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();
            }
        }
        else{
            Intent it = new Intent(EquipMBActivity.this, EsperaInforActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void onBackPressed() {
        pmmContext.setContImplemento(pmmContext.getContImplemento() - 1);
        Intent it = new Intent(EquipMBActivity.this, HorimetroActivity.class);
        startActivity(it);
        finish();
    }

}
