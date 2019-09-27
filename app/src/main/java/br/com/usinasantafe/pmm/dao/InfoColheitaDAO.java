package br.com.usinasantafe.pmm.dao;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.to.variaveis.InfoColheitaTO;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class InfoColheitaDAO {

    public InfoColheitaDAO() {
    }

    public void verInfoColheitaEquip(String dado, Context telaAtual, Class telaProx1, Class telaProx2){
        VerifDadosServ.getInstance().verDados(dado, "Colheita", telaAtual, telaProx1, telaProx2);
    }

    public void recColheita(String result) {

        ConfigCTR configCTR = new ConfigCTR();

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    InfoColheitaTO infoColheitaTO = new InfoColheitaTO();
                    infoColheitaTO.deleteAll();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        infoColheitaTO = gson.fromJson(objeto.toString(), InfoColheitaTO.class);
                        infoColheitaTO.insert();

                    }

                    if(configCTR.getVisDadosColhConfig() == 0L){
                        Log.i("PMM", "CHEGOU AKI 2");
                        VerifDadosServ.getInstance().pulaTelaDadosColheita();
                    }
                    else{
                        Log.i("PMM", "CHEGOU AKI 3");
                        configCTR.atualVisDadosColhConfig(2L);
                    }

                } else {

                    if(configCTR.getVisDadosColhConfig() == 0L) {
                        Log.i("PMM", "CHEGOU AKI 4");
                        configCTR.atualVisDadosColhConfig(3L);
                        VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
                    }
                }

            } else {
                if(configCTR.getVisDadosColhConfig() == 0L) {
                    Log.i("PMM", "CHEGOU AKI 5");
                    configCTR.atualVisDadosColhConfig(1L);
                    VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
                }
            }

        } catch (Exception e) {
            if(configCTR.getVisDadosColhConfig() == 0L) {
                Log.i("PMM", "CHEGOU AKI 6");
                configCTR.atualVisDadosColhConfig(1L);
                VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
            }
        }

    }

}
