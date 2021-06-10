package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.TurnoBean;

public class TurnoDAO {

    public TurnoDAO() {
    }

    public List<TurnoBean> getTurnoCodList(Long codTurno){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.get("codTurno", codTurno);
    }

    public TurnoBean getTurnoId(Long idTurno){
        List<TurnoBean> turnoList = getTurnoIdList(idTurno);
        TurnoBean turnoBean = turnoList.get(0);
        turnoList.clear();
        return turnoBean;
    }

    public List<TurnoBean> getTurnoIdList(Long idTurno){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.get("idTurno", idTurno);
    }

}
