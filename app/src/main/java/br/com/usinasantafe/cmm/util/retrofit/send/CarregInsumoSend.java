package br.com.usinasantafe.cmm.util.retrofit.send;

import br.com.usinasantafe.cmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.util.retrofit.ConnRetrofit;
import br.com.usinasantafe.cmm.util.retrofit.dao.CarregInsumoDao;
import retrofit2.Call;
import retrofit2.Response;

public class CarregInsumoSend {

    public CarregInsumoSend() {
    }

    public CarregCompBean envioDadoCarregInsumo(CarregCompBean carregCompBean) throws Exception {
        try {
            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            CarregInsumoDao carregInsumoDao = ConnRetrofit.getInstance().conn().create(CarregInsumoDao.class);
            Call<CarregCompBean> call = carregInsumoDao.envioDadosCarregInsumo(carregCompBean, "Bearer " + atualAplicDAO.token());
            return call.execute().body();
        } catch (Exception e){
            throw new Exception(e);
        }
    }

}
