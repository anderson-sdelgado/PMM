package br.com.usinasantafe.pmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.PneuBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class PneuDAO {

    public PneuDAO() {
    }

    public boolean verPneuNro(String nroPneu){
        List<PneuBean> pneuList = pneuNroList(nroPneu);
        boolean ret = (pneuList.size() > 0);
        pneuList.clear();
        return ret;
    }

    public void verPneu(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        VerifDadosServ.getInstance().verifDados(dado, "Pneu", telaAtual, telaProx, progressDialog, activity);
    }

    public void recDadosPneu(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            PneuBean pneuBean = gson.fromJson(objeto.toString(), PneuBean.class);
            pneuBean.insert();
        }

    }

    public List<PneuBean> pneuNroList(String nroPneu){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroPneu(nroPneu));

        PneuBean pneuBean = new PneuBean();
        return pneuBean.get(pesqArrayList);

    }

    private EspecificaPesquisa getPesqNroPneu(String nroPneu){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("nroPneu");
        pesquisa.setValor(nroPneu);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
