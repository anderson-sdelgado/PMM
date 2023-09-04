package br.com.usinasantafe.pmm.model.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.security.MessageDigest;

import br.com.usinasantafe.pmm.BuildConfig;
import br.com.usinasantafe.pmm.model.bean.AtualAplicBean;

public class AtualAplicDAO {

    public AtualAplicDAO() {
    }

    public String dadosVerAtualAplicBean(Long idEquip, Long idCheckList, String versaoAplic){
        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setVersao(versaoAplic);
        atualAplicBean.setIdEquip(idEquip);
        atualAplicBean.setIdCheckList(idCheckList);
        return getToken(atualAplicBean);
    }

    public String getAtualBDToken(){
        AtualAplicBean atualAplicBean = new AtualAplicBean();
        return getToken(atualAplicBean);
    }

    public String getAtualNroOS(Long nroOS) {
        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setNroOS(nroOS);
        return getToken(atualAplicBean);
    }

    public String getAtualCodPneu(Long codPneu) {
        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setCodPneu(codPneu);
        return getToken(atualAplicBean);
    }

    public String getAtualNroOSIdEquip(Long nroOS, Long idEquip) {
        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setNroOS(nroOS);
        atualAplicBean.setIdEquip(idEquip);
        return getToken(atualAplicBean);
    }

    public String getAtualIdEquip(Long idEquip) {
        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setIdEquip(idEquip);
        return getToken(atualAplicBean);
    }


    public String getAtualNroEquip(Long nroEquip) {
        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setNroEquip(nroEquip);
        return getToken(atualAplicBean);
    }

    private String getToken(AtualAplicBean atualAplicBean){

        String token = "";

        try {

            EquipDAO equipDAO = new EquipDAO();
            token = BuildConfig.FLAVOR.toUpperCase() + "-VERSAO_" + BuildConfig.VERSION_NAME + "-" + equipDAO.getEquip().getIdEquip();
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(token.getBytes(),0, token.length());
            token = (new BigInteger(1, m.digest()).toString(16).toUpperCase());

        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
        }

        atualAplicBean.setToken(token);
        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicBean, atualAplicBean.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        return json.toString();

    }

}
