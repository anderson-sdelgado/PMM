package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 28/04/2017.
 */

@DatabaseTable(tableName="tbrequipativest")
public class REquipAtivTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idEquipAtiv;
    @DatabaseField
    private Long idEquip;
    @DatabaseField
    private Long idAtiv;

    public REquipAtivTO() {
    }

    public Long getIdEquipAtiv() {
        return idEquipAtiv;
    }

    public void setIdEquipAtiv(Long idEquipAtiv) {
        this.idEquipAtiv = idEquipAtiv;
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public Long getIdAtiv() {
        return idAtiv;
    }

    public void setIdAtiv(Long idAtiv) {
        this.idAtiv = idAtiv;
    }
}
