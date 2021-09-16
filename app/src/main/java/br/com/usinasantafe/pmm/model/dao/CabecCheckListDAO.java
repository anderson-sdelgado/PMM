package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;

public class CabecCheckListDAO {

    public CabecCheckListDAO() {
    }

    public boolean verCabecAberto(){
        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List cabecList = cabecCheckListBean.get("statusCabCL", 1L);
        Boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public CabecCheckListBean getCabecAberto(){
        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List cabecList = cabecCheckListBean.get("statusCabCL", 1L);
        cabecCheckListBean = (CabecCheckListBean) cabecList.get(0);
        cabecList.clear();
        return cabecCheckListBean;
    }

    public void createCabecAberto(Long nroEquip, Long matricFunc, Long idTurno){
        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        cabecCheckListBean.setDtCabCL(Tempo.getInstance().dthr());
        cabecCheckListBean.setEquipCabCL(nroEquip);
        cabecCheckListBean.setFuncCabCL(matricFunc);
        cabecCheckListBean.setTurnoCabCL(idTurno);
        cabecCheckListBean.setStatusCabCL(1L);
        cabecCheckListBean.insert();
    }

    public boolean verAberturaCheckList(Long idTurno){

        ConfigCTR configCTR = new ConfigCTR();
        EquipBean equipBean = configCTR.getEquip();
        ConfigBean configBean = configCTR.getConfig();

        if ((equipBean.getIdCheckList() > 0) &&
                ((configBean.getUltTurnoCLConfig() != idTurno)
                        || ((configBean.getUltTurnoCLConfig() == idTurno)
                                    && (!configBean.getDtUltCLConfig().equals(Tempo.getInstance().dt()))))) {
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
        CabecCheckListBean cabecCheckListBean = getCabecAberto();
        cabecCheckListBean.setStatusCabCL(2L);
        cabecCheckListBean.update();
        EnvioDadosServ.getInstance().envioDados();
    }

    public List<CabecCheckListBean> cabecCheckListFechList(){
        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        return cabecCheckListBean.get("statusCabCL", 2L);
    }

    public ArrayList<Long> idCabecCLFechArrayList() {

        List<CabecCheckListBean> cabecCheckListList = cabecCheckListFechList();
        ArrayList<Long> idCabecCheckListLongs = new ArrayList<Long>();

        for (CabecCheckListBean cabecCheckListBean : cabecCheckListList) {
            idCabecCheckListLongs.add(cabecCheckListBean.getIdCabCL());
        }

        return idCabecCheckListLongs;

    }

    public void delCabecCLFech(){
        List<CabecCheckListBean> cabecCheckListList = cabecCheckListFechList();
        for(CabecCheckListBean cabecCheckListBean : cabecCheckListList){
            cabecCheckListBean.delete();
        }
        cabecCheckListList.clear();
    }

}
