package br.com.usinasantafe.pmm.bean.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bean.variaveis.ApontImpleMMTO;
import br.com.usinasantafe.pmm.control.BoletimInterface;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.control.ApontCTR;
import br.com.usinasantafe.pmm.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.bean.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.MovLeiraTO;
import br.com.usinasantafe.pmm.bean.variaveis.RendMMTO;

public class BoletimMMDAO implements BoletimInterface {

    public BoletimMMDAO() {
    }

    public boolean verBolAberto(){
        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = boletimMMTO.get("statusBolMM", 1L);
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public BoletimMMTO getBolMMAberto(){
        BoletimMMTO boletimMMTO = new BoletimMMTO();
        List boletimMMList = boletimMMTO.get("statusBolMM", 1L);
        boletimMMTO = (BoletimMMTO) boletimMMList.get(0);
        boletimMMList.clear();
        return boletimMMTO;
    }

    public Long getIdBolAberto(){
        BoletimMMTO boletimMMTO = getBolMMAberto();
        return boletimMMTO.getIdBolMM();
    }

    public void atualQtdeApontBol(){
        BoletimMMTO boletimMMTO = getBolMMAberto();
        boletimMMTO.setQtdeApontBolMM(boletimMMTO.getQtdeApontBolMM() + 1L);
        boletimMMTO.update();
    }

    public void insRend(Long nroOS){

        BoletimMMTO boletimMMTO = getBolMMAberto();

        RendMMTO rendMMTO = new RendMMTO();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("idBolRendMM");
        pesq1.setValor(boletimMMTO.getIdBolMM());
        pesq1.setTipo(1);
        listaPesq.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("nroOSRendMM");
        pesq2.setValor(nroOS);
        pesq2.setTipo(1);
        listaPesq.add(pesq2);

        List rendList = rendMMTO.get(listaPesq);

        if (rendList.size() == 0) {
            rendMMTO.setIdBolRendMM(boletimMMTO.getIdBolMM());
            rendMMTO.setIdExtBolRendMM(boletimMMTO.getIdExtBolMM());
            rendMMTO.setNroOSRendMM(nroOS);
            rendMMTO.setValorRendMM(0D);
            rendMMTO.insert();
            rendMMTO.commit();
        }

    }

    public void insMovLeira(Long idLeira, Long tipo){
        BoletimMMTO boletimMMTO = getBolMMAberto();
        MovLeiraTO movLeiraTO = new MovLeiraTO();
        movLeiraTO.setTipoMovLeira(tipo);
        movLeiraTO.setIdLeira(idLeira);
        movLeiraTO.setDataHoraMovLeira(Tempo.getInstance().dataComHora().getDataHora());
        movLeiraTO.setIdBolMovLeira(boletimMMTO.getIdBolMM());
        movLeiraTO.setIdExtBolMovLeira(boletimMMTO.getIdExtBolMM());
        movLeiraTO.setStatusMovLeira(1L);
        movLeiraTO.insert();
    }

    public boolean verRend(){
        RendMMTO rendMMTO = new RendMMTO();
        List rendList = rendMMTO.get("idBolRendMM", getBolMMAberto().getIdBolMM());
        Boolean ret = (rendList.size() > 0);
        rendList.clear();
        return ret;
    }

    public int qtdeRend(){
        RendMMTO rendMMTO = new RendMMTO();
        List rendList = rendMMTO.get("idBolRendMM", getBolMMAberto().getIdBolMM());
        return rendList.size();
    }

    public RendMMTO getRend(int pos){
        RendMMTO rendMMTO = new RendMMTO();
        List rendList = rendMMTO.getAndOrderBy("idBolRendMM", getBolMMAberto().getIdBolMM(), "idRendMM", true);
        rendMMTO = (RendMMTO) rendList.get(pos);
        rendList.clear();
        return rendMMTO;
    }

    public void atualRend(RendMMTO rendMMTO){
        rendMMTO.setDthrRendMM(Tempo.getInstance().dataComHora().getDataHora());
        rendMMTO.update();
        rendMMTO.commit();
    }

    public void salvarBolAberto(BoletimMMTO boletimMMTO){
        boletimMMTO.setStatusBolMM(1L);
        boletimMMTO.setDthrInicialBolMM(Tempo.getInstance().dataComHora().getDataHora());
        boletimMMTO.setStatusDtHrInicialBolMM(Tempo.getInstance().dataComHora().getStatus());
        boletimMMTO.setQtdeApontBolMM(0L);
        boletimMMTO.insert();
    }

    public void salvarBolFechado(BoletimMMTO boletimMMTO) {

        BoletimMMTO boletimMMTOBD = new BoletimMMTO();
        List listBoletim = boletimMMTOBD.get("statusBolMM", 1L);
        boletimMMTOBD = (BoletimMMTO) listBoletim.get(0);
        listBoletim.clear();

        boletimMMTOBD.setDthrFinalBolMM(Tempo.getInstance().dataComHora().getDataHora());
        boletimMMTOBD.setStatusDtHrFinalBolMM(Tempo.getInstance().dataComHora().getStatus());
        boletimMMTOBD.setStatusBolMM(2L);
        boletimMMTOBD.setHodometroFinalBolMM(boletimMMTO.getHodometroFinalBolMM());
        boletimMMTOBD.update();

    }

    public List bolFechadoList() {
        BoletimMMTO boletimMMTO = new BoletimMMTO();
        return boletimMMTO.get("statusBolMM", 2L);
    }

    public List bolAbertoSemEnvioList() {

        BoletimMMTO boletimMMTO = new BoletimMMTO();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBolMM");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBolMM");
        pesquisa2.setValor(0);
        pesquisa2.setTipo(1);
        listaPesq.add(pesquisa2);

        return boletimMMTO.get(listaPesq);

    }

