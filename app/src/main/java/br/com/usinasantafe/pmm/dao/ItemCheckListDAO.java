package br.com.usinasantafe.pmm.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class ItemCheckListDAO {

    public ItemCheckListDAO() {
    }

    public void atualCheckList(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().verDados(dado, "CheckList", telaAtual, telaProx, progressDialog);
    }

    public void recDadosCheckList(String result) {

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("_") + 1;
                String objPrinc = result.substring(0, (pos1 - 1));
                String objSeg = result.substring(pos1);

                JSONObject jObj = new JSONObject(objPrinc);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                EquipTO equipTO = new EquipTO();
                equipTO.deleteAll();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    equipTO = gson.fromJson(objeto.toString(), EquipTO.class);
                    equipTO.insert();

                }

                jObj = new JSONObject(objSeg);
                jsonArray = jObj.getJSONArray("dados");

                ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                itemCheckListTO.deleteAll();

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    ItemCheckListTO itemCheckList = gson.fromJson(objeto.toString(), ItemCheckListTO.class);
                    itemCheckList.insert();

                }

                CabecalhoCLDAO cabecalhoCLDAO = new CabecalhoCLDAO();
                cabecalhoCLDAO.atualDataAtualCL();
                VerifDadosServ.getInstance().pulaTelaSemTerm();

            } else {
                VerifDadosServ.getInstance().pulaTelaSemTerm();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            VerifDadosServ.getInstance().pulaTelaSemTerm();
        }

    }

    public int qtdeItem(Long idChecklist){
        ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
        return itemCheckListTO.get("idCheckList", idChecklist).size();
    }

}
