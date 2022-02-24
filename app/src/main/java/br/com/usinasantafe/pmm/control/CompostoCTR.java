package br.com.usinasantafe.pmm.control;

import android.content.Context;

import org.json.JSONArray;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.LeiraBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.pmm.model.dao.CarregCompDAO;
import br.com.usinasantafe.pmm.model.dao.LeiraDAO;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.model.dao.ProdutoDAO;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Json;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class CompostoCTR {

    private Long tipoMovLeira;

    public CompostoCTR() {
    }

    public Long getTipoMovLeira() {
        return tipoMovLeira;
    }

    public void setTipoMovLeira(Long tipoMovLeira) {
        this.tipoMovLeira = tipoMovLeira;
    }

    public boolean verLeira(Long codLeira){
        LeiraDAO leiraDAO = new LeiraDAO();
        return leiraDAO.verLeiraCod(codLeira);
    }

    public LeiraBean getLeiraCod(Long codLeira){
        LeiraDAO leiraDAO = new LeiraDAO();
        return leiraDAO.getLeiraCod(codLeira);
    }

    public LeiraBean getLeiraId(Long idLeira){
        LeiraDAO leiraDAO = new LeiraDAO();
        return leiraDAO.getLeiraId(idLeira);
    }

    public List<LeiraBean> leiraStatusList(Long tipoMovLeira){
        LeiraDAO leiraDAO = new LeiraDAO();
        return leiraDAO.leiraStatusList(tipoMovLeira);
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
        carregCompDAO.abrirCarregComposto(motoMecFuncCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert(), configCTR.getConfig().getEquipConfig(), getLeiraCod(codLeira).getIdLeira(), configCTR.getOS().getIdOS());
    }

    public void updateLeira(LeiraBean leiraBean, Long tipoMovLeira){
        LeiraDAO leiraDAO = new LeiraDAO();
        leiraDAO.updateLeira(leiraBean, tipoMovLeira);
    }

    public void salvarLeiraDescarreg(Long codLeira){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        carregCompDAO.salvarLeiraDescarreg(getLeiraCod(codLeira).getIdLeira());
    }

    public String dadosEnvioCarreg(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.dadosEnvioCarregInsumo();
    }

    public String dadosEnvioLeiraDescarreg(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.dadosEnvioCarregComposto();
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

    public CarregCompBean getOrdCarregLeira(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.getOrdCarregLeira();
    }

    public boolean verOrdemCarreg(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.verOrdemCarreg();
    }

    public void verifDadosCarreg(Context telaAtual, Class telaProx, String activity){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        ConfigCTR configCTR = new ConfigCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("configCTR.setStatusRetVerif(1L);\n" +
                "        carregCompDAO.verifDadosCarreg(configCTR.getConfig().getEquipConfig(), telaAtual, telaProx, activity);", activity);
        configCTR.setStatusRetVerif(1L);
        carregCompDAO.verifDadosCarreg(configCTR.getConfig().getEquipConfig(), telaAtual, telaProx, activity);
    }

    public void receberVerifOrdCarreg(String result, String activity) {

        try {

            if (!result.contains("exceeded")) {

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(result);

                if (jsonArray.length() > 0) {

                    CarregCompDAO carregCompDAO = new CarregCompDAO();
                    carregCompDAO.recebOrdCarreg(jsonArray);

                    ConfigCTR configCTR = new ConfigCTR();
                    configCTR.setStatusRetVerif(0L);

                }
                else{
                    VerifDadosServ.status = 1;
                }

            } else {
                VerifDadosServ.status = 1;
            }

        } catch (Exception e) {
            VerifDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void updCarregInsumo(String retorno, String activity) {

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String obj = retorno.substring(pos1);

            Json json = new Json();
            CarregCompDAO carregCompDAO = new CarregCompDAO();
            carregCompDAO.updCarregInsumo(json.jsonArray(obj));

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch(Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void updCarregCompostoDescarreg(String result, String activity) {

        try {

            if (!result.contains("exceeded")) {

                int pos1 = result.indexOf("_") + 1;
                String obj = result.substring(pos1);
                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(obj);

                if (jsonArray.length() > 0) {
                    CarregCompDAO carregCompDAO = new CarregCompDAO();
                    carregCompDAO.updCarregCompostoDescarreg(jsonArray);
                }

                EnvioDadosServ.getInstance().envioDados(activity);

            } else {
                EnvioDadosServ.status = 1;
            }

        } catch (Exception e) {
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public boolean verifEnvioCarregInsumo(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.verEnvioCarreg();
    }

    public boolean verifEnvioLeiraDescarreg(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        return carregCompDAO.verEnvioLeiraDescarreg();
    }

    public void deleteCarregComp(){
        CarregCompDAO carregCompDAO = new CarregCompDAO();
        carregCompDAO.deleteCarregComp();
    }

}
