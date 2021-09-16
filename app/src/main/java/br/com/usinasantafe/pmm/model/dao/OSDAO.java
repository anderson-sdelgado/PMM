package br.com.usinasantafe.pmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class OSDAO {

    public OSDAO() {
    }

    public OSBean getOSBean(Long nroOS){
        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get("nroOS", nroOS);
        osBean = osList.get(0);
        osList.clear();
        return osBean;
    }

    public void osDelAll(){
        OSBean osBean = new OSBean();
        osBean.deleteAll();
    }

    public void rOSAtivDelAll(){
        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        rOSAtivBean.deleteAll();
    }

    public boolean verAtivOS(Long idAtivOS, Long nroOS){
        List<OSBean> osList = ativOSList(idAtivOS, nroOS);
        boolean retorno = osList.size() > 0;
        osList.clear();
        return retorno;
    }

    public OSBean getOSTipoAtiv(Long idAtiv, Long nroOS){
        List<OSBean> osList = osList(nroOS);
        OSBean retOSBean = new OSBean();
        for(OSBean osBean : osList){
            if(idAtiv.equals(osBean.getIdAtiv())){
                retOSBean = osBean;
            }
        }
        osList.clear();
        return retOSBean;
    }

    private List<OSBean> ativOSList(Long idAtivOS, Long nroOS){
        OSBean osBean = new OSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqAtiv(idAtivOS));
        pesqArrayList.add(getPesqNroOS(nroOS));
        return osBean.get(pesqArrayList);
    }

    private List<OSBean> osList(Long nroOS){
        OSBean osBean = new OSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroOS(nroOS));
        return osBean.get(pesqArrayList);
    }

    private EspecificaPesquisa getPesqAtiv(Long idAtivOS){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idAtivOS");
        especificaPesquisa.setValor(idAtivOS);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqLib(Long idLibOS){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idLibOS");
        especificaPesquisa.setValor(idLibOS);
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

}
