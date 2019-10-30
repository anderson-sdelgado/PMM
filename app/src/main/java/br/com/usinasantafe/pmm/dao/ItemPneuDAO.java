package br.com.usinasantafe.pmm.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pmm.bean.estaticas.PneuTO;
import br.com.usinasantafe.pmm.bean.variaveis.ItemPneuTO;
import br.com.usinasantafe.pmm.util.Tempo;
import br.com.usinasantafe.pmm.util.VerifDadosServ;

public class ItemPneuDAO {

    public ItemPneuDAO() {
    }

    public void salvarItem(ItemPneuTO itemPneuTO){
        itemPneuTO.setDthrItemPneu(Tempo.getInstance().dataComHora());
        itemPneuTO.insert();
    }


    public List getListItemPneu(Long idBolPneu){
        ItemPneuTO itemPneuTO = new ItemPneuTO();
        return itemPneuTO.get("idCabecItemPneu", idBolPneu);
    }

    public void verPneu(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(false);
        VerifDadosServ.getInstance().verDados(dado, "Pneu", telaAtual, telaProx, progressDialog);
    }

    public void recDadosPneu(String result){

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        PneuTO pneuTO = gson.fromJson(objeto.toString(), PneuTO.class);
                        pneuTO.insert();

                    }

                    VerifDadosServ.getInstance().pulaTelaComTerm();

                } else {

                    VerifDadosServ.getInstance().msgComTerm("PNEU INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");

                }

            } else {

                VerifDadosServ.getInstance().msgComTerm("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE PNEU! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

    }

}
