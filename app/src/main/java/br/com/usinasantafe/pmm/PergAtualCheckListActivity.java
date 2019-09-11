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
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;

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

                EquipTO equipTO = new EquipTO();
                List equipList = equipTO.get("idEquip", pmmContext.getBoletimMMTO().getIdEquipBolMM());
                equipTO = (EquipTO) equipList.get(0);
                equipList.clear();

                ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                List itemCheckList =  itemCheckListTO.get("idCheckList", equipTO.getIdCheckList());
                Long qtde = (long) itemCheckList.size();
                itemCheckList.clear();

                CabecCLTO cabecCLTO = new CabecCLTO();
                cabecCLTO.setDtCabCL(Tempo.getInstance().datahora());
                cabecCLTO.setEquipCabCL(equipTO.getNroEquip());
                cabecCLTO.setFuncCabCL(pmmContext.getBoletimMMTO().getMatricFuncBolMM());
                cabecCLTO.setTurnoCabCL(pmmContext.getBoletimMMTO().getIdTurnoBolMM());
                cabecCLTO.setQtdeItemCabCL(qtde);
                cabecCLTO.setStatusCabCL(1L);
                cabecCLTO.setDtAtualCabCL("0");
                cabecCLTO.insert();

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
                    progressBar.setMessage("ATUALIZANDO CHECKLIST...");
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
