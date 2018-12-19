package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 30/03/2017.
 */

@DatabaseTable(tableName="tbrespitemclvar")
public class RespItemCheckListTO extends Entidade {

    private static final long serialVersionUID = 1L;

//    @DatabaseField(id=true)
    @DatabaseField(generatedId=true)
    private Long idIt;
    @DatabaseField
    private Long idItBDIt;
    @DatabaseField
    private Long idCabIt;
    @DatabaseField
    private Long opIt;

    public RespItemCheckListTO() {
    }

    public Long getIdIt() {
        return idIt;
    }

//    public void setIdItemCheckList(Long idItemCheckList) {
//        this.idItemCheckList = idItemCheckList;
//    }

    public Long getIdItBDIt() {
        return idItBDIt;
    }

    public void setIdItBDIt(Long idItBDIt) {
        this.idItBDIt = idItBDIt;
    }

    public Long getIdCabIt() {
        return idCabIt;
    }

    public void setIdCabIt(Long idCabIt) {
        this.idCabIt = idCabIt;
    }

    public Long getOpIt() {
        return opIt;
    }

    public void setOpIt(Long opIt) {
        this.opIt = opIt;
    }
}
