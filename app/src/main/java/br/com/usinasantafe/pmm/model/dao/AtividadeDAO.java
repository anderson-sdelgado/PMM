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
        VerifDadosServ.getInstance().verifDados(dado, "Atividade", telaAtual, telaProx, progressDialog);
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
        List<AtividadeBean> atividadeEquipList = atividadeBean.in("idAtiv", rEquipAtivArrayList);

        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        List<ROSAtivBean> rOSAtivList = rOSAtivBean.get("nroOS", nroOS);

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

    public ArrayList retAtivArrayList(Long equip, ArrayList<Long> idAtivOSArrayList){

        ArrayList atividadeArrayList = new ArrayList();

        REquipAtivBean rEquipAtivBean = new REquipAtivBean();
        List rEquipAtivList = rEquipAtivBean.get("idEquip", equip);

        ArrayList<Long> rEquipAtivArrayList = new ArrayList<Long>();

        for (int i = 0; i < rEquipAtivList.size(); i++) {
            rEquipAtivBean = (REquipAtivBean) rEquipAtivList.get(i);
            rEquipAtivArrayList.add(rEquipAtivBean.getIdAtiv());
        }

        AtividadeBean atividadeEquipBean = new AtividadeBean();
        List<AtividadeBean> atividadeEquipList = atividadeEquipBean.in("idAtiv", rEquipAtivArrayList);

        AtividadeBean atividadeOSBean = new AtividadeBean();
        List<AtividadeBean> atividadeOSList = atividadeOSBean.in("idAtiv", idAtivOSArrayList);

        for (AtividadeBean atividadeEquipBD : atividadeEquipList) {
            for (AtividadeBean atividadeOSBD : atividadeOSList) {
                if (Objects.equals(atividadeEquipBD.getIdAtiv(), atividadeOSBD.getIdAtiv())) {
                    atividadeArrayList.add(atividadeEquipBD);
                }
            }
        }

        return atividadeArrayList;

    }


}
