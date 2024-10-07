package br.com.usinasantafe.cmm.util.retrofit.search;

import br.com.usinasantafe.cmm.control.CECCTR;
import br.com.usinasantafe.cmm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.model.dao.LogErroDAO;
import br.com.usinasantafe.cmm.util.VerifDadosServ;
import br.com.usinasantafe.cmm.util.retrofit.ConnRetrofit;
import br.com.usinasantafe.cmm.util.retrofit.dao.CECDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CECSearch {

    public CECSearch() {
    }

    public void receberDadoCEC(ConfigBean configBean) {
        try {
            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            CECDao cecDao = ConnRetrofit.getInstance().conn().create(CECDao.class);
            Call<CECBean> call = cecDao.receberDadosCEC(configBean, "Bearer " + atualAplicDAO.token());
            call.enqueue(new Callback<>() {

                @Override
                public void onResponse(Call<CECBean> call, Response<CECBean> response) {
                    CECCTR cecCTR = new CECCTR();
                    cecCTR.receberDadosCEC(response.body());
                }

                @Override
                public void onFailure(Call<CECBean> call, Throwable t) {
                    LogErroDAO.getInstance().insertLogErro(t);
                    VerifDadosServ.getInstance().msg("FALHA NA PESQUISA DE CEC! POR FAVOR, TENTAR NOVAMENTE EM UM PONTO COM SINAL MELHOR.");
                }
            });
        } catch (Exception e){
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().msg("FALHA NA PESQUISA DE ORDEM DE CARREGAMENTO! POR FAVOR, TENTAR NOVAMENTE EM UM PONTO COM SINAL MELHOR.");
        }
    }

}
