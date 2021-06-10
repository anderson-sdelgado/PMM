package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ImpleMMBean;

public class ImpleMMDAO {

    public ImpleMMDAO() {
    }

    public void impleMMDelAll(){
        ImpleMMBean impleMMBean = new ImpleMMBean();
        impleMMBean.deleteAll();
    }

    public List<ApontImpleMMBean> apontImpleEnvioList(ArrayList<Long> idApontList){
        ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
        return apontImpleMMBean.in("idApontMMFert", idApontList);
    }

    public void salvarApontImple(Long idApont, String dthr){

        ImpleMMBean impleMMBean = new ImpleMMBean();
        List impleList = impleMMBean.all();

        for (int i = 0; i < impleList.size(); i++) {
            impleMMBean = (ImpleMMBean) impleList.get(i);
            ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
            apontImpleMMBean.setIdApontMMFert(idApont);
            apontImpleMMBean.setCodEquipImpleMM(impleMMBean.getCodEquipImpleMM());
            apontImpleMMBean.setPosImpleMM(impleMMBean.getPosImpleMM());
            apontImpleMMBean.setDthrImpleMM(dthr);
            apontImpleMMBean.insert();
        }

    }

    public String dadosEnvioApontImpleMM(List<ApontImpleMMBean> apontImpleMMList){

        JsonArray jsonArrayImplemento = new JsonArray();

        for (ApontImpleMMBean apontImpleMMBean : apontImpleMMList) {
            Gson gsonItemImp = new Gson();
            jsonArrayImplemento.add(gsonItemImp.toJsonTree(apontImpleMMBean, apontImpleMMBean.getClass()));
        }

        apontImpleMMList.clear();

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("implemento", jsonArrayImplemento);

        return jsonImplemento.toString();

    }

    public void deleteImple(ArrayList<Long> idApontArrayList){

        ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
        List<ApontImpleMMBean> implementoList = apontImpleMMBean.in("idApontMMFert", idApontArrayList);

        for (int l = 0; l < implementoList.size(); l++) {
            apontImpleMMBean = (ApontImpleMMBean) implementoList.get(l);
            apontImpleMMBean.delete();
        }

        idApontArrayList.clear();

    }

}
