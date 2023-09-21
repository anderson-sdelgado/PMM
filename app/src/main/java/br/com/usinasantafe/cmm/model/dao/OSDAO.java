package br.com.usinasantafe.cmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.util.VerifDadosServ;

public class OSDAO {

    public OSDAO() {
    }

    public void osDelAll(){
        OSBean osBean = new OSBean();
        osBean.deleteAll();
    }

    public boolean verOS(Long nroOS){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroOS(nroOS));
        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get(pesqArrayList);
        boolean ret = osList.size() > 0;
        osList.clear();
        return ret;
    }

    public boolean verLib(Long nroOS, Long idLib){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroOS(nroOS));
        pesqArrayList.add(getPesqIdLib(idLib));
        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get(pesqArrayList);
        boolean ret = osList.size() > 0;
        osList.clear();
        return ret;
    }

    public void rOSAtivDelAll(){
        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        rOSAtivBean.deleteAll();
    }

    public OSBean getOS(Long nroOS){
        OSBean osBean = new OSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroOS(nroOS));
        List<OSBean> osList = osBean.get(pesqArrayList);
        osBean = osList.get(0);
        osList.clear();
        return osBean;
    }

    public Double rendOS(Long nroOS){
        OSBean osBean = new OSBean();
        List<OSBean> osList = osList(nroOS);
        if (osList.size() > 0) {
            osBean = osList.get(0);
        } else {
            osBean.setAreaProgrOS(150D);
        }
        return osBean.getAreaProgrOS();
    }

    private List<OSBean> osList(Long nroOS){
        OSBean osBean = new OSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroOS(nroOS));
        return osBean.get(pesqArrayList);
    }

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        VerifDadosServ.getInstance().verifDados(dado, "OS", telaAtual, telaProx, progressDialog, activity);
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

    public boolean verOSMecan(Long nroOS, Long idEquip){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroOS(nroOS));
        pesqArrayList.add(getPesqIdEquip(idEquip));
        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get(pesqArrayList);
        boolean ret = osList.size() > 0;
        osList.clear();
        return ret;
    }

    public void verOSMecan(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verifDados(dado, "OSMecan", telaAtual, telaProx, progressDialog, null);
    }

    public void recDadosOSMecan(JSONArray jsonArray, Long idEquip) throws JSONException {

        deleteOSMecan(idEquip);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            OSBean osBean = gson.fromJson(objeto.toString(), OSBean.class);
            osBean.insert();
        }

    }

    private void deleteOSMecan(Long idEquip){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdEquip(idEquip));

        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get(pesqArrayList);
        pesqArrayList.clear();

        for(OSBean osBeanBD : osList){
            osBeanBD.delete();
        }

    }

    private EspecificaPesquisa getPesqIdEquip(Long idEquip){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idEquip");
        especificaPesquisa.setValor(idEquip);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqNroOS(Long nroOS){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("nroOS");
        especificaPesquisa.setValor(nroOS);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqIdLib(Long idLib){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idLibOS");
        especificaPesquisa.setValor(idLib);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
