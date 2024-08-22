package br.com.usinasantafe.cmm.util.retrofit.send;

import java.util.List;

import br.com.usinasantafe.cmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.util.retrofit.ConnRetrofit;
import br.com.usinasantafe.cmm.util.retrofit.dao.CheckListDao;
import retrofit2.Call;
import retrofit2.Response;

public class CheckListSend {

    public CheckListSend() {
    }

    public List<CabecCheckListBean> envioDadoCheckList(List<CabecCheckListBean> cabecCheckListList) throws Exception {
        try {
            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            CheckListDao checkListDao = ConnRetrofit.getInstance().conn().create(CheckListDao.class);
            Call<List<CabecCheckListBean>> call = checkListDao.envioDadosCheckList(cabecCheckListList, "Bearer " + atualAplicDAO.token());
            return call.execute().body();
        } catch (Exception e){
            throw new Exception(e);
        }
    }

}
