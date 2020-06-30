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

    public void setImplemento(ImpleMMBean impleMMBean){
        List impleList = impleMMBean.get("posImpleMM", impleMMBean.getPosImpleMM());
        if(impleList.size() > 0) {
            Long imp = impleMMBean.getCodEquipImpleMM();
            impleMMBean = (ImpleMMBean) impleList.get(0);
            impleMMBean.setCodEquipImpleMM(imp);
            impleMMBean.update();
        }
        else{
            impleMMBean.insert();
        }
        impleList.clear();
    }

    public boolean verDuplicImple(Long nroEquip){
        ImpleMMBean impleMMBean = new ImpleMMBean();
        List impleMMList = impleMMBean.get("codEquipImpleMM", nroEquip);
        return (impleMMList.size() == 0);
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
