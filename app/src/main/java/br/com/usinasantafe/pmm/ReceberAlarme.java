package br.com.usinasantafe.pmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.pst.DatabaseHelper;
import br.com.usinasantafe.pmm.to.estaticas.EquipTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.to.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.to.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.to.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.to.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.to.variaveis.RespItemCLTO;

public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("PMM", "DATA HORA = " + Tempo.getInstance().dataComHora());
		teste();

		if(EnvioDadosServ.getInstance().verifDadosEnvio()){
			Log.i("PMM", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}
	}

	public void teste() {

		BoletimMMTO boletimMMTO = new BoletimMMTO();
		List boletimMMList = boletimMMTO.all();

		Log.i("PMM", "AKI");

		for (int i = 0; i < boletimMMList.size(); i++) {

			boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
			Log.i("PMM", "BOLETIM MM");
			Log.i("PMM", "idBoletim = " + boletimMMTO.getIdBolMM());
			Log.i("PMM", "idExtBoletim = " + boletimMMTO.getIdExtBolMM());
			Log.i("PMM", "dthrInicioBoletim = " + boletimMMTO.getDthrInicialBolMM());
			Log.i("PMM", "dthrFimBoletim = " + boletimMMTO.getDthrFinalBolMM());
			Log.i("PMM", "statusBoletim = " + boletimMMTO.getStatusBolMM());

		}

		ApontMMTO apontMMTO = new ApontMMTO();
		List apontaMMList = apontMMTO.all();

		for (int i = 0; i < apontaMMList.size(); i++) {

			apontMMTO = (ApontMMTO) apontaMMList.get(i);
			Log.i("PMM", "APONTAMENTO MM");
			Log.i("PMM", "idAponta = " + apontMMTO.getIdApontMM());
			Log.i("PMM", "idBolAponta = " + apontMMTO.getIdBolApontMM());
			Log.i("PMM", "idExtBolAponta = " + apontMMTO.getIdExtBolApontMM());
			Log.i("PMM", "dthrAponta = " + apontMMTO.getDthrApontMM());
			Log.i("PMM", "statusApontMM = " + apontMMTO.getStatusApontMM());

		}

		BoletimFertTO boletimFertTO = new BoletimFertTO();
		List boletimFertList = boletimFertTO.all();

		for (int i = 0; i < boletimFertList.size(); i++) {

			boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
			Log.i("PMM", "BOLETIM FERT");
			Log.i("PMM", "idBoletim = " + boletimFertTO.getIdBolFert());
			Log.i("PMM", "idExtBoletim = " + boletimFertTO.getIdExtBolFert());
			Log.i("PMM", "dthrInicioBoletim = " + boletimFertTO.getDthrInicialBolFert());
			Log.i("PMM", "dthrFimBoletim = " + boletimFertTO.getDthrFinalBolFert());
			Log.i("PMM", "statusBoletim = " + boletimFertTO.getStatusBolFert());

		}

		ApontFertTO apontFertTO = new ApontFertTO();
		List apontaAplicFertList = apontFertTO.all();

		for (int j = 0; j < apontaAplicFertList.size(); j++) {
			apontFertTO = (ApontFertTO) apontaAplicFertList.get(j);

			Log.i("PMM", "APONTA FERT");
			Log.i("PMM", "idApontaAplicFert = " + apontFertTO.getIdApontFert());
			Log.i("PMM", "idBolApontaAplicFert = " + apontFertTO.getIdBolApontFert());
			Log.i("PMM", "idExtBolApontaAplicFert = " + apontFertTO.getIdExtBolApontFert());
			Log.i("PMM", "statusApontFert = " + apontFertTO.getStatusApontFert());

		}

		ConfigTO configTO = new ConfigTO();
		List configList = configTO.all();

		for (int j = 0; j < configList.size(); j++) {

			configTO = (ConfigTO) configList.get(j);

			Log.i("PMM", "Config");
			Log.i("PMM", "equipConfig = " + configTO.getEquipConfig());
			Log.i("PMM", "senhaConfig = " + configTO.getSenhaConfig());
			Log.i("PMM", "ultTurnoCLConfig = " + configTO.getUltTurnoCLConfig());
			Log.i("PMM", "dtUltApontConfig = " + configTO.getDtUltApontConfig());
			Log.i("PMM", "osConfig = " + configTO.getOsConfig());
			Log.i("PMM", "horimetroConfig = " + configTO.getHorimetroConfig());

		}

		EquipTO equipTO = new EquipTO();
		List equipTOList = equipTO.all();

		for (int j = 0; j < equipTOList.size(); j++) {

			equipTO = (EquipTO) equipTOList.get(j);

			Log.i("PMM", "Equipamento");
			Log.i("PMM", "idEquip = " + equipTO.getIdEquip());
			Log.i("PMM", "codEquip = " + equipTO.getNroEquip());
			Log.i("PMM", "codTurno = " + equipTO.getCodTurno());
			Log.i("PMM", "idCheckList = " + equipTO.getIdCheckList());
			Log.i("PMM", "tipoEquipFert = " + equipTO.getTipoEquipFert());

		}

		CabecCLTO cabecCLTO = new CabecCLTO();
		List cabecList = cabecCLTO.all();

		for (int j = 0; j < cabecList.size(); j++) {

			cabecCLTO = (CabecCLTO) cabecList.get(j);

			Log.i("PMM", "CabecCheckList");
			Log.i("PMM", "IdCabecCheck = " + cabecCLTO.getIdCabCL());
			Log.i("PMM", "DtCabecCheckList = " + cabecCLTO.getDtCabCL());
			Log.i("PMM", "StatusCabecCheckList = " + cabecCLTO.getStatusCabCL());
			Log.i("PMM", "DtAtualCheckList = " + cabecCLTO.getDtAtualCabCL());

		}

		RespItemCLTO respItemCLTO = new RespItemCLTO();
		List respItemList = respItemCLTO.all();

		for (int j = 0; j < respItemList.size(); j++) {

			respItemCLTO = (RespItemCLTO) respItemList.get(j);

			Log.i("PMM", "RespItemCheckList");
			Log.i("PMM", "IdItemCheckList = " + respItemCLTO.getIdItCL());
			Log.i("PMM", "IdItItemCheckList = " + respItemCLTO.getIdItBDItCL());
			Log.i("PMM", "IdCabecItemCheckList = " + respItemCLTO.getIdCabItCL());
			Log.i("PMM", "OpcaoItemCheckList = " + respItemCLTO.getOpItCL());

		}

		Log.i("PMM", "versão = " + PMMContext.versaoAplic);

	}
}