package br.com.usinasantafe.cmm.util.retrofit.send;

import br.com.usinasantafe.cmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.util.retrofit.ConnRetrofit;
import br.com.usinasantafe.cmm.util.retrofit.dao.PreCECDao;
import retrofit2.Call;
import retrofit2.Response;

public class PreCECSend {

    public PreCECSend() {
    }

    public PreCECBean envioDadoPreCEC(PreCECBean preCECBean) throws Exception {
        try {
            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            PreCECDao preCECDao = ConnRetrofit.getInstance().conn().create(PreCECDao.class);
            Call<PreCECBean> call = preCECDao.envioDadosPreCEC(preCECBean, "Bearer " + atualAplicDAO.token());
            return call.execute().body();
        } catch (Exception e){
            throw new Exception(e);
        }
    }

}
