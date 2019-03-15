package br.com.usinasantafe.pmm;

import android.os.Bundle;

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

public class GrafDispActivity extends ActivityGeneric {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_disp);

        final AnyChartView grafTratorPlantOperDia = findViewById(R.id.graf_trator_plant_oper_dia);
        grafTratorPlantOperDia.setProgressBar(findViewById(R.id.progress_bar_trator_plant_oper_dia));
        APIlib.getInstance().setActiveAnyChartView(grafTratorPlantOperDia);

        final Pie pieTratorPlantOperDia = AnyChart.pie();

        List<DataEntry> dataTratorPlantOperDia = new ArrayList<>();
        dataTratorPlantOperDia.add(new ValueDataEntry("DISP", 100));
        dataTratorPlantOperDia.add(new ValueDataEntry("PAR", 0));

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

        final Pie pieTratorPlantOperMes = AnyChart.pie();

        List<DataEntry> dataTratorPlantOperMes = new ArrayList<>();
        dataTratorPlantOperMes.add(new ValueDataEntry("DISP", 100));
        dataTratorPlantOperMes.add(new ValueDataEntry("PAR", 0));

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

        final Pie pieTratorPlantOperAno = AnyChart.pie();

        List<DataEntry> dataTratorPlantOperAno = new ArrayList<>();
        dataTratorPlantOperAno.add(new ValueDataEntry("DISP", 100));
        dataTratorPlantOperAno.add(new ValueDataEntry("PAR", 0));

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

        final Pie pieTratorPlantCampoDia = AnyChart.pie();

        List<DataEntry> dataTratorPlantCampoDia = new ArrayList<>();
        dataTratorPlantCampoDia.add(new ValueDataEntry("DISP", 85));
        dataTratorPlantCampoDia.add(new ValueDataEntry("PAR", 15));

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

        final Pie pieTratorPlantCampoMes = AnyChart.pie();

        List<DataEntry> dataTratorPlantCampoMes = new ArrayList<>();
        dataTratorPlantCampoMes.add(new ValueDataEntry("DISP", 84));
        dataTratorPlantCampoMes.add(new ValueDataEntry("PAR", 16));

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

        final Pie pieTratorPlantCampoAno = AnyChart.pie();

        List<DataEntry> dataTratorPlantCampoAno = new ArrayList<>();
        dataTratorPlantCampoAno.add(new ValueDataEntry("DISP", 84));
        dataTratorPlantCampoAno.add(new ValueDataEntry("PAR", 16));

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

        final Pie pieCaminhaoOperDia = AnyChart.pie();

        List<DataEntry> dataCaminhaoOperDia = new ArrayList<>();
        dataCaminhaoOperDia.add(new ValueDataEntry("DISP", 78));
        dataCaminhaoOperDia.add(new ValueDataEntry("PAR", 22));

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

        final Pie pieCaminhaoOperMes = AnyChart.pie();

        List<DataEntry> dataCaminhaoOperMes = new ArrayList<>();
        dataCaminhaoOperMes.add(new ValueDataEntry("DISP", 85));
        dataCaminhaoOperMes.add(new ValueDataEntry("PAR", 15));

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

        final Pie pieCaminhaoOperAno = AnyChart.pie();

        List<DataEntry> dataCaminhaoOperAno = new ArrayList<>();
        dataCaminhaoOperAno.add(new ValueDataEntry("DISP", 85));
        dataCaminhaoOperAno.add(new ValueDataEntry("PAR", 15));

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

        final Pie pieCaminhaoCampoDia = AnyChart.pie();

        List<DataEntry> dataCaminhaoCampoDia = new ArrayList<>();
        dataCaminhaoCampoDia.add(new ValueDataEntry("DISP", 90));
        dataCaminhaoCampoDia.add(new ValueDataEntry("PAR", 10));

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

        final Pie pieCaminhaoCampoMes = AnyChart.pie();

        List<DataEntry> dataCaminhaoCampoMes = new ArrayList<>();
        dataCaminhaoCampoMes.add(new ValueDataEntry("DISP", 92));
        dataCaminhaoCampoMes.add(new ValueDataEntry("PAR", 8));

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

        final Pie pieCaminhaoCampoAno = AnyChart.pie();

        List<DataEntry> dataCaminhaoCampoAno = new ArrayList<>();
        dataCaminhaoCampoAno.add(new ValueDataEntry("DISP", 92));
        dataCaminhaoCampoAno.add(new ValueDataEntry("PAR", 8));

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

        final Pie pieColhedoraOperDia = AnyChart.pie();

        List<DataEntry> dataColhedoraOperDia = new ArrayList<>();
        dataColhedoraOperDia.add(new ValueDataEntry("DISP", 40));
        dataColhedoraOperDia.add(new ValueDataEntry("PAR", 60));

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

        final Pie pieColhedoraOperMes = AnyChart.pie();

        List<DataEntry> dataColhedoraOperMes = new ArrayList<>();
        dataColhedoraOperMes.add(new ValueDataEntry("DISP", 46));
        dataColhedoraOperMes.add(new ValueDataEntry("PAR", 54));

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

        final Pie pieColhedoraOperAno = AnyChart.pie();

        List<DataEntry> dataColhedoraOperAno = new ArrayList<>();
        dataColhedoraOperAno.add(new ValueDataEntry("DISP", 46));
        dataColhedoraOperAno.add(new ValueDataEntry("PAR", 54));

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

        final Pie pieColhedoraCampoDia = AnyChart.pie();

        List<DataEntry> dataColhedoraCampoDia = new ArrayList<>();
        dataColhedoraCampoDia.add(new ValueDataEntry("DISP", 88));
        dataColhedoraCampoDia.add(new ValueDataEntry("PAR", 12));

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

        final Pie pieColhedoraCampoMes = AnyChart.pie();

        List<DataEntry> dataColhedoraCampoMes = new ArrayList<>();
        dataColhedoraCampoMes.add(new ValueDataEntry("DISP", 93));
        dataColhedoraCampoMes.add(new ValueDataEntry("PAR", 7));

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

        final Pie pieColhedoraCampoAno = AnyChart.pie();

        List<DataEntry> dataColhedoraCampoAno = new ArrayList<>();
        dataColhedoraCampoAno.add(new ValueDataEntry("DISP", 93));
        dataColhedoraCampoAno.add(new ValueDataEntry("PAR", 7));

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

        final Pie pieTratorTransbOperDia = AnyChart.pie();

        List<DataEntry> dataTratorTransbOperDia = new ArrayList<>();
        dataTratorTransbOperDia.add(new ValueDataEntry("DISP", 90));
        dataTratorTransbOperDia.add(new ValueDataEntry("PAR", 10));

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

        final Pie pieTratorTransbOperMes = AnyChart.pie();

        List<DataEntry> dataTratorTransbOperMes = new ArrayList<>();
        dataTratorTransbOperMes.add(new ValueDataEntry("DISP", 91));
        dataTratorTransbOperMes.add(new ValueDataEntry("PAR", 9));

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

        final Pie pieTratorTransbOperAno = AnyChart.pie();

        List<DataEntry> dataTratorTransbOperAno = new ArrayList<>();
        dataTratorTransbOperAno.add(new ValueDataEntry("DISP", 91));
        dataTratorTransbOperAno.add(new ValueDataEntry("PAR", 9));

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

        final Pie pieTratorTransbCampoDia = AnyChart.pie();

        List<DataEntry> dataTratorTransbCampoDia = new ArrayList<>();
        dataTratorTransbCampoDia.add(new ValueDataEntry("DISP", 99));
        dataTratorTransbCampoDia.add(new ValueDataEntry("PAR", 1));

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

        final Pie pieTratorTransbCampoMes = AnyChart.pie();

        List<DataEntry> dataTratorTransbCampoMes = new ArrayList<>();
        dataTratorTransbCampoMes.add(new ValueDataEntry("DISP", 97));
        dataTratorTransbCampoMes.add(new ValueDataEntry("PAR", 3));

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

        final Pie pieTratorTransbCampoAno = AnyChart.pie();

        List<DataEntry> dataTratorTransbCampoAno = new ArrayList<>();
        dataTratorTransbCampoAno.add(new ValueDataEntry("DISP", 97));
        dataTratorTransbCampoAno.add(new ValueDataEntry("PAR", 3));

        pieTratorTransbCampoAno.data(dataTratorTransbCampoAno);

        pieTratorTransbCampoAno.labels().position("outside");

        pieTratorTransbCampoAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorTransbCampoAno.setChart(pieTratorTransbCampoAno);



    }
}
