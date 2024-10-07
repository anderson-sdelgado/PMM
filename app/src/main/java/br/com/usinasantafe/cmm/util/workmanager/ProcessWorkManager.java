package br.com.usinasantafe.cmm.util.workmanager;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.List;

import br.com.usinasantafe.cmm.control.CECCTR;
import br.com.usinasantafe.cmm.control.CheckListCTR;
import br.com.usinasantafe.cmm.control.CompostoCTR;
import br.com.usinasantafe.cmm.control.MotoMecFertCTR;
import br.com.usinasantafe.cmm.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.CarregCompBean;
import br.com.usinasantafe.cmm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.cmm.util.EnvioDadosServ;
import br.com.usinasantafe.cmm.util.retrofit.send.BoletimMMFertSend;
import br.com.usinasantafe.cmm.util.retrofit.send.CarregInsumoSend;
import br.com.usinasantafe.cmm.util.retrofit.send.CheckListSend;
import br.com.usinasantafe.cmm.util.retrofit.send.PreCECSend;

public class ProcessWorkManager extends Worker {

    public ProcessWorkManager(@NonNull Context context,
                              @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        EnvioDadosServ.status = 2;
        if(verifChecklist()){
            try {
                CheckListSend checkListSend = new CheckListSend();
                updateChecklist(checkListSend.envioDadoCheckList(dadosEnvioCheckList()));
            } catch (Exception e) {
                EnvioDadosServ.status = 1;
                return Result.retry();
            }
        }
        if(verifEnvioCarregInsumo()){
            try {
                CarregInsumoSend carregInsumoSend = new CarregInsumoSend();
                updateCarregInsumo(carregInsumoSend.envioDadoCarregInsumo(dadosEnvioCarregInsumo()));
            } catch (Exception e) {
                EnvioDadosServ.status = 1;
                return Result.retry();
            }
        }
        if(verifPreCEC()){
            try {
                PreCECSend preCECSend = new PreCECSend();
                updatePreCEC(preCECSend.envioDadoPreCEC(dadosEnvioPreCEC()));
            } catch (Exception e) {
                EnvioDadosServ.status = 1;
                return Result.retry();
            }
        }
        if(verifBoletimMMFertEnvio()){
            try {
                BoletimMMFertSend boletimMMFertSend = new BoletimMMFertSend();
                updateBoletimMMFert(boletimMMFertSend.envioDadoMotoMecFert(dadosEnvioBoletimMMFert()));
            } catch (Exception e) {
                EnvioDadosServ.status = 1;
                return Result.retry();
            }
        }
        EnvioDadosServ.status = 3;
        return Result.success();
    }

    public boolean verifChecklist() {
        CheckListCTR checkListCTR = new CheckListCTR();
        return checkListCTR.verEnvioDados();
    }

    public List<CabecCheckListBean> dadosEnvioCheckList() {
        CheckListCTR checkListCTR = new CheckListCTR();
        return checkListCTR.dadosEnvioRetrofit();
    }

    public void updateChecklist(List<CabecCheckListBean> cabecCheckListList) {
        CheckListCTR checkListCTR = new CheckListCTR();
        checkListCTR.updateRecChecklistRetrofit(cabecCheckListList);
    }

    public boolean verifEnvioCarregInsumo() {
        CompostoCTR compostoCTR = new CompostoCTR();
        return compostoCTR.verifEnvioCarregInsumoComposto();
    }

    public CarregCompBean dadosEnvioCarregInsumo() {
        CompostoCTR compostoCTR = new CompostoCTR();
        return compostoCTR.dadosEnvioCarregInsumoRetrofit();
    }

    public void updateCarregInsumo(CarregCompBean carregCompBean) {
        CompostoCTR compostoCTR = new CompostoCTR();
        compostoCTR.updCarregInsumoRetrofit(carregCompBean);
    }

    public boolean verifPreCEC() {
        CECCTR cecCTR = new CECCTR();
        return cecCTR.verPreCECFechado();
    }

    public PreCECBean dadosEnvioPreCEC() {
        CECCTR cecCTR = new CECCTR();
        return cecCTR.dadosEnvioPreCECRetrofit();
    }

    public void updatePreCEC(PreCECBean preCECBean){
        CECCTR cecCTR = new CECCTR();
        cecCTR.atualPreCEC(preCECBean);
    }

    public boolean verifBoletimMMFertEnvio() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.verEnvioBol();
    }

    public List<BoletimMMFertBean> dadosEnvioBoletimMMFert() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.dadosEnvioBoletimMMFertRetrofit();
    }

    public void updateBoletimMMFert(List<BoletimMMFertBean> boletimMMFertList){
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        motoMecFertCTR.updateBolEnviadoRetrofit(boletimMMFertList);
    }

}
