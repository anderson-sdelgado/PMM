package br.com.usinasantafe.pmm.model.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import br.com.usinasantafe.pmm.control.ConfigCTR;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pmm.util.Tempo;

public class LogErroDAO {

    private static LogErroDAO instance = null;

    public static LogErroDAO getInstance() {
        if (instance == null)
            instance = new LogErroDAO();
        return instance;
    }

    public void insert(Throwable ex){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElemConfig()){
            if(configCTR.getConfig().getFlagLogErro().equals(1L)) {
                ConfigBean configBean = configCTR.getConfig();
                LogErroBean logErroBean = new LogErroBean();
                logErroBean.setIdEquip(configBean.getEquipConfig());
                logErroBean.setException(throwableToString(ex));
                logErroBean.setDthr(Tempo.getInstance().dthr());
                logErroBean.setStatus(1L);
                logErroBean.insert();
            }
        }
    }

    public void insert(String erro){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElemConfig()){
            if(configCTR.getConfig().getFlagLogErro().equals(1L)) {
                ConfigBean configBean = configCTR.getConfig();
                LogErroBean logErroBean = new LogErroBean();
                logErroBean.setIdEquip(configBean.getEquipConfig());
                logErroBean.setException("RETORNO SERVIDOR COM FALHA = " + erro);
                logErroBean.setDthr(Tempo.getInstance().dthr());
                logErroBean.setStatus(1L);
                logErroBean.insert();
            }
        }
    }

    private String throwableToString(Throwable t) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, false);

        t.printStackTrace(printWriter);

        printWriter.flush();
        stringWriter.flush();

        return stringWriter.toString();

    }

    public List<LogErroBean> logErroBeanList(){
        LogErroBean logErroBean = new LogErroBean();
        return logErroBean.orderBy("idLogErro", true);
    }


}
