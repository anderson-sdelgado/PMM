package br.com.usinasantafe.pmm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.to.tb.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaTO;

/**
 * Created by anderson on 08/03/2018.
 */

public class AdapterListHistorico extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;

    public AdapterListHistorico(Context context, List itens) {
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

        view = layoutInflater.inflate(R.layout.activity_item_historico, null);
        TextView textViewHistApont = (TextView) view.findViewById(R.id.textViewHistApont);
        TextView textViewHistHorario = (TextView) view.findViewById(R.id.textViewHistHorario);
        TextView textViewHistImplTransb = (TextView) view.findViewById(R.id.textViewHistImplTransb);

        BackupApontaTO backupApontaTO = (BackupApontaTO) itens.get(position);
        if(backupApontaTO.getParadaAponta() == 0) {
            AtividadeTO atividadeTO = new AtividadeTO();
            List atividadeList = atividadeTO.get("idAtiv", backupApontaTO.getAtividadeAponta());
            atividadeTO = (AtividadeTO) atividadeList.get(0);
            textViewHistApont.setText("ATIVIDADE: " + atividadeTO.getCodAtiv() + " - " + atividadeTO.getDescrAtiv());
            textViewHistApont.setTextColor(Color.BLUE);
        }
        else{
            ParadaTO paradaTO = new ParadaTO();
            List paradaList = paradaTO.get("idParada", backupApontaTO.getParadaAponta());
            paradaTO = (ParadaTO) paradaList.get(0);
            textViewHistApont.setText("PARADA: " + paradaTO.getCodParada() + " - " + paradaTO.getDescrParada());
            textViewHistApont.setTextColor(Color.RED);
        }

        textViewHistHorario.setText("HORÁRIO: " + backupApontaTO.getDthrAponta().substring(11));

        if(backupApontaTO.getTransbAponta() > 0){
            textViewHistImplTransb.setText("TRANSBORDO: " + backupApontaTO.getTransbAponta());
        }
        else{
            textViewHistImplTransb.setText("");
        }

        return view;
    }
}
