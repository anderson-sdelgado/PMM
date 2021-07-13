package br.com.usinasantafe.pmm.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class OSActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_os);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkOS = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = findViewById(R.id.buttonCancPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        if ((pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L)
            || (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L)){
            editText.setText("");
        } else {
            editText.setText(String.valueOf(pmmContext.getConfigCTR().getConfig().getOsConfig()));
        }

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    Long nroOS = Long.parseLong(editTextPadrao.getText().toString());
                    pmmContext.getConfigCTR().setOsConfig(nroOS);

                    if (pmmContext.getConfigCTR().verOS(nroOS)) {

                        if (connectNetwork) {
                            pmmContext.getConfigCTR().setStatusConConfig(1L);
                        }
                        else{
                            pmmContext.getConfigCTR().setStatusConConfig(0L);
                        }

                        Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        if (connectNetwork) {

                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("PESQUISANDO OS...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            pmmContext.getMotoMecFertCTR().verOS(editTextPadrao.getText().toString()
                                    , OSActivity.this, ListaAtividadeActivity.class, progressBar);

                        } else {

                            pmmContext.getConfigCTR().setStatusConConfig(0L);

                            Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }
                }
            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            Intent it = new Intent(OSActivity.this, ListaTurnoActivity.class);
            startActivity(it);
            finish();
        } else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L) {
            Intent it = new Intent(OSActivity.this, MenuCertifActivity.class);
            startActivity(it);
            finish();
        } else {
            if(PMMContext.aplic == 1){
                Intent it = new Intent(OSActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 2){
                Intent it = new Intent(OSActivity.this, FrenteActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 3){
                Intent it = new Intent(OSActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        }
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(VerifDadosServ.status < 3) {

                VerifDadosServ.getInstance().cancel();

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                pmmContext.getConfigCTR().setStatusConConfig(0L);
                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

}
