package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class EquipActivity extends ActivityGeneric {

    private TextView textViewCodEquip;
    private TextView textViewDescEquip;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        pmmContext = (PMMContext) getApplication();

        textViewCodEquip = findViewById(R.id.textViewCodEquip);
        textViewDescEquip = findViewById(R.id.textViewDescEquip);
        Button buttonOkEquip = findViewById(R.id.buttonOkEquip);
        Button buttonCancEquip = findViewById(R.id.buttonCancEquip);

        LogProcessoDAO.getInstance().insertLogProcesso("textViewCodEquip.setText(String.valueOf(" + pmmContext.getConfigCTR().getEquip().getNroEquip() + "));\n" +
                "        textViewDescEquip.setText(pmmContext.getConfigCTR().getEquip().getDescrClasseEquip());", getLocalClassName());
        textViewCodEquip.setText(String.valueOf(pmmContext.getConfigCTR().getEquip().getNroEquip()));
        textViewDescEquip.setText(pmmContext.getConfigCTR().getEquip().getDescrClasseEquip());

        buttonOkEquip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonOkEquip.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){

                    LogProcessoDAO.getInstance().insertLogProcesso("                if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){\n" +
                            "                    pmmContext.getMotoMecFertCTR().delCarreta();", getLocalClassName());
                    pmmContext.getMotoMecFertCTR().delCarreta();

                    if(pmmContext.getConfigCTR().getEquip().getCodClasseEquip() == 1L){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getEquip().getCodClasseEquip() == 1L){\n" +
                                "                        pmmContext.getCecCTR().setLib(pmmContext.getCecCTR().getOS().getIdLibOS());", getLocalClassName());
                        pmmContext.getCecCTR().setLib(pmmContext.getCecCTR().getOS().getIdLibOS());
                    }

                    LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(EquipActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                    Intent it = new Intent(EquipActivity.this, MsgNumCarretaActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().setIdEquipBolMMFert(" + pmmContext.getConfigCTR().getEquip().getIdEquip() + ");", getLocalClassName());
                    pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFert().setIdEquipBolMMFert(pmmContext.getConfigCTR().getEquip().getIdEquip());

                    LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);" + pmmContext.getConfigCTR().getEquip().getIdEquip() + ");", getLocalClassName());
                    Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancEquip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancEquip.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 16L){\n" +
                            "                    Intent it = new Intent(EquipActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                    Intent it = new Intent(EquipActivity.this, ListaAtividadeActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{ \n" +
                            "                    Intent it = new Intent(EquipActivity.this, OperadorActivity.class);", getLocalClassName());
                    Intent it = new Intent(EquipActivity.this, OperadorActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }

    public void onBackPressed()  {
    }

}
