package br.com.usinasantafe.pmm.to.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 31/03/2017.
 */
@DatabaseTable(tableName="tbcabclvar")
public class CabecCLTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabCL;
    @DatabaseField
    private Long equipCabCL;
    @DatabaseField
    private String dtCabCL;
    @DatabaseField
    private Long funcCabCL;
    @DatabaseField
    private Long turnoCabCL;
    @DatabaseField
    private Long statusCabCL;  //1 - Aberto; 2 - Encerrado
    @DatabaseField
    private Long qtdeItemCabCL;
    @DatabaseField
    private String dtAtualCabCL;

    public CabecCLTO() {
    }

    public Long getIdCabCL() {
        return idCabCL;
    }

    public Long getEquipCabCL() {
        return equipCabCL;
    }

    public void setEquipCabCL(Long equipCabCL) {
        this.equipCabCL = equipCabCL;
    }

    public String getDtCabCL() {
        return dtCabCL;
    }

    public void setDtCabCL(String dtCabCL) {
        this.dtCabCL = dtCabCL;
    }

    public Long getFuncCabCL() {
        return funcCabCL;
    }

    public void setFuncCabCL(Long funcCabCL) {
        this.funcCabCL = funcCabCL;
    }

    public Long getTurnoCabCL() {
        return turnoCabCL;
    }

    public void setTurnoCabCL(Long turnoCabCL) {
        this.turnoCabCL = turnoCabCL;
    }

    public Long getStatusCabCL() {
        return statusCabCL;
    }

    public void setStatusCabCL(Long statusCabCL) {
        this.statusCabCL = statusCabCL;
    }

    public Long getQtdeItemCabCL() {
        return qtdeItemCabCL;
    }

    public void setQtdeItemCabCL(Long qtdeItemCabCL) {
        this.qtdeItemCabCL = qtdeItemCabCL;
    }

    public String getDtAtualCabCL() {
        return dtAtualCabCL;
    }

    public void setDtAtualCabCL(String dtAtualCabCL) {
        this.dtAtualCabCL = dtAtualCabCL;
    }
}
