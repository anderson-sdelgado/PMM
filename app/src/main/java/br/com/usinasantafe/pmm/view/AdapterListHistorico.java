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
import br.com.usinasantafe.pmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ParadaBean;

/**
 * Created by anderson on 08/03/2018.
 */
public class AdapterListHistorico extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;
    private TextView textViewHistApont;
    private TextView textViewHistHorario;
    private TextView textViewHistDetalhes;

    public AdapterListHistorico(Context context, List itens) {
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
        textViewHistApont = view.findViewById(R.id.textViewHistApont);
        textViewHistHorario = view.findViewById(R.id.textViewHistHorario);
        textViewHistDetalhes = view.findViewById(R.id.textViewHistDetalhes);

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();

        ApontMMFertBean apontMMFertBean = (ApontMMFertBean) itens.get(position);
        descrApont(apontMMFertBean.getParadaApontMMFert(), apontMMFertBean.getAtivApontMMFert());
        horarioApont(apontMMFertBean.getDthrApontMMFert());
        if(apontMMFertBean.getTransbApontMMFert() > 0){
            textViewHistDetalhes.setText("TRANSBORDO: " + apontMMFertBean.getTransbApontMMFert());
        }
        else if(apontMMFertBean.getBocalApontMMFert() > 0){
            BocalBean bocalBean = motoMecFertCTR.getBocal(apontMMFertBean.getBocalApontMMFert());
            textViewHistDetalhes.setText("BOCAL: " + bocalBean.getDescrBocal() + "\n" +
                    "PRESSÃO: " + apontMMFertBean.getPressaoApontMMFert() + "\n" +
                    "VELOCIDADE: " + apontMMFertBean.getVelocApontMMFert());
        }
        else{
            textViewHistDetalhes.setText("");
        }

        return view;
    }

    public void descrApont(Long idParada, Long idAtiv){
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        if(idParada == 0) {
            AtividadeBean atividadeBean = motoMecFertCTR.getAtividade(idAtiv);
            textViewHistApont.setText("ATIVIDADE: " + atividadeBean.getCodAtiv() + " - " + atividadeBean.getDescrAtiv());
            textViewHistApont.setTextColor(Color.BLUE);
        }
        else{
            ParadaBean paradaBean = motoMecFertCTR.getParada(idParada);
            textViewHistApont.setText("PARADA: " + paradaBean.getCodParada() + " - " + paradaBean.getDescrParada());
            textViewHistApont.setTextColor(Color.RED);
        }
    }

    public void horarioApont(String dataHora){
        textViewHistHorario.setText("HORÁRIO: " + dataHora.substring(11));
    }

}
