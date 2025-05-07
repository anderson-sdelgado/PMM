package br.com.usinasantafe.cmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.ApontImplMMBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ImplementoMMBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;

public class ImplementoMMDAO {

    public ImplementoMMDAO() {
    }

    public void implementoMMDelAll(){
        ImplementoMMBean implementoMMBean = new ImplementoMMBean();
        implementoMMBean.deleteAll();
    }

    public List<ApontImplMMBean> apontImplEnvioListRetrofit(Long idApont){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusEnvioApontImpl());
        pesqArrayList.add(getPesqIdApont(idApont));
        ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
        return apontImplMMBean.getAndOrderBy(pesqArrayList, "idApontImplMM", true);
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

    private boolean checkAddCodImpl(Long idApont, Long codEquip, Long pos){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdApont(idApont));
        pesqArrayList.add(getPesqCodEquip(codEquip));
        pesqArrayList.add(getPesqPos(pos));
        ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
        List apontList = apontImplMMBean.get(pesqArrayList);
        boolean ret = apontList.size() == 0;
        apontList.clear();
        return ret;
    }

    public void salvarApontImpl(Long idApont, String dthr, String activity){
        ImplementoMMBean implementoMMBean = new ImplementoMMBean();
        List<ImplementoMMBean> implementoList = implementoMMBean.all();
        LogProcessoDAO.getInstance().insertLogProcesso("for (ImplementoMMBean implementoMMBeanBD : implementoList) {", activity);
        for (ImplementoMMBean implementoMMBeanBD : implementoList) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(checkAddCodImpl(idApont, implementoMMBeanBD.getCodEquipImplMM(), implementoMMBeanBD.getPosImplMM())){", activity);
            if(checkAddCodImpl(idApont, implementoMMBeanBD.getCodEquipImplMM(), implementoMMBeanBD.getPosImplMM())){
                LogProcessoDAO.getInstance().insertLogProcesso("ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();\n" +
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

    }

    private String dadosApontImplMM(ApontImplMMBean apontImplMMBean){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(apontImplMMBean, apontImplMMBean.getClass()).toString();
    }

    public void updateApontImpl(Long idApont){

        List<ApontImplMMBean> apontImplList = apontImplMMList(idApont);
        ApontImplMMBean apontImplMMBean = apontImplList.get(0);
        apontImplList.clear();
        apontImplMMBean.setStatusImplMM(2L);
        apontImplMMBean.update();

    }


    public void deleteApontImpl(ArrayList<Long> idApontImplMMArrayList){

        ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
        List<ApontImplMMBean> apontImplList = apontImplMMBean.in("idApontMMFert", idApontImplMMArrayList);

        for (ApontImplMMBean apontImplMMBeanBD : apontImplList) {
            apontImplMMBeanBD.delete();
        }

        idApontImplMMArrayList.clear();

    }

    public List<ApontImplMMBean> apontImplMMList(Long idApontImplMM){
        ApontImplMMBean apontImplMMBean = new ApontImplMMBean();
        return apontImplMMBean.get("idApontImplMM", idApontImplMM);
    }


    private EspecificaPesquisa getPesqStatusEnvioApontImpl(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusImplMM");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdApont(Long idApont){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idApontMMFert");
        pesquisa.setValor(idApont);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCodEquip(Long codEquip){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("codEquipImplMM");
        pesquisa.setValor(codEquip);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqPos(Long pos){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("posImplMM");
        pesquisa.setValor(pos);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
