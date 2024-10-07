package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class HorimetroActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private Double horimetroNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horimetro);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkHorimetro = findViewById(R.id.buttonOkPadrao);
        Button buttonCancHorimetro = findViewById(R.id.buttonCancPadrao);
        TextView textViewHorimetro = findViewById(R.id.textViewPadrao);

        if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO INICIAL");
        }
        else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO FINAL");
        }
        else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO FINAL");
        }
        else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO INICIAL");
        }
        else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 26L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO FINAL");
        }

        buttonOkHorimetro.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    String horimetro = editTextPadrao.getText().toString();\n" +
                        "                    horimetroNum = Double.valueOf(horimetro.replace(\",\", \".\"));", getLocalClassName());
                String horimetro = editTextPadrao.getText().toString();
                horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                if (horimetroNum >= cmmContext.getConfigCTR().getConfig().getHorimetroConfig()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (horimetroNum >= pmmContext.getConfigCTR().getConfig().getHorimetroConfig()) {\n" +
                            "                        verTela();", getLocalClassName());
                    verTela();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"O HORIMETRO DIGITADO \" + horimetroNum + \" É MENOR QUE O HORIMETRO ANTERIOR DA MAQUINA \" + pmmContext.getConfigCTR().getConfig().getHorimetroConfig() + \". DESEJA MANTER ESSE VALOR?\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("O HODÔMETRO REGISTRADO " + horimetroNum + " É MENOR QUE O ANTERIOR DE " + cmmContext.getConfigCTR().getConfig().getHorimetroConfig() + ". DESEJA MANTÊ-LO?");
                    alerta.setPositiveButton("SIM", (dialog, which) -> {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                                verTela();", getLocalClassName());
                        verTela();
                    });

                    alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {", getLocalClassName()));

                    alerta.show();

                }

            }

        });

        buttonCancHorimetro.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancHorimetro.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                if (editTextPadrao.getText().toString().length() > 0) {\n" +
                    "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                    "                }", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }


    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                    "            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
            Intent it;
            switch (BuildConfig.FLAVOR){
                case "pmm":
                    LogProcessoDAO.getInstance().insertLogProcesso("case \"pmm\":\n" +
                            "                it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);
                    break;
                case "ecm":
                    LogProcessoDAO.getInstance().insertLogProcesso("case \"ecm\":\n" +
                            "                it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);
                    break;
                default:
                    LogProcessoDAO.getInstance().insertLogProcesso("default:\n" +
                            "                it = new Intent(HorimetroActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    it = new Intent(HorimetroActivity.this, MenuPrincPCOMPActivity.class);
            }
            startActivity(it);
        }
        finish();
    }

    public void verTela(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void verTela(){", getLocalClassName());
        if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                    "            salvarBoletimAberto();", getLocalClassName());
            salvarBoletimAberto();
        }
        else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                    "            salvarBoletimFechado();", getLocalClassName());
            salvarBoletimFechado();
        }
        else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {\n" +
                    "            salvarBoletimFechado();", getLocalClassName());
            salvarBoletimFechado();
        }
        else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L) {
            salvarBoletimAberto();
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            salvarBoletimAberto();", getLocalClassName());
        }
        else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 26L) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                    "            salvarBoletimFechado();", getLocalClassName());
            salvarBoletimFechado();
        }
    }

    public void salvarBoletimAberto() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBoletimAberto() {\n" +
                "pmmContext.getConfigCTR().setHodometroInicialBolMMFert(horimetroNum,  getLongitude(), getLatitude());", getLocalClassName());
        cmmContext.getConfigCTR().setHodometroInicialConfig(horimetroNum,  getLongitude(), getLatitude());
        if(cmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){\n" +
                    "List<RFuncaoAtivParBean> rFuncaoAtividadeList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList();", getLocalClassName());
            List<RFuncaoAtivParBean> rFuncaoAtividadeList = cmmContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());
            LogProcessoDAO.getInstance().insertLogProcesso("boolean implemento = false;\n" +
                    "            for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {\n" +
                    "                if(rFuncaoAtivParBean.getCodFuncao() == 3){\n" +
                    "                    implemento = true;\n" +
                    "                }\n" +
                    "            }\n" +
                    "            rFuncaoAtividadeList.clear();", getLocalClassName());
            boolean implemento = false;
            for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {
                if (rFuncaoAtivParBean.getCodFuncao() == 3) {
                    implemento = true;
                    break;
                }
            }
            rFuncaoAtividadeList.clear();
            if(implemento) {
                LogProcessoDAO.getInstance().insertLogProcesso("if(implemento){\n" +
                        "pmmContext.getMotoMecFertCTR().setContImplemento(1L);\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);", getLocalClassName());
                cmmContext.getMotoMecFertCTR().setContImplemento(1L);
                Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "pmmContext.getConfigCTR().setHorimetroConfig(horimetroNum);\n" +
                        "                pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto();", getLocalClassName());
                cmmContext.getConfigCTR().setHorimetroConfig(horimetroNum);
                cmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());
                if(cmmContext.getCheckListCTR().verAberturaCheckList(cmmContext.getConfigCTR().getConfig().getIdTurnoConfig())){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getCheckListCTR().verAberturaCheckList(pmmContext.getConfigCTR().getConfig().getIdTurnoConfig())){\n" +
                            "pmmContext.getMotoMecFertCTR().inserirParadaCheckList();", getLocalClassName());
                    cmmContext.getMotoMecFertCTR().inserirParadaCheckList(cmmContext, getLocalClassName());
                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCheckListCTR().setPosCheckList(1);", getLocalClassName());
                    cmmContext.getCheckListCTR().setPosCheckList(1);
                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCheckListCTR().createCabecAberto();", getLocalClassName());
                    cmmContext.getCheckListCTR().createCabecAberto(getLocalClassName());
                    Intent it;
                    if (cmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {\n" +
                                "                        it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);", getLocalClassName());
                        it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                        it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);
                    }
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("else{", getLocalClassName());
                    if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {", getLocalClassName());
                        if(BuildConfig.FLAVOR.equals("pmm")){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                                    "                Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                            Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);
                            startActivity(it);
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("else if(PMMContext.aplic == 2){\n" +
                                    "                Intent it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                            Intent it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);
                            startActivity(it);
                        }
                        finish();
                    } else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L) {
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                        Intent it = new Intent(HorimetroActivity.this, VerifOperadorActivity.class);", getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, VerifOperadorActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            Intent it = new Intent(HorimetroActivity.this, EquipMBActivity.class);", getLocalClassName());
            Intent it = new Intent(HorimetroActivity.this, EquipMBActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void salvarBoletimFechado() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBoletimFechado() {\n" +
                "pmmContext.getConfigCTR().setHorimetroConfig(" + horimetroNum + ");", getLocalClassName());
        cmmContext.getConfigCTR().setHorimetroConfig(horimetroNum);
        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().setHodometroFinalConfig(" + horimetroNum + ");", getLocalClassName());
        cmmContext.getConfigCTR().setHodometroFinalConfig(horimetroNum);
        if(cmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){", getLocalClassName());
            if (cmmContext.getMotoMecFertCTR().verRendMM()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verRendMM()) {\n" +
                        "                pmmContext.getMotoMecFertCTR().setContRend(1);\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);", getLocalClassName());
                cmmContext.getMotoMecFertCTR().setContRend(1);
                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {", getLocalClassName());
                    if (cmmContext.getMotoMecFertCTR().verRecolh()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verRecolh()) {\n" +
                                "                Intent it = new Intent(HorimetroActivity.this, ListaOSRecolhActivity.class);", getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, ListaEquipOSRecolhActivity.class);
                        startActivity(it);
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());\n" +
                                "                Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);", getLocalClassName());
                        cmmContext.getMotoMecFertCTR().salvarBolMMFertFechado(cmmContext, getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);
                        startActivity(it);
                    }
                    finish();
                }
                else if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                    pmmContext.getConfigCTR().setPosicaoTela(18L);\n" +
                            "                    Intent it = new Intent(HorimetroActivity.this, OperadorActivity.class);", getLocalClassName());
                    cmmContext.getConfigCTR().setPosicaoTela(18L);
                    Intent it = new Intent(HorimetroActivity.this, OperadorActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else{", getLocalClassName());
            if (cmmContext.getMotoMecFertCTR().verRecolh()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verRecolh()) {\n" +
                        "                pmmContext.getMotoMecFertCTR().setContRecolh(1);\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, RecolhimentoActivity.class);", getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, ListaEquipOSRecolhActivity.class);
                startActivity(it);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);", getLocalClassName());
                cmmContext.getMotoMecFertCTR().salvarBolMMFertFechado(cmmContext, getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);
                startActivity(it);
            }
            finish();
        }

    }


}
