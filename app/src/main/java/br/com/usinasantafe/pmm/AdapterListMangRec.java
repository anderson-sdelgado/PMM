package br.com.usinasantafe.pmm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.to.tb.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RecolhimentoTO;

/**
 * Created by anderson on 19/10/2015.
 */
public class AdapterListMangRec extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;

    public AdapterListMangRec(Context context, List itens) {
        // TODO Auto-generated constructor stub
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

        view = layoutInflater.inflate(R.layout.activity_item_rec_mang, null);

        TextView textViewEquipRecMang = (TextView) view.findViewById(R.id.textViewEquipRecMang);
        TextView textViewOSRecMang = (TextView) view.findViewById(R.id.textViewOSRecMang);
        TextView textViewValorRecMang = (TextView) view.findViewById(R.id.textViewValorRecMang);

        RecolhimentoTO recolhimentoTO = (RecolhimentoTO) itens.get(position);

        EquipSegTO equipSegTO = new EquipSegTO();
//        List equipSegList = equipSegTO.get("idEquip", recolhimentoTO.getEquipRecol());
//        equipSegTO = (EquipSegTO) equipSegList.get(0);

        textViewEquipRecMang.setText("EQUIP: " + equipSegTO.getCodEquip());
        textViewOSRecMang.setText("NRO OS: " + recolhimentoTO.getNroOSRecol());

        if(recolhimentoTO.getValorRecol() > 0){
            textViewValorRecMang.setText("QTDE REC.: " + recolhimentoTO.getValorRecol());
        }
        else{
            textViewValorRecMang.setText("QTDE REC.: ");
        }

        return view;
    }
}
