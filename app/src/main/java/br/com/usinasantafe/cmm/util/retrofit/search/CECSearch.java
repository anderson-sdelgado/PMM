package br.com.usinasantafe.cmm.util.retrofit.search;

import br.com.usinasantafe.cmm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.util.retrofit.ConnRetrofit;
import br.com.usinasantafe.cmm.util.retrofit.dao.CECDao;
import retrofit2.Call;
import retrofit2.Response;

public class CECSearch {

    public CECSearch() {
    }

    public CECBean receberDadoCEC(ConfigBean configBean) throws Exception {
        try {
            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            CECDao cecDao = ConnRetrofit.getInstance().conn().create(CECDao.class);
            Call<CECBean> call = cecDao.receberDadosCEC(configBean, "Bearer " + atualAplicDAO.token());
            return call.execute().body();
        } catch (Exception e){
            throw new Exception(e);
        }
    }

}
