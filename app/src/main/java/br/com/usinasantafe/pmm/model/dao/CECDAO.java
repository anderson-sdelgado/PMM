package br.com.usinasantafe.pmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

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
        VerifDadosServ.getInstance().setVerTerm(false);
        VerifDadosServ.getInstance().verDados(dado, "CEC", telaAtual, telaProx, progressDialog);
    }

    public void recDadosCEC(String result){

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result.trim());
                JSONArray jsonArray = jObj.getJSONArray("cec");

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    CECBean cecBean = gson.fromJson(objeto.toString(), CECBean.class);
                    cecBean.insert();

                }

                VerifDadosServ.getInstance().pulaTelaComTerm();

            } else {
                VerifDadosServ.getInstance().envioDados();
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().envioDados();
        }

    }

}