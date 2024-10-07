package br.com.usinasantafe.cmm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.util.Tempo;
import br.com.usinasantafe.cmm.util.VerifDadosServ;

public class CarregCompDAO {

    public CarregCompDAO() {
    }

    public boolean verLeiraExibir() {
        List<CarregCompBean> carregList = leiraExibir();
        boolean ret = carregList.size() > 0;
        carregList.clear();
        return ret;
    }

    public boolean verCarregInsumo() {
        List<CarregCompBean> carregList = carregInsumoList();
        boolean ret = carregList.size() > 0;
        carregList.clear();
        return ret;
    }

    private List<CarregCompBean> leiraExibir() {
        CarregCompBean carregCompBean = new CarregCompBean();
        return carregCompBean.dif("idLeiraCarreg", 0L);
    }

    private List<CarregCompBean> carregInsumoList() {
        CarregCompBean carregCompBean = new CarregCompBean();
        List<CarregCompBean> carregList = carregCompBean.get("statusCarreg", 1L);
        return carregList;
    }

    public List<CarregCompBean> carregCompostoDescarregInsumo(ArrayList<Long> idApontList) {

        CarregCompBean carregCompBean = new CarregCompBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCarreg");
        pesquisa.setValor(4L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        return carregCompBean.inAndGet("idApontCarreg", idApontList, pesqArrayList);

    }

    public List<CarregCompBean> carregCompostoDescarregInsumoRetrofit(Long idApont) {

        CarregCompBean carregCompBean = new CarregCompBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqDescarregEnvio());
        pesqArrayList.add(getPesqIdApont(idApont));
        return carregCompBean.get(pesqArrayList);

    }

    private List<CarregCompBean> ordCarregList() {
        CarregCompBean carregCompBean = new CarregCompBean();
        return carregCompBean.orderBy("idCarreg", false);
    }

    public List<CarregCompBean> carregCompList(ArrayList<Long> idCarregArrayList) {
        CarregCompBean carregCompBean = new CarregCompBean();
        return carregCompBean.in("idCarreg", idCarregArrayList);
    }

    public List<CarregCompBean> carregCompList(Long idCarreg) {
        CarregCompBean carregCompBean = new CarregCompBean();
        return carregCompBean.get("idCarreg", idCarreg);
    }

    private CarregCompBean getCarregInsumo() {
        List<CarregCompBean> carregList = carregInsumoList();
        CarregCompBean carregCompBean = carregList.get(0);
        carregList.clear();
        return carregCompBean;
    }

    public CarregCompBean getOrdCarreg() {
        List<CarregCompBean> carregList = ordCarregList();
        CarregCompBean carregCompBean = carregList.get(0);
        carregList.clear();
        return carregCompBean;
    }

    public boolean verEnvioCarregInsumoComposto() {
        List<CarregCompBean> carregList = carregInsumoList();
        boolean ret = carregList.size() > 0;
        carregList.clear();
        return ret;
    }

    public boolean verOrdemCarregComLeira() {
        List<CarregCompBean> carregList = ordCarregList();
        boolean ret = false;
        if (carregList.size() > 0) {
            CarregCompBean carregCompBean = carregList.get(0);
            if (carregCompBean.getStatusCarreg() > 2L) {
                ret = true;
            }
        }
        carregList.clear();
        return ret;
    }

    public void abrirCarregInsumo(Long matricFunc, Long idEquip, Long idProd) {
        CarregCompBean carregCompBean = new CarregCompBean();
        carregCompBean.setDthrCarreg(Tempo.getInstance().dthrAtualString());
        carregCompBean.setDthrCarregLong(Tempo.getInstance().dthrStringToLong(Tempo.getInstance().dthrAtualString()));
        carregCompBean.setEquipCarreg(idEquip);
        carregCompBean.setProdCarreg(idProd);
        carregCompBean.setMotoCarreg(matricFunc);
        carregCompBean.setTipoCarreg(1L);
        carregCompBean.setIdLeiraCarreg(0L);
        carregCompBean.setStatusCarreg(1L);
        carregCompBean.insert();
    }

