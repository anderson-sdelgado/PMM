package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCLBean;

public class RespItemCLDAO {

    public RespItemCLDAO() {
    }

    public void clearRespItem(Long idCabCL){
        RespItemCLBean respItemCLBean = new RespItemCLBean();
        if (respItemCLBean.hasElements()) {
            List respList = respItemCLBean.get("idCabItCL", idCabCL);
            for (int i = 0; i < respList.size(); i++) {
                respItemCLBean = (RespItemCLBean) respList.get(i);
                respItemCLBean.delete();
            }
            respList.clear();
        }
    }

    public void setRespCheckList(Long idCabCL, RespItemCLBean respItemCLBean){

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("idCabItCL");
        pesquisa1.setValor(idCabCL);
        pesquisa1.setTipo(1);
        pesqArrayList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idItBDItCL");
        pesquisa2.setValor(respItemCLBean.getIdItBDItCL());
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        List respList = respItemCLBean.get(pesqArrayList);
        if(respList.size() > 0) {
            Long opcao = respItemCLBean.getOpItCL();
            respItemCLBean = (RespItemCLBean) respList.get(0);
            respItemCLBean.setOpItCL(opcao);
            respItemCLBean.update();
        }
        else{
            respItemCLBean.setIdCabItCL(idCabCL);
            respItemCLBean.insert();
        }
        respList.clear();
    }

    public List respItemList(Long idCabCL){
        RespItemCLBean respItemCLBean = new RespItemCLBean();
        return respItemCLBean.get("idCabItCL", idCabCL);
    }

}
