package br.com.usinasantafe.pmm.to.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 28/04/2017.
 */

@DatabaseTable(tableName="tbatividadeest")
public class AtividadeTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idAtiv;
    @DatabaseField
    private Long codAtiv;
    @DatabaseField
    private String descrAtiv;

    public AtividadeTO() {
    }

    public Long getIdAtiv() {
        return idAtiv;
    }

    public void setIdAtiv(Long idAtiv) {
        this.idAtiv = idAtiv;
    }

    public Long getCodAtiv() {
        return codAtiv;
    }

    public void setCodAtiv(Long codAtiv) {
        this.codAtiv = codAtiv;
    }

    public String getDescrAtiv() {
        return descrAtiv;
    }

    public void setDescrAtiv(String descrAtiv) {
        this.descrAtiv = descrAtiv;
    }

}
