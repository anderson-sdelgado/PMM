package br.com.usinasantafe.pmm.model.pst;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.usinasantafe.pmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.LeiraBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.PneuBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.PressaoBocalBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.RAtivParadaBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.REquipPneuBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BackupApontaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecPneuBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ItemPneuBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.MovLeiraBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCLBean;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pmm_db";
	public static final int FORCA_BD_VERSION = 4;

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

			TableUtils.createTable(cs, EquipBean.class);
			TableUtils.createTable(cs, EquipSegBean.class);
			TableUtils.createTable(cs, AtividadeBean.class);
			TableUtils.createTable(cs, REquipAtivBean.class);
			TableUtils.createTable(cs, TurnoBean.class);
			TableUtils.createTable(cs, FuncBean.class);
			TableUtils.createTable(cs, OSBean.class);
			TableUtils.createTable(cs, ROSAtivBean.class);
			TableUtils.createTable(cs, RAtivParadaBean.class);
			TableUtils.createTable(cs, ParadaBean.class);
			TableUtils.createTable(cs, ItemCheckListBean.class);
			TableUtils.createTable(cs, BocalBean.class);
			TableUtils.createTable(cs, PressaoBocalBean.class);
			TableUtils.createTable(cs, RFuncaoAtivParBean.class);
			TableUtils.createTable(cs, LeiraBean.class);
			TableUtils.createTable(cs, REquipPneuBean.class);
			TableUtils.createTable(cs, PneuBean.class);

			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, BoletimMMBean.class);
			TableUtils.createTable(cs, ApontMMBean.class);
			TableUtils.createTable(cs, CabecCLBean.class);
			TableUtils.createTable(cs, RespItemCLBean.class);
			TableUtils.createTable(cs, RendMMBean.class);
			TableUtils.createTable(cs, RecolhFertBean.class);
			TableUtils.createTable(cs, ImpleMMBean.class);
			TableUtils.createTable(cs, BackupApontaBean.class);
			TableUtils.createTable(cs, BoletimFertBean.class);
			TableUtils.createTable(cs, ApontFertBean.class);
			TableUtils.createTable(cs, InfColheitaBean.class);
			TableUtils.createTable(cs, InfPlantioBean.class);
			TableUtils.createTable(cs, ApontImpleMMBean.class);
			TableUtils.createTable(cs, MovLeiraBean.class);
			TableUtils.createTable(cs, CabecPneuBean.class);
			TableUtils.createTable(cs, ItemPneuBean.class);
			
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

			if((oldVersion == 1) && (newVersion > 1)){

				TableUtils.dropTable(cs, EquipBean.class, true);
				TableUtils.dropTable(cs, EquipSegBean.class, true);
				TableUtils.dropTable(cs, AtividadeBean.class, true);
				TableUtils.dropTable(cs, REquipAtivBean.class, true);
				TableUtils.dropTable(cs, TurnoBean.class, true);
				TableUtils.dropTable(cs, FuncBean.class, true);
				TableUtils.dropTable(cs, OSBean.class, true);
				TableUtils.dropTable(cs, ROSAtivBean.class, true);
				TableUtils.dropTable(cs, RAtivParadaBean.class, true);
				TableUtils.dropTable(cs, ParadaBean.class, true);
				TableUtils.dropTable(cs, ItemCheckListBean.class, true);
				TableUtils.dropTable(cs, BocalBean.class, true);
				TableUtils.dropTable(cs, PressaoBocalBean.class, true);
				TableUtils.dropTable(cs, RFuncaoAtivParBean.class, true);
				TableUtils.dropTable(cs, LeiraBean.class, true);
				TableUtils.dropTable(cs, REquipPneuBean.class, true);
				TableUtils.dropTable(cs, PneuBean.class, true);

				TableUtils.dropTable(cs, ConfigBean.class, true);
				TableUtils.dropTable(cs, BoletimMMBean.class, true);
				TableUtils.dropTable(cs, ApontMMBean.class, true);
				TableUtils.dropTable(cs, CabecCLBean.class, true);
				TableUtils.dropTable(cs, RespItemCLBean.class, true);
				TableUtils.dropTable(cs, RendMMBean.class, true);
				TableUtils.dropTable(cs, RecolhFertBean.class, true);
				TableUtils.dropTable(cs, ImpleMMBean.class, true);
				TableUtils.dropTable(cs, BackupApontaBean.class, true);
				TableUtils.dropTable(cs, BoletimFertBean.class, true);
				TableUtils.dropTable(cs, ApontFertBean.class, true);
				TableUtils.dropTable(cs, InfColheitaBean.class, true);
				TableUtils.dropTable(cs, InfPlantioBean.class, true);
				TableUtils.dropTable(cs, ApontImpleMMBean.class, true);
				TableUtils.dropTable(cs, MovLeiraBean.class, true);
				TableUtils.dropTable(cs, CabecPneuBean.class, true);
				TableUtils.dropTable(cs, ItemPneuBean.class, true);

				TableUtils.createTable(cs, EquipBean.class);
				TableUtils.createTable(cs, EquipSegBean.class);
				TableUtils.createTable(cs, AtividadeBean.class);
				TableUtils.createTable(cs, REquipAtivBean.class);
				TableUtils.createTable(cs, TurnoBean.class);
				TableUtils.createTable(cs, FuncBean.class);
				TableUtils.createTable(cs, OSBean.class);
				TableUtils.createTable(cs, ROSAtivBean.class);
				TableUtils.createTable(cs, RAtivParadaBean.class);
				TableUtils.createTable(cs, ParadaBean.class);
				TableUtils.createTable(cs, ItemCheckListBean.class);
				TableUtils.createTable(cs, BocalBean.class);
				TableUtils.createTable(cs, PressaoBocalBean.class);
				TableUtils.createTable(cs, RFuncaoAtivParBean.class);
				TableUtils.createTable(cs, LeiraBean.class);
				TableUtils.createTable(cs, REquipPneuBean.class);
				TableUtils.createTable(cs, PneuBean.class);

				TableUtils.createTable(cs, ConfigBean.class);
				TableUtils.createTable(cs, BoletimMMBean.class);
				TableUtils.createTable(cs, ApontMMBean.class);
				TableUtils.createTable(cs, CabecCLBean.class);
				TableUtils.createTable(cs, RespItemCLBean.class);
				TableUtils.createTable(cs, RendMMBean.class);
				TableUtils.createTable(cs, RecolhFertBean.class);
				TableUtils.createTable(cs, ImpleMMBean.class);
				TableUtils.createTable(cs, BackupApontaBean.class);
				TableUtils.createTable(cs, BoletimFertBean.class);
				TableUtils.createTable(cs, ApontFertBean.class);
				TableUtils.createTable(cs, InfColheitaBean.class);
				TableUtils.createTable(cs, InfPlantioBean.class);
				TableUtils.createTable(cs, ApontImpleMMBean.class);
				TableUtils.createTable(cs, MovLeiraBean.class);
				TableUtils.createTable(cs, CabecPneuBean.class);
				TableUtils.createTable(cs, ItemPneuBean.class);

			}
			else if((oldVersion == 2) && (newVersion > 2)){

				TableUtils.dropTable(cs, BoletimMMBean.class, true);
				TableUtils.dropTable(cs, ApontMMBean.class, true);
				TableUtils.dropTable(cs, BoletimFertBean.class, true);
				TableUtils.dropTable(cs, ApontFertBean.class, true);

				TableUtils.createTable(cs, BoletimMMBean.class);
				TableUtils.createTable(cs, ApontMMBean.class);
				TableUtils.createTable(cs, BoletimFertBean.class);
				TableUtils.createTable(cs, ApontFertBean.class);

			}
			else if((oldVersion == 3) && (newVersion > 3)){

				TableUtils.dropTable(cs, ConfigBean.class, true);

				TableUtils.createTable(cs, ConfigBean.class);

			}

		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