    public String dadosEnvioBolAberto(BoletimMMTO boletimMMTO){

        Gson gsonCabec = new Gson();
        JsonArray jsonArrayBoletim = new JsonArray();
        jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));

        ApontCTR apontCTR = new ApontCTR();
        String dadosEnvioApont = apontCTR.dadosEnvioApontBolMM(boletimMMTO.getIdBolMM());

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        return jsonBoletim.toString() + "_" + dadosEnvioApont;

    }

    public String dadosEnvioBolFechado(){

        List boletimMMList = bolFechadoList();

        JsonArray jsonArrayBoletim = new JsonArray();
        String dadosEnvioApont = "";
        JsonArray jsonArrayRendimento = new JsonArray();

        for (int i = 0; i < boletimMMList.size(); i++) {

            BoletimMMTO boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));

            ApontCTR apontCTR = new ApontCTR();
            dadosEnvioApont = apontCTR.dadosEnvioApontBolMM(boletimMMTO.getIdBolMM());

            RendMMTO rendMMTO = new RendMMTO();
            List rendList = rendMMTO.get("idBolRendMM", boletimMMTO.getIdBolMM());

            for (int j = 0; j < rendList.size(); j++) {
                rendMMTO = (RendMMTO) rendList.get(j);
                Gson gsonRend = new Gson();
                jsonArrayRendimento.add(gsonRend.toJsonTree(rendMMTO, rendMMTO.getClass()));
            }

            rendList.clear();

        }

        boletimMMList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonRend = new JsonObject();
        jsonRend.add("rendimento", jsonArrayRendimento);

        return jsonBoletim.toString() + "_" + dadosEnvioApont + "=" + jsonRend.toString();
    }

    public void updateBolAberto(String retorno){

        try{

            int pos1 = retorno.indexOf("_") + 1;
            int pos2 = retorno.indexOf("|") + 1;
            int pos3 = retorno.indexOf("#") + 1;
            String objPrinc = retorno.substring(pos1, pos2);
            String objSeg = retorno.substring(pos2, pos3);
            String objTerc = retorno.substring(pos3);

            JSONObject jObjBolMM = new JSONObject(objPrinc);
            JSONArray jsonArrayBolMM = jObjBolMM.getJSONArray("boletim");

            JSONObject objBol = jsonArrayBolMM.getJSONObject(0);
            Gson gsonBol = new Gson();
            BoletimMMTO boletimMMTO = gsonBol.fromJson(objBol.toString(), BoletimMMTO.class);

            List bolMMList = boletimMMTO.get("idBolMM", boletimMMTO.getIdBolMM());
            BoletimMMTO boletimMMTOBD = (BoletimMMTO) bolMMList.get(0);
            bolMMList.clear();

            boletimMMTOBD.setIdExtBolMM(boletimMMTO.getIdExtBolMM());
            boletimMMTOBD.update();

            JSONObject jObjApontMM = new JSONObject(objSeg);
            JSONArray jsonArrayApontMM = jObjApontMM.getJSONArray("apont");

            if (jsonArrayApontMM.length() > 0) {

                ArrayList<Long> apontaArrayList = new ArrayList<Long>();
                ApontMMTO apontMMTO = new ApontMMTO();

                for (int i = 0; i < jsonArrayApontMM.length(); i++) {

                    JSONObject objApont = jsonArrayApontMM.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontMMTO = gsonApont.fromJson(objApont.toString(), ApontMMTO.class);

                    apontaArrayList.add(apontMMTO.getIdApontMM());

                }

                List apontMMList = apontMMTO.in("idApontMM", apontaArrayList);

                for (int i = 0; i < apontMMList.size(); i++) {

                    apontMMTO = (ApontMMTO) apontMMList.get(i);
                    apontMMTO.setIdExtBolApontMM(boletimMMTO.getIdExtBolMM());
                    apontMMTO.setStatusApontMM(2L);
                    apontMMTO.update();

                }

                ApontImpleMMTO impleMMTO = new ApontImpleMMTO();
                List implementoList = impleMMTO.in("idApontMM", apontaArrayList);

                for (int l = 0; l < implementoList.size(); l++) {
                    impleMMTO = (ApontImpleMMTO) implementoList.get(l);
                    impleMMTO.delete();
                }

                apontaArrayList.clear();

            }

            JSONObject jObjMovLeira = new JSONObject(objTerc);
            JSONArray jsonArrayMovLeira = jObjMovLeira.getJSONArray("movLeira");

            if (jsonArrayMovLeira.length() > 0) {

                ArrayList<Long> movLeiraArrayList = new ArrayList<Long>();
                MovLeiraTO movLeiraTO = new MovLeiraTO();

                for (int i = 0; i < jsonArrayMovLeira.length(); i++) {

                    JSONObject objMovLeira = jsonArrayMovLeira.getJSONObject(i);
                    Gson gsonMovLeira = new Gson();
                    movLeiraTO = gsonMovLeira.fromJson(objMovLeira.toString(), MovLeiraTO.class);

                    movLeiraArrayList.add(movLeiraTO.getIdMovLeira());

                }

                List movLeiraList = movLeiraTO.in("idMovLeira", movLeiraArrayList);

                for (int i = 0; i < movLeiraList.size(); i++) {

                    movLeiraTO = (MovLeiraTO) movLeiraList.get(i);
                    movLeiraTO.setIdExtBolMovLeira(boletimMMTO.getIdExtBolMM());
                    movLeiraTO.setStatusMovLeira(2L);
                    movLeiraTO.update();

                }

            }


        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void deleteBolFechado(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjBolMM = new JSONObject(objPrinc);
            JSONArray jsonArrayBolMM = jObjBolMM.getJSONArray("boletim");

            for (int i = 0; i < jsonArrayBolMM.length(); i++) {

                JSONObject objBol = jsonArrayBolMM.getJSONObject(i);
                Gson gsonBol = new Gson();
                BoletimMMTO boletimMMTO = gsonBol.fromJson(objBol.toString(), BoletimMMTO.class);

                List bolMMList = boletimMMTO.get("idBolMM", boletimMMTO.getIdBolMM());
                BoletimMMTO boletimMMTOBD = (BoletimMMTO) bolMMList.get(0);
                bolMMList.clear();

                if(boletimMMTOBD.getQtdeApontBolMM() == boletimMMTO.getQtdeApontBolMM()){

                    ApontMMTO apontMMTO = new ApontMMTO();
                    List apontaList = apontMMTO.get("idBolApontMM", boletimMMTOBD.getIdBolMM());

                    for (int j = 0; j < apontaList.size(); j++) {

                        apontMMTO = (ApontMMTO) apontaList.get(j);
                        apontMMTO.delete();

                        ApontImpleMMTO apontImpleMMTO = new ApontImpleMMTO();
                        List apontImpleList = apontImpleMMTO.get("idApontMM", apontMMTO.getIdApontMM());

                        for (int l = 0; l < apontImpleList.size(); l++) {
                            apontImpleMMTO = (ApontImpleMMTO) apontImpleList.get(l);
                            apontImpleMMTO.delete();
                        }

                        apontImpleList.clear();

                    }

                    apontaList.clear();

                    RendMMTO rendMMTO = new RendMMTO();
                    List rendList = rendMMTO.get("idBolRendMM", boletimMMTOBD.getIdBolMM());

                    for (int j = 0; j < rendList.size(); j++) {
                        rendMMTO = (RendMMTO) rendList.get(j);
                        rendMMTO.delete();
                    }

                    boletimMMTOBD.delete();

                }

            }


        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

}
