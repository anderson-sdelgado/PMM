package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.RAtivParadaBean;

public class ParadaDAO {

    public ParadaDAO() {
    }

    public List getListParada(Long idAtiv){
        RAtivParadaBean rAtivParadaBean = new RAtivParadaBean();
        List rAtivParadaList = rAtivParadaBean.get("idAtiv", idAtiv);
        ArrayList<Long> rLista = new ArrayList<Long>();
        for (int i = 0; i < rAtivParadaList.size(); i++) {
            rAtivParadaBean = (RAtivParadaBean) rAtivParadaList.get(i);
            rLista.add(rAtivParadaBean.getIdParada());
        }
        rAtivParadaList.clear();
        ParadaBean paradaBean = new ParadaBean();
        return paradaBean.inAndOrderBy("idParada", rLista, "codParada", true);
    }

    public ParadaBean getParadaBean(String paradaString){
        ParadaBean paradaBean = new ParadaBean();
        List paradaList = paradaBean.get("codParada", paradaString.substring(0, paradaString.indexOf('-')).trim());
        paradaBean = (ParadaBean) paradaList.get(0);
        paradaList.clear();
        return paradaBean;
    }

}
