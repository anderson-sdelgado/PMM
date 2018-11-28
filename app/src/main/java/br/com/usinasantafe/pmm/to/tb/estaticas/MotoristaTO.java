/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.pmm.to.tb.estaticas;

/**
 *
 * @author anderson
 */
import br.com.usinasantafe.pmm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbmotoristaest")
public class MotoristaTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long codMotorista;
	@DatabaseField
    private String nomeMotorista;

    public MotoristaTO() {
    }

	public Long getCodMotorista() {
		return codMotorista;
	}

	public void setCodMotorista(Long codmotorista) {
		this.codMotorista = codmotorista;
	}

	public String getNomeMotorista() {
		return nomeMotorista;
	}

	public void setNomeMotorista(String nomemotorista) {
		this.nomeMotorista = nomemotorista;
	}

}
