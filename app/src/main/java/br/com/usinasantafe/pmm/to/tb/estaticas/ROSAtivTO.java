package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 05/05/2017.
 */
@DatabaseTable(tableName="tbrosativest")
public class ROSAtivTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idROSAtiv;
    @DatabaseField
    private Long nroOS;
    @DatabaseField
    private Long codAtiv;

    public ROSAtivTO() {
    }

    public Long getIdROSAtiv() {
        return idROSAtiv;
    }

    public void setIdROSAtiv(Long idROSAtiv) {
        this.idROSAtiv = idROSAtiv;
    }

    public Long getNroOS() {
        return nroOS;
    }

    public void setNroOS(Long nroOS) {
        this.nroOS = nroOS;
    }

    public Long getCodAtiv() {
        return codAtiv;
    }

    public void setCodAtiv(Long codAtiv) {
        this.codAtiv = codAtiv;
    }
}
