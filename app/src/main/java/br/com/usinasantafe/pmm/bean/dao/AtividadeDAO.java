package br.com.usinasantafe.pmm.bean.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.bean.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.bean.estaticas.REquipAtivTO;
import br.com.usinasantafe.pmm.bean.estaticas.RFuncaoAtivParTO;
import br.com.usinasantafe.pmm.bean.estaticas.ROSAtivTO;
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

                REquipAtivTO rEquipAtivTO = new REquipAtivTO();
                rEquipAtivTO.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    REquipAtivTO rEquipAtiv = gson.fromJson(objeto.toString(), REquipAtivTO.class);
                    rEquipAtiv.insert();

                }

                jObj = new JSONObject(objSeg);
                jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    ROSAtivTO rosAtivTO = new ROSAtivTO();
                    rosAtivTO.deleteAll();

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        ROSAtivTO rosAtiv = gson.fromJson(objeto.toString(), ROSAtivTO.class);
                        rosAtiv.insert();

                    }

                }

                jObj = new JSONObject(objTerc);
                jsonArray = jObj.getJSONArray("dados");

                AtividadeTO atividadeTO = new AtividadeTO();
                atividadeTO.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    AtividadeTO atividade = gson.fromJson(objeto.toString(), AtividadeTO.class);
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

        REquipAtivTO rEquipAtivTO = new REquipAtivTO();
        List rEquipAtivList = rEquipAtivTO.get("idEquip", equip);

        ArrayList<Long> rEquipAtivArrayList = new ArrayList<Long>();

        for (int i = 0; i < rEquipAtivList.size(); i++) {
            rEquipAtivTO = (REquipAtivTO) rEquipAtivList.get(i);
            rEquipAtivArrayList.add(rEquipAtivTO.getIdAtiv());
        }

        AtividadeTO atividadeTO = new AtividadeTO();
        List atividadeList = atividadeTO.in("idAtiv", rEquipAtivArrayList);

        ROSAtivTO rOSAtivTO = new ROSAtivTO();
        List rOSAtivList = rOSAtivTO.get("nroOS", nroOS);

        if (rOSAtivList.size() > 0) {

            for (int i = 0; i < atividadeList.size(); i++) {
                atividadeTO = (AtividadeTO) atividadeList.get(i);
                for (int j = 0; j < rOSAtivList.size(); j++) {
                    rOSAtivTO = (ROSAtivTO) rOSAtivList.get(j);
                    if (Objects.equals(atividadeTO.getIdAtiv(), rOSAtivTO.getIdAtiv())) {
                        atividadeArrayList.add(atividadeTO);
                    }
                }
            }

        } else {
            for (int i = 0; i < atividadeList.size(); i++) {
                atividadeTO = (AtividadeTO) atividadeList.get(i);
                atividadeArrayList.add(atividadeTO);
            }
        }

        return atividadeArrayList;

    }

    public List getListFuncaoAtividade(Long idAtiv){

        RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
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

        return rFuncaoAtivParTO.get(pesqList);
    }

    public List getListFuncaoParada(Long idParada){

        RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
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

        return rFuncaoAtivParTO.get(pesqList);
    }

    public Long idParadaCheckList(){

        RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
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

        List rFuncaoAtivParList =   rFuncaoAtivParTO.get(pesqList);
        rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(0);
        rFuncaoAtivParList.clear();

        return rFuncaoAtivParTO.getIdAtivPar();

    }

    public Long idParadaImplemento(){

        RFuncaoAtivParTO rFuncaoAtivParTO = new RFuncaoAtivParTO();
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

        List rFuncaoAtivParList =   rFuncaoAtivParTO.get(pesqList);
        rFuncaoAtivParTO = (RFuncaoAtivParTO) rFuncaoAtivParList.get(0);
        rFuncaoAtivParList.clear();

        return rFuncaoAtivParTO.getIdAtivPar();

    }

}
