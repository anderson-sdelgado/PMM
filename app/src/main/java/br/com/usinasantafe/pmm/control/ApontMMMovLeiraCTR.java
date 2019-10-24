package br.com.usinasantafe.pmm.control;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.dao.ApontFertDAO;
import br.com.usinasantafe.pmm.dao.ApontMMDAO;
import br.com.usinasantafe.pmm.dao.BoletimFertDAO;
import br.com.usinasantafe.pmm.dao.BoletimMMDAO;
import br.com.usinasantafe.pmm.dao.EquipDAO;
import br.com.usinasantafe.pmm.dao.MovLeiraDAO;
import br.com.usinasantafe.pmm.bean.estaticas.EquipTO;
import br.com.usinasantafe.pmm.bean.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.bean.estaticas.RAtivParadaTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontImpleMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.MovLeiraTO;

public class ApontMMMovLeiraCTR {

    private ApontMMTO apontMMTO;
    private ApontFertTO apontFertTO;
    private int tipoEquip;

    public ApontMMMovLeiraCTR() {
        if (apontMMTO == null)
            apontMMTO = new ApontMMTO();
        if (apontFertTO == null)
            apontFertTO = new ApontFertTO();
    }

    public void setOSApont(Long os){
        EquipDAO equipDAO = new EquipDAO();
        EquipTO equipTO = equipDAO.getEquip();
        if(equipTO.getTipo() == 1) {
            tipoEquip = 1;
        }
        else{
            tipoEquip = 2;
        }
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
        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora());
    }

    public void salvarApontMM(ApontMMTO apontMMTO){
        BoletimCTR boletimCTR = new BoletimCTR();
        boletimCTR.atualQtdeApontBol();

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        apontMMDAO.salvarApont(apontMMTO, boletimCTR.getBolMMAberto());

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora());
    }

    public void salvarParadaImple(){
        BoletimCTR boletimCTR = new BoletimCTR();
        boletimCTR.atualQtdeApontBol();

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        apontMMDAO.salvarParadaImple(boletimCTR.getBolMMAberto());

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora());
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
        apontMMTO.setTransbApontMM(this.apontMMTO.getTransbApontMM());
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
        return apontMMDAO.apontAbertoMMList(idBol);
    }

    public List getListMovLeiraAberto(Long idBol){
        MovLeiraDAO movLeiraDAO = new MovLeiraDAO();
        return movLeiraDAO.movLeiraAbertoList(idBol);
    }

    public List getListApontAbertoFert(Long idBol){
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        return apontFertDAO.apontAbertoList(idBol);
    }

    public List getListApontAbertoMM(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.apontAbertoMMList();
    }

    public List getListMovLeiraAberto(){
        MovLeiraDAO movLeiraDAO = new MovLeiraDAO();
        return movLeiraDAO.movLeiraAbertoMMList();
    }

    public Boolean verEnvioDadosApontMM(){
        Boolean retorno = false;
        if((getListApontAbertoMM().size() > 0) || getListMovLeiraAberto().size() > 0){
            retorno = true;
        }
        return retorno;
    }

    public String dadosEnvioApontBolMM(Long idBol){
        return envioApontMM(getListApontAbertoMM(idBol), getListMovLeiraAberto(idBol));
    }

    public String dadosEnvioApontMM(){
        return envioApontMM(getListApontAbertoMM(), getListMovLeiraAberto());
    }

    public String envioApontMM(List apontaList, List movLeiraList){

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayImplemento = new JsonArray();
        JsonArray jsonArrayMovLeira = new JsonArray();

        for (int i = 0; i < apontaList.size(); i++) {

            ApontMMTO apontMMTO = (ApontMMTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontMMTO, apontMMTO.getClass()));

            ApontImpleMMTO apontImpleMMTO = new ApontImpleMMTO();
            List apontImpleList = apontImpleMMTO.get("idApontMM", apontMMTO.getIdApontMM());

            for (int l = 0; l < apontImpleList.size(); l++) {
                apontImpleMMTO = (ApontImpleMMTO) apontImpleList.get(l);
                Gson gsonItemImp = new Gson();
                jsonArrayImplemento.add(gsonItemImp.toJsonTree(apontImpleMMTO, apontImpleMMTO.getClass()));
            }

            apontImpleList.clear();

        }

        apontaList.clear();

        for (int j = 0; j < movLeiraList.size(); j++) {
            MovLeiraTO movLeiraTO = (MovLeiraTO) movLeiraList.get(j);
            Gson gsonRend = new Gson();
            jsonArrayMovLeira.add(gsonRend.toJsonTree(movLeiraTO, movLeiraTO.getClass()));
        }

        movLeiraList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("implemento", jsonArrayImplemento);

        JsonObject jsonMovLeira = new JsonObject();
        jsonMovLeira.add("movleira", jsonArrayMovLeira);

        return jsonAponta.toString() + "|" + jsonImplemento.toString() + "#" + jsonMovLeira.toString();

    }

    public List apontAbertoFertList(){
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        return apontFertDAO.apontAbertoFertList();
    }

    public boolean verEnvioDadosApontFert(){
        ApontFertDAO apontFertDAO = new ApontFertDAO();
        return apontFertDAO.apontAbertoFertList().size() > 0;
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
            int pos2 = retorno.indexOf("|") + 1;

            String objPrinc = retorno.substring(pos1, pos2);
            String objSeg = retorno.substring(pos2);

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

                ApontImpleMMTO apontImpleMMTO = new ApontImpleMMTO();
                List apontImpleList = apontImpleMMTO.in("idApontImpleMM", rLista);

                for (int l = 0; l < apontImpleList.size(); l++) {
                    apontImpleMMTO = (ApontImpleMMTO) apontImpleList.get(l);
                    apontImpleMMTO.delete();
                }

                rLista.clear();

            }

            JSONObject jObjMovLeira = new JSONObject(objSeg);
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
                    movLeiraTO.setStatusMovLeira(2L);
                    movLeiraTO.update();

                }

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
