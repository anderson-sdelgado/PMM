package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
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

import br.com.usinasantafe.pmm.to.tb.estaticas.GrafPlanRealPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafProdPlantioTO;

public class GrafProdActivity extends ActivityGeneric {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_prod);

        GrafProdPlantioTO grafProdPlantioTO = new GrafProdPlantioTO();
        List grafProdPlantioList = grafProdPlantioTO.all();
        for (int j = 0; j < grafProdPlantioList.size(); j++) {

            grafProdPlantioTO = (GrafProdPlantioTO) grafProdPlantioList.get(j);

            final AnyChartView grafProdFrenteDia = findViewById(R.id.graf_prod_frente_dia);
            grafProdFrenteDia.setProgressBar(findViewById(R.id.progress_bar_prod_frente_dia));
            APIlib.getInstance().setActiveAnyChartView(grafProdFrenteDia);

            final Cartesian barProdFrenteDia = AnyChart.bar();

            List<DataEntry> seriesProdFrenteDia = new ArrayList<>();
            seriesProdFrenteDia.add(new CustomDataEntry("DIAS", grafProdPlantioTO.getProdFrenteMetaDia(), grafProdPlantioTO.getProdFrenteRealDia()));

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
            seriesProdFrenteMes.add(new CustomDataEntry("MÊS", grafProdPlantioTO.getProdFrenteMetaMes(), grafProdPlantioTO.getProdFrenteRealMes()));

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
            seriesProdFrenteAno.add(new CustomDataEntry("ANO", grafProdPlantioTO.getProdFrenteMetaAno(), grafProdPlantioTO.getProdFrenteRealAno()));

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
            seriesMediaPlanDia.add(new CustomDataEntry("DIAS", grafProdPlantioTO.getMediaPlantMetaDia(), grafProdPlantioTO.getMediaPlantRealDia()));

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
            seriesMediaPlanMes.add(new CustomDataEntry("MÊS", grafProdPlantioTO.getMediaPlantMetaMes(), grafProdPlantioTO.getMediaPlantRealMes()));

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
            seriesMediaPlanAno.add(new CustomDataEntry("ANO", grafProdPlantioTO.getMediaPlantMetaAno(), grafProdPlantioTO.getMediaPlantRealAno()));

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

        }

        TextView textJanPlanMes = (TextView) findViewById(R.id.textJanPlanMes);
        TextView textJanPlanAcum = (TextView) findViewById(R.id.textJanPlanAcum);
        TextView textJanRealMes = (TextView) findViewById(R.id.textJanRealMes);
        TextView textJanRealAcum = (TextView) findViewById(R.id.textJanRealAcum);
        TextView textJanSaldMes = (TextView) findViewById(R.id.textJanSaldMes);
        TextView textJanSaldAcum = (TextView) findViewById(R.id.textJanSaldAcum);

        TextView textFevPlanMes = (TextView) findViewById(R.id.textFevPlanMes);
        TextView textFevPlanAcum = (TextView) findViewById(R.id.textFevPlanAcum);
        TextView textFevRealMes = (TextView) findViewById(R.id.textFevRealMes);
        TextView textFevRealAcum = (TextView) findViewById(R.id.textFevRealAcum);
        TextView textFevSaldMes = (TextView) findViewById(R.id.textFevSaldMes);
        TextView textFevSaldAcum = (TextView) findViewById(R.id.textFevSaldAcum);

        TextView textMarPlanMes = (TextView) findViewById(R.id.textMarPlanMes);
        TextView textMarPlanAcum = (TextView) findViewById(R.id.textMarPlanAcum);
        TextView textMarRealMes = (TextView) findViewById(R.id.textMarRealMes);
        TextView textMarRealAcum = (TextView) findViewById(R.id.textMarRealAcum);
        TextView textMarSaldMes = (TextView) findViewById(R.id.textMarSaldMes);
        TextView textMarSaldAcum = (TextView) findViewById(R.id.textMarSaldAcum);

        TextView textAbrPlanMes = (TextView) findViewById(R.id.textAbrPlanMes);
        TextView textAbrPlanAcum = (TextView) findViewById(R.id.textAbrPlanAcum);
        TextView textAbrRealMes = (TextView) findViewById(R.id.textAbrRealMes);
        TextView textAbrRealAcum = (TextView) findViewById(R.id.textAbrRealAcum);
        TextView textAbrSaldMes = (TextView) findViewById(R.id.textAbrSaldMes);
        TextView textAbrSaldAcum = (TextView) findViewById(R.id.textAbrSaldAcum);

        TextView textMaiPlanMes = (TextView) findViewById(R.id.textMaiPlanMes);
        TextView textMaiPlanAcum = (TextView) findViewById(R.id.textMaiPlanAcum);
        TextView textMaiRealMes = (TextView) findViewById(R.id.textMaiRealMes);
        TextView textMaiRealAcum = (TextView) findViewById(R.id.textMaiRealAcum);
        TextView textMaiSaldMes = (TextView) findViewById(R.id.textMaiSaldMes);
        TextView textMaiSaldAcum = (TextView) findViewById(R.id.textMaiSaldAcum);

        TextView textJunPlanMes = (TextView) findViewById(R.id.textJunPlanMes);
        TextView textJunPlanAcum = (TextView) findViewById(R.id.textJunPlanAcum);
        TextView textJunRealMes = (TextView) findViewById(R.id.textJunRealMes);
        TextView textJunRealAcum = (TextView) findViewById(R.id.textJunRealAcum);
        TextView textJunSaldMes = (TextView) findViewById(R.id.textJunSaldMes);
        TextView textJunSaldAcum = (TextView) findViewById(R.id.textJunSaldAcum);

        TextView textJulPlanMes = (TextView) findViewById(R.id.textJulPlanMes);
        TextView textJulPlanAcum = (TextView) findViewById(R.id.textJulPlanAcum);
        TextView textJulRealMes = (TextView) findViewById(R.id.textJulRealMes);
        TextView textJulRealAcum = (TextView) findViewById(R.id.textJulRealAcum);
        TextView textJulSaldMes = (TextView) findViewById(R.id.textJulSaldMes);
        TextView textJulSaldAcum = (TextView) findViewById(R.id.textJulSaldAcum);

        TextView textAgoPlanMes = (TextView) findViewById(R.id.textAgoPlanMes);
        TextView textAgoPlanAcum = (TextView) findViewById(R.id.textAgoPlanAcum);
        TextView textAgoRealMes = (TextView) findViewById(R.id.textAgoRealMes);
        TextView textAgoRealAcum = (TextView) findViewById(R.id.textAgoRealAcum);
        TextView textAgoSaldMes = (TextView) findViewById(R.id.textAgoSaldMes);
        TextView textAgoSaldAcum = (TextView) findViewById(R.id.textAgoSaldAcum);

        TextView textSetPlanMes = (TextView) findViewById(R.id.textSetPlanMes);
        TextView textSetPlanAcum = (TextView) findViewById(R.id.textSetPlanAcum);
        TextView textSetRealMes = (TextView) findViewById(R.id.textSetRealMes);
        TextView textSetRealAcum = (TextView) findViewById(R.id.textSetRealAcum);
        TextView textSetSaldMes = (TextView) findViewById(R.id.textSetSaldMes);
        TextView textSetSaldAcum = (TextView) findViewById(R.id.textSetSaldAcum);

        TextView textOutPlanMes = (TextView) findViewById(R.id.textOutPlanMes);
        TextView textOutPlanAcum = (TextView) findViewById(R.id.textOutPlanAcum);
        TextView textOutRealMes = (TextView) findViewById(R.id.textOutRealMes);
        TextView textOutRealAcum = (TextView) findViewById(R.id.textOutRealAcum);
        TextView textOutSaldMes = (TextView) findViewById(R.id.textOutSaldMes);
        TextView textOutSaldAcum = (TextView) findViewById(R.id.textOutSaldAcum);

        TextView textNovPlanMes = (TextView) findViewById(R.id.textNovPlanMes);
        TextView textNovPlanAcum = (TextView) findViewById(R.id.textNovPlanAcum);
        TextView textNovRealMes = (TextView) findViewById(R.id.textNovRealMes);
        TextView textNovRealAcum = (TextView) findViewById(R.id.textNovRealAcum);
        TextView textNovSaldMes = (TextView) findViewById(R.id.textNovSaldMes);
        TextView textNovSaldAcum = (TextView) findViewById(R.id.textNovSaldAcum);

        TextView textDezPlanMes = (TextView) findViewById(R.id.textDezPlanMes);
        TextView textDezPlanAcum = (TextView) findViewById(R.id.textDezPlanAcum);
        TextView textDezRealMes = (TextView) findViewById(R.id.textDezRealMes);
        TextView textDezRealAcum = (TextView) findViewById(R.id.textDezRealAcum);
        TextView textDezSaldMes = (TextView) findViewById(R.id.textDezSaldMes);
        TextView textDezSaldAcum = (TextView) findViewById(R.id.textDezSaldAcum);

        GrafPlanRealPlantioTO grafPlanRealPlantioTO = new GrafPlanRealPlantioTO();
        List grafPlanRealPlantioList = grafPlanRealPlantioTO.all();

        grafPlanRealPlantioTO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(0);
        textJanPlanMes.setText(String.valueOf(grafPlanRealPlantioTO.getValorMesPlan()));
        textJanPlanAcum.setText(String.valueOf(grafPlanRealPlantioTO.getValorAcumPlan()));
        textJanRealMes.setText(String.valueOf(grafPlanRealPlantioTO.getValorMesReal()));
        textJanRealAcum.setText(String.valueOf(grafPlanRealPlantioTO.getValorAcumReal()));
        textJanSaldMes.setText(String.valueOf(grafPlanRealPlantioTO.getValorMesPlan() - grafPlanRealPlantioTO.getValorMesReal()));
        textJanSaldAcum.setText(String.valueOf(grafPlanRealPlantioTO.getValorAcumPlan() - grafPlanRealPlantioTO.getValorAcumReal()));

        grafPlanRealPlantioTO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(1);
        textFevPlanMes.setText(String.valueOf(grafPlanRealPlantioTO.getValorMesPlan()));
        textFevPlanAcum.setText(String.valueOf(grafPlanRealPlantioTO.getValorAcumPlan()));
        textFevRealMes.setText(String.valueOf(grafPlanRealPlantioTO.getValorMesReal()));
        textFevRealAcum.setText(String.valueOf(grafPlanRealPlantioTO.getValorAcumReal()));
        textFevSaldMes.setText(String.valueOf(grafPlanRealPlantioTO.getValorMesPlan() - grafPlanRealPlantioTO.getValorMesReal()));
        textFevSaldAcum.setText(String.valueOf(grafPlanRealPlantioTO.getValorAcumPlan() - grafPlanRealPlantioTO.getValorAcumReal()));

        grafPlanRealPlantioTO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2);
        textMarPlanMes.setText(String.valueOf(grafPlanRealPlantioTO.getValorMesPlan()));
        textMarPlanAcum.setText(String.valueOf(grafPlanRealPlantioTO.getValorAcumPlan()));
        textMarRealMes.setText(String.valueOf(grafPlanRealPlantioTO.getValorMesReal()));
        textMarRealAcum.setText(String.valueOf(grafPlanRealPlantioTO.getValorAcumReal()));
        textMarSaldMes.setText(String.valueOf(grafPlanRealPlantioTO.getValorMesPlan() - grafPlanRealPlantioTO.getValorMesReal()));
        textMarSaldAcum.setText(String.valueOf(grafPlanRealPlantioTO.getValorAcumPlan() - grafPlanRealPlantioTO.getValorAcumReal()));

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
        seriesLinePlanReal.add(new CustomDataEntry("JAN", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(0)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(0)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("FEV", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(1)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(1)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("MAR", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("ABR", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("MAI", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("JUN", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("JUL", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("AGO", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("SET", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("OUT", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("NOV", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));
        seriesLinePlanReal.add(new CustomDataEntry("DEZ", ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumPlan(), ((GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2)).getValorAcumReal()));

        Set setLinePlanReal = Set.instantiate();
        setLinePlanReal.data(seriesLinePlanReal);
        Mapping series1MapLinePlanReal = setLinePlanReal.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2MapLinePlanReal = setLinePlanReal.mapAs("{ x: 'x', value: 'value2' }");

        Line series1LinePlanReal = lPlanReal.line(series1MapLinePlanReal);
        series1LinePlanReal.name("PLANEJADO");
        series1LinePlanReal.color("red");
        series1LinePlanReal.hovered().markers().enabled(true);
        series1LinePlanReal.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1LinePlanReal.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2LinePlanReal = lPlanReal.line(series2MapLinePlanReal);
        series2LinePlanReal.name("REALIZADO");
        series2LinePlanReal.color("blue");
        series2LinePlanReal.hovered().markers().enabled(true);
        series2LinePlanReal.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2LinePlanReal.tooltip()
                .position("left")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        lPlanReal.legend().enabled(true);
        lPlanReal.legend().fontSize(13d);
        lPlanReal.legend().padding(0d, 0d, 10d, 0d);

        linePlanReal.setChart(lPlanReal);

        Button buttonProxGrafProd = (Button) findViewById(R.id.buttonProxGrafProd);

        buttonProxGrafProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(GrafProdActivity.this, GrafDispActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }

}
