package br.com.usinasantafe.cmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ImplementoMMBean;

public class EquipSegDAO {

    public EquipSegDAO() {
    }

    public boolean verImple(Long nroEquip){
        return verEquipSeg(nroEquip, 3L);
    }

    public void setImplemento(Long pos, Long impl){
        ImplementoMMBean implementoMMBean = new ImplementoMMBean();
        List<ImplementoMMBean> implList = implementoMMBean.get("posImplMM", pos);
        if(implList.size() > 0) {
            implementoMMBean = implList.get(0);
            implementoMMBean.setCodEquipImplMM(impl);
            implementoMMBean.update();
        }
        else{
            implementoMMBean.setCodEquipImplMM(impl);
            implementoMMBean.setPosImplMM(pos);
            implementoMMBean.insert();
        }
        implList.clear();
    }

    public boolean verDuplicImple(Long nroEquip){
        ImplementoMMBean implementoMMBean = new ImplementoMMBean();
        List<ImplementoMMBean> implMMList = implementoMMBean.get("codEquipImplMM", nroEquip);
        return (implMMList.size() == 0);
    }

    public boolean verMotoBomba(Long nroEquip){
        return verEquipSeg(nroEquip, 4L);
    }

    public boolean verTransb(Long nroEquip){
        return verEquipSeg(nroEquip, 2L);
    }

    private boolean verEquipSeg(Long nroEquip, Long tipo){

        ArrayList pesqArrayList = new ArrayList();
        EquipSegBean equipSegBean = new EquipSegBean();

        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("nroEquip");
        pesquisa1.setValor(nroEquip);
        pesquisa1.setTipo(1);
        pesqArrayList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("tipoEquip");
        pesquisa2.setValor(tipo);
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        return (equipSegBean.get(pesqArrayList).size() > 0);

    }

    public EquipSegBean getEquipSeg(Long nroEquip){

        EquipSegBean equipSegBean = new EquipSegBean();
        List equipSegList = equipSegBean.get("nroEquip", nroEquip);
        equipSegBean = (EquipSegBean) equipSegList.get(0);
        equipSegList.clear();

        return equipSegBean;

    }

}
