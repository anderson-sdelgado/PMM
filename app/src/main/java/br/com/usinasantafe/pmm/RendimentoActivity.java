package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.bean.estaticas.OSTO;
import br.com.usinasantafe.pmm.bean.variaveis.RendMMTO;

public class RendimentoActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private RendMMTO rendMMTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendimento);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkRendimento = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancRendimento = (Button) findViewById(R.id.buttonCancPadrao);
        TextView textViewRendimento = (TextView) findViewById(R.id.textViewPadrao);
        EditText editText = (EditText) findViewById(R.id.editTextPadrao);

        int cont = 0;

        if(pmmContext.getVerPosTela() == 4){
            cont = pmmContext.getContRend() - 1;
        }
        else if(pmmContext.getVerPosTela() == 7){
            cont = pmmContext.getPosRend();
        }

        rendMMTO =  pmmContext.getBoletimCTR().getRend(cont);

        textViewRendimento.setText("OS " + rendMMTO.getNroOSRendMM() +" \nRENDIMENTO :");
        if(rendMMTO.getValorRendMM() > 0){
            editText.setText(String.valueOf(rendMMTO.getValorRendMM()).replace(".", ","));
        }
        else{
            editText.setText("");
        }

        buttonOkRendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextPadrao.getText().toString().equals("")) {
                    verifRend();
                }
                else{
                    if(pmmContext.getVerPosTela() == 7){
                        Intent it = new Intent(RendimentoActivity.this, MenuPrincNormalActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        });

        buttonCancRendimento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        if(pmmContext.getVerPosTela() == 4){
            if(pmmContext.getPosRend() > 1){
                pmmContext.setPosRend(pmmContext.getPosRend() - 1);
                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            }
            else{
                Intent it = new Intent(RendimentoActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            }
        }
        else if(pmmContext.getVerPosTela() == 7){
            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void verTela(Double rendNum){

        rendMMTO.setValorRendMM(rendNum);
        pmmContext.getBoletimCTR().atualRend(rendMMTO);

        if (pmmContext.getVerPosTela() == 4) {
            if (pmmContext.getBoletimCTR().qtdeRend() == pmmContext.getContRend()) {
                pmmContext.getBoletimCTR().salvarBolFechadoMM();
                Intent it = new Intent(RendimentoActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            } else {
                pmmContext.setContRend(pmmContext.getContRend() + 1);
                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            }

        }
        else if (pmmContext.getVerPosTela() == 7) {
            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void verifRend(){
        String rend = editTextPadrao.getText().toString();
        Double rendNum = Double.valueOf(rend.replace(",", "."));
        OSTO osTO = new OSTO();
        List osList = osTO.get("nroOS", rendMMTO.getNroOSRendMM());
        if (osList.size() > 0) {
            osTO = (OSTO) osList.get(0);
        } else {
            osTO.setAreaProgrOS(150D);
        }
        if (rendNum <= osTO.getAreaProgrOS()) {
            verTela(rendNum);
        } else {
            AlertDialog.Builder alerta = new AlertDialog.Builder(RendimentoActivity.this);
            alerta.setTitle("ATENCAO");
            alerta.setMessage("VALOR INFORMADO MAIS ALTO DO QUE O PERMITIDO PRA OS. VALOR VERIFIQUE O VALOR DIGITADO.");
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alerta.show();
        }
    }

}
