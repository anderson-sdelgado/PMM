package br.com.usinasantafe.pmm.control;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import br.com.usinasantafe.pmm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.pmm.model.dao.InformativoDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
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

    public void verifDadosInformativo(String dados, Context telaAtual, Class telaProx, String activity){
        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setVerInforConfig(0L);
        InformativoDAO informativoDAO = new InformativoDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("informativoDAO.verifDadosInformativo(dados ,telaAtual, telaProx, activity);", activity);
        informativoDAO.verifDadosInformativo(dados ,telaAtual, telaProx, activity);
    }

    public void verifDadosInformativo(String activity) {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().verifDadosInformativo()", activity);
        VerifDadosServ.getInstance().verifDadosInformativo(String.valueOf(motoMecFertCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert()), "Informativo", activity);
    }

    public void receberVerifInformativo(String result){

        ConfigCTR configCTR = new ConfigCTR();

        try {

            if (!result.contains("exceeded")) {

                String[] retorno = result.split("_");

                int tipo = Integer.parseInt(retorno[0]);

                if(tipo == 1){
                    recPlantio(retorno[1]);
                }
                else if(tipo == 3){
                    recColheita(retorno[1]);
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
            LogErroDAO.getInstance().insertLogErro(e);
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
