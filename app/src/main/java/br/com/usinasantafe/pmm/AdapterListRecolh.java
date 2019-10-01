package br.com.usinasantafe.pmm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.bean.variaveis.RecolhFertTO;

/**
 * Created by anderson on 19/10/2015.
 */
public class AdapterListRecolh extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;

    public AdapterListRecolh(Context context, List itens) {

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

        view = layoutInflater.inflate(R.layout.activity_item_os_recol, null);

        TextView textViewOSRecMang = (TextView) view.findViewById(R.id.textViewOSRecMang);
        TextView textViewValorRecMang = (TextView) view.findViewById(R.id.textViewValorRecMang);

        RecolhFertTO recolhFertTO = (RecolhFertTO) itens.get(position);

        textViewOSRecMang.setText("NRO OS: " + recolhFertTO.getNroOSRecolhFert());

        if(recolhFertTO.getValorRecolhFert() > 0){
            textViewValorRecMang.setText("QTDE REC.: " + recolhFertTO.getValorRecolhFert());
        }
        else{
            textViewValorRecMang.setText("QTDE REC.: ");
        }

        return view;
    }
}
