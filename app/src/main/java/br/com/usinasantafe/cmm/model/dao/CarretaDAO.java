package br.com.usinasantafe.cmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.cmm.control.ConfigCTR;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.cmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.CarretaBean;
import br.com.usinasantafe.cmm.model.pst.EspecificaPesquisa;

public class CarretaDAO {

    public CarretaDAO() {
    }

    public boolean hasElemCarreta(){
        CarretaBean carretaBean = new CarretaBean();
        return carretaBean.hasElements();
    }

    public int verCarreta(Long nroCarreta){
        ConfigCTR configCTR = new ConfigCTR();
        int numCarreta = getQtdeCarreta() + 1;
        int retorno; //1 - CARRETA CORRETA; 2 - NÃO EXISTE NA BASE DE DADOS; 3 - CARRETA REPETIDA; 4 - CARRETA INVERTIDA;
        if(verCarretaEquipSeg(nroCarreta)){
            if(!verCarretaBD(nroCarreta)){
                EquipBean equipBean = configCTR.getEquip();
                EquipSegBean carreta = getCarretaEquipSeg(nroCarreta);
                if(equipBean.getCodClasseEquip() == 1){ //CAMINHÃO CANAVIEIRO
                    if(carreta.getCodClasseEquip() != 21){//REBOQUE
                        retorno = 1;
                    }
                    else{
                        retorno = 4;
                    }
                } else { //CAVALO CANAVIEIRO
                    if(carreta.getCodClasseEquip() == 21){  //SEMI REBOQUE
                        if(numCarreta == 1){
                            retorno = 1;
                        }
                        else{
                            retorno = 4;
                        }
                    } else { //REBOQUE
                        if(numCarreta > 1){
                            retorno = 1;
                        }
                        else{
                            retorno = 4;
                        }
                    }
                }
            }
            else{
                retorno = 3;
            }
        }
        else{
            retorno = 2;
        }
        return retorno;
    }

    public void insCarreta(Long carreta){
        CarretaBean carretaBean = new CarretaBean();
        carretaBean.setPosCarreta((long) (getQtdeCarreta() + 1));
        carretaBean.setNroEquip(carreta);
        carretaBean.insert();
    }

    public void delCarreta(){
        CarretaBean carretaBean = new CarretaBean();
        carretaBean.deleteAll();
    }

    public boolean verCarretaEquipSeg(Long nroCarreta){
        List<EquipSegBean> equipSegList = carretaEquipSegList(nroCarreta);
        boolean ver = equipSegList.size() > 0;
        equipSegList.clear();
        return ver;
    }

    public boolean verCarretaBD(Long nroCarreta){
        List<CarretaBean> carretaList = carretaList(nroCarreta);
        boolean ver = carretaList.size() > 0;
        carretaList.clear();
        return ver;
    }

    public EquipSegBean getCarretaEquipSeg(Long nroCarreta){
        List<EquipSegBean> equipSegList = carretaEquipSegList(nroCarreta);
        EquipSegBean equipSegBean = (EquipSegBean) equipSegList.get(0);
        equipSegList.clear();
        return equipSegBean;
    }

    private List<EquipSegBean> carretaEquipSegList(Long nroCarreta){
        EquipSegBean equipSegBean = new EquipSegBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroEquip(nroCarreta));
        pesqArrayList.add(getPesqTipoCarreta());
        return equipSegBean.get(pesqArrayList);
    }

    private List<CarretaBean> carretaList(Long nroCarreta){
        CarretaBean carretaBean = new CarretaBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroEquip(nroCarreta));
        return carretaBean.get(pesqArrayList);
    }

    public int getQtdeCarreta(){
        CarretaBean carretaBean = new CarretaBean();
        List<CarretaBean> carretaList = carretaBean.all();
        int pos = carretaList.size();
        carretaList.clear();
        return pos;
    }

    public String getDescrCarreta(){
        CarretaBean carretaBean = new CarretaBean();
        List<CarretaBean> carretaList = carretaBean.orderBy("posCarreta", true);
        String textoCarreta = "CARRETA(S): ";
        for(CarretaBean carretaBeanBD : carretaList){
            textoCarreta = textoCarreta + carretaBeanBD.getNroEquip() + " ";
        }
        carretaList.clear();
        return textoCarreta;
    }

    private EspecificaPesquisa getPesqNroEquip(Long nroEquip){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("nroEquip");
        especificaPesquisa.setValor(nroEquip);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqTipoCarreta(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoEquip");
        especificaPesquisa.setValor(6L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
