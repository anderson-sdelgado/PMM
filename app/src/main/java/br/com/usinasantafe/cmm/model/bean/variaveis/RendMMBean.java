package br.com.usinasantafe.cmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.cmm.model.pst.Entidade;

/**
 * Created by anderson on 17/08/2017.
 */
@DatabaseTable(tableName="tbrendmmvar")
public class RendMMBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRendMM;
    @DatabaseField
    private Long idBolMMFert;
    @DatabaseField
    private Long nroOSRendMM;
    @DatabaseField
    private Double valorRendMM;
    @DatabaseField
    private String dthrRendMM;
//    @DatabaseField
//    private Long statusRendMM; //1 - enviar; 2 - enviado

    public RendMMBean() {
    }

    public Long getIdRendMM() {
        return idRendMM;
    }

    public Long getIdBolMMFert() {
        return idBolMMFert;
    }

    public void setIdBolMMFert(Long idBolMMFert) {
        this.idBolMMFert = idBolMMFert;
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

//    public Long getStatusRendMM() {
//        return statusRendMM;
//    }
//
//    public void setStatusRendMM(Long statusRendMM) {
//        this.statusRendMM = statusRendMM;
//    }
}
