package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimPneuBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ItemMedPneuBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;

public class ItemMedPneuDAO {

    private ItemMedPneuBean itemMedPneuBean;

    public ItemMedPneuDAO() {
    }

    public ItemMedPneuBean getItemMedPneuBean() {
        if (itemMedPneuBean == null)
            itemMedPneuBean = new ItemMedPneuBean();
        return itemMedPneuBean;
    }

    public void setItemMedPneuBean() {
        this.itemMedPneuBean = new ItemMedPneuBean();
    }

    public void salvarItemMedPneu(){
        itemMedPneuBean.insert();
    }

    public boolean verItemMedPneuIdBolIdPosConf(Long idBol, Long idPosConf){
        List<ItemMedPneuBean> itemMedPneuIdBolPosList = itemMedPneuIdBolIdPosConfList(idBol, idPosConf);
        boolean ret = (itemMedPneuIdBolPosList.size() > 0);
        itemMedPneuIdBolPosList.clear();
        return ret;
    }

    public boolean verItemMedPneuIdBolNroPneu(Long idBol, String nroPneu){
        List<ItemMedPneuBean> itemMedPneuIdBolPosList = itemMedPneuIdBolNroPneuList(idBol, nroPneu);
        boolean ret = (itemMedPneuIdBolPosList.size() > 0);
        itemMedPneuIdBolPosList.clear();
        return ret;
    }

    public ItemMedPneuBean getItemMedPneuIdBolIdPosConf(Long idBol, Long idPosConf){
        List<ItemMedPneuBean> itemMedPneuIdBolPosList = itemMedPneuIdBolIdPosConfList(idBol, idPosConf);
        ItemMedPneuBean itemMedPneuBean = itemMedPneuIdBolPosList.get(0);
        itemMedPneuIdBolPosList.clear();
        return itemMedPneuBean;
    }

    public List<ItemMedPneuBean> itemMedPneuIdBolNroPneuList(Long idBol, String nroPneu){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemMedPneuIdBol(idBol));
        pesqArrayList.add(getPesqItemMedPneuNroPneu(nroPneu));

        ItemMedPneuBean itemMedPneuBean = new ItemMedPneuBean();
        return itemMedPneuBean.get(pesqArrayList);

    }

    public List<ItemMedPneuBean> itemMedPneuIdBolList(Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemMedPneuIdBol(idBol));

        ItemMedPneuBean itemMedPneuBean = new ItemMedPneuBean();
        return itemMedPneuBean.getAndOrderBy(pesqArrayList, "idItemMedPneu", true);

    }

    public List<ItemMedPneuBean> itemMedPneuIdBolList(ArrayList<Long> idBolPneuList){
        ItemMedPneuBean itemMedPneuBean = new ItemMedPneuBean();
        return itemMedPneuBean.getAndOrderBy("idBolItemMedPneu", idBolPneuList, "idItemMedPneu", true);
    }

    public List<ItemMedPneuBean> itemMedPneuIdBolIdPosConfList(Long idBol, Long idPosConf){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemMedPneuIdBol(idBol));
        pesqArrayList.add(getPesqItemMedPneuPos(idPosConf));

        ItemMedPneuBean itemMedPneuBean = new ItemMedPneuBean();
        return itemMedPneuBean.getAndOrderBy(pesqArrayList, "idItemMedPneu", true);

    }

    public void deleteItemMedPneuIdBol(Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemMedPneuIdBol(idBol));

        ItemMedPneuBean itemMedPneuBean = new ItemMedPneuBean();
        List<ItemMedPneuBean> itemMedPneuList = itemMedPneuBean.get(pesqArrayList);

        for(ItemMedPneuBean itemMedPneuBeanBD : itemMedPneuList){
            itemMedPneuBeanBD.delete();
        }

    }

    public String dadosEnvioItemMedPneu(List<ItemMedPneuBean> itemMedPneuList){

        JsonArray jsonArrayItemMedPneu = new JsonArray();

        for (ItemMedPneuBean itemMedPneuBean : itemMedPneuList) {
            Gson gsonItemImp = new Gson();
            jsonArrayItemMedPneu.add(gsonItemImp.toJsonTree(itemMedPneuBean, itemMedPneuBean.getClass()));
        }

        itemMedPneuList.clear();

        JsonObject jsonItemMedPneu = new JsonObject();
        jsonItemMedPneu.add("itemmedpneu", jsonArrayItemMedPneu);

        return jsonItemMedPneu.toString();

    }

    private EspecificaPesquisa getPesqItemMedPneuNroPneu(String nroPneu){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("nroPneuItemMedPneu");
        pesquisa.setValor(nroPneu);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqItemMedPneuIdBol(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolItemMedPneu");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqItemMedPneuPos(Long idPosConf){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idPosConfPneu");
        pesquisa.setValor(idPosConf);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
