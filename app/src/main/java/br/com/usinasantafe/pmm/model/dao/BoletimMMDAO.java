package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.pmm.control.BoletimInterface;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.control.ApontCTR;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.MovLeiraBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;

public class BoletimMMDAO implements BoletimInterface {

    public BoletimMMDAO() {
    }

    public boolean verBolAberto(){
        List boletimMMList = boletimAbertoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public BoletimMMBean getBolMMAberto(){
        List boletimMMList = boletimAbertoList();
        BoletimMMBean boletimMMBean = (BoletimMMBean) boletimMMList.get(0);
        boletimMMList.clear();
        return boletimMMBean;
    }

    private List boletimAbertoList(){
        BoletimMMBean boletimMMBean = new BoletimMMBean();
        return boletimMMBean.get("statusBolMM", 1L);
    }

    public Long getIdBolAberto(){
        BoletimMMBean boletimMMBean = getBolMMAberto();
        return boletimMMBean.getIdBolMM();
    }

    public void atualQtdeApontBol(){
        BoletimMMBean boletimMMBean = getBolMMAberto();
        boletimMMBean.setQtdeApontBolMM(boletimMMBean.getQtdeApontBolMM() + 1L);
        boletimMMBean.update();
    }

    public void insRend(Long nroOS){

        BoletimMMBean boletimMMBean = getBolMMAberto();

        RendMMBean rendMMBean = new RendMMBean();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("idBolRendMM");
        pesq1.setValor(boletimMMBean.getIdBolMM());
        pesq1.setTipo(1);
        listaPesq.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("nroOSRendMM");
        pesq2.setValor(nroOS);
        pesq2.setTipo(1);
        listaPesq.add(pesq2);

        List rendList = rendMMBean.get(listaPesq);

        if (rendList.size() == 0) {
            rendMMBean.setIdBolRendMM(boletimMMBean.getIdBolMM());
            rendMMBean.setIdExtBolRendMM(boletimMMBean.getIdExtBolMM());
            rendMMBean.setNroOSRendMM(nroOS);
            rendMMBean.setValorRendMM(0D);
            rendMMBean.insert();
            rendMMBean.commit();
        }

    }

    public void insMovLeira(Long idLeira, Long tipo){
        BoletimMMBean boletimMMBean = getBolMMAberto();
        MovLeiraBean movLeiraBean = new MovLeiraBean();
        movLeiraBean.setTipoMovLeira(tipo);
        movLeiraBean.setIdLeira(idLeira);
        movLeiraBean.setDataHoraMovLeira(Tempo.getInstance().dataComHora());
        movLeiraBean.setIdBolMovLeira(boletimMMBean.getIdBolMM());
        movLeiraBean.setIdExtBolMovLeira(boletimMMBean.getIdExtBolMM());
        movLeiraBean.setStatusMovLeira(1L);
        movLeiraBean.insert();
    }

    public boolean verRend(){
        RendMMBean rendMMBean = new RendMMBean();
        List rendList = rendMMBean.get("idBolRendMM", getBolMMAberto().getIdBolMM());
        Boolean ret = (rendList.size() > 0);
        rendList.clear();
        return ret;
    }

    public int qtdeRend(){
        RendMMBean rendMMBean = new RendMMBean();
        List rendList = rendMMBean.get("idBolRendMM", getBolMMAberto().getIdBolMM());
        return rendList.size();
    }

    public RendMMBean getRend(int pos){
        RendMMBean rendMMBean = new RendMMBean();
        List rendList = rendMMBean.getAndOrderBy("idBolRendMM", getBolMMAberto().getIdBolMM(), "idRendMM", true);
        rendMMBean = (RendMMBean) rendList.get(pos);
        rendList.clear();
        return rendMMBean;
    }

    public void atualRend(RendMMBean rendMMBean){
        rendMMBean.setDthrRendMM(Tempo.getInstance().dataComHora());
        rendMMBean.update();
        rendMMBean.commit();
    }

    public void salvarBolAberto(BoletimMMBean boletimMMBean){
        boletimMMBean.setStatusBolMM(1L);
        boletimMMBean.setDthrInicialBolMM(Tempo.getInstance().dataComHora());
        boletimMMBean.setQtdeApontBolMM(0L);
        boletimMMBean.insert();
    }

    public void salvarBolFechado(BoletimMMBean boletimMMBean) {

        List<BoletimMMBean> listBoletim = boletimAbertoList();

        for(BoletimMMBean boletimMMBeanBD : listBoletim){
            boletimMMBeanBD.setDthrFinalBolMM(Tempo.getInstance().dataComHora());
            boletimMMBeanBD.setStatusBolMM(2L);
            boletimMMBeanBD.setHodometroFinalBolMM(boletimMMBean.getHodometroFinalBolMM());
            boletimMMBeanBD.update();
        }

        listBoletim.clear();

    }

    public List boletimFechadoList() {
        BoletimMMBean boletimMMBean = new BoletimMMBean();
        return boletimMMBean.get("statusBolMM", 2L);
    }

//    public List bolAbertoSemEnvioList() {
//
//        BoletimMMBean boletimMMBean = new BoletimMMBean();
//        ArrayList listaPesq = new ArrayList();
//
//        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
//        pesquisa.setCampo("statusBolMM");
//        pesquisa.setValor(1L);
//        pesquisa.setTipo(1);
//        listaPesq.add(pesquisa);
//
//        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
//        pesquisa2.setCampo("idExtBolMM");
//        pesquisa2.setValor(0L);
//        pesquisa2.setTipo(1);
//        listaPesq.add(pesquisa2);
//
//        return boletimMMBean.get(listaPesq);
//
//    }

    public String dadosEnvioBolAberto(){

        List boletimMMList = boletimAbertoList();

        ArrayList<Long> idBolList = new ArrayList<Long>();
        JsonArray jsonArrayBoletim = new JsonArray();

        for (int i = 0; i < boletimMMList.size(); i++) {

            BoletimMMBean boletimMMBean = (BoletimMMBean) boletimMMList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMBean, boletimMMBean.getClass()));

            idBolList.add(boletimMMBean.getIdBolMM());

        }

