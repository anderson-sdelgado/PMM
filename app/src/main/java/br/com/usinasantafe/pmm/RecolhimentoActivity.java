package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.tb.variaveis.RecolhimentoTO;

public class RecolhimentoActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private RecolhimentoTO recolhimentoTO;
    private List recolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recolhimento);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkRecolMang = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancRecolMang = (Button) findViewById(R.id.buttonCancPadrao);
        TextView textViewRecolMang = (TextView) findViewById(R.id.textViewPadrao);
        EditText editText = (EditText) findViewById(R.id.editTextPadrao);

        int cont = 0;

        if (pmmContext.getVerPosTela() == 4) {
            cont = pmmContext.getContRecolhimento() - 1;
        } else if (pmmContext.getVerPosTela() == 14) {
            cont = pmmContext.getPosRecolhimento();
        }

        recolhimentoTO = new RecolhimentoTO();
        recolList = recolhimentoTO.orderBy("idRecol", true);
        recolhimentoTO = (RecolhimentoTO) recolList.get(cont);

        textViewRecolMang.setText("OS: " + recolhimentoTO.getNroOSRecol() + " \nRECOL. MANGUEIRA:");
        if (recolhimentoTO.getValorRecol() > 0) {
            editText.setText(String.valueOf(recolhimentoTO.getValorRecol()));
        } else {
            editText.setText("");
        }

        buttonOkRecolMang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pmmContext.getVerPosTela() == 4) {

                    if (!editTextPadrao.getText().toString().equals("")) {

                        Long valorRecolMang = Long.parseLong(editTextPadrao.getText().toString());

                        recolhimentoTO.setValorRecol(valorRecolMang);
                        recolhimentoTO.setDthrRecol(Tempo.getInstance().datahora());
                        recolhimentoTO.setStatusRecol(2L);
                        recolhimentoTO.update();
                        recolhimentoTO.commit();

                        if (recolList.size() == pmmContext.getContRecolhimento()) {

                            ManipDadosEnvio.getInstance().salvaBoletimFechadoFert();
                            ManipDadosEnvio.getInstance().envioDadosPrinc();
                            Intent it = new Intent(RecolhimentoActivity.this, MenuInicialActivity.class);
                            startActivity(it);
                            finish();

                        } else {

                            pmmContext.setContRecolhimento(pmmContext.getContRecolhimento() + 1);
                            Intent it = new Intent(RecolhimentoActivity.this, RecolhimentoActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                } else if (pmmContext.getVerPosTela() == 14) {

                    if (!editTextPadrao.getText().toString().equals("")) {

                        Long valorRecolMang = Long.parseLong(editTextPadrao.getText().toString());

                        recolhimentoTO.setValorRecol(valorRecolMang);
                        recolhimentoTO.update();
                        recolhimentoTO.commit();

                    }

                    Intent it = new Intent(RecolhimentoActivity.this, ListaOSRecolActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancRecolMang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });


    }

    public void onBackPressed() {
        if (pmmContext.getVerPosTela() == 4) {
            if(pmmContext.getPosRecolhimento() > 1){
                pmmContext.setPosRecolhimento(pmmContext.getPosRecolhimento() - 1);
                Intent it = new Intent(RecolhimentoActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();
            }
            else{
                Intent it = new Intent(RecolhimentoActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            }
        } else if (pmmContext.getVerPosTela() == 14) {
            Intent it = new Intent(RecolhimentoActivity.this, ListaOSRendActivity.class);
            startActivity(it);
            finish();
        }
    }
}
