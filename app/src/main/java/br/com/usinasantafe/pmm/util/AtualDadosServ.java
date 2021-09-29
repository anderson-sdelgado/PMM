package br.com.usinasantafe.pmm.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.pmm.model.dao.LogErroDAO;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pmm.util.conHttp.GetBDGenerico;
import br.com.usinasantafe.pmm.model.pst.GenericRecordable;
import br.com.usinasantafe.pmm.util.conHttp.UrlsConexaoHttp;

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
	public void manipularDadosHttp(String tipo, String result, String activity){

		LogProcessoDAO.getInstance().insertLogProcesso("if(!result.equals(\"\")){", activity);
		if(!result.equals("")){

			try{

				Log.i("PMM", "TIPO -> " + tipo);
				Log.i("PMM", "RESULT -> " + result);

				JSONObject jObj = new JSONObject(result);
				JSONArray jsonArray = jObj.getJSONArray("dados");
				Class classe = Class.forName(manipLocalClasse(tipo));

				LogProcessoDAO.getInstance().insertLogProcesso("genericRecordable.deleteAll('" + classe + "');", activity);
				genericRecordable.deleteAll(classe);

				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject objeto = jsonArray.getJSONObject(i);
					Gson gson = new Gson();
					genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);
				}

				LogProcessoDAO.getInstance().insertLogProcesso("Terminou atualização da tabela = '" + classe + "'", activity);
				if(contAtualBD > 0){
					LogProcessoDAO.getInstance().insertLogProcesso("atualizandoBD();", activity);
					atualizandoBD(activity);
				}

			}
			catch (Exception e) {
				LogErroDAO.getInstance().insertLogErro(e);
			}

		}
		else{
			LogProcessoDAO.getInstance().insertLogProcesso("encerrar();", activity);
			encerrar(activity);
		}

	}
	

	public void atualTodasTabBD(Context telaAtual, ProgressDialog progressDialog, String activity){
		
		try {
			
			this.tipoReceb = 1;
			this.telaAtual = telaAtual;
			this.progressDialog = progressDialog;
			tabAtualArrayList = new ArrayList();

	        Class<?> retClasse = Class.forName(urlsConexaoHttp.localUrl); 

	        for (Field field : retClasse.getDeclaredFields()) {
	            String campo = field.getName();
	            if(campo.contains("Bean")){
	            	tabAtualArrayList.add(campo);
	            }
	        }
	        
	        classe = (String) tabAtualArrayList.get(contAtualBD);
	        String[] url = {classe, activity};
		    contAtualBD++;

			LogProcessoDAO.getInstance().insertLogProcesso("getBDGenerico.execute('" + classe + "');", activity);
			GetBDGenerico getBDGenerico = new GetBDGenerico();
	        getBDGenerico.execute(url);
	        
		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
		}
        
	}

	public void atualGenericoBD(Context telaAtual, Class telaProx, ProgressDialog progressDialog, ArrayList classeArrayList, int tipoReceb, String activity){

		try {

			this.tipoReceb = tipoReceb;
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
			String[] url = {classe, activity};
			contAtualBD++;

			LogProcessoDAO.getInstance().insertLogProcesso("getBDGenerico.execute('" + classe + "');", activity);
			GetBDGenerico getBDGenerico = new GetBDGenerico();
			getBDGenerico.execute(url);

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
		}

	}


	public void atualizandoBD(String activity){

		LogProcessoDAO.getInstance().insertLogProcesso("if(this.tipoReceb == 1){", activity);
		if(this.tipoReceb == 1){
		
			qtdeBD = tabAtualArrayList.size();

			LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD < tabAtualArrayList.size()){", activity);
			if(contAtualBD < tabAtualArrayList.size()){
				
				this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);
		        classe = (String) tabAtualArrayList.get(contAtualBD);
				String[] url = {classe, activity};
				contAtualBD++;

				LogProcessoDAO.getInstance().insertLogProcesso("getBDGenerico.execute('" + classe + "');", activity);
				GetBDGenerico getBDGenerico = new GetBDGenerico();
		        getBDGenerico.execute(url);
		        
			} else {
				contAtualBD = 0;
				LogProcessoDAO.getInstance().insertLogProcesso("this.progressDialog.dismiss();\n" +
						"\t\t\t\tAlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
						"\t\t\t\talerta.setTitle(\"ATENCAO\");\n" +
						"\t\t\t\talerta.setMessage(\"ATUALIZAÇÃO REALIZADA COM SUCESSO OS DADOS.\");\n" +
						"\t\t\t\talerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
						"\t\t\t\t\t@Override\n" +
						"\t\t\t\t\tpublic void onClick(DialogInterface dialog, int which) {\n" +
						"\t\n" +
						"\t\t\t\t\t}\n" +
						"\t\t\t\t});\n" +
						"\t\t\t\t\n" +
						"\t\t\t\talerta.show();", activity);
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

			LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD < tabAtualArrayList.size()){", activity);
			if(contAtualBD < tabAtualArrayList.size()){
				
		        classe = (String) tabAtualArrayList.get(contAtualBD);
				String[] url = {classe, activity};
				contAtualBD++;

				LogProcessoDAO.getInstance().insertLogProcesso("getBDGenerico.execute('" + classe + "');", activity);
				GetBDGenerico getBDGenerico = new GetBDGenerico();
		        getBDGenerico.execute(url);
		        
			} else {

				contAtualBD = 0;
				LogProcessoDAO.getInstance().insertLogProcesso("this.progressDialog.dismiss();\n" +
						"\t\t\t\tAlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
						"\t\t\t\talerta.setTitle(\"ATENCAO\");\n" +
						"\t\t\t\talerta.setMessage(\"ATUALIZAÇÃO REALIZADA COM SUCESSO OS DADOS.\");\n" +
						"\t\t\t\talerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
						"\t\t\t\t\t@Override\n" +
						"\t\t\t\t\tpublic void onClick(DialogInterface dialog, int which) {\n" +
						"\t\t\t\t\t\tprogressDialog.dismiss();\n" +
						"\t\t\t\t\t\tIntent it = new Intent(telaAtual, telaProx);\n" +
						"\t\t\t\t\t\ttelaAtual.startActivity(it);\n" +
						"\t\t\t\t\t}\n" +
						"\t\t\t\t});\n" +
						"\t\t\t\talerta.show();", activity);

				this.progressDialog.dismiss();
				AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("ATUALIZAÇÃO REALIZADA COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						progressDialog.dismiss();
						Intent it = new Intent(telaAtual, telaProx);
						telaAtual.startActivity(it);
					}
				});
				alerta.show();


			}
			
		}

	}

	public void encerrar(String activity){

		LogProcessoDAO.getInstance().insertLogProcesso("if(this.tipoReceb == 1){", activity);
		if(this.tipoReceb == 1){

			LogProcessoDAO.getInstance().insertLogProcesso("this.progressDialog.dismiss();\n" +
					"\t\t\tAlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
					"\t\t\talerta.setTitle(\"ATENCAO\");\n" +
					"\t\t\talerta.setMessage(\"FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
					"\t\t\talerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
					"\t\t\t\t@Override\n" +
					"\t\t\t\tpublic void onClick(DialogInterface dialog, int which) {\n" +
					"\t\n" +
					"\t\t\t\t}\n" +
					"\t\t\t});\n" +
					"\t\t\talerta.show();", activity);
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

	public String manipLocalClasse(String classe){
	    if(classe.contains("Bean")){
	    	classe = urlsConexaoHttp.localPSTEstatica + classe;
	    }
		return classe;
	}

}
