package br.com.usinasantafe.cmm.util.retrofit.search;

import android.os.AsyncTask;

import br.com.usinasantafe.cmm.control.CompostoCTR;
import br.com.usinasantafe.cmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.model.dao.LogErroDAO;
import br.com.usinasantafe.cmm.util.VerifDadosServ;
import br.com.usinasantafe.cmm.util.retrofit.ConnRetrofit;
import br.com.usinasantafe.cmm.util.retrofit.dao.CarregCompDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarregCompSearch {

    public CarregCompSearch() {
    }

    public void receberDadoCarregComp(ConfigBean configBean) {
        try {
            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            CarregCompDao carregCompDao = ConnRetrofit.getInstance().conn().create(CarregCompDao.class);
            Call<CarregCompBean> call = carregCompDao.receberDadosCarregComp(configBean, "Bearer " + atualAplicDAO.token());
            call.enqueue(new Callback<>() {

                @Override
                public void onResponse(Call<CarregCompBean> call, Response<CarregCompBean> response) {
                    CompostoCTR compostoCTR = new CompostoCTR();
                    compostoCTR.receberCarregComp(response.body());
                }

                @Override
                public void onFailure(Call<CarregCompBean> call, Throwable t) {
                    LogErroDAO.getInstance().insertLogErro(t);
                    VerifDadosServ.getInstance().msg("FALHA NA PESQUISA DE LOCAL! POR FAVOR, TENTAR NOVAMENTE EM UM PONTO COM SINAL MELHOR.");
                }
            });
        } catch (Exception e){
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().msg("FALHA NA PESQUISA DE ORDEM DE CARREGAMENTO! POR FAVOR, TENTAR NOVAMENTE EM UM PONTO COM SINAL MELHOR.");
        }
    }

}
