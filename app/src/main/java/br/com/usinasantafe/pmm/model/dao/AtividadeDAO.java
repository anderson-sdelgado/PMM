package br.com.usinasantafe.pmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class AtividadeDAO {

    public AtividadeDAO() {
    }

    public boolean verROSAtiv(Long nroOS){
        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        List<ROSAtivBean> rOSAtivList = rOSAtivBean.get("nroOS", nroOS);
        boolean ret = rOSAtivList.size() > 0;
        rOSAtivList.clear();
        return ret;
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(true);
        VerifDadosServ.getInstance().verDados(dado, "Atividade", telaAtual, telaProx, progressDialog);
    }

    public void recDadosAtiv(String objPrim, String objSeg, String objTerc, String objQuart) {

        try {

            JSONObject jObj = new JSONObject(objPrim);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            REquipAtivBean rEquipAtivBean = new REquipAtivBean();
            rEquipAtivBean.deleteAll();

            for (int j = 0; j < jsonArray.length(); j++) {

                JSONObject objeto = jsonArray.getJSONObject(j);
                Gson gson = new Gson();
                REquipAtivBean rEquipAtiv = gson.fromJson(objeto.toString(), REquipAtivBean.class);
                rEquipAtiv.insert();

            }

            jObj = new JSONObject(objSeg);
            jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                ROSAtivBean rosAtivBean = new ROSAtivBean();
                rosAtivBean.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    ROSAtivBean rosAtiv = gson.fromJson(objeto.toString(), ROSAtivBean.class);
                    rosAtiv.insert();

                }

            }

            jObj = new JSONObject(objTerc);
            jsonArray = jObj.getJSONArray("dados");

            AtividadeBean atividadeBean = new AtividadeBean();
            atividadeBean.deleteAll();

            for (int j = 0; j < jsonArray.length(); j++) {

                JSONObject objeto = jsonArray.getJSONObject(j);
                Gson gson = new Gson();
                AtividadeBean atividade = gson.fromJson(objeto.toString(), AtividadeBean.class);
                atividade.insert();

            }

            jObj = new JSONObject(objQuart);
            jsonArray = jObj.getJSONArray("dados");

            RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
            rFuncaoAtivParBean.deleteAll();

            for (int j = 0; j < jsonArray.length(); j++) {

                JSONObject objeto = jsonArray.getJSONObject(j);
                Gson gson = new Gson();
                RFuncaoAtivParBean rFuncaoAtivPar = gson.fromJson(objeto.toString(), RFuncaoAtivParBean.class);
                rFuncaoAtivPar.insert();

            }

            VerifDadosServ.getInstance().pulaTelaSemTerm();

        } catch (Exception e) {
            LogErroDAO.getInstance().insert(e);
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE OS! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

    }

    public ArrayList retAtivArrayList(Long equip, Long nroOS){

        ArrayList atividadeArrayList = new ArrayList();

        REquipAtivBean rEquipAtivBean = new REquipAtivBean();
        List rEquipAtivList = rEquipAtivBean.get("idEquip", equip);

        ArrayList<Long> rEquipAtivArrayList = new ArrayList<Long>();

        for (int i = 0; i < rEquipAtivList.size(); i++) {
            rEquipAtivBean = (REquipAtivBean) rEquipAtivList.get(i);
            rEquipAtivArrayList.add(rEquipAtivBean.getIdAtiv());
        }

        AtividadeBean atividadeBean = new AtividadeBean();
        List<AtividadeBean> atividadeList = atividadeBean.in("idAtiv", rEquipAtivArrayList);

        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        List<ROSAtivBean> rOSAtivList = rOSAtivBean.get("nroOS", nroOS);

        if (rOSAtivList.size() > 0) {

            for (int i = 0; i < atividadeList.size(); i++) {
                atividadeBean = (AtividadeBean) atividadeList.get(i);
                for (int j = 0; j < rOSAtivList.size(); j++) {
                    rOSAtivBean = (ROSAtivBean) rOSAtivList.get(j);
                    if (Objects.equals(atividadeBean.getIdAtiv(), rOSAtivBean.getIdAtiv())) {
                        atividadeArrayList.add(atividadeBean);
                    }
                }
            }

        } else {
            for (int i = 0; i < atividadeList.size(); i++) {
                atividadeBean = (AtividadeBean) atividadeList.get(i);
                atividadeArrayList.add(atividadeBean);
            }
        }

        return atividadeArrayList;

    }


}
