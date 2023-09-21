package br.com.usinasantafe.cmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.cmm.util.VerifDadosServ;

public class CECDAO {

    public CECDAO() {
    }

    public boolean verCEC(){
        List<CECBean> cecList = cecListDesc();
        boolean retorno = cecList.size() > 0;
        cecList.clear();
        return retorno;
    }

    public List<CECBean> cecListDesc(){
        CECBean cecBean = new CECBean();
        List<CECBean> cecList = cecBean.orderBy("idCEC", false);
        return cecList;
    }

    public List<CECBean> cecListCresc(){
        CECBean cecBean = new CECBean();
        List<CECBean> cecList = cecBean.orderBy("idCEC", true);
        return cecList;
    }

    public CECBean getCEC(){
        List<CECBean> cecList = cecListDesc();
        CECBean cecBean = cecList.get(0);
        cecList.clear();
        return cecBean;
    }

    public void delCEC(){
        List<CECBean> cecList = cecListCresc();
        int qtdeCEC = cecList.size();
        if (qtdeCEC > 10) {
            CECBean cecBean = (CECBean) cecList.get(0);
            cecBean.delete();
        }
        cecList.clear();
    }

    public void verCEC(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verifDados(dado, "CEC", telaAtual, telaProx, progressDialog, null);
    }

    public void recDadosCEC(String cec) throws JSONException {

        JSONObject jsonObj = new JSONObject(cec);
        JSONArray jsonArray = jsonObj.getJSONArray("cec");

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            CECBean cecBean = gson.fromJson(objeto.toString(), CECBean.class);
            cecBean.insert();

        }

    }

}