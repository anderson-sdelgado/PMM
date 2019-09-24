package br.com.usinasantafe.pmm.control;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.dao.ApontFertDAO;
import br.com.usinasantafe.pmm.dao.ApontMMDAO;
import br.com.usinasantafe.pmm.dao.BoletimFertDAO;
import br.com.usinasantafe.pmm.dao.BoletimMMDAO;
import br.com.usinasantafe.pmm.dao.EquipDAO;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.ImpleMMTO;
import br.com.usinasantafe.pmm.to.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.estaticas.RAtivParadaTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;

public class ApontCTR {

    private ApontMMTO apontMMTO;
    private ApontFertTO apontFertTO;
    private int tipoEquip;

    public ApontCTR() {
        if (apontMMTO == null)
            apontMMTO = new ApontMMTO();
        if (apontFertTO == null)
            apontFertTO = new ApontFertTO();
        EquipDAO equipDAO = new EquipDAO();
        EquipTO equipTO = equipDAO.getEquip();
        if(equipTO.getTipo() == 1) {
            tipoEquip = 1;
        }
        else{
            tipoEquip = 2;
        }
    }

    public void setOSApont(Long os){
        if(tipoEquip == 1) {
            apontMMTO.setOsApontMM(os);
        }
        else{
            apontFertTO.setOsApontFert(os);
        }
    }

    public void setAtivApont(Long ativ){
        ConfigCTR configCTR = new ConfigCTR();
        if(tipoEquip == 1) {
            apontMMTO.setAtivApontMM(ativ);
            apontMMTO.setStatusConApontMM(configCTR.getConfig().getStatusConConfig());
            apontMMTO.setParadaApontMM(0L);
            apontMMTO.setTransbApontMM(0L);
        }
        else{
            apontFertTO.setAtivApontFert(ativ);
            apontFertTO.setStatusConApontFert(configCTR.getConfig().getStatusConConfig());
            apontFertTO.setParadaApontFert(0L);
        }
    }

    public Long getAtivApont(){
        ConfigCTR configCTR = new ConfigCTR();
        if(tipoEquip == 1) {
            return apontMMTO.getAtivApontMM();
        }
        else{
            return apontFertTO.getAtivApontFert();
        }
    }

    public Long getBocalApontFert(){
        return apontFertTO.getBocalApontFert();
    }

    public Double getPressaoApontFert(){
        return apontFertTO.getPressaoApontFert();
    }

    public void setTransb(Long transb){
        apontMMTO.setTransbApontMM(transb);
    }

    public void setBocal(Long bocal){
        apontFertTO.setBocalApontFert(bocal);
    }

    public void setPressaoBocal(Double pressaoBocal){
        apontFertTO.setPressaoApontFert(pressaoBocal);
    }

    public void setVelocApont(Long velocApont){
        apontFertTO.setVelocApontFert(velocApont);
    }

    public void salvarApont(){
        BoletimCTR boletimCTR = new BoletimCTR();
        boletimCTR.atualQtdeApontBol();

        if(tipoEquip == 1) {
            ApontMMDAO apontMMDAO = new ApontMMDAO();
            apontMMDAO.salvarApont(apontMMTO, boletimCTR.getBolMMAberto());
        }
        else{
            ApontFertDAO apontFertDAO = new ApontFertDAO();
            apontFertDAO.salvarApont(apontFertTO, boletimCTR.getBolFertAberto());
        }

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.atualDtUltApontConfig(Tempo.getInstance().dataComHora());
    }

    public void salvarApontMM(ApontMMTO apontMMTO){
        BoletimCTR boletimCTR = new BoletimCTR();
        boletimCTR.atualQtdeApontBol();

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        apontMMDAO.salvarApont(apontMMTO, boletimCTR.getBolMMAberto());

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.atualDtUltApontConfig(Tempo.getInstance().dataComHora());
    }

    public void salvarParadaImple(){
        BoletimCTR boletimCTR = new BoletimCTR();
        boletimCTR.atualQtdeApontBol();

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        apontMMDAO.salvarParadaImple(boletimCTR.getBolMMAberto());

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.atualDtUltApontConfig(Tempo.getInstance().dataComHora());
    }

