package br.com.usinasantafe.pmm.model.dao;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class InformativoDAO {

    public InformativoDAO() {
    }

    public InfPlantioBean getInfPlantio(){
        InfPlantioBean infPlantioBean = new InfPlantioBean();
        List<InfPlantioBean> infPlantioList = infPlantioBean.all();
        infPlantioBean = infPlantioList.get(0);
        infPlantioList.clear();
        return infPlantioBean;
    }

    public InfColheitaBean getInfColheita(){
        InfColheitaBean infColheitaBean = new InfColheitaBean();
        List<InfColheitaBean> infoColheitaList = infColheitaBean.all();
        infColheitaBean = infoColheitaList.get(0);
        infoColheitaList.clear();
        return infColheitaBean;
    }

    public void verifDadosInformativo(String dado, Context telaAtual, Class telaProx, String activity){
        VerifDadosServ.getInstance().verifDados(dado, "Informativo", telaAtual, telaProx, activity);
    }

    public void recDadosInfColheita(JSONArray jsonArray) throws JSONException {

        InfColheitaBean infColheitaBean = new InfColheitaBean();
        infColheitaBean.deleteAll();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            infColheitaBean = gson.fromJson(objeto.toString(), InfColheitaBean.class);
            infColheitaBean.insert();

        }

    }

    public void recDadosInfPlantio(JSONArray jsonArray) throws JSONException {

        InfPlantioBean infPlantioBean = new InfPlantioBean();
        infPlantioBean.deleteAll();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            infPlantioBean = gson.fromJson(objeto.toString(), InfPlantioBean.class);
            infPlantioBean.insert();

        }

    }

}
