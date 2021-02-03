package br.com.usinasantafe.pmm.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tblogvar")
public class LogErroBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idLog;
    @DatabaseField
    private Long idEquip;
    @DatabaseField
    private String exception;
    @DatabaseField
    private String dthr;
    @DatabaseField
    private Long status;

    public LogErroBean() {
    }

    public Long getIdLog() {
        return idLog;
    }

    public void setIdLog(Long idLog) {
        this.idLog = idLog;
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDthr() {
        return dthr;
    }

    public void setDthr(String dthr) {
        this.dthr = dthr;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
