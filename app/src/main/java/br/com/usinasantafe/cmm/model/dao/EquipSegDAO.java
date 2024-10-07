package br.com.usinasantafe.cmm.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ImplementoMMBean;

public class EquipSegDAO {

    public EquipSegDAO() {
    }

    public boolean verImple(Long nroEquip){
        return verEquipSeg(nroEquip, 3L);
    }

    public void setImplemento(Long pos, Long impl){
        ImplementoMMBean implementoMMBean = new ImplementoMMBean();
        List<ImplementoMMBean> implList = implementoMMBean.get("posImplMM", pos);
        if(implList.size() > 0) {
            implementoMMBean = implList.get(0);
            implementoMMBean.setCodEquipImplMM(impl);
            implementoMMBean.update();
        }
        else{
            implementoMMBean.setCodEquipImplMM(impl);
            implementoMMBean.setPosImplMM(pos);
            implementoMMBean.insert();
        }
        implList.clear();
    }

    public boolean verDuplicImple(Long nroEquip){
        ImplementoMMBean implementoMMBean = new ImplementoMMBean();
        List<ImplementoMMBean> implMMList = implementoMMBean.get("codEquipImplMM", nroEquip);
        return (implMMList.size() == 0);
    }

    public boolean verMotoBomba(Long nroEquip){
        return verEquipSeg(nroEquip, 4L);
    }

    public boolean verTransb(Long nroEquip){
        return verEquipSeg(nroEquip, 2L);
    }

    public boolean verCarretel(Long nroEquip){
        return verEquipSeg(nroEquip, 1L);
    }


    public void recDadosEquipSeg(JSONArray jsonArray) throws JSONException {

        EquipSegBean equipSegBean = new EquipSegBean();
        equipSegBean.deleteAll();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            EquipSegBean equipSegBeanServ = gson.fromJson(objeto.toString(), EquipSegBean.class);
            equipSegBeanServ.insert();

        }

    }


    private boolean verEquipSeg(Long nroEquip, Long tipo){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNro(nroEquip));
        pesqArrayList.add(getPesqTipo(tipo));
        EquipSegBean equipSegBean = new EquipSegBean();
        List<EquipSegBean> equipSegList = equipSegBean.get(pesqArrayList);
        boolean ret = equipSegList.size() > 0;
        equipSegList.clear();
        return ret;
    }

    public EquipSegBean getEquipSeg(Long nroEquip){
        EquipSegBean equipSegBean = new EquipSegBean();
        List equipSegList = equipSegBean.get("nroEquip", nroEquip);
        equipSegBean = (EquipSegBean) equipSegList.get(0);
        equipSegList.clear();
        return equipSegBean;
    }

    private EspecificaPesquisa getPesqNro(Long nroEquip){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("nroEquip");
        especificaPesquisa.setValor(nroEquip);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqTipo(Long tipo){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoEquip");
        especificaPesquisa.setValor(tipo);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
