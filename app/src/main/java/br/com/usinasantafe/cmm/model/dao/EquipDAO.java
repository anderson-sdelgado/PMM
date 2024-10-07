package br.com.usinasantafe.cmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.model.bean.AtualAplicBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.REquipPneuBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.util.VerifDadosServ;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipBean;

public class EquipDAO {

    public EquipDAO() {
    }

    public boolean verifEquip(Long nroEquip){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNro(nroEquip));
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get(pesqArrayList);
        boolean ret = equipList.size() > 0;
        equipList.clear();
        return ret;
    }

    public String dadosVerEquip(Long nroEquip, String versaoAplic){

        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setVersao(versaoAplic);
        atualAplicBean.setNroEquip(nroEquip);
        atualAplicBean.setAplic(BuildConfig.FLAVOR.toUpperCase());

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicBean, atualAplicBean.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        return json.toString();
    }

    public EquipBean getEquipId(Long idEquip){
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get("idEquip", idEquip);
        equipBean = equipList.get(0);
        equipList.clear();
        if ((equipBean.getTipoEquipFert() == 1) || (equipBean.getTipoEquipFert() == 2)) {
            equipBean.setTipoEquip(2L);
        } else {
            equipBean.setTipoEquip(1L);
        }
        return equipBean;
    }

    public EquipBean getEquipNro(Long nroEquip){
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.get("nroEquip", nroEquip);
        equipBean = equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    public void verEquip(Long nroEquip, String senha, String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity, int tipo){
        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().verifDados(dado, \"Equip\", telaAtual, telaProx, progressDialog, activity);", activity);
        VerifDadosServ.getInstance().verifDados(nroEquip, senha, dado, "Equip", telaAtual, telaProx, progressDialog, activity, tipo);
    }

    public void recDadosEquip(JSONArray jsonArray) throws JSONException {

        EquipBean equipBean = new EquipBean();
        equipBean.deleteAll();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            EquipBean equipBeanServ = gson.fromJson(objeto.toString(), EquipBean.class);
            equipBeanServ.insert();

        }

    }

    public void recDadosREquipAtiv(JSONArray jsonArray) throws JSONException {

        REquipAtivBean rEquipAtivBean = new REquipAtivBean();
        rEquipAtivBean.deleteAll();

        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject objeto = jsonArray.getJSONObject(j);
            Gson gson = new Gson();
            REquipAtivBean rEquipAtiv = gson.fromJson(objeto.toString(), REquipAtivBean.class);
            rEquipAtiv.insert();
        }

    }

    public List<REquipPneuBean> rEquipPneuList(Long idEquip){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqId(idEquip));

        REquipPneuBean rEquipPneuBean = new REquipPneuBean();
        return rEquipPneuBean.getAndOrderBy(pesqArrayList,"posPneu", true);
    }

    private EspecificaPesquisa getPesqId(Long idEquip){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idEquip");
        especificaPesquisa.setValor(idEquip);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqNro(Long nroEquip){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("nroEquip");
        especificaPesquisa.setValor(nroEquip);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
