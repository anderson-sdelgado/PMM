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

    public EquipBean getEquip(){
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.all();
        equipBean = equipList.get(0);
        equipList.clear();
        if ((equipBean.getTipoEquipFert() == 1) || (equipBean.getTipoEquipFert() == 2)) {
            equipBean.setTipoEquip(2L);
        } else {
            equipBean.setTipoEquip(1L);
        }
        return equipBean;
    }

    public void verEquip(String senha, String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity, int tipo){
        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().verifDados(dado, \"Equip\", telaAtual, telaProx, progressDialog, activity);", activity);
        VerifDadosServ.getInstance().verifDados(senha, dado, "Equip", telaAtual, telaProx, progressDialog, activity, tipo);
    }

    public EquipBean recDadosEquip(JSONArray jsonArray) throws JSONException {

        EquipBean equipBean = new EquipBean();
        equipBean.deleteAll();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            equipBean = gson.fromJson(objeto.toString(), EquipBean.class);
            equipBean.insert();

        }

        return equipBean;

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

    public void recDadosREquipPneu(JSONArray jsonArray) throws JSONException {

        REquipPneuBean rEquipPneuBean = new REquipPneuBean();
        rEquipPneuBean.deleteAll();

        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject objeto = jsonArray.getJSONObject(j);
            Gson gson = new Gson();
            REquipPneuBean rEquipPneu = gson.fromJson(objeto.toString(), REquipPneuBean.class);
            rEquipPneu.insert();
        }

    }


    public String dadosEnvioEquip(){

        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.all();
        JsonArray equipJsonArray = new JsonArray();

        equipBean = equipList.get(0);
        Gson gson = new Gson();
        equipJsonArray.add(gson.toJsonTree(equipBean, equipBean.getClass()));
        equipList.clear();

        JsonObject equipJsonObj = new JsonObject();
        equipJsonObj.add("equip", equipJsonArray);

        return equipJsonObj.toString();

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
