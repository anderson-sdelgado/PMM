package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 13/04/2018.
 */
@DatabaseTable(tableName="tbrendmangrecvar")
public class RecolMangTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRendMangRecol;
    @DatabaseField
    private Long idBolRendMangRecol;
    @DatabaseField
    private Long idExtBolRendMangRecol;
    @DatabaseField
    private Long nroOSRendMangRecol;
    @DatabaseField
    private Long equipRendMangRecol;
    @DatabaseField
    private Long valorRendMangRecol;
    @DatabaseField
    private String dthrRendMangRecol;
    @DatabaseField
    private Long statusRendMangRecol; //1 - Aberto; 2 - Encerrado

    public RecolMangTO() {
    }

    public Long getIdRendMangRecol() {
        return idRendMangRecol;
    }

    public void setIdRendMangRecol(Long idRendMangRecol) {
        this.idRendMangRecol = idRendMangRecol;
    }

    public Long getIdBolRendMangRecol() {
        return idBolRendMangRecol;
    }

    public void setIdBolRendMangRecol(Long idBolRendMangRecol) {
        this.idBolRendMangRecol = idBolRendMangRecol;
    }

    public Long getIdExtBolRendMangRecol() {
        return idExtBolRendMangRecol;
    }

    public void setIdExtBolRendMangRecol(Long idExtBolRendMangRecol) {
        this.idExtBolRendMangRecol = idExtBolRendMangRecol;
    }

    public Long getNroOSRendMangRecol() {
        return nroOSRendMangRecol;
    }

    public void setNroOSRendMangRecol(Long nroOSRendMangRecol) {
        this.nroOSRendMangRecol = nroOSRendMangRecol;
    }

    public Long getEquipRendMangRecol() {
        return equipRendMangRecol;
    }

    public void setEquipRendMangRecol(Long equipRendMangRecol) {
        this.equipRendMangRecol = equipRendMangRecol;
    }

    public Long getValorRendMangRecol() {
        return valorRendMangRecol;
    }

    public void setValorRendMangRecol(Long valorRendMangRecol) {
        this.valorRendMangRecol = valorRendMangRecol;
    }

    public String getDthrRendMangRecol() {
        return dthrRendMangRecol;
    }

    public void setDthrRendMangRecol(String dthrRendMangRecol) {
        this.dthrRendMangRecol = dthrRendMangRecol;
    }

    public Long getStatusRendMangRecol() {
        return statusRendMangRecol;
    }

    public void setStatusRendMangRecol(Long statusRendMangRecol) {
        this.statusRendMangRecol = statusRendMangRecol;
    }
}
