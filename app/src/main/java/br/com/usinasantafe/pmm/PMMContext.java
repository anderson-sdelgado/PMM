package br.com.usinasantafe.pmm;

import android.app.Application;

import br.com.usinasantafe.pmm.control.CECCTR;
import br.com.usinasantafe.pmm.control.CompostoCTR;
import br.com.usinasantafe.pmm.control.MotoMecFertCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.control.InformativoCTR;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;

/**
 * Created by anderson on 26/04/2017.
 */

public class PMMContext extends Application {

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private MotoMecFertCTR motoMecFertCTR;
    private CECCTR cecCTR;
    private InformativoCTR informativoCTR;
    private CheckListCTR checkListCTR;
    private ConfigCTR configCTR;
    private CompostoCTR compostoCTR;

    private Long contImplemento;
    public static String versaoAplic = "4.00";
    public static int aplic = 2;   // 1 - PMM; 2 - ECM; 3 - PCOMP
    private int contRend;
    private int posRend;
    private int contRecolh;
    private int posRecolh;
    private int contDataHora;
    private int posCheckList;
    private int tipoMovComp;
    private boolean verTelaLeira;

    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;

    @Override
    public void onCreate() {
        super.onCreate();
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    public MotoMecFertCTR getMotoMecFertCTR() {
        if (motoMecFertCTR == null)
            motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR;
    }

    public CECCTR getCecCTR() {
        if (cecCTR == null)
            cecCTR = new CECCTR();
        return cecCTR;
    }

    public InformativoCTR getInformativoCTR(){
        if (informativoCTR == null)
            informativoCTR = new InformativoCTR();
        return informativoCTR;
    }

    public CheckListCTR getCheckListCTR(){
        if (checkListCTR == null)
            checkListCTR = new CheckListCTR();
        return checkListCTR;
    }

    public ConfigCTR getConfigCTR(){
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public CompostoCTR getCompostoCTR(){
        if (compostoCTR == null)
            compostoCTR = new CompostoCTR();
        return compostoCTR;
    }

    public Long getContImplemento() {
        return contImplemento;
    }

    public void setContImplemento(Long contImplemento) {
        this.contImplemento = contImplemento;
    }

    public int getPosCheckList() {
        return posCheckList;
    }

    public void setPosCheckList(int posCheckList) {
        this.posCheckList = posCheckList;
    }

    public int getContRend() {
        return contRend;
    }

    public void setContRend(int contRend) {
        this.contRend = contRend;
    }

    public int getPosRend() {
        return posRend;
    }

    public void setPosRend(int posRend) {
        this.posRend = posRend;
    }

    public int getContRecolh() {
        return contRecolh;
    }

    public void setContRecolh(int contRecolh) {
        this.contRecolh = contRecolh;
    }

    public int getPosRecolh() {
        return posRecolh;
    }

    public void setPosRecolh(int posRecolh) {
        this.posRecolh = posRecolh;
    }

    public int getContDataHora() {
        return contDataHora;
    }

    public void setContDataHora(int contDataHora) {
        this.contDataHora = contDataHora;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            LogErroDAO.getInstance().insert(ex);
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    };

    public boolean isVerTelaLeira() {
        return verTelaLeira;
    }

    public void setVerTelaLeira(boolean verTelaLeira) {
        this.verTelaLeira = verTelaLeira;
    }

    public int getTipoMovComp() {
        return tipoMovComp;
    }

    public void setTipoMovComp(int tipoMovComp) {
        this.tipoMovComp = tipoMovComp;
    }

}
