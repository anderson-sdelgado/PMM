package br.com.usinasantafe.pmm.to.tb.estaticas;

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
    @DatabaseField
    private Long flagRendimento;
    @DatabaseField
    private Long flagTransbordo;
    @DatabaseField
    private Long flagImplemento;
    @DatabaseField
    private Long flagCarretel;

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

    public Long getFlagRendimento() {
        return flagRendimento;
    }

    public void setFlagRendimento(Long flagRendimento) {
        this.flagRendimento = flagRendimento;
    }

    public Long getFlagTransbordo() {
        return flagTransbordo;
    }

    public void setFlagTransbordo(Long flagTransbordo) {
        this.flagTransbordo = flagTransbordo;
    }

    public Long getFlagImplemento() {
        return flagImplemento;
    }

    public void setFlagImplemento(Long flagImplemento) {
        this.flagImplemento = flagImplemento;
    }

    public Long getFlagCarretel() {
        return flagCarretel;
    }

    public void setFlagCarretel(Long flagCarretel) {
        this.flagCarretel = flagCarretel;
    }
}
