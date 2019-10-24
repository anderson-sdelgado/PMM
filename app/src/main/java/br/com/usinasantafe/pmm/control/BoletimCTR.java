package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.dao.ApontMMDAO;
import br.com.usinasantafe.pmm.dao.AtividadeDAO;
import br.com.usinasantafe.pmm.dao.BoletimFertDAO;
import br.com.usinasantafe.pmm.dao.BoletimMMDAO;
import br.com.usinasantafe.pmm.dao.EquipDAO;
import br.com.usinasantafe.pmm.dao.EquipSegDAO;
import br.com.usinasantafe.pmm.dao.OSDAO;
import br.com.usinasantafe.pmm.bean.estaticas.EquipTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontImpleMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.ImpleMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.MovLeiraTO;
import br.com.usinasantafe.pmm.bean.variaveis.RecolhFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.RendMMTO;
import br.com.usinasantafe.pmm.util.AtualDadosServ;

public class BoletimCTR {

    private BoletimMMTO boletimMMTO;
    private BoletimFertTO boletimFertTO;
    private OSDAO osDAO;
    private AtividadeDAO atividadeDAO;
    private int tipoEquip;

    public BoletimCTR() {
        if (boletimMMTO == null)
            boletimMMTO = new BoletimMMTO();
        if (boletimFertTO == null)
            boletimFertTO = new BoletimFertTO();
    }

    public void setTipoEquip(){
        EquipDAO equipDAO = new EquipDAO();
        EquipTO equipTO = equipDAO.getEquip();
        if(equipTO.getTipo() == 1) {
            tipoEquip = 1;
        }
        else{
            tipoEquip = 2;
        }
    }

    public boolean verBolABerto(){
        boolean ret = false;
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        if(boletimMMDAO.verBolMMAberto() || boletimFertDAO.verBolFertAberto()){
            ret = true;
            if(boletimMMDAO.verBolMMAberto()){
                boletimMMTO = boletimMMDAO.getBolMMAberto();
            }
            if(boletimFertDAO.verBolFertAberto()){
                boletimFertTO = boletimFertDAO.getBolFertAberto();
            }
        }
        return ret;
    }

    public void setFuncBol(Long matric){
        if(tipoEquip == 1) {
            boletimMMTO.setMatricFuncBolMM(matric);
        }
        else{
            boletimFertTO.setMatricFuncBolFert(matric);
        }
    }

    public void setEquipBol(){
        EquipDAO equipDAO = new EquipDAO();
        EquipTO equipTO = equipDAO.getEquip();
        if(equipTO.getTipo() == 1) {
            boletimMMTO.setIdEquipBolMM(equipTO.getIdEquip());
        }
        else{
            boletimFertTO.setIdEquipBolFert(equipTO.getIdEquip());
        }
    }

    public void setTurnoBol(Long idTurno){
        if(tipoEquip == 1) {
            boletimMMTO.setIdTurnoBolMM(idTurno);
        }
        else{
            boletimFertTO.setIdTurnoBolFert(idTurno);
        }
    }

    public void setOSBol(Long os){
        if(tipoEquip == 1) {
            boletimMMTO.setOsBolMM(os);
        }
        else{
            boletimFertTO.setOsBolFert(os);
        }
    }

    public void setAtivBol(Long ativ){
        ConfigCTR configCTR = new ConfigCTR();
        if(tipoEquip == 1) {
            boletimMMTO.setAtivPrincBolMM(ativ);
            boletimMMTO.setStatusConBolMM(configCTR.getConfig().getStatusConConfig());
        }
        else{
            boletimFertTO.setAtivPrincBolFert(ativ);
            boletimFertTO.setStatusConBolFert(configCTR.getConfig().getStatusConConfig());
        }
    }

