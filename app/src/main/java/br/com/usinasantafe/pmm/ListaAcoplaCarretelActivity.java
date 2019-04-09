package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.AlocaCarretelTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaAplicFertTO;


public class ListaAcoplaCarretelActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private ListView lista;
    private List carretelList;
    private AdapterListChoice adapterListChoice;
    private ArrayList<ViewHolderChoice> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_acopla_carretel);

        pmmContext = (PMMContext) getApplication();

        Log.i("PMM","TELA = " + pmmContext.getVerPosTela());

        itens = new ArrayList<ViewHolderChoice>();

        Button buttonSalvarListaCarretel = (Button) findViewById(R.id.buttonSalvarListaCarretel);
        Button buttonRetListaCarretel = (Button) findViewById(R.id.buttonRetListaCarretel);

        EquipSegTO equipSegTO = new EquipSegTO();
        carretelList = equipSegTO.get("tipoEquip", 1L);

        if (pmmContext.getVerPosTela() == 1) {

            for (int i = 0; i < carretelList.size(); i++) {
                equipSegTO = (EquipSegTO) carretelList.get(i);
                ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                viewHolderChoice.setSelected(false);
                viewHolderChoice.setDescrCheckBox(String.valueOf(equipSegTO.getCodEquip()));
                itens.add(viewHolderChoice);
            }

        } else if((pmmContext.getVerPosTela() == 17) || (pmmContext.getVerPosTela() == 18)) {

            AlocaCarretelTO alocaCarretelTO = new AlocaCarretelTO();
            List alocaCarretelList = alocaCarretelTO.all();

            for (int i = 0; i < carretelList.size(); i++) {
                equipSegTO = (EquipSegTO) carretelList.get(i);
                ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                viewHolderChoice.setDescrCheckBox(String.valueOf(equipSegTO.getCodEquip()));
                boolean tipo = false;
                viewHolderChoice.setSelected(false);
                for (int j = 0; j < alocaCarretelList.size(); j++) {
                    alocaCarretelTO = (AlocaCarretelTO) alocaCarretelList.get(j);
                    if (equipSegTO.getIdEquip().equals(alocaCarretelTO.getIdEquipCarretel())) {
                        tipo = true;
                    }
                }
                viewHolderChoice.setSelected(tipo);
                itens.add(viewHolderChoice);
            }

        }

        adapterListChoice = new AdapterListChoice(this, itens);
        lista = (ListView) findViewById(R.id.listaCarretel);
        lista.setAdapter(adapterListChoice);

        buttonRetListaCarretel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (pmmContext.getVerPosTela() == 1) {
                    Intent it = new Intent(ListaAcoplaCarretelActivity.this, HorimetroActivity.class);
                    startActivity(it);
                    finish();
                } else if((pmmContext.getVerPosTela() == 17) || (pmmContext.getVerPosTela() == 18)) {
                    Intent it = new Intent(ListaAcoplaCarretelActivity.this, ListaEquipFertActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonSalvarListaCarretel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                int contCarretel = 0;
                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    if (viewHolderChoice.isSelected()) {
                        contCarretel = contCarretel + 1;
                    }
                }

                EquipTO equipTO = new EquipTO();
                List equipList = equipTO.all();
                equipTO = (EquipTO) equipList.get(0);

                if (pmmContext.getVerPosTela() == 1) {

                    if ((equipTO.getTipoEquipFert() == 3L) && (contCarretel > 1)) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAcoplaCarretelActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO PODE SER ALOCADO MAIS DE UM CARRETEL NUM CANHÃO. POR FAVOR, VERIFIQUE OS CARRETEIS ACOPLADOS.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();

                    } else if ((equipTO.getTipoEquipFert() == 4L) && (contCarretel > 2)) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAcoplaCarretelActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO PODE SER ALOCADO MAIS DE 2 CARRETEIS NUMA MOTOBOMBA. POR FAVOR, VERIFIQUE OS CARRETEIS ACOPLADOS.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();
                    } else {

                        AlocaCarretelTO alocaCarretelTO = new AlocaCarretelTO();
                        alocaCarretelTO.deleteAll();

                        for (int i = 0; i < itens.size(); i++) {
                            ViewHolderChoice viewHolderChoice = itens.get(i);
                            if (viewHolderChoice.isSelected()) {
                                EquipSegTO equipSegTO = (EquipSegTO) carretelList.get(i);
                                alocaCarretelTO = new AlocaCarretelTO();
                                alocaCarretelTO.setIdEquipCarretel(equipSegTO.getIdEquip());
                                alocaCarretelTO.setCodEquipCarretel(equipSegTO.getCodEquip());
                                alocaCarretelTO.insert();
                            }
                        }

                        pmmContext.getBoletimMMTO().setStatusBoletim(1L);
                        ManipDadosEnvio.getInstance().salvaBoletimAbertoMM(pmmContext.getBoletimMMTO(), false, getLatitude(), getLongitude());
                        ManipDadosEnvio.getInstance().envioDadosPrinc();

                        Intent it = new Intent(ListaAcoplaCarretelActivity.this, MenuPrincNormalActivity.class);
                        startActivity(it);
                        finish();

                    }

                }
                else if ((pmmContext.getVerPosTela() == 17) || (pmmContext.getVerPosTela() == 18)) {

                    if ((equipTO.getTipoEquipFert() == 3L) && (contCarretel > 1)) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAcoplaCarretelActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO PODE SER ALOCADO MAIS DE UM CARRETEL NUM CANHÃO. POR FAVOR, VERIFIQUE OS CARRETEIS ACOPLADOS.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();

                    } else if ((equipTO.getTipoEquipFert() == 4L) && (contCarretel > 2)) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAcoplaCarretelActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO PODE SER ALOCADO MAIS DE 2 CARRETEIS NUMA MOTOBOMBA. POR FAVOR, VERIFIQUE OS CARRETEIS ACOPLADOS.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();

                    } else {

                        boolean verDesalEquipTrabal = false;
                        Long equip = 0L;
                        AlocaCarretelTO alocaCarretelTO = new AlocaCarretelTO();
                        List alocaCarretelList = alocaCarretelTO.all();

                        for (int i = 0; i < itens.size(); i++) {
                            ViewHolderChoice viewHolderChoice = itens.get(i);
                            if (!viewHolderChoice.isSelected()) {
                                EquipSegTO equipSegTO = (EquipSegTO) carretelList.get(i);
                                for (int j = 0; j < alocaCarretelList.size(); j++) {
                                    alocaCarretelTO = (AlocaCarretelTO) alocaCarretelList.get(j);
                                    if (equipSegTO.getIdEquip().equals(alocaCarretelTO.getIdEquipCarretel())) {
                                        verDesalEquipTrabal = verifBackupAplicFert(equipSegTO.getIdEquip());
                                        if(verDesalEquipTrabal){
                                            equip = equipSegTO.getCodEquip();
                                        }
                                    }
                                }
                            }
                        }

                        if(verDesalEquipTrabal){

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAcoplaCarretelActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("O EQUIPAMENTO " + equip + " NÃO PODE SER DESACOPLADO DO BOLETIM POR ESTA TRABALHANDO AINDA. POR FAVOR, EFETUE UMA PARADA PARA O MESMO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alerta.show();

                        }
                        else {

                            alocaCarretelTO.deleteAll();

                            for (int i = 0; i < itens.size(); i++) {
                                ViewHolderChoice viewHolderChoice = itens.get(i);
                                if (viewHolderChoice.isSelected()) {
                                    EquipSegTO equipSegTO = (EquipSegTO) carretelList.get(i);
                                    alocaCarretelTO = new AlocaCarretelTO();
                                    alocaCarretelTO.setIdEquipCarretel(equipSegTO.getIdEquip());
                                    alocaCarretelTO.setCodEquipCarretel(equipSegTO.getCodEquip());
                                    alocaCarretelTO.insert();
                                }
                            }

                            Intent it = new Intent(ListaAcoplaCarretelActivity.this, ListaEquipFertActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                }
            }
        });

    }

    public void onBackPressed() {
    }

    public boolean verifBackupAplicFert(Long codEquip) {

        boolean v = false;

        BackupApontaAplicFertTO backupApontaAplicFertTO = new BackupApontaAplicFertTO();
        List bkpApontaAplicFertList = backupApontaAplicFertTO.getAndOrderBy("equipApontaAplicFert", codEquip, "idApontaAplicFert", true);

        if (bkpApontaAplicFertList.size() > 0) {
            backupApontaAplicFertTO = (BackupApontaAplicFertTO) bkpApontaAplicFertList.get(bkpApontaAplicFertList.size() - 1);
            if (backupApontaAplicFertTO.getParadaApontaAplicFert() == 0L) {
                v = true;
            }
        }
        return v;
    }

}
