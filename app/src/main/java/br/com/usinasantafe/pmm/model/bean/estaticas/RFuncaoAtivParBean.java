package br.com.usinasantafe.pmm.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pmm.model.pst.Entidade;

@DatabaseTable(tableName="tbrfuncaoativparest")
public class RFuncaoAtivParBean extends Entidade {

    @DatabaseField(generatedId=true)
    private Long idRFuncaoAtivPar;
    @DatabaseField
    private Long idAtivPar;
    @DatabaseField
    private Long codFuncao; // ATIVIDADE -> 1 - RENDIMENTO; 2 - TRANSBORDO; 3 - IMPLEMENTO; 4 - CARRETEL
                            // PARADA -> 1 - CHECKLIST; 2 - IMPLEMENTO; 3 - CALIBRAGEM
    @DatabaseField
    private Long tipoFuncao; // 1 - ATIVIDADE; 2 - PARADA

    public RFuncaoAtivParBean() {
    }

    public Long getIdRFuncaoAtivPar() {
        return idRFuncaoAtivPar;
    }

    public void setIdRFuncaoAtivPar(Long idRFuncaoAtivPar) {
        this.idRFuncaoAtivPar = idRFuncaoAtivPar;
    }

    public Long getIdAtivPar() {
        return idAtivPar;
    }

    public void setIdAtivPar(Long idAtivPar) {
        this.idAtivPar = idAtivPar;
    }

    public Long getCodFuncao() {
        return codFuncao;
    }

    public void setCodFuncao(Long codFuncao) {
        this.codFuncao = codFuncao;
    }

    public Long getTipoFuncao() {
        return tipoFuncao;
    }

    public void setTipoFuncao(Long tipoFuncao) {
        this.tipoFuncao = tipoFuncao;
    }
}
