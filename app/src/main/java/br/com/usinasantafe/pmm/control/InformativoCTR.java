package br.com.usinasantafe.pmm.control;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import br.com.usinasantafe.pmm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.pmm.model.dao.InformativoDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.util.Json;
import br.com.usinasantafe.pmm.util.VerifDadosServ;
import br.com.usinasantafe.pmm.view.DadosColheitaActivity;
import br.com.usinasantafe.pmm.view.DadosPlantioActivity;

public class InformativoCTR {

    public InformativoCTR() {
    }

    public InfColheitaBean getInfColheita(){
        InformativoDAO informativoDAO = new InformativoDAO();
        return informativoDAO.getInfColheita();
    }

    public InfPlantioBean getInfPlantio(){
        InformativoDAO informativoDAO = new InformativoDAO();
        return informativoDAO.getInfPlantio();
    }

    public void verifDadosInformativo(String dados, Context telaAtual, Class telaProx){
        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setVerInforConfig(0L);
        InformativoDAO informativoDAO = new InformativoDAO();
        informativoDAO.verifDadosInformativo(dados ,telaAtual, telaProx);
    }

    public void verifDadosInformativo() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        VerifDadosServ.getInstance().verifDadosInformativo(String.valueOf(motoMecFertCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert()), "Informativo");
    }

    public void receberVerifInformativo(String retorno){

        ConfigCTR configCTR = new ConfigCTR();

        try {

            if (!retorno.contains("exceeded")) {

                int pos1 = retorno.trim().indexOf("=") + 1;
                int pos2 = retorno.trim().indexOf("_") + 1;
                int tipo = Integer.valueOf(retorno.trim().substring(pos1, (pos2 - 1)));

                String dados = retorno.substring(pos2);

                if(tipo == 1){
                    recPlantio(dados);
                }
                else if(tipo == 3){
                    recColheita(dados);
                }
                else{
                    if(configCTR.getVerRecInformativo() == 0L) {
                        configCTR.setVerInforConfig(3L);
                        VerifDadosServ.getInstance().pulaTelaSemBarra();
                    }
                }

            } else {
                if(configCTR.getVerRecInformativo() == 0L) {
                    configCTR.setVerInforConfig(1L);
                    VerifDadosServ.getInstance().pulaTelaSemBarra();
                }
            }

        } catch (Exception e) {
            LogErroDAO.getInstance().insert(e);
            if(configCTR.getVerRecInformativo() == 0L) {
                configCTR.setVerInforConfig(1L);
                VerifDadosServ.getInstance().pulaTelaSemBarra();
            }
        }

    }

    private void recColheita(String result) throws JSONException {

        ConfigCTR configCTR = new ConfigCTR();

        Json json = new Json();
        JSONArray jsonArray = json.jsonArray(result);

        if (jsonArray.length() > 0) {

            InformativoDAO informativoDAO = new InformativoDAO();
            informativoDAO.recDadosInfColheita(jsonArray);

            if(configCTR.getVerRecInformativo() == 0L){
                VerifDadosServ.getInstance().pulaTelaSemBarra(DadosColheitaActivity.class);
            }
            else{
                configCTR.setVerInforConfig(2L);
            }

        } else {

            if(configCTR.getVerRecInformativo() == 0L) {
                configCTR.setVerInforConfig(3L);
                VerifDadosServ.getInstance().pulaTelaSemBarra();
            }
            else if(configCTR.getVerRecInformativo() == 1L) {
                configCTR.setVerInforConfig(3L);
            }
        }

    }

    private void recPlantio(String result) throws JSONException {

        ConfigCTR configCTR = new ConfigCTR();

        Json json = new Json();
        JSONArray jsonArray = json.jsonArray(result);

        if (jsonArray.length() > 0) {

            InformativoDAO informativoDAO = new InformativoDAO();
            informativoDAO.recDadosInfPlantio(jsonArray);

            if(configCTR.getVerRecInformativo() == 0L){
                VerifDadosServ.getInstance().pulaTelaSemBarra(DadosPlantioActivity.class);
            }
            else{
                configCTR.setVerInforConfig(2L);
            }

        } else {

            if(configCTR.getVerRecInformativo() == 0L) {
                configCTR.setVerInforConfig(3L);
                VerifDadosServ.getInstance().pulaTelaSemBarra();
            }
            else if(configCTR.getVerRecInformativo() == 1L) {
                configCTR.setVerInforConfig(3L);
            }
        }

    }

}
