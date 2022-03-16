package br.com.usinasantafe.pmm.model.dao;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.ServicoBean;

public class ServicoDAO {

    public ServicoDAO() {
    }

    public ServicoBean getServico(Long idServItemOS){

        ServicoBean servicoBean = new ServicoBean();
        List<ServicoBean> servicoList = servicoBean.get("idServico", idServItemOS);
        if(servicoList.size() > 0){
            servicoBean = servicoList.get(0);
        }
        else{
            servicoBean.setDescrServico("SERVIÇO INEXISTENTE");
        }
        servicoList.clear();

        return servicoBean;

    }

}
