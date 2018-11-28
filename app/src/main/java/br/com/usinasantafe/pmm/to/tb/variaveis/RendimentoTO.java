package br.com.usinasantafe.pmm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

/**
 * Created by anderson on 17/08/2017.
 */
@DatabaseTable(tableName="tbrendimentovar")
public class RendimentoTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRendimento;
    @DatabaseField
    private Long idBolRendimento;
    @DatabaseField
    private Long idExtBolRendimento;
    @DatabaseField
    private Long nroOSRendimento;
    @DatabaseField
    private Double valorRendimento;
    @DatabaseField
    private String dthrRendimento;

    public RendimentoTO() {
    }

    public Long getIdRendimento() {
        return idRendimento;
    }

    public Long getIdBolRendimento() {
        return idBolRendimento;
    }

    public void setIdBolRendimento(Long idBolRendimento) {
        this.idBolRendimento = idBolRendimento;
    }

    public Long getIdExtBolRendimento() {
        return idExtBolRendimento;
    }

    public void setIdExtBolRendimento(Long idExtBolRendimento) {
        this.idExtBolRendimento = idExtBolRendimento;
    }

    public Long getNroOSRendimento() {
        return nroOSRendimento;
    }

    public void setNroOSRendimento(Long nroOSRendimento) {
        this.nroOSRendimento = nroOSRendimento;
    }

    public Double getValorRendimento() {
        return valorRendimento;
    }

    public void setValorRendimento(Double valorRendimento) {
        this.valorRendimento = valorRendimento;
    }

    public String getDthrRendimento() {
        return dthrRendimento;
    }

    public void setDthrRendimento(String dthrRendimento) {
        this.dthrRendimento = dthrRendimento;
    }

}
