package br.com.usinasantafe.pmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.MovLeiraBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pmm.util.EnvioDadosServ;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.model.pst.DatabaseHelper;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimFertBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.RespItemCLBean;

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

		BoletimMMBean boletimMMBean = new BoletimMMBean();
		List boletimMMList = boletimMMBean.all();

		Log.i("PMM", "AKI");

		for (int i = 0; i < boletimMMList.size(); i++) {

			boletimMMBean = (BoletimMMBean) boletimMMList.get(i);
			Log.i("PMM", "BOLETIM MM");
			Log.i("PMM", "idBoletim = " + boletimMMBean.getIdBolMM());
			Log.i("PMM", "idExtBoletim = " + boletimMMBean.getIdExtBolMM());
			Log.i("PMM", "dthrInicioBoletim = " + boletimMMBean.getDthrInicialBolMM());
			Log.i("PMM", "dthrFimBoletim = " + boletimMMBean.getDthrFinalBolMM());
			Log.i("PMM", "statusBoletim = " + boletimMMBean.getStatusBolMM());

		}

		ApontMMBean apontMMBean = new ApontMMBean();
		List apontaMMList = apontMMBean.all();

		for (int i = 0; i < apontaMMList.size(); i++) {

			apontMMBean = (ApontMMBean) apontaMMList.get(i);
			Log.i("PMM", "APONTAMENTO MM");
			Log.i("PMM", "idAponta = " + apontMMBean.getIdApontMM());
			Log.i("PMM", "idBolAponta = " + apontMMBean.getIdBolApontMM());
			Log.i("PMM", "idExtBolAponta = " + apontMMBean.getIdExtBolApontMM());
			Log.i("PMM", "dthrAponta = " + apontMMBean.getDthrApontMM());
			Log.i("PMM", "statusApontMM = " + apontMMBean.getStatusApontMM());

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
			Log.i("PMM", "idApontMM = " + apontImpleMMBean.getIdApontMM());
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
			Log.i("PMM", "idBolRendimento = " + rendMMBean.getIdBolRendMM());
			Log.i("PMM", "idExtBolRendimento = " + rendMMBean.getIdExtBolRendMM());
			Log.i("PMM", "nroOSRendimento = " + rendMMBean.getNroOSRendMM());
			Log.i("PMM", "valorRendimento = " + rendMMBean.getValorRendMM());
			Log.i("PMM", "dthrRendimento = " + rendMMBean.getDthrRendMM());
		}

		MovLeiraBean movLeiraBean = new MovLeiraBean();
		List movLeiraList = movLeiraBean.all();

		for (int l = 0; l < movLeiraList.size(); l++) {
			movLeiraBean = (MovLeiraBean) movLeiraList.get(l);
			Log.i("PMM", "MOVLEIRA");
			Log.i("PMM", "idMovLeira = " + movLeiraBean.getIdMovLeira());
			Log.i("PMM", "idBolMovLeira = " + movLeiraBean.getIdBolMovLeira());
			Log.i("PMM", "idExtBolMovLeira = " + movLeiraBean.getIdExtBolMovLeira());
			Log.i("PMM", "idLeira = " + movLeiraBean.getIdLeira());
			Log.i("PMM", "tipoMovLeira = " + movLeiraBean.getTipoMovLeira());
			Log.i("PMM", "dataHoraMovLeira = " + movLeiraBean.getDataHoraMovLeira());
			Log.i("PMM", "statusMovLeira = " + movLeiraBean.getStatusMovLeira());
		}

		BoletimFertBean boletimFertBean = new BoletimFertBean();
		List boletimFertList = boletimFertBean.all();

		for (int i = 0; i < boletimFertList.size(); i++) {

			boletimFertBean = (BoletimFertBean) boletimFertList.get(i);
			Log.i("PMM", "BOLETIM FERT");
			Log.i("PMM", "idBoletim = " + boletimFertBean.getIdBolFert());
			Log.i("PMM", "idExtBoletim = " + boletimFertBean.getIdExtBolFert());
			Log.i("PMM", "dthrInicioBoletim = " + boletimFertBean.getDthrInicialBolFert());
			Log.i("PMM", "dthrFimBoletim = " + boletimFertBean.getDthrFinalBolFert());
			Log.i("PMM", "statusBoletim = " + boletimFertBean.getStatusBolFert());

		}

		ApontFertBean apontFertBean = new ApontFertBean();
		List apontaAplicFertList = apontFertBean.all();

		for (int j = 0; j < apontaAplicFertList.size(); j++) {
			apontFertBean = (ApontFertBean) apontaAplicFertList.get(j);

			Log.i("PMM", "APONTA FERT");
			Log.i("PMM", "idApontaAplicFert = " + apontFertBean.getIdApontFert());
			Log.i("PMM", "idBolApontaAplicFert = " + apontFertBean.getIdBolApontFert());
			Log.i("PMM", "idExtBolApontaAplicFert = " + apontFertBean.getIdExtBolApontFert());
			Log.i("PMM", "dthrApontFert = " + apontFertBean.getDthrApontFert());
			Log.i("PMM", "statusApontFert = " + apontFertBean.getStatusApontFert());

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

		CabecCLBean cabecCLBean = new CabecCLBean();
		List cabecList = cabecCLBean.all();

		for (int j = 0; j < cabecList.size(); j++) {

			cabecCLBean = (CabecCLBean) cabecList.get(j);

			Log.i("PMM", "CabecCheckList");
			Log.i("PMM", "IdCabecCheck = " + cabecCLBean.getIdCabCL());
			Log.i("PMM", "DtCabecCheckList = " + cabecCLBean.getDtCabCL());
			Log.i("PMM", "StatusCabecCheckList = " + cabecCLBean.getStatusCabCL());

		}

		RespItemCLBean respItemCLBean = new RespItemCLBean();
		List respItemList = respItemCLBean.all();

		for (int j = 0; j < respItemList.size(); j++) {

			respItemCLBean = (RespItemCLBean) respItemList.get(j);

			Log.i("PMM", "RespItemCheckList");
			Log.i("PMM", "IdItemCheckList = " + respItemCLBean.getIdItCL());
			Log.i("PMM", "IdItItemCheckList = " + respItemCLBean.getIdItBDItCL());
			Log.i("PMM", "IdCabecItemCheckList = " + respItemCLBean.getIdCabItCL());
			Log.i("PMM", "OpcaoItemCheckList = " + respItemCLBean.getOpItCL());

		}

		Log.i("PMM", "versão = " + PMMContext.versaoAplic);

	}
}