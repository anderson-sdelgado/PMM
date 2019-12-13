package br.com.usinasantafe.pmm.dao;

import java.util.List;

import br.com.usinasantafe.pmm.bean.estaticas.REquipPneuTO;
import br.com.usinasantafe.pmm.bean.variaveis.CabecPneuTO;
import br.com.usinasantafe.pmm.util.Tempo;

public class CabecPneuDAO {

    public CabecPneuDAO() {
    }

    public boolean verCabecPneuAberto() {
        CabecPneuTO cabecPneuTO = new CabecPneuTO();
        List cabecPneuList = cabecPneuTO.get("statusCabecPneu", 1L);
        boolean ret = (cabecPneuList.size() > 0);
        cabecPneuList.clear();
        return ret;
    }

    public CabecPneuTO getCabecPneuAberto(){
        CabecPneuTO cabecPneuTO = new CabecPneuTO();
        List cabecPneuList = cabecPneuTO.get("statusCabecPneu", 1L);
        cabecPneuTO = (CabecPneuTO) cabecPneuList.get(0);
        cabecPneuList.clear();
        return cabecPneuTO;
    }

    public Long getIdCabecAberto(){
        CabecPneuTO cabecPneuTO = getCabecPneuAberto();
        return cabecPneuTO.getIdCabecPneu();
    }

    public void salvarDados(Long func, Long equip, Long idApont){
        CabecPneuTO cabecPneuTO = new CabecPneuTO();
        cabecPneuTO.setIdApontCabecPneu(idApont);
        cabecPneuTO.setEquipCabecPneu(equip);
        cabecPneuTO.setFuncCabecPneu(func);
        cabecPneuTO.setDthrCabecPneu(Tempo.getInstance().dataComHora().getDataHora());
        cabecPneuTO.setStatusCabecPneu(1L);
        cabecPneuTO.insert();
    }

    public boolean verFechCabec(List itemMedPneuList){
        REquipPneuTO rEquipPneuTO = new REquipPneuTO();
        List rEquipPneuList = rEquipPneuTO.all();
        boolean verif = (rEquipPneuList.size() == itemMedPneuList.size());
        if(verif){
            CabecPneuTO cabecPneuTO = getCabecPneuAberto();
            cabecPneuTO.setStatusCabecPneu(2L);
            cabecPneuTO.update();
        }
        rEquipPneuList.clear();
        itemMedPneuList.clear();
        return verif;
    }

}
