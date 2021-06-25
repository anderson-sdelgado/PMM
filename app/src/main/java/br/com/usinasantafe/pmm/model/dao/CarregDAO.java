package br.com.usinasantafe.pmm.model.dao;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.CarregBean;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class CarregDAO {

    public CarregDAO() {
    }

    public boolean verLeiraExibir(){
        List carregList = leiraExibir();
        boolean ret = carregList.size() > 0;
        carregList.clear();
        return ret;
    }

    private List leiraExibir(){
        CarregBean carregBean = new CarregBean();
        return carregBean.dif("idLeiraCarreg", 0L);
    }

    public CarregBean carregLeiraExibir(){
        List carregList = leiraExibir();
        CarregBean carregBean = (CarregBean) carregList.get(0);
        carregList.clear();
        return carregBean;
    }

    private List<CarregBean> carregInsumoList(){
        CarregBean carregBean = new CarregBean();
        List<CarregBean> carregList = carregBean.get("statusCarreg", 1L);
        return carregList;
    }

    private List<CarregBean> leiraDescarregList(){
        CarregBean carregBean = new CarregBean();
        List<CarregBean> carregList = carregBean.get("statusCarreg", 4L);
        return carregList;
    }

    private List<CarregBean> ordCarregList(){
        CarregBean carregBean = new CarregBean();
        List carregList = carregBean.getAndOrderBy("statusCarreg", 3L, "idCarreg", false);
        return carregList;
    }

    private CarregBean getLeiraDescarreg(){
        List<CarregBean> carregList = leiraDescarregList();
        CarregBean carregBean = carregList.get(0);
        carregList.clear();
        return carregBean;
    }

    private CarregBean getCarregInsumo(){
        List<CarregBean> carregList = carregInsumoList();
        CarregBean carregBean = carregList.get(0);
        carregList.clear();
        return carregBean;
    }

    public CarregBean getOrdCarreg(){
        List<CarregBean> carregList = ordCarregList();
        CarregBean carregBean = carregList.get(0);
        carregList.clear();
        return carregBean;
    }

    public boolean verEnviaCarregInsumo(){
        List carregList = carregInsumoList();
        boolean ret = carregList.size() > 0;
        carregList.clear();
        return ret;
    }

    public boolean verEnvioLeiraDescarreg(){
        List carregList = leiraDescarregList();
        boolean ret = carregList.size() > 0;
        carregList.clear();
        return ret;
    }

    public void abrirCarregInsumo(Long matricFunc, Long idEquip, Long idProd, Context context){

        CarregBean carregBean = new CarregBean();
        carregBean.setDthrCarreg(Tempo.getInstance().dthrSemTZ());
        carregBean.setEquipCarreg(idEquip);
        carregBean.setProdCarreg(idProd);
        carregBean.setMotoCarreg(matricFunc);
        carregBean.setTipoCarreg(1L);
        carregBean.setIdLeiraCarreg(0L);
        carregBean.setStatusCarreg(1L);
        carregBean.insert();

        EnvioDadosServ.getInstance().envioDados(context);

    }

    public void abrirCarregComposto(Long matricFunc, Long idEquip, Long idLeira, Context context){

        CarregBean carregBean = new CarregBean();
        carregBean.setDthrCarreg(Tempo.getInstance().dthrSemTZ());
        carregBean.setEquipCarreg(idEquip);
        carregBean.setProdCarreg(76271L);
        carregBean.setMotoCarreg(matricFunc);
        carregBean.setTipoCarreg(2L);
        carregBean.setIdLeiraCarreg(idLeira);
        carregBean.setStatusCarreg(1L);
        carregBean.insert();

//        EnvioDadosServ.getInstance().envioDados(context);

    }

    public void salvarLeiraDescarreg(Long idLeira, Context context){

        CarregBean carregBean = getOrdCarreg();
        carregBean.setIdLeiraCarreg(idLeira);
        carregBean.setStatusCarreg(4L);
        carregBean.update();

        EnvioDadosServ.getInstance().envioDados(context);

    }


    public void pesqCarregProduto(Long idEquip, Context telaAtual, Class telaProx){
        VerifDadosServ.getInstance().verDados(String.valueOf(idEquip), "OrdCarregProduto", telaAtual, telaProx);
    }

    public void pesqCarregComposto(Long idEquip, Context telaAtual, Class telaProx){
        VerifDadosServ.getInstance().verDados(String.valueOf(idEquip), "OrdCarregComposto", telaAtual, telaProx);
    }

    public String dadosEnvioCarregInsumo(){

        JsonArray carregJsonArray = new JsonArray();

        CarregBean carregBean = getCarregInsumo();
        Gson gson = new Gson();
        carregJsonArray.add(gson.toJsonTree(carregBean, carregBean.getClass()));

        JsonObject carregJsonObj = new JsonObject();
        carregJsonObj.add("carreg", carregJsonArray);

        return carregJsonObj.toString();

    }

    public String dadosEnvioLeiraDescarreg(){

        JsonArray carregJsonArray = new JsonArray();

        CarregBean carregBean = getLeiraDescarreg();
        Gson gson = new Gson();
        carregJsonArray.add(gson.toJsonTree(carregBean, carregBean.getClass()));

        JsonObject carregJsonObj = new JsonObject();
        carregJsonObj.add("carreg", carregJsonArray);

        return carregJsonObj.toString();

    }

    public void updCarregInsumo(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;

            String obj = retorno.substring(pos1);

            JSONObject jObjCarreg = new JSONObject(obj);
            JSONArray jsonArrayCarreg = jObjCarreg.getJSONArray("carreg");

            JSONObject objCarreg = jsonArrayCarreg.getJSONObject(0);
            Gson gsonCarreg = new Gson();
            CarregBean carregBean = gsonCarreg.fromJson(objCarreg.toString(), CarregBean.class);

            List carregList = carregBean.get("idCarreg", carregBean.getIdCarreg());
            CarregBean carregBeanBD = (CarregBean) carregList.get(0);
            carregBeanBD.setStatusCarreg(2L);
            carregBeanBD.update();

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void recebOrdCarreg(String result) {

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        CarregBean carregBean = gson.fromJson(objeto.toString(), CarregBean.class);
                        List<CarregBean> carregList = carregBean.get("dthrCarreg", carregBean.getDthrCarreg());
                        if(carregList.size() > 0){
                            CarregBean carregBeanBD = carregList.get(0);
                            carregBeanBD.setIdRegCompostoCarreg(carregBean.getIdRegCompostoCarreg());
                            carregBeanBD.setIdOrdCarreg(carregBean.getIdOrdCarreg());
                            carregBeanBD.setPesoEntradaCarreg(carregBean.getPesoEntradaCarreg());
                            carregBeanBD.setPesoSaidaCarreg(carregBean.getPesoSaidaCarreg());
                            carregBeanBD.setPesoLiquidoCarreg(carregBean.getPesoLiquidoCarreg());
                            carregBeanBD.setStatusCarreg(3L);
                            carregBeanBD.update();
                        }
                        else{
                            carregBean.setStatusCarreg(3L);
                            carregBean.insert();
                        }


                    }

                    VerifDadosServ.getInstance().pulaTelaSemTerm();

                }

            } else {
                VerifDadosServ.getInstance().envioDados();
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().envioDados();
        }

    }

    public void updCarregLeiraDescarreg(String result) {

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        CarregBean carregBean = gson.fromJson(objeto.toString(), CarregBean.class);
                        List<CarregBean> carregList = carregBean.get("idCarreg", carregBean.getIdCarreg());
                        CarregBean carregBeanBD = carregList.get(0);
                        carregBeanBD.setStatusCarreg(5L);
                        carregBeanBD.update();

                    }

                    VerifDadosServ.getInstance().pulaTelaSemTerm();

                }

            } else {
                VerifDadosServ.getInstance().envioDados();
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().envioDados();
        }

    }

}
