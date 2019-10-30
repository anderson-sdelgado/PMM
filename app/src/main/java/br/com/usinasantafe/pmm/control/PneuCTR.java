package br.com.usinasantafe.pmm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

import br.com.usinasantafe.pmm.bean.variaveis.ItemPneuTO;
import br.com.usinasantafe.pmm.dao.CabecPneuDAO;
import br.com.usinasantafe.pmm.dao.ItemPneuDAO;

public class PneuCTR {

    private ItemPneuTO itemPneuTO;

    public PneuCTR() {
    }

    public boolean verCalibAberto(){
        CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
        return cabecPneuDAO.verCabecPneuAberto();
    }

    public List getListItemCalibPneu(){
        CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
        ItemPneuDAO itemPneuDAO = new ItemPneuDAO();
        return itemPneuDAO.getListItemPneu(cabecPneuDAO.getIdCabecAberto());
    }

    public ItemPneuTO getItemPneuTO() {
        return itemPneuTO;
    }

    public void setItemPneuTO(Long idPosConfPneu) {
        CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
        this.itemPneuTO = new ItemPneuTO();
        this.itemPneuTO.setPosItemPneu(idPosConfPneu);
        this.itemPneuTO.setIdCabecItemPneu(cabecPneuDAO.getIdCabecAberto());
    }

    public void verPneu(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ItemPneuDAO itemPneuDAO = new ItemPneuDAO();
        itemPneuDAO.verPneu(dado, telaAtual, telaProx, progressDialog);
    }

    public void salvarItem(){
        ItemPneuDAO itemPneuDAO = new ItemPneuDAO();
        itemPneuDAO.salvarItem(itemPneuTO);
    }

    public boolean verFechCabec(){
        CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
        ItemPneuDAO itemPneuDAO = new ItemPneuDAO();
        return cabecPneuDAO.verFechCabec(itemPneuDAO.getListItemPneu(cabecPneuDAO.getIdCabecAberto()));
    }

}
