package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.charts.CircularGauge;
import com.anychart.enums.Anchor;
import com.anychart.graphics.vector.text.HAlign;

import java.util.List;

import br.com.usinasantafe.pmm.to.tb.estaticas.GrafQualPlantioTO;

public class GrafTermQualActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_term_qual);

        GrafQualPlantioTO grafQualPlantioTO = new GrafQualPlantioTO();
        List grafQualPlantioList = grafQualPlantioTO.all();
        grafQualPlantioTO = (GrafQualPlantioTO) grafQualPlantioList.get(0);

        final AnyChartView termCalibAduboDia = findViewById(R.id.term_calib_adubo_dia);
        termCalibAduboDia.setProgressBar(findViewById(R.id.progress_bar_term_calib_adubo_dia));
        APIlib.getInstance().setActiveAnyChartView(termCalibAduboDia);

        termCalibAduboDia.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermCalibAduboDia = AnyChart.circular();
        gaugeTermCalibAduboDia.padding("10%");
        gaugeTermCalibAduboDia.startAngle(270);
        gaugeTermCalibAduboDia.sweepAngle(180);
        gaugeTermCalibAduboDia.fill("white");

        gaugeTermCalibAduboDia.title("DIA");

        double calibAduboDia = calcNotaCalibAdub(grafQualPlantioTO.getValorCalibAdubDia());
        gaugeTermCalibAduboDia.data(new SingleValueDataSet( new Double[]{calibAduboDia}));

        gaugeTermCalibAduboDia.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermCalibAduboDia.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermCalibAduboDia.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermCalibAduboDia.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermCalibAduboDia.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermCalibAduboDia.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermCalibAduboDia.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermCalibAduboDia.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermCalibAduboDia.cap()
                .radius("3%");

        gaugeTermCalibAduboDia.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermCalibAduboDia.label(1)
                .fontSize("20px")
                .text("" + calibAduboDia + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termCalibAduboDia.setChart(gaugeTermCalibAduboDia);

        final AnyChartView termCalibAduboMes = findViewById(R.id.term_calib_adubo_mes);
        termCalibAduboMes.setProgressBar(findViewById(R.id.progress_bar_term_calib_adubo_mes));
        APIlib.getInstance().setActiveAnyChartView(termCalibAduboMes);

        termCalibAduboMes.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermCalibAduboMes = AnyChart.circular();
        gaugeTermCalibAduboMes.padding("10%");
        gaugeTermCalibAduboMes.startAngle(270);
        gaugeTermCalibAduboMes.sweepAngle(180);
        gaugeTermCalibAduboMes.fill("white");

        gaugeTermCalibAduboMes.title("MÊS");

        double calibAduboMes = calcNotaCalibAdub(grafQualPlantioTO.getValorCalibAdubMes());
        gaugeTermCalibAduboMes.data(new SingleValueDataSet(new Double[]{calibAduboMes}));

        gaugeTermCalibAduboMes.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermCalibAduboMes.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermCalibAduboMes.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermCalibAduboMes.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermCalibAduboMes.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermCalibAduboMes.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermCalibAduboMes.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermCalibAduboMes.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermCalibAduboMes.cap()
                .radius("3%");

        gaugeTermCalibAduboMes.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermCalibAduboMes.label(1)
                .fontSize("20px")
                .text("" + calibAduboMes + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termCalibAduboMes.setChart(gaugeTermCalibAduboMes);

        final AnyChartView termCalibAduboAno = findViewById(R.id.term_calib_adubo_ano);
        termCalibAduboAno.setProgressBar(findViewById(R.id.progress_bar_term_calib_adubo_ano));
        APIlib.getInstance().setActiveAnyChartView(termCalibAduboAno);

        termCalibAduboAno.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermCalibAduboAno = AnyChart.circular();
        gaugeTermCalibAduboAno.padding("10%");
        gaugeTermCalibAduboAno.startAngle(270);
        gaugeTermCalibAduboAno.sweepAngle(180);
        gaugeTermCalibAduboAno.fill("white");

        gaugeTermCalibAduboAno.title("ANO");

        double calibAduboAno = calcNotaCalibAdub(grafQualPlantioTO.getValorCalibAdubAno());
        gaugeTermCalibAduboAno.data(new SingleValueDataSet(new Double[]{calibAduboAno}));

        gaugeTermCalibAduboAno.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermCalibAduboAno.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermCalibAduboAno.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermCalibAduboAno.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermCalibAduboAno.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermCalibAduboAno.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermCalibAduboAno.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermCalibAduboAno.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermCalibAduboAno.cap()
                .radius("3%");

        gaugeTermCalibAduboAno.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermCalibAduboAno.label(1)
                .fontSize("20px")
                .text("" + calibAduboAno + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termCalibAduboAno.setChart(gaugeTermCalibAduboAno);

        final AnyChartView termCalibInsetDia = findViewById(R.id.term_calib_inset_dia);
        termCalibInsetDia.setProgressBar(findViewById(R.id.progress_bar_term_calib_inset_dia));
        APIlib.getInstance().setActiveAnyChartView(termCalibInsetDia);

        termCalibInsetDia.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermCalibInsetDia = AnyChart.circular();
        gaugeTermCalibInsetDia.padding("10%");
        gaugeTermCalibInsetDia.startAngle(270);
        gaugeTermCalibInsetDia.sweepAngle(180);
        gaugeTermCalibInsetDia.fill("white");

        gaugeTermCalibInsetDia.title("DIA");

        double calibInsetDia = calcNotaCalibInset(grafQualPlantioTO.getValorCalibInsetDia());
        gaugeTermCalibInsetDia.data(new SingleValueDataSet( new Double[]{calibInsetDia}));

        gaugeTermCalibInsetDia.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermCalibInsetDia.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermCalibInsetDia.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermCalibInsetDia.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermCalibInsetDia.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermCalibInsetDia.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermCalibInsetDia.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermCalibInsetDia.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermCalibInsetDia.cap()
                .radius("3%");

        gaugeTermCalibInsetDia.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermCalibInsetDia.label(1)
                .fontSize("20px")
                .text("" + calibInsetDia + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termCalibInsetDia.setChart(gaugeTermCalibInsetDia);

        final AnyChartView termCalibInsetMes = findViewById(R.id.term_calib_inset_mes);
        termCalibInsetMes.setProgressBar(findViewById(R.id.progress_bar_term_calib_inset_mes));
        APIlib.getInstance().setActiveAnyChartView(termCalibInsetMes);

        termCalibInsetMes.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermCalibInsetMes = AnyChart.circular();
        gaugeTermCalibInsetMes.padding("10%");
        gaugeTermCalibInsetMes.startAngle(270);
        gaugeTermCalibInsetMes.sweepAngle(180);
        gaugeTermCalibInsetMes.fill("white");

        gaugeTermCalibInsetMes.title("MÊS");

        double calibInsetMes = calcNotaCalibInset(grafQualPlantioTO.getValorCalibInsetMes());
        gaugeTermCalibInsetMes.data(new SingleValueDataSet(new Double[]{calibInsetMes}));

        gaugeTermCalibInsetMes.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermCalibInsetMes.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermCalibInsetMes.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermCalibInsetMes.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermCalibInsetMes.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermCalibInsetMes.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermCalibInsetMes.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermCalibInsetMes.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermCalibInsetMes.cap()
                .radius("3%");

        gaugeTermCalibInsetMes.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermCalibInsetMes.label(1)
                .fontSize("20px")
                .text("" + calibInsetMes + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termCalibInsetMes.setChart(gaugeTermCalibInsetMes);

        final AnyChartView termCalibInsetAno = findViewById(R.id.term_calib_inset_ano);
        termCalibInsetAno.setProgressBar(findViewById(R.id.progress_bar_term_calib_inset_ano));
        APIlib.getInstance().setActiveAnyChartView(termCalibInsetAno);

        termCalibInsetAno.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermCalibInsetAno = AnyChart.circular();
        gaugeTermCalibInsetAno.padding("10%");
        gaugeTermCalibInsetAno.startAngle(270);
        gaugeTermCalibInsetAno.sweepAngle(180);
        gaugeTermCalibInsetAno.fill("white");

        gaugeTermCalibInsetAno.title("ANO");

        double calibInsetAno = calcNotaCalibInset(grafQualPlantioTO.getValorCalibInsetAno());
        gaugeTermCalibInsetAno.data(new SingleValueDataSet(new Double[]{calibInsetAno}));

        gaugeTermCalibInsetAno.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermCalibInsetAno.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermCalibInsetAno.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermCalibInsetAno.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermCalibInsetAno.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermCalibInsetAno.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermCalibInsetAno.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermCalibInsetAno.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermCalibInsetAno.cap()
                .radius("3%");

        gaugeTermCalibInsetAno.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermCalibInsetAno.label(1)
                .fontSize("20px")
                .text("" + calibInsetAno + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termCalibInsetAno.setChart(gaugeTermCalibInsetAno);

        final AnyChartView termGemasDia = findViewById(R.id.term_gemas_dia);
        termGemasDia.setProgressBar(findViewById(R.id.progress_bar_term_gemas_dia));
        APIlib.getInstance().setActiveAnyChartView(termGemasDia);

        termGemasDia.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermGemasDia = AnyChart.circular();
        gaugeTermGemasDia.padding("10%");
        gaugeTermGemasDia.startAngle(270);
        gaugeTermGemasDia.sweepAngle(180);
        gaugeTermGemasDia.fill("white");

        gaugeTermGemasDia.title("DIA");

        double gemasDia = calcNotaGemas(grafQualPlantioTO.getValorGemasDia());
        gaugeTermGemasDia.data(new SingleValueDataSet( new Double[]{gemasDia}));

        gaugeTermGemasDia.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermGemasDia.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermGemasDia.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermGemasDia.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermGemasDia.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermGemasDia.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermGemasDia.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermGemasDia.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermGemasDia.cap()
                .radius("3%");

        gaugeTermGemasDia.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermGemasDia.label(1)
                .fontSize("20px")
                .text("" + gemasDia + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termGemasDia.setChart(gaugeTermGemasDia);

        final AnyChartView termGemasMes = findViewById(R.id.term_gemas_mes);
        termGemasMes.setProgressBar(findViewById(R.id.progress_bar_term_gemas_mes));
        APIlib.getInstance().setActiveAnyChartView(termGemasMes);

        termGemasMes.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermGemasMes = AnyChart.circular();
        gaugeTermGemasMes.padding("10%");
        gaugeTermGemasMes.startAngle(270);
        gaugeTermGemasMes.sweepAngle(180);
        gaugeTermGemasMes.fill("white");

        gaugeTermGemasMes.title("MÊS");

        double gemasMes = calcNotaGemas(grafQualPlantioTO.getValorGemasMes());
        gaugeTermGemasMes.data(new SingleValueDataSet(new Double[]{gemasMes}));

        gaugeTermGemasMes.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermGemasMes.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermGemasMes.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermGemasMes.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermGemasMes.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermGemasMes.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermGemasMes.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermGemasMes.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermGemasMes.cap()
                .radius("3%");

        gaugeTermGemasMes.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermGemasMes.label(1)
                .fontSize("20px")
                .text("" + gemasMes + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termGemasMes.setChart(gaugeTermGemasMes);

        final AnyChartView termGemasAno = findViewById(R.id.term_gemas_ano);
        termGemasAno.setProgressBar(findViewById(R.id.progress_bar_term_gemas_ano));
        APIlib.getInstance().setActiveAnyChartView(termGemasAno);

        termGemasAno.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermGemasAno = AnyChart.circular();
        gaugeTermGemasAno.padding("10%");
        gaugeTermGemasAno.startAngle(270);
        gaugeTermGemasAno.sweepAngle(180);
        gaugeTermGemasAno.fill("white");

        gaugeTermGemasAno.title("ANO");

        double gemasAno = calcNotaGemas(grafQualPlantioTO.getValorGemasAno());
        gaugeTermGemasAno.data(new SingleValueDataSet(new Double[]{gemasAno}));

        gaugeTermGemasAno.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermGemasAno.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermGemasAno.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermGemasAno.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermGemasAno.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermGemasAno.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermGemasAno.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermGemasAno.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermGemasAno.cap()
                .radius("3%");

        gaugeTermGemasAno.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermGemasAno.label(1)
                .fontSize("20px")
                .text("" + gemasAno + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termGemasAno.setChart(gaugeTermGemasAno);

        final AnyChartView termAltCobrDia = findViewById(R.id.term_alt_cobr_dia);
        termAltCobrDia.setProgressBar(findViewById(R.id.progress_bar_term_alt_cobr_dia));
        APIlib.getInstance().setActiveAnyChartView(termAltCobrDia);

        termAltCobrDia.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermAltCobrDia = AnyChart.circular();
        gaugeTermAltCobrDia.padding("10%");
        gaugeTermAltCobrDia.startAngle(270);
        gaugeTermAltCobrDia.sweepAngle(180);
        gaugeTermAltCobrDia.fill("white");

        gaugeTermAltCobrDia.title("DIA");

        double altCobrDia = calcNotaAltCobr(grafQualPlantioTO.getValorAltCobrDia());
        gaugeTermAltCobrDia.data(new SingleValueDataSet( new Double[]{altCobrDia}));

        gaugeTermAltCobrDia.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermAltCobrDia.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermAltCobrDia.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermAltCobrDia.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermAltCobrDia.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermAltCobrDia.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermAltCobrDia.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermAltCobrDia.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermAltCobrDia.cap()
                .radius("3%");

        gaugeTermAltCobrDia.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermAltCobrDia.label(1)
                .fontSize("20px")
                .text("" + altCobrDia + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termAltCobrDia.setChart(gaugeTermAltCobrDia);

        final AnyChartView termAltCobrMes = findViewById(R.id.term_alt_cobr_mes);
        termAltCobrMes.setProgressBar(findViewById(R.id.progress_bar_term_alt_cobr_mes));
        APIlib.getInstance().setActiveAnyChartView(termAltCobrMes);

        termAltCobrMes.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermAltCobrMes = AnyChart.circular();
        gaugeTermAltCobrMes.padding("10%");
        gaugeTermAltCobrMes.startAngle(270);
        gaugeTermAltCobrMes.sweepAngle(180);
        gaugeTermAltCobrMes.fill("white");

        gaugeTermAltCobrMes.title("MÊS");

        double altCobrMes = calcNotaAltCobr(grafQualPlantioTO.getValorAltCobrMes());
        gaugeTermAltCobrMes.data(new SingleValueDataSet(new Double[]{altCobrMes}));

        gaugeTermAltCobrMes.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermAltCobrMes.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermAltCobrMes.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermAltCobrMes.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermAltCobrMes.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermAltCobrMes.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermAltCobrMes.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermAltCobrMes.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermAltCobrMes.cap()
                .radius("3%");

        gaugeTermAltCobrMes.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermAltCobrMes.label(1)
                .fontSize("20px")
                .text("" + altCobrMes + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termAltCobrMes.setChart(gaugeTermAltCobrMes);

        final AnyChartView termAltCobrAno = findViewById(R.id.term_alt_cobr_ano);
        termAltCobrAno.setProgressBar(findViewById(R.id.progress_bar_term_alt_cobr_ano));
        APIlib.getInstance().setActiveAnyChartView(termAltCobrAno);

        termAltCobrAno.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermAltCobrAno = AnyChart.circular();
        gaugeTermAltCobrAno.padding("10%");
        gaugeTermAltCobrAno.startAngle(270);
        gaugeTermAltCobrAno.sweepAngle(180);
        gaugeTermAltCobrAno.fill("white");

        gaugeTermAltCobrAno.title("ANO");

        double altCobrAno = calcNotaAltCobr(grafQualPlantioTO.getValorAltCobrAno());
        gaugeTermAltCobrAno.data(new SingleValueDataSet(new Double[]{altCobrAno}));

        gaugeTermAltCobrAno.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermAltCobrAno.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermAltCobrAno.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermAltCobrAno.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermAltCobrAno.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermAltCobrAno.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermAltCobrAno.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermAltCobrAno.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermAltCobrAno.cap()
                .radius("3%");

        gaugeTermAltCobrAno.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermAltCobrAno.label(1)
                .fontSize("20px")
                .text("" + altCobrAno + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termAltCobrAno.setChart(gaugeTermAltCobrAno);

        final AnyChartView termProfSulcDia = findViewById(R.id.term_prof_sulc_dia);
        termProfSulcDia.setProgressBar(findViewById(R.id.progress_bar_term_prof_sulc_dia));
        APIlib.getInstance().setActiveAnyChartView(termProfSulcDia);

        termProfSulcDia.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermProfSulcDia = AnyChart.circular();
        gaugeTermProfSulcDia.padding("10%");
        gaugeTermProfSulcDia.startAngle(270);
        gaugeTermProfSulcDia.sweepAngle(180);
        gaugeTermProfSulcDia.fill("white");

        gaugeTermProfSulcDia.title("DIA");

        double profSulcDia = calcNotaProfSulc(grafQualPlantioTO.getValorProfSulcDia());
        gaugeTermProfSulcDia.data(new SingleValueDataSet( new Double[]{profSulcDia}));

        gaugeTermProfSulcDia.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermProfSulcDia.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermProfSulcDia.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermProfSulcDia.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermProfSulcDia.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermProfSulcDia.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermProfSulcDia.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermProfSulcDia.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermProfSulcDia.cap()
                .radius("3%");

        gaugeTermProfSulcDia.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermProfSulcDia.label(1)
                .fontSize("20px")
                .text("" + profSulcDia + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termProfSulcDia.setChart(gaugeTermProfSulcDia);

        final AnyChartView termProfSulcMes = findViewById(R.id.term_prof_sulc_mes);
        termProfSulcMes.setProgressBar(findViewById(R.id.progress_bar_term_prof_sulc_mes));
        APIlib.getInstance().setActiveAnyChartView(termProfSulcMes);

        termProfSulcMes.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermProfSulcMes = AnyChart.circular();
        gaugeTermProfSulcMes.padding("10%");
        gaugeTermProfSulcMes.startAngle(270);
        gaugeTermProfSulcMes.sweepAngle(180);
        gaugeTermProfSulcMes.fill("white");

        gaugeTermProfSulcMes.title("MES");

        double profSulcMes = calcNotaProfSulc(grafQualPlantioTO.getValorProfSulcMes());
        gaugeTermProfSulcMes.data(new SingleValueDataSet(new Double[]{profSulcMes}));

        gaugeTermProfSulcMes.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermProfSulcMes.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermProfSulcMes.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermProfSulcMes.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermProfSulcMes.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermProfSulcMes.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermProfSulcMes.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermProfSulcMes.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermProfSulcMes.cap()
                .radius("3%");

        gaugeTermProfSulcMes.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermProfSulcMes.label(1)
                .fontSize("20px")
                .text("" + profSulcMes + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termProfSulcMes.setChart(gaugeTermProfSulcMes);

        final AnyChartView termProfSulcAno = findViewById(R.id.term_prof_sulc_ano);
        termProfSulcAno.setProgressBar(findViewById(R.id.progress_bar_term_prof_sulc_ano));
        APIlib.getInstance().setActiveAnyChartView(termProfSulcAno);

        termProfSulcAno.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermProfSulcAno = AnyChart.circular();
        gaugeTermProfSulcAno.padding("10%");
        gaugeTermProfSulcAno.startAngle(270);
        gaugeTermProfSulcAno.sweepAngle(180);
        gaugeTermProfSulcAno.fill("white");

        gaugeTermProfSulcAno.title("ANO");

        double profSulcAno = calcNotaProfSulc(grafQualPlantioTO.getValorProfSulcAno());
        gaugeTermProfSulcAno.data(new SingleValueDataSet(new Double[]{profSulcAno}));

        gaugeTermProfSulcAno.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermProfSulcAno.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermProfSulcAno.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermProfSulcAno.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermProfSulcAno.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermProfSulcAno.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermProfSulcAno.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermProfSulcAno.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermProfSulcAno.cap()
                .radius("3%");

        gaugeTermProfSulcAno.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermProfSulcAno.label(1)
                .fontSize("20px")
                .text("" + profSulcAno + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termProfSulcAno.setChart(gaugeTermProfSulcAno);

        final AnyChartView termFalhasDeposDia = findViewById(R.id.term_falhas_depos_dia);
        termFalhasDeposDia.setProgressBar(findViewById(R.id.progress_bar_term_falhas_depos_dia));
        APIlib.getInstance().setActiveAnyChartView(termFalhasDeposDia);

        termFalhasDeposDia.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermFalhasDeposDia = AnyChart.circular();
        gaugeTermFalhasDeposDia.padding("10%");
        gaugeTermFalhasDeposDia.startAngle(270);
        gaugeTermFalhasDeposDia.sweepAngle(180);
        gaugeTermFalhasDeposDia.fill("white");

        gaugeTermFalhasDeposDia.title("DIA");

        double falhasDeposDia = calcNotaFalhas(grafQualPlantioTO.getValorFalhasDia());
        gaugeTermFalhasDeposDia.data(new SingleValueDataSet( new Double[]{falhasDeposDia}));

        gaugeTermFalhasDeposDia.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermFalhasDeposDia.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermFalhasDeposDia.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermFalhasDeposDia.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermFalhasDeposDia.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermFalhasDeposDia.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermFalhasDeposDia.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermFalhasDeposDia.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermFalhasDeposDia.cap()
                .radius("3%");

        gaugeTermFalhasDeposDia.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermFalhasDeposDia.label(1)
                .fontSize("20px")
                .text("" + falhasDeposDia + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termFalhasDeposDia.setChart(gaugeTermFalhasDeposDia);

        final AnyChartView termFalhasDeposMes = findViewById(R.id.term_falhas_depos_mes);
        termFalhasDeposMes.setProgressBar(findViewById(R.id.progress_bar_term_falhas_depos_mes));
        APIlib.getInstance().setActiveAnyChartView(termFalhasDeposMes);

        termFalhasDeposMes.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermFalhasDeposMes = AnyChart.circular();
        gaugeTermFalhasDeposMes.padding("10%");
        gaugeTermFalhasDeposMes.startAngle(270);
        gaugeTermFalhasDeposMes.sweepAngle(180);
        gaugeTermFalhasDeposMes.fill("white");

        gaugeTermFalhasDeposMes.title("MÊS");

        double falhasDeposMes = calcNotaFalhas(grafQualPlantioTO.getValorFalhasMes());
        gaugeTermFalhasDeposMes.data(new SingleValueDataSet(new Double[]{falhasDeposMes}));

        gaugeTermFalhasDeposMes.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermFalhasDeposMes.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermFalhasDeposMes.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermFalhasDeposMes.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermFalhasDeposMes.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermFalhasDeposMes.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermFalhasDeposMes.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermFalhasDeposMes.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermFalhasDeposMes.cap()
                .radius("3%");

        gaugeTermFalhasDeposMes.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermFalhasDeposMes.label(1)
                .fontSize("20px")
                .text("" + falhasDeposMes + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termFalhasDeposMes.setChart(gaugeTermFalhasDeposMes);

        final AnyChartView termFalhasDeposAno = findViewById(R.id.term_falhas_depos_ano);
        termFalhasDeposAno.setProgressBar(findViewById(R.id.progress_bar_term_falhas_depos_ano));
        APIlib.getInstance().setActiveAnyChartView(termFalhasDeposAno);

        termFalhasDeposAno.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermFalhasDeposAno = AnyChart.circular();
        gaugeTermFalhasDeposAno.padding("10%");
        gaugeTermFalhasDeposAno.startAngle(270);
        gaugeTermFalhasDeposAno.sweepAngle(180);
        gaugeTermFalhasDeposAno.fill("white");

        gaugeTermFalhasDeposAno.title("ANO");

        double falhasDeposAno = calcNotaFalhas(grafQualPlantioTO.getValorFalhasAno());
        gaugeTermFalhasDeposAno.data(new SingleValueDataSet(new Double[]{falhasDeposAno}));

        gaugeTermFalhasDeposAno.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermFalhasDeposAno.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermFalhasDeposAno.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermFalhasDeposAno.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermFalhasDeposAno.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermFalhasDeposAno.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermFalhasDeposAno.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermFalhasDeposAno.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermFalhasDeposAno.cap()
                .radius("3%");

        gaugeTermFalhasDeposAno.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermFalhasDeposAno.label(1)
                .fontSize("20px")
                .text("" + falhasDeposAno + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termFalhasDeposAno.setChart(gaugeTermFalhasDeposAno);

        final AnyChartView termQualidadeDia = findViewById(R.id.term_qualidade_dia);
        termQualidadeDia.setProgressBar(findViewById(R.id.progress_bar_term_qualidade_dia));
        APIlib.getInstance().setActiveAnyChartView(termQualidadeDia);

        termQualidadeDia.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermQualidadeDia = AnyChart.circular();
        gaugeTermQualidadeDia.padding("10%");
        gaugeTermQualidadeDia.startAngle(270);
        gaugeTermQualidadeDia.sweepAngle(180);
        gaugeTermQualidadeDia.fill("white");

        gaugeTermQualidadeDia.title("DIA");

        double qualidadeDia = calcQualidade(calibAduboDia, calibInsetDia, gemasDia, altCobrDia, profSulcDia, falhasDeposDia);

        gaugeTermQualidadeDia.data(new SingleValueDataSet( new Double[]{qualidadeDia}));

        gaugeTermQualidadeDia.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermQualidadeDia.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermQualidadeDia.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermQualidadeDia.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermQualidadeDia.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermQualidadeDia.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermQualidadeDia.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermQualidadeDia.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermQualidadeDia.cap()
                .radius("3%");

        gaugeTermQualidadeDia.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermQualidadeDia.label(1)
                .fontSize("20px")
                .text("" + qualidadeDia + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termQualidadeDia.setChart(gaugeTermQualidadeDia);

        final AnyChartView termQualidadeMes = findViewById(R.id.term_qualidade_mes);
        termQualidadeMes.setProgressBar(findViewById(R.id.progress_bar_term_qualidade_mes));
        APIlib.getInstance().setActiveAnyChartView(termQualidadeMes);

        termQualidadeMes.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermQualidadeMes = AnyChart.circular();
        gaugeTermQualidadeMes.padding("10%");
        gaugeTermQualidadeMes.startAngle(270);
        gaugeTermQualidadeMes.sweepAngle(180);
        gaugeTermQualidadeMes.fill("white");

        gaugeTermQualidadeMes.title("MÊS");

        double qualidadeMes = calcQualidade(calibAduboMes, calibInsetMes, gemasMes, altCobrMes, profSulcMes, falhasDeposMes);
        gaugeTermQualidadeMes.data(new SingleValueDataSet(new Double[]{qualidadeMes}));

        gaugeTermQualidadeMes.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermQualidadeMes.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermQualidadeMes.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermQualidadeMes.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermQualidadeMes.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermQualidadeMes.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermQualidadeMes.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermQualidadeMes.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermQualidadeMes.cap()
                .radius("3%");

        gaugeTermQualidadeMes.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermQualidadeMes.label(1)
                .fontSize("20px")
                .text("" + qualidadeMes + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termQualidadeMes.setChart(gaugeTermQualidadeMes);

        final AnyChartView termQualidadeAno = findViewById(R.id.term_qualidade_ano);
        termQualidadeAno.setProgressBar(findViewById(R.id.progress_bar_term_qualidade_ano));
        APIlib.getInstance().setActiveAnyChartView(termQualidadeAno);

        termQualidadeAno.setAnimationCacheEnabled(false);

        final CircularGauge gaugeTermQualidadeAno = AnyChart.circular();
        gaugeTermQualidadeAno.padding("10%");
        gaugeTermQualidadeAno.startAngle(270);
        gaugeTermQualidadeAno.sweepAngle(180);
        gaugeTermQualidadeAno.fill("white");

        gaugeTermQualidadeAno.title("ANO");

        double qualidadeAno = calcQualidade(calibAduboAno, calibInsetAno, gemasAno, altCobrAno, profSulcAno, falhasDeposAno);
        gaugeTermQualidadeAno.data(new SingleValueDataSet(new Double[]{qualidadeAno}));

        gaugeTermQualidadeAno.axis(0)
                .radius(95)
                .width(0)
        ;

        gaugeTermQualidadeAno.axis(0)
                .scale()
                .minimum(0.5)
                .maximum(5.5);

        gaugeTermQualidadeAno.axis(0).scale()
                .ticks("{interval: 0.5}");

        gaugeTermQualidadeAno.axis(0).labels()
                .fontSize("9px")
                .fontColor("black")
        ;

        gaugeTermQualidadeAno.axis(0)
                .labels()
                .format("function(){\n" +
                        "var value = this.value;\n" +
                        "var text;\n" +
                        "if(value == 1){\n" +
                        "return value = 'PESSIMO'\n" +
                        "}\n" +
                        "else if(value == 2){\n" +
                        "return value = 'RUIM'\n" +
                        "}\n" +
                        "else if(value == 3){\n" +
                        "return value = 'REGULAR'\n" +
                        "}\n" +
                        "else if(value == 4){\n" +
                        "return value = 'BOM'\n" +
                        "}\n" +
                        "else if(value == 5){\n" +
                        "return value = 'ÓTIMO'\n" +
                        "}\n" +
                        "else{\n" +
                        "return '';\n" +
                        "}}");

        gaugeTermQualidadeAno.needle(0)
                .enabled(true)
                .startRadius("0%")
                .endRadius("95%")
                .middleRadius(0)
                .startWidth("1%")
                .endWidth("1%")
                .fill("black", 0.5)
                .stroke("none")
                .middleWidth("1%");

        gaugeTermQualidadeAno.marker(0)
                .axisIndex(1)
                .dataIndex(1)
                .size(10)
                .type("triangle-down")
                .position("outside")
                .radius(55);

        gaugeTermQualidadeAno.bar(0)
                .axisIndex(1)
                .position("inside")
                .dataIndex(1)
                .width(3)
                .radius(60)
                .zIndex(10);

        gaugeTermQualidadeAno.cap()
                .radius("3%");

        gaugeTermQualidadeAno.range(0, "{\n" +
                "from: 0.5,\n" +
                "to: 5.5,\n" +
                "fill: {keys: ['red', 'orange', 'yellow', 'green']},\n" +
                "position: 'inside',\n" +
                "radius: 100,\n" +
                "endSize: '60%',\n" +
                "startSize: '60%',\n" +
                "zIndex: 10}"
        );

        gaugeTermQualidadeAno.label(1)
                .fontSize("20px")
                .text("" + qualidadeAno + "")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .anchor(Anchor.CENTER_TOP)
                .padding(20,0,0,0)
        ;

        termQualidadeAno.setChart(gaugeTermQualidadeAno);


    }

    public int calcNotaCalibAdub(Long valor){
        if(valor <= 2){
            return 5;
        }
        else if((valor >= 3) && (valor <= 5)){
            return 4;
        }
        else if((valor >= 6) && (valor <= 8)){
            return 3;
        }
        else if((valor >= 9) && (valor <= 10)){
            return 2;
        }
        else{
            return 1;
        }
    }

    public int calcNotaCalibInset(Long valor){
        if(valor <= 2){
            return 5;
        }
        else if((valor >= 3) && (valor <= 5)){
            return 4;
        }
        else if((valor >= 6) && (valor <= 8)){
            return 3;
        }
        else if((valor >= 9) && (valor <= 10)){
            return 2;
        }
        else{
            return 1;
        }
    }

    public int calcNotaGemas(Long valor){
        if((valor >= 18) && (valor <= 20)){
            return 5;
        }
        else if((valor >= 21) && (valor <= 23)){
            return 4;
        }
        else if((valor >= 24) && (valor <= 26)){
            return 3;
        }
        else if((valor >= 26) && (valor <= 28)){
            return 2;
        }
        else if(valor >= 28){
            return 1;
        }
        else{
            return 5;
        }
    }

    public int calcNotaAltCobr(Long valor){
        if((valor >= 7) && (valor <= 10)){
            return 5;
        }
        else if((valor >= 11) && (valor <= 12)){
            return 4;
        }
        else if((valor >= 13) && (valor <= 14)){
            return 3;
        }
        else if((valor >= 14) && (valor <= 15)){
            return 2;
        }
        else if(valor >= 15){
            return 1;
        }
        else{
            return 5;
        }
    }

    public int calcNotaProfSulc(Long valor){
        if(valor >= 40){
            return 5;
        }
        else if((valor >= 35) && (valor <= 39)){
            return 4;
        }
        else if((valor >= 30) && (valor <= 34)){
            return 3;
        }
        else if((valor >= 25) && (valor <= 29)){
            return 2;
        }
        else{
            return 1;
        }
    }

    public int calcNotaFalhas(Long valor){
        if(valor <= 10){
            return 5;
        }
        else if((valor >= 11) && (valor <= 30)){
            return 4;
        }
        else if((valor >= 31) && (valor <= 50)){
            return 3;
        }
        else if((valor >= 51) && (valor <= 70)){
            return 2;
        }
        else{
            return 1;
        }
    }

    public double calcQualidade(double calibAdub, double calibInset, double gema,
                       double altCobr, double profSulc, double falhas){

        double ca = calibAdub * 15;
        double ci = calibInset * 15;
        double g = gema * 30;
        double ac = altCobr * 10;
        double ps = profSulc * 10;
        double f = falhas * 20;

        double qual = ((ca + ci + g + ac + ps + f) / 100);

        return Math.round(qual);

    }

}
