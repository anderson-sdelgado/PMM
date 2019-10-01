/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.pmm.bean.estaticas;

/**
 *
 * @author anderson
 */
import br.com.usinasantafe.pmm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbmotoristaest")
public class FuncionarioTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long matricFunc;
	@DatabaseField
    private String nomeFunc;

    public FuncionarioTO() {
    }

	public Long getMatricFunc() {
		return matricFunc;
	}

	public void setMatricFunc(Long matricFunc) {
		this.matricFunc = matricFunc;
	}

	public String getNomeFunc() {
		return nomeFunc;
	}

	public void setNomeFunc(String nomeFunc) {
		this.nomeFunc = nomeFunc;
	}

}
