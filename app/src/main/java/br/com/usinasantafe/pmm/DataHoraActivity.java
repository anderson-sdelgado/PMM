package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.bean.estaticas.TurnoTO;
import br.com.usinasantafe.pmm.bean.variaveis.ConfigTO;

public class DataHoraActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkDataHora = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancDataHora = (Button) findViewById(R.id.buttonCancPadrao);
        TextView textViewDataHora = (TextView) findViewById(R.id.textViewPadrao);

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
                            if((valor >= 2019) && (valor <= 3000)){
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
                            Long codTurno = pmmContext.getBoletimCTR().getTurno();
                            TurnoTO turnoTO = new TurnoTO();
                            List turnoList = turnoTO.get("idTurno", codTurno);
                            turnoTO = (TurnoTO) turnoList.get(0);
                            turnoList.clear();

                            String horaInicialStr = turnoTO.getDescTurno().substring(9, 11);
                            String horaFinalStr = turnoTO.getDescTurno().substring(17, 19);
                            int horaInicial = Integer.parseInt(horaInicialStr);
                            int horaFinal = Integer.parseInt(horaFinalStr);

                            if(horaFinal == 0){
                                horaFinal = 23;
                            }

                            if((valor >= horaInicial) && (valor <= horaFinal)){
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
                            if((valor <= 59)){
                                pmmContext.setMinuto(valor);
                                Long dif = Tempo.getInstance().difDthr(pmmContext.getDia(), pmmContext.getMes(), pmmContext.getAno()
                                        , pmmContext.getHora(), pmmContext.getMinuto());

                                ConfigCTR configCTR = new ConfigCTR();

                                ConfigTO configTO = configCTR.getConfig();
                                configTO.setDifDthrConfig(dif);
                                configTO.update();

                                if (pmmContext.getVerPosTela() == 1) {
                                    it = new Intent(DataHoraActivity.this, OSActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else {
                                    it = new Intent(DataHoraActivity.this, MenuPrincNormalActivity.class);
                                    startActivity(it);
                                    finish();
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
                // TODO Auto-generated method stub
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
