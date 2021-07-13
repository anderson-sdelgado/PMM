package br.com.usinasantafe.pmm.control;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.LeiraBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.pmm.model.dao.CarregCompDAO;
import br.com.usinasantafe.pmm.model.dao.LeiraDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.ProdutoDAO;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Json;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class CompostoCTR {

    public CompostoCTR() {
    }

    public boolean verLeira(Long codLeira){
        LeiraDAO leiraDAO = new LeiraDAO();
        return leiraDAO.verLeiraCod(codLeira);
    }

    public LeiraBean getLeiraCod(Long codLeira){
        LeiraDAO leiraDAO = new LeiraDAO();
        return leiraDAO.getLeiraCod(codLeira);
    }

    public boolean pesqLeiraExibir(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.verLeiraExibir();
    }

    public void abrirCarregInsumo(ProdutoBean produtoBean){
        MotoMecFertCTR motoMecFuncCTR = new MotoMecFertCTR();
        ConfigCTR configCTR = new ConfigCTR();
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        carregCompDAO.abrirCarregInsumo(motoMecFuncCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert(), configCTR.getConfig().getEquipConfig(), produtoBean.getIdProduto());
    }

    public void abrirCarregComposto(Long codLeira){
        MotoMecFertCTR motoMecFuncCTR = new MotoMecFertCTR();
        ConfigCTR configCTR = new ConfigCTR();
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        carregCompDAO.abrirCarregComposto(motoMecFuncCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert(), configCTR.getConfig().getEquipConfig(), getLeiraCod(codLeira).getIdLeira());
    }

    public void salvarLeiraDescarreg(Long codLeira){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        carregCompDAO.salvarLeiraDescarreg(getLeiraCod(codLeira).getIdLeira());
    }

    public String dadosEnvioCarreg(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.dadosEnvioCarreg();
    }

    public String dadosEnvioLeiraDescarreg(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.dadosEnvioLeiraDescarreg();
    }

    public boolean verProduto(String codProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.verProduto(codProduto);
    }

    public ProdutoBean getProduto(String codProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.getProduto(codProduto);
    }

    public CarregCompBean getOrdCarreg(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.getOrdCarreg();
    }

    public void verifDadosCarreg(Context telaAtual, Class telaProx){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        ConfigCTR configCTR = new ConfigCTR();
        carregCompDAO.verifDadosCarreg(configCTR.getConfig().getEquipConfig(), telaAtual, telaProx);
    }

    public void receberVerifOrdCarreg(String result) {
        try {

            if (!result.contains("exceeded")) {

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(result);

                if (jsonArray.length() > 0) {
                    CarregCompDAO carregCompDAO = new CarregCompDAO();
                    carregCompDAO.recebOrdCarreg(jsonArray);
                }
                else{
                    VerifDadosServ.status = 1;
                }

            } else {
                VerifDadosServ.status = 1;
            }

        } catch (Exception e) {
            VerifDadosServ.status = 1;
            LogErroDAO.getInstance().insert(e);
        }

    }

    public void updCarregInsumo(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;

            String obj = retorno.substring(pos1);

            Json json = new Json();
            CarregCompDAO carregCompDAO = new CarregCompDAO();
            carregCompDAO.updCarregInsumo(json.jsonArray(obj));

            EnvioDadosServ.getInstance().envioDados(4);

        }
        catch(Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insert(e);
        }

    }

    public void updCarregLeiraDescarreg(String result) {

        try {

            if (!result.contains("exceeded")) {

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(result);

                if (jsonArray.length() > 0) {

                    CarregCompDAO carregCompDAO = new CarregCompDAO();
                    carregCompDAO.updCarregLeiraDescarreg(jsonArray);

                }

                EnvioDadosServ.getInstance().envioDados(5);

            } else {
                EnvioDadosServ.status = 1;
            }

        } catch (Exception e) {
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insert(e);
        }

    }

    public boolean verifEnvioCarreg(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.verEnvioCarreg();
    }

    public boolean verifEnvioLeiraDescarreg(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.verEnvioLeiraDescarreg();
    }

}
