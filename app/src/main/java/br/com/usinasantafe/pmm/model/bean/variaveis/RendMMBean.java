package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

/**
 * Created by anderson on 17/08/2017.
 */
@DatabaseTable(tableName="tbrendmmvar")
public class RendMMBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRendMM;
    @DatabaseField
    private Long idBolRendMM;
    @DatabaseField
    private Long idExtBolRendMM;
    @DatabaseField
    private Long nroOSRendMM;
    @DatabaseField
    private Double valorRendMM;
    @DatabaseField
    private String dthrRendMM;

    public RendMMBean() {
    }

    public Long getIdRendMM() {
        return idRendMM;
    }

    public Long getIdBolRendMM() {
        return idBolRendMM;
    }

    public void setIdBolRendMM(Long idBolRendMM) {
        this.idBolRendMM = idBolRendMM;
    }

    public Long getIdExtBolRendMM() {
        return idExtBolRendMM;
    }

    public void setIdExtBolRendMM(Long idExtBolRendMM) {
        this.idExtBolRendMM = idExtBolRendMM;
    }

    public Long getNroOSRendMM() {
        return nroOSRendMM;
    }

    public void setNroOSRendMM(Long nroOSRendMM) {
        this.nroOSRendMM = nroOSRendMM;
    }

    public Double getValorRendMM() {
        return valorRendMM;
    }

    public void setValorRendMM(Double valorRendMM) {
        this.valorRendMM = valorRendMM;
    }

    public String getDthrRendMM() {
        return dthrRendMM;
    }

    public void setDthrRendMM(String dthrRendMM) {
        this.dthrRendMM = dthrRendMM;
    }

}