    public void atualDadosOperador(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList operadorArrayList = new ArrayList();
        operadorArrayList.add("FuncionarioTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, operadorArrayList);
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList turnoArrayList = new ArrayList();
        turnoArrayList.add("TurnoTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, turnoArrayList);
    }

    public void atualDadosEquipSeg(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList equipSegArrayList = new ArrayList();
        equipSegArrayList.add("EquipSegTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, equipSegArrayList);
    }

    public void atualDadosParada(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList paradaArrayList = new ArrayList();
        paradaArrayList.add("RAtivParadaTO");
        paradaArrayList.add("ParadaTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, paradaArrayList);
    }

    public void atualDadosBocal(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList bocalArrayList = new ArrayList();
        bocalArrayList.add("BocalTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, bocalArrayList);
    }

    public void atualDadosPressao(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList bocalArrayList = new ArrayList();
        bocalArrayList.add("PressaoBocalTO");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, bocalArrayList);
    }

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog);
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        atividadeDAO = new AtividadeDAO();
        atividadeDAO.verAtiv(dado  + "_" + configCTR.getEquip().getNroEquip(), telaAtual, telaProx, progressDialog);
    }

    public ArrayList retAtivFiltrArrayList(Long nroOS){
        ConfigCTR configCTR = new ConfigCTR();
        atividadeDAO = new AtividadeDAO();
        return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), nroOS);
    }

    public Long getIdParadaCheckList(){
        atividadeDAO = new AtividadeDAO();
        return atividadeDAO.idParadaCheckList();
    }

    public List retFuncaoAtivParList(Long idAtiv){
        atividadeDAO = new AtividadeDAO();
        return atividadeDAO.retFuncaoAtivParList(idAtiv);
    }

    public void atualQtdeApontBol(){
        EquipDAO equipDAO = new EquipDAO();
        EquipTO equipTO = equipDAO.getEquip();
        if(equipTO.getTipo() == 1) {
            BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
            boletimMMDAO.atualQtdeApontBol();
        }
        else{
            BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
            boletimFertDAO.atualQtdeApontBol();
        }
    }

