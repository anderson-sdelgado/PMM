package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.to.tb.estaticas.PneuTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimPneuTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ItemMedPneuTO;

public class PneuActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pneu);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkPneu = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancPneu = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkPneu.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    pmmContext.getItemMedPneuTO().setNroPneuItemMedPneu(editTextPadrao.getText().toString());

                    PneuTO pneuTO = new PneuTO();
                    List pneuList = pneuTO.get("codPneu", editTextPadrao.getText().toString());

                    if(pneuList.size() == 0){

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(PneuActivity.this)) {

                            progressBar = new ProgressDialog(PneuActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Pneu...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            ManipDadosVerif.getInstance().verDados(editTextPadrao.getText().toString(), "Pneu"
                                    , PneuActivity.this, PressaoEncPneuActivity.class, progressBar);

                        }
                        else{

                            Intent it = new Intent(PneuActivity.this, PressaoEncPneuActivity.class);
                            startActivity(it);

                        }

                    }
                    else{

                        boolean verCad = true;

                        BoletimPneuTO boletimPneuTO = new BoletimPneuTO();
                        List boletimPneuList = boletimPneuTO.get("statusBolPneu", 1L);
                        boletimPneuTO = (BoletimPneuTO) boletimPneuList.get(0);
                        ItemMedPneuTO itemMedPneuTO = new ItemMedPneuTO();
                        List itemMedPneuList = itemMedPneuTO.get("idBolItemMedPneu", boletimPneuTO.getIdBolPneu());
                        for(int i = 0; i < itemMedPneuList.size(); i++) {
                            itemMedPneuTO = (ItemMedPneuTO) itemMedPneuList.get(i);
                            if(editTextPadrao.getText().toString().equals(itemMedPneuTO.getNroPneuItemMedPneu())){
                                verCad = false;
                            }
                        }

                        if(verCad){
                            Intent it = new Intent(PneuActivity.this, PressaoEncPneuActivity.class);
                            startActivity(it);
                        }
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(PneuActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("PNEU REPETIDO! FAVOR CALIBRAR OUTRO PNEU.");

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub

                                }
                            });
                            alerta.show();

                        }

                    }

                    pneuList.clear();

                }

            }

        });

        buttonCancPneu.setOnClickListener(new View.OnClickListener() {

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
        Intent it = new Intent(PneuActivity.this, ListaPosPneuActivity.class);
        startActivity(it);
        finish();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(!ManipDadosVerif.getInstance().isVerTerm()) {

                ManipDadosVerif.getInstance().cancelVer();
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                Intent it = new Intent(PneuActivity.this, PressaoEncPneuActivity.class);
                startActivity(it);
                finish();

            }

        }
    };


}
