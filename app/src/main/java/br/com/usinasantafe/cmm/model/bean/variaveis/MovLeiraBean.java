package br.com.usinasantafe.cmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.cmm.model.pst.Entidade;

@DatabaseTable(tableName="tbmovleiravar")
public class MovLeiraBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idMovLeira;
    @DatabaseField
    private Long idBolMMFert;
    @DatabaseField
    private Long idExtBolMMFert;
    @DatabaseField
    private Long idLeiraMovLeira;
    @DatabaseField
    private Long tipoMovLeira;
    @DatabaseField
    private String dthrMovLeira;
    @DatabaseField
    private Long dthrLongMovLeira;
    @DatabaseField
    private Long statusMovLeira;  //1 - Enviar; 2 - Enviado

    public MovLeiraBean() {
    }

    public Long getIdMovLeira() {
        return idMovLeira;
    }

    public void setIdMovLeira(Long idMovLeira) {
        this.idMovLeira = idMovLeira;
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

    public Long getIdLeiraMovLeira() {
        return idLeiraMovLeira;
    }

    public void setIdLeiraMovLeira(Long idLeiraMovLeira) {
        this.idLeiraMovLeira = idLeiraMovLeira;
    }

    public Long getTipoMovLeira() {
        return tipoMovLeira;
    }

    public void setTipoMovLeira(Long tipoMovLeira) {
        this.tipoMovLeira = tipoMovLeira;
    }

    public String getDthrMovLeira() {
        return dthrMovLeira;
    }

    public void setDthrMovLeira(String dthrMovLeira) {
        this.dthrMovLeira = dthrMovLeira;
    }

    public Long getDthrLongMovLeira() {
        return dthrLongMovLeira;
    }

    public void setDthrLongMovLeira(Long dthrLongMovLeira) {
        this.dthrLongMovLeira = dthrLongMovLeira;
    }

    public Long getStatusMovLeira() {
        return statusMovLeira;
    }

    public void setStatusMovLeira(Long statusMovLeira) {
        this.statusMovLeira = statusMovLeira;
    }
}
