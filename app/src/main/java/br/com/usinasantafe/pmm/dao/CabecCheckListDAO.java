package br.com.usinasantafe.pmm.dao;

import java.util.List;

import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.bean.estaticas.EquipTO;
import br.com.usinasantafe.pmm.bean.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.bean.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.bean.variaveis.ConfigTO;

public class CabecCheckListDAO {

    public CabecCheckListDAO() {
    }

    public boolean verCabecAberto(){
        CabecCLTO cabecCLTO = new CabecCLTO();
        List cabecList = cabecCLTO.get("statusCabCL", 1L);
        Boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public CabecCLTO getCabecAberto(){
        CabecCLTO cabecCLTO = new CabecCLTO();
        List cabecList = cabecCLTO.get("statusCabCL", 1L);
        cabecCLTO = (CabecCLTO) cabecList.get(0);
        cabecList.clear();
        return cabecCLTO;
    }

    public void createCabecAberto(BoletimCTR boletimCTR){

        ConfigCTR configCTR = new ConfigCTR();

        CabecCLTO cabecCLTO = new CabecCLTO();
        cabecCLTO.setDtCabCL(Tempo.getInstance().dataComHora().getDataHora());
        cabecCLTO.setEquipCabCL(configCTR.getEquip().getNroEquip());
        cabecCLTO.setFuncCabCL(boletimCTR.getFunc());
        cabecCLTO.setTurnoCabCL(boletimCTR.getTurno());
        cabecCLTO.setStatusCabCL(1L);
        cabecCLTO.insert();

    }

    public boolean verAberturaCheckList(Long idTurno){

        ConfigCTR configCTR = new ConfigCTR();
        EquipTO equipTO = configCTR.getEquip();
        ConfigTO configTO = configCTR.getConfig();

        if ((equipTO.getIdCheckList() > 0) &&
                ((configTO.getUltTurnoCLConfig() != idTurno)
                        || ((configTO.getUltTurnoCLConfig() == idTurno)
                                    && (!configTO.getDtUltCLConfig().equals(Tempo.getInstance().dataSHora()))))) {
            return true;
        }
        else{
            return false;
        }

    }

    public ItemCheckListTO getItemCheckList(int pos){

        ConfigCTR configCTR = new ConfigCTR();

        ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
        List itemCheckList = itemCheckListTO.getAndOrderBy("idCheckList", configCTR.getEquip().getIdCheckList(), "idItemCheckList", true);
        itemCheckListTO = (ItemCheckListTO) itemCheckList.get(pos - 1);
        itemCheckList.clear();

        return itemCheckListTO;

    }

    public void salvarFechCheckList() {
        CabecCLTO cabecCLTO = getCabecAberto();
        cabecCLTO.setStatusCabCL(2L);
        cabecCLTO.update();
    }

    public List bolFechList(){
        CabecCLTO cabecCLTO = new CabecCLTO();
        return cabecCLTO.get("statusCabCL", 2L);
    }

}
