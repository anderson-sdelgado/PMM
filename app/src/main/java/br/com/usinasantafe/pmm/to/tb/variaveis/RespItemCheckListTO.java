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
    private Long idItemCheckList;
    @DatabaseField
    private Long idItItemCheckList;
    @DatabaseField
    private Long idCabecItemCheckList;
    @DatabaseField
    private Long opcaoItemCheckList;

    public RespItemCheckListTO() {
    }

    public Long getIdItemCheckList() {
        return idItemCheckList;
    }

//    public void setIdItemCheckList(Long idItemCheckList) {
//        this.idItemCheckList = idItemCheckList;
//    }

    public Long getIdItItemCheckList() {
        return idItItemCheckList;
    }

    public void setIdItItemCheckList(Long idItItemCheckList) {
        this.idItItemCheckList = idItItemCheckList;
    }

    public Long getIdCabecItemCheckList() {
        return idCabecItemCheckList;
    }

    public void setIdCabecItemCheckList(Long idCabecItemCheckList) {
        this.idCabecItemCheckList = idCabecItemCheckList;
    }

    public Long getOpcaoItemCheckList() {
        return opcaoItemCheckList;
    }

    public void setOpcaoItemCheckList(Long opcaoItemCheckList) {
        this.opcaoItemCheckList = opcaoItemCheckList;
    }
}
