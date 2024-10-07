package br.com.usinasantafe.cmm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.control.ConfigCTR;
import br.com.usinasantafe.cmm.control.MotoMecFertCTR;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.RecolhFertBean;

/**
 * Created by anderson on 19/10/2015.
 */
public class AdapterListEquipOSRecolh extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;

    public AdapterListEquipOSRecolh(Context context, List itens) {

        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
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
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.activity_item_equip_os_recol, null);

        TextView textViewEquipRecMang = view.findViewById(R.id.textViewEquipRecMang);
        TextView textViewOSRecMang = view.findViewById(R.id.textViewOSRecMang);
        TextView textViewValorRecMang = view.findViewById(R.id.textViewValorRecMang);

        RecolhFertBean recolhFertBean = (RecolhFertBean) itens.get(position);

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertBean boletimMMFertBean = motoMecFertCTR.getBolMMFert(recolhFertBean.getIdBolMMFert());
        EquipBean equipBean = configCTR.getEquip(boletimMMFertBean.getIdEquipBolMMFert());

        textViewEquipRecMang.setText("NRO EQUIP: " + equipBean.getNroEquip());
        textViewOSRecMang.setText("NRO OS: " + recolhFertBean.getNroOSRecolhFert());

        if(recolhFertBean.getValorRecolhFert() > 0){
            textViewValorRecMang.setText("QTDE REC.: " + recolhFertBean.getValorRecolhFert());
        }
        else{
            textViewValorRecMang.setText("QTDE REC.: ");
        }

        return view;
    }
}
