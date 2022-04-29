package br.com.usinasantafe.pmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class AtividadeDAO {

    public AtividadeDAO() {
    }

    public AtividadeBean getAtividade(Long idAtiv){
        AtividadeBean atividadeBean = new AtividadeBean();
        List<AtividadeBean> atividadeList = atividadeBean.get("idAtiv", idAtiv);
        atividadeBean = atividadeList.get(0);
        atividadeList.clear();
        return atividadeBean;
    }

    public boolean verROSAtiv(Long idOS){
        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        List<ROSAtivBean> rOSAtivList = rOSAtivBean.get("idOS", idOS);
        boolean ret = rOSAtivList.size() > 0;
        rOSAtivList.clear();
        return ret;
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verifDados(dado, "Atividade", telaAtual, telaProx, progressDialog, null);
    }

    public void verAtivECM(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verifDados(dado, "AtividadeECM", telaAtual, telaProx, progressDialog, null);
    }

    public void recDadosAtiv(JSONArray jsonArray) throws JSONException {

        AtividadeBean atividadeBean = new AtividadeBean();
        atividadeBean.deleteAll();

        for (int j = 0; j < jsonArray.length(); j++) {

            JSONObject objeto = jsonArray.getJSONObject(j);
            Gson gson = new Gson();
            AtividadeBean atividade = gson.fromJson(objeto.toString(), AtividadeBean.class);
            atividade.insert();

        }

    }

    public ArrayList retAtivArrayList(Long equip, Long idOS){

        ArrayList atividadeArrayList = new ArrayList();

        REquipAtivBean rEquipAtivBean = new REquipAtivBean();
        List<REquipAtivBean> rEquipAtivList = rEquipAtivBean.get("idEquip", equip);

        ArrayList<Long> rEquipAtivArrayList = new ArrayList<Long>();

        for (REquipAtivBean rEquipAtivBeanBD : rEquipAtivList) {
            rEquipAtivArrayList.add(rEquipAtivBeanBD.getIdAtiv());
        }

        AtividadeBean atividadeBean = new AtividadeBean();
        List<AtividadeBean> atividadeEquipList = atividadeBean.in("idAtiv", rEquipAtivArrayList);

        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        List<ROSAtivBean> rOSAtivList = rOSAtivBean.get("idOS", idOS);

        if (rOSAtivList.size() > 0) {

            for (AtividadeBean atividadeBeanBD : atividadeEquipList) {
                for (ROSAtivBean rOSAtivBeanBD : rOSAtivList) {
                    if (Objects.equals(atividadeBeanBD.getIdAtiv(), rOSAtivBeanBD.getIdAtiv())) {
                        atividadeArrayList.add(atividadeBeanBD);
                    }
                }
            }

        } else {
            for (AtividadeBean atividadeBeanBD : atividadeEquipList) {
                atividadeArrayList.add(atividadeBeanBD);
            }
        }

        return atividadeArrayList;

    }


}
