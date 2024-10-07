package br.com.usinasantafe.cmm.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.cmm.control.ConfigCTR;
import br.com.usinasantafe.cmm.model.dao.AtualAplicDAO;
import br.com.usinasantafe.cmm.model.dao.LogErroDAO;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;
import br.com.usinasantafe.cmm.util.conHttp.PostBDGenerico;
import br.com.usinasantafe.cmm.model.pst.GenericRecordable;
import br.com.usinasantafe.cmm.util.conHttp.UrlsConexaoHttp;

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
	private Class telaProxAlt;
	private String dado;
	
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
					Log.i("PMM", "OBJETO -> " + objeto.toString());
					genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);
				}

				if(contAtualBD > 0){
					LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD > 0){" +
							"atualizandoBD(activity);", activity);
					atualizandoBD(activity);
				}

			}
			catch (Exception e) {
				Log.i("PMM", "Erro integração -> " + e);
				LogErroDAO.getInstance().insertLogErro(e);
			}

		} else {
			LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
					"encerrar(activity);", activity);
			encerrar(activity);
		}

	}

	public void atualTodasTabBD(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity, int tipoReceb){

		this.tipoReceb = tipoReceb;
		this.telaAtual = telaAtual;
		this.telaProx = telaProx;
		this.progressDialog = progressDialog;

		allClasses();
		postAtualizacao(activity);

	}

	public void atualTodasTabBD(Context telaAtual, ProgressDialog progressDialog, String activity, int tipoReceb){

		this.tipoReceb = tipoReceb;
		this.telaAtual = telaAtual;
		this.progressDialog = progressDialog;

		allClasses();
		postAtualizacao(activity);

	}

	public void atualGenericoBD(ArrayList classeArrayList, int tipoReceb, String activity){

		this.tipoReceb = tipoReceb;

		selecionarClasses(classeArrayList);
		postAtualizacao(activity);

	}

	public void atualGenericoBD(Context telaAtual, Class telaProx, ProgressDialog progressDialog, ArrayList classeArrayList, int tipoReceb, String activity){

		this.tipoReceb = tipoReceb;
		this.telaAtual = telaAtual;
		this.telaProx = telaProx;
		this.progressDialog = progressDialog;

		selecionarClasses(classeArrayList);
		postAtualizacao(activity);

	}

	public void atualGenericoBD(Context telaAtual, Class telaProx, ProgressDialog progressDialog, ArrayList classeArrayList, int tipoReceb, String activity, Class telaProxAlt, String dado){

		this.tipoReceb = tipoReceb;
		this.telaAtual = telaAtual;
		this.telaProx = telaProx;
		this.progressDialog = progressDialog;
		this.telaProxAlt = telaProxAlt;
		this.dado = dado;

		selecionarClasses(classeArrayList);
		postAtualizacao(activity);

	}

	public void allClasses(){

		try {

			tabAtualArrayList = new ArrayList();

			Class<?> retClasse = Class.forName(UrlsConexaoHttp.localUrl);

			for (Field field : retClasse.getDeclaredFields()) {
				String campo = field.getName();
				if(campo.contains("Bean")){
					tabAtualArrayList.add(campo);
				}
			}

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
		}

	}

	public void selecionarClasses(ArrayList classeArrayList){

		try {

			tabAtualArrayList = new ArrayList();

			Class<?> retClasse = Class.forName(UrlsConexaoHttp.localUrl);

			for (Field field : retClasse.getDeclaredFields()) {
				String campo = field.getName();
				for (int i = 0; i < classeArrayList.size(); i++) {
					String classe = (String) classeArrayList.get(i);
					if(campo.equals(classe)){
						tabAtualArrayList.add(campo);
					}
				}
			}

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
		}

	}

	public void postAtualizacao(String activity){

		classe = (String) tabAtualArrayList.get(contAtualBD);
		String[] url = {classe, activity};
		contAtualBD++;

		AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
		Map<String, Object> parametrosPost = new HashMap<>();
		parametrosPost.put("dado", atualAplicDAO.getAtualBDToken());
		Log.i("ECM", "dado -> " + atualAplicDAO.getAtualBDToken());

		LogProcessoDAO.getInstance().insertLogProcesso("postBDGenerico.execute('" + classe + "');", activity);
		PostBDGenerico postBDGenerico = new PostBDGenerico();
		postBDGenerico.setParametrosPost(parametrosPost);
		postBDGenerico.execute(url);

	}

	public void atualizandoBD(String activity){

		LogProcessoDAO.getInstance().insertLogProcesso("public void atualizandoBD(String activity){\n" +
				"qtdeBD = tabAtualArrayList.size();", activity);
		qtdeBD = tabAtualArrayList.size();
		if(contAtualBD < qtdeBD){

			LogProcessoDAO.getInstance().insertLogProcesso("if(contAtualBD < tabAtualArrayList.size()){\n" +
					"this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);\n" +
					"        classe = (String) tabAtualArrayList.get(contAtualBD);\n" +
					"String[] url = {classe, activity};\n" +
					"contAtualBD++;\n" +
					"GetBDGenerico getBDGenerico = new GetBDGenerico();\n" +
					"        getBDGenerico.execute(url);\n" +
					"        getBDGenerico.execute('" + classe + "');", activity);
			if(this.tipoReceb < 4) {
				this.progressDialog.setProgress((contAtualBD * 100) / qtdeBD);
			}

			postAtualizacao(activity);

		} else {

			LogProcessoDAO.getInstance().insertLogProcesso("} else {", activity);
			contAtualBD = 0;
			if(this.tipoReceb < 4) {
				this.progressDialog.dismiss();
			}

			if(this.tipoReceb == 1){

				LogProcessoDAO.getInstance().insertLogProcesso("if(this.tipoReceb == 1){\n" +
						"contAtualBD = 0;\n" +
						"this.progressDialog.dismiss();\n" +
						"AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
						"alerta.setTitle(\"ATENCAO\");\n" +
						"alerta.setMessage(\"ATUALIZAÇÃO REALIZADA COM SUCESSO OS DADOS.\");", activity);

				AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("ATUALIZAÇÃO REALIZADA COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
								"@Override\n" +
								"public void onClick(DialogInterface dialog, int which) {", activity);
					}
				});

				alerta.show();

			} else if(this.tipoReceb == 2) {

				LogProcessoDAO.getInstance().insertLogProcesso("} else if(this.tipoReceb == 2) {\n" +
						"contAtualBD = 0;\n" +
						"this.progressDialog.dismiss();\n" +
						"AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
						"alerta.setTitle(\"ATENCAO\");\n" +
						"alerta.setMessage(\"ATUALIZAÇÃO REALIZADA COM SUCESSO OS DADOS.\");", activity);
				AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("ATUALIZAÇÃO REALIZADA COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
								"@Override\n" +
								"public void onClick(DialogInterface dialog, int which) {\n" +
								"progressDialog.dismiss();\n" +
								"Intent it = new Intent(telaAtual, telaProx);\n" +
								"telaAtual.startActivity(it);", activity);
						Intent it = new Intent(telaAtual, telaProx);
						telaAtual.startActivity(it);
					}
				});
				alerta.show();

			}
		}

	}

	public void encerrar(String activity){

		LogProcessoDAO.getInstance().insertLogProcesso("public void encerrar(String activity){", activity);
		if(this.tipoReceb == 1){
			LogProcessoDAO.getInstance().insertLogProcesso("if(this.tipoReceb == 1){\n" +
					"this.progressDialog.dismiss();\n" +
					"AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);\n" +
					"alerta.setTitle(\"ATENCAO\");\n" +
					"alerta.setMessage(\"FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", activity);
			this.progressDialog.dismiss();
			AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
			alerta.setTitle("ATENCAO");
			alerta.setMessage("FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
			alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
							"@Override\n" +
							"public void onClick(DialogInterface dialog, int which) {", activity);
				}
			});
			alerta.show();
			
		}
	}

	public String manipLocalClasse(String classe){
	    if(classe.contains("Bean")){
	    	classe = UrlsConexaoHttp.localPSTEstatica + classe;
	    }
		return classe;
	}

}
