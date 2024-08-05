package br.com.usinasantafe.cmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.LocalCarregBean;
import br.com.usinasantafe.cmm.util.VerifDadosServ;

public class LocalCarregCanaDAO {

    public void verifLocalCarreg(String dados, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        VerifDadosServ.getInstance().verifDados(dados, "LocalCarreg", telaAtual, telaProx, progressDialog, activity);
    }

    public LocalCarregBean getLocalCarreg() {
        LocalCarregBean localCarregBean = new LocalCarregBean();
        List<LocalCarregBean> localCarregList = localCarregBean.all();
        localCarregBean = localCarregList.get(0);
        localCarregList.clear();
        return localCarregBean;
    }

    public void recLocalCarreg(JSONArray jsonArray) throws JSONException {

        LocalCarregBean localCarregBean = new LocalCarregBean();
        localCarregBean.deleteAll();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            localCarregBean = gson.fromJson(objeto.toString(), LocalCarregBean.class);
            localCarregBean.insert();
        }

    }

}
