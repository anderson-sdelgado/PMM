package br.com.usinasantafe.pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.AlocaCarretelTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaAplicFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaMMTO;

public class ListaEquipFertActivity extends ActivityGeneric {

    private ListView lista;
    private List carretelList;
    private PMMContext pmmContext;
    private int tela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equip_fert);

        Button buttonRetEquipFert = (Button) findViewById(R.id.buttonRetEquipFert);
        Button buttonAcoplaCarretelEquipFert = (Button) findViewById(R.id.buttonAcoplaCarretelEquipFert);

        pmmContext = (PMMContext) getApplication();

        ArrayList<String> itens = new ArrayList<String>();

        EquipTO equipTO = new EquipTO();
        List equipList = equipTO.get("idEquip", pmmContext.getBoletimMMTO().getCodEquipBoletim());
        equipTO = (EquipTO) equipList.get(0);
        itens.add(String.valueOf(equipTO.getCodEquip()));

        AlocaCarretelTO alocaCarretelTO = new AlocaCarretelTO();
        carretelList = alocaCarretelTO.all();

        for (int i = 0; i < carretelList.size(); i++) {
            alocaCarretelTO = (AlocaCarretelTO) carretelList.get(i);
            itens.add(String.valueOf(alocaCarretelTO.getCodEquipCarretel()));
        }

        tela = 0;

        if ((pmmContext.getVerPosTela() == 5)|| (pmmContext.getVerPosTela() == 8) || (pmmContext.getVerPosTela() == 9) || (pmmContext.getVerPosTela() == 17)) {
            tela = 1;
        } else if ((pmmContext.getVerPosTela() == 11) || (pmmContext.getVerPosTela() == 12) || (pmmContext.getVerPosTela() == 13) || (pmmContext.getVerPosTela() == 18)) {
            tela = 2;
        }

        AdapterListEquipFert adapterListEquipFert = new AdapterListEquipFert(this, itens, tela);
        lista = (ListView) findViewById(R.id.listaEquipFert);
        lista.setAdapter(adapterListEquipFert);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                if (position == 0) {

                    if ((pmmContext.getVerPosTela() == 5) || (pmmContext.getVerPosTela() == 8) || (pmmContext.getVerPosTela() == 9) || (pmmContext.getVerPosTela() == 17)) {
                        pmmContext.setVerPosTela(8);
                    } else if ((pmmContext.getVerPosTela() == 11) || (pmmContext.getVerPosTela() == 12) || (pmmContext.getVerPosTela() == 13) || (pmmContext.getVerPosTela() == 18)) {
                        pmmContext.setVerPosTela(12);
                    }

                    if (verifBackup()) {

                        String msg;

                        if (tela == 1) {
                            msg = "EQUIPAMENTO JÁ ESTA TRABALHANDO. POR FAVOR, APONTE OUTRO EQUIPAMENTO.";
                        } else {
                            msg = "EQUIPAMENTO JÁ ESTA PARADO. POR FAVOR, APONTE OUTRO EQUIPAMENTO.";
                        }

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaEquipFertActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage(msg);
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    } else {

                        Intent it = new Intent(ListaEquipFertActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();

                    }

                } else {

                    if ((pmmContext.getVerPosTela() == 5)|| (pmmContext.getVerPosTela() == 8) || (pmmContext.getVerPosTela() == 9) || (pmmContext.getVerPosTela() == 17)) {
                        pmmContext.setVerPosTela(9);
                    } else if ((pmmContext.getVerPosTela() == 11) || (pmmContext.getVerPosTela() == 12) || (pmmContext.getVerPosTela() == 13) || (pmmContext.getVerPosTela() == 18)) {
                        pmmContext.setVerPosTela(13);
                    }
                    AlocaCarretelTO alocaCarretelTO = (AlocaCarretelTO) carretelList.get(position - 1);
                    pmmContext.getApontaAplicFertTO().setEquipApontaAplicFert(alocaCarretelTO.getIdEquipCarretel());

                    if (verifBackupAplicFert(alocaCarretelTO.getCodEquipCarretel())) {

                        String msg;

                        if (tela == 1) {
                            msg = "EQUIPAMENTO JÁ ESTA TRABALHANDO. POR FAVOR, APONTE OUTRO EQUIPAMENTO.";
                        } else {
                            msg = "EQUIPAMENTO JÁ ESTA PARADO. POR FAVOR, APONTE OUTRO EQUIPAMENTO.";
                        }

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaEquipFertActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage(msg);
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    } else {

                        Intent it = new Intent(ListaEquipFertActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }

        });

        buttonRetEquipFert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaEquipFertActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAcoplaCarretelEquipFert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if ((pmmContext.getVerPosTela() == 5) || (pmmContext.getVerPosTela() == 8) || (pmmContext.getVerPosTela() == 9) || (pmmContext.getVerPosTela() == 17)) {
                    pmmContext.setVerPosTela(17);
                } else if ((pmmContext.getVerPosTela() == 11) || (pmmContext.getVerPosTela() == 12) || (pmmContext.getVerPosTela() == 13) || (pmmContext.getVerPosTela() == 18)) {
                    pmmContext.setVerPosTela(18);
                }

                Intent it = new Intent(ListaEquipFertActivity.this, ListaAcoplaCarretelActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public boolean verifBackup() {

        boolean v = false;

        BackupApontaMMTO backupApontaMMTO = new BackupApontaMMTO();
        List bkpApontaList = backupApontaMMTO.all();

        if (bkpApontaList.size() > 0) {
            backupApontaMMTO = (BackupApontaMMTO) bkpApontaList.get(bkpApontaList.size() - 1);
            if (this.tela == 1) {
                if (backupApontaMMTO.getParadaAponta() == 0L) {
                    v = true;
                }
            } else {
                if (backupApontaMMTO.getParadaAponta() > 0L) {
                    v = true;
                }
            }
        }

        return v;

    }

    public boolean verifBackupAplicFert(Long codEquip) {

        boolean v = false;

        AlocaCarretelTO alocaCarretelTO = new AlocaCarretelTO();
        List carretelList = alocaCarretelTO.get("codEquipCarretel", codEquip);
        alocaCarretelTO = (AlocaCarretelTO) carretelList.get(0);

        BackupApontaAplicFertTO backupApontaAplicFertTO = new BackupApontaAplicFertTO();
        List bkpApontaAplicFertList = backupApontaAplicFertTO.getAndOrderBy("equipApontaAplicFert", alocaCarretelTO.getIdEquipCarretel(), "idApontaAplicFert", true);

        if (bkpApontaAplicFertList.size() > 0) {
            backupApontaAplicFertTO = (BackupApontaAplicFertTO) bkpApontaAplicFertList.get(bkpApontaAplicFertList.size() - 1);
            if (this.tela == 1) {
                if (backupApontaAplicFertTO.getParadaApontaAplicFert() == 0L) {
                    v = true;
                }
            } else {
                if (backupApontaAplicFertTO.getParadaApontaAplicFert() > 0L) {
                    v = true;
                }
            }
        }
        return v;
    }

    public void onBackPressed() {
    }

}
