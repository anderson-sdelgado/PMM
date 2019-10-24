package br.com.usinasantafe.pmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pmm.bean.variaveis.ImpleMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontImpleMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.MovLeiraTO;
import br.com.usinasantafe.pmm.bean.variaveis.RendMMTO;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.pst.DatabaseHelper;
import br.com.usinasantafe.pmm.bean.estaticas.EquipTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.ApontMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimFertTO;
import br.com.usinasantafe.pmm.bean.variaveis.BoletimMMTO;
import br.com.usinasantafe.pmm.bean.variaveis.CabecCLTO;
import br.com.usinasantafe.pmm.bean.variaveis.ConfigTO;
import br.com.usinasantafe.pmm.bean.variaveis.RespItemCLTO;

public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("PMM", "DATA HORA = " + Tempo.getInstance().dataComHora());
//		teste();

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

		ImpleMMTO impleMMTO = new ImpleMMTO();
		List impleMMList = impleMMTO.all();

		for (int i = 0; i < impleMMList.size(); i++) {

			impleMMTO = (ImpleMMTO) impleMMList.get(i);
			Log.i("PMM", "IMPLEMENTO MM");
			Log.i("PMM", "posImpleMM = " + impleMMTO.getPosImpleMM());
			Log.i("PMM", "codEquipImpleMM = " + impleMMTO.getCodEquipImpleMM());

		}

		ApontImpleMMTO apontImpleMMTO = new ApontImpleMMTO();
		List apontImpleList = apontImpleMMTO.all();

		for (int l = 0; l < apontImpleList.size(); l++) {
			apontImpleMMTO = (ApontImpleMMTO) apontImpleList.get(l);
			Log.i("PMM", "IMPLEMENTO");
			Log.i("PMM", "idApontImplemento = " + apontImpleMMTO.getIdApontImpleMM());
			Log.i("PMM", "idApontMM = " + apontImpleMMTO.getIdApontMM());
			Log.i("PMM", "posImplemento = " + apontImpleMMTO.getPosImpleMM());
			Log.i("PMM", "codEquipImplemento = " + apontImpleMMTO.getCodEquipImpleMM());
		}

		RendMMTO rendMMTO = new RendMMTO();
		List rendimentoList = rendMMTO.all();

		for (int j = 0; j < rendimentoList.size(); j++) {
			rendMMTO = (RendMMTO) rendimentoList.get(j);
			Log.i("PMM", "RENDIMENTO");
			Log.i("PMM", "idRendimento = " + rendMMTO.getIdRendMM());
			Log.i("PMM", "idBolRendimento = " + rendMMTO.getIdBolRendMM());
			Log.i("PMM", "idExtBolRendimento = " + rendMMTO.getIdExtBolRendMM());
			Log.i("PMM", "nroOSRendimento = " + rendMMTO.getNroOSRendMM());
			Log.i("PMM", "valorRendimento = " + rendMMTO.getValorRendMM());
			Log.i("PMM", "dthrRendimento = " + rendMMTO.getDthrRendMM());
		}

		MovLeiraTO movLeiraTO = new MovLeiraTO();
		List movLeiraList = movLeiraTO.all();

		for (int l = 0; l < movLeiraList.size(); l++) {
			movLeiraTO = (MovLeiraTO) movLeiraList.get(l);
			Log.i("PMM", "MOVLEIRA");
			Log.i("PMM", "idMovLeira = " + movLeiraTO.getIdMovLeira());
			Log.i("PMM", "idBolMovLeira = " + movLeiraTO.getIdBolMovLeira());
			Log.i("PMM", "idExtBolMovLeira = " + movLeiraTO.getIdExtBolMovLeira());
			Log.i("PMM", "idLeira = " + movLeiraTO.getIdLeira());
			Log.i("PMM", "tipoMovLeira = " + movLeiraTO.getTipoMovLeira());
			Log.i("PMM", "dataHoraMovLeira = " + movLeiraTO.getDataHoraMovLeira());
			Log.i("PMM", "statusMovLeira = " + movLeiraTO.getStatusMovLeira());
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