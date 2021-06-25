package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbmovleiravar")
public class MovLeiraBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idMovLeiraMM;
    @DatabaseField
    private Long idBolMMFert;
    @DatabaseField
    private Long idExtBolMMFert;
    @DatabaseField
    private Long idLeiraMovLeiraMM;
    @DatabaseField
    private Long tipoMovLeiraMM;
    @DatabaseField
    private String dthrMovLeiraMM;
    @DatabaseField
    private Long statusMovLeiraMM;  //1 - Enviar; 2 - Enviado

    public MovLeiraBean() {
    }

    public Long getIdMovLeiraMM() {
        return idMovLeiraMM;
    }

    public void setIdMovLeiraMM(Long idMovLeiraMM) {
        this.idMovLeiraMM = idMovLeiraMM;
    }

    public Long getIdLeiraMovLeiraMM() {
        return idLeiraMovLeiraMM;
    }

    public void setIdLeiraMovLeiraMM(Long idLeiraMovLeiraMM) {
        this.idLeiraMovLeiraMM = idLeiraMovLeiraMM;
    }

    public Long getTipoMovLeiraMM() {
        return tipoMovLeiraMM;
    }

    public void setTipoMovLeiraMM(Long tipoMovLeiraMM) {
        this.tipoMovLeiraMM = tipoMovLeiraMM;
    }

    public String getDthrMovLeiraMM() {
        return dthrMovLeiraMM;
    }

    public void setDthrMovLeiraMM(String dthrMovLeiraMM) {
        this.dthrMovLeiraMM = dthrMovLeiraMM;
    }

    public Long getIdBolMMFert() {
        return idBolMMFert;
    }

    public void setIdBolMMFert(Long idBolMMFert) {
        this.idBolMMFert = idBolMMFert;
    }

    public Long getIdExtBolMMFert() {
        return idExtBolMMFert;
    }

    public void setIdExtBolMMFert(Long idExtBolMMFert) {
        this.idExtBolMMFert = idExtBolMMFert;
    }

    public Long getStatusMovLeiraMM() {
        return statusMovLeiraMM;
    }

    public void setStatusMovLeiraMM(Long statusMovLeiraMM) {
        this.statusMovLeiraMM = statusMovLeiraMM;
    }
}
