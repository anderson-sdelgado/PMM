package br.com.usinasantafe.cmm.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.cmm.model.pst.Entidade;

/**
 * Created by anderson on 05/05/2017.
 */
@DatabaseTable(tableName="tbrosativest")
public class ROSAtivBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idROSAtiv;
    @DatabaseField
    private Long idOS;
    @DatabaseField
    private Long idAtiv;

    public ROSAtivBean() {
    }

    public Long getIdROSAtiv() {
        return idROSAtiv;
    }

    public void setIdROSAtiv(Long idROSAtiv) {
        this.idROSAtiv = idROSAtiv;
    }

    public Long getIdOS() {
        return idOS;
    }

    public void setIdOS(Long idOS) {
        this.idOS = idOS;
    }

    public Long getIdAtiv() {
        return idAtiv;
    }

    public void setIdAtiv(Long idAtiv) {
        this.idAtiv = idAtiv;
    }
}
