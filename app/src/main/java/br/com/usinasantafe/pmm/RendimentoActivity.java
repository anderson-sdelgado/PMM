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

import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.tb.estaticas.OSTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RendimentoTO;

public class RendimentoActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private RendimentoTO rendimentoTO;
    private List rendList;

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
            cont = pmmContext.getContRendimento() - 1;
        }
        else if(pmmContext.getVerPosTela() == 7){
            cont = pmmContext.getPosRendimento();
        }

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List listBoletim = boletimMMTO.get("statusBoletim", 1L);
        boletimMMTO = (BoletimMMTO) listBoletim.get(0);
        listBoletim.clear();

        rendimentoTO = new RendimentoTO();
        rendList = rendimentoTO.getAndOrderBy("idBolRendimento", boletimMMTO.getIdBoletim(), "idRendimento", true);
        rendimentoTO = (RendimentoTO) rendList.get(cont);

        textViewRendimento.setText("OS " + rendimentoTO.getNroOSRendimento() +" \nRendimento :");
        if(rendimentoTO.getValorRendimento() > 0){
            editText.setText(String.valueOf(rendimentoTO.getValorRendimento()).replace(".", ","));
        }
        else{
            editText.setText("");
        }

        buttonOkRendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                if(pmmContext.getVerPosTela() == 4){

                    if (!editTextPadrao.getText().toString().equals("")) {

                        String rend = editTextPadrao.getText().toString();
                        Double rendNum = Double.valueOf(rend.replace(",", "."));

                        OSTO osTO = new OSTO();
                        List osList = osTO.get("nroOS", rendimentoTO.getNroOSRendimento());
                        if (osList.size() > 0) {
                            osTO = (OSTO) osList.get(0);
                        } else {
                            osTO.setAreaProgrOS(150D);
                        }

                        if (rendNum <= osTO.getAreaProgrOS()) {

                            rendimentoTO.setValorRendimento(rendNum);
                            rendimentoTO.setDthrRendimento(Tempo.getInstance().datahora());
                            rendimentoTO.update();
                            rendimentoTO.commit();

                            if (rendList.size() == pmmContext.getContRendimento()) {

                                ManipDadosEnvio.getInstance().salvaBoletimFechadoMM();
                                ManipDadosEnvio.getInstance().envioDadosPrinc();
                                Intent it = new Intent(RendimentoActivity.this, MenuInicialActivity.class);
                                startActivity(it);
                                finish();

                            } else {

                                pmmContext.setContRendimento(pmmContext.getContRendimento() + 1);
                                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);
                                startActivity(it);
                                finish();

                            }

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

                }else if(pmmContext.getVerPosTela() == 7){

                    if (!editTextPadrao.getText().toString().equals("")) {

                        String rend = editTextPadrao.getText().toString();
                        Double rendNum = Double.valueOf(rend.replace(",", "."));

                        OSTO osTO = new OSTO();
                        List osList = osTO.get("nroOS", rendimentoTO.getNroOSRendimento());
                        if (osList.size() > 0) {
                            osTO = (OSTO) osList.get(0);
                        } else {
                            osTO.setAreaProgrOS(150D);
                        }

                        if (rendNum <= osTO.getAreaProgrOS()) {

                            rendimentoTO.setValorRendimento(rendNum);
                            rendimentoTO.update();
                            rendimentoTO.commit();

                            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);
                            startActivity(it);
                            finish();

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
                    else{
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
            Intent it = new Intent(RendimentoActivity.this, HorimetroActivity.class);
            startActivity(it);
            finish();
        }
        else if(pmmContext.getVerPosTela() == 7){
            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);
            startActivity(it);
            finish();
        }
    }

}
