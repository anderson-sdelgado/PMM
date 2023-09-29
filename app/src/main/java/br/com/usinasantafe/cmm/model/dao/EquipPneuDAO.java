package br.com.usinasantafe.cmm.model.dao;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.estaticas.EquipPneuBean;

public class EquipPneuDAO {

    public EquipPneuDAO() {
    }

    public boolean verEquipPneuNro(Long nroEquip){
        EquipPneuBean equipPneuBean = new EquipPneuBean();
        List<EquipPneuBean> equipPneuList = equipPneuBean.get("nroEquip", nroEquip);
        boolean ret = equipPneuList.size() > 0;
        equipPneuList.clear();
        return ret;
    }

    public EquipPneuBean getEquipPneuNro(Long nroEquip){
        EquipPneuBean equipPneuBean = new EquipPneuBean();
        List<EquipPneuBean> equipPneuList = equipPneuBean.get("nroEquip", nroEquip);
        equipPneuBean = equipPneuList.get(0);
        equipPneuList.clear();
        return equipPneuBean;
    }

}
