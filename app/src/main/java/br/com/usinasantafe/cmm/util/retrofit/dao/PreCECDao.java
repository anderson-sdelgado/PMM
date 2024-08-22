package br.com.usinasantafe.cmm.util.retrofit.dao;

import br.com.usinasantafe.cmm.model.bean.variaveis.PreCECBean;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PreCECDao {

    @POST("inserirprecec.php")
    Call<PreCECBean> envioDadosPreCEC(@Body PreCECBean prececBean, @Header("Authorization") String auth);

}
