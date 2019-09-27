package br.com.usinasantafe.pmm.pst;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.usinasantafe.pmm.to.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.estaticas.BocalTO;
import br.com.usinasantafe.pmm.to.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.estaticas.FuncionarioTO;
import br.com.usinasantafe.pmm.to.estaticas.LeiraTO;
import br.com.usinasantafe.pmm.to.estaticas.OSTO;
import br.com.usinasantafe.pmm.to.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.estaticas.PressaoBocalTO;
import br.com.usinasantafe.pmm.to.estaticas.RAtivParadaTO;
import br.com.usinasantafe.pmm.to.estaticas.REquipAtivTO;
import br.com.usinasantafe.pmm.to.estaticas.RFuncaoAtivParTO;
import br.com.usinasantafe.pmm.to.estaticas.ROSAtivTO;
import br.com.usinasantafe.pmm.to.estaticas.TurnoTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontImpleMMTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.ImpleMMTO;
import br.com.usinasantafe.pmm.to.variaveis.InfoColheitaTO;
import br.com.usinasantafe.pmm.to.variaveis.MovLeiraTO;
import br.com.usinasantafe.pmm.to.variaveis.RecolhFertTO;
import br.com.usinasantafe.pmm.to.variaveis.RendMMTO;
import br.com.usinasantafe.pmm.to.variaveis.RespItemCLTO;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pmm_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {

		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);

		instance = this;
		
	}

	@Override
	public void close() {
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{

			TableUtils.createTable(cs, EquipTO.class);
			TableUtils.createTable(cs, EquipSegTO.class);
			TableUtils.createTable(cs, AtividadeTO.class);
			TableUtils.createTable(cs, REquipAtivTO.class);
			TableUtils.createTable(cs, TurnoTO.class);
			TableUtils.createTable(cs, FuncionarioTO.class);
			TableUtils.createTable(cs, OSTO.class);
			TableUtils.createTable(cs, ROSAtivTO.class);
			TableUtils.createTable(cs, RAtivParadaTO.class);
			TableUtils.createTable(cs, ParadaTO.class);
			TableUtils.createTable(cs, ItemCheckListTO.class);
			TableUtils.createTable(cs, BocalTO.class);
			TableUtils.createTable(cs, PressaoBocalTO.class);
			TableUtils.createTable(cs, RFuncaoAtivParTO.class);
			TableUtils.createTable(cs, LeiraTO.class);

			TableUtils.createTable(cs, ConfigTO.class);
			TableUtils.createTable(cs, BoletimMMTO.class);
			TableUtils.createTable(cs, ApontMMTO.class);
			TableUtils.createTable(cs, CabecCLTO.class);
			TableUtils.createTable(cs, RespItemCLTO.class);
			TableUtils.createTable(cs, RendMMTO.class);
			TableUtils.createTable(cs, RecolhFertTO.class);
			TableUtils.createTable(cs, ImpleMMTO.class);
			TableUtils.createTable(cs, BackupApontaTO.class);
			TableUtils.createTable(cs, BoletimFertTO.class);
			TableUtils.createTable(cs, ApontFertTO.class);
			TableUtils.createTable(cs, InfoColheitaTO.class);
			TableUtils.createTable(cs, ApontImpleMMTO.class);
			TableUtils.createTable(cs, MovLeiraTO.class);
			
		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
