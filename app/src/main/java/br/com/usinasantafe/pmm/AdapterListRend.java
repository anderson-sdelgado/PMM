package br.com.usinasantafe.pmm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.to.variaveis.RendMMTO;

/**
 * Created by anderson on 19/10/2015.
 */
public class AdapterListRend extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;

    public AdapterListRend(Context context, List itens) {

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
        view = layoutInflater.inflate(R.layout.activity_item_rend, null);

        TextView textViewRendNroOS = (TextView) view.findViewById(R.id.textViewRendNroOS);
        TextView textViewRendValor = (TextView) view.findViewById(R.id.textViewRendValor);

        RendMMTO rendMMTO = (RendMMTO) itens.get(position);

        textViewRendNroOS.setText("NRO OS: " + rendMMTO.getNroOSRendMM());

        if(rendMMTO.getValorRendMM() > 0){
            textViewRendValor.setText("REND.: " + rendMMTO.getValorRendMM());
        }
        else{
            textViewRendValor.setText("REND.: ");
        }

        return view;
    }
}