    public void setParadaApont(Long parada){
        if(tipoEquip == 1) {
            apontMMTO.setParadaApontMM(parada);
        }
        else{
            apontFertTO.setBocalApontFert(0L);
            apontFertTO.setPressaoApontFert(0D);
            apontFertTO.setVelocApontFert(0L);
            apontFertTO.setParadaApontFert(parada);
        }
    }

    public List rAtivParadaList(){
        Long ativ;
        if(tipoEquip == 1) {
            ativ = apontMMTO.getAtivApontMM();
        }
        else{
            ativ = apontFertTO.getAtivApontFert();
        }
        RAtivParadaTO rAtivParadaTO = new RAtivParadaTO();
        List rAtivParadaList = rAtivParadaTO.get("idAtiv", ativ);
        ArrayList<Long> rLista = new ArrayList<Long>();
        for (int i = 0; i < rAtivParadaList.size(); i++) {
            rAtivParadaTO = (RAtivParadaTO) rAtivParadaList.get(i);
            rLista.add(rAtivParadaTO.getIdParada());
        }
        rAtivParadaList.clear();
        ParadaTO paradaTO = new ParadaTO();
        return paradaTO.inAndOrderBy("idParada", rLista, "codParada", true);
    }


    public boolean verifBackupApont() {

        boolean v = false;
        ConfigCTR configCTR = new ConfigCTR();

        if(configCTR.getEquip().getTipo() == 1){
            BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
            ApontMMDAO apontMMDAO = new ApontMMDAO();
            List apontMMList = apontMMDAO.apontList(boletimMMDAO.getIdBolMMAberto());
            if(apontMMList.size() > 0){
                ApontMMTO apontMMTO = (ApontMMTO) apontMMList.get(apontMMList.size() - 1);
                if ((this.apontMMTO.getOsApontMM().equals(apontMMTO.getOsApontMM()))
                        && (this.apontMMTO.getAtivApontMM().equals(apontMMTO.getAtivApontMM()))
                        && (this.apontMMTO.getParadaApontMM().equals(apontMMTO.getParadaApontMM()))) {
                    v = true;
                }
            }
            apontMMList.clear();
        }
        else{
            BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
            ApontFertDAO apontFertDAO = new ApontFertDAO();
            List apontFertList = apontFertDAO.apontList(boletimFertDAO.getIdBolFertAberto());
            if(apontFertList.size() > 0){
                ApontFertTO apontFertTO = (ApontFertTO) apontFertList.get(apontFertList.size() - 1);
                if ((this.apontFertTO.getOsApontFert().equals(apontFertTO.getOsApontFert()))
                        && (this.apontFertTO.getAtivApontFert().equals(apontFertTO.getAtivApontFert()))
                        && (this.apontFertTO.getParadaApontFert().equals(apontFertTO.getParadaApontFert()))) {
                    v = true;
                }
            }
            apontFertList.clear();
        }
        return v;
    }

    public boolean verifBackupApontTransb() {
        boolean v = false;
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        List apontMMList = apontMMDAO.apontList(boletimMMDAO.getIdBolMMAberto());
        if(apontMMList.size() > 0){
            ApontMMTO apontMMTO = (ApontMMTO) apontMMList.get(apontMMList.size() - 1);
            if ((this.apontMMTO.getOsApontMM().equals(apontMMTO.getOsApontMM()))
                    && (this.apontMMTO.getAtivApontMM().equals(apontMMTO.getAtivApontMM()))
                    && (this.apontMMTO.getParadaApontMM().equals(apontMMTO.getParadaApontMM()))
                    && (this.apontMMTO.getTransbApontMM().equals(apontMMTO.getTransbApontMM()))) {
                v = true;
            }
        }
        apontMMList.clear();
        return v;
    }

    public void salvarApontTransb(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        List apontMMList = apontMMDAO.apontList(boletimMMDAO.getIdBolMMAberto());
        ApontMMTO apontMMTO = (ApontMMTO) apontMMList.get(apontMMList.size() - 1);
        apontMMList.clear();
        salvarApontMM(apontMMTO);
    }

    public int verTrocaTransb(){
        ConfigCTR configCTR = new ConfigCTR();
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.verTransbordo(configCTR.getConfig().getDtUltApontConfig());
    }


