package br.com.usinasantafe.pmm;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.AlocaCarretelTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaAplicFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaMMTO;

/**
 * Created by anderson on 19/10/2015.
 */
public class AdapterListEquipFert extends BaseAdapter {

    private ArrayList<String> itens;
    private LayoutInflater layoutInflater;
    private int tela;

    public AdapterListEquipFert(Context context, ArrayList<String> itens, int tela) {
        // TODO Auto-generated constructor stub
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
        this.tela = tela;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_item_lista, null);
        TextView textView = (TextView) convertView.findViewById(R.id.textViewItemList);
        if (position == 0) {
            textView.setText(itens.get(position));
            if(verifBackup()) {
                textView.setTextColor(Color.RED);
            }
            else{
                textView.setTextColor(Color.BLUE);
            }
        } else {
            textView.setText(itens.get(position));
            if(verifBackupAplicFert(Long.parseLong(itens.get(position)))) {
                textView.setTextColor(Color.RED);
            }
            else{
                textView.setTextColor(Color.BLUE);
            }
        }

        return convertView;
    }

    public boolean verifBackup() {

        boolean v = false;

        BackupApontaMMTO backupApontaMMTO = new BackupApontaMMTO();
        List bkpApontaList = backupApontaMMTO.all();

        if (bkpApontaList.size() > 0) {
            backupApontaMMTO = (BackupApontaMMTO) bkpApontaList.get(bkpApontaList.size() - 1);
            if (this.tela == 1) {
                if (backupApontaMMTO.getParadaAponta() == 0L){
                    v = true;
                }
            } else {
                if (backupApontaMMTO.getParadaAponta() > 0L){
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
                if (backupApontaAplicFertTO.getParadaApontaAplicFert() == 0L){
                    v = true;
                }
            } else {
                if (backupApontaAplicFertTO.getParadaApontaAplicFert() > 0L){
                    v = true;
                }
            }
        }
        return v;
    }

}
