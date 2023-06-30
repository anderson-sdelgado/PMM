package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ApontImplMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ImplementoMMBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;

public class ImplementoMMDAO {

    public ImplementoMMDAO() {
    }

    public void implementoMMDelAll(){
        ImplementoMMBean implementoMMBean = new ImplementoMMBean();
        implementoMMBean.deleteAll();
    }

    public List<ApontImplMMBean> apontImplEnvioList(ArrayList<Long> idApontList){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusEnvioApontImpl());
        ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
        return apontImplMMBean.inAndGetAndOrderBy("idApontMMFert", idApontList, pesqArrayList, "idApontImplMM", true);
    }

    public ArrayList<String> apontImplAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("APONT. IMPLEMENTO");
        ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
        List<ApontImplMMBean> apontMMFertList = apontImplMMBean.orderBy("idApontImplMM", true);
        for (ApontImplMMBean apontImplMMBeanBD : apontMMFertList) {
            dadosArrayList.add(dadosApontImplMM(apontImplMMBeanBD));
        }
        apontMMFertList.clear();
        return dadosArrayList;
    }

    public void salvarApontImpl(Long idApont, String dthr, String activity){

        ImplementoMMBean implementoMMBean = new ImplementoMMBean();
        List<ImplementoMMBean> implementoList = implementoMMBean.all();
        for (ImplementoMMBean implementoMMBeanBD : implementoList) {
            LogProcessoDAO.getInstance().insertLogProcesso("ImplMMBean impleMMBean = new ImplMMBean();\n" +
                    "        List<ImpleMMBean> implementoList = impleMMBean.all();\n" +
                    "        for (ImpleMMBean impleMMBeanBD : implementoList) {\n" +
                    "            ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();\n" +
                    "            apontImpleMMBean.setIdApontMMFert(" + idApont + ");\n" +
                    "            apontImpleMMBean.setCodEquipImpleMM(" + implementoMMBeanBD.getCodEquipImplMM() + ");\n" +
                    "            apontImpleMMBean.setPosImpleMM(" + implementoMMBeanBD.getPosImplMM() + ");\n" +
                    "            apontImpleMMBean.setDthrImpleMM(" + dthr + ");", activity);
            ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
            apontImplMMBean.setIdApontMMFert(idApont);
            apontImplMMBean.setCodEquipImplMM(implementoMMBeanBD.getCodEquipImplMM());
            apontImplMMBean.setPosImplMM(implementoMMBeanBD.getPosImplMM());
            apontImplMMBean.setDthrImplMM(dthr);
            apontImplMMBean.setStatusImplMM(1L);
            apontImplMMBean.insert();
        }

    }

    private String dadosApontImplMM(ApontImplMMBean apontImplMMBean){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(apontImplMMBean, apontImplMMBean.getClass()).toString();
    }

    public String dadosEnvioApontImplMM(List<ApontImplMMBean> apontImpleMMList){

        JsonArray jsonArrayImplemento = new JsonArray();

        for (ApontImplMMBean apontImplMMBean : apontImpleMMList) {
            Gson gsonItemImp = new Gson();
            jsonArrayImplemento.add(gsonItemImp.toJsonTree(apontImplMMBean, apontImplMMBean.getClass()));
        }

        apontImpleMMList.clear();

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("implemento", jsonArrayImplemento);

        return jsonImplemento.toString();

    }

    public ArrayList<Long> idApontImplArrayList(String objeto) throws Exception {

        ArrayList<Long> idApontImplArrayList = new ArrayList<Long>();

        JSONObject jObjApontImpl = new JSONObject(objeto);
        JSONArray jsonArrayApontImpl = jObjApontImpl.getJSONArray("apontimpl");

        for (int i = 0; i < jsonArrayApontImpl.length(); i++) {

            JSONObject objApont = jsonArrayApontImpl.getJSONObject(i);
            Gson gsonApont = new Gson();
            ApontImplMMBean apontImplMMBean = gsonApont.fromJson(objApont.toString(), ApontImplMMBean.class);

            idApontImplArrayList.add(apontImplMMBean.getIdApontMMFert());

        }

        return idApontImplArrayList;

    }

    public void updateApontImpl(ArrayList<Long> idApontImplMMArrayList){

        List<ApontImplMMBean> apontImplList = apontImplMMList(idApontImplMMArrayList);

        for (ApontImplMMBean apontImplMMBeanBD : apontImplList) {
            apontImplMMBeanBD.setStatusImplMM(2L);
            apontImplMMBeanBD.update();
        }

        idApontImplMMArrayList.clear();

    }

    public void deleteApontImpl(ArrayList<Long> idApontImplMMArrayList){

        ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
        List<ApontImplMMBean> apontImplList = apontImplMMBean.in("idApontMMFert", idApontImplMMArrayList);

        for (ApontImplMMBean apontImplMMBeanBD : apontImplList) {
            apontImplMMBeanBD.delete();
        }

        idApontImplMMArrayList.clear();

    }

    public List<ApontImplMMBean> apontImplMMList(ArrayList<Long> idApontImplMMArrayList){
        ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
        return apontImplMMBean.in("idApontImplMM", idApontImplMMArrayList);
    }

    private EspecificaPesquisa getPesqStatusEnvioApontImpl(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusImplMM");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
