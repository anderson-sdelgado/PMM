package br.com.usinasantafe.pmm.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.ItemOSMecanBean;

public class ItemOSMecanDAO {

    public ItemOSMecanDAO() {
    }

    public List<ItemOSMecanBean> itemOSMecanList(Long idOS){
        ItemOSMecanBean itemOSMecanBean = new ItemOSMecanBean();
        return itemOSMecanBean.getAndOrderBy("idOS", idOS,"seqItemOS",true);
    }

    public void itemOSDelAll(){
        ItemOSMecanBean itemOSMecanBean = new ItemOSMecanBean();
        itemOSMecanBean.deleteAll();
    }

    public void recDadosItemOS(JSONArray jsonArray) throws JSONException {

        itemOSDelAll();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            ItemOSMecanBean itemOSMecanBean = gson.fromJson(objeto.toString(), ItemOSMecanBean.class);
            itemOSMecanBean.insert();
        }

    }

}
