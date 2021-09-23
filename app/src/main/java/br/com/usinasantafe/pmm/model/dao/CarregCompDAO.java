package br.com.usinasantafe.pmm.model.dao;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class CarregCompDAO {

    public CarregCompDAO() {
    }

    public boolean verLeiraExibir(){
        List carregList = leiraExibir();
        boolean ret = carregList.size() > 0;
        carregList.clear();
        return ret;
    }

    private List leiraExibir(){
        CarregCompBean carregCompBean = new CarregCompBean();
        return carregCompBean.dif("idLeiraCarreg", 0L);
    }

    private List<CarregCompBean> carregInsumoList(){
        CarregCompBean carregCompBean = new CarregCompBean();
        List<CarregCompBean> carregList = carregCompBean.get("statusCarreg", 1L);
        return carregList;
    }

    private List<CarregCompBean> carregCompostoList(){
        CarregCompBean carregCompBean = new CarregCompBean();
        List<CarregCompBean> carregList = carregCompBean.get("statusCarreg", 4L);
        return carregList;
    }

    private List<CarregCompBean> ordCarregList(){
        CarregCompBean carregCompBean = new CarregCompBean();
        List carregList = carregCompBean.getAndOrderBy("statusCarreg", 3L, "idCarreg", false);
        return carregList;
    }

    private CarregCompBean getCarregComposto(){
        List<CarregCompBean> carregList = carregCompostoList();
        CarregCompBean carregCompBean = carregList.get(0);
        carregList.clear();
        return carregCompBean;
    }

    private CarregCompBean getCarregInsumo(){
        List<CarregCompBean> carregList = carregInsumoList();
        CarregCompBean carregCompBean = carregList.get(0);
        carregList.clear();
        return carregCompBean;
    }

    public CarregCompBean getOrdCarreg(){
        List<CarregCompBean> carregList = ordCarregList();
        CarregCompBean carregCompBean = carregList.get(0);
        carregList.clear();
        return carregCompBean;
    }

    public boolean verEnvioCarreg(){
        List carregList = carregInsumoList();
        boolean ret = carregList.size() > 0;
        carregList.clear();
        return ret;
    }

    public boolean verEnvioLeiraDescarreg(){
        List carregList = carregCompostoList();
        boolean ret = carregList.size() > 0;
        carregList.clear();
        return ret;
    }

    public void abrirCarregInsumo(Long matricFunc, Long idEquip, Long idProd){

        CarregCompBean carregCompBean = new CarregCompBean();
        carregCompBean.setDthrCarreg(Tempo.getInstance().dthr());
        carregCompBean.setEquipCarreg(idEquip);
        carregCompBean.setProdCarreg(idProd);
        carregCompBean.setMotoCarreg(matricFunc);
        carregCompBean.setTipoCarreg(1L);
        carregCompBean.setIdLeiraCarreg(0L);
        carregCompBean.setStatusCarreg(1L);
        carregCompBean.insert();

        EnvioDadosServ.getInstance().envioDados(null);

    }

    public void abrirCarregComposto(Long matricFunc, Long idEquip, Long idLeira, Long os){

        CarregCompBean carregCompBean = new CarregCompBean();
        carregCompBean.setDthrCarreg(Tempo.getInstance().dthr());
        carregCompBean.setEquipCarreg(idEquip);
        carregCompBean.setMotoCarreg(matricFunc);
        carregCompBean.setOsCarreg(os);
        carregCompBean.setTipoCarreg(2L);
        carregCompBean.setIdLeiraCarreg(idLeira);
        carregCompBean.setStatusCarreg(1L);
        carregCompBean.insert();

        EnvioDadosServ.getInstance().envioDados(null);

    }

    public void salvarLeiraDescarreg(Long idLeira){

        CarregCompBean carregCompBean = getOrdCarreg();
        carregCompBean.setIdLeiraCarreg(idLeira);
        carregCompBean.setStatusCarreg(4L);
        carregCompBean.update();

        EnvioDadosServ.getInstance().envioDados(null);

    }


    public void verifDadosCarreg(Long idEquip, Context telaAtual, Class telaProx, String activity){
        VerifDadosServ.getInstance().verifDados(String.valueOf(idEquip), "OrdCarreg", telaAtual, telaProx, activity);
    }

    public String dadosEnvioCarregInsumo(){

        JsonArray carregJsonArray = new JsonArray();

        CarregCompBean carregCompBean = getCarregInsumo();
        Gson gson = new Gson();
        carregJsonArray.add(gson.toJsonTree(carregCompBean, carregCompBean.getClass()));

        JsonObject carregJsonObj = new JsonObject();
        carregJsonObj.add("carreg", carregJsonArray);

        return carregJsonObj.toString();

    }

    public String dadosEnvioCarregComposto(){

        JsonArray carregJsonArray = new JsonArray();

        CarregCompBean carregCompBean = getCarregComposto();
        Gson gson = new Gson();
        carregJsonArray.add(gson.toJsonTree(carregCompBean, carregCompBean.getClass()));

        JsonObject carregJsonObj = new JsonObject();
        carregJsonObj.add("carreg", carregJsonArray);

        return carregJsonObj.toString();

    }

    public void updCarregInsumo(JSONArray jsonArray) throws JSONException {

        JSONObject objCarreg = jsonArray.getJSONObject(0);
        Gson gsonCarreg = new Gson();
        CarregCompBean carregCompBean = gsonCarreg.fromJson(objCarreg.toString(), CarregCompBean.class);

        List<CarregCompBean> carregList = carregCompBean.get("idCarreg", carregCompBean.getIdCarreg());
        CarregCompBean carregCompBeanBD = carregList.get(0);
        carregCompBeanBD.setStatusCarreg(2L);
        carregCompBeanBD.update();

    }

    public void recebOrdCarreg(JSONArray jsonArray) throws JSONException {

        Log.i("ECM", "RECEBIMENTO 4 ");

        for (int i = 0; i < jsonArray.length(); i++) {

            Log.i("ECM", "RECEBIMENTO 5 ");

            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            CarregCompBean carregCompBean = gson.fromJson(objeto.toString(), CarregCompBean.class);
            List<CarregCompBean> carregList = carregCompBean.get("dthrCarreg", carregCompBean.getDthrCarreg());
            if(carregList.size() > 0){

                Log.i("ECM", "RECEBIMENTO 6 ");

                CarregCompBean carregCompBeanBD = carregList.get(0);
                carregCompBeanBD.setIdRegCompostoCarreg(carregCompBean.getIdRegCompostoCarreg());
                carregCompBeanBD.setIdOrdCarreg(carregCompBean.getIdOrdCarreg());
                carregCompBeanBD.setPesoEntradaCarreg(carregCompBean.getPesoEntradaCarreg());
                carregCompBeanBD.setPesoSaidaCarreg(carregCompBean.getPesoSaidaCarreg());
                carregCompBeanBD.setPesoLiquidoCarreg(carregCompBean.getPesoLiquidoCarreg());
                carregCompBeanBD.setStatusCarreg(3L);
                carregCompBeanBD.update();
            }
            else{

                Log.i("ECM", "RECEBIMENTO 7 ");
                carregCompBean.setStatusCarreg(3L);
                carregCompBean.insert();
            }

        }

        Log.i("ECM", "RECEBIMENTO 7 ");
        VerifDadosServ.getInstance().pulaTela();

    }

    public void updCarregComposto(JSONArray jsonArray) throws JSONException {

        Log.i("ECM", "RECEBIMENTO 5 ");

        for (int i = 0; i < jsonArray.length(); i++) {
            Log.i("ECM", "RECEBIMENTO 6 ");
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            CarregCompBean carregCompBean = gson.fromJson(objeto.toString(), CarregCompBean.class);
            List<CarregCompBean> carregList = carregCompBean.get("idCarreg", carregCompBean.getIdCarreg());
            CarregCompBean carregCompBeanBD = carregList.get(0);
            carregCompBeanBD.setStatusCarreg(5L);
            carregCompBeanBD.update();

        }

    }

}
