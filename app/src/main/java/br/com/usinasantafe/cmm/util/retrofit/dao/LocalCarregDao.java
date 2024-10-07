package br.com.usinasantafe.cmm.util.retrofit.dao;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.LocalCarregBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LocalCarregDao {

    @POST("retlocalcarreg.php")
    Call<List<LocalCarregBean>> receberDadosLocalCarreg(@Body ConfigBean configBean, @Header("Authorization") String auth);

}
