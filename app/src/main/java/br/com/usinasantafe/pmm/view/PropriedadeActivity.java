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

public class PropriedadeActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propriedade);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkPropriedade = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPropriedade = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPropriedade = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPropriedade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connectNetwork) {

                    progressBar = new ProgressDialog(PropriedadeActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pmmContext.getMotoMecFertCTR().atualDados(PropriedadeActivity.this, PropriedadeActivity.class, progressBar, "Propriedade", 1);

                }
                else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);
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

        buttonOkPropriedade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    if (pmmContext.getConfigCTR().verPropriedade(Long.parseLong(editTextPadrao.getText().toString()))) {

                        pmmContext.getConfigCTR().setIdPropriedade(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(PropriedadeActivity.this, MsgPropriedadeActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("PROPRIEDADE INCORRETA! POR FAVOR, VERIFIQUE O CÓDIGO DA PROPRIEDADE E DIGITE NOVAMENTE.");
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

        buttonCancPropriedade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(PropriedadeActivity.this, FrenteActivity.class);
        startActivity(it);
        finish();
    }
}