package br.com.usinasantafe.cmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.security.MessageDigest;

import br.com.usinasantafe.cmm.BuildConfig;
import br.com.usinasantafe.cmm.control.ConfigCTR;
import br.com.usinasantafe.cmm.model.bean.AtualAplicBean;
import com.google.common.base.Strings;

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

    public String getAtualCodPneu(String codPneu) {
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
            ConfigCTR configCTR = new ConfigCTR();
            token = BuildConfig.FLAVOR.toUpperCase() + "-VERSAO_" + BuildConfig.VERSION_NAME + "-" + equipDAO.getEquipId(configCTR.getConfig().getIdEquipConfig()).getIdEquip();
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(token.getBytes(),0, token.length());
            BigInteger bigInteger = new BigInteger(1, m.digest());
            String str = bigInteger.toString(16).toUpperCase();
            token = Strings.padStart(str, 32, '0');

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

    public String token() {

        String token = "";

        try {

            EquipDAO equipDAO = new EquipDAO();
            ConfigCTR configCTR = new ConfigCTR();
            token = BuildConfig.FLAVOR.toUpperCase() + "-VERSAO_" + BuildConfig.VERSION_NAME + "-" + equipDAO.getEquipId(configCTR.getConfig().getIdEquipConfig()).getIdEquip();
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(token.getBytes(),0, token.length());
            BigInteger bigInteger = new BigInteger(1, m.digest());
            String str = bigInteger.toString(16).toUpperCase();
            token = Strings.padStart(str, 32, '0');


        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
        }

        return token;
    }

}
