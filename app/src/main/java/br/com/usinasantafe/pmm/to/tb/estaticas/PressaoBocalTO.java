package br.com.usinasantafe.pmm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbpressaobocalest")
public class PressaoBocalTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idPressaoBocal;
    @DatabaseField
    private Long idBocal;
    @DatabaseField
    private Double valorPressao;
    @DatabaseField
    private Long valorVeloc;

    public PressaoBocalTO() {
    }

    public Long getIdPressaoBocal() {
        return idPressaoBocal;
    }

    public void setIdPressaoBocal(Long idPressaoBocal) {
        this.idPressaoBocal = idPressaoBocal;
    }

    public Long getIdBocal() {
        return idBocal;
    }

    public void setIdBocal(Long idBocal) {
        this.idBocal = idBocal;
    }

    public Double getValorPressao() {
        return valorPressao;
    }

    public void setValorPressao(Double valorPressao) {
        this.valorPressao = valorPressao;
    }

    public Long getValorVeloc() {
        return valorVeloc;
    }

    public void setValorVeloc(Long valorVeloc) {
        this.valorVeloc = valorVeloc;
    }
}
