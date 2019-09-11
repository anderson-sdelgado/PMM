package br.com.usinasantafe.pmm.to.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 30/03/2017.
 */

@DatabaseTable(tableName="tbrespitemclvar")
public class RespItemCLTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItCL;
    @DatabaseField
    private Long idItBDItCL;
    @DatabaseField
    private Long idCabItCL;
    @DatabaseField
    private Long opItCL;

    public RespItemCLTO() {
    }

    public Long getIdItCL() {
        return idItCL;
    }

    public Long getIdItBDItCL() {
        return idItBDItCL;
    }

    public void setIdItBDItCL(Long idItBDItCL) {
        this.idItBDItCL = idItBDItCL;
    }

    public Long getIdCabItCL() {
        return idCabItCL;
    }

    public void setIdCabItCL(Long idCabItCL) {
        this.idCabItCL = idCabItCL;
    }

    public Long getOpItCL() {
        return opItCL;
    }

    public void setOpItCL(Long opItCL) {
        this.opItCL = opItCL;
    }
}
