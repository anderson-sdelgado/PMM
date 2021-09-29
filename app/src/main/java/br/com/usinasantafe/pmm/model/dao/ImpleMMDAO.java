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

    public ArrayList<String> impleAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("APONT. IMPLEMENTO");
        ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
        List<ApontImpleMMBean> apontMMFertList = apontImpleMMBean.orderBy("idApontImpleMM", true);
        for (ApontImpleMMBean apontImpleMMBeanBD : apontMMFertList) {
            dadosArrayList.add(dadosApontImpleMM(apontImpleMMBeanBD));
        }
        apontMMFertList.clear();
        return dadosArrayList;
    }

    public void salvarApontImple(Long idApont, String dthr, String activity){

        ImpleMMBean impleMMBean = new ImpleMMBean();
        List<ImpleMMBean> impleList = impleMMBean.all();
        for (ImpleMMBean impleMMBeanBD : impleList) {
            LogProcessoDAO.getInstance().insertLogProcesso("ImpleMMBean impleMMBean = new ImpleMMBean();\n" +
                    "        List<ImpleMMBean> impleList = impleMMBean.all();\n" +
                    "        for (ImpleMMBean impleMMBeanBD : impleList) {\n" +
                    "            ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();\n" +
                    "            apontImpleMMBean.setIdApontMMFert(" + idApont + ");\n" +
                    "            apontImpleMMBean.setCodEquipImpleMM(" + impleMMBeanBD.getCodEquipImpleMM() + ");\n" +
                    "            apontImpleMMBean.setPosImpleMM(" + impleMMBeanBD.getPosImpleMM() + ");\n" +
                    "            apontImpleMMBean.setDthrImpleMM(" + dthr + ");", activity);
            ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
            apontImpleMMBean.setIdApontMMFert(idApont);
            apontImpleMMBean.setCodEquipImpleMM(impleMMBeanBD.getCodEquipImpleMM());
            apontImpleMMBean.setPosImpleMM(impleMMBeanBD.getPosImpleMM());
            apontImpleMMBean.setDthrImpleMM(dthr);
            apontImpleMMBean.insert();
        }

    }

    private String dadosApontImpleMM(ApontImpleMMBean apontImpleMMBean){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(apontImpleMMBean, apontImpleMMBean.getClass()).toString();
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
