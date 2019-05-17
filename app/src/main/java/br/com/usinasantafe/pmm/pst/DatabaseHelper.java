package br.com.usinasantafe.pmm.pst;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.usinasantafe.pmm.to.tb.estaticas.AtividadeTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipSegTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafDispEquipPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafPlanRealPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafProdPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafQualPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.MotoristaTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.OSTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ParadaTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.PneuTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.RAtivParadaTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.REquipAtivTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.REquipPneuTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.ROSAtivTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.TurnoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ApontaMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BackupApontaTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.BoletimPneuTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.AlocaCarretelTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ImplementoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ItemMedPneuTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RecolhimentoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RendimentoTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.RespItemCheckListTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.TransbordoTO;

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
		
		// TODO Auto-generated constructor stub
		instance = this;
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		// TODO Auto-generated method stub
		
		try{

			TableUtils.createTable(cs, EquipTO.class);
			TableUtils.createTable(cs, EquipSegTO.class);
			TableUtils.createTable(cs, AtividadeTO.class);
			TableUtils.createTable(cs, REquipAtivTO.class);
			TableUtils.createTable(cs, TurnoTO.class);
			TableUtils.createTable(cs, MotoristaTO.class);
			TableUtils.createTable(cs, OSTO.class);
			TableUtils.createTable(cs, ROSAtivTO.class);
			TableUtils.createTable(cs, RAtivParadaTO.class);
			TableUtils.createTable(cs, ParadaTO.class);
			TableUtils.createTable(cs, ItemCheckListTO.class);
			TableUtils.createTable(cs, REquipPneuTO.class);
			TableUtils.createTable(cs, PneuTO.class);
			TableUtils.createTable(cs, GrafProdPlantioTO.class);
			TableUtils.createTable(cs, GrafPlanRealPlantioTO.class);
			TableUtils.createTable(cs, GrafDispEquipPlantioTO.class);
			TableUtils.createTable(cs, GrafQualPlantioTO.class);

			TableUtils.createTable(cs, ConfiguracaoTO.class);
			TableUtils.createTable(cs, BoletimMMTO.class);
			TableUtils.createTable(cs, ApontaMMTO.class);
			TableUtils.createTable(cs, CabecCheckListTO.class);
			TableUtils.createTable(cs, RespItemCheckListTO.class);
			TableUtils.createTable(cs, RendimentoTO.class);
			TableUtils.createTable(cs, RecolhimentoTO.class);
			TableUtils.createTable(cs, TransbordoTO.class);
			TableUtils.createTable(cs, ImplementoTO.class);
			TableUtils.createTable(cs, AlocaCarretelTO.class);
			TableUtils.createTable(cs, BackupApontaTO.class);
			TableUtils.createTable(cs, BoletimPneuTO.class);
			TableUtils.createTable(cs, ItemMedPneuTO.class);
			TableUtils.createTable(cs, BoletimFertTO.class);
			TableUtils.createTable(cs, ApontaFertTO.class);
			
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
//				TableUtils.createTable(cs, ConfiguracaoTO.class);
				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
