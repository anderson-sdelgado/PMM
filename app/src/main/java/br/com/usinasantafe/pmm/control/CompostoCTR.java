package br.com.usinasantafe.pmm.control;

import android.content.Context;

import br.com.usinasantafe.pmm.model.bean.estaticas.LeiraBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CarregBean;
import br.com.usinasantafe.pmm.model.dao.CarregDAO;
import br.com.usinasantafe.pmm.model.dao.LeiraDAO;
import br.com.usinasantafe.pmm.model.dao.ProdutoDAO;

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
        CarregDAO carregDAO = new CarregDAO();
        return carregDAO.verLeiraExibir();
    }

    public void abrirCarregInsumo(Context context, ProdutoBean produtoBean){
        MotoMecFertCTR motoMecFuncCTR = new MotoMecFertCTR();
        ConfigCTR configCTR = new ConfigCTR();
        CarregDAO carregDAO = new CarregDAO();
        carregDAO.abrirCarregInsumo(motoMecFuncCTR.getBoletimMMFertAberto().getMatricFuncBolMMFert(), configCTR.getConfig().getEquipConfig(), produtoBean.getIdProduto(), context);
    }

    public void salvarLeiraDescarreg(Long codLeira, Context context){
        CarregDAO carregDAO = new CarregDAO();
        carregDAO.salvarLeiraDescarreg(getLeiraCod(codLeira).getIdLeira(), context);
    }

    public String dadosEnvioCarregInsumo(){
        CarregDAO carregDAO = new CarregDAO();
        return carregDAO.dadosEnvioCarregInsumo();
    }

    public String dadosEnvioLeiraDescarreg(){
        CarregDAO carregDAO = new CarregDAO();
        return carregDAO.dadosEnvioLeiraDescarreg();
    }

    public boolean verProduto(String codProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.verProduto(codProduto);
    }

    public ProdutoBean getProduto(String codProduto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.getProduto(codProduto);
    }

    public CarregBean getOrdCarreg(){
        CarregDAO carregDAO = new CarregDAO();
        return carregDAO.getOrdCarreg();
    }

    public void pesqCarregComposto(Context telaAtual, Class telaProx){
        CarregDAO carregDAO = new CarregDAO();
        ConfigCTR configCTR = new ConfigCTR();
        carregDAO.pesqCarregComposto(configCTR.getConfig().getEquipConfig(), telaAtual, telaProx);
    }

    public void pesqCarregProduto(Context telaAtual, Class telaProx){
        CarregDAO carregDAO = new CarregDAO();
        ConfigCTR configCTR = new ConfigCTR();
        carregDAO.pesqCarregProduto(configCTR.getConfig().getEquipConfig(), telaAtual, telaProx);
    }

    public void updateCarregInsumo(String retorno) {
        CarregDAO carregDAO = new CarregDAO();
        carregDAO.updCarregInsumo(retorno);
    }

    public void updCarregLeiraDescarreg(String result) {
        CarregDAO carregDAO = new CarregDAO();
        carregDAO.updCarregLeiraDescarreg(result);
    }

    public boolean verEnviaCarregInsumo(){
        CarregDAO carregDAO = new CarregDAO();
        return carregDAO.verEnviaCarregInsumo();
    }

    public boolean verEnvioLeiraDescarreg(){
        CarregDAO carregDAO = new CarregDAO();
        return carregDAO.verEnvioLeiraDescarreg();
    }

    public void recebOrdCarreg(String result) {
        CarregDAO carregDAO = new CarregDAO();
        carregDAO.recebOrdCarreg(result);
    }

}
