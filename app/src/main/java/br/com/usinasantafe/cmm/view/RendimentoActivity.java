package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class RendimentoActivity extends ActivityGeneric {

    private CMMContext cmmContext;
    private RendMMBean rendMMBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendimento);

        cmmContext = (CMMContext) getApplication();

        Button buttonOkRendimento = findViewById(R.id.buttonOkPadrao);
        Button buttonCancRendimento = findViewById(R.id.buttonCancPadrao);
        TextView textViewRendimento = findViewById(R.id.textViewPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        int cont;

        LogProcessoDAO.getInstance().insertLogProcesso("int cont;\n" +
                "if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L){\n" +
                "            cont = pmmContext.getMotoMecFertCTR().getContRend() - 1;\n" +
                "        }\n" +
                "        else{\n" +
                "            cont = pmmContext.getMotoMecFertCTR().getPosRend();\n" +
                "        }", getLocalClassName());
        if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L){
            cont = cmmContext.getMotoMecFertCTR().getContRend() - 1;
        }
        else{
            cont = cmmContext.getMotoMecFertCTR().getPosRend();
        }

        LogProcessoDAO.getInstance().insertLogProcesso("rendMMBean =  pmmContext.getMotoMecFertCTR().getRend(cont);", getLocalClassName());
        rendMMBean =  cmmContext.getMotoMecFertCTR().getRend(cont);

        textViewRendimento.setText("OS " + rendMMBean.getNroOSRendMM() +" \nRENDIMENTO :");

        LogProcessoDAO.getInstance().insertLogProcesso("if(rendMMBean.getValorRendMM() > 0){\n" +
                "            editText.setText(String.valueOf(rendMMBean.getValorRendMM()).replace(\".\", \",\"));\n" +
                "        }\n" +
                "        else{\n" +
                "            editText.setText(\"\");\n" +
                "        }", getLocalClassName());
        if(rendMMBean.getValorRendMM() > 0){
            editText.setText(String.valueOf(rendMMBean.getValorRendMM()).replace(".", ","));
        }
        else{
            editText.setText("");
        }

        buttonOkRendimento.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkRendimento.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                    verifRend();", getLocalClassName());
                verifRend();
            } else {
                if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 7L){
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                    if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 7L){\n" +
                            "                        Intent it = new Intent(RendimentoActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(RendimentoActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonCancRendimento.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancRendimento.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
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
        if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L){", getLocalClassName());
            if(cmmContext.getMotoMecFertCTR().getPosRend() > 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getMotoMecFertCTR().getPosRend() > 1){\n" +
                        "                pmmContext.getMotoMecFertCTR().setPosRend(pmmContext.getMotoMecFertCTR().getPosRend() - 1);\n" +
                        "                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);", getLocalClassName());
                cmmContext.getMotoMecFertCTR().setPosRend(cmmContext.getMotoMecFertCTR().getPosRend() - 1);
                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);
                startActivity(it);
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                Intent it = new Intent(RendimentoActivity.this, HorimetroActivity.class);", getLocalClassName());
                Intent it = new Intent(RendimentoActivity.this, HorimetroActivity.class);
                startActivity(it);
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);", getLocalClassName());
            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);
            startActivity(it);
        }
        finish();
    }

    public void verTela(Double rendNum){

        LogProcessoDAO.getInstance().insertLogProcesso("rendMMBean.setValorRendMM(" + rendNum + ");\n" +
                "        pmmContext.getMotoMecFertCTR().atualRend(" + rendMMBean + ");", getLocalClassName());
        rendMMBean.setValorRendMM(rendNum);
        cmmContext.getMotoMecFertCTR().atualRend(rendMMBean);

        if (cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {", getLocalClassName());
            if (cmmContext.getMotoMecFertCTR().qtdeRend() == cmmContext.getMotoMecFertCTR().getContRend()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getMotoMecFertCTR().qtdeRend() == pmmContext.getMotoMecFertCTR().getContRend()) {\n" +
                        "                pmmContext.getMotoMecFertCTR().salvarBolMMFertFechado();", getLocalClassName());
                cmmContext.getMotoMecFertCTR().salvarBolMMFertFechado(cmmContext, getLocalClassName());
                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(RendimentoActivity.this, TelaInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(RendimentoActivity.this, TelaInicialActivity.class);
                startActivity(it);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "pmmContext.getMotoMecFertCTR().setContRend(pmmContext.getMotoMecFertCTR().getContRend() + 1);\n" +
                        "                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);", getLocalClassName());
                cmmContext.getMotoMecFertCTR().setContRend(cmmContext.getMotoMecFertCTR().getContRend() + 1);
                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);
                startActivity(it);
            }
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);", getLocalClassName());
            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);
            startActivity(it);
        }
        finish();
    }

    public void verifRend(){
        LogProcessoDAO.getInstance().insertLogProcesso("String rend = editTextPadrao.getText().toString();\n" +
                "        Double rendNum = Double.valueOf(rend.replace(\",\", \".\"));", getLocalClassName());
        String rend = editTextPadrao.getText().toString();
        Double rendNum = Double.valueOf(rend.replace(",", "."));
        if (rendNum <= cmmContext.getMotoMecFertCTR().rendOS(rendMMBean.getNroOSRendMM())) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (rendNum <= pmmContext.getMotoMecFertCTR().rendOS(rendMMBean.getNroOSRendMM())) {\n" +
                    "            verTela(rendNum);", getLocalClassName());
            verTela(rendNum);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(RendimentoActivity.this);\n" +
                    "            alerta.setTitle(\"ATENCAO\");\n" +
                    "            alerta.setMessage(\"VALOR INFORMADO MAIS ALTO DO QUE O PERMITIDO PRA OS. VALOR VERIFIQUE O VALOR DIGITADO.\");\n" +
                    "            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                    "                @Override\n" +
                    "                public void onClick(DialogInterface dialog, int which) {\n" +
                    "                }\n" +
                    "            });\n" +
                    "            alerta.show();", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(RendimentoActivity.this);
            alerta.setTitle("ATENCAO");
            alerta.setMessage("VALOR INFORMADO MAIS ALTO DO QUE O PERMITIDO PRA OS. VALOR VERIFIQUE O VALOR DIGITADO.");
            alerta.setPositiveButton("OK", (dialog, which) -> {
            });
            alerta.show();
        }
    }

}
