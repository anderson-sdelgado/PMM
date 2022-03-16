package br.com.usinasantafe.pmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.OSMecanBean;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class OSMecanDAO {

    public OSMecanDAO() {
    }

    public void osMecanDelAll(){
        OSMecanBean osMecanBean = new OSMecanBean();
        osMecanBean.deleteAll();
    }

    public OSMecanBean getOSMecan(Long nroOS){
        List<OSMecanBean> osList = osMecanList(nroOS);
        OSMecanBean osBean = osList.get(0);
        osList.clear();
        return osBean;
    }

    public List<OSMecanBean> osMecanList(Long nroOS){
        OSMecanBean osMecanBean = new OSMecanBean();
        return osMecanBean.get("nroOS", nroOS);
    }

    public void verOSMecan(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        VerifDadosServ.getInstance().verifDados(dado, "OSMecan", telaAtual, telaProx, progressDialog, activity);
    }

    public boolean verOSMecanBD(Long nroOS){
        OSMecanBean osMecanBean = new OSMecanBean();
        List<OSMecanBean> osList = osMecanBean.get("nroOS", nroOS);
        boolean ret = osList.size() > 0;
        osList.clear();
        return ret;
    }

    public void recDadosOSMecan(JSONArray jsonArray) throws JSONException {

        osMecanDelAll();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            OSMecanBean osMecanBean = gson.fromJson(objeto.toString(), OSMecanBean.class);
            osMecanBean.insert();
        }

    }

}
