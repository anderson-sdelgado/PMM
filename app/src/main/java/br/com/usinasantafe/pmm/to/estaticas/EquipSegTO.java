package br.com.usinasantafe.pmm.to.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 01/03/2018.
 */
@DatabaseTable(tableName="tbequipsegest")
public class EquipSegTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEquip;
    @DatabaseField
    private Long nroEquip;
    @DatabaseField
    private Long codClasseEquip;
    @DatabaseField
    private String descrClasseEquip;
    @DatabaseField
    private Long tipoEquip; //1 - Carretel; 2 - Transbordo; 3 - Implemento

    public EquipSegTO() {
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public Long getNroEquip() {
        return nroEquip;
    }

    public void setNroEquip(Long nroEquip) {
        this.nroEquip = nroEquip;
    }

    public Long getCodClasseEquip() {
        return codClasseEquip;
    }

    public void setCodClasseEquip(Long codClasseEquip) {
        this.codClasseEquip = codClasseEquip;
    }

    public String getDescrClasseEquip() {
        return descrClasseEquip;
    }

    public void setDescrClasseEquip(String descrClasseEquip) {
        this.descrClasseEquip = descrClasseEquip;
    }

    public Long getTipoEquip() {
        return tipoEquip;
    }

    public void setTipoEquip(Long tipoEquip) {
        this.tipoEquip = tipoEquip;
    }
}
