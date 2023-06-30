package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.BuildConfig;
import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class ImplementoActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implemento);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkImplemento = findViewById(R.id.buttonOkPadrao);
        Button buttonCancImplemento = findViewById(R.id.buttonCancPadrao);
        TextView textViewImplemento = findViewById(R.id.textViewImplemento);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        LogProcessoDAO.getInstance().insertLogProcesso("textViewImplemento.setText(\"IMPLEMENTO \" + " + pmmContext.getMotoMecFertCTR().getContImplemento() + " + \":\");", getLocalClassName());
        textViewImplemento.setText("IMPLEMENTO " + pmmContext.getMotoMecFertCTR().getContImplemento() + ":");

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {\n", getLocalClassName());

                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "progressBar = new ProgressDialog(ImplementoActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"Atualizando Implemento...\");\n" +
                                    "                            progressBar.show();", getLocalClassName());

                            progressBar = new ProgressDialog(ImplementoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Implemento...");
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().atualDados(ImplementoActivity.this, ImplementoActivity.class, progressBar, \"EquipSeg\", 1, getLocalClassName());", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().atualDados(ImplementoActivity.this, ImplementoActivity.class, progressBar, "EquipSeg", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });

                alerta.show();

            }

        });

        buttonOkImplemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkImplemento.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if(pmmContext.getMotoMecFertCTR().getContImplemento() == 1L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().getContImplemento() == 1L){", getLocalClassName());
                    if (!editTextPadrao.getText().toString().equals("")) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                                "                        Long impl = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                        Long impl = Long.parseLong(editTextPadrao.getText().toString());
                        if(pmmContext.getMotoMecFertCTR().verImplemento(impl)){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().verImplemento(impl)){\n" +
                                    "                            pmmContext.getMotoMecFertCTR().setImplemento(pmmContext.getMotoMecFertCTR().getContImplemento(), impl);\n" +
                                    "                            pmmContext.getMotoMecFertCTR().setContImplemento(2L);\n" +
                                    "                            Intent it = new Intent(ImplementoActivity.this, ImplementoActivity.class);", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().setImplemento(pmmContext.getMotoMecFertCTR().getContImplemento(), impl);
                            pmmContext.getMotoMecFertCTR().setContImplemento(2L);
                            Intent it = new Intent(ImplementoActivity.this, ImplementoActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            msg("NUMERAÇÃO DE IMPLEMENTO INCORRETA. FAVOR, VERIFICAR A NUMERAÇÃO OU ATUALIZAR A BASE DE DADOS NOVAMENTE!");
                        }
                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if (!editTextPadrao.getText().toString().equals("")) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                                "                        Long impl = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                        Long impl = Long.parseLong(editTextPadrao.getText().toString());
                        if(pmmContext.getMotoMecFertCTR().verImplemento(impl) && (pmmContext.getMotoMecFertCTR().verDuplicImpleMM(impl))){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().verImplemento(impl) && (pmmContext.getMotoMecFertCTR().verDuplicImpleMM(impl))){\n" +
                                    "                            pmmContext.getMotoMecFertCTR().setImplemento(pmmContext.getMotoMecFertCTR().getContImplemento(), impl);\n" +
                                    "                            verTela();", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().setImplemento(pmmContext.getMotoMecFertCTR().getContImplemento(), impl);
                            verTela();
                        }
                        else{
                            msg("NUMERAÇÃO DE IMPLEMENTO INCORRETA OU REPETIDA. POR FAVOR, VERIFICAR A NUMERAÇÃO OU ATUALIZAR A BASE DE DADOS NOVAMENTE!");
                        }
                    }
                    else{
                        LogProcessoDAO.getInstance().insertLogProcesso("verTela();", getLocalClassName());
                        verTela();
                    }
                }


            }
        });

        buttonCancImplemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancImplemento.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                        "                }", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void verTela(){
        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                    "            salvarBoletimAberto();", getLocalClassName());
            salvarBoletimAberto();
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            Intent it = new Intent(ImplementoActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(ImplementoActivity.this, MenuPrincPMMActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void msg(String msg){
        LogProcessoDAO.getInstance().insertLogProcesso("public void msg(String msg){\n" +
                "AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);\n" +
                "        alerta.setTitle(\"ATENÇÃO\");\n" +
                "        alerta.setMessage(" + msg + ");\n" +
                "        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                "            @Override\n" +
                "            public void onClick(DialogInterface dialog, int which) {\n" +
                "            }\n" +
                "        });\n" +
                "        alerta.show();", getLocalClassName());
        AlertDialog.Builder alerta = new AlertDialog.Builder(ImplementoActivity.this);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage(msg);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.show();
    }


    public void salvarBoletimAberto() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBoletimAberto() {\n" +
                "pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());", getLocalClassName());
        pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());
        if(pmmContext.getCheckListCTR().verAberturaCheckList(pmmContext.getConfigCTR().getConfig().getIdTurnoConfig())){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getCheckListCTR().verAberturaCheckList(pmmContext.getConfigCTR().getConfig().getIdTurnoConfig())){\n" +
                    "            pmmContext.getMotoMecFertCTR().inserirParadaCheckList(getLocalClassName());\n" +
                    "            pmmContext.getCheckListCTR().setPosCheckList(1);\n" +
                    "            pmmContext.getCheckListCTR().createCabecAberto(getLocalClassName());", getLocalClassName());
            pmmContext.getMotoMecFertCTR().inserirParadaCheckList(getLocalClassName());
            pmmContext.getCheckListCTR().setPosCheckList(1);
            pmmContext.getCheckListCTR().createCabecAberto(getLocalClassName());
            if (pmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {\n" +
                        "                Intent it = new Intent(ImplementoActivity.this, PergAtualCheckListActivity.class);", getLocalClassName());
                Intent it = new Intent(ImplementoActivity.this, PergAtualCheckListActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                Intent it = new Intent(ImplementoActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                Intent it = new Intent(ImplementoActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();
            }
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{", getLocalClassName());
            if(BuildConfig.FLAVOR.equals("pmm")){
                LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                        "                Intent it = new Intent(ImplementoActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(ImplementoActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if(BuildConfig.FLAVOR.equals("ecm")){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                        "                Intent it = new Intent(ImplementoActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(ImplementoActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else if(BuildConfig.FLAVOR.equals("pcomp")){
                LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 3){\n" +
                        "                Intent it = new Intent(ImplementoActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(ImplementoActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        }
    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if (pmmContext.getMotoMecFertCTR().getContImplemento() == 1) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().getContImplemento() == 1) {", getLocalClassName());
            if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                        "                Intent it = new Intent(ImplementoActivity.this, HorimetroActivity.class);", getLocalClassName());
                Intent it = new Intent(ImplementoActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                Intent it = new Intent(ImplementoActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(ImplementoActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            pmmContext.getMotoMecFertCTR().setContImplemento(pmmContext.getMotoMecFertCTR().getContImplemento() - 1);\n" +
                    "            Intent it = new Intent(ImplementoActivity.this, ImplementoActivity.class);", getLocalClassName());
            pmmContext.getMotoMecFertCTR().setContImplemento(pmmContext.getMotoMecFertCTR().getContImplemento() - 1);
            Intent it = new Intent(ImplementoActivity.this, ImplementoActivity.class);
            startActivity(it);
            finish();
        }
    }

}
