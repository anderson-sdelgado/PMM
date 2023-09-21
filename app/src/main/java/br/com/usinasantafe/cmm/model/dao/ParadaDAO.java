package br.com.usinasantafe.cmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.RAtivParadaBean;

public class ParadaDAO {

    public ParadaDAO() {
    }

    public ParadaBean getParada(Long idParada){
        ParadaBean paradaBean = new ParadaBean();
        List<ParadaBean> paradaList = paradaBean.get("idParada", idParada);
        paradaBean = paradaList.get(0);
        paradaList.clear();
        return paradaBean;
    }

    public List<ParadaBean> getListParada(Long idAtiv){
        RAtivParadaBean rAtivParadaBean = new RAtivParadaBean();
        List<RAtivParadaBean> rAtivParadaList = rAtivParadaBean.get("idAtiv", idAtiv);
        ArrayList<Long> rLista = new ArrayList<Long>();
        for (int i = 0; i < rAtivParadaList.size(); i++) {
            rAtivParadaBean = rAtivParadaList.get(i);
            rLista.add(rAtivParadaBean.getIdParada());
        }
        rAtivParadaList.clear();
        ParadaBean paradaBean = new ParadaBean();
        return paradaBean.inAndOrderBy("idParada", rLista, "codParada", true);
    }

    public ParadaBean getParadaBean(String paradaString){
        ParadaBean paradaBean = new ParadaBean();
        List<ParadaBean> paradaList = paradaBean.get("codParada", paradaString.substring(0, paradaString.indexOf('-')).trim());
        paradaBean = paradaList.get(0);
        paradaList.clear();
        return paradaBean;
    }

}
