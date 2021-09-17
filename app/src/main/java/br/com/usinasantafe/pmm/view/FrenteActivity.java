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

public class FrenteActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frente);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkFrente = findViewById(R.id.buttonOkPadrao);
        Button buttonCancFrente = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualFrente = findViewById(R.id.buttonAtualPadrao);

        buttonAtualFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connectNetwork) {

                    progressBar = new ProgressDialog(FrenteActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pmmContext.getMotoMecFertCTR().atualDados(FrenteActivity.this, FrenteActivity.class, progressBar, "Leira", 1);

                }
                else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(FrenteActivity.this);
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

        buttonOkFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    if (pmmContext.getConfigCTR().verFrente(Long.parseLong(editTextPadrao.getText().toString()))) {

                        pmmContext.getConfigCTR().setIdFrente(Long.parseLong(editTextPadrao.getText().toString()));

                        Intent it = new Intent(FrenteActivity.this, PropriedadeActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(FrenteActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FRENTE INCORRETA! POR FAVOR, VERIFIQUE A NUMERAÇÃO DA FRENTE E DIGITE NOVAMENTE.");

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

        buttonCancFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(FrenteActivity.this, MenuPrincECMActivity.class);
        startActivity(it);
        finish();
    }

}