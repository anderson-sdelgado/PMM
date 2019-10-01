package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pmm.bean.estaticas.LeiraTO;

public class FechLeiraActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fech_leira);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkFechLeira = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancFechLeira = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonOkFechLeira.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    LeiraTO leiraTO = new LeiraTO();
                    List leiraList = leiraTO.get("codLeira", Long.parseLong(editTextPadrao.getText().toString()));

                    if (leiraList.size() > 0) {

                        leiraTO = (LeiraTO) leiraList.get(0);
                        pmmContext.getBoletimCTR().insMovLeiraFechada(leiraTO.getIdLeira());
                        leiraList.clear();

                        Intent it = new Intent(FechLeiraActivity.this, MenuPrincNormalActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(FechLeiraActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NUMERAÇÃO DE LEIRA INEXISTENTE! FAVOR VERIFICA A MESMA.");
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

        buttonCancFechLeira.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(FechLeiraActivity.this, MenuPrincNormalActivity.class);
        startActivity(it);
        finish();
    }
}
