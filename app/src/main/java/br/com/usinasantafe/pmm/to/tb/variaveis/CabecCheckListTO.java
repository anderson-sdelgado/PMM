package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 31/03/2017.
 */
@DatabaseTable(tableName="tbcabchecklistvar")
public class CabecCheckListTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecCheckList;
    @DatabaseField
    private Long equipCabecCheckList;
    @DatabaseField
    private String dtCabecCheckList;
    @DatabaseField
    private Long funcCabecCheckList;
    @DatabaseField
    private Long turnoCabecCheckList;
    @DatabaseField
    private Long statusCabecCheckList;  //1 - Aberto; 2 - Encerrado
    @DatabaseField
    private Long qtdeItemCabecCheckList;
    @DatabaseField
    private String dtAtualCheckList;

    public CabecCheckListTO() {
    }

    public Long getIdCabecCheckList() {
        return idCabecCheckList;
    }

//    public void setIdCabecCheckList(Long idCabecCheckList) {
//        this.idCabecCheckList = idCabecCheckList;
//    }

    public Long getEquipCabecCheckList() {
        return equipCabecCheckList;
    }

    public void setEquipCabecCheckList(Long equipCabecCheckList) {
        this.equipCabecCheckList = equipCabecCheckList;
    }

    public String getDtCabecCheckList() {
        return dtCabecCheckList;
    }

    public void setDtCabecCheckList(String dtCabecCheckList) {
        this.dtCabecCheckList = dtCabecCheckList;
    }

    public Long getFuncCabecCheckList() {
        return funcCabecCheckList;
    }

    public void setFuncCabecCheckList(Long funcCabecCheckList) {
        this.funcCabecCheckList = funcCabecCheckList;
    }

    public Long getTurnoCabecCheckList() {
        return turnoCabecCheckList;
    }

    public void setTurnoCabecCheckList(Long turnoCabecCheckList) {
        this.turnoCabecCheckList = turnoCabecCheckList;
    }

    public Long getStatusCabecCheckList() {
        return statusCabecCheckList;
    }

    public void setStatusCabecCheckList(Long statusCabecCheckList) {
        this.statusCabecCheckList = statusCabecCheckList;
    }

    public Long getQtdeItemCabecCheckList() {
        return qtdeItemCabecCheckList;
    }

    public void setQtdeItemCabecCheckList(Long qtdeItemCabecCheckList) {
        this.qtdeItemCabecCheckList = qtdeItemCabecCheckList;
    }

    public String getDtAtualCheckList() {
        return dtAtualCheckList;
    }

    public void setDtAtualCheckList(String dtAtualCheckList) {
        this.dtAtualCheckList = dtAtualCheckList;
    }
}
