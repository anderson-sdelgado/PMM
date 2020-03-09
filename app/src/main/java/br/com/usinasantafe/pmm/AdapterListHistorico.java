package br.com.usinasantafe.pmm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMBean;

/**
 * Created by anderson on 08/03/2018.
 */

public class AdapterListHistorico extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;
    private Long tipoEquip;
    private TextView textViewHistApont;
    private TextView textViewHistHorario;
    private TextView textViewHistDetalhes;

    public AdapterListHistorico(Context context, List itens, Long tipoEquip) {
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
        this.tipoEquip = tipoEquip;
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
        textViewHistApont = (TextView) view.findViewById(R.id.textViewHistApont);
        textViewHistHorario = (TextView) view.findViewById(R.id.textViewHistHorario);
        textViewHistDetalhes = (TextView) view.findViewById(R.id.textViewHistDetalhes);

        if (tipoEquip == 1L) {
            ApontMMBean apontMMBean = (ApontMMBean) itens.get(position);
            descrApont(apontMMBean.getParadaApontMM(), apontMMBean.getAtivApontMM());
            horarioApont(apontMMBean.getDthrApontMM());
            if(apontMMBean.getTransbApontMM() > 0){
                textViewHistDetalhes.setText("TRANSBORDO: " + apontMMBean.getTransbApontMM());
            }
            else{
                textViewHistDetalhes.setText("");
            }
        }
        else{
            ApontFertBean apontFertBean = (ApontFertBean) itens.get(position);
            descrApont(apontFertBean.getParadaApontFert(), apontFertBean.getAtivApontFert());
            horarioApont(apontFertBean.getDthrApontFert());
            if(apontFertBean.getParadaApontFert() > 0) {
                textViewHistDetalhes.setText("");
            }
            else{
                BocalBean bocalBean = new BocalBean();
                List bocalList = bocalBean.get("idBocal", apontFertBean.getBocalApontFert());
                bocalBean = (BocalBean) bocalList.get(0);
                bocalList.clear();
                textViewHistDetalhes.setText("BOCAL: " + bocalBean.getDescrBocal() + "\n" +
                        "PRESSÃO: " + apontFertBean.getPressaoApontFert() + "\n" +
                        "VELOCIDADE: " + apontFertBean.getVelocApontFert());
            }
        }

        return view;
    }

    public void descrApont(Long parada, Long ativ){
        if(parada == 0) {
            AtividadeBean atividadeBean = new AtividadeBean();
            List atividadeList = atividadeBean.get("idAtiv", ativ);
            atividadeBean = (AtividadeBean) atividadeList.get(0);
            textViewHistApont.setText("ATIVIDADE: " + atividadeBean.getCodAtiv() + " - " + atividadeBean.getDescrAtiv());
            textViewHistApont.setTextColor(Color.BLUE);
        }
        else{
            ParadaBean paradaBean = new ParadaBean();
            List paradaList = paradaBean.get("idParada", parada);
            paradaBean = (ParadaBean) paradaList.get(0);
            textViewHistApont.setText("PARADA: " + paradaBean.getCodParada() + " - " + paradaBean.getDescrParada());
            textViewHistApont.setTextColor(Color.RED);
        }
    }

    public void horarioApont(String dataHora){
        textViewHistHorario.setText("HORÁRIO: " + Tempo.getInstance().dataHoraCTZ(dataHora).substring(11));
    }

}
