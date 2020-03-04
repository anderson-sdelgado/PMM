package br.com.usinasantafe.pmm.bean.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.bean.variaveis.RespItemCLTO;

public class RespItemCLDAO {

    public RespItemCLDAO() {
    }

    public void clearRespItem(Long idCabCL){
        RespItemCLTO respItemCLTO = new RespItemCLTO();
        if (respItemCLTO.hasElements()) {
            List respList = respItemCLTO.get("idCabItCL", idCabCL);
            for (int i = 0; i < respList.size(); i++) {
                respItemCLTO = (RespItemCLTO) respList.get(i);
                respItemCLTO.delete();
            }
            respList.clear();
        }
    }

    public void setRespCheckList(Long idCabCL, RespItemCLTO respItemCLTO){

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("idCabItCL");
        pesquisa1.setValor(idCabCL);
        pesquisa1.setTipo(1);
        pesqArrayList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idItBDItCL");
        pesquisa2.setValor(respItemCLTO.getIdItBDItCL());
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        List respList = respItemCLTO.get(pesqArrayList);
        if(respList.size() > 0) {
            Long opcao = respItemCLTO.getOpItCL();
            respItemCLTO = (RespItemCLTO) respList.get(0);
            respItemCLTO.setOpItCL(opcao);
            respItemCLTO.update();
        }
        else{
            respItemCLTO.setIdCabItCL(idCabCL);
            respItemCLTO.insert();
        }
        respList.clear();
    }

    public List respItemList(Long idCabCL){
        RespItemCLTO respItemCLTO = new RespItemCLTO();
        return respItemCLTO.get("idCabItCL", idCabCL);
    }

}
