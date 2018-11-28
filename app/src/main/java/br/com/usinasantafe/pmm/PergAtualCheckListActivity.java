package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;

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
                cabecCheckListTO.setDtCabecCheckList(Tempo.getInstance().data());
                cabecCheckListTO.setEquipCabecCheckList(equipTO.getCodEquip());
                cabecCheckListTO.setFuncCabecCheckList(pmmContext.getBoletimMMTO().getCodMotoBoletim());
                cabecCheckListTO.setTurnoCabecCheckList(pmmContext.getBoletimMMTO().getCodTurnoBoletim());
                cabecCheckListTO.setQtdeItemCabecCheckList(qtde);
                cabecCheckListTO.setStatusCabecCheckList(1L);
                cabecCheckListTO.setDtAtualCheckList("0");
                cabecCheckListTO.insert();

                Intent it = new Intent(PergAtualCheckListActivity.this, ItemChecklistActivity.class);
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

                    ManipDadosVerif.getInstance().verDados("", "CheckList"
                            , PergAtualCheckListActivity.this, ItemChecklistActivity.class, progressBar);

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


}
