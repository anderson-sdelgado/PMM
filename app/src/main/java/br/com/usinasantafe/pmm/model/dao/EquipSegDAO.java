package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ImpleMMBean;

public class EquipSegDAO {

    public EquipSegDAO() {
    }

    public boolean verImple(Long nroEquip){
        return verEquipSeg(nroEquip, 3L);
    }

    public void setImplemento(Long pos, Long impl){
        ImpleMMBean impleMMBean = new ImpleMMBean();
        List<ImpleMMBean> implList = impleMMBean.get("posImpleMM", pos);
        if(implList.size() > 0) {
            impleMMBean = implList.get(0);
            impleMMBean.setCodEquipImpleMM(impl);
            impleMMBean.update();
        }
        else{
            impleMMBean.setCodEquipImpleMM(impl);
            impleMMBean.setPosImpleMM(pos);
            impleMMBean.insert();
        }
        implList.clear();
    }

    public boolean verDuplicImple(Long nroEquip){
        ImpleMMBean impleMMBean = new ImpleMMBean();
        List<ImpleMMBean> implMMList = impleMMBean.get("codEquipImpleMM", nroEquip);
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
