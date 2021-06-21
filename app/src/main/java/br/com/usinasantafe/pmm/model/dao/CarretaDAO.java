package br.com.usinasantafe.pmm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pmm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.CarretaBean;
import br.com.usinasantafe.pmm.model.pst.EspecificaPesquisa;

public class CarretaDAO {

    public CarretaDAO() {
    }

    public boolean hasElemCarreta(){
        CarretaBean carretaBean = new CarretaBean();
        return carretaBean.hasElements();
    }

    public int verCarreta(Long nroCarreta){
        int retorno; //1 - CARRETA CORRETA; 2 - NÃO EXISTE NA BASE DE DADOS; 3 - CARRETA REPETIDA; 4 - CARRETA INVERTIDA;
        if(verCarretaEquipSeg(nroCarreta)){
            if(!verCarretaBD(nroCarreta)){
                ConfigCTR configCTR = new ConfigCTR();
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
                        if((getQtdeCarreta() + 1) == 1){
                            retorno = 1;
                        }
                        else{
                            retorno = 4;
                        }
                    } else { //REBOQUE
                        if((getQtdeCarreta() + 1) > 1){
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
        if(getQtdeCarreta() == 0){
            CarretaBean carretaBean1 = new CarretaBean();
            carretaBean1.setPosCarreta(1L);
            carretaBean1.setNroEquip(carreta);
            carretaBean1.insert();
        }
        else if(getQtdeCarreta() == 1){
            CarretaBean carretaBean2 = new CarretaBean();
            carretaBean2.setPosCarreta(2L);
            carretaBean2.setNroEquip(carreta);
            carretaBean2.insert();
        }
        else if(getQtdeCarreta() == 2){
            CarretaBean carretaBean3 = new CarretaBean();
            carretaBean3.setPosCarreta(3L);
            carretaBean3.setNroEquip(carreta);
            carretaBean3.insert();
        }
        else if(getQtdeCarreta() == 3){
            CarretaBean carretaBean4 = new CarretaBean();
            carretaBean4.setPosCarreta(4L);
            carretaBean4.setNroEquip(carreta);
            carretaBean4.insert();
        }


    }

    public void delCarreta(){
        CarretaBean carretaBean = new CarretaBean();
        carretaBean.deleteAll();
    }

    public boolean verCarretaEquipSeg(Long nroCarreta){
        List carretaList = carretaEquipSegList(nroCarreta);
        boolean ver = carretaList.size() > 0;
        carretaList.clear();
        return ver;
    }

    public boolean verCarretaBD(Long nroCarreta){
        List carretaList = carretaList(nroCarreta);
        boolean ver = carretaList.size() > 0;
        carretaList.clear();
        return ver;
    }

    public EquipSegBean getCarretaEquipSeg(Long nroCarreta){
        List carretaList = carretaEquipSegList(nroCarreta);
        EquipSegBean equipSegBean = (EquipSegBean) carretaList.get(0);
        carretaList.clear();
        return equipSegBean;
    }

    private List carretaEquipSegList(Long nroCarreta){
        EquipSegBean equipSegBean = new EquipSegBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroEquip(nroCarreta));
        pesqArrayList.add(getPesqTipoCarreta());
        return equipSegBean.get(pesqArrayList);
    }

    private List carretaList(Long nroCarreta){
        CarretaBean carretaBean = new CarretaBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroEquip(nroCarreta));
        return carretaBean.get(pesqArrayList);
    }

    public int getQtdeCarreta(){
        CarretaBean carretaBean = new CarretaBean();
        List carretaList = carretaBean.all();
        int pos = carretaList.size();
        carretaList.clear();
        return pos;
    }

    public String getDescrCarreta(){
        CarretaBean carretaBean = new CarretaBean();
        List carretaList = carretaBean.orderBy("posCarreta", true);
        String textoCarreta = "";
        if (carretaList.size() == 0) {
            textoCarreta = "CARRETA(S): ";
        } else if (carretaList.size() == 1) {
            carretaBean = (CarretaBean) carretaList.get(0);
            textoCarreta = "CARRETA(S): " + carretaBean.getNroEquip();
        } else if (carretaList.size() == 2) {
            textoCarreta = "CARRETA(S): ";
            carretaBean = (CarretaBean) carretaList.get(0);
            textoCarreta = textoCarreta + carretaBean.getNroEquip();
            carretaBean = (CarretaBean) carretaList.get(1);
            textoCarreta = textoCarreta + " - " + carretaBean.getNroEquip();
        } else if (carretaList.size() == 3) {
            textoCarreta = "CARRETA(S): ";
            carretaBean = (CarretaBean) carretaList.get(0);
            textoCarreta = textoCarreta + carretaBean.getNroEquip();
            carretaBean = (CarretaBean) carretaList.get(1);
            textoCarreta = textoCarreta + " - " + carretaBean.getNroEquip();
            carretaBean = (CarretaBean) carretaList.get(2);
            textoCarreta = textoCarreta + " - " + carretaBean.getNroEquip();
        } else {
            textoCarreta = "CARRETA(S): ";
            carretaBean = (CarretaBean) carretaList.get(0);
            textoCarreta = textoCarreta + carretaBean.getNroEquip();
            carretaBean = (CarretaBean) carretaList.get(1);
            textoCarreta = textoCarreta + " - " + carretaBean.getNroEquip();
            carretaBean = (CarretaBean) carretaList.get(2);
            textoCarreta = textoCarreta + " - " + carretaBean.getNroEquip();
            carretaBean = (CarretaBean) carretaList.get(3);
            textoCarreta = textoCarreta + " - " + carretaBean.getNroEquip();
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
