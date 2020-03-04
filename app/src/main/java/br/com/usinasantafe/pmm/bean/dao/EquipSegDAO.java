package br.com.usinasantafe.pmm.bean.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.bean.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.bean.variaveis.ImpleMMTO;

public class EquipSegDAO {

    public EquipSegDAO() {
    }

    public boolean verImple(Long nroEquip){
        return verEquipSeg(nroEquip, 3L);
    }

    public void setImplemento(ImpleMMTO impleMMTO){
        List impleList = impleMMTO.get("posImpleMM", impleMMTO.getPosImpleMM());
        if(impleList.size() > 0) {
            Long imp = impleMMTO.getCodEquipImpleMM();
            impleMMTO = (ImpleMMTO) impleList.get(0);
            impleMMTO.setCodEquipImpleMM(imp);
            impleMMTO.update();
        }
        else{
            impleMMTO.insert();
        }
        impleList.clear();
    }

    public boolean verDuplicImple(Long nroEquip){
        ImpleMMTO impleMMTO = new ImpleMMTO();
        List impleMMList = impleMMTO.get("codEquipImpleMM", nroEquip);
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
        EquipSegTO equipSegTO = new EquipSegTO();

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

        return (equipSegTO.get(pesqArrayList).size() > 0);

    }

}
