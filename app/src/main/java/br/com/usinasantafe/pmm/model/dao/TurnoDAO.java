package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.estaticas.TurnoBean;

public class TurnoDAO {

    public TurnoDAO() {
    }

    public List getTurnoList(Long codTurno){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.get("codTurno", codTurno);
    }

}
