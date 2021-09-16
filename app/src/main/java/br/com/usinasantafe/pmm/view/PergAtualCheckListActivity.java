package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class PergAtualCheckListActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perg_atual_check_list);

        pmmContext = (PMMContext) getApplication();

        Button buttonSimAtualCL = findViewById(R.id.buttonSimAtualCL);
        Button buttonNaoAtualCL = findViewById(R.id.buttonNaoAtualCL);

        buttonNaoAtualCL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pmmContext.getCheckListCTR().setPosCheckList(1);
                Intent it = new Intent(PergAtualCheckListActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonSimAtualCL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (connectNetwork) {

                    progressBar = new ProgressDialog(PergAtualCheckListActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO CHECKLIST...");
                    progressBar.show();

                    customHandler.postDelayed(updateTimerThread, 10000);

                    pmmContext.getCheckListCTR().atualCheckList(String.valueOf(pmmContext.getConfigCTR().getEquip().getNroEquip()), PergAtualCheckListActivity.this, ItemCheckListActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder( PergAtualCheckListActivity.this);
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

    }

    public void onBackPressed()  {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(VerifDadosServ.status < 3) {

                VerifDadosServ.getInstance().cancel();

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                AlertDialog.Builder alerta = new AlertDialog.Builder(PergAtualCheckListActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA ATUALIZAÇÃO DE CHECKLIST! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
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
