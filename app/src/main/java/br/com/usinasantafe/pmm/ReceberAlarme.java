package br.com.usinasantafe.pmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pmm.bo.ManipDadosEnvio;
import br.com.usinasantafe.pmm.bo.Tempo;
import br.com.usinasantafe.pmm.pst.DatabaseHelper;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfigTO;

public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("PMM", "DATA HORA = " + Tempo.getInstance().datahora());
		ConfigTO configTO = new ConfigTO();
		List configList = configTO.all();
		configTO = (ConfigTO) configList.get(0);
		configList.clear();



		if(ManipDadosEnvio.getInstance().verifDadosEnvio()){
			Log.i("PMM", "ENVIANDO");
			ManipDadosEnvio.getInstance().envioDados(context);
		}
	}

}