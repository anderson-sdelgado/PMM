package br.com.usinasantafe.pmm;

import android.app.Application;

import br.com.usinasantafe.pmm.control.CECCTR;
import br.com.usinasantafe.pmm.control.CompostoCTR;
import br.com.usinasantafe.pmm.control.MecanicoCTR;
import br.com.usinasantafe.pmm.control.MotoMecFertCTR;
import br.com.usinasantafe.pmm.control.CheckListCTR;
import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;

public class PMMContext extends Application {

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private MotoMecFertCTR motoMecFertCTR;
    private CECCTR cecCTR;
    private CheckListCTR checkListCTR;
    private ConfigCTR configCTR;
    private CompostoCTR compostoCTR;
    private MecanicoCTR mecanicoCTR;

    public static String versaoWS = "5.02";

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

    public MecanicoCTR getMecanicoCTR(){
        if (mecanicoCTR == null)
            mecanicoCTR = new MecanicoCTR();
        return mecanicoCTR;
    }

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            LogErroDAO.getInstance().insertLogErro(ex);
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    };

}
