package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;

public class PreCECDAO {

    public PreCECDAO() {
    }

    ///////////////////////////////////CERTIFICADO ABERTO//////////////////////////////////////////

    public void abrirPreCEC(){
        PreCECBean preCECBean = new PreCECBean();
        preCECBean.setDataSaidaUsina(Tempo.getInstance().dthr());
        preCECBean.setDataChegCampo("");
        preCECBean.setDataSaidaCampo("");
        preCECBean.setCam(0L);
        preCECBean.setLibCam(0L);
        preCECBean.setCarr1(0L);
        preCECBean.setLibCarr1(0L);
        preCECBean.setCarr2(0L);
        preCECBean.setLibCarr2(0L);
        preCECBean.setCarr3(0L);
        preCECBean.setLibCarr3(0L);
        preCECBean.setCarr4(0L);
        preCECBean.setLibCarr4(0L);
        preCECBean.setStatus(1L);
        preCECBean.insert();
    }

    public void clearPreCECAberto(){
        PreCECBean preCECBean = getPreCECAberto();
        preCECBean.setCam(0L);
        preCECBean.setLibCam(0L);
        preCECBean.setCarr1(0L);
        preCECBean.setLibCarr1(0L);
        preCECBean.setCarr2(0L);
        preCECBean.setLibCarr2(0L);
        preCECBean.setCarr3(0L);
        preCECBean.setLibCarr3(0L);
        preCECBean.setCarr4(0L);
        preCECBean.setLibCarr4(0L);
        preCECBean.update();
    }

    public void delPreCECAberto(){
        if(verPreCECAberto()){
            PreCECBean preCECBean = getPreCECAberto();
            preCECBean.delete();
        }
    }

    public void fechaPreCEC(Long matricFunc, Long codTurno, Long nroEquip){
        PreCECBean preCECBean = getPreCECAberto();
        preCECBean.setMoto(matricFunc);
        preCECBean.setTurno(codTurno);
        preCECBean.setCam(nroEquip);
        preCECBean.setDataSaidaCampo(Tempo.getInstance().dthr());
        preCECBean.setStatus(2L);
        preCECBean.update();
        delPrecCEC();
        EnvioDadosServ.getInstance().envioDados(null);
    }

    public String dadosEnvioPreCEC(){

        List<PreCECBean> preCECFechadoList = preCECListFechado();
        JsonArray preCECJsonArray = new JsonArray();

        for (PreCECBean preCECBean : preCECFechadoList) {
            Gson gson = new Gson();
            preCECJsonArray.add(gson.toJsonTree(preCECBean, preCECBean.getClass()));
        }

        preCECFechadoList.clear();

        JsonObject preCECJsonObj = new JsonObject();
        preCECJsonObj.add("precec", preCECJsonArray);

        return preCECJsonObj.toString();

    }

    public void atualPreCEC(String objeto) throws JSONException {

        JSONObject jsonObj = new JSONObject(objeto);
        JSONArray preCECJsonArray = jsonObj.getJSONArray("precec");

        for (int i = 0; i < preCECJsonArray.length(); i++) {

            JSONObject objApont = preCECJsonArray.getJSONObject(i);
            Gson gsonApont = new Gson();
            PreCECBean preCECBean = gsonApont.fromJson(objApont.toString(), PreCECBean.class);

            List<PreCECBean> preCECList = preCECBean.get("idPreCEC", preCECBean.getIdPreCEC());

            if(preCECList.size() > 0){
                PreCECBean preCECBDBean = preCECList.get(i);
                preCECBDBean.setStatus(3L);
                preCECBDBean.update();
            }

        }

    }

    public void delPrecCEC(){
        List<PreCECBean> precCECList = preCECListTerminado();
        int qtdeCEC = precCECList.size();
        if (qtdeCEC > 10) {
            PreCECBean preCECBean = precCECList.get(0);
            preCECBean.delete();
        }
        precCECList.clear();
    }

    /////////////////////////////VERIFICAR DADOS////////////////////////////////

    public boolean hasPreCEC(){
        List<PreCECBean> preCECAbertoList = preCECList();
        boolean retorno = preCECAbertoList.size() > 0;
        preCECAbertoList.clear();
        return retorno;
    }

