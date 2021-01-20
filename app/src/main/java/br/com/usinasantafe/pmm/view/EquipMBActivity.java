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
import br.com.usinasantafe.pmm.control.CheckListCTR;

public class EquipMBActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_bomba);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkMotoBomba = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancMotoBomba = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

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

                            pmmContext.getBoletimCTR().atualDadosEquipSeg(EquipMBActivity.this, EquipMBActivity.class, progressBar);

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

                    if (pmmContext.getBoletimCTR().verMotoBomba(motoBomba)) {

                        pmmContext.getBoletimCTR().setIdEquipBombaBol(pmmContext.getBoletimCTR().getEquipSeg(motoBomba).getIdEquip());
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
        pmmContext.getBoletimCTR().salvarBolAbertoFert();
        CheckListCTR checkListCTR = new CheckListCTR();
        if(checkListCTR.verAberturaCheckList(pmmContext.getBoletimCTR().getTurno())){
            pmmContext.getApontCTR().inserirParadaCheckList(pmmContext.getBoletimCTR());
            pmmContext.setPosCheckList(1);
            checkListCTR.createCabecAberto(pmmContext.getBoletimCTR());
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
