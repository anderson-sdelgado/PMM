package br.com.usinasantafe.pmm.bo;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.pmm.conWEB.ConHttpGetBDGenerico;
import br.com.usinasantafe.pmm.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pmm.pst.GenericRecordable;

import com.google.gson.Gson;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class ManipDadosReceb {

	private ArrayList tabAtualArrayList;
	private static ManipDadosReceb instance = null;
	private int contAtualBD = 0;
	private String classe = "";
	private ProgressDialog progressDialog;
	private int qtdeBD = 0;
	private GenericRecordable genericRecordable;
	private Context context;
	private int tipoReceb;
	private UrlsConexaoHttp urlsConexaoHttp;
	
	public ManipDadosReceb() {

		genericRecordable = new GenericRecordable();
	}
	
    public static ManipDadosReceb getInstance() {
        if (instance == null)
        instance = new ManipDadosReceb();
        return instance;
    }
	
	@SuppressWarnings("unchecked")
	public void manipularDadosHttp(String tipo, String result){

		if(!result.equals("")){

			try{

				Log.i("PMM", "TIPO -> " + tipo);
				Log.i("PMM", "RESULT -> " + result);

				JSONObject jObj = new JSONObject(result);
				JSONArray jsonArray = jObj.getJSONArray("dados");
				Class classe = Class.forName(manipLocalClasse(tipo));
				genericRecordable.deleteAll(classe);

				for(int i = 0; i < jsonArray.length(); i++){

					JSONObject objeto = jsonArray.getJSONObject(i);
					Gson gson = new Gson();
					genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);

				}

				Log.i("PMM", " SALVOU DADOS ");

				if(contAtualBD > 0){
					atualizandoBD();
				}

			}
			catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("PMM", "Erro Manip = " + e);
			}

		}
		else{
			encerrar();
		}

	}
	

	public void atualizarBD(ProgressDialog progressDialog){
		
		try {
			
			this.tipoReceb = 1;
			this.progressDialog = progressDialog;
			tabAtualArrayList = new ArrayList();
	        Class<?> retClasse = Class.forName(urlsConexaoHttp.localUrl); 

	        for (Field field : retClasse.getDeclaredFields()) {
	            String campo = field.getName();
	            Log.i("PMM", "Campo = " + campo);
	            if(campo.contains("TO")){
	            	tabAtualArrayList.add(campo);
	            }
	            
	        }
	        
	        classe = (String) tabAtualArrayList.get(contAtualBD);
			
	        String[] url = {classe};
			
		    contAtualBD++;

	        ConHttpGetBDGenerico conHttpGetBDGenerico = new ConHttpGetBDGenerico();
	        conHttpGetBDGenerico.execute(url);
	        
		} catch (Exception e) {
			Log.i("PMM", "ERRO Manip2 = " + e);
		}
        
	}

	public void atualizarBD() {

		try {

			this.tipoReceb = 2;
			tabAtualArrayList = new ArrayList();
			Class<?> retClasse = Class.forName(urlsConexaoHttp.localUrl);

			for (Field field : retClasse.getDeclaredFields()) {
				String campo = field.getName();
				Log.i("PMM", "Campo = " + campo);
				if (campo.contains("TO")) {
					tabAtualArrayList.add(campo);
				}

			}

			classe = (String) tabAtualArrayList.get(contAtualBD);

			String[] url = {classe};

			contAtualBD++;

			ConHttpGetBDGenerico conHttpGetBDGenerico = new ConHttpGetBDGenerico();
			conHttpGetBDGenerico.execute(url);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("PMM", "Erro Manip2 = " + e);
		}

	}
	
	public void atualizandoBD(){

		if(this.tipoReceb == 1){
		
			qtdeBD = tabAtualArrayList.size();
			
			if(contAtualBD < tabAtualArrayList.size()){
				
				this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);
		        classe = (String) tabAtualArrayList.get(contAtualBD);
				String[] url = {classe};
				contAtualBD++;

				ConHttpGetBDGenerico conHttpGetBDGenerico = new ConHttpGetBDGenerico();
		        conHttpGetBDGenerico.execute(url);
		        
			}
			else
			{
				this.progressDialog.dismiss();
				contAtualBD = 0;
				ManipDadosEnvio.getInstance().envioDados(this.context);
				AlertDialog.Builder alerta = new AlertDialog.Builder(this.context);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("FOI ATUALIZADO COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
	
					}
				});
				
				alerta.show();
			}
		
		}
		else if(this.tipoReceb == 2){
			
			qtdeBD = tabAtualArrayList.size();
			
			if(contAtualBD < tabAtualArrayList.size()){
				
		        classe = (String) tabAtualArrayList.get(contAtualBD);
				String[] url = {classe};
				contAtualBD++;

				ConHttpGetBDGenerico conHttpGetBDGenerico = new ConHttpGetBDGenerico();
		        conHttpGetBDGenerico.execute(url);
		        
			}
			else
			{
				contAtualBD = 0;
			}
			
		}

	}

	public void encerrar(){
		
		if(this.tipoReceb == 1){
			
			this.progressDialog.dismiss();
			AlertDialog.Builder alerta = new AlertDialog.Builder(this.context);
			alerta.setTitle("ATENCAO");
			alerta.setMessage("FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
			alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
	
				}
			});
			
			alerta.show();
			
		}
	}

	public void tempo(){

		try {

			Class<?> retClasse = Class.forName(urlsConexaoHttp.localUrl);
			tabAtualArrayList = new ArrayList();

			for (Field field : retClasse.getDeclaredFields()) {
				String campo = field.getName();
				Log.i("PMM", "Campo = " + campo);
				if (campo.equals("datahorahttp")) {
					Log.i("PMM", "Campo = " + campo);
					tabAtualArrayList.add(campo);
				}

			}

			classe = (String) tabAtualArrayList.get(contAtualBD);

			String[] url = {classe};

			ConHttpGetBDGenerico conHttpGetBDGenerico = new ConHttpGetBDGenerico();
			conHttpGetBDGenerico.execute(url);

		} catch (Exception e) {
			Log.i("PMM", "Erro Manip2 = " + e);
		}

	}

	public String manipLocalClasse(String classe){
	    if(classe.contains("TO")){
	    	classe = urlsConexaoHttp.localPSTEstatica + classe;
	    }
		return classe;
	}
	
	public void setContext(Context context){
		this.context = context;
	}
	
}
