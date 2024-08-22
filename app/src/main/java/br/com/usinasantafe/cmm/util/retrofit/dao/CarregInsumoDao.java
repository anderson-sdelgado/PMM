package br.com.usinasantafe.cmm.util.retrofit.dao;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.CarregCompBean;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CarregInsumoDao {

    @POST("inserircarreg.php")
    Call<CarregCompBean> envioDadosCarregInsumo(@Body CarregCompBean carregComp, @Header("Authorization") String auth);

}
