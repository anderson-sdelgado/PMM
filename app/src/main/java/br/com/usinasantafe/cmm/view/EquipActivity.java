package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class EquipActivity extends ActivityGeneric {

    private TextView textViewCodEquip;
    private TextView textViewDescEquip;
    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        cmmContext = (CMMContext) getApplication();

        textViewCodEquip = findViewById(R.id.textViewCodEquip);
        textViewDescEquip = findViewById(R.id.textViewDescEquip);
        Button buttonOkEquip = findViewById(R.id.buttonOkEquip);
        Button buttonCancEquip = findViewById(R.id.buttonCancEquip);

        LogProcessoDAO.getInstance().insertLogProcesso("textViewCodEquip.setText(String.valueOf(" + cmmContext.getConfigCTR().getEquip().getNroEquip() + "));\n" +
                "        textViewDescEquip.setText(pmmContext.getConfigCTR().getEquip().getDescrClasseEquip());", getLocalClassName());
        textViewCodEquip.setText(String.valueOf(cmmContext.getConfigCTR().getEquip().getNroEquip()));
        textViewDescEquip.setText(cmmContext.getConfigCTR().getEquip().getDescrClasseEquip());

        buttonOkEquip.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonOkEquip.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){\n" +
                        "                    pmmContext.getCecCTR().clearPreCECAberto();", getLocalClassName());
                cmmContext.getCecCTR().clearPreCECAberto();
                if(cmmContext.getConfigCTR().getEquip().getCodClasseEquip() == 1L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getEquip().getCodClasseEquip() == 1L) {\n" +
                            "pmmContext.getConfigCTR().setCarreta(0L);\n" +
                            "                        Intent it = new Intent(EquipActivity.this, OSActivity.class);", getLocalClassName());
                    cmmContext.getConfigCTR().setCarreta(0L);
                    Intent it = new Intent(EquipActivity.this, OSActivity.class);
                    startActivity(it);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        Intent it = new Intent(EquipActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                    Intent it = new Intent(EquipActivity.this, MsgNumCarretaActivity.class);
                    startActivity(it);
                }
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);" + cmmContext.getConfigCTR().getEquip().getIdEquip() + ");", getLocalClassName());
                Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);
                startActivity(it);
            }
            finish();
        });

        buttonCancEquip.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancEquip.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if(cmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){\n" +
                        "                    pmmContext.getCecCTR().clearPreCECAberto();\n" +
                        "                    Intent it = new Intent(EquipActivity.this, MenuCertifActivity.class);", getLocalClassName());
                cmmContext.getCecCTR().clearPreCECAberto();
                Intent it = new Intent(EquipActivity.this, MenuCertifActivity.class);
                startActivity(it);
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{ \n" +
                        "                    Intent it = new Intent(EquipActivity.this, OperadorActivity.class);", getLocalClassName());
                Intent it = new Intent(EquipActivity.this, OperadorActivity.class);
                startActivity(it);
            }
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
