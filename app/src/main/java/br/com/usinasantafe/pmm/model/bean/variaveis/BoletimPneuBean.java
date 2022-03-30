package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbbolpneuvar")
public class BoletimPneuBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idBolPneu;
    @DatabaseField
    private Long idApontBolPneu;
    @DatabaseField
    private Long matricFuncBolPneu;
    @DatabaseField
    private Long idEquipBolPneu;
    @DatabaseField
    private String dthrBolPneu;
    @DatabaseField
    private Long dthrLongBolPneu;
    @DatabaseField
    private Long statusBolPneu;  //1 - Aberto; 2 - Encerrado; 3 - Enviado

    public BoletimPneuBean() {
    }

    public Long getIdBolPneu() {
        return idBolPneu;
    }

    public void setIdBolPneu(Long idBolPneu) {
        this.idBolPneu = idBolPneu;
    }

    public Long getIdApontBolPneu() {
        return idApontBolPneu;
    }

    public void setIdApontBolPneu(Long idApontBolPneu) {
        this.idApontBolPneu = idApontBolPneu;
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
}
