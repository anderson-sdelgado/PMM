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
        else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO INICIAL");
        }

        buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String horimetro = editTextPadrao.getText().toString();
                    horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                    if (horimetroNum >= pmmContext.getConfigCTR().getConfig().getHorimetroConfig()) {
                        verTela();
                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O HORIMETRO DIGITADO " + horimetroNum + " É MENOR QUE O HORIMETRO ANTERIOR DA MAQUINA " + pmmContext.getConfigCTR().getConfig().getHorimetroConfig() + ". DESEJA MANTER ESSE VALOR?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                verTela();
                            }

                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

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

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void verTela(){
        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            salvarBoletimAberto();
        }
        else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            salvarBoletimFechado();
        }
        else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
            salvarBoletimFechado();
        }
        else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 18L) {
            salvarBoletimAberto();
        }
    }

    public void salvarBoletimAberto() {
        pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().setHodometroInicialBol(horimetroNum,  getLongitude(), getLatitude());
        if(pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){
            List<RFuncaoAtivParBean> rFuncaoAtividadeList = pmmContext.getMotoMecFertCTR().getFuncaoAtividadeList();
            boolean implemento = false;
            for (int i = 0; i < rFuncaoAtividadeList.size(); i++) {
                RFuncaoAtivParBean rFuncaoAtivParBean = (RFuncaoAtivParBean) rFuncaoAtividadeList.get(i);
                if(rFuncaoAtivParBean.getCodFuncao() == 3){
                    implemento = true;
                }
            }
            rFuncaoAtividadeList.clear();
            if(implemento){
                pmmContext.getMotoMecFertCTR().setContImplemento(1L);
                Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);
                startActivity(it);
                finish();
            }
            else{
                pmmContext.getConfigCTR().setHorimetroConfig(horimetroNum);
                pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto();
                if(pmmContext.getCheckListCTR().verAberturaCheckList(pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().getIdTurnoBolMMFert())){
                    pmmContext.getMotoMecFertCTR().inserirParadaCheckList();
                    pmmContext.getCheckListCTR().setPosCheckList(1);
                    pmmContext.getCheckListCTR().createCabecAberto();
                    if (pmmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {
                        Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        Intent it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else{
                    if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                        Intent it = new Intent(HorimetroActivity.this, EsperaInforActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(HorimetroActivity.this, VerifOperadorActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        }
        else{
            Intent it = new Intent(HorimetroActivity.this, EquipMBActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void salvarBoletimFechado() {
        pmmContext.getConfigCTR().setHorimetroConfig(horimetroNum);
        pmmContext.getMotoMecFertCTR().getBoletimMMFertDAO().getBoletimMMFertBean().setHodometroFinalBolMMFert(horimetroNum);
        if(pmmContext.getConfigCTR().getEquip().getTipoEquip() == 1){
            if (pmmContext.getMotoMecFertCTR().verRendMM()) {
                pmmContext.getMotoMecFertCTR().setContRend(1);
                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                pmmContext.getMotoMecFertCTR().salvarBolMMFertFechado();

                if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
                    Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
                    pmmContext.getConfigCTR().setPosicaoTela(18L);
                    Intent it = new Intent(HorimetroActivity.this, OperadorActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        }
        else{
            if (pmmContext.getMotoMecFertCTR().verRecolh()) {
                pmmContext.getMotoMecFertCTR().setContRecolh(1);
                Intent it = new Intent(HorimetroActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                pmmContext.getMotoMecFertCTR().salvarBolMMFertAberto();
                Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        }

    }

    public void onBackPressed() {
        if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        } else {
            if(PMMContext.aplic == 1){
                Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 2){
                Intent it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else if(PMMContext.aplic == 3){
                Intent it = new Intent(HorimetroActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        }
    }

}
