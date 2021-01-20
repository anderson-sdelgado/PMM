package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCheckListBean;

public class RespItemCLDAO {

    public RespItemCLDAO() {
    }

    public void clearRespItem(Long idCabCL){
        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        if (respItemCheckListBean.hasElements()) {
            List respList = respItemCheckListBean.get("idCabItCL", idCabCL);
            for (int i = 0; i < respList.size(); i++) {
                respItemCheckListBean = (RespItemCheckListBean) respList.get(i);
                respItemCheckListBean.delete();
            }
            respList.clear();
        }
    }

    public void setRespCheckList(Long idCabCL, RespItemCheckListBean respItemCheckListBean){

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("idCabItCL");
        pesquisa1.setValor(idCabCL);
        pesquisa1.setTipo(1);
        pesqArrayList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idItBDItCL");
        pesquisa2.setValor(respItemCheckListBean.getIdItBDItCL());
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        List respList = respItemCheckListBean.get(pesqArrayList);
        if(respList.size() > 0) {
            Long opcao = respItemCheckListBean.getOpItCL();
            respItemCheckListBean = (RespItemCheckListBean) respList.get(0);
            respItemCheckListBean.setOpItCL(opcao);
            respItemCheckListBean.update();
        }
        else{
            respItemCheckListBean.setIdCabItCL(idCabCL);
            respItemCheckListBean.insert();
        }
        respList.clear();
    }

    public List respItemList(Long idCabCL){
        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        return respItemCheckListBean.get("idCabItCL", idCabCL);
    }

}
