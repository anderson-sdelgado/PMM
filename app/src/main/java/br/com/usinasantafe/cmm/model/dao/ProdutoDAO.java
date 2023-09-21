package br.com.usinasantafe.cmm.model.dao;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.estaticas.ProdutoBean;

public class ProdutoDAO {

    public ProdutoDAO() {
    }

    public boolean verProduto(String codProduto){
        ProdutoBean produtoBean = new ProdutoBean();
        List<ProdutoBean> produtoList = produtoBean.get("codProduto", codProduto);
        boolean ret = produtoList.size() > 0;
        produtoList.clear();
        return ret;
    }

    public ProdutoBean getProduto(String codProduto){
        ProdutoBean produtoBean = new ProdutoBean();
        List<ProdutoBean> produtoList = produtoBean.get("codProduto", codProduto);
        produtoBean = produtoList.get(0);
        produtoList.clear();
        return produtoBean;
    }

}