    public BoletimMMTO getBolMMAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getBolMMAberto();
    }

    public BoletimFertTO getBolFertAberto(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.getBolFertAberto();
    }

    public void insRend(Long nroOS){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.insRend(nroOS);
    }

    public void setHodometroInicialBol(Double horimetroNum){
        if(tipoEquip == 1) {
            boletimMMTO.setHodometroInicialBolMM(horimetroNum);
            boletimMMTO.setHodometroFinalBolMM(0D);
            boletimMMTO.setIdExtBolMM(0L);
        }
        else{
            boletimFertTO.setHodometroInicialBolFert(horimetroNum);
            boletimFertTO.setHodometroFinalBolFert(0D);
            boletimFertTO.setIdExtBolFert(0L);
        }
    }

    public void setHodometroFinalBol(Double horimetroNum){
        if(tipoEquip == 1) {
            boletimMMTO.setHodometroFinalBolMM(horimetroNum);
        }
        else{
            boletimFertTO.setHodometroFinalBolFert(horimetroNum);
        }
    }

    public void setIdEquipBombaBol(Long idEquip){
        boletimFertTO.setIdEquipBombaBolFert(idEquip);
    }

    public Long getAtiv(){
        if(tipoEquip == 1) {
            return boletimMMTO.getAtivPrincBolMM();
        }
        else{
            return boletimFertTO.getAtivPrincBolFert();
        }
    }

    public Long getTurno(){
        if(tipoEquip == 1) {
            return boletimMMTO.getIdTurnoBolMM();
        }
        else{
            return boletimFertTO.getIdTurnoBolFert();
        }
    }

    public Long getFunc(){
        if(tipoEquip == 1) {
            return boletimMMTO.getMatricFuncBolMM();
        }
        else{
            return boletimFertTO.getMatricFuncBolFert();
        }
    }

    public Long getIdBol(){
        if(tipoEquip == 1) {
            BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
            return boletimMMDAO.getIdBolMMAberto();
        }
        else{
            BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
            return boletimFertDAO.getIdBolFertAberto();
        }
    }

    public void salvarBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolAberto(boletimMMTO);
    }

    public void salvarBolAbertoFert(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        boletimFertDAO.salvarBolAberto(boletimFertTO);
    }

    public void salvarBolFechadoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolFechado(boletimMMTO);
    }

    public void salvarBolFechadoFert(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        boletimFertDAO.salvarBolFechado(boletimFertTO);
    }

    public boolean verRendMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.verRend();
    }

    public RendMMTO getRend(int pos){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getRend(pos);
    }

    public void atualRend(RendMMTO rendMMTO){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.atualRend(rendMMTO);
    }

    public int qtdeRend(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.qtdeRend();
    }

    public boolean verRecolh(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.verRecolh();
    }

    public void insMovLeiraAberta(Long idLeira){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.insMovLeira(idLeira,1L);
    }

    public void insMovLeiraFechada(Long idLeira){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.insMovLeira(idLeira,2L);
    }

    public RecolhFertTO getRecolh(int pos) {
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.getRecolh(pos);
    }

    public void atualRecolh(RecolhFertTO recolhFertTO){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        boletimFertDAO.atualRecolh(recolhFertTO);
    }

    public int qtdeRecolh(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.qtdeRecolh();
    }

    public boolean verImplemento(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verImple(nroEquip);
    }

    public void setImplemento(ImpleMMTO impleMMTO){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        equipSegDAO.setImplemento(impleMMTO);
    }

    public boolean verDuplicImpleMM(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verDuplicImple(nroEquip);
    }

    public boolean verMotoBomba(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verMotoBomba(nroEquip);
    }

    public Long ultAtivMMMenu(){
        BoletimMMTO boletimMMTO = getBolMMAberto();
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        List apontList = apontMMDAO.apontList(boletimMMTO.getIdBolMM());
        if (apontList.size() == 0) {
            return boletimMMTO.getAtivPrincBolMM();
        } else {
            ApontMMTO apontTO = (ApontMMTO) apontList.get(apontList.size() - 1);
            return apontTO.getAtivApontMM();
        }
    }

    public boolean verTransb(Long nroEquip){
        EquipSegDAO equipSegDAO = new EquipSegDAO();
        return equipSegDAO.verTransb(nroEquip);
    }

    public int getTipoEquip() {
        return tipoEquip;
    }

    public String dadosEnvioBolFechadoMM(){

        List boletimMMList = bolFechMMList();

        JsonArray jsonArrayBoletim = new JsonArray();
        String dadosEnvioApont = "";
        JsonArray jsonArrayRendimento = new JsonArray();

        for (int i = 0; i < boletimMMList.size(); i++) {

            BoletimMMTO boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));

            ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
            dadosEnvioApont = apontMMMovLeiraCTR.dadosEnvioApontBolMM(boletimMMTO.getIdBolMM());

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

    public List bolAbertoSemEnvioMMList(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolAbertoSemEnvioList();
    }

    public boolean verEnvioDadosBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolAbertoSemEnvioList().size() > 0;
    }

    public String dadosEnvioBolAbertoMMSemEnvio(){

        List boletimMMList = bolAbertoSemEnvioMMList();

        JsonArray jsonArrayBoletim = new JsonArray();
        String dadosEnvioApont = "";

        for (int i = 0; i < boletimMMList.size(); i++) {

            BoletimMMTO boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));

            ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
            dadosEnvioApont = apontMMMovLeiraCTR.dadosEnvioApontBolMM(boletimMMTO.getIdBolMM());

        }

        boletimMMList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        return jsonBoletim.toString() + "_" + dadosEnvioApont;

    }

    public String dadosEnvioBolFechadoFert(){

        List boletimFertList = bolFechFertList();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayRecolhimento = new JsonArray();

        for (int i = 0; i < boletimFertList.size(); i++) {

            BoletimFertTO boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertTO, boletimFertTO.getClass()));

            ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
            List apontaFertList = apontMMMovLeiraCTR.getListApontAbertoFert(boletimFertTO.getIdBolFert());

            for (int j = 0; j < apontaFertList.size(); j++) {

                ApontFertTO apontFertTO = (ApontFertTO) apontaFertList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontFertTO, apontFertTO.getClass()));

            }

            apontaFertList.clear();

            RecolhFertTO recolhFertTO = new RecolhFertTO();
            List recolhimentoList = recolhFertTO.get("idBolRecolhFert", boletimFertTO.getIdBolFert());

            for (int j = 0; j < recolhimentoList.size(); j++) {
                recolhFertTO = (RecolhFertTO) recolhimentoList.get(j);
                Gson gsonRecol = new Gson();
                jsonArrayRecolhimento.add(gsonRecol.toJsonTree(recolhFertTO, recolhFertTO.getClass()));
            }

            recolhimentoList.clear();

        }

        boletimFertList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonRecolhimento = new JsonObject();
        jsonRecolhimento.add("recolhimento", jsonArrayRecolhimento);

        return jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonRecolhimento.toString();
    }

    public List bolAbertoSemEnvioFertList(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.bolAbertoSemEnvioList();
    }

    public boolean verEnvioDadosBolAbertoFert(){
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.bolAbertoSemEnvioList().size() > 0;
    }

    public String dadosEnvioBolAbertoFertSemEnvio(){

        List boletimFertList = bolAbertoSemEnvioFertList();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();

        for (int i = 0; i < boletimFertList.size(); i++) {

            BoletimFertTO boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertTO, boletimFertTO.getClass()));

            ApontMMMovLeiraCTR apontMMMovLeiraCTR = new ApontMMMovLeiraCTR();
            List apontaList = apontMMMovLeiraCTR.getListApontAbertoFert(boletimFertTO.getIdBolFert());

            for (int j = 0; j < apontaList.size(); j++) {

                ApontFertTO apontFertTO = (ApontFertTO) apontaList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontFertTO, apontFertTO.getClass()));

            }

            apontaList.clear();

        }

        boletimFertList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        return jsonBoletim.toString() + "_" + jsonAponta.toString();

    }

    public void deleteBolFechadoMM(String retorno) {

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

    public void updateBolAbertoMM(String retorno){

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

    public void deleteBolFechadoFert(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjBolFert = new JSONObject(objPrinc);
            JSONArray jsonArrayBolFert = jObjBolFert.getJSONArray("boletim");

            for (int i = 0; i < jsonArrayBolFert.length(); i++) {

                JSONObject objBol = jsonArrayBolFert.getJSONObject(i);
                Gson gsonBol = new Gson();
                BoletimFertTO boletimFertTO = gsonBol.fromJson(objBol.toString(), BoletimFertTO.class);

                List bolFertList = boletimFertTO.get("idBolFert", boletimFertTO.getIdBolFert());
                BoletimFertTO boletimFertTOBD = (BoletimFertTO) bolFertList.get(0);
                bolFertList.clear();

                if(boletimFertTOBD.getQtdeApontBolFert() == boletimFertTO.getQtdeApontBolFert()){

                    ApontFertTO apontFertTO = new ApontFertTO();
                    List apontList = apontFertTO.get("idBolApontFert", boletimFertTOBD.getIdBolFert());

                    for (int j = 0; j < apontList.size(); j++) {

                        apontFertTO = (ApontFertTO) apontList.get(j);
                        apontFertTO.delete();

                    }

                    apontList.clear();

                    RecolhFertTO recolhFertTO = new RecolhFertTO();
                    List recolhList = recolhFertTO.get("idBolRecolhFert", boletimFertTOBD.getIdBolFert());

                    for (int j = 0; j < recolhList.size(); j++) {
                        recolhFertTO = (RecolhFertTO) recolhList.get(j);
                        recolhFertTO.delete();
                    }

                    recolhList.clear();

                    boletimFertTOBD.delete();

                }

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void updateBolAbertoFert(String retorno){

        try{

            int pos1 = retorno.indexOf("_") + 1;
            int pos2 = retorno.indexOf("|") + 1;
            String objPrinc = retorno.substring(pos1, pos2);
            String objSeg = retorno.substring(pos2);

            JSONObject jObjBolFert = new JSONObject(objPrinc);
            JSONArray jsonArrayBolFert = jObjBolFert.getJSONArray("boletim");

            JSONObject objBol = jsonArrayBolFert.getJSONObject(0);
            Gson gsonBol = new Gson();
            BoletimFertTO boletimFertTO = gsonBol.fromJson(objBol.toString(), BoletimFertTO.class);

            List bolFertList = boletimFertTO.get("idBolFert", boletimFertTO.getIdBolFert());
            BoletimFertTO boletimFertTOBD = (BoletimFertTO) bolFertList.get(0);
            bolFertList.clear();

            boletimFertTOBD.setIdExtBolFert(boletimFertTO.getIdExtBolFert());
            boletimFertTOBD.update();

            JSONObject jObjApontFert = new JSONObject(objSeg);
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
                    apontFertTO.setIdExtBolApontFert(boletimFertTO.getIdExtBolFert());
                    apontFertTO.setStatusApontFert(2L);
                    apontFertTO.update();

                }

                rList.clear();

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public List bolFechMMList() {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolFechList();
    }

    public boolean verEnvioDadosBolFechMM() {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolFechList().size() > 0;
    }

    public List bolFechFertList() {
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.bolFechList();
    }

    public boolean verEnvioDadosBolFechFert() {
        BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
        return boletimFertDAO.bolFechList().size() > 0;
    }

}
