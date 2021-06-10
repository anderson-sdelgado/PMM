package br.com.usinasantafe.pmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.pst.DatabaseHelper;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCheckListBean;

public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (DatabaseHelper.getInstance() == null) {
			new DatabaseHelper(context);
		}

		Log.i("PMM", "DATA HORA = " + Tempo.getInstance().dataComHora());
		teste();

		if (EnvioDadosServ.getInstance().verifDadosEnvio()) {
			Log.i("PMM", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}

	}

	public void teste() {

		BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
		List boletimMMFertList = boletimMMFertBean.all();

		Log.i("PMM", "AKI");

		for (int i = 0; i < boletimMMFertList.size(); i++) {

			boletimMMFertBean = (BoletimMMFertBean) boletimMMFertList.get(i);
			Log.i("PMM", "BOLETIM MM");
			Log.i("PMM", "idBoletim = " + boletimMMFertBean.getIdBolMMFert());
			Log.i("PMM", "idExtBoletim = " + boletimMMFertBean.getIdExtBolMMFert());
			Log.i("PMM", "matricFuncBoletim = " + boletimMMFertBean.getMatricFuncBolMMFert());
			Log.i("PMM", "idTurnoBoletim = " + boletimMMFertBean.getIdTurnoBolMMFert());
			Log.i("PMM", "dthrInicioBoletim = " + boletimMMFertBean.getDthrInicialBolMMFert());
			Log.i("PMM", "dthrFimBoletim = " + boletimMMFertBean.getDthrFinalBolMMFert());
			Log.i("PMM", "statusBoletim = " + boletimMMFertBean.getStatusBolMMFert());

		}

		ApontMMFertBean apontMMBean = new ApontMMFertBean();
		List apontaMMList = apontMMBean.all();

		for (int i = 0; i < apontaMMList.size(); i++) {

			apontMMBean = (ApontMMFertBean) apontaMMList.get(i);
			Log.i("PMM", "APONTAMENTO MM");
			Log.i("PMM", "idApont = " + apontMMBean.getIdApontMMFert());
			Log.i("PMM", "idBolApont = " + apontMMBean.getIdBolMMFert());
			Log.i("PMM", "dthrApont = " + apontMMBean.getDthrApontMMFert());
			Log.i("PMM", "transbApont = " + apontMMBean.getTransbApontMMFert());
			Log.i("PMM", "statusApont = " + apontMMBean.getStatusApontMMFert());

		}

		ImpleMMBean impleMMBean = new ImpleMMBean();
		List impleMMList = impleMMBean.all();

		for (int i = 0; i < impleMMList.size(); i++) {

			impleMMBean = (ImpleMMBean) impleMMList.get(i);
			Log.i("PMM", "IMPLEMENTO");
			Log.i("PMM", "posImpleMM = " + impleMMBean.getPosImpleMM());
			Log.i("PMM", "codEquipImpleMM = " + impleMMBean.getCodEquipImpleMM());

		}

		ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
		List apontImpleList = apontImpleMMBean.all();

		for (int l = 0; l < apontImpleList.size(); l++) {
			apontImpleMMBean = (ApontImpleMMBean) apontImpleList.get(l);
			Log.i("PMM", "APONT IMPLEMENTO MM");
			Log.i("PMM", "idApontImplemento = " + apontImpleMMBean.getIdApontImpleMM());
			Log.i("PMM", "idApontMM = " + apontImpleMMBean.getIdApontMMFert());
			Log.i("PMM", "posImplemento = " + apontImpleMMBean.getPosImpleMM());
			Log.i("PMM", "codEquipImplemento = " + apontImpleMMBean.getCodEquipImpleMM());
			Log.i("PMM", "dthrImpleMM = " + apontImpleMMBean.getDthrImpleMM());
		}

		RendMMBean rendMMBean = new RendMMBean();
		List rendimentoList = rendMMBean.all();

		for (int j = 0; j < rendimentoList.size(); j++) {
			rendMMBean = (RendMMBean) rendimentoList.get(j);
			Log.i("PMM", "RENDIMENTO");
			Log.i("PMM", "idRendimento = " + rendMMBean.getIdRendMM());
			Log.i("PMM", "idBolRendimento = " + rendMMBean.getIdBolMMFert());
			Log.i("PMM", "nroOSRendimento = " + rendMMBean.getNroOSRendMM());
			Log.i("PMM", "valorRendimento = " + rendMMBean.getValorRendMM());
			Log.i("PMM", "dthrRendimento = " + rendMMBean.getDthrRendMM());
		}


		ConfigBean configBean = new ConfigBean();
		List configList = configBean.all();

		for (int j = 0; j < configList.size(); j++) {

			configBean = (ConfigBean) configList.get(j);

			Log.i("PMM", "Config");
			Log.i("PMM", "equipConfig = " + configBean.getEquipConfig());
			Log.i("PMM", "senhaConfig = " + configBean.getSenhaConfig());
			Log.i("PMM", "ultTurnoCLConfig = " + configBean.getUltTurnoCLConfig());
			Log.i("PMM", "dtUltApontConfig = " + configBean.getDtUltApontConfig());
			Log.i("PMM", "osConfig = " + configBean.getOsConfig());
			Log.i("PMM", "horimetroConfig = " + configBean.getHorimetroConfig());
			Log.i("PMM", "dtServConfig = " + configBean.getDtServConfig());
			Log.i("PMM", "flagLogErro = " + configBean.getFlagLogErro());
			Log.i("PMM", "flagLogEnvio = " + configBean.getFlagLogEnvio());

		}

		EquipBean equipBean = new EquipBean();
		List equipTOList = equipBean.all();

		for (int j = 0; j < equipTOList.size(); j++) {

			equipBean = (EquipBean) equipTOList.get(j);

			Log.i("PMM", "Equipamento");
			Log.i("PMM", "idEquip = " + equipBean.getIdEquip());
			Log.i("PMM", "codEquip = " + equipBean.getNroEquip());
			Log.i("PMM", "codTurno = " + equipBean.getCodTurno());
			Log.i("PMM", "idCheckList = " + equipBean.getIdCheckList());
			Log.i("PMM", "tipoEquipFert = " + equipBean.getTipoEquipFert());

		}

		CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
		List cabecList = cabecCheckListBean.all();

		for (int j = 0; j < cabecList.size(); j++) {

			cabecCheckListBean = (CabecCheckListBean) cabecList.get(j);

			Log.i("PMM", "CabecCheckList");
			Log.i("PMM", "IdCabecCheck = " + cabecCheckListBean.getIdCabCL());
			Log.i("PMM", "DtCabecCheckList = " + cabecCheckListBean.getDtCabCL());
			Log.i("PMM", "StatusCabecCheckList = " + cabecCheckListBean.getStatusCabCL());

		}

		RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
		List respItemList = respItemCheckListBean.all();

		for (int j = 0; j < respItemList.size(); j++) {

			respItemCheckListBean = (RespItemCheckListBean) respItemList.get(j);

			Log.i("PMM", "RespItemCheckList");
			Log.i("PMM", "IdItemCheckList = " + respItemCheckListBean.getIdItCL());
			Log.i("PMM", "IdItItemCheckList = " + respItemCheckListBean.getIdItBDItCL());
			Log.i("PMM", "IdCabecItemCheckList = " + respItemCheckListBean.getIdCabItCL());
			Log.i("PMM", "OpcaoItemCheckList = " + respItemCheckListBean.getOpItCL());

		}

		LogErroBean logErroBean = new LogErroBean();
		List logErroList = logErroBean.all();

		for (int j = 0; j < logErroList.size(); j++) {

			logErroBean = (LogErroBean) logErroList.get(j);

			Log.i("PMM", "LogErro");
			Log.i("PMM", "IdLog = " + logErroBean.getIdLog());
			Log.i("PMM", "IdEquip = " + logErroBean.getIdEquip());
			Log.i("PMM", "Dthr = " + logErroBean.getDthr());
			Log.i("PMM", "Exception = " + logErroBean.getException());
			Log.i("PMM", "Status = " + logErroBean.getStatus());

		}
		Log.i("PMM", "versão = " + PMMContext.versaoAplic);

	}
}