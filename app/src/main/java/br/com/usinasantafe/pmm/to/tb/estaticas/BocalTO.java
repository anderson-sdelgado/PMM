package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbbocalest")
public class BocalTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idBocal;
    @DatabaseField
    private Long codBocal;
    @DatabaseField
    private String descrBocal;

    public BocalTO() {
    }

    public Long getIdBocal() {
        return idBocal;
    }

    public void setIdBocal(Long idBocal) {
        this.idBocal = idBocal;
    }

    public Long getCodBocal() {
        return codBocal;
    }

    public void setCodBocal(Long codBocal) {
        this.codBocal = codBocal;
    }

    public String getDescrBocal() {
        return descrBocal;
    }

    public void setDescrBocal(String descrBocal) {
        this.descrBocal = descrBocal;
    }

}
