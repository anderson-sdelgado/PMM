package br.com.usinasantafe.cmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

import br.com.usinasantafe.cmm.model.pst.Entidade;

@DatabaseTable(tableName="tbbolpneuvar")
public class BoletimPneuBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idBolPneu;
    @DatabaseField
    private Long idBolMMFertPneu;
    @DatabaseField
    private Long matricFuncBolPneu;
    @DatabaseField
    private Long idEquipBolPneu;
    @DatabaseField
    private Long tipoBolPneu; // 1 - Calibragem; 2 - Troca
    @DatabaseField
    private String dthrBolPneu;
    @DatabaseField
    private Long dthrLongBolPneu;
    @DatabaseField
    private Long statusBolPneu;  // 1 - Aberto; 2 - Encerrado; 3 - Enviado
    private List<ItemCalibPneuBean> itemCalibPneuList;
    private List<ItemManutPneuBean> itemManutPneuBeanList;

    public BoletimPneuBean() {
    }

    public Long getIdBolPneu() {
        return idBolPneu;
    }

    public void setIdBolPneu(Long idBolPneu) {
        this.idBolPneu = idBolPneu;
    }

    public Long getIdBolMMFertPneu() {
        return idBolMMFertPneu;
    }

    public void setIdBolMMFertPneu(Long idBolMMFertPneu) {
        this.idBolMMFertPneu = idBolMMFertPneu;
    }

    public Long getMatricFuncBolPneu() {
        return matricFuncBolPneu;
    }

    public void setMatricFuncBolPneu(Long matricFuncBolPneu) {
        this.matricFuncBolPneu = matricFuncBolPneu;
    }

    public Long getIdEquipBolPneu() {
        return idEquipBolPneu;
    }

    public void setIdEquipBolPneu(Long idEquipBolPneu) {
        this.idEquipBolPneu = idEquipBolPneu;
    }

    public Long getTipoBolPneu() {
        return tipoBolPneu;
    }

    public void setTipoBolPneu(Long tipoBolPneu) {
        this.tipoBolPneu = tipoBolPneu;
    }

    public String getDthrBolPneu() {
        return dthrBolPneu;
    }

    public void setDthrBolPneu(String dthrBolPneu) {
        this.dthrBolPneu = dthrBolPneu;
    }

    public Long getStatusBolPneu() {
        return statusBolPneu;
    }

    public void setStatusBolPneu(Long statusBolPneu) {
        this.statusBolPneu = statusBolPneu;
    }

    public Long getDthrLongBolPneu() {
        return dthrLongBolPneu;
    }

    public void setDthrLongBolPneu(Long dthrLongBolPneu) {
        this.dthrLongBolPneu = dthrLongBolPneu;
    }

    public List<ItemCalibPneuBean> getItemCalibPneuList() {
        return itemCalibPneuList;
    }

    public void setItemCalibPneuList(List<ItemCalibPneuBean> itemCalibPneuList) {
        this.itemCalibPneuList = itemCalibPneuList;
    }

    public List<ItemManutPneuBean> getItemManutPneuBeanList() {
        return itemManutPneuBeanList;
    }

    public void setItemManutPneuBeanList(List<ItemManutPneuBean> itemManutPneuBeanList) {
        this.itemManutPneuBeanList = itemManutPneuBeanList;
    }
}
