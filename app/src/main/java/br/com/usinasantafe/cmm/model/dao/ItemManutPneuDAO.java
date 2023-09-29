package br.com.usinasantafe.cmm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.ItemManutPneuBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.cmm.util.Tempo;

public class ItemManutPneuDAO {

    public ItemManutPneuDAO() {
    }

    public void insertItemManutPneu(Long idBolPneu, ItemManutPneuBean itemManutPneuBean){
        if(verItemManutPneuIdBolIdPosConf(idBolPneu, itemManutPneuBean.getIdPosConfItemManutPneu())) {
            ItemManutPneuBean itemManutPneuBeanBD = getItemManutPneuIdBolIdPosConf(idBolPneu, itemManutPneuBean.getIdPosConfItemManutPneu());
            itemManutPneuBeanBD.setNroPneuRetItemManutPneu(itemManutPneuBean.getNroPneuRetItemManutPneu());
            itemManutPneuBeanBD.setNroPneuColItemManutPneu(itemManutPneuBean.getNroPneuColItemManutPneu());
            itemManutPneuBeanBD.setDthrItemManutPneu(Tempo.getInstance().dthrAtualString());
            itemManutPneuBeanBD.update();
        } else {
            itemManutPneuBean.setIdBolItemManutPneu(idBolPneu);
            itemManutPneuBean.setDthrItemManutPneu(Tempo.getInstance().dthrAtualString());
            itemManutPneuBean.insert();
        }
    }

    public ItemManutPneuBean getItemManutPneuIdBolIdPosConf(Long idBol, Long idPosConf){
        List<ItemManutPneuBean> itemManutPneuIdBolPosList = itemManutPneuIdBolIdPosConfList(idBol, idPosConf);
        ItemManutPneuBean itemManutPneuBean = itemManutPneuIdBolPosList.get(0);
        itemManutPneuIdBolPosList.clear();
        return itemManutPneuBean;
    }

    public void deleteItemManutPneuIdBol(Long idBol){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemManutPneuIdBol(idBol));

        ItemManutPneuBean itemManutPneuBean = new ItemManutPneuBean();
        List<ItemManutPneuBean> itemManutPneuList = itemManutPneuBean.get(pesqArrayList);

        for(ItemManutPneuBean itemManutPneuBeanBD : itemManutPneuList){
            itemManutPneuBeanBD.delete();
        }

        itemManutPneuList.clear();

    }

    public void deleteItemManutPneu(ArrayList<Long> idBolPneuLongs){

        List<ItemManutPneuBean> itemManutPneuList = itemManutPneuIdBolList(idBolPneuLongs);

        for (ItemManutPneuBean itemManutPneuBean : itemManutPneuList) {
            itemManutPneuBean.delete();
        }

        itemManutPneuList.clear();

    }

    public boolean verItemManutPneuIdBolIdPosConf(Long idBol, Long idPosConf){
        List<ItemManutPneuBean> itemMedPneuIdBolPosList = itemManutPneuIdBolIdPosConfList(idBol, idPosConf);
        boolean ret = (itemMedPneuIdBolPosList.size() > 0);
        itemMedPneuIdBolPosList.clear();
        return ret;
    }

    public ArrayList<Long> idItemManutPneuArrayList(List<ItemManutPneuBean> itemManutPneuList) {
        ArrayList<Long> idItemManutPneuList = new ArrayList<Long>();
        for (ItemManutPneuBean itemCalibPneuBean : itemManutPneuList) {
            idItemManutPneuList.add(itemCalibPneuBean.getIdItemManutPneu());
        }
        return idItemManutPneuList;
    }

    public List<ItemManutPneuBean> itemManutPneuIdBolIdPosConfList(Long idBol, Long idPosConf){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemManutPneuIdBol(idBol));
        pesqArrayList.add(getPesqItemMedPneuPos(idPosConf));

        ItemManutPneuBean itemManutPneuBean = new ItemManutPneuBean();
        return itemManutPneuBean.getAndOrderBy(pesqArrayList, "idItemManutPneu", true);

    }

    public List<ItemManutPneuBean> itemManutPneuIdBolList(Long idBolPneu){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqItemManutPneuIdBol(idBolPneu));

        ItemManutPneuBean itemManutPneuBean = new ItemManutPneuBean();
        return itemManutPneuBean.get(pesqArrayList);
    }

    public List<ItemManutPneuBean> itemManutPneuIdBolList(ArrayList<Long> idBolPneuArrayList){
        ItemManutPneuBean itemManutPneuBean = new ItemManutPneuBean();
        return itemManutPneuBean.in("idBolItemManutPneu", idBolPneuArrayList);
    }

    public String dadosEnvioItemManutPneu(List<ItemManutPneuBean> itemManutPneuList){

        JsonArray jsonArrayApont = new JsonArray();

        for (ItemManutPneuBean itemManutPneuBean : itemManutPneuList) {
            Gson gson = new Gson();
            jsonArrayApont.add(gson.toJsonTree(itemManutPneuBean, itemManutPneuBean.getClass()));
        }

        itemManutPneuList.clear();

        JsonObject jsonItemManutPneu = new JsonObject();
        jsonItemManutPneu.add("itemmanutpneu", jsonArrayApont);

        return jsonItemManutPneu.toString();

    }

    private EspecificaPesquisa getPesqItemManutPneuIdBol(Long idBol){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolItemManutPneu");
        pesquisa.setValor(idBol);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqItemMedPneuPos(Long idPosConf){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idPosConfItemManutPneu");
        pesquisa.setValor(idPosConf);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
