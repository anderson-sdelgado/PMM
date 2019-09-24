package br.com.usinasantafe.pmm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.to.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.estaticas.BocalTO;
import br.com.usinasantafe.pmm.to.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.BackupApontaTO;

/**
 * Created by anderson on 08/03/2018.
 */

public class AdapterListHistorico extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;
    private int tipoEquip;
    private TextView textViewHistApont;
    private TextView textViewHistHorario;
    private TextView textViewHistDetalhes;

    public AdapterListHistorico(Context context, List itens, int tipoEquip) {
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

        if (tipoEquip == 1) {
            ApontMMTO apontMMTO = (ApontMMTO) itens.get(position);
            descrApont(apontMMTO.getParadaApontMM(), apontMMTO.getAtivApontMM());
            horarioApont(apontMMTO.getDthrApontMM());
            if(apontMMTO.getTransbApontMM() > 0){
                textViewHistDetalhes.setText("TRANSBORDO: " + apontMMTO.getTransbApontMM());
            }
            else{
                textViewHistDetalhes.setText("");
            }
        }
        else{
            ApontFertTO apontFertTO = (ApontFertTO) itens.get(position);
            descrApont(apontFertTO.getParadaApontFert(), apontFertTO.getAtivApontFert());
            horarioApont(apontFertTO.getDthrApontFert());
            if(apontFertTO.getParadaApontFert() > 0) {
                textViewHistDetalhes.setText("");
            }
            else{
                BocalTO bocalTO = new BocalTO();
                List bocalList = bocalTO.get("idBocal", apontFertTO.getBocalApontFert());
                bocalTO = (BocalTO) bocalList.get(0);
                bocalList.clear();
                textViewHistDetalhes.setText("BOCAL: " + bocalTO.getDescrBocal() + "\n" +
                        "PRESSÃO: " + apontFertTO.getPressaoApontFert() + "\n" +
                        "VELOCIDADE: " + apontFertTO.getVelocApontFert());
            }
        }

        return view;
    }

    public void descrApont(Long parada, Long ativ){
        if(parada == 0) {
            AtividadeTO atividadeTO = new AtividadeTO();
            List atividadeList = atividadeTO.get("idAtiv", ativ);
            atividadeTO = (AtividadeTO) atividadeList.get(0);
            textViewHistApont.setText("ATIVIDADE: " + atividadeTO.getCodAtiv() + " - " + atividadeTO.getDescrAtiv());
            textViewHistApont.setTextColor(Color.BLUE);
        }
        else{
            ParadaTO paradaTO = new ParadaTO();
            List paradaList = paradaTO.get("idParada", parada);
            paradaTO = (ParadaTO) paradaList.get(0);
            textViewHistApont.setText("PARADA: " + paradaTO.getCodParada() + " - " + paradaTO.getDescrParada());
            textViewHistApont.setTextColor(Color.RED);
        }
    }

    public void horarioApont(String dataHora){
        textViewHistHorario.setText("HORÁRIO: " + Tempo.getInstance().dataHoraCTZ(dataHora).substring(11));
    }

}
