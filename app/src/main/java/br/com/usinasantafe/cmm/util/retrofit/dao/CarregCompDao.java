package br.com.usinasantafe.cmm.util.retrofit.dao;

import br.com.usinasantafe.cmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CarregCompDao {

    @POST("retcarreg.php")
    Call<CarregCompBean> receberDadosCarregComp(@Body ConfigBean configBean, @Header("Authorization") String auth);

}
