package br.com.usinasantafe.pmm;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;

public class GrafProdActivity extends ActivityGeneric {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_prod);

        final AnyChartView grafProdFrenteDia = findViewById(R.id.graf_prod_frente_dia);
        grafProdFrenteDia.setProgressBar(findViewById(R.id.progress_bar_prod_frente_dia));
        APIlib.getInstance().setActiveAnyChartView(grafProdFrenteDia);

        final Cartesian barProdFrenteDia = AnyChart.bar();

        List<DataEntry> seriesProdFrenteDia = new ArrayList<>();
        seriesProdFrenteDia.add(new CustomDataEntry("DIAS", 27.62, 42.5));

        Set setProdFrenteDia = Set.instantiate();
        setProdFrenteDia.data(seriesProdFrenteDia);
        Mapping series1ProdFrenteDia = setProdFrenteDia.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2ProdFrenteDia = setProdFrenteDia.mapAs("{ x: 'x', value: 'value2' }");

        Bar ser1ProdFrenteDia = barProdFrenteDia.bar(series1ProdFrenteDia);
        ser1ProdFrenteDia.labels().format("{%Value}");
        ser1ProdFrenteDia.labels().enabled(true);
        ser1ProdFrenteDia.name("META")
                .color("Red");
        ser1ProdFrenteDia.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER);

        Bar ser2ProdFrenteDia = barProdFrenteDia.bar(series2ProdFrenteDia);
        ser2ProdFrenteDia.labels().format("{%Value}");
        ser2ProdFrenteDia.labels().enabled(true);
        ser2ProdFrenteDia.name("REALIZADO");
        ser2ProdFrenteDia.tooltip()
                .position("left")
                .anchor(Anchor.RIGHT_CENTER);

        barProdFrenteDia.isVertical(false);
        barProdFrenteDia.legend().enabled(true);
        barProdFrenteDia.legend().inverted(true);
        barProdFrenteDia.legend().fontSize(13d);
        barProdFrenteDia.legend().padding(0d, 0d, 20d, 0d);

        grafProdFrenteDia.setChart(barProdFrenteDia);

        final AnyChartView grafProdFrenteMes = findViewById(R.id.graf_prod_frente_mes);
        grafProdFrenteMes.setProgressBar(findViewById(R.id.progress_bar_prod_frente_mes));
        APIlib.getInstance().setActiveAnyChartView(grafProdFrenteMes);

        final Cartesian barProdFrenteMes = AnyChart.bar();

        List<DataEntry> seriesProdFrenteMes = new ArrayList<>();
        seriesProdFrenteMes.add(new CustomDataEntry("MÊS", 296.21, 411.29));

        Set setProdFrenteMes = Set.instantiate();
        setProdFrenteMes.data(seriesProdFrenteMes);
        Mapping series1ProdFrenteMes = setProdFrenteMes.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2ProdFrenteMes = setProdFrenteMes.mapAs("{ x: 'x', value: 'value2' }");

        Bar ser1ProdFrenteMes = barProdFrenteMes.bar(series1ProdFrenteMes);
        ser1ProdFrenteMes.labels().format("{%Value}");
        ser1ProdFrenteMes.labels().enabled(true);
        ser1ProdFrenteMes.name("META")
                .color("Red");
        ser1ProdFrenteMes.tooltip()
                .position("right")
                .anchor(Anchor.CENTER_TOP);

        Bar ser2ProdFrenteMes = barProdFrenteMes.bar(series2ProdFrenteMes);
        ser2ProdFrenteMes.labels().format("{%Value}");
        ser2ProdFrenteMes.labels().enabled(true);
        ser2ProdFrenteMes.name("REALIZADO");
        ser2ProdFrenteMes.tooltip()
                .position("left")
                .anchor(Anchor.CENTER_TOP);

        barProdFrenteMes.isVertical(false);
        barProdFrenteMes.legend().enabled(true);
        barProdFrenteMes.legend().inverted(true);
        barProdFrenteMes.legend().fontSize(13d);
        barProdFrenteMes.legend().padding(0d, 0d, 20d, 0d);

        grafProdFrenteMes.setChart(barProdFrenteMes);

        final AnyChartView grafProdFrenteAno = findViewById(R.id.graf_prod_frente_ano);
        grafProdFrenteAno.setProgressBar(findViewById(R.id.progress_bar_prod_frente_ano));
        APIlib.getInstance().setActiveAnyChartView(grafProdFrenteAno);

        final Cartesian barProdFrenteAno = AnyChart.bar();

        List<DataEntry> seriesProdFrenteAno = new ArrayList<>();
        seriesProdFrenteAno.add(new CustomDataEntry("ANO", 296.21, 3627));

        Set setProdFrenteAno = Set.instantiate();
        setProdFrenteAno.data(seriesProdFrenteAno);
        Mapping series1ProdFrenteAno = setProdFrenteAno.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2ProdFrenteAno = setProdFrenteAno.mapAs("{ x: 'x', value: 'value2' }");

        Bar ser1ProdFrenteAno = barProdFrenteAno.bar(series1ProdFrenteAno);
        ser1ProdFrenteAno.labels().format("{%Value}");
        ser1ProdFrenteAno.labels().enabled(true);
        ser1ProdFrenteAno.name("META")
                .color("Red");
        ser1ProdFrenteAno.tooltip()
                .position("right")
                .anchor(Anchor.CENTER_TOP);

        Bar ser2ProdFrenteAno = barProdFrenteAno.bar(series2ProdFrenteAno);
        ser2ProdFrenteAno.labels().format("{%Value}");
        ser2ProdFrenteAno.labels().enabled(true);
        ser2ProdFrenteAno.name("REALIZADO");
        ser2ProdFrenteAno.tooltip()
                .position("left")
                .anchor(Anchor.CENTER_TOP);

        barProdFrenteAno.isVertical(false);
        barProdFrenteAno.legend().enabled(true);
        barProdFrenteAno.legend().inverted(true);
        barProdFrenteAno.legend().fontSize(13d);
        barProdFrenteAno.legend().padding(0d, 0d, 20d, 0d);

        grafProdFrenteAno.setChart(barProdFrenteAno);





        final AnyChartView grafMediaPlanDia = findViewById(R.id.graf_media_plant_dia);
        grafMediaPlanDia.setProgressBar(findViewById(R.id.progress_bar_media_plant_dia));
        APIlib.getInstance().setActiveAnyChartView(grafMediaPlanDia);

        final Cartesian barMediaPlanDia = AnyChart.bar();

        List<DataEntry> seriesMediaPlanDia = new ArrayList<>();
        seriesMediaPlanDia.add(new CustomDataEntry("DIAS", 5.52, 8.5));

        Set setMediaPlanDia = Set.instantiate();
        setMediaPlanDia.data(seriesMediaPlanDia);
        Mapping series1MediaPlanDia = setMediaPlanDia.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2MediaPlanDia = setMediaPlanDia.mapAs("{ x: 'x', value: 'value2' }");

        Bar ser1MediaPlanDia = barMediaPlanDia.bar(series1MediaPlanDia);
        ser1MediaPlanDia.labels().format("{%Value}");
        ser1MediaPlanDia.labels().enabled(true);
        ser1MediaPlanDia.name("META")
                .color("Red");
        ser1MediaPlanDia.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER);

        Bar ser2MediaPlanDia = barMediaPlanDia.bar(series2MediaPlanDia);
        ser2MediaPlanDia.labels().format("{%Value}");
        ser2MediaPlanDia.labels().enabled(true);
        ser2MediaPlanDia.name("REALIZADO");
        ser2MediaPlanDia.tooltip()
                .position("left")
                .anchor(Anchor.RIGHT_CENTER);

        barMediaPlanDia.isVertical(false);
        barMediaPlanDia.legend().enabled(true);
        barMediaPlanDia.legend().inverted(true);
        barMediaPlanDia.legend().fontSize(13d);
        barMediaPlanDia.legend().padding(0d, 0d, 20d, 0d);

        grafMediaPlanDia.setChart(barMediaPlanDia);

        final AnyChartView grafMediaPlanMes = findViewById(R.id.graf_media_plant_mes);
        grafMediaPlanMes.setProgressBar(findViewById(R.id.progress_bar_media_plant_mes));
        APIlib.getInstance().setActiveAnyChartView(grafMediaPlanMes);

        final Cartesian barMediaPlanMes = AnyChart.bar();

        List<DataEntry> seriesMediaPlanMes = new ArrayList<>();
        seriesMediaPlanMes.add(new CustomDataEntry("MÊS", 8.5, 7.22));

        Set setMediaPlanMes = Set.instantiate();
        setMediaPlanMes.data(seriesMediaPlanMes);
        Mapping series1MediaPlanMes = setMediaPlanMes.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2MediaPlanMes = setMediaPlanMes.mapAs("{ x: 'x', value: 'value2' }");

        Bar ser1MediaPlanMes = barMediaPlanMes.bar(series1MediaPlanMes);
        ser1MediaPlanMes.labels().format("{%Value}");
        ser1MediaPlanMes.labels().enabled(true);
        ser1MediaPlanMes.name("META")
                .color("Red");
        ser1MediaPlanMes.tooltip()
                .position("right")
                .anchor(Anchor.CENTER_TOP);

        Bar ser2MediaPlanMes = barMediaPlanMes.bar(series2MediaPlanMes);
        ser2MediaPlanMes.labels().format("{%Value}");
        ser2MediaPlanMes.labels().enabled(true);
        ser2MediaPlanMes.name("REALIZADO");
        ser2MediaPlanMes.tooltip()
                .position("left")
                .anchor(Anchor.CENTER_TOP);

        barMediaPlanMes.isVertical(false);
        barMediaPlanMes.legend().enabled(true);
        barMediaPlanMes.legend().inverted(true);
        barMediaPlanMes.legend().fontSize(13d);
        barMediaPlanMes.legend().padding(0d, 0d, 20d, 0d);

        grafMediaPlanMes.setChart(barMediaPlanMes);

        final AnyChartView grafMediaPlanAno = findViewById(R.id.graf_media_plant_ano);
        grafMediaPlanAno.setProgressBar(findViewById(R.id.progress_bar_media_plant_ano));
        APIlib.getInstance().setActiveAnyChartView(grafMediaPlanAno);

        final Cartesian barMediaPlanAno = AnyChart.bar();

        List<DataEntry> seriesMediaPlanAno = new ArrayList<>();
        seriesMediaPlanAno.add(new CustomDataEntry("ANO", 8.5, 7.23));

        Set setMediaPlanAno = Set.instantiate();
        setMediaPlanAno.data(seriesMediaPlanAno);
        Mapping series1MediaPlanAno = setMediaPlanAno.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2MediaPlanAno = setMediaPlanAno.mapAs("{ x: 'x', value: 'value2' }");

        Bar ser1MediaPlanAno = barMediaPlanAno.bar(series1MediaPlanAno);
        ser1MediaPlanAno.labels().format("{%Value}");
        ser1MediaPlanAno.labels().enabled(true);
        ser1MediaPlanAno.name("META")
                .color("Red");
        ser1MediaPlanAno.tooltip()
                .position("right")
                .anchor(Anchor.CENTER_TOP);

        Bar ser2MediaPlanAno = barMediaPlanAno.bar(series2MediaPlanAno);
        ser2MediaPlanAno.labels().format("{%Value}");
        ser2MediaPlanAno.labels().enabled(true);
        ser2MediaPlanAno.name("REALIZADO");
        ser2MediaPlanAno.tooltip()
                .position("left")
                .anchor(Anchor.CENTER_TOP);

        barMediaPlanAno.isVertical(false);
        barMediaPlanAno.legend().enabled(true);
        barMediaPlanAno.legend().inverted(true);
        barMediaPlanAno.legend().fontSize(13d);
        barMediaPlanAno.legend().padding(0d, 0d, 20d, 0d);

        grafMediaPlanAno.setChart(barMediaPlanAno);



        final AnyChartView linePlanReal = findViewById(R.id.linha_plan_real);
        linePlanReal.setProgressBar(findViewById(R.id.progress_bar_linha_plan_real));
        APIlib.getInstance().setActiveAnyChartView(linePlanReal);

        final Cartesian lPlanReal = AnyChart.line();

        lPlanReal.animation(true);

        lPlanReal.padding(10d, 20d, 5d, 20d);

        lPlanReal.crosshair().enabled(true);
        lPlanReal.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        lPlanReal.tooltip().positionMode(TooltipPositionMode.POINT);

        List<DataEntry> seriesLinePlanReal = new ArrayList<>();
        seriesLinePlanReal.add(new CustomDataEntry("JAN", 411.29, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("FEV", 1074.29, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("MAR", 1877.54, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("ABR", 2719.04, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("MAI", 3178.04, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("JUN", 3351.44, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("JUL", 3351.44, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("AGO", 3351.44, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("SET", 3499.5, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("OUT", 3627, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("NOV", 3627, 296.21));
        seriesLinePlanReal.add(new CustomDataEntry("DEZ", 3627, 296.21));

        Set setLinePlanReal = Set.instantiate();
        setLinePlanReal.data(seriesLinePlanReal);
        Mapping series1MapLinePlanReal = setLinePlanReal.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2MapLinePlanReal = setLinePlanReal.mapAs("{ x: 'x', value: 'value2' }");

        Line series1LinePlanReal = lPlanReal.line(series1MapLinePlanReal);
        series1LinePlanReal.name("Brandy");
        series1LinePlanReal.hovered().markers().enabled(true);
        series1LinePlanReal.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1LinePlanReal.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = lPlanReal.line(series2MapLinePlanReal);
        series2.name("PLANEJADO");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("REALIZADO")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        lPlanReal.legend().enabled(true);
        lPlanReal.legend().fontSize(13d);
        lPlanReal.legend().padding(0d, 0d, 10d, 0d);

        linePlanReal.setChart(lPlanReal);

    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }

}
