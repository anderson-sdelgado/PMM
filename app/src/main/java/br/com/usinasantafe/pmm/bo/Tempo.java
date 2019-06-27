package br.com.usinasantafe.pmm.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.pmm.to.tb.estaticas.DataTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfigTO;

public class Tempo {

    private static Tempo instance = null;
    private boolean envioDado;
	
	public Tempo() {
		// TODO Auto-generated constructor stub
	}
	
    public static Tempo getInstance() {
        if (instance == null)
        instance = new Tempo();
        return instance;
    }

    public String datahora(){

        String dataCerta = "";

        TimeZone tz = TimeZone.getDefault();
        Date dataHora = new Date();
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        Long dt =  dataHora.getTime() - tz.getOffset(d.getTime()) + dif();
        cal.setTimeInMillis(dt);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);
        mes = mes + 1;

        String mesStr = "";
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String diaStr = "";
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        String horasStr = "";
        if(horas < 10){
            horasStr = "0" + horas;
        }
        else{
            horasStr = String.valueOf(horas);
        }

        String minutosStr = "";
        if(minutos < 10){
            minutosStr = "0" + minutos;
        }
        else{
            minutosStr = String.valueOf(minutos);
        }

        dataCerta = ""+diaStr+"/"+mesStr+"/"+ano+" "+horasStr+":"+minutosStr;

        return dataCerta;

    }

    public String dataSHora(){

        String dataCerta = "";

        TimeZone tz = TimeZone.getDefault();
        Date dataHora = new Date();
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        Long dt =  dataHora.getTime() - tz.getOffset(d.getTime()) + dif();
        cal.setTimeInMillis(dt);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        mes = mes + 1;

        String mesStr = "";
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String diaStr = "";
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        dataCerta = ""+diaStr+"/"+mesStr+"/"+ano;

        return dataCerta;

    }

    public String dataHoraCTZ(String data){

        try{

            StringBuffer dtServ = new StringBuffer(data);

            Log.i("PMM", "DATA HORA: " + dtServ);

            dtServ.delete(10, 11);
            dtServ.insert(10, " ");

            String dtStr = String.valueOf(dtServ);

            String diaStr = dtStr.substring(0, 2);
            String mesStr = dtStr.substring(3, 5);
            String anoStr = dtStr.substring(6, 10);
            String horaStr = dtStr.substring(11, 13);
            String minutoStr = dtStr.substring(14, 16);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            cal.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            cal.set(Calendar.YEAR, Integer.parseInt(anoStr));
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            cal.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            Date date = cal.getTime();

            TimeZone tz = TimeZone.getDefault();
            Date d = new Date();
            Long dt =  date.getTime() + tz.getOffset(d.getTime()) + dif();
            cal.setTimeInMillis(dt);

            int mes = cal.get(Calendar.MONTH);
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int ano = cal.get(Calendar.YEAR);
            int horas = cal.get(Calendar.HOUR_OF_DAY);
            int minutos = cal.get(Calendar.MINUTE);
            mes = mes + 1;

            if(mes < 10){
                mesStr = "0" + mes;
            }
            else{
                mesStr = String.valueOf(mes);
            }

            if(dia < 10){
                diaStr = "0" + dia;
            }
            else{
                diaStr = String.valueOf(dia);
            }

            String horasStr = "";
            if(horas < 10){
                horasStr = "0" + horas;
            }
            else{
                horasStr = String.valueOf(horas);
            }

            String minutosStr = "";
            if(minutos < 10){
                minutosStr = "0" + minutos;
            }
            else{
                minutosStr = String.valueOf(minutos);
            }

            data = ""+diaStr+"/"+mesStr+"/"+ano+" "+horasStr+":"+minutosStr;

        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("PMM", "Erro Manip = " + e);
        }

        return data;

    }

	public boolean isEnvioDado() {
		return envioDado;
	}

	public void setEnvioDado(boolean envioDado) {
		this.envioDado = envioDado;
	}

    public boolean verDthrServ(String dthrServ){

        StringBuffer dtServ = new StringBuffer(dthrServ);

        Log.i("PMM", "DATA HORA SERVIDOR: " + dtServ);

        dtServ.delete(10, 11);
        dtServ.insert(10, " ");

        String dtStr = String.valueOf(dtServ);

        String diaStr = dtStr.substring(0, 2);
        String mesStr = dtStr.substring(3, 5);
        String anoStr = dtStr.substring(6, 10);
        String horaStr = dtStr.substring(11, 13);
        String minutoStr = dtStr.substring(14, 16);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
        cal.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
        cal.set(Calendar.YEAR, Integer.parseInt(anoStr));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
        cal.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

        TimeZone tz = TimeZone.getDefault();
        Date dataHoraServ = cal.getTime();
        Date d = new Date();
        Long longDtServ =  dataHoraServ.getTime() - tz.getOffset(d.getTime());

        Date dataHoraCel = new Date();
        Long longDtCel =  dataHoraCel.getTime() - tz.getOffset(d.getTime());

        Long dthrDif = longDtServ - longDtCel;
        Long diaDif = dthrDif/24/60/60/1000;

        if(diaDif == 0){
            return true;
        }
        else{
            return false;
        }

    }

    public Long difDthr(String dthrDig){

        StringBuffer dtDig = new StringBuffer(dthrDig);

        Log.i("PMM", "DATA HORA DIGITADA: " + dtDig);

        dtDig.delete(10, 11);
        dtDig.insert(10, " ");

        String dtStr = String.valueOf(dtDig);

        String diaStr = dtStr.substring(0, 2);
        String mesStr = dtStr.substring(3, 5);
        String anoStr = dtStr.substring(6, 10);
        String horaStr = dtStr.substring(11, 13);
        String minutoStr = dtStr.substring(14, 16);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
        cal.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
        cal.set(Calendar.YEAR, Integer.parseInt(anoStr));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
        cal.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

        TimeZone tz = TimeZone.getDefault();
        Date dataHoraDig = cal.getTime();
        Date d = new Date();
        Long longDtDig =  dataHoraDig.getTime() - tz.getOffset(d.getTime());

        Date dataHoraCel = new Date();
        Long longDtCel =  dataHoraCel.getTime() - tz.getOffset(d.getTime());

        Long dif = longDtCel - longDtDig;

        return dif;

    }

    public Long dif(){
        ConfigTO configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);
        configList.clear();
        return configTO.getDifDthrConfig();
    }

}
