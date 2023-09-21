package br.com.usinasantafe.cmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.ItemCalibPneuBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.util.Tempo;

public class ItemCalibPneuDAO {

    private ItemCalibPneuBean itemCalibPneuBean;

    public ItemCalibPneuDAO() {
    }

    public ItemCalibPneuBean getItemMedPneuBean() {
        if (itemCalibPneuBean == null)
            itemCalibPneuBean = new ItemCalibPneuBean();
        return itemCalibPneuBean;
    }

    public void setItemMedPneuBean() {
        this.itemCalibPneuBean = new ItemCalibPneuBean();
    }

    public void salvarItemMedPneu(Long idBoletimPneu){
        if(verItemMedPneuIdBolIdPosConf(idBoletimPneu, itemCalibPneuBean.getIdPosConfItemCalibPneu())) {
            ItemCalibPneuBean itemCalibPneuBeanBD = getItemMedPneuIdBolIdPosConf(idBoletimPneu, itemCalibPneuBean.getIdPosConfItemCalibPneu());
            itemCalibPneuBeanBD.setNroPneuItemCalibPneu(itemCalibPneuBean.getNroPneuItemCalibPneu());
            itemCalibPneuBeanBD.setPressaoEncItemCalibPneu(itemCalibPneuBean.getPressaoEncItemCalibPneu());
            itemCalibPneuBeanBD.setPressaoColItemCalibPneu(itemCalibPneuBean.getPressaoColItemCalibPneu());
            itemCalibPneuBeanBD.setDthrItemCalibPneu(Tempo.getInstance().dthrAtualString());
            itemCalibPneuBeanBD.update();
        } else {
            itemCalibPneuBean.setDthrItemCalibPneu(Tempo.getInstance().dthrAtualString());
            itemCalibPneuBean.setIdBolItemCalibPneu(idBoletimPneu);
            itemCalibPneuBean.insert();
        }
    }

    public boolean verItemMedPneuIdBolIdPosConf(Long idBol, Long idPosConf){
        List<ItemCalibPneuBean> itemMedPneuIdBolPosList = itemMedPneuIdBolIdPosConfList(idBol, idPosConf);
        boolean ret = (itemMedPneuIdBolPosList.size() > 0);
        itemMedPneuIdBolPosList.clear();
        return ret;
    }

    public boolean verItemMedPneuIdBolNroPneu(Long idBol, String nroPneu){
        List<ItemCalibPneuBean> itemMedPneuIdBolPosList = itemMedPneuIdBolNroPneuList(idBol, nroPneu);
        boolean ret = (itemMedPneuIdBolPosList.size() > 0);
        itemMedPneuIdBolPosList.clear();
        return ret;
    }

    public ItemCalibPneuBean getItemMedPneuIdBolIdPosConf(Long idBol, Long idPosConf){
        List<ItemCalibPneuBean> itemMedPneuIdBolPosList = itemMedPneuIdBolIdPosConfList(idBol, idPosConf);
        ItemCalibPneuBean itemCalibPneuBean = itemMedPneuIdBolPosList.get(0);
        itemMedPneuIdBolPosList.clear();
        return itemCalibPneuBean;
    }

    public List<ItemCalibPneuBean> itemMedPneuIdBolNroPneuList(Long idBol, String nroPneu){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemMedPneuIdBol(idBol));
        pesqArrayList.add(getPesqItemMedPneuNroPneu(nroPneu));

        ItemCalibPneuBean itemCalibPneuBean = new ItemCalibPneuBean();
        return itemCalibPneuBean.get(pesqArrayList);

    }

    public List<ItemCalibPneuBean> itemMedPneuIdBolList(Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemMedPneuIdBol(idBol));

        ItemCalibPneuBean itemCalibPneuBean = new ItemCalibPneuBean();
        return itemCalibPneuBean.getAndOrderBy(pesqArrayList, "idItemCalibPneu", true);

    }

    public List<ItemCalibPneuBean> itemMedPneuIdBolList(ArrayList<Long> idBolPneuList){
        ItemCalibPneuBean itemCalibPneuBean = new ItemCalibPneuBean();
        return itemCalibPneuBean.inAndOrderBy("idBolItemCalibPneu", idBolPneuList, "idItemCalibPneu", true);
    }

    public List<ItemCalibPneuBean> itemMedPneuIdBolIdPosConfList(Long idBol, Long idPosConf){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemMedPneuIdBol(idBol));
        pesqArrayList.add(getPesqItemMedPneuPos(idPosConf));

        ItemCalibPneuBean itemCalibPneuBean = new ItemCalibPneuBean();
        return itemCalibPneuBean.getAndOrderBy(pesqArrayList, "idItemCalibPneu", true);

    }

    public void deleteItemMedPneuIdBol(Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemMedPneuIdBol(idBol));

        ItemCalibPneuBean itemCalibPneuBean = new ItemCalibPneuBean();
        List<ItemCalibPneuBean> itemMedPneuList = itemCalibPneuBean.get(pesqArrayList);

        for(ItemCalibPneuBean itemCalibPneuBeanBD : itemMedPneuList){
            itemCalibPneuBeanBD.delete();
        }

    }

    public void deleteItemMedPneu(ArrayList<Long> idBoletimPneuArrayList){

        ItemCalibPneuBean itemCalibPneuBean = new ItemCalibPneuBean();
        List<ItemCalibPneuBean> itemMedPneuList = itemCalibPneuBean.in("idBolItemCalibPneu", idBoletimPneuArrayList);

        for (ItemCalibPneuBean itemCalibPneuBeanBD : itemMedPneuList) {
            itemCalibPneuBeanBD.delete();
        }

        idBoletimPneuArrayList.clear();

    }

    public String dadosEnvioItemMedPneu(List<ItemCalibPneuBean> itemMedPneuList){

        JsonArray jsonArrayItemMedPneu = new JsonArray();

        for (ItemCalibPneuBean itemCalibPneuBean : itemMedPneuList) {
            Gson gsonItemImp = new Gson();
            jsonArrayItemMedPneu.add(gsonItemImp.toJsonTree(itemCalibPneuBean, itemCalibPneuBean.getClass()));
        }

        itemMedPneuList.clear();

        JsonObject jsonItemMedPneu = new JsonObject();
        jsonItemMedPneu.add("itemmedpneu", jsonArrayItemMedPneu);

        return jsonItemMedPneu.toString();

    }

    private EspecificaPesquisa getPesqItemMedPneuNroPneu(String nroPneu){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("nroPneuItemCalibPneu");
        pesquisa.setValor(nroPneu);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqItemMedPneuIdBol(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolItemCalibPneu");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqItemMedPneuPos(Long idPosConf){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idPosConfItemCalibPneu");
        pesquisa.setValor(idPosConf);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
