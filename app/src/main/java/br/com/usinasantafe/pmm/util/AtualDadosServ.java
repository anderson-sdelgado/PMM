package br.com.usinasantafe.pmm.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.pmm.util.conHttp.GetBDGenerico;
import br.com.usinasantafe.pmm.model.pst.GenericRecordable;

import com.google.gson.Gson;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

public class AtualDadosServ {

	private ArrayList tabAtualArrayList;
	private static AtualDadosServ instance = null;
	private int contAtualBD = 0;
	private String classe = "";
	private ProgressDialog progressDialog;
	private int qtdeBD = 0;
	private GenericRecordable genericRecordable;
	private Context telaAtual;
	private Class telaProx;
	private int tipoReceb;
	private UrlsConexaoHttp urlsConexaoHttp;
	
	public AtualDadosServ() {

		genericRecordable = new GenericRecordable();
	}
	
    public static AtualDadosServ getInstance() {
        if (instance == null)
        instance = new AtualDadosServ();
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
	

	public void atualTodasTabBD(Context telaAtual, ProgressDialog progressDialog){
		
		try {
			
			this.tipoReceb = 1;
			this.telaAtual = telaAtual;
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

	        GetBDGenerico getBDGenerico = new GetBDGenerico();
	        getBDGenerico.execute(url);
	        
		} catch (Exception e) {
			Log.i("PMM", "ERRO 2 = " + e);
		}
        
	}

	public void atualGenericoBD(Context telaAtual, Class telaProx, ProgressDialog progressDialog, ArrayList classeArrayList){

		try {

			this.tipoReceb = 1;
			this.telaAtual = telaAtual;
			this.telaProx = telaProx;
			this.progressDialog = progressDialog;
			tabAtualArrayList = new ArrayList();

			Class<?> retClasse = Class.forName(urlsConexaoHttp.localUrl);

			for (Field field : retClasse.getDeclaredFields()) {
				String campo = field.getName();
				Log.i("PMM", "Campo = " + campo);
				for (int i = 0; i < classeArrayList.size(); i++) {
					String classe = (String) classeArrayList.get(i);
					if(campo.equals(classe)){
						tabAtualArrayList.add(campo);
					}
				}
			}

			classe = (String) tabAtualArrayList.get(contAtualBD);
			String[] url = {classe};
			contAtualBD++;

			GetBDGenerico getBDGenerico = new GetBDGenerico();
			getBDGenerico.execute(url);

		} catch (Exception e) {
			Log.i("PMM", "ERRO = " + e);
		}

	}
	
	public void atualizandoBD(){

		if(this.tipoReceb == 1){
		
			qtdeBD = tabAtualArrayList.size();

			Log.i("PMM", "CHEGOU AKI 1");
			Log.i("PMM", "contAtualBD = " + contAtualBD);
			Log.i("PMM", "tabAtualArrayList.size() = " + tabAtualArrayList.size());

			if(contAtualBD < tabAtualArrayList.size()){
				
				this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);
		        classe = (String) tabAtualArrayList.get(contAtualBD);
				String[] url = {classe};
				contAtualBD++;

				GetBDGenerico getBDGenerico = new GetBDGenerico();
		        getBDGenerico.execute(url);
		        
			} else {
				contAtualBD = 0;
				this.progressDialog.dismiss();
				AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("ATUALIZAÇÃO REALIZADA COM SUCESSO OS DADOS.");
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

				GetBDGenerico getBDGenerico = new GetBDGenerico();
		        getBDGenerico.execute(url);
		        
			} else {

				contAtualBD = 0;
				this.progressDialog.dismiss();
				AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("ATUALIZAÇÃO REALIZADA COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});

				alerta.show();

				this.progressDialog.dismiss();
				Intent it = new Intent(this.telaAtual, telaProx);
				telaAtual.startActivity(it);

			}
			
		}

	}

	public void encerrar(){
		
		if(this.tipoReceb == 1){
			
			this.progressDialog.dismiss();
			AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
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

			GetBDGenerico getBDGenerico = new GetBDGenerico();
			getBDGenerico.execute(url);

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

}
