package br.com.usinasantafe.cmm.util.retrofit.send;

import android.util.Log;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.util.retrofit.ConnRetrofit;
import br.com.usinasantafe.cmm.util.retrofit.dao.BoletimMMFertDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoletimMMFertSend {

    public BoletimMMFertSend() {
    }

    public List<BoletimMMFertBean> envioDadoMotoMecFert(List<BoletimMMFertBean> boletimMMFertList) throws Exception {
        try {
            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            BoletimMMFertDao boletimMMFertDao = ConnRetrofit.getInstance().conn().create(BoletimMMFertDao.class);
            Call<List<BoletimMMFertBean>> call = boletimMMFertDao.envioDadosBoletimMMFert(boletimMMFertList, "Bearer " + atualAplicDAO.token());
            return call.execute().body();
        } catch (Exception e){
            throw new Exception(e);
        }

    }

}
