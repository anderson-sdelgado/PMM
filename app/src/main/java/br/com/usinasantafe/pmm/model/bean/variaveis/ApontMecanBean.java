package br.com.usinasantafe.pmm.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbapontmecanvar")
public class ApontMecanBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idApontMecan;
    @DatabaseField
    private Long idBolApontMecan;
    @DatabaseField
    private Long osApontMecan;
    @DatabaseField
    private Long itemOSApontMecan;
    @DatabaseField
    private String dthrInicialApontMecan;
    @DatabaseField
    private String dthrFinalApontMecan;
    @DatabaseField
    private Long statusApontMecan;
    // 1 - Aberto Não Enviado;
    // 2 - Aberto Enviado;
    // 3 - Fechado Não Enviado;
    // 4 - Fechado Enviado;

    public ApontMecanBean() {
    }

    public Long getIdApontMecan() {
        return idApontMecan;
    }

    public void setIdApontMecan(Long idApontMecan) {
        this.idApontMecan = idApontMecan;
    }

    public Long getIdBolApontMecan() {
        return idBolApontMecan;
    }

    public void setIdBolApontMecan(Long idBolApontMecan) {
        this.idBolApontMecan = idBolApontMecan;
    }

    public Long getOsApontMecan() {
        return osApontMecan;
    }

    public void setOsApontMecan(Long osApontMecan) {
        this.osApontMecan = osApontMecan;
    }

    public Long getItemOSApontMecan() {
        return itemOSApontMecan;
    }

    public void setItemOSApontMecan(Long itemOSApontMecan) {
        this.itemOSApontMecan = itemOSApontMecan;
    }

    public String getDthrInicialApontMecan() {
        return dthrInicialApontMecan;
    }

    public void setDthrInicialApontMecan(String dthrInicialApontMecan) {
        this.dthrInicialApontMecan = dthrInicialApontMecan;
    }

    public String getDthrFinalApontMecan() {
        return dthrFinalApontMecan;
    }

    public void setDthrFinalApontMecan(String dthrFinalApontMecan) {
        this.dthrFinalApontMecan = dthrFinalApontMecan;
    }


    public Long getStatusApontMecan() {
        return statusApontMecan;
    }

    public void setStatusApontMecan(Long statusApontMecan) {
        this.statusApontMecan = statusApontMecan;
    }
}
