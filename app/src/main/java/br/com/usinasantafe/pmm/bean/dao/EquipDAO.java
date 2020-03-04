package br.com.usinasantafe.pmm.bean.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.bean.estaticas.REquipPneuTO;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.bean.estaticas.REquipAtivTO;
import br.com.usinasantafe.pmm.util.VerifDadosServ;
import br.com.usinasantafe.pmm.bean.estaticas.EquipTO;

public class EquipDAO {

    public EquipDAO() {
    }

    public EquipTO getEquip(){
        EquipTO equipTO = new EquipTO();
        List equipList = equipTO.all();
        equipTO = (EquipTO) equipList.get(0);
        equipList.clear();
        if ((equipTO.getTipoEquipFert() == 1) || (equipTO.getTipoEquipFert() == 2)) {
            equipTO.setTipo(2L);
        } else {
            equipTO.setTipo(1L);
        }
        return equipTO;
    }

    public void verEquip(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(true);
        VerifDadosServ.getInstance().verDados(dado, "Equip", telaAtual, telaProx, progressDialog);
    }

    public void recDadosEquip(String result){
        try {

            int pos1 = result.indexOf("#") + 1;
            int pos2 = result.indexOf("_") + 1;
            String objPrinc = result.substring(0, (pos1 - 1));
            String objSeg = result.substring(pos1, (pos2 - 1));
            String objTerc = result.substring(pos2);

            JSONObject jObj = new JSONObject(objPrinc);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

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

                REquipAtivTO rEquipAtivTO = new REquipAtivTO();
                rEquipAtivTO.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    REquipAtivTO rEquipAtiv = gson.fromJson(objeto.toString(), REquipAtivTO.class);
                    rEquipAtiv.insert();

                }

                jObj = new JSONObject(objTerc);
                jsonArray = jObj.getJSONArray("dados");

                REquipPneuTO rEquipPneuTO = new REquipPneuTO();
                rEquipPneuTO.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    REquipPneuTO rEquipPneu = gson.fromJson(objeto.toString(), REquipPneuTO.class);
                    rEquipPneu.insert();

                }

                ConfigCTR configCTR = new ConfigCTR();
                configCTR.setEquipConfig(equipTO);

                VerifDadosServ.getInstance().pulaTelaSemTerm();

            } else {
                VerifDadosServ.getInstance().msgSemTerm("EQUIPAMENTO INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE EQUIPAMENTO! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

}
