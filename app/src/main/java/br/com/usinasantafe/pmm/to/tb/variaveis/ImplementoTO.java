package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 06/11/2017.
 */
@DatabaseTable(tableName="tbimplementovar")
public class ImplementoTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idImplemento;
    @DatabaseField
    private Long idApontImplemento;
    @DatabaseField
    private Long posImplemento;
    @DatabaseField
    private Long codEquipImplemento;
    @DatabaseField
    private String dthrImplemento;

    public ImplementoTO() {
    }

    public Long getIdImplemento() {
        return idImplemento;
    }

    public void setIdImplemento(Long idImplemento) {
        this.idImplemento = idImplemento;
    }

    public Long getIdApontImplemento() {
        return idApontImplemento;
    }

    public void setIdApontImplemento(Long idApontImplemento) {
        this.idApontImplemento = idApontImplemento;
    }

    public Long getCodEquipImplemento() {
        return codEquipImplemento;
    }

    public void setCodEquipImplemento(Long codEquipImplemento) {
        this.codEquipImplemento = codEquipImplemento;
    }

    public Long getPosImplemento() {
        return posImplemento;
    }

    public void setPosImplemento(Long posImplemento) {
        this.posImplemento = posImplemento;
    }

    public String getDthrImplemento() {
        return dthrImplemento;
    }

    public void setDthrImplemento(String dthrImplemento) {
        this.dthrImplemento = dthrImplemento;
    }
}