    public void abrirCarregComposto(Long matricFunc, Long idEquip, Long idLeira, Long os, Long idApont) {
        CarregCompBean carregCompBean = new CarregCompBean();
        carregCompBean.setIdApontCarreg(idApont);
        carregCompBean.setDthrCarreg(Tempo.getInstance().dthrAtualString());
        carregCompBean.setDthrCarregLong(Tempo.getInstance().dthrStringToLong(Tempo.getInstance().dthrAtualString()));
        carregCompBean.setIdOrdCarreg(0L);
        carregCompBean.setEquipCarreg(idEquip);
        carregCompBean.setMotoCarreg(matricFunc);
        carregCompBean.setOsCarreg(os);
        carregCompBean.setTipoCarreg(2L);
        carregCompBean.setIdLeiraCarreg(idLeira);
        carregCompBean.setStatusCarreg(4L);
        carregCompBean.insert();
    }

    public void salvarDescarregCarreg(Long idLeira, Long idApont) {
        CarregCompBean carregCompBean = getOrdCarreg();
        carregCompBean.setIdApontCarreg(idApont);
        carregCompBean.setIdLeiraCarreg(idLeira);
        carregCompBean.setStatusCarreg(4L);
        carregCompBean.update();
    }

    public void deleteCarreg(ArrayList<Long> idApontCarregArrayList) {

        CarregCompBean carregCompBean = new CarregCompBean();
        List<CarregCompBean> carregCompList = carregCompBean.in("idApontCarreg", idApontCarregArrayList);

        for (CarregCompBean carregCompBeanBD : carregCompList) {
            carregCompBeanBD.delete();
        }

        idApontCarregArrayList.clear();

    }

    public void verifDadosCarreg(String dados, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity) {
        VerifDadosServ.getInstance().verifDados(dados, "OrdCarreg", telaAtual, telaProx, progressDialog, activity);
    }

    public String dadosEnvioCarreg(List<CarregCompBean> carregCompList) {

        JsonArray carregJsonArray = new JsonArray();

        for (CarregCompBean carregCompBean : carregCompList) {
            Gson gsonItemImp = new Gson();
            carregJsonArray.add(gsonItemImp.toJsonTree(carregCompBean, carregCompBean.getClass()));
        }

        carregCompList.clear();

        JsonObject carregJsonObj = new JsonObject();
        carregJsonObj.add("carreg", carregJsonArray);

        return carregJsonObj.toString();

    }

    public String dadosEnvioCarregInsumo() {

        JsonArray carregJsonArray = new JsonArray();

        CarregCompBean carregCompBean = getCarregInsumo();
        Gson gson = new Gson();
        carregJsonArray.add(gson.toJsonTree(carregCompBean, carregCompBean.getClass()));

        JsonObject carregJsonObj = new JsonObject();
        carregJsonObj.add("carreg", carregJsonArray);

        return carregJsonObj.toString();

    }

    public CarregCompBean dadosEnvioCarregInsumoRetrofit() {
        return getCarregInsumo();
    }

    public ArrayList<Long> idCarregArrayList(String objeto) throws Exception {

        ArrayList<Long> idCarregArrayList = new ArrayList<Long>();

        JSONObject jObjCarreg = new JSONObject(objeto);
        JSONArray jsonArrayCarreg = jObjCarreg.getJSONArray("carreg");

        for (int i = 0; i < jsonArrayCarreg.length(); i++) {

            JSONObject objCarreg = jsonArrayCarreg.getJSONObject(i);
            Gson gsonCarreg = new Gson();
            CarregCompBean carregCompBean = gsonCarreg.fromJson(objCarreg.toString(), CarregCompBean.class);

            idCarregArrayList.add(carregCompBean.getIdCarreg());

        }

        return idCarregArrayList;

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

    public void updCarregInsumo(CarregCompBean carregCompBean) {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCarreg(carregCompBean.getIdCarreg()));
        List<CarregCompBean> carregList = carregCompBean.get(pesqArrayList);
        CarregCompBean carregCompBDBean = carregList.get(0);
        carregCompBDBean.setStatusCarreg(2L);
        carregCompBDBean.update();
    }


    public void updDescarregInsumoCarregComposto(ArrayList<Long> idCarregArrayList) {

        List<CarregCompBean> carregCompList = carregCompList(idCarregArrayList);

        for (CarregCompBean carregCompBean : carregCompList) {
            carregCompBean.setStatusCarreg(5L);
            carregCompBean.update();
        }

        carregCompList.clear();
        idCarregArrayList.clear();
    }

