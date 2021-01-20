package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.util.ConexaoWeb;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class OSActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();
    private ConfigCTR configCTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_os);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        configCTR = new ConfigCTR();

        if (pmmContext.getVerPosTela() == 1) {
            EditText editText = (EditText) findViewById(R.id.editTextPadrao);
            editText.setText("");
        } else {
            EditText editText = (EditText) findViewById(R.id.editTextPadrao);
            editText.setText(String.valueOf(configCTR.getConfig().getOsConfig()));
        }

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    Long nroOS = Long.parseLong(editTextPadrao.getText().toString());
                    configCTR.setOsConfig(nroOS);

                    ConexaoWeb conexaoWeb = new ConexaoWeb();

                    if (pmmContext.getConfigCTR().verOS(nroOS)) {

                        if (conexaoWeb.verificaConexao(OSActivity.this)) {
                            configCTR.setStatusConConfig(1L);
                        }
                        else{
                            configCTR.setStatusConConfig(0L);
                        }

                        VerifDadosServ.getInstance().setVerTerm(true);

                        Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        if (conexaoWeb.verificaConexao(OSActivity.this)) {

                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("PESQUISANDO OS...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            pmmContext.getBoletimCTR().verOS(editTextPadrao.getText().toString()
                                    , OSActivity.this, ListaAtividadeActivity.class, progressBar);

                        } else {

                            configCTR.setStatusConConfig(0L);

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
        if (pmmContext.getVerPosTela() == 1) {
            Intent it = new Intent(OSActivity.this, ListaTurnoActivity.class);
            startActivity(it);
            finish();
        } else {
            Intent it = new Intent(OSActivity.this, MenuPrincNormalActivity.class);
            startActivity(it);
            finish();
        }
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(!VerifDadosServ.getInstance().isVerTerm()) {

                VerifDadosServ.getInstance().cancelVer();

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                configCTR.setStatusConConfig(0L);
                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

}
