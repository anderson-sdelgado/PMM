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
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class AtividadeDAO {

    public AtividadeDAO() {
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(true);
        VerifDadosServ.getInstance().verDados(dado, "Atividade", telaAtual, telaProx, progressDialog);
    }

    public void recDadosAtiv(String result) {

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("_") + 1;
                int pos2 = result.indexOf("|") + 1;
                String objPrim = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1, (pos2 - 1));
                String objTerc = result.substring(pos2);

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

                VerifDadosServ.getInstance().pulaTelaSemTerm();

            } else {
                VerifDadosServ.getInstance().msgSemTerm("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
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
        List atividadeList = atividadeBean.in("idAtiv", rEquipAtivArrayList);

        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        List rOSAtivList = rOSAtivBean.get("nroOS", nroOS);

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

    public List getListFuncaoAtividade(Long idAtiv){

        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("idAtivPar");
        pesquisa1.setValor(idAtiv);
        pesquisa1.setTipo(1);
        pesqList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("tipoFuncao");
        pesquisa2.setValor(1L);
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        return rFuncaoAtivParBean.get(pesqList);
    }

    public List getListFuncaoParada(Long idParada){

        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("idAtivPar");
        pesquisa1.setValor(idParada);
        pesquisa1.setTipo(1);
        pesqList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("tipoFuncao");
        pesquisa2.setValor(2L);
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        return rFuncaoAtivParBean.get(pesqList);
    }

    public Long idParadaCheckList(){

        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("codFuncao");
        pesq1.setValor(1L);
        pesq1.setTipo(1);
        pesqList.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("tipoFuncao");
        pesq2.setValor(2L);
        pesq2.setTipo(1);
        pesqList.add(pesq2);

        List rFuncaoAtivParList =   rFuncaoAtivParBean.get(pesqList);
        rFuncaoAtivParBean = (RFuncaoAtivParBean) rFuncaoAtivParList.get(0);
        rFuncaoAtivParList.clear();

        return rFuncaoAtivParBean.getIdAtivPar();

    }

    public Long idParadaImplemento(){

        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("codFuncao");
        pesq1.setValor(2L);
        pesq1.setTipo(1);
        pesqList.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("tipoFuncao");
        pesq2.setValor(2L);
        pesq2.setTipo(1);
        pesqList.add(pesq2);

        List rFuncaoAtivParList =   rFuncaoAtivParBean.get(pesqList);
        rFuncaoAtivParBean = (RFuncaoAtivParBean) rFuncaoAtivParList.get(0);
        rFuncaoAtivParList.clear();

        return rFuncaoAtivParBean.getIdAtivPar();

    }

}