    public boolean verPreCECFechado(){
        List<PreCECBean> preCECFechadoList = preCECListFechado();
        boolean retorno = preCECFechadoList.size() > 0;
        preCECFechadoList.clear();
        return retorno;
    }

    public boolean verPreCECAberto(){
        List<PreCECBean> preCECAbertoList = preCECListAberto();
        boolean retorno = preCECAbertoList.size() > 0;
        preCECAbertoList.clear();
        return retorno;
    }

    public boolean verDataPreCEC(){
        List<PreCECBean> preCECList = preCECListAberto();
        PreCECBean preCECBean = preCECList.get(0);
        preCECList.clear();
        return ((!preCECBean.getDataSaidaUsina().equals("")) && (!preCECBean.getDataChegCampo().equals("")));
    }

    ////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////SET DADOS////////////////////////////////

    public void setDataChegCampo(){
        PreCECBean preCECBean = getPreCECAberto();
        preCECBean.setDataChegCampo(Tempo.getInstance().dthr());
        preCECBean.update();
    }

    public void setLib(Long lib, int qtde){
        PreCECBean preCECBean = getPreCECAberto();
        if(qtde == 0){
            preCECBean.setLibCam(lib);
        } else if(qtde == 1){
            preCECBean.setLibCarr1(lib);
        } else if(qtde == 2){
            preCECBean.setLibCarr2(lib);
        } else if(qtde == 3){
            preCECBean.setLibCarr3(lib);
        } else if(qtde == 4){
            preCECBean.setLibCarr4(lib);
        }
        preCECBean.update();
    }

    public void setCarr(Long carr, int pos){
        PreCECBean preCECBean = getPreCECAberto();
        if(pos == 1){
            preCECBean.setCarr1(carr);
        } else if(pos == 2){
            preCECBean.setCarr2(carr);
        } else if(pos == 3){
            preCECBean.setCarr3(carr);
        } else if(pos == 4){
            preCECBean.setCarr4(carr);
        }
        preCECBean.update();
    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////GET DADOS/////////////////////////////////

    public PreCECBean getPreCECAberto(){
        List<PreCECBean> preCECList = preCECListAberto();
        PreCECBean preCECBean = preCECList.get(0);
        preCECList.clear();
        return preCECBean;
    }

    public String getDataChegCampo(){
        PreCECBean preCECBean = getPreCECAberto();
        return preCECBean.getDataChegCampo();
    }

    public String getDataSaidaUlt(){
        PreCECBean preCECBean = new PreCECBean();
        List<PreCECBean> preCECList = preCECBean.all();
        String retorno;
        if (preCECList.size() == 0)  {
            retorno = "NÃO POSSUE CARREGAMENTOS";
        } else {
            preCECBean = preCECList.get(preCECList.size() - 1);
            if(preCECBean.getDataSaidaCampo().equals("")){
                retorno = "NÃO POSSUE CARREGAMENTOS";
            }
            else {
                retorno = "ULT. VIAGEM: " + preCECBean.getDataSaidaCampo();
            }
        }
        preCECList.clear();
        return retorno;
    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////LIST DE PRECEC/////////////////////////////////////////

    private List<PreCECBean> preCECList(){
        PreCECBean preCECBean = new PreCECBean();
        List<PreCECBean> preCECList = preCECBean.all();
        return preCECList;
    }

    private List<PreCECBean> preCECListAberto(){
        PreCECBean preCECBean = new PreCECBean();
        List<PreCECBean> preCECList = preCECBean.get("status", 1L);
        return preCECList;
    }

    public List<PreCECBean> preCECListFechado(){
        PreCECBean preCECBean = new PreCECBean();
        List<PreCECBean> preCECList = preCECBean.get("status", 2L);
        return preCECList;
    }

    public List<PreCECBean> preCECListTerminado(){
        PreCECBean preCECBean = new PreCECBean();
        List<PreCECBean> preCECList = preCECBean.difAndOrderBy("status", 1L, "idPreCEC", true);
        return preCECList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
