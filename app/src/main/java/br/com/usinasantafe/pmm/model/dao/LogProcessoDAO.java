package br.com.usinasantafe.pmm.model.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.Tempo;

public class LogProcessoDAO {

    private static LogProcessoDAO instance = null;

    public static LogProcessoDAO getInstance() {
        if (instance == null)
            instance = new LogProcessoDAO();
        return instance;
    }

    public void insertLogProcesso(String processo, String activity){
        LogProcessoBean logProcessoBean = new LogProcessoBean();
        logProcessoBean.setProcesso(processo);
        logProcessoBean.setActivity(activity);
        logProcessoBean.setDthr(Tempo.getInstance().dthr());
        logProcessoBean.setDthrLong(Tempo.getInstance().dthrStringToLong(Tempo.getInstance().dthr()));
        logProcessoBean.insert();
    }

    public List<LogProcessoBean> logProcessoList(){
        LogProcessoBean logProcessoBean = new LogProcessoBean();
        return logProcessoBean.orderBy("idLogProcesso", false);
    }

    public void deleteLogProcesso(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDtrhLongDia1Menos(Tempo.getInstance().dthrLongDia1Menos()));

        LogProcessoBean logProcessoBean = new LogProcessoBean();
        logProcessoBean.deleteGet(pesqArrayList);
    }

    private EspecificaPesquisa getPesqDtrhLongDia1Menos(Long dtrhLongDia1Menos){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("dthrLong");
        pesquisa.setValor(dtrhLongDia1Menos);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
