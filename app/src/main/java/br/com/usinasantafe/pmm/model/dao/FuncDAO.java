package br.com.usinasantafe.pmm.model.dao;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.FuncBean;


public class FuncDAO {

    public FuncDAO() {
    }

    public boolean hasElements(){
        FuncBean funcBean = new FuncBean();
        return funcBean.hasElements();
    }

    public boolean verFunc(Long matricFunc){
        List<FuncBean> funcList = funcList(matricFunc);
        boolean ret = funcList.size() > 0;
        funcList.clear();
        return ret;
    }

    public FuncBean getFunc(Long matricColab){
        List<FuncBean> funcList = funcList(matricColab);
        FuncBean colabBean = (FuncBean) funcList.get(0);
        funcList.clear();
        return colabBean;
    }

    private List<FuncBean> funcList(Long matricFunc){
        FuncBean funcBean = new FuncBean();
        return funcBean.get("matricFunc", matricFunc);
    }

}
