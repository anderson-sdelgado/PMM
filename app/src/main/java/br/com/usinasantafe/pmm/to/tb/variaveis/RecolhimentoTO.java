package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 13/04/2018.
 */
@DatabaseTable(tableName="tbrendmangrecvar")
public class RecolhimentoTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRecol;
    @DatabaseField
    private Long idBolRecol;
    @DatabaseField
    private Long idExtBolRecol;
    @DatabaseField
    private Long nroOSRecol;
    @DatabaseField
    private Long valorRecol;
    @DatabaseField
    private String dthrRecol;
    @DatabaseField
    private Long statusRecol; //1 - Aberto; 2 - Encerrado

    public RecolhimentoTO() {
    }

    public Long getIdRecol() {
        return idRecol;
    }

    public void setIdRecol(Long idRecol) {
        this.idRecol = idRecol;
    }

    public Long getIdBolRecol() {
        return idBolRecol;
    }

    public void setIdBolRecol(Long idBolRecol) {
        this.idBolRecol = idBolRecol;
    }

    public Long getIdExtBolRecol() {
        return idExtBolRecol;
    }

    public void setIdExtBolRecol(Long idExtBolRecol) {
        this.idExtBolRecol = idExtBolRecol;
    }

    public Long getNroOSRecol() {
        return nroOSRecol;
    }

    public void setNroOSRecol(Long nroOSRecol) {
        this.nroOSRecol = nroOSRecol;
    }

    public Long getValorRecol() {
        return valorRecol;
    }

    public void setValorRecol(Long valorRecol) {
        this.valorRecol = valorRecol;
    }

    public String getDthrRecol() {
        return dthrRecol;
    }

    public void setDthrRecol(String dthrRecol) {
        this.dthrRecol = dthrRecol;
    }

    public Long getStatusRecol() {
        return statusRecol;
    }

    public void setStatusRecol(Long statusRecol) {
        this.statusRecol = statusRecol;
    }
}
