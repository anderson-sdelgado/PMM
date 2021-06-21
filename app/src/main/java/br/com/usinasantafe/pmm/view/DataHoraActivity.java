package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;

public class DataHoraActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private EditText editPadrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkDataHora = findViewById(R.id.buttonOkPadrao);
        Button buttonCancDataHora = findViewById(R.id.buttonCancPadrao);
        TextView textViewDataHora = findViewById(R.id.textViewPadrao);

        switch (pmmContext.getContDataHora()) {
            case 1:
                textViewDataHora.setText("DIA:");
                break;
            case 2:
                textViewDataHora.setText("MÊS:");
                break;
            case 3:
                textViewDataHora.setText("ANO:");
                break;
            case 4:
                textViewDataHora.setText("HORA:");
                break;
            case 5:
                textViewDataHora.setText("MINUTOS:");
                break;
        }

        editPadrao = (EditText) findViewById(R.id.editTextPadrao);

        editPadrao.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                switch (pmmContext.getContDataHora()) {
                    case 1:
                        editPadrao.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                        break;
                    case 2:
                        editPadrao.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                        break;
                    case 3:
                        editPadrao.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
                        break;
                    case 4:
                        editPadrao.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                        break;
                    case 5:
                        editPadrao.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                        break;
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        buttonOkDataHora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    int valor = Integer.parseInt(editTextPadrao.getText().toString());

                    Intent it;
                    switch (pmmContext.getContDataHora()) {
                        case 1:
                            if((valor <= 31)){
                                pmmContext.setDia(valor);
                                pmmContext.setContDataHora(pmmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("DIA INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                        case 2:
                            if((valor <= 12)){
                                pmmContext.setMes(valor);
                                pmmContext.setContDataHora(pmmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("MÊS INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                        case 3:
                            if((valor >= 2020) && (valor <= 3000)){
                                pmmContext.setAno(valor);
                                pmmContext.setContDataHora(pmmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("ANO INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                        case 4:
                            if(valor <= 23){
                                pmmContext.setHora(valor);
                                pmmContext.setContDataHora(pmmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("HORA INCORRETA! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }

                            break;
                        case 5:
                            if(valor <= 59){
                                pmmContext.setMinuto(valor);
                                Long dif = Tempo.getInstance().difDthr(pmmContext.getDia(), pmmContext.getMes(), pmmContext.getAno()
                                        , pmmContext.getHora(), pmmContext.getMinuto());

                                ConfigCTR configCTR = new ConfigCTR();

                                ConfigBean configBean = configCTR.getConfig();
                                configBean.setDifDthrConfig(dif);
                                configBean.update();

                                if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                                    it = new Intent(DataHoraActivity.this, OSActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else {
                                    if(pmmContext.getConfigCTR().getConfig().getAplic() == 1L){
                                        it = new Intent(DataHoraActivity.this, MenuPrincPMMActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else if(pmmContext.getConfigCTR().getConfig().getAplic() == 2L){
                                        it = new Intent(DataHoraActivity.this, MenuPrincECMActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else if(pmmContext.getConfigCTR().getConfig().getAplic() == 3L){
                                        it = new Intent(DataHoraActivity.this, MenuPrincPCOMPActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                }
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("MINUTO INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                    }

                }

            }
        });

        buttonCancDataHora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        if(pmmContext.getContDataHora() > 1){
            pmmContext.setContDataHora(pmmContext.getContDataHora() - 1);
            Intent it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
            startActivity(it);
            finish();
        }
    }

}
