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
    private Long idCab;
    @DatabaseField
    private Long equipCab;
    @DatabaseField
    private String dtCab;
    @DatabaseField
    private Long funcCab;
    @DatabaseField
    private Long turnoCab;
    @DatabaseField
    private Long statusCab;  //1 - Aberto; 2 - Encerrado
    @DatabaseField
    private Long qtdeItemCab;
    @DatabaseField
    private String dtAtualCab;

    public CabecCheckListTO() {
    }

    public Long getIdCab() {
        return idCab;
    }

    public Long getEquipCab() {
        return equipCab;
    }

    public void setEquipCab(Long equipCab) {
        this.equipCab = equipCab;
    }

    public String getDtCab() {
        return dtCab;
    }

    public void setDtCab(String dtCab) {
        this.dtCab = dtCab;
    }

    public Long getFuncCab() {
        return funcCab;
    }

    public void setFuncCab(Long funcCab) {
        this.funcCab = funcCab;
    }

    public Long getTurnoCab() {
        return turnoCab;
    }

    public void setTurnoCab(Long turnoCab) {
        this.turnoCab = turnoCab;
    }

    public Long getStatusCab() {
        return statusCab;
    }

    public void setStatusCab(Long statusCab) {
        this.statusCab = statusCab;
    }

    public Long getQtdeItemCab() {
        return qtdeItemCab;
    }

    public void setQtdeItemCab(Long qtdeItemCab) {
        this.qtdeItemCab = qtdeItemCab;
    }

    public String getDtAtualCab() {
        return dtAtualCab;
    }

    public void setDtAtualCab(String dtAtualCab) {
        this.dtAtualCab = dtAtualCab;
    }
}