    public List getListApont(){
        if(tipoEquip == 1) {
            BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
            ApontMMDAO apontMMDAO = new ApontMMDAO();
            List apontMMList = apontMMDAO.apontList(boletimMMDAO.getIdBolMMAberto());
            return  apontMMList;
        }
        else{
            BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
            ApontFertDAO apontFertDAO = new ApontFertDAO();
            List apontFertList = apontFertDAO.apontList(boletimFertDAO.getIdBolFertAberto());
            return  apontFertList;
        }
    }

    public List getListApontAbertoMM(Long idBol){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.apontAbertoList(idBol);
    }

    public List getListApontAbertoFert(Long idBol){
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        return apontFertDAO.apontAbertoList(idBol);
    }

    public List apontAbertoMMList(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.apontAbertoMMList();
    }

    public String dadosEnvioApontMM(){

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();

        List apontaList = apontAbertoMMList();

        for (int i = 0; i < apontaList.size(); i++) {

            ApontMMTO apontMMTO = (ApontMMTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontMMTO, apontMMTO.getClass()));

            ImpleMMTO impleMMTO = new ImpleMMTO();
            List implementoList = impleMMTO.get("idApontImpleMM", apontMMTO.getIdApontMM());

            for (int j = 0; j < implementoList.size(); j++) {
                impleMMTO = (ImpleMMTO) implementoList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayImplemento.add(gsonItem.toJsonTree(impleMMTO, impleMMTO.getClass()));
            }

            implementoList.clear();

        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("implemento", jsonArrayImplemento);

        return jsonAponta.toString() + "_" + jsonImplemento.toString();

    }

    public List apontAbertoFertList(){
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        return apontFertDAO.apontAbertoFertList();
    }

    public String dadosEnvioApontFert(){

        JsonArray jsonArrayAponta = new JsonArray();

        List apontaList = apontAbertoFertList();

        for (int i = 0; i < apontaList.size(); i++) {

            apontFertTO = (ApontFertTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontFertTO, apontFertTO.getClass()));

        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        return jsonAponta.toString();

    }

    public void updateApontMM(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjApontMM = new JSONObject(objPrinc);
            JSONArray jsonArrayApontMM = jObjApontMM.getJSONArray("apont");

            if (jsonArrayApontMM.length() > 0) {

                ArrayList<Long> rLista = new ArrayList<Long>();
                ApontMMTO apontMMTO = new ApontMMTO();

                for (int i = 0; i < jsonArrayApontMM.length(); i++) {

                    JSONObject objApont = jsonArrayApontMM.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontMMTO = gsonApont.fromJson(objApont.toString(), ApontMMTO.class);

                    rLista.add(apontMMTO.getIdApontMM());

                }

                List apontMMList = apontMMTO.in("idApontMM", rLista);

                for (int i = 0; i < apontMMList.size(); i++) {

                    apontMMTO = (ApontMMTO) apontMMList.get(0);
                    apontMMTO.setStatusApontMM(2L);
                    apontMMTO.update();

                }

                ImpleMMTO impleMMTO = new ImpleMMTO();
                List implementoList = impleMMTO.in("idApontImpleMM", rLista);

                for (int l = 0; l < implementoList.size(); l++) {
                    impleMMTO = (ImpleMMTO) implementoList.get(l);
                    impleMMTO.delete();
                }

                rLista.clear();

            }

        }
        catch(Exception e){
            Log.i("PMM", "FALHA = " + e.toString());
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void updateApontaFert(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjApontFert = new JSONObject(objPrinc);
            JSONArray jsonArrayApontFert = jObjApontFert.getJSONArray("apont");

            if (jsonArrayApontFert.length() > 0) {

                ArrayList<Long> rList = new ArrayList<Long>();
                ApontFertTO apontFertTO = new ApontFertTO();

                for (int i = 0; i < jsonArrayApontFert.length(); i++) {

                    JSONObject objApont = jsonArrayApontFert.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontFertTO = gsonApont.fromJson(objApont.toString(), ApontFertTO.class);

                    rList.add(apontFertTO.getIdApontFert());

                }

                List apontFertList = apontFertTO.in("idApontFert", rList);

                for (int i = 0; i < apontFertList.size(); i++) {

                    apontFertTO = (ApontFertTO) apontFertList.get(0);
                    apontFertTO.setStatusApontFert(2L);
                    apontFertTO.update();

                }

                rList.clear();

            }

        }
        catch(Exception e){
            Log.i("PMM", "FALHA = " + e.toString());
            Tempo.getInstance().setEnvioDado(true);
        }

    }

}
