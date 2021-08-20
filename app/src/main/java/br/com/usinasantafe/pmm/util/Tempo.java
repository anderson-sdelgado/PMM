package br.com.usinasantafe.pmm.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pmm.model.dao.LogErroDAO;

public class Tempo {

    private static Tempo instance = null;
	
	public Tempo() {
	}
	
    public static Tempo getInstance() {
        if (instance == null)
        instance = new Tempo();
        return instance;
    }

    public Long dtHr(){
        Date dataHora = new Date();
        return dataHora.getTime() + dif();
    }

    public String dthr(){
        return dthrLongToString(dtHr());
    }

    public String dt(){
        return dtLongToString(dtHr());
    }

    public Long dthrAddMinutoLong(Long dthrLong, int minuto){
        dthrLong = dthrLong + (minuto * 60 * 1000);
        return dthrLong;
    }

    public boolean verDthrServ(String dthrServ){

        Date dataHoraServ = dthrStringToCalendar(dthrServ).getTime();
        Long longDtServ =  dataHoraServ.getTime();

        Date dataHoraCel = new Date();
        Long longDtCel =  dataHoraCel.getTime();

        Long dthrDif =  longDtServ - longDtCel;
        Long diaDif = dthrDif/24/60/60/1000;

        if((diaDif >= 0) && (diaDif <= 15)){
            return true;
        }
        else{
            return false;
        }

    }

    public Long difDthr(int dia, int mes, int ano, int hora, int minuto){

        String diaStr;
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        String mesStr;
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String anoStr = String.valueOf(ano);

        String horaStr = "";
        if(hora < 10){
            horaStr = "0" + hora;
        }
        else{
            horaStr = String.valueOf(hora);
        }

        String minutoStr = "";
        if(minuto < 10){
            minutoStr = "0" + minuto;
        }
        else{
            minutoStr = String.valueOf(minuto);
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
        cal.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
        cal.set(Calendar.YEAR, Integer.parseInt(anoStr));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
        cal.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

        Date dataHoraDig = cal.getTime();
        Long longDtDig =  dataHoraDig.getTime();

        Long dif = longDtDig - dtHr();

        return dif;

    }

    public Long dif(){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.all();
        configBean = configList.get(0);
        configList.clear();
        return configBean.getDifDthrConfig();
    }

    public String dthrLongToString(Long dthrLong){

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dthrLong);

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

        return ""+diaStr+"/"+mesStr+"/"+ano+" "+horasStr+":"+minutosStr;

    }

    public String dtLongToString(Long dthrLong){

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dthrLong);

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

        return ""+diaStr+"/"+mesStr+"/"+ano;

    }


    public Long dthrStringToLong(String dthrString){
        return dthrStringToCalendar(dthrString).getTimeInMillis();
    }

    public Calendar dthrStringToCalendar(String dthrString){

        Calendar calendar = Calendar.getInstance();

        try {

            String diaStr = dthrString.substring(0, 2);
            String mesStr = dthrString.substring(3, 5);
            String anoStr = dthrString.substring(6, 10);
            String horaStr = dthrString.substring(11, 13);
            String minutoStr = dthrString.substring(14, 16);

            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            calendar.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            calendar.set(Calendar.YEAR, Integer.parseInt(anoStr));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            calendar.set(Calendar.MINUTE, Integer.parseInt(minutoStr));
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 9999);

        }
        catch (Exception e) {
            LogErroDAO.getInstance().insert(e);
        }

        return calendar;

    }

}