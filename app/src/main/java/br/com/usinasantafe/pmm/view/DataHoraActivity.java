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
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.Tempo;
//import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;

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

        switch (pmmContext.getConfigCTR().getContDataHora()) {
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

                switch (pmmContext.getConfigCTR().getContDataHora()) {
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

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkDataHora.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (!editTextPadrao.getText().toString().equals("")) {

                    int valor = Integer.parseInt(editTextPadrao.getText().toString());

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "                    int valor = Integer.parseInt(" + editTextPadrao.getText().toString() + ");\n" +
                            "                    Intent it;\n" +
                            "                    switch (" + pmmContext.getConfigCTR().getContDataHora() + ") {", getLocalClassName());
                    Intent it;
                    switch (pmmContext.getConfigCTR().getContDataHora()) {
                        case 1:
                            if((valor <= 31)){
                                LogProcessoDAO.getInstance().insertLogProcesso("case 1:\n" +
                                        "                                if((valor <= 31)){\n" +
                                        "                                pmmContext.getConfigCTR().setDia(" + valor + ");\n" +
                                        "                                pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() + 1);\n" +
                                        "                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
                                pmmContext.getConfigCTR().setDia(valor);
                                pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"DIA INCORRETO! FAVOR VERIFICAR.\");\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());
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
                                LogProcessoDAO.getInstance().insertLogProcesso("case 2:\n" +
                                        "                            if((valor <= 12)){\n" +
                                        "                                pmmContext.getConfigCTR().setMes(" + valor + ");\n" +
                                        "                                pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() + 1);\n" +
                                        "                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
                                pmmContext.getConfigCTR().setMes(valor);
                                pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"MÊS INCORRETO! FAVOR VERIFICAR.\");\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());
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
                                LogProcessoDAO.getInstance().insertLogProcesso("case 3:\n" +
                                        "                            if((valor >= 2020) && (valor <= 3000)){\n" +
                                        "                                pmmContext.getConfigCTR().setAno(" + valor + ");\n" +
                                        "                                pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() + 1);\n" +
                                        "                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
                                pmmContext.getConfigCTR().setAno(valor);
                                pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"ANO INCORRETO! FAVOR VERIFICAR.\");\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());
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
                                LogProcessoDAO.getInstance().insertLogProcesso("case 4:\n" +
                                        "                            if(valor <= 23){\n" +
                                        "                                pmmContext.getConfigCTR().setHora(" + valor + ");\n" +
                                        "                                pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() + 1);\n" +
                                        "                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
                                pmmContext.getConfigCTR().setHora(valor);
                                pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"HORA INCORRETA! FAVOR VERIFICAR.\");\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());
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

                                LogProcessoDAO.getInstance().insertLogProcesso("case 5:\n" +
                                        " if(valor <= 59){\n" +
                                        " pmmContext.getConfigCTR().setMinuto(valor);\n" +
                                        " pmmContext.getConfigCTR().setDifDthrConfig(Tempo.getInstance().difDthr(" + pmmContext.getConfigCTR().getDia() + ", " + pmmContext.getConfigCTR().getMes() + ", " + pmmContext.getConfigCTR().getAno() + "\n" +
                                        " , " + pmmContext.getConfigCTR().getHora() + ", " + pmmContext.getConfigCTR().getMinuto() + "));", getLocalClassName());
                                pmmContext.getConfigCTR().setMinuto(valor);
                                pmmContext.getConfigCTR().setDifDthrConfig(Tempo.getInstance().difDthr(pmmContext.getConfigCTR().getDia(), pmmContext.getConfigCTR().getMes(), pmmContext.getConfigCTR().getAno()
                                        , pmmContext.getConfigCTR().getHora(), pmmContext.getConfigCTR().getMinuto()));

                                if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {", getLocalClassName());
                                    if(PMMContext.aplic == 2) {

                                        LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 2){", getLocalClassName());
                                        if (connectNetwork) {
                                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                                    "pmmContext.getConfigCTR().setStatusConConfig(1L);", getLocalClassName());
                                            pmmContext.getConfigCTR().setStatusConConfig(1L);
                                        }
                                        else{
                                            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                                    "pmmContext.getConfigCTR().setStatusConConfig(0L);", getLocalClassName());
                                            pmmContext.getConfigCTR().setStatusConConfig(0L);
                                        }
                                        LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(ListaTurnoActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                                        it = new Intent(DataHoraActivity.this, ListaAtividadeActivity.class);
                                        startActivity(it);
                                        finish();

                                    } else if(PMMContext.aplic == 3){
                                        LogProcessoDAO.getInstance().insertLogProcesso("} else if(PMMContext.aplic == 3){\n" +
                                                "                                        it = new Intent(DataHoraActivity.this, OSActivity.class);", getLocalClassName());
                                        it = new Intent(DataHoraActivity.this, ListaFuncaoCompActivity.class);
                                        startActivity(it);
                                        finish();
                                    } else {
                                        LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                                                "                                        it = new Intent(DataHoraActivity.this, OSActivity.class);", getLocalClassName());
                                        it = new Intent(DataHoraActivity.this, OSActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                }
                                else {
                                    if(PMMContext.aplic == 1){
                                        LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                                                "                                    if(PMMContext.aplic == 1){\n" +
                                                "                                        it = new Intent(DataHoraActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                                        it = new Intent(DataHoraActivity.this, MenuPrincPMMActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else if(PMMContext.aplic == 2){
                                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                                                "                                        it = new Intent(DataHoraActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                                        it = new Intent(DataHoraActivity.this, MenuPrincECMActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else if(PMMContext.aplic == 3){
                                        LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                                                "                                        it = new Intent(DataHoraActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                                        it = new Intent(DataHoraActivity.this, MenuPrincPCOMPActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                }
                            }
                            else{
                                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"MINUTO INCORRETO! FAVOR VERIFICAR.\");\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());
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
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancDataHora.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {" +
                        "if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                        "                }", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {", getLocalClassName());
        if(pmmContext.getConfigCTR().getContDataHora() > 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getContDataHora() > 1){\n" +
                    "            pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() - 1);\n" +
                    "            Intent it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
            pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() - 1);
            Intent it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
            startActivity(it);
            finish();
        }
    }

}
