package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RecolMangTO;

public class RecolMangFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private RecolMangTO recolMangTO;
    private List recolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recol_mang_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkRecolMang = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancRecolMang = (Button) findViewById(R.id.buttonCancPadrao);
        TextView textViewRecolMang = (TextView) findViewById(R.id.textViewPadrao);
        EditText editText = (EditText) findViewById(R.id.editTextPadrao);

        int cont = 0;

        if (pmmContext.getVerPosTela() == 10) {
            cont = pmmContext.getContRecolMangFert() - 1;
        } else if (pmmContext.getVerPosTela() == 14) {
            cont = pmmContext.getPosRecolMangFert();
        }

        recolMangTO = new RecolMangTO();
        recolList = recolMangTO.orderBy("idRendMangRecol", true);
        recolMangTO = (RecolMangTO) recolList.get(cont);

        EquipSegTO equipSegTO = new EquipSegTO();
        List equipSegList = equipSegTO.get("idEquip", recolMangTO.getEquipRendMangRecol());
        equipSegTO = (EquipSegTO) equipSegList.get(0);

        textViewRecolMang.setText("Equipamento: " + equipSegTO.getCodEquip() + " \nOS: " + recolMangTO.getNroOSRendMangRecol() + " \nRecol. Mangueira:");
        if (recolMangTO.getValorRendMangRecol() > 0) {
            editText.setText(String.valueOf(recolMangTO.getValorRendMangRecol()));
        } else {
            editText.setText("");
        }

        buttonOkRecolMang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                if (pmmContext.getVerPosTela() == 10) {

                    if (!editTextPadrao.getText().toString().equals("")) {

                        Long valorRecolMang = Long.parseLong(editTextPadrao.getText().toString());

                        recolMangTO.setValorRendMangRecol(valorRecolMang);
                        recolMangTO.setDthrRendMangRecol(Tempo.getInstance().data());
                        recolMangTO.setStatusRendMangRecol(2L);
                        recolMangTO.update();
                        recolMangTO.commit();

                        if (recolList.size() == pmmContext.getContRecolMangFert()) {

                            ManipDadosEnvio.getInstance().salvaBoletimFechado();
                            ManipDadosEnvio.getInstance().envioDadosPrinc();
                            Intent it = new Intent(RecolMangFertActivity.this, MenuInicialActivity.class);
                            startActivity(it);
                            finish();

                        } else {

                            pmmContext.setContRecolMangFert(pmmContext.getContRecolMangFert() + 1);
                            Intent it = new Intent(RecolMangFertActivity.this, RecolMangFertActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                } else if (pmmContext.getVerPosTela() == 14) {

                    if (!editTextPadrao.getText().toString().equals("")) {

                        Long valorRecolMang = Long.parseLong(editTextPadrao.getText().toString());

                        recolMangTO.setValorRendMangRecol(valorRecolMang);
                        recolMangTO.update();
                        recolMangTO.commit();

                        Intent it = new Intent(RecolMangFertActivity.this, ListaRecMangActivity.class);
                        startActivity(it);
                        finish();


                    } else {
                        Intent it = new Intent(RecolMangFertActivity.this, MenuPrincNormalActivity.class);
                        startActivity(it);
                        finish();
                    }
                }

            }
        });

        buttonCancRecolMang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });


    }

    public void onBackPressed() {
        if (pmmContext.getVerPosTela() == 11) {
            Intent it = new Intent(RecolMangFertActivity.this, HorimetroActivity.class);
            startActivity(it);
            finish();
        } else if (pmmContext.getVerPosTela() == 14) {
            Intent it = new Intent(RecolMangFertActivity.this, ListaOSRendActivity.class);
            startActivity(it);
            finish();
        }
    }
}
