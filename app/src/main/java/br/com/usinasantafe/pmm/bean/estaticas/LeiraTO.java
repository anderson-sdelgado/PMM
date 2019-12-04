package br.com.usinasantafe.pmm.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.pst.Entidade;

@DatabaseTable(tableName="tbleiraest")
public class LeiraTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idLeira;
    @DatabaseField
    private Long codLeira;
    @DatabaseField
    private Long statusLeira; //0 - Liberada para qualquer Inicio; 1 - Leira para Finalizar Descarga; 2 - Leira para Finalizar Cargar

    public LeiraTO() {
    }

    public Long getIdLeira() {
        return idLeira;
    }

    public void setIdLeira(Long idLeira) {
        this.idLeira = idLeira;
    }

    public Long getCodLeira() {
        return codLeira;
    }

    public void setCodLeira(Long codLeira) {
        this.codLeira = codLeira;
    }

    public Long getStatusLeira() {
        return statusLeira;
    }

    public void setStatusLeira(Long statusLeira) {
        this.statusLeira = statusLeira;
    }
}
