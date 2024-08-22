package br.com.usinasantafe.cmm.util.retrofit.dao;

import br.com.usinasantafe.cmm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.PreCECBean;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CECDao {

    @POST("retcec.php")
    Call<CECBean> receberDadosCEC(@Body ConfigBean configBean, @Header("Authorization") String auth);

}
