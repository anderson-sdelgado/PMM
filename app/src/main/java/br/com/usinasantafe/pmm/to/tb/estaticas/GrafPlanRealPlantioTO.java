package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbplanrealplantvar")
public class GrafPlanRealPlantioTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long mesPlanReal;
    @DatabaseField
    private Double valorMesPlan;
    @DatabaseField
    private Double valorAcumPlan;
    @DatabaseField
    private Double valorMesReal;
    @DatabaseField
    private Double valorAcumReal;

    public GrafPlanRealPlantioTO() {
    }

    public Long getMesPlanReal() {
        return mesPlanReal;
    }

    public void setMesPlanReal(Long mesPlanReal) {
        this.mesPlanReal = mesPlanReal;
    }

    public Double getValorMesPlan() {
        return valorMesPlan;
    }

    public void setValorMesPlan(Double valorMesPlan) {
        this.valorMesPlan = valorMesPlan;
    }

    public Double getValorAcumPlan() {
        return valorAcumPlan;
    }

    public void setValorAcumPlan(Double valorAcumPlan) {
        this.valorAcumPlan = valorAcumPlan;
    }

    public Double getValorMesReal() {
        return valorMesReal;
    }

    public void setValorMesReal(Double valorMesReal) {
        this.valorMesReal = valorMesReal;
    }

    public Double getValorAcumReal() {
        return valorAcumReal;
    }

    public void setValorAcumReal(Double valorAcumReal) {
        this.valorAcumReal = valorAcumReal;
    }
}
