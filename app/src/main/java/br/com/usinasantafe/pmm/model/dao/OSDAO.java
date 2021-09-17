package br.com.usinasantafe.pmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class OSDAO {

    public OSDAO() {
    }

    public void osDelAll(){
        OSBean osBean = new OSBean();
        osBean.deleteAll();
    }

    public void rOSAtivDelAll(){
        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        rOSAtivBean.deleteAll();
    }

    public OSBean getOS(Long nroOS){
        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get("nroOS", nroOS);
        osBean = osList.get(0);
        osList.clear();
        return osBean;
    }

    public OSBean getOS(Long idAtiv, Long nroOS){
        List<OSBean> osList = osList(idAtiv, nroOS);
        OSBean osBean = osList.get(0);
        osList.clear();
        return osBean;
    }

    public ArrayList<Long> idAtivArrayList(Long nroOS){
        ArrayList<Long> idAtivArrayList = new ArrayList<Long>();
        List<OSBean> osList = osList(nroOS);
        for (OSBean osBean : osList) {
            idAtivArrayList.add(osBean.getIdAtiv());
        }
        osList.clear();
        return idAtivArrayList;
    }

    private List<OSBean> osList(Long nroOS){
        OSBean osBean = new OSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroOS(nroOS));
        return osBean.get(pesqArrayList);
    }

    private List<OSBean> osList(Long idAtiv, Long nroOS){
        OSBean osBean = new OSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroOS(nroOS));
        pesqArrayList.add(getPesqIdAtiv(idAtiv));
        return osBean.get(pesqArrayList);
    }

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verifDados(dado, "OS", telaAtual, telaProx, progressDialog);
    }

    public void recDadosOS(JSONArray jsonArray) throws JSONException {

        osDelAll();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            OSBean osBean = gson.fromJson(objeto.toString(), OSBean.class);
            osBean.insert();
        }

    }

    public void recDadosROSAtiv(JSONArray jsonArray) throws JSONException {

        rOSAtivDelAll();
        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject objeto = jsonArray.getJSONObject(j);
            Gson gson = new Gson();
            ROSAtivBean rosAtivBean = gson.fromJson(objeto.toString(), ROSAtivBean.class);
            rosAtivBean.insert();
        }

    }

    public boolean verOS(Long nroOS){
        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get("nroOS", nroOS);
        boolean ret = osList.size() > 0;
        osList.clear();
        return ret;
    }

    private EspecificaPesquisa getPesqNroOS(Long nroOS){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("nroOS");
        especificaPesquisa.setValor(nroOS);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqIdAtiv(Long idAtiv){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idAtiv");
        especificaPesquisa.setValor(idAtiv);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
