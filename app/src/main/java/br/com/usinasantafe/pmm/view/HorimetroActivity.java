package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class HorimetroActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private Double horimetroNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horimetro);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkHorimetro = findViewById(R.id.buttonOkPadrao);
        Button buttonCancHorimetro = findViewById(R.id.buttonCancPadrao);
        TextView textViewHorimetro = findViewById(R.id.textViewPadrao);

        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO INICIAL");
        }
        else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO FINAL");
        }
        else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO FINAL");
        }
        else{
            textViewHorimetro.setText("HORIMETRO/HODOMETRO INICIAL");
        }

        buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "                    \n" +
                            "                    String horimetro = editTextPadrao.getText().toString();\n" +
                            "                    horimetroNum = Double.valueOf(horimetro.replace(\",\", \".\"));", getLocalClassName());
                    String horimetro = editTextPadrao.getText().toString();
                    horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                    if (horimetroNum >= pmmContext.getConfigCTR().getConfig().getHorimetroConfig()) {
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
                        alerta.setMessage("O HODÔMETRO REGISTRADO " + horimetroNum + " É MENOR QUE O ANTERIOR DE " + pmmContext.getConfigCTR().getConfig().getHorimetroConfig() + ". DESEJA MANTÊ-LO?");
                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                verTela();", getLocalClassName());
                                verTela();
                            }

                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {", getLocalClassName());
                            }
                        });

                        alerta.show();

                    }

                }

            }
        });

        buttonCancHorimetro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancHorimetro.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                        "                }", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void verTela(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void verTela(){", getLocalClassName());
        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                    "            salvarBoletimAberto();", getLocalClassName());
            salvarBoletimAberto();
        }
        else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                    "            salvarBoletimFechado();", getLocalClassName());
            salvarBoletimFechado();
        }
        else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {\n" +
                    "            salvarBoletimFechado();", getLocalClassName());
            salvarBoletimFechado();
        }
        else{
            salvarBoletimAberto();
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            salvarBoletimAberto();", getLocalClassName());
        }
    }

    public void salvarBoletimAberto() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBoletimAberto() {\n" +
                "pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBolMMFert().setHodometroInicialBol(horimetroNum,  getLongitude(), getLatitude());", getLocalClassName());
        pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBolMMFert().setHodometroInicialBol(horimetroNum,  getLongitude(), getLatitude());
        if(pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){\n" +
                    "List<RFuncaoAtivParBean> rFuncaoAtividadeList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList();", getLocalClassName());
            List<RFuncaoAtivParBean> rFuncaoAtividadeList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());
            LogProcessoDAO.getInstance().insertLogProcesso("boolean implemento = false;\n" +
                    "            for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {\n" +
                    "                if(rFuncaoAtivParBean.getCodFuncao() == 3){\n" +
                    "                    implemento = true;\n" +
                    "                }\n" +
                    "            }\n" +
                    "            rFuncaoAtividadeList.clear();", getLocalClassName());
            boolean implemento = false;
            for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {
                if(rFuncaoAtivParBean.getCodFuncao() == 3){
                    implemento = true;
                }
            }
            rFuncaoAtividadeList.clear();
            if(implemento){
                LogProcessoDAO.getInstance().insertLogProcesso("if(implemento){\n" +
                        "pmmContext.getMotoMecFertCTR().setContImplemento(1L);\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);", getLocalClassName());
                pmmContext.getMotoMecFertCTR().setContImplemento(1L);
                Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);
                startActivity(it);
                finish();
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "pmmContext.getConfigCTR().setHorimetroConfig(horimetroNum);\n" +
                        "                pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto();", getLocalClassName());
                pmmContext.getConfigCTR().setHorimetroConfig(horimetroNum);
                pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());
                if(pmmContext.getCheckListCTR().verAberturaCheckList(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBolMMFert().getIdTurnoBolMMFert())){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getCheckListCTR().verAberturaCheckList(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBolMMFert().getIdTurnoBolMMFert())){\n" +
                            "pmmContext.getMotoMecFertCTR().inserirParadaCheckList();", getLocalClassName());
                    pmmContext.getMotoMecFertCTR().inserirParadaCheckList(getLocalClassName());
                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCheckListCTR().setPosCheckList(1);", getLocalClassName());
                    pmmContext.getCheckListCTR().setPosCheckList(1);
                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getCheckListCTR().createCabecAberto();", getLocalClassName());
                    pmmContext.getCheckListCTR().createCabecAberto(getLocalClassName());
                    if (pmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {\n" +
                                "                        Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);", getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{", getLocalClassName());
                    if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                                "                    Intent it = new Intent(HorimetroActivity.this, EsperaInforActivity.class);", getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, EsperaInforActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                        Intent it = new Intent(HorimetroActivity.this, VerifOperadorActivity.class);", getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, VerifOperadorActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        }
        else{
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
        pmmContext.getConfigCTR().setHorimetroConfig(horimetroNum);
        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBolMMFert().setHodometroFinalBolMMFert(" + horimetroNum + ");", getLocalClassName());
        pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBolMMFert().setHodometroFinalBolMMFert(horimetroNum);
        if(pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){", getLocalClassName());
            if (pmmContext.getMotoMecFertCTR().verRendMM()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verRendMM()) {\n" +
                        "                pmmContext.getMotoMecFertCTR().setContRend(1);\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);", getLocalClassName());
                pmmContext.getMotoMecFertCTR().setContRend(1);
                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                pmmContext.getMotoMecFertCTR().salvarBolMMFertFechado();", getLocalClassName());
                pmmContext.getMotoMecFertCTR().salvarBolMMFertFechado(getLocalClassName());
                if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                            "                    Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                    pmmContext.getConfigCTR().setPosicaoTela(18L);\n" +
                            "                    Intent it = new Intent(HorimetroActivity.this, OperadorActivity.class);", getLocalClassName());
                    pmmContext.getConfigCTR().setPosicaoTela(18L);
                    Intent it = new Intent(HorimetroActivity.this, OperadorActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{", getLocalClassName());
            if (pmmContext.getMotoMecFertCTR().verRecolh()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().verRecolh()) {\n" +
                        "                pmmContext.getMotoMecFertCTR().setContRecolh(1);\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, RecolhimentoActivity.class);", getLocalClassName());
                pmmContext.getMotoMecFertCTR().setContRecolh(1);
                Intent it = new Intent(HorimetroActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);", getLocalClassName());
                pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            }
        }

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                    "            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
            if(PMMContext.aplic == 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(PMMContext.aplic == 1){\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 2){
                LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                        "            else if(PMMContext.aplic == 2){\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 3){
                LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                        "            else if(PMMContext.aplic == 3){\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        }
    }

}
