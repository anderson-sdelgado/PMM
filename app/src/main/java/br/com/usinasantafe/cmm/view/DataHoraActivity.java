package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.Tempo;

public class DataHoraActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private EditText editPadrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkDataHora = findViewById(R.id.buttonOkPadrao);
        Button buttonCancDataHora = findViewById(R.id.buttonCancPadrao);
        TextView textViewDataHora = findViewById(R.id.textViewPadrao);

        switch (cmmContext.getConfigCTR().getContDataHora()) {
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

        editPadrao = findViewById(R.id.editTextPadrao);

        editPadrao.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                switch (cmmContext.getConfigCTR().getContDataHora()) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        editPadrao.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                        break;
                    case 3:
                        editPadrao.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
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

        buttonOkDataHora.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkDataHora.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {
                int valor = Integer.parseInt(editTextPadrao.getText().toString());
                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    int valor = Integer.parseInt(" + editTextPadrao.getText().toString() + ");\n" +
                        "                    Intent it;\n" +
                        "                    switch (" + cmmContext.getConfigCTR().getContDataHora() + ") {", getLocalClassName());
                Intent it;
                switch (cmmContext.getConfigCTR().getContDataHora()) {
                    case 1:
                        if((valor <= 31)){
                            LogProcessoDAO.getInstance().insertLogProcesso("case 1:\n" +
                                    "                                if((valor <= 31)){\n" +
                                    "                                pmmContext.getConfigCTR().setDia(" + valor + ");\n" +
                                    "                                pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() + 1);\n" +
                                    "                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
                            cmmContext.getConfigCTR().setDia(valor);
                            cmmContext.getConfigCTR().setContDataHora(cmmContext.getConfigCTR().getContDataHora() + 1);
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
                            alerta.setPositiveButton("OK", (dialog, which) -> {
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
                            cmmContext.getConfigCTR().setMes(valor);
                            cmmContext.getConfigCTR().setContDataHora(cmmContext.getConfigCTR().getContDataHora() + 1);
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
                            alerta.setPositiveButton("OK", (dialog, which) -> {
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
                            cmmContext.getConfigCTR().setAno(valor);
                            cmmContext.getConfigCTR().setContDataHora(cmmContext.getConfigCTR().getContDataHora() + 1);
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
                            alerta.setPositiveButton("OK", (dialog, which) -> {
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
                            cmmContext.getConfigCTR().setHora(valor);
                            cmmContext.getConfigCTR().setContDataHora(cmmContext.getConfigCTR().getContDataHora() + 1);
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
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });
                            alerta.show();
                        }

                        break;
                    case 5:
                        if(valor <= 59){

                            LogProcessoDAO.getInstance().insertLogProcesso("case 5:\n" +
                                    " if(valor <= 59){\n" +
                                    " pmmContext.getConfigCTR().setMinuto(valor);\n" +
                                    " pmmContext.getConfigCTR().setDifDthrConfig(Tempo.getInstance().difDthr(" + cmmContext.getConfigCTR().getDia() + ", " + cmmContext.getConfigCTR().getMes() + ", " + cmmContext.getConfigCTR().getAno() + "\n" +
                                    " , " + cmmContext.getConfigCTR().getHora() + ", " + cmmContext.getConfigCTR().getMinuto() + "));", getLocalClassName());
                            cmmContext.getConfigCTR().setMinuto(valor);
                            cmmContext.getConfigCTR().setDifDthrConfig(Tempo.getInstance().difDthr(cmmContext.getConfigCTR().getDia(), cmmContext.getConfigCTR().getMes(), cmmContext.getConfigCTR().getAno()
                                    , cmmContext.getConfigCTR().getHora(), cmmContext.getConfigCTR().getMinuto()));

                            if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {", getLocalClassName());
                                if(BuildConfig.FLAVOR.equals("ecm")) {

                                    LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 2){", getLocalClassName());
                                    if (connectNetwork) {
                                        LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                                "pmmContext.getConfigCTR().setStatusConConfig(1L);", getLocalClassName());
                                        cmmContext.getConfigCTR().setStatusConConfig(1L);
                                    }
                                    else{
                                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                                "pmmContext.getConfigCTR().setStatusConConfig(0L);", getLocalClassName());
                                        cmmContext.getConfigCTR().setStatusConConfig(0L);
                                    }
                                    LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(ListaTurnoActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                                    it = new Intent(DataHoraActivity.this, ListaAtividadeActivity.class);
                                    startActivity(it);
                                    finish();

                                } else if(BuildConfig.FLAVOR.equals("pcomp")){
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
                                if(BuildConfig.FLAVOR.equals("pmm")){
                                    LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                                            "                                    if(PMMContext.aplic == 1){\n" +
                                            "                                        it = new Intent(DataHoraActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                                    it = new Intent(DataHoraActivity.this, MenuPrincPMMActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(BuildConfig.FLAVOR.equals("ecm")){
                                    LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                                            "                                        it = new Intent(DataHoraActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                                    it = new Intent(DataHoraActivity.this, MenuPrincECMActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(BuildConfig.FLAVOR.equals("pcomp")){
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
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });
                            alerta.show();
                        }
                        break;
                }

            }

        });

        buttonCancDataHora.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancDataHora.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {" +
                    "if (editTextPadrao.getText().toString().length() > 0) {\n" +
                    "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                    "                }", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {", getLocalClassName());
        if(cmmContext.getConfigCTR().getContDataHora() > 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getContDataHora() > 1){\n" +
                    "            pmmContext.getConfigCTR().setContDataHora(pmmContext.getConfigCTR().getContDataHora() - 1);\n" +
                    "            Intent it = new Intent(DataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
            cmmContext.getConfigCTR().setContDataHora(cmmContext.getConfigCTR().getContDataHora() - 1);
            Intent it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
            startActivity(it);
            finish();
        }
    }

}
