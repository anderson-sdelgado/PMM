package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.to.tb.estaticas.GrafDispEquipPlantioTO;

public class GrafDispActivity extends ActivityGeneric {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_disp);

        final AnyChartView grafTratorPlantOperDia = findViewById(R.id.graf_trator_plant_oper_dia);
        grafTratorPlantOperDia.setProgressBar(findViewById(R.id.progress_bar_trator_plant_oper_dia));
        APIlib.getInstance().setActiveAnyChartView(grafTratorPlantOperDia);

        grafTratorPlantOperDia.setAnimationCacheEnabled(false);

        GrafDispEquipPlantioTO grafDispEquipPlantioTO = new GrafDispEquipPlantioTO();
        List grafDispEquipPlantList = grafDispEquipPlantioTO.all();
        grafDispEquipPlantioTO = (GrafDispEquipPlantioTO) grafDispEquipPlantList.get(0);

        int disp = 0;
        int par = 0;

        final Pie pieTratorPlantOperDia = AnyChart.pie();

        List<DataEntry> dataTratorPlantOperDia = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperTratorPlanDia() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperTratorPlanDia().intValue();
            disp = 100 - par;
        }

        dataTratorPlantOperDia.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantOperDia.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorPlantOperDia.data(dataTratorPlantOperDia);

        pieTratorPlantOperDia.labels().position("outside");

        pieTratorPlantOperDia.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorPlantOperDia.setChart(pieTratorPlantOperDia);

        final AnyChartView grafTratorPlantOperMes = findViewById(R.id.graf_trator_plant_oper_mes);
        grafTratorPlantOperMes.setProgressBar(findViewById(R.id.progress_bar_trator_plant_oper_mes));
        APIlib.getInstance().setActiveAnyChartView(grafTratorPlantOperMes);

        grafTratorPlantOperMes.setAnimationCacheEnabled(false);

        final Pie pieTratorPlantOperMes = AnyChart.pie();

        List<DataEntry> dataTratorPlantOperMes = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperTratorPlanMes() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperTratorPlanMes().intValue();
            disp = 100 - par;
        }

        dataTratorPlantOperMes.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantOperMes.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorPlantOperMes.data(dataTratorPlantOperMes);

        pieTratorPlantOperMes.labels().position("outside");

        pieTratorPlantOperMes.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorPlantOperMes.setChart(pieTratorPlantOperMes);

        final AnyChartView grafTratorPlantOperAno = findViewById(R.id.graf_trator_plant_oper_ano);
        grafTratorPlantOperAno.setProgressBar(findViewById(R.id.progress_bar_trator_plant_oper_ano));
        APIlib.getInstance().setActiveAnyChartView(grafTratorPlantOperAno);

        grafTratorPlantOperAno.setAnimationCacheEnabled(false);

        final Pie pieTratorPlantOperAno = AnyChart.pie();

        List<DataEntry> dataTratorPlantOperAno = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperTratorPlanAno() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperTratorPlanAno().intValue();
            disp = 100 - par;
        }

        dataTratorPlantOperAno.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantOperAno.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorPlantOperAno.data(dataTratorPlantOperAno);

        pieTratorPlantOperAno.labels().position("outside");

        pieTratorPlantOperAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorPlantOperAno.setChart(pieTratorPlantOperAno);

        final AnyChartView grafTratorPlantCampoDia = findViewById(R.id.graf_trator_plant_campo_dia);
        grafTratorPlantCampoDia.setProgressBar(findViewById(R.id.progress_bar_trator_plant_campo_dia));
        APIlib.getInstance().setActiveAnyChartView(grafTratorPlantCampoDia);

        grafTratorPlantCampoDia.setAnimationCacheEnabled(false);

        final Pie pieTratorPlantCampoDia = AnyChart.pie();

        List<DataEntry> dataTratorPlantCampoDia = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoTratorPlanDia() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoTratorPlanDia().intValue();
            disp = 100 - par;
        }

        dataTratorPlantCampoDia.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantCampoDia.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorPlantCampoDia.data(dataTratorPlantCampoDia);

        pieTratorPlantCampoDia.labels().position("outside");

        pieTratorPlantCampoDia.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorPlantCampoDia.setChart(pieTratorPlantCampoDia);

        final AnyChartView grafTratorPlantCampoMes = findViewById(R.id.graf_trator_plant_campo_mes);
        grafTratorPlantCampoMes.setProgressBar(findViewById(R.id.progress_bar_trator_plant_campo_mes));
        APIlib.getInstance().setActiveAnyChartView(grafTratorPlantCampoMes);

        grafTratorPlantCampoMes.setAnimationCacheEnabled(false);

        final Pie pieTratorPlantCampoMes = AnyChart.pie();

        List<DataEntry> dataTratorPlantCampoMes = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoTratorPlanMes() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoTratorPlanMes().intValue();
            disp = 100 - par;
        }

        dataTratorPlantCampoMes.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantCampoMes.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorPlantCampoMes.data(dataTratorPlantCampoMes);

        pieTratorPlantCampoMes.labels().position("outside");

        pieTratorPlantCampoMes.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorPlantCampoMes.setChart(pieTratorPlantCampoMes);

        final AnyChartView grafTratorPlantCampoAno = findViewById(R.id.graf_trator_plant_campo_ano);
        grafTratorPlantCampoAno.setProgressBar(findViewById(R.id.progress_bar_trator_plant_campo_ano));
        APIlib.getInstance().setActiveAnyChartView(grafTratorPlantCampoAno);

        grafTratorPlantCampoAno.setAnimationCacheEnabled(false);

        final Pie pieTratorPlantCampoAno = AnyChart.pie();

        List<DataEntry> dataTratorPlantCampoAno = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoTratorPlanAno() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoTratorPlanAno().intValue();
            disp = 100 - par;
        }

        dataTratorPlantCampoAno.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantCampoAno.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorPlantCampoAno.data(dataTratorPlantCampoAno);

        pieTratorPlantCampoAno.labels().position("outside");

        pieTratorPlantCampoAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorPlantCampoAno.setChart(pieTratorPlantCampoAno);

        final AnyChartView grafCaminhaoOperDia = findViewById(R.id.graf_caminhao_oper_dia);
        grafCaminhaoOperDia.setProgressBar(findViewById(R.id.progress_bar_caminhao_oper_dia));
        APIlib.getInstance().setActiveAnyChartView(grafCaminhaoOperDia);

        grafCaminhaoOperDia.setAnimationCacheEnabled(false);

        final Pie pieCaminhaoOperDia = AnyChart.pie();

        List<DataEntry> dataCaminhaoOperDia = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperCamMudaDia() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperCamMudaDia().intValue();
            disp = 100 - par;
        }

        dataCaminhaoOperDia.add(new PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoOperDia.add(new PersonDataEntry("PAR", par, "red"));

        pieCaminhaoOperDia.data(dataCaminhaoOperDia);

        pieCaminhaoOperDia.labels().position("outside");

        pieCaminhaoOperDia.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafCaminhaoOperDia.setChart(pieCaminhaoOperDia);

        final AnyChartView grafCaminhaoOperMes = findViewById(R.id.graf_caminhao_oper_mes);
        grafCaminhaoOperMes.setProgressBar(findViewById(R.id.progress_bar_caminhao_oper_mes));
        APIlib.getInstance().setActiveAnyChartView(grafCaminhaoOperMes);

        grafCaminhaoOperMes.setAnimationCacheEnabled(false);

        final Pie pieCaminhaoOperMes = AnyChart.pie();

        List<DataEntry> dataCaminhaoOperMes = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperCamMudaMes() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperCamMudaMes().intValue();
            disp = 100 - par;
        }

        dataCaminhaoOperMes.add(new PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoOperMes.add(new PersonDataEntry("PAR", par, "red"));

        pieCaminhaoOperMes.data(dataCaminhaoOperMes);

        pieCaminhaoOperMes.labels().position("outside");

        pieCaminhaoOperMes.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafCaminhaoOperMes.setChart(pieCaminhaoOperMes);

        final AnyChartView grafCaminhaoOperAno = findViewById(R.id.graf_caminhao_oper_ano);
        grafCaminhaoOperAno.setProgressBar(findViewById(R.id.progress_bar_caminhao_oper_ano));
        APIlib.getInstance().setActiveAnyChartView(grafCaminhaoOperAno);

        grafCaminhaoOperAno.setAnimationCacheEnabled(false);

        final Pie pieCaminhaoOperAno = AnyChart.pie();

        List<DataEntry> dataCaminhaoOperAno = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperCamMudaAno() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperCamMudaAno().intValue();
            disp = 100 - par;
        }

        dataCaminhaoOperAno.add(new PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoOperAno.add(new PersonDataEntry("PAR", par, "red"));

        pieCaminhaoOperAno.data(dataCaminhaoOperAno);

        pieCaminhaoOperAno.labels().position("outside");

        pieCaminhaoOperAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafCaminhaoOperAno.setChart(pieCaminhaoOperAno);

        final AnyChartView grafCaminhaoCampoDia = findViewById(R.id.graf_caminhao_campo_dia);
        grafCaminhaoCampoDia.setProgressBar(findViewById(R.id.progress_bar_caminhao_campo_dia));
        APIlib.getInstance().setActiveAnyChartView(grafCaminhaoCampoDia);

        grafCaminhaoCampoDia.setAnimationCacheEnabled(false);

        final Pie pieCaminhaoCampoDia = AnyChart.pie();

        List<DataEntry> dataCaminhaoCampoDia = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoCamMudaDia() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoCamMudaDia().intValue();
            disp = 100 - par;
        }

        dataCaminhaoCampoDia.add(new PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoCampoDia.add(new PersonDataEntry("PAR", par, "red"));

        pieCaminhaoCampoDia.data(dataCaminhaoCampoDia);

        pieCaminhaoCampoDia.labels().position("outside");

        pieCaminhaoCampoDia.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafCaminhaoCampoDia.setChart(pieCaminhaoCampoDia);

        final AnyChartView grafCaminhaoCampoMes = findViewById(R.id.graf_caminhao_campo_mes);
        grafCaminhaoCampoMes.setProgressBar(findViewById(R.id.progress_bar_caminhao_campo_mes));
        APIlib.getInstance().setActiveAnyChartView(grafCaminhaoCampoMes);

        grafCaminhaoCampoMes.setAnimationCacheEnabled(false);

        final Pie pieCaminhaoCampoMes = AnyChart.pie();

        List<DataEntry> dataCaminhaoCampoMes = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoCamMudaMes() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoCamMudaMes().intValue();
            disp = 100 - par;
        }

        dataCaminhaoCampoMes.add(new PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoCampoMes.add(new PersonDataEntry("PAR", par, "red"));

        pieCaminhaoCampoMes.data(dataCaminhaoCampoMes);

        pieCaminhaoCampoMes.labels().position("outside");

        pieCaminhaoCampoMes.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafCaminhaoCampoMes.setChart(pieCaminhaoCampoMes);

        final AnyChartView grafCaminhaoCampoAno = findViewById(R.id.graf_caminhao_campo_ano);
        grafCaminhaoCampoAno.setProgressBar(findViewById(R.id.progress_bar_caminhao_campo_ano));
        APIlib.getInstance().setActiveAnyChartView(grafCaminhaoCampoAno);

        grafCaminhaoCampoAno.setAnimationCacheEnabled(false);

        final Pie pieCaminhaoCampoAno = AnyChart.pie();

        List<DataEntry> dataCaminhaoCampoAno = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoCamMudaAno() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoCamMudaAno().intValue();
            disp = 100 - par;
        }

        dataCaminhaoCampoAno.add(new PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoCampoAno.add(new PersonDataEntry("PAR", par, "red"));

        pieCaminhaoCampoAno.data(dataCaminhaoCampoAno);

        pieCaminhaoCampoAno.labels().position("outside");

        pieCaminhaoCampoAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafCaminhaoCampoAno.setChart(pieCaminhaoCampoAno);

        final AnyChartView grafColhedoraOperDia = findViewById(R.id.graf_colhedora_oper_dia);
        grafColhedoraOperDia.setProgressBar(findViewById(R.id.progress_bar_colhedora_oper_dia));
        APIlib.getInstance().setActiveAnyChartView(grafColhedoraOperDia);

        grafColhedoraOperDia.setAnimationCacheEnabled(false);

        final Pie pieColhedoraOperDia = AnyChart.pie();

        List<DataEntry> dataColhedoraOperDia = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperColhedoraDia() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperColhedoraDia().intValue();
            disp = 100 - par;
        }

        dataColhedoraOperDia.add(new PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraOperDia.add(new PersonDataEntry("PAR", par, "red"));

        pieColhedoraOperDia.data(dataColhedoraOperDia);

        pieColhedoraOperDia.labels().position("outside");

        pieColhedoraOperDia.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafColhedoraOperDia.setChart(pieColhedoraOperDia);

        final AnyChartView grafColhedoraOperMes = findViewById(R.id.graf_colhedora_oper_mes);
        grafColhedoraOperMes.setProgressBar(findViewById(R.id.progress_bar_colhedora_oper_mes));
        APIlib.getInstance().setActiveAnyChartView(grafColhedoraOperMes);

        grafColhedoraOperMes.setAnimationCacheEnabled(false);

        final Pie pieColhedoraOperMes = AnyChart.pie();

        List<DataEntry> dataColhedoraOperMes = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperColhedoraMes() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperColhedoraMes().intValue();
            disp = 100 - par;
        }

        dataColhedoraOperMes.add(new PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraOperMes.add(new PersonDataEntry("PAR", par, "red"));

        pieColhedoraOperMes.data(dataColhedoraOperMes);

        pieColhedoraOperMes.labels().position("outside");

        pieColhedoraOperMes.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafColhedoraOperMes.setChart(pieColhedoraOperMes);

        final AnyChartView grafColhedoraOperAno = findViewById(R.id.graf_colhedora_oper_ano);
        grafColhedoraOperAno.setProgressBar(findViewById(R.id.progress_bar_colhedora_oper_ano));
        APIlib.getInstance().setActiveAnyChartView(grafColhedoraOperAno);

        grafColhedoraOperAno.setAnimationCacheEnabled(false);

        final Pie pieColhedoraOperAno = AnyChart.pie();

        List<DataEntry> dataColhedoraOperAno = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperColhedoraAno() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperColhedoraAno().intValue();
            disp = 100 - par;
        }

        dataColhedoraOperAno.add(new PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraOperAno.add(new PersonDataEntry("PAR", par, "red"));

        pieColhedoraOperAno.data(dataColhedoraOperAno);

        pieColhedoraOperAno.labels().position("outside");

        pieColhedoraOperAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafColhedoraOperAno.setChart(pieColhedoraOperAno);

        final AnyChartView grafColhedoraCampoDia = findViewById(R.id.graf_colhedora_campo_dia);
        grafColhedoraCampoDia.setProgressBar(findViewById(R.id.progress_bar_colhedora_campo_dia));
        APIlib.getInstance().setActiveAnyChartView(grafColhedoraCampoDia);

        grafColhedoraCampoDia.setAnimationCacheEnabled(false);

        final Pie pieColhedoraCampoDia = AnyChart.pie();

        List<DataEntry> dataColhedoraCampoDia = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoColhedoraDia() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoColhedoraDia().intValue();
            disp = 100 - par;
        }

        dataColhedoraCampoDia.add(new PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraCampoDia.add(new PersonDataEntry("PAR", par, "red"));

        pieColhedoraCampoDia.data(dataColhedoraCampoDia);

        pieColhedoraCampoDia.labels().position("outside");

        pieColhedoraCampoDia.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafColhedoraCampoDia.setChart(pieColhedoraCampoDia);

        final AnyChartView grafColhedoraCampoMes = findViewById(R.id.graf_colhedora_campo_mes);
        grafColhedoraCampoMes.setProgressBar(findViewById(R.id.progress_bar_colhedora_campo_mes));
        APIlib.getInstance().setActiveAnyChartView(grafColhedoraCampoMes);

        grafColhedoraCampoMes.setAnimationCacheEnabled(false);

        final Pie pieColhedoraCampoMes = AnyChart.pie();

        List<DataEntry> dataColhedoraCampoMes = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoColhedoraMes() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoColhedoraMes().intValue();
            disp = 100 - par;
        }

        dataColhedoraCampoDia.add(new PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraCampoDia.add(new PersonDataEntry("PAR", par, "red"));

        pieColhedoraCampoMes.data(dataColhedoraCampoMes);

        pieColhedoraCampoMes.labels().position("outside");

        pieColhedoraCampoMes.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafColhedoraCampoMes.setChart(pieColhedoraCampoMes);

        final AnyChartView grafColhedoraCampoAno = findViewById(R.id.graf_colhedora_campo_ano);
        grafColhedoraCampoAno.setProgressBar(findViewById(R.id.progress_bar_colhedora_campo_ano));
        APIlib.getInstance().setActiveAnyChartView(grafColhedoraCampoAno);

        grafColhedoraCampoAno.setAnimationCacheEnabled(false);

        final Pie pieColhedoraCampoAno = AnyChart.pie();

        List<DataEntry> dataColhedoraCampoAno = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoColhedoraAno() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoColhedoraAno().intValue();
            disp = 100 - par;
        }

        dataColhedoraCampoAno.add(new PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraCampoAno.add(new PersonDataEntry("PAR", par, "red"));

        pieColhedoraCampoAno.data(dataColhedoraCampoAno);

        pieColhedoraCampoAno.labels().position("outside");

        pieColhedoraCampoAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafColhedoraCampoAno.setChart(pieColhedoraCampoAno);

        final AnyChartView grafTratorTransbOperDia = findViewById(R.id.graf_trator_transb_oper_dia);
        grafTratorTransbOperDia.setProgressBar(findViewById(R.id.progress_bar_trator_transb_oper_dia));
        APIlib.getInstance().setActiveAnyChartView(grafTratorTransbOperDia);

        grafTratorTransbOperDia.setAnimationCacheEnabled(false);

        final Pie pieTratorTransbOperDia = AnyChart.pie();

        List<DataEntry> dataTratorTransbOperDia = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperTratorTransbDia() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperTratorTransbDia().intValue();
            disp = 100 - par;
        }

        dataTratorTransbOperDia.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbOperDia.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorTransbOperDia.data(dataTratorTransbOperDia);

        pieTratorTransbOperDia.labels().position("outside");

        pieTratorTransbOperDia.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorTransbOperDia.setChart(pieTratorTransbOperDia);

        final AnyChartView grafTratorTransbOperMes = findViewById(R.id.graf_trator_transb_oper_mes);
        grafTratorTransbOperMes.setProgressBar(findViewById(R.id.progress_bar_trator_transb_oper_mes));
        APIlib.getInstance().setActiveAnyChartView(grafTratorTransbOperMes);

        grafTratorTransbOperMes.setAnimationCacheEnabled(false);

        final Pie pieTratorTransbOperMes = AnyChart.pie();

        List<DataEntry> dataTratorTransbOperMes = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperTratorTransbMes() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperTratorTransbMes().intValue();
            disp = 100 - par;
        }

        dataTratorTransbOperMes.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbOperMes.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorTransbOperMes.data(dataTratorTransbOperMes);

        pieTratorTransbOperMes.labels().position("outside");

        pieTratorTransbOperMes.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorTransbOperMes.setChart(pieTratorTransbOperMes);

        final AnyChartView grafTratorTransbOperAno = findViewById(R.id.graf_trator_transb_oper_ano);
        grafTratorTransbOperAno.setProgressBar(findViewById(R.id.progress_bar_trator_transb_oper_ano));
        APIlib.getInstance().setActiveAnyChartView(grafTratorTransbOperAno);

        grafTratorTransbOperAno.setAnimationCacheEnabled(false);

        final Pie pieTratorTransbOperAno = AnyChart.pie();

        List<DataEntry> dataTratorTransbOperAno = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorOperTratorTransbAno() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorOperTratorTransbAno().intValue();
            disp = 100 - par;
        }

        dataTratorTransbOperAno.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbOperAno.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorTransbOperAno.data(dataTratorTransbOperAno);

        pieTratorTransbOperAno.labels().position("outside");

        pieTratorTransbOperAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorTransbOperAno.setChart(pieTratorTransbOperAno);

        final AnyChartView grafTratorTransbCampoDia = findViewById(R.id.graf_trator_transb_campo_dia);
        grafTratorTransbCampoDia.setProgressBar(findViewById(R.id.progress_bar_trator_transb_campo_dia));
        APIlib.getInstance().setActiveAnyChartView(grafTratorTransbCampoDia);

        grafTratorTransbCampoDia.setAnimationCacheEnabled(false);

        final Pie pieTratorTransbCampoDia = AnyChart.pie();

        List<DataEntry> dataTratorTransbCampoDia = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoTratorTransbDia() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoTratorTransbDia().intValue();
            disp = 100 - par;
        }

        dataTratorTransbCampoDia.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbCampoDia.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorTransbCampoDia.data(dataTratorTransbCampoDia);

        pieTratorTransbCampoDia.labels().position("outside");

        pieTratorTransbCampoDia.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorTransbCampoDia.setChart(pieTratorTransbCampoDia);

        final AnyChartView grafTratorTransbCampoMes = findViewById(R.id.graf_trator_transb_campo_mes);
        grafTratorTransbCampoMes.setProgressBar(findViewById(R.id.progress_bar_trator_transb_campo_mes));
        APIlib.getInstance().setActiveAnyChartView(grafTratorTransbCampoMes);

        grafTratorTransbCampoMes.setAnimationCacheEnabled(false);

        final Pie pieTratorTransbCampoMes = AnyChart.pie();

        List<DataEntry> dataTratorTransbCampoMes = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoTratorTransbMes() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoTratorTransbMes().intValue();
            disp = 100 - par;
        }

        dataTratorTransbCampoMes.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbCampoMes.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorTransbCampoMes.data(dataTratorTransbCampoMes);

        pieTratorTransbCampoMes.labels().position("outside");

        pieTratorTransbCampoMes.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorTransbCampoMes.setChart(pieTratorTransbCampoMes);

        final AnyChartView grafTratorTransbCampoAno = findViewById(R.id.graf_trator_transb_campo_ano);
        grafTratorTransbCampoAno.setProgressBar(findViewById(R.id.progress_bar_trator_transb_campo_ano));
        APIlib.getInstance().setActiveAnyChartView(grafTratorTransbCampoAno);

        grafTratorTransbCampoAno.setAnimationCacheEnabled(false);

        final Pie pieTratorTransbCampoAno = AnyChart.pie();

        List<DataEntry> dataTratorTransbCampoAno = new ArrayList<>();

        if(grafDispEquipPlantioTO.getValorCampoTratorTransbAno() > 100 ){
            disp = 100;
            par = 0;
        }
        else{
            par = grafDispEquipPlantioTO.getValorCampoTratorTransbAno().intValue();
            disp = 100 - par;
        }

        dataTratorTransbCampoAno.add(new PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbCampoAno.add(new PersonDataEntry("PAR", par, "red"));

        pieTratorTransbCampoAno.data(dataTratorTransbCampoAno);

        pieTratorTransbCampoAno.labels().position("outside");

        pieTratorTransbCampoAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorTransbCampoAno.setChart(pieTratorTransbCampoAno);

    }

    private class PersonDataEntry extends ValueDataEntry {
        PersonDataEntry(String x, Number value, String fill) {
            super(x, value);
            setValue("fill", fill);
        }
    }

}
