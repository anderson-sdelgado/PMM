package br.com.usinasantafe.cmm.util.retrofit.dao;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.CabecCheckListBean;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CheckListDao {

    @POST("inserirchecklist.php")
    Call<List<CabecCheckListBean>> envioDadosCheckList(@Body List<CabecCheckListBean> cabecCheckListBean, @Header("Authorization") String auth);

}
