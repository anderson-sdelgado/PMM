package br.com.usinasantafe.pmm.to.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbmovleiravar")
public class MovLeiraTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idMovLeira;
    @DatabaseField
    private Long idBolLeiraMM;
    @DatabaseField
    private Long idExtBolLeiraMM;
    @DatabaseField
    private Long idLeira;
    @DatabaseField
    private Long tipoMovLeira;
    @DatabaseField
    private String dataHoraMovLeira;
    @DatabaseField
    private Long statusMovLeira;  //1 - Enviar; 2 - Enviado

    public MovLeiraTO() {
    }

    public Long getIdMovLeira() {
        return idMovLeira;
    }

    public void setIdMovLeira(Long idMovLeira) {
        this.idMovLeira = idMovLeira;
    }

    public Long getIdLeira() {
        return idLeira;
    }

    public void setIdLeira(Long idLeira) {
        this.idLeira = idLeira;
    }

    public Long getTipoMovLeira() {
        return tipoMovLeira;
    }

    public void setTipoMovLeira(Long tipoMovLeira) {
        this.tipoMovLeira = tipoMovLeira;
    }

    public String getDataHoraMovLeira() {
        return dataHoraMovLeira;
    }

    public void setDataHoraMovLeira(String dataHoraMovLeira) {
        this.dataHoraMovLeira = dataHoraMovLeira;
    }

    public Long getIdBolLeiraMM() {
        return idBolLeiraMM;
    }

    public void setIdBolLeiraMM(Long idBolLeiraMM) {
        this.idBolLeiraMM = idBolLeiraMM;
    }

    public Long getIdExtBolLeiraMM() {
        return idExtBolLeiraMM;
    }

    public void setIdExtBolLeiraMM(Long idExtBolLeiraMM) {
        this.idExtBolLeiraMM = idExtBolLeiraMM;
    }

    public Long getStatusMovLeira() {
        return statusMovLeira;
    }

    public void setStatusMovLeira(Long statusMovLeira) {
        this.statusMovLeira = statusMovLeira;
    }
}
