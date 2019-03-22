package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbdispequipplantvar")
public class GrafDispEquipPlantioTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idGrafDispEquip;
    @DatabaseField
    private Long valorOperTratorPlanDia;
    @DatabaseField
    private Long valorCampoTratorPlanDia;
    @DatabaseField
    private Long valorOperTratorPlanMes;
    @DatabaseField
    private Long valorCampoTratorPlanMes;
    @DatabaseField
    private Long valorOperTratorPlanAno;
    @DatabaseField
    private Long valorCampoTratorPlanAno;
    @DatabaseField
    private Long valorOperCamMudaDia;
    @DatabaseField
    private Long valorCampoCamMudaDia;
    @DatabaseField
    private Long valorOperCamMudaMes;
    @DatabaseField
    private Long valorCampoCamMudaMes;
    @DatabaseField
    private Long valorOperCamMudaAno;
    @DatabaseField
    private Long valorCampoCamMudaAno;
    @DatabaseField
    private Long valorOperColhedoraDia;
    @DatabaseField
    private Long valorCampoColhedoraDia;
    @DatabaseField
    private Long valorOperColhedoraMes;
    @DatabaseField
    private Long valorCampoColhedoraMes;
    @DatabaseField
    private Long valorOperColhedoraAno;
    @DatabaseField
    private Long valorCampoColhedoraAno;
    @DatabaseField
    private Long valorOperTratorTransbDia;
    @DatabaseField
    private Long valorCampoTratorTransbDia;
    @DatabaseField
    private Long valorOperTratorTransbMes;
    @DatabaseField
    private Long valorCampoTratorTransbMes;
    @DatabaseField
    private Long valorOperTratorTransbAno;
    @DatabaseField
    private Long valorCampoTratorTransbAno;

    public GrafDispEquipPlantioTO() {
    }

    public Long getIdGrafDispEquip() {
        return idGrafDispEquip;
    }

    public void setIdGrafDispEquip(Long idGrafDispEquip) {
        this.idGrafDispEquip = idGrafDispEquip;
    }

    public Long getValorOperTratorPlanDia() {
        return valorOperTratorPlanDia;
    }

    public void setValorOperTratorPlanDia(Long valorOperTratorPlanDia) {
        this.valorOperTratorPlanDia = valorOperTratorPlanDia;
    }

    public Long getValorCampoTratorPlanDia() {
        return valorCampoTratorPlanDia;
    }

    public void setValorCampoTratorPlanDia(Long valorCampoTratorPlanDia) {
        this.valorCampoTratorPlanDia = valorCampoTratorPlanDia;
    }

    public Long getValorOperTratorPlanMes() {
        return valorOperTratorPlanMes;
    }

    public void setValorOperTratorPlanMes(Long valorOperTratorPlanMes) {
        this.valorOperTratorPlanMes = valorOperTratorPlanMes;
    }

    public Long getValorCampoTratorPlanMes() {
        return valorCampoTratorPlanMes;
    }

    public void setValorCampoTratorPlanMes(Long valorCampoTratorPlanMes) {
        this.valorCampoTratorPlanMes = valorCampoTratorPlanMes;
    }

    public Long getValorOperTratorPlanAno() {
        return valorOperTratorPlanAno;
    }

    public void setValorOperTratorPlanAno(Long valorOperTratorPlanAno) {
        this.valorOperTratorPlanAno = valorOperTratorPlanAno;
    }

    public Long getValorCampoTratorPlanAno() {
        return valorCampoTratorPlanAno;
    }

    public void setValorCampoTratorPlanAno(Long valorCampoTratorPlanAno) {
        this.valorCampoTratorPlanAno = valorCampoTratorPlanAno;
    }

    public Long getValorOperCamMudaDia() {
        return valorOperCamMudaDia;
    }

    public void setValorOperCamMudaDia(Long valorOperCamMudaDia) {
        this.valorOperCamMudaDia = valorOperCamMudaDia;
    }

    public Long getValorCampoCamMudaDia() {
        return valorCampoCamMudaDia;
    }

    public void setValorCampoCamMudaDia(Long valorCampoCamMudaDia) {
        this.valorCampoCamMudaDia = valorCampoCamMudaDia;
    }

    public Long getValorOperCamMudaMes() {
        return valorOperCamMudaMes;
    }

    public void setValorOperCamMudaMes(Long valorOperCamMudaMes) {
        this.valorOperCamMudaMes = valorOperCamMudaMes;
    }

    public Long getValorCampoCamMudaMes() {
        return valorCampoCamMudaMes;
    }

    public void setValorCampoCamMudaMes(Long valorCampoCamMudaMes) {
        this.valorCampoCamMudaMes = valorCampoCamMudaMes;
    }

    public Long getValorOperCamMudaAno() {
        return valorOperCamMudaAno;
    }

    public void setValorOperCamMudaAno(Long valorOperCamMudaAno) {
        this.valorOperCamMudaAno = valorOperCamMudaAno;
    }

    public Long getValorCampoCamMudaAno() {
        return valorCampoCamMudaAno;
    }

    public void setValorCampoCamMudaAno(Long valorCampoCamMudaAno) {
        this.valorCampoCamMudaAno = valorCampoCamMudaAno;
    }

    public Long getValorOperColhedoraDia() {
        return valorOperColhedoraDia;
    }

    public void setValorOperColhedoraDia(Long valorOperColhedoraDia) {
        this.valorOperColhedoraDia = valorOperColhedoraDia;
    }

    public Long getValorCampoColhedoraDia() {
        return valorCampoColhedoraDia;
    }

    public void setValorCampoColhedoraDia(Long valorCampoColhedoraDia) {
        this.valorCampoColhedoraDia = valorCampoColhedoraDia;
    }

    public Long getValorOperColhedoraMes() {
        return valorOperColhedoraMes;
    }

    public void setValorOperColhedoraMes(Long valorOperColhedoraMes) {
        this.valorOperColhedoraMes = valorOperColhedoraMes;
    }

    public Long getValorCampoColhedoraMes() {
        return valorCampoColhedoraMes;
    }

    public void setValorCampoColhedoraMes(Long valorCampoColhedoraMes) {
        this.valorCampoColhedoraMes = valorCampoColhedoraMes;
    }

    public Long getValorOperColhedoraAno() {
        return valorOperColhedoraAno;
    }

    public void setValorOperColhedoraAno(Long valorOperColhedoraAno) {
        this.valorOperColhedoraAno = valorOperColhedoraAno;
    }

    public Long getValorCampoColhedoraAno() {
        return valorCampoColhedoraAno;
    }

    public void setValorCampoColhedoraAno(Long valorCampoColhedoraAno) {
        this.valorCampoColhedoraAno = valorCampoColhedoraAno;
    }

    public Long getValorOperTratorTransbDia() {
        return valorOperTratorTransbDia;
    }

    public void setValorOperTratorTransbDia(Long valorOperTratorTransbDia) {
        this.valorOperTratorTransbDia = valorOperTratorTransbDia;
    }

    public Long getValorCampoTratorTransbDia() {
        return valorCampoTratorTransbDia;
    }

    public void setValorCampoTratorTransbDia(Long valorCampoTratorTransbDia) {
        this.valorCampoTratorTransbDia = valorCampoTratorTransbDia;
    }

    public Long getValorOperTratorTransbMes() {
        return valorOperTratorTransbMes;
    }

    public void setValorOperTratorTransbMes(Long valorOperTratorTransbMes) {
        this.valorOperTratorTransbMes = valorOperTratorTransbMes;
    }

    public Long getValorCampoTratorTransbMes() {
        return valorCampoTratorTransbMes;
    }

    public void setValorCampoTratorTransbMes(Long valorCampoTratorTransbMes) {
        this.valorCampoTratorTransbMes = valorCampoTratorTransbMes;
    }

    public Long getValorOperTratorTransbAno() {
        return valorOperTratorTransbAno;
    }

    public void setValorOperTratorTransbAno(Long valorOperTratorTransbAno) {
        this.valorOperTratorTransbAno = valorOperTratorTransbAno;
    }

    public Long getValorCampoTratorTransbAno() {
        return valorCampoTratorTransbAno;
    }

    public void setValorCampoTratorTransbAno(Long valorCampoTratorTransbAno) {
        this.valorCampoTratorTransbAno = valorCampoTratorTransbAno;
    }
}
