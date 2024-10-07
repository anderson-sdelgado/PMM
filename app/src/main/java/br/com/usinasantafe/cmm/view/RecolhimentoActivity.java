package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class RecolhimentoActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private RecolhFertBean recolhFertBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recolhimento);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkRecolMang = findViewById(R.id.buttonOkPadrao);
        Button buttonCancRecolMang = findViewById(R.id.buttonCancPadrao);
        TextView textViewRecolMang = findViewById(R.id.textViewPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        LogProcessoDAO.getInstance().insertLogProcesso("recolhFertBean =  cmmContext.getMotoMecFertCTR().getRecolh(cmmContext.getMotoMecFertCTR().getContRecolh());", getLocalClassName());
        recolhFertBean =  cmmContext.getMotoMecFertCTR().getRecolh(cmmContext.getMotoMecFertCTR().getIdRecolh());

        if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                    "            BoletimMMFertBean boletimMMFertBean = cmmContext.getMotoMecFertCTR().getBolMMFert(recolhFertBean.getIdBolMMFert());\n" +
                    "            EquipBean equipBean = cmmContext.getConfigCTR().getEquip(boletimMMFertBean.getIdEquipBolMMFert());\n" +
                    "            textViewRecolMang.setText(\"NRO EQUIP: \" + equipBean.getNroEquip() + \"\\nNRO OS: \" + recolhFertBean.getNroOSRecolhFert() + \" \\nRECOL. MANGUEIRA:\");", getLocalClassName());
            BoletimMMFertBean boletimMMFertBean = cmmContext.getMotoMecFertCTR().getBolMMFert(recolhFertBean.getIdBolMMFert());
            EquipBean equipBean = cmmContext.getConfigCTR().getEquip(boletimMMFertBean.getIdEquipBolMMFert());
            textViewRecolMang.setText("NRO EQUIP: " + equipBean.getNroEquip() + "\nNRO OS: " + recolhFertBean.getNroOSRecolhFert() + " \nRECOL. MANGUEIRA:");
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            textViewRecolMang.setText(\"NRO OS: \" + recolhFertBean.getNroOSRecolhFert() + \" \\nRECOL. MANGUEIRA:\");", getLocalClassName());
            textViewRecolMang.setText("NRO OS: " + recolhFertBean.getNroOSRecolhFert() + " \nRECOL. MANGUEIRA:");
        }

        LogProcessoDAO.getInstance().insertLogProcesso("if (recolhFertBean.getValorRecolhFert() > 0) {\n" +
                "            editText.setText(String.valueOf(recolhFertBean.getValorRecolhFert()));\n" +
                "        } else {\n" +
                "            editText.setText(\"\");\n" +
                "        }", getLocalClassName());
        if (recolhFertBean.getValorRecolhFert() > 0) {
            editText.setText(String.valueOf(recolhFertBean.getValorRecolhFert()));
        } else {
            editText.setText("");
        }

        buttonOkRecolMang.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkRecolMang.setOnClickListener(v -> {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                Long valorRecolMang = Long.parseLong(editTextPadrao.getText().toString());\n" +
                        "                recolhFertBean.setValorRecolhFert(valorRecolMang);\n" +
                        "                cmmContext.getMotoMecFertCTR().atualRecolh(recolhFertBean);\n" +
                        "                Intent it = new Intent(RecolhimentoActivity.this, ListaOSRecolhActivity.class);;", getLocalClassName());
                Long valorRecolMang = Long.parseLong(editTextPadrao.getText().toString());
                recolhFertBean.setValorRecolhFert(valorRecolMang);
                cmmContext.getMotoMecFertCTR().atualRecolh(recolhFertBean);
                Intent it = new Intent(RecolhimentoActivity.this, ListaOSRecolhActivity.class);
                startActivity(it);
                finish();
            }
            LogProcessoDAO.getInstance().insertLogProcesso("if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                    "                it = new Intent(RecolhimentoActivity.this, ListaEquipOSRecolhActivity.class);\n" +
                    "            } else {\n" +
                    "                it = new Intent(RecolhimentoActivity.this, ListaOSRecolhActivity.class);\n" +
                    "            }", getLocalClassName());
            Intent it;
            if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
                it = new Intent(RecolhimentoActivity.this, ListaEquipOSRecolhActivity.class);
            } else {
                it = new Intent(RecolhimentoActivity.this, ListaOSRecolhActivity.class);
            }
            startActivity(it);
            finish();
        });

        buttonCancRecolMang.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso(" buttonCancRecolMang.setOnClickListener(v -> {\n" +
                    "            if (editTextPadrao.getText().toString().length() > 0) {\n" +
                    "                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                    "            }", getLocalClassName());
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        Intent it;
        if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                    "            it = new Intent(RecolhimentoActivity.this, ListaEquipOSRecolhActivity.class);", getLocalClassName());
            it = new Intent(RecolhimentoActivity.this, ListaEquipOSRecolhActivity.class);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            it = new Intent(RecolhimentoActivity.this, ListaOSRecolhActivity.class);", getLocalClassName());
            it = new Intent(RecolhimentoActivity.this, ListaOSRecolhActivity.class);
        }
        startActivity(it);
        finish();
    }

}
