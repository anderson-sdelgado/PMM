package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.control.MotoMecFertCTR;
import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;

public class RecolhimentoActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private RecolhFertBean recolhFertBean;
    private MotoMecFertCTR motoMecFertCTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recolhimento);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkRecolMang = findViewById(R.id.buttonOkPadrao);
        Button buttonCancRecolMang = findViewById(R.id.buttonCancPadrao);
        TextView textViewRecolMang = findViewById(R.id.textViewPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        motoMecFertCTR = new MotoMecFertCTR();

        int cont = 0;

        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            cont = pmmContext.getMotoMecFertCTR().getContRecolh() - 1;
        } else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 9L) {
            cont = pmmContext.getMotoMecFertCTR().getPosRecolh();
        }

        recolhFertBean =  motoMecFertCTR.getRecolh(cont);

        textViewRecolMang.setText("OS: " + recolhFertBean.getNroOSRecolhFert() + " \nRECOL. MANGUEIRA:");
        if (recolhFertBean.getValorRecolhFert() > 0) {
            editText.setText(String.valueOf(recolhFertBean.getValorRecolhFert()));
        } else {
            editText.setText("");
        }

        buttonOkRecolMang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {
                    verTela();
                }
                else{
                    if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 9L){
                        Intent it = new Intent(RecolhimentoActivity.this, MenuPrincPMMActivity.class);
                        startActivity(it);
                        finish();
                    }
                }

            }

        });

        buttonCancRecolMang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });


    }

    public void onBackPressed() {
        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            if(pmmContext.getMotoMecFertCTR().getPosRecolh() > 1){
                pmmContext.getMotoMecFertCTR().setPosRecolh(pmmContext.getMotoMecFertCTR().getPosRecolh() - 1);
                Intent it = new Intent(RecolhimentoActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();
            }
            else{
                Intent it = new Intent(RecolhimentoActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            }
        } else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 9L) {
            Intent it = new Intent(RecolhimentoActivity.this, ListaOSRendActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void verTela(){

        Long valorRecolMang = Long.parseLong(editTextPadrao.getText().toString());
        recolhFertBean.setValorRecolhFert(valorRecolMang);
        pmmContext.getMotoMecFertCTR().atualRecolh(recolhFertBean);

        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            if (pmmContext.getMotoMecFertCTR().qtdeRecolh() == pmmContext.getMotoMecFertCTR().getContRecolh()) {
                pmmContext.getMotoMecFertCTR().salvarBolMMFertFechado();
                Intent it = new Intent(RecolhimentoActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            } else {
                pmmContext.getMotoMecFertCTR().setContRecolh(pmmContext.getMotoMecFertCTR().getContRecolh() + 1);
                Intent it = new Intent(RecolhimentoActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();
            }
        }
        else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 9L) {
            Intent it = new Intent(RecolhimentoActivity.this, ListaOSRecolhActivity.class);
            startActivity(it);
            finish();
        }
    }

}
