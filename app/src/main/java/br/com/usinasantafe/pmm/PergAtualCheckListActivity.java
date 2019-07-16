package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ConexaoWeb;
import br.com.usinasantafe.pmm.bo.ManipDadosVerif;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfigTO;

public class PergAtualCheckListActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perg_atual_check_list);

        pmmContext = (PMMContext) getApplication();

        Button buttonSimAtualCL = (Button) findViewById(R.id.buttonSimAtualCL);
        Button buttonNaoAtualCL = (Button) findViewById(R.id.buttonNaoAtualCL);

        buttonNaoAtualCL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                EquipTO equipTO = new EquipTO();
                List equipList = equipTO.get("idEquip", pmmContext.getBoletimMMTO().getCodEquipBoletim());
                equipTO = (EquipTO) equipList.get(0);
                equipList.clear();

                ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                List itemCheckList =  itemCheckListTO.get("idChecklist", equipTO.getIdChecklist());
                Long qtde = (long) itemCheckList.size();
                itemCheckList.clear();

                CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
                cabecCheckListTO.setDtCab(Tempo.getInstance().datahora());
                cabecCheckListTO.setEquipCab(equipTO.getNroEquip());
                cabecCheckListTO.setFuncCab(pmmContext.getBoletimMMTO().getCodMotoBoletim());
                cabecCheckListTO.setTurnoCab(pmmContext.getBoletimMMTO().getCodTurnoBoletim());
                cabecCheckListTO.setQtdeItemCab(qtde);
                cabecCheckListTO.setStatusCab(1L);
                cabecCheckListTO.setDtAtualCab("0");
                cabecCheckListTO.insert();

                Intent it = new Intent(PergAtualCheckListActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonSimAtualCL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(PergAtualCheckListActivity.this)) {

                    progressBar = new ProgressDialog(PergAtualCheckListActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando CheckList...");
                    progressBar.show();

                    ConfigTO configTO = new ConfigTO();
                    List configList = configTO.all();
                    configTO = (ConfigTO) configList.get(0);
                    configList.clear();

                    ManipDadosVerif.getInstance().verDados(String.valueOf(configTO.getEquipConfig()), "CheckList"
                            , PergAtualCheckListActivity.this, ItemCheckListActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder( PergAtualCheckListActivity.this);
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

    }

    public void onBackPressed()  {
    }

}
