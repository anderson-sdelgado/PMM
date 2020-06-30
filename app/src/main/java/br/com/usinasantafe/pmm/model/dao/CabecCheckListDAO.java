package br.com.usinasantafe.pmm.model.dao;

import java.util.List;

import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;

public class CabecCheckListDAO {

    public CabecCheckListDAO() {
    }

    public boolean verCabecAberto(){
        CabecCLBean cabecCLBean = new CabecCLBean();
        List cabecList = cabecCLBean.get("statusCabCL", 1L);
        Boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public CabecCLBean getCabecAberto(){
        CabecCLBean cabecCLBean = new CabecCLBean();
        List cabecList = cabecCLBean.get("statusCabCL", 1L);
        cabecCLBean = (CabecCLBean) cabecList.get(0);
        cabecList.clear();
        return cabecCLBean;
    }

    public void createCabecAberto(BoletimCTR boletimCTR){

        ConfigCTR configCTR = new ConfigCTR();

        CabecCLBean cabecCLBean = new CabecCLBean();
        cabecCLBean.setDtCabCL(Tempo.getInstance().dataComHora());
        cabecCLBean.setEquipCabCL(configCTR.getEquip().getNroEquip());
        cabecCLBean.setFuncCabCL(boletimCTR.getFunc());
        cabecCLBean.setTurnoCabCL(boletimCTR.getTurno());
        cabecCLBean.setStatusCabCL(1L);
        cabecCLBean.insert();

    }

    public boolean verAberturaCheckList(Long idTurno){

        ConfigCTR configCTR = new ConfigCTR();
        EquipBean equipBean = configCTR.getEquip();
        ConfigBean configBean = configCTR.getConfig();

        if ((equipBean.getIdCheckList() > 0) &&
                ((configBean.getUltTurnoCLConfig() != idTurno)
                        || ((configBean.getUltTurnoCLConfig() == idTurno)
                                    && (!configBean.getDtUltCLConfig().equals(Tempo.getInstance().dataSHora()))))) {
            return true;
        }
        else{
            return false;
        }

    }

    public ItemCheckListBean getItemCheckList(int pos){

        ConfigCTR configCTR = new ConfigCTR();

        ItemCheckListBean itemCheckListBean = new ItemCheckListBean();
        List itemCheckList = itemCheckListBean.getAndOrderBy("idCheckList", configCTR.getEquip().getIdCheckList(), "idItemCheckList", true);
        itemCheckListBean = (ItemCheckListBean) itemCheckList.get(pos - 1);
        itemCheckList.clear();

        return itemCheckListBean;

    }

    public void salvarFechCheckList() {
        CabecCLBean cabecCLBean = getCabecAberto();
        cabecCLBean.setStatusCabCL(2L);
        cabecCLBean.update();
    }

    public List bolFechList(){
        CabecCLBean cabecCLBean = new CabecCLBean();
        return cabecCLBean.get("statusCabCL", 2L);
    }

}
