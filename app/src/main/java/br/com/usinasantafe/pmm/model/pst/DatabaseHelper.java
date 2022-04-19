package br.com.usinasantafe.pmm.model.pst;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.usinasantafe.pmm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.BocalBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ComponenteBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.FrenteBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ItemOSMecanBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.LeiraBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.PneuBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.PressaoBocalBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.PropriedadeBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.RAtivParadaBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.REquipPneuBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.ServicoBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontImplMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMecanBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimPneuBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CarretaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ImplementoMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ItemCalibPneuBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.MovLeiraBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RecolhFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCheckListBean;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "mm_db";
	public static final int FORCA_BD_VERSION = 2;

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

			TableUtils.createTable(cs, AtividadeBean.class);
			TableUtils.createTable(cs, BocalBean.class);
			TableUtils.createTable(cs, ComponenteBean.class);
			TableUtils.createTable(cs, EquipBean.class);
			TableUtils.createTable(cs, EquipSegBean.class);
			TableUtils.createTable(cs, FrenteBean.class);
			TableUtils.createTable(cs, FuncBean.class);
			TableUtils.createTable(cs, ItemCheckListBean.class);
			TableUtils.createTable(cs, ItemOSMecanBean.class);
			TableUtils.createTable(cs, LeiraBean.class);
			TableUtils.createTable(cs, MotoMecBean.class);
			TableUtils.createTable(cs, OSBean.class);
			TableUtils.createTable(cs, ParadaBean.class);
			TableUtils.createTable(cs, PneuBean.class);
			TableUtils.createTable(cs, PressaoBocalBean.class);
			TableUtils.createTable(cs, ProdutoBean.class);
			TableUtils.createTable(cs, PropriedadeBean.class);
			TableUtils.createTable(cs, RAtivParadaBean.class);
			TableUtils.createTable(cs, REquipAtivBean.class);
			TableUtils.createTable(cs, REquipPneuBean.class);
			TableUtils.createTable(cs, RFuncaoAtivParBean.class);
			TableUtils.createTable(cs, ROSAtivBean.class);
			TableUtils.createTable(cs, ServicoBean.class);
			TableUtils.createTable(cs, TurnoBean.class);

			TableUtils.createTable(cs, ApontImplMMBean.class);
			TableUtils.createTable(cs, ApontMecanBean.class);
			TableUtils.createTable(cs, ApontMMFertBean.class);
			TableUtils.createTable(cs, BoletimMMFertBean.class);
			TableUtils.createTable(cs, BoletimPneuBean.class);
			TableUtils.createTable(cs, CabecCheckListBean.class);
			TableUtils.createTable(cs, CarregCompBean.class);
			TableUtils.createTable(cs, CarretaBean.class);
			TableUtils.createTable(cs, CECBean.class);
			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, ImplementoMMBean.class);
			TableUtils.createTable(cs, InfColheitaBean.class);
			TableUtils.createTable(cs, InfPlantioBean.class);
			TableUtils.createTable(cs, ItemCalibPneuBean.class);
			TableUtils.createTable(cs, LogErroBean.class);
			TableUtils.createTable(cs, LogProcessoBean.class);
			TableUtils.createTable(cs, MovLeiraBean.class);
			TableUtils.createTable(cs, PreCECBean.class);
			TableUtils.createTable(cs, RecolhFertBean.class);
			TableUtils.createTable(cs, RendMMBean.class);
			TableUtils.createTable(cs, RespItemCheckListBean.class);
			
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

			if((oldVersion <= 1) && (newVersion > 1)){

				TableUtils.dropTable(cs, AtividadeBean.class, true);
				TableUtils.dropTable(cs, BocalBean.class, true);
				TableUtils.dropTable(cs, ComponenteBean.class, true);
				TableUtils.dropTable(cs, EquipBean.class, true);
				TableUtils.dropTable(cs, EquipSegBean.class, true);
				TableUtils.dropTable(cs, FrenteBean.class, true);
				TableUtils.dropTable(cs, FuncBean.class, true);
				TableUtils.dropTable(cs, ItemCheckListBean.class, true);
				TableUtils.dropTable(cs, ItemOSMecanBean.class, true);
				TableUtils.dropTable(cs, LeiraBean.class, true);
				TableUtils.dropTable(cs, MotoMecBean.class, true);
				TableUtils.dropTable(cs, OSBean.class, true);
				TableUtils.dropTable(cs, ParadaBean.class, true);
				TableUtils.dropTable(cs, PneuBean.class, true);
				TableUtils.dropTable(cs, PressaoBocalBean.class, true);
				TableUtils.dropTable(cs, ProdutoBean.class, true);
				TableUtils.dropTable(cs, PropriedadeBean.class, true);
				TableUtils.dropTable(cs, RAtivParadaBean.class, true);
				TableUtils.dropTable(cs, REquipAtivBean.class, true);
				TableUtils.dropTable(cs, REquipPneuBean.class, true);
				TableUtils.dropTable(cs, RFuncaoAtivParBean.class, true);
				TableUtils.dropTable(cs, ROSAtivBean.class, true);
				TableUtils.dropTable(cs, ServicoBean.class, true);
				TableUtils.dropTable(cs, TurnoBean.class, true);

				TableUtils.dropTable(cs, ApontImplMMBean.class, true);
				TableUtils.dropTable(cs, ApontMecanBean.class, true);
				TableUtils.dropTable(cs, ApontMMFertBean.class, true);
				TableUtils.dropTable(cs, BoletimMMFertBean.class, true);
				TableUtils.dropTable(cs, BoletimPneuBean.class, true);
				TableUtils.dropTable(cs, CabecCheckListBean.class, true);
				TableUtils.dropTable(cs, CarregCompBean.class, true);
				TableUtils.dropTable(cs, CarretaBean.class, true);
				TableUtils.dropTable(cs, CECBean.class, true);
				TableUtils.dropTable(cs, ConfigBean.class, true);
				TableUtils.dropTable(cs, ImplementoMMBean.class, true);
				TableUtils.dropTable(cs, InfColheitaBean.class, true);
				TableUtils.dropTable(cs, InfPlantioBean.class, true);
				TableUtils.dropTable(cs, ItemCalibPneuBean.class, true);
				TableUtils.dropTable(cs, LogErroBean.class, true);
				TableUtils.dropTable(cs, LogProcessoBean.class, true);
				TableUtils.dropTable(cs, MovLeiraBean.class, true);
				TableUtils.dropTable(cs, PreCECBean.class, true);
				TableUtils.dropTable(cs, RecolhFertBean.class, true);
				TableUtils.dropTable(cs, RendMMBean.class, true);
				TableUtils.dropTable(cs, RespItemCheckListBean.class, true);

				///////////////////////////////////////////////////////////////

				TableUtils.createTable(cs, AtividadeBean.class);
				TableUtils.createTable(cs, BocalBean.class);
				TableUtils.createTable(cs, ComponenteBean.class);
				TableUtils.createTable(cs, EquipBean.class);
				TableUtils.createTable(cs, EquipSegBean.class);
				TableUtils.createTable(cs, FrenteBean.class);
				TableUtils.createTable(cs, FuncBean.class);
				TableUtils.createTable(cs, ItemCheckListBean.class);
				TableUtils.createTable(cs, ItemOSMecanBean.class);
				TableUtils.createTable(cs, LeiraBean.class);
				TableUtils.createTable(cs, MotoMecBean.class);
				TableUtils.createTable(cs, OSBean.class);
				TableUtils.createTable(cs, ParadaBean.class);
				TableUtils.createTable(cs, PneuBean.class);
				TableUtils.createTable(cs, PressaoBocalBean.class);
				TableUtils.createTable(cs, ProdutoBean.class);
				TableUtils.createTable(cs, PropriedadeBean.class);
				TableUtils.createTable(cs, RAtivParadaBean.class);
				TableUtils.createTable(cs, REquipAtivBean.class);
				TableUtils.createTable(cs, REquipPneuBean.class);
				TableUtils.createTable(cs, RFuncaoAtivParBean.class);
				TableUtils.createTable(cs, ROSAtivBean.class);
				TableUtils.createTable(cs, ServicoBean.class);
				TableUtils.createTable(cs, TurnoBean.class);

				TableUtils.createTable(cs, ApontImplMMBean.class);
				TableUtils.createTable(cs, ApontMecanBean.class);
				TableUtils.createTable(cs, ApontMMFertBean.class);
				TableUtils.createTable(cs, BoletimMMFertBean.class);
				TableUtils.createTable(cs, BoletimPneuBean.class);
				TableUtils.createTable(cs, CabecCheckListBean.class);
				TableUtils.createTable(cs, CarregCompBean.class);
				TableUtils.createTable(cs, CarretaBean.class);
				TableUtils.createTable(cs, CECBean.class);
				TableUtils.createTable(cs, ConfigBean.class);
				TableUtils.createTable(cs, ImplementoMMBean.class);
				TableUtils.createTable(cs, InfColheitaBean.class);
				TableUtils.createTable(cs, InfPlantioBean.class);
				TableUtils.createTable(cs, ItemCalibPneuBean.class);
				TableUtils.createTable(cs, LogErroBean.class);
				TableUtils.createTable(cs, LogProcessoBean.class);
				TableUtils.createTable(cs, MovLeiraBean.class);
				TableUtils.createTable(cs, PreCECBean.class);
				TableUtils.createTable(cs, RecolhFertBean.class);
				TableUtils.createTable(cs, RendMMBean.class);
				TableUtils.createTable(cs, RespItemCheckListBean.class);

			}

		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
