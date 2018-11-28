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

    @DatabaseField(id=true)
    private Long idEquipAtiv;
    @DatabaseField
    private Long codEquip;
    @DatabaseField
    private Long codAtiv;

    public REquipAtivTO() {
    }

    public Long getIdEquipAtiv() {
        return idEquipAtiv;
    }

    public void setIdEquipAtiv(Long idEquipAtiv) {
        this.idEquipAtiv = idEquipAtiv;
    }

    public Long getCodEquip() {
        return codEquip;
    }

    public void setCodEquip(Long codEquip) {
        this.codEquip = codEquip;
    }

    public Long getCodAtiv() {
        return codAtiv;
    }

    public void setCodAtiv(Long codAtiv) {
        this.codAtiv = codAtiv;
    }

}
