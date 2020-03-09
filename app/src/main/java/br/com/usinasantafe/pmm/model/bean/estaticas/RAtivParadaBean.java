package br.com.usinasantafe.pmm.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

/**
 * Created by anderson on 05/05/2017.
 */
@DatabaseTable(tableName="tbrativparest")
public class RAtivParadaBean extends Entidade {

    @DatabaseField(generatedId=true)
    private Long idRAtivParada;
    @DatabaseField
    private Long idAtiv;
    @DatabaseField
    private Long idParada;

    public RAtivParadaBean() {
    }

    public Long getIdRAtivParada() {
        return idRAtivParada;
    }

    public void setIdRAtivParada(Long idRAtivParada) {
        this.idRAtivParada = idRAtivParada;
    }

    public Long getIdAtiv() {
        return idAtiv;
    }

    public void setIdAtiv(Long idAtiv) {
        this.idAtiv = idAtiv;
    }

    public Long getIdParada() {
        return idParada;
    }

    public void setIdParada(Long idParada) {
        this.idParada = idParada;
    }
}
