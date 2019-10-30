package br.com.usinasantafe.pmm.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import br.com.usinasantafe.pmm.control.ApontCTR;
import br.com.usinasantafe.pmm.control.BoletimCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;

import android.os.AsyncTask;
import android.util.Log;

public class ConHttpPostCadGenerico extends AsyncTask<String, Void, String> {


	private static ConHttpPostCadGenerico instance = null;
	private Map<String, Object> parametrosPost = null;

	public ConHttpPostCadGenerico() {

	}

    public static ConHttpPostCadGenerico getInstance() {
        if (instance == null)
        instance = new ConHttpPostCadGenerico();
        return instance;
    }


	@Override
	protected String doInBackground(String... arg) {
		// TODO Auto-generated method stub
		
		BufferedReader bufferedReader = null;
		String resultado = null;
		
		String url = arg[0];
		
		try {

			String parametros = getQueryString(parametrosPost);
			URL urlCon = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();

			OutputStream out = connection.getOutputStream();
			byte[] bytes = parametros.getBytes("UTF8");
			out.write(bytes);
			out.flush();
			out.close();

			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LS = System.getProperty("line.separator");
			while((line = bufferedReader.readLine()) != null){
				stringBuffer.append(line + LS);
			}
			bufferedReader.close();
			resultado = stringBuffer.toString();

			connection.disconnect();
			
		} catch (Exception e) {
			Log.i("PMM", "Erro = " + e);
			EnvioDadosServ.getInstance().setEnviando(false);
			Tempo.getInstance().setEnvioDado(true);
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (Exception er) {
					Log.i("PMM", "Erro = " + er);
				}
				
			}
		}
		finally{
			
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (Exception e) {
					Log.i("PMM", "Erro = " + e);
				}
				
			}
			
		}
		return resultado;
		
	}

	protected void onPostExecute(String result) {

		try {
			EnvioDadosServ.getInstance().setEnviando(false);
			Log.i("ECM", "VALOR RECEBIDO --> " + result);
			if(result.trim().equals("GRAVOU-CHECKLIST")){
				CheckListCTR checkListCTR = new CheckListCTR();
				checkListCTR.delChecklist();
			}
			else{
				if (result.trim().contains("BOLABERTOMM")) {
					BoletimCTR boletimCTR = new BoletimCTR();
					boletimCTR.updBolAbertoMM(result);
				} else if (result.trim().contains("BOLFECHADOMM")) {
					BoletimCTR boletimCTR = new BoletimCTR();
					boletimCTR.delBolFechadoMM(result);
				} else if (result.trim().contains("APONTMM")) {
					ApontCTR apontCTR = new ApontCTR();
					apontCTR.updateApontMM(result);
				} else if (result.trim().contains("BOLABERTOFERT")) {
					BoletimCTR boletimCTR = new BoletimCTR();
					boletimCTR.updBolAbertoFert(result);
				} else if (result.trim().contains("BOLFECHADOFERT")) {
					BoletimCTR boletimCTR = new BoletimCTR();
					boletimCTR.delBolFechadoFert(result);
				} else if (result.trim().contains("APONTFERT")) {
					ApontCTR apontCTR = new ApontCTR();
					apontCTR.updateApontaFert(result);
				}
			}
		} catch (Exception e) {

			Log.i("PMM", "Erro2 = " + e);
		}
		
    }

	public void setParametrosPost(Map<String, Object> parametrosPost) {
		this.parametrosPost = parametrosPost;
	}

	private String getQueryString(Map<String, Object> params) throws Exception {
		if (params == null || params.size() == 0) {
			return null;
		}
		String urlParams = null;
		Iterator<String> e = (Iterator<String>) params.keySet().iterator();
		while (e.hasNext()) {
			String chave = (String) e.next();
			Object objValor = params.get(chave);
			String valor = objValor.toString();
			urlParams = urlParams == null ? "" : urlParams + "&";
			urlParams += chave + "=" + valor;
		}
		return urlParams;
	}

}
