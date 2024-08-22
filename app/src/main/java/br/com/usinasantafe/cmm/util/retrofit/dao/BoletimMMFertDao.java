package br.com.usinasantafe.cmm.util.retrofit.dao;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BoletimMMFertDao {

    @POST("inserirboletimmmfert.php")
    Call<List<BoletimMMFertBean>> envioDadosBoletimMMFert(@Body List<BoletimMMFertBean> boletimMMFertList, @Header("Authorization") String auth);

}
