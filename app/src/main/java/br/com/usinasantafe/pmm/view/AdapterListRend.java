package br.com.usinasantafe.pmm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;

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

        TextView textViewRendNroOS = view.findViewById(R.id.textViewRendNroOS);
        TextView textViewRendValor = view.findViewById(R.id.textViewRendValor);

        RendMMBean rendMMBean = (RendMMBean) itens.get(position);

        textViewRendNroOS.setText("NRO OS: " + rendMMBean.getNroOSRendMM());

        if(rendMMBean.getValorRendMM() > 0){
            textViewRendValor.setText("REND.: " + rendMMBean.getValorRendMM());
        }
        else{
            textViewRendValor.setText("REND.: ");
        }

        return view;
    }
}