    public void updDescarregInsumoCarregComposto(Long idCarreg) {

        List<CarregCompBean> carregCompList = carregCompList(idCarreg);
        CarregCompBean carregCompBean = carregCompList.get(0);
        carregCompList.clear();
        carregCompBean.setStatusCarreg(5L);
        carregCompBean.update();

    }

    public void recOrdCarreg(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            CarregCompBean carregCompBean = gson.fromJson(objeto.toString(), CarregCompBean.class);
            List<CarregCompBean> carregList = carregCompBean.get("dthrCarreg", carregCompBean.getDthrCarreg());
            if (carregList.size() > 0) {
                CarregCompBean carregCompBeanBD = carregList.get(0);
                carregCompBeanBD.setIdRegCompostoCarreg(carregCompBean.getIdRegCompostoCarreg());
                carregCompBeanBD.setIdOrdCarreg(carregCompBean.getIdOrdCarreg());
                carregCompBeanBD.setPesoEntradaCarreg(carregCompBean.getPesoEntradaCarreg());
                carregCompBeanBD.setPesoSaidaCarreg(carregCompBean.getPesoSaidaCarreg());
                carregCompBeanBD.setPesoLiquidoCarreg(carregCompBean.getPesoLiquidoCarreg());
                if (carregCompBeanBD.getTipoCarreg() == 1L) {
                    carregCompBeanBD.setStatusCarreg(3L);
                }
                carregCompBeanBD.update();
            } else {
                if (carregCompBean.getTipoCarreg() == 1L) {
                    carregCompBean.setStatusCarreg(3L);
                } else {
                    carregCompBean.setStatusCarreg(5L);
                }
                carregCompBean.insert();
            }
        }

        VerifDadosServ.getInstance().pulaTela();

    }

    public void recOrdCarreg(CarregCompBean carregCompBean){

        List<CarregCompBean> carregList = carregCompBean.get("dthrCarreg", carregCompBean.getDthrCarreg());
        if (!carregList.isEmpty()) {
            CarregCompBean carregCompBeanBD = carregList.get(0);
            carregCompBeanBD.setIdRegCompostoCarreg(carregCompBean.getIdRegCompostoCarreg());
            carregCompBeanBD.setIdOrdCarreg(carregCompBean.getIdOrdCarreg());
            carregCompBeanBD.setPesoEntradaCarreg(carregCompBean.getPesoEntradaCarreg());
            carregCompBeanBD.setPesoSaidaCarreg(carregCompBean.getPesoSaidaCarreg());
            carregCompBeanBD.setPesoLiquidoCarreg(carregCompBean.getPesoLiquidoCarreg());
            if (carregCompBeanBD.getTipoCarreg() == 1L) {
                carregCompBeanBD.setStatusCarreg(3L);
            }
            carregCompBeanBD.update();
        } else {
            if (carregCompBean.getTipoCarreg() == 1L) {
                carregCompBean.setStatusCarreg(3L);
            } else {
                carregCompBean.setStatusCarreg(5L);
            }
            carregCompBean.insert();
        }

    }

    private String dadosCarregComp(CarregCompBean carregCompBean) {
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(carregCompBean, carregCompBean.getClass()).toString();
    }

    public ArrayList<String> carregCompAllArrayList(ArrayList<String> dadosArrayList) {
        dadosArrayList.add("CARREG. COMPOSTAGEM");
        CarregCompBean carregCompBean = new CarregCompBean();
        List<CarregCompBean> carregCompList = carregCompBean.orderBy("idCarreg", true);
        for (CarregCompBean carregCompBeanBD : carregCompList) {
            dadosArrayList.add(dadosCarregComp(carregCompBeanBD));
        }
        carregCompList.clear();
        return dadosArrayList;
    }


    private EspecificaPesquisa getPesqDescarregEnvio() {
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCarreg");
        pesquisa.setValor(4L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdApont(Long idApont) {
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idApontCarreg");
        pesquisa.setValor(idApont);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdCarreg(Long idCarreg) {
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCarreg");
        pesquisa.setValor(idCarreg);
        pesquisa.setTipo(1);
        return pesquisa;
    }


}
