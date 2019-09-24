package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.to.estaticas.RFuncaoAtivParTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.RecolhFertTO;

public class HorimetroActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private Double horimetroNum;
    private ConfigCTR configCTR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horimetro);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkHorimetro = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancHorimetro = (Button) findViewById(R.id.buttonCancPadrao);

        TextView textViewHorimetro = (TextView) findViewById(R.id.textViewPadrao);
        textViewHorimetro.setText(pmmContext.getTextoHorimetro());

        buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String horimetro = editTextPadrao.getText().toString();
                    horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                    configCTR = new ConfigCTR();
                    if (horimetroNum >= configCTR.getConfig().getHorimetroConfig()) {
                        verTela();
                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O HORIMETRO DIGITADO " + horimetroNum + " É MENOR QUE O HORIMETRO ANTERIOR DA MAQUINA " + configCTR.getConfig().getHorimetroConfig() + ". DESEJA MANTER ESSE VALOR?");

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
        if (pmmContext.getVerPosTela() == 1) {
            salvarBoletimAberto();
        }
        else if (pmmContext.getVerPosTela() == 4) {
            salvarBoletimFechado();
        }
    }

    public void salvarBoletimAberto() {
        pmmContext.getBoletimCTR().setHodometroInicialBol(horimetroNum);
        if(configCTR.getEquip().getTipo() == 1){
            List rFuncaoAtivParList = pmmContext.getBoletimCTR().retFuncaoAtivParList(pmmContext.getBoletimCTR().getAtiv());
            boolean implemento = false;
            for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                RFuncaoAtivParTO rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(i);
                if(rFuncaoAtivParTO.getCodFuncao() == 3){
                    implemento = true;
                }
            }
            if(implemento){
                pmmContext.setContImplemento(1);
                Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);
                startActivity(it);
                finish();
            }
            else{
                configCTR.atualHorimetroConfig(horimetroNum);
                pmmContext.getBoletimCTR().salvarBolAbertoMM();
                CheckListCTR checkListCTR = new CheckListCTR();
                if(checkListCTR.verAberturaCheckList(pmmContext.getBoletimCTR().getTurno())){
                    pmmContext.setPosCheckList(1);
                    if (pmmContext.getVerAtualCL().equals("N_AC")) {
                        Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        checkListCTR.createCabecAberto(pmmContext.getBoletimCTR());
                        Intent it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else{
                    Intent it = new Intent(HorimetroActivity.this, EsperaDadosOperActivity.class);
                    startActivity(it);
                    finish();
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

        configCTR.atualHorimetroConfig(horimetroNum);
        pmmContext.getBoletimCTR().setHodometroFinalBol(horimetroNum);

        if(configCTR.getEquip().getTipo() == 1){
            if (pmmContext.getBoletimCTR().verRendMM()) {
                pmmContext.setContRend(1);
                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                pmmContext.getBoletimCTR().salvarBolFechadoMM();
                Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        }
        else{
            if (pmmContext.getBoletimCTR().verRecolh()) {
                pmmContext.setContRecolh(1);
                Intent it = new Intent(HorimetroActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                pmmContext.getBoletimCTR().salvarBolFechadoFert();
                Intent it = new Intent(HorimetroActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        }

    }

    public void onBackPressed() {
        if (pmmContext.getVerPosTela() == 1) {
            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        } else {
            Intent it = new Intent(HorimetroActivity.this, MenuPrincNormalActivity.class);
            startActivity(it);
            finish();
        }
    }

}
