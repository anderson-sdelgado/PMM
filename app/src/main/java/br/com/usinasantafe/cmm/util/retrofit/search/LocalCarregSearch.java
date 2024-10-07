package br.com.usinasantafe.cmm.util.retrofit.search;

import android.util.Log;

import java.util.List;

import br.com.usinasantafe.cmm.control.MotoMecFertCTR;
import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.LocalCarregBean;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.model.dao.LogErroDAO;
import br.com.usinasantafe.cmm.util.VerifDadosServ;
import br.com.usinasantafe.cmm.util.retrofit.ConnRetrofit;
import br.com.usinasantafe.cmm.util.retrofit.dao.LocalCarregDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalCarregSearch {

    public LocalCarregSearch() {
    }

    public void receberDadoLocalCarreg(ConfigBean configBean) {
        try {
            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            LocalCarregDao localCarregDao = ConnRetrofit.getInstance().conn().create(LocalCarregDao.class);
            Call<List<LocalCarregBean>> call = localCarregDao.receberDadosLocalCarreg(configBean, "Bearer " + atualAplicDAO.token());
            call.enqueue(new Callback<>() {

                @Override
                public void onResponse(Call<List<LocalCarregBean>> call, Response<List<LocalCarregBean>> response) {
                    MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
                    motoMecFertCTR.receberLocalCarreg(response.body());
                }

                @Override
                public void onFailure(Call<List<LocalCarregBean>> call, Throwable t) {
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