        ApontCTR apontCTR = new ApontCTR();
        String dadosEnvioApont = apontCTR.dadosEnvioApontBolMM(idBolList);

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        return jsonBoletim.toString() + "_" + dadosEnvioApont;

    }

    public String dadosEnvioBolFechado(){

        List boletimMMList = boletimFechadoList();

        JsonArray jsonArrayBoletim = new JsonArray();
        String dadosEnvioApont = "";
        JsonArray jsonArrayRendimento = new JsonArray();

        ArrayList<Long> idBolList = new ArrayList<Long>();

        for (int i = 0; i < boletimMMList.size(); i++) {

            BoletimMMBean boletimMMBean = (BoletimMMBean) boletimMMList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMBean, boletimMMBean.getClass()));

            RendMMBean rendMMBean = new RendMMBean();
            List rendList = rendMMBean.get("idBolRendMM", boletimMMBean.getIdBolMM());

            for (int j = 0; j < rendList.size(); j++) {
                rendMMBean = (RendMMBean) rendList.get(j);
                Gson gsonRend = new Gson();
                jsonArrayRendimento.add(gsonRend.toJsonTree(rendMMBean, rendMMBean.getClass()));
            }

            rendList.clear();

            idBolList.add(boletimMMBean.getIdBolMM());

        }

        boletimMMList.clear();

        ApontCTR apontCTR = new ApontCTR();
        dadosEnvioApont = apontCTR.dadosEnvioApontBolMM(idBolList);

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

            BoletimMMBean boletimMMBean = new BoletimMMBean();

            for (int i = 0; i < jsonArrayBolMM.length(); i++) {

                JSONObject objBol = jsonArrayBolMM.getJSONObject(i);
                Gson gsonBol = new Gson();
                boletimMMBean = gsonBol.fromJson(objBol.toString(), BoletimMMBean.class);

                List bolMMList = boletimMMBean.get("idBolMM", boletimMMBean.getIdBolMM());
                BoletimMMBean boletimMMTOBD = (BoletimMMBean) bolMMList.get(0);
                bolMMList.clear();

                boletimMMTOBD.setIdExtBolMM(boletimMMBean.getIdExtBolMM());
                boletimMMTOBD.update();

            }

            JSONObject jObjApontMM = new JSONObject(objSeg);
            JSONArray jsonArrayApontMM = jObjApontMM.getJSONArray("apont");

            if (jsonArrayApontMM.length() > 0) {

                ArrayList<Long> apontaArrayList = new ArrayList<Long>();
                ApontMMBean apontMMBean = new ApontMMBean();

                for (int i = 0; i < jsonArrayApontMM.length(); i++) {

                    JSONObject objApont = jsonArrayApontMM.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontMMBean = gsonApont.fromJson(objApont.toString(), ApontMMBean.class);

                    apontaArrayList.add(apontMMBean.getIdApontMM());

                }

                List apontMMList = apontMMBean.in("idApontMM", apontaArrayList);

                for (int i = 0; i < apontMMList.size(); i++) {

                    apontMMBean = (ApontMMBean) apontMMList.get(i);
                    apontMMBean.setIdExtBolApontMM(boletimMMBean.getIdExtBolMM());
                    apontMMBean.setStatusApontMM(2L);
                    apontMMBean.update();

                }

                ApontImpleMMBean impleMMTO = new ApontImpleMMBean();
                List implementoList = impleMMTO.in("idApontMM", apontaArrayList);

                for (int l = 0; l < implementoList.size(); l++) {
                    impleMMTO = (ApontImpleMMBean) implementoList.get(l);
                    impleMMTO.delete();
                }

                apontaArrayList.clear();

            }

            JSONObject jObjMovLeira = new JSONObject(objTerc);
            JSONArray jsonArrayMovLeira = jObjMovLeira.getJSONArray("movLeira");

            if (jsonArrayMovLeira.length() > 0) {

                ArrayList<Long> movLeiraArrayList = new ArrayList<Long>();
                MovLeiraBean movLeiraBean = new MovLeiraBean();

                for (int i = 0; i < jsonArrayMovLeira.length(); i++) {

                    JSONObject objMovLeira = jsonArrayMovLeira.getJSONObject(i);
                    Gson gsonMovLeira = new Gson();
                    movLeiraBean = gsonMovLeira.fromJson(objMovLeira.toString(), MovLeiraBean.class);

                    movLeiraArrayList.add(movLeiraBean.getIdMovLeira());

                }

                List movLeiraList = movLeiraBean.in("idMovLeira", movLeiraArrayList);

                for (int i = 0; i < movLeiraList.size(); i++) {

                    movLeiraBean = (MovLeiraBean) movLeiraList.get(i);
                    movLeiraBean.setIdExtBolMovLeira(boletimMMBean.getIdExtBolMM());
                    movLeiraBean.setStatusMovLeira(2L);
                    movLeiraBean.update();

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
                BoletimMMBean boletimMMBean = gsonBol.fromJson(objBol.toString(), BoletimMMBean.class);

                List bolMMList = boletimMMBean.get("idBolMM", boletimMMBean.getIdBolMM());
                BoletimMMBean boletimMMTOBD = (BoletimMMBean) bolMMList.get(0);
                bolMMList.clear();

                ApontMMBean apontMMBean = new ApontMMBean();
                List apontaList = apontMMBean.get("idBolApontMM", boletimMMBean.getIdBolMM());

                Long qtde = Long.valueOf(apontaList.size());

                if(qtde == boletimMMBean.getQtdeApontBolMM()){

                    for (int j = 0; j < apontaList.size(); j++) {

                        apontMMBean = (ApontMMBean) apontaList.get(j);
                        apontMMBean.delete();

                        ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
                        List apontImpleList = apontImpleMMBean.get("idApontMM", apontMMBean.getIdApontMM());

                        for (int l = 0; l < apontImpleList.size(); l++) {
                            apontImpleMMBean = (ApontImpleMMBean) apontImpleList.get(l);
                            apontImpleMMBean.delete();
                        }

                        apontImpleList.clear();

                    }

                    apontaList.clear();

                    RendMMBean rendMMBean = new RendMMBean();
                    List rendList = rendMMBean.get("idBolRendMM", boletimMMBean.getIdBolMM());

                    for (int j = 0; j < rendList.size(); j++) {
                        rendMMBean = (RendMMBean) rendList.get(j);
                        rendMMBean.delete();
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
