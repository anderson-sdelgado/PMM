package br.com.usinasantafe.pmm.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.control.MotoMecFertCTR;
import br.com.usinasantafe.pmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogProcessoBean;

/**
 * Created by anderson on 08/03/2018.
 */

public class AdapterListErro extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;
    private TextView textViewErroId;
    private TextView textViewErroEquip;
    private TextView textViewErroDthr;
    private TextView textViewErroDescr;

    public AdapterListErro(Context context, List itens) {
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

        view = layoutInflater.inflate(R.layout.activity_item_historico, null);
        textViewErroId = view.findViewById(R.id.textViewErroId);
        textViewErroEquip = view.findViewById(R.id.textViewErroEquip);
        textViewErroDthr = view.findViewById(R.id.textViewErroDthr);
        textViewErroDescr = view.findViewById(R.id.textViewErroDescr);

        LogErroBean logErroBean = (LogErroBean) itens.get(position);

        textViewErroId.setText("ID = " + logErroBean.getIdLogErro());
        textViewErroDthr.setText("DTHR = " + logErroBean.getDthr());
        textViewErroDescr.setText(logErroBean.getException());

        return view;
    }

}
