package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.CircularGauge;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Line;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Align;
import com.anychart.enums.Anchor;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.anychart.graphics.vector.text.HAlign;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.to.tb.estaticas.GrafDispEquipPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafPlanRealPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafProdPlantioTO;
import br.com.usinasantafe.pmm.to.tb.estaticas.GrafQualPlantioTO;
import br.com.usinasantafe.pmm.to.tb.variaveis.ConfigTO;

public class GraficoPlantioActivity extends ActivityGeneric {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_plantio);

        ConfigTO configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);
        configTO.setVerVisGrafConfig(1L);
        configTO.update();

        GrafProdPlantioTO grafProdPlantioTO = new GrafProdPlantioTO();
        List grafProdPlantioList = grafProdPlantioTO.all();
        for (int j = 0; j < grafProdPlantioList.size(); j++) {

            grafProdPlantioTO = (GrafProdPlantioTO) grafProdPlantioList.get(j);

            final AnyChartView grafProdFrenteDia = findViewById(R.id.graf_prod_frente_dia);
            grafProdFrenteDia.setProgressBar(findViewById(R.id.progress_bar_prod_frente_dia));
            APIlib.getInstance().setActiveAnyChartView(grafProdFrenteDia);

            grafProdFrenteDia.setAnimationCacheEnabled(false);

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

            grafProdFrenteMes.setAnimationCacheEnabled(false);

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

            grafProdFrenteAno.setAnimationCacheEnabled(false);

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

            grafMediaPlanDia.setAnimationCacheEnabled(false);

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

            grafMediaPlanMes.setAnimationCacheEnabled(false);

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

            grafMediaPlanAno.setAnimationCacheEnabled(false);

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

        if(grafPlanRealPlantioList.size() > 0) {

            GrafPlanRealPlantioTO grafPlanRealPlantio01TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(0);

            GrafPlanRealPlantioTO grafPlanRealPlantio02TO;
            if(grafPlanRealPlantioList.size() < 2){
                grafPlanRealPlantio02TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio02TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(1);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio03TO;
            if(grafPlanRealPlantioList.size() < 3){
                grafPlanRealPlantio03TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio03TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(2);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio04TO;
            if(grafPlanRealPlantioList.size() < 4){
                grafPlanRealPlantio04TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio04TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(3);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio05TO;
            if(grafPlanRealPlantioList.size() < 5){
                grafPlanRealPlantio05TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio05TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(4);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio06TO;
            if(grafPlanRealPlantioList.size() < 6){
                grafPlanRealPlantio06TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio06TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(5);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio07TO;
            if(grafPlanRealPlantioList.size() < 7){
                grafPlanRealPlantio07TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio07TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(6);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio08TO;
            if(grafPlanRealPlantioList.size() < 8){
                grafPlanRealPlantio08TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio08TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(7);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio09TO;
            if(grafPlanRealPlantioList.size() < 9){
                grafPlanRealPlantio09TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio09TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(8);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio10TO;
            if(grafPlanRealPlantioList.size() < 10){
                grafPlanRealPlantio10TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio10TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(9);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio11TO;
            if(grafPlanRealPlantioList.size() < 11){
                grafPlanRealPlantio11TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio11TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(10);
            }

            GrafPlanRealPlantioTO grafPlanRealPlantio12TO;
            if(grafPlanRealPlantioList.size() < 12){
                grafPlanRealPlantio12TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(grafPlanRealPlantioList.size() - 1);
            }
            else{
                grafPlanRealPlantio12TO = (GrafPlanRealPlantioTO) grafPlanRealPlantioList.get(11);
            }

            textJanPlanMes.setText(String.valueOf(grafPlanRealPlantio01TO.getValorMesPlan()));
            textJanPlanAcum.setText(String.valueOf(grafPlanRealPlantio01TO.getValorAcumPlan()));
            textJanRealMes.setText(String.valueOf(grafPlanRealPlantio01TO.getValorMesReal()));
            textJanRealAcum.setText(String.valueOf(grafPlanRealPlantio01TO.getValorAcumReal()));
            textJanSaldMes.setText(String.valueOf(grafPlanRealPlantio01TO.getValorMesPlan() - grafPlanRealPlantio01TO.getValorMesReal()));
            textJanSaldAcum.setText(String.valueOf(grafPlanRealPlantio01TO.getValorAcumPlan() - grafPlanRealPlantio01TO.getValorAcumReal()));

            textFevPlanMes.setText(String.valueOf(grafPlanRealPlantio02TO.getValorMesPlan()));
            textFevPlanAcum.setText(String.valueOf(grafPlanRealPlantio02TO.getValorAcumPlan()));
            textFevRealMes.setText(String.valueOf(grafPlanRealPlantio02TO.getValorMesReal()));
            textFevRealAcum.setText(String.valueOf(grafPlanRealPlantio02TO.getValorAcumReal()));
            textFevSaldMes.setText(String.valueOf(grafPlanRealPlantio02TO.getValorMesPlan() - grafPlanRealPlantio02TO.getValorMesReal()));
            textFevSaldAcum.setText(String.valueOf(grafPlanRealPlantio02TO.getValorAcumPlan() - grafPlanRealPlantio02TO.getValorAcumReal()));

            textMarPlanMes.setText(String.valueOf(grafPlanRealPlantio03TO.getValorMesPlan()));
            textMarPlanAcum.setText(String.valueOf(grafPlanRealPlantio03TO.getValorAcumPlan()));
            textMarRealMes.setText(String.valueOf(grafPlanRealPlantio03TO.getValorMesReal()));
            textMarRealAcum.setText(String.valueOf(grafPlanRealPlantio03TO.getValorAcumReal()));
            textMarSaldMes.setText(String.valueOf(grafPlanRealPlantio03TO.getValorMesPlan() - grafPlanRealPlantio03TO.getValorMesReal()));
            textMarSaldAcum.setText(String.valueOf(grafPlanRealPlantio03TO.getValorAcumPlan() - grafPlanRealPlantio03TO.getValorAcumReal()));

            textAbrPlanMes.setText(String.valueOf(grafPlanRealPlantio04TO.getValorMesPlan()));
            textAbrPlanAcum.setText(String.valueOf(grafPlanRealPlantio04TO.getValorAcumPlan()));
            textAbrRealMes.setText(String.valueOf(grafPlanRealPlantio04TO.getValorMesReal()));
            textAbrRealAcum.setText(String.valueOf(grafPlanRealPlantio04TO.getValorAcumReal()));
            textAbrSaldMes.setText(String.valueOf(grafPlanRealPlantio04TO.getValorMesPlan() - grafPlanRealPlantio04TO.getValorMesReal()));
            textAbrSaldAcum.setText(String.valueOf(grafPlanRealPlantio04TO.getValorAcumPlan() - grafPlanRealPlantio04TO.getValorAcumReal()));

            textMaiPlanMes.setText(String.valueOf(grafPlanRealPlantio05TO.getValorMesPlan()));
            textMaiPlanAcum.setText(String.valueOf(grafPlanRealPlantio05TO.getValorAcumPlan()));
            textMaiRealMes.setText(String.valueOf(grafPlanRealPlantio05TO.getValorMesReal()));
            textMaiRealAcum.setText(String.valueOf(grafPlanRealPlantio05TO.getValorAcumReal()));
            textMaiSaldMes.setText(String.valueOf(grafPlanRealPlantio05TO.getValorMesPlan() - grafPlanRealPlantio05TO.getValorMesReal()));
            textMaiSaldAcum.setText(String.valueOf(grafPlanRealPlantio05TO.getValorAcumPlan() - grafPlanRealPlantio05TO.getValorAcumReal()));

            textJunPlanMes.setText(String.valueOf(grafPlanRealPlantio06TO.getValorMesPlan()));
            textJunPlanAcum.setText(String.valueOf(grafPlanRealPlantio06TO.getValorAcumPlan()));
            textJunRealMes.setText(String.valueOf(grafPlanRealPlantio06TO.getValorMesReal()));
            textJunRealAcum.setText(String.valueOf(grafPlanRealPlantio06TO.getValorAcumReal()));
            textJunSaldMes.setText(String.valueOf(grafPlanRealPlantio06TO.getValorMesPlan() - grafPlanRealPlantio06TO.getValorMesReal()));
            textJunSaldAcum.setText(String.valueOf(grafPlanRealPlantio06TO.getValorAcumPlan() - grafPlanRealPlantio06TO.getValorAcumReal()));

            textJulPlanMes.setText(String.valueOf(grafPlanRealPlantio07TO.getValorMesPlan()));
            textJulPlanAcum.setText(String.valueOf(grafPlanRealPlantio07TO.getValorAcumPlan()));
            textJulRealMes.setText(String.valueOf(grafPlanRealPlantio07TO.getValorMesReal()));
            textJulRealAcum.setText(String.valueOf(grafPlanRealPlantio07TO.getValorAcumReal()));
            textJulSaldMes.setText(String.valueOf(grafPlanRealPlantio07TO.getValorMesPlan() - grafPlanRealPlantio07TO.getValorMesReal()));
            textJulSaldAcum.setText(String.valueOf(grafPlanRealPlantio07TO.getValorAcumPlan() - grafPlanRealPlantio07TO.getValorAcumReal()));

            textAgoPlanMes.setText(String.valueOf(grafPlanRealPlantio08TO.getValorMesPlan()));
            textAgoPlanAcum.setText(String.valueOf(grafPlanRealPlantio08TO.getValorAcumPlan()));
            textAgoRealMes.setText(String.valueOf(grafPlanRealPlantio08TO.getValorMesReal()));
            textAgoRealAcum.setText(String.valueOf(grafPlanRealPlantio08TO.getValorAcumReal()));
            textAgoSaldMes.setText(String.valueOf(grafPlanRealPlantio08TO.getValorMesPlan() - grafPlanRealPlantio08TO.getValorMesReal()));
            textAgoSaldAcum.setText(String.valueOf(grafPlanRealPlantio08TO.getValorAcumPlan() - grafPlanRealPlantio08TO.getValorAcumReal()));

            textSetPlanMes.setText(String.valueOf(grafPlanRealPlantio09TO.getValorMesPlan()));
            textSetPlanAcum.setText(String.valueOf(grafPlanRealPlantio09TO.getValorAcumPlan()));
            textSetRealMes.setText(String.valueOf(grafPlanRealPlantio09TO.getValorMesReal()));
            textSetRealAcum.setText(String.valueOf(grafPlanRealPlantio09TO.getValorAcumReal()));
            textSetSaldMes.setText(String.valueOf(grafPlanRealPlantio09TO.getValorMesPlan() - grafPlanRealPlantio09TO.getValorMesReal()));
            textSetSaldAcum.setText(String.valueOf(grafPlanRealPlantio09TO.getValorAcumPlan() - grafPlanRealPlantio09TO.getValorAcumReal()));

            textOutPlanMes.setText(String.valueOf(grafPlanRealPlantio10TO.getValorMesPlan()));
            textOutPlanAcum.setText(String.valueOf(grafPlanRealPlantio10TO.getValorAcumPlan()));
            textOutRealMes.setText(String.valueOf(grafPlanRealPlantio10TO.getValorMesReal()));
            textOutRealAcum.setText(String.valueOf(grafPlanRealPlantio10TO.getValorAcumReal()));
            textOutSaldMes.setText(String.valueOf(grafPlanRealPlantio10TO.getValorMesPlan() - grafPlanRealPlantio10TO.getValorMesReal()));
            textOutSaldAcum.setText(String.valueOf(grafPlanRealPlantio10TO.getValorAcumPlan() - grafPlanRealPlantio10TO.getValorAcumReal()));

            textNovPlanMes.setText(String.valueOf(grafPlanRealPlantio11TO.getValorMesPlan()));
            textNovPlanAcum.setText(String.valueOf(grafPlanRealPlantio11TO.getValorAcumPlan()));
            textNovRealMes.setText(String.valueOf(grafPlanRealPlantio11TO.getValorMesReal()));
            textNovRealAcum.setText(String.valueOf(grafPlanRealPlantio11TO.getValorAcumReal()));
            textNovSaldMes.setText(String.valueOf(grafPlanRealPlantio11TO.getValorMesPlan() - grafPlanRealPlantio11TO.getValorMesReal()));
            textNovSaldAcum.setText(String.valueOf(grafPlanRealPlantio11TO.getValorAcumPlan() - grafPlanRealPlantio11TO.getValorAcumReal()));

            textDezPlanMes.setText(String.valueOf(grafPlanRealPlantio12TO.getValorMesPlan()));
            textDezPlanAcum.setText(String.valueOf(grafPlanRealPlantio12TO.getValorAcumPlan()));
            textDezRealMes.setText(String.valueOf(grafPlanRealPlantio12TO.getValorMesReal()));
            textDezRealAcum.setText(String.valueOf(grafPlanRealPlantio12TO.getValorAcumReal()));
            textDezSaldMes.setText(String.valueOf(grafPlanRealPlantio12TO.getValorMesPlan() - grafPlanRealPlantio12TO.getValorMesReal()));
            textDezSaldAcum.setText(String.valueOf(grafPlanRealPlantio12TO.getValorAcumPlan() - grafPlanRealPlantio12TO.getValorAcumReal()));

            final AnyChartView linePlanReal = findViewById(R.id.linha_plan_real);
            linePlanReal.setProgressBar(findViewById(R.id.progress_bar_linha_plan_real));
            APIlib.getInstance().setActiveAnyChartView(linePlanReal);

            linePlanReal.setAnimationCacheEnabled(false);

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
            seriesLinePlanReal.add(new CustomDataEntry("JAN", grafPlanRealPlantio01TO.getValorAcumPlan(), grafPlanRealPlantio01TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("FEV", grafPlanRealPlantio02TO.getValorAcumPlan(), grafPlanRealPlantio02TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("MAR", grafPlanRealPlantio03TO.getValorAcumPlan(), grafPlanRealPlantio03TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("ABR", grafPlanRealPlantio04TO.getValorAcumPlan(), grafPlanRealPlantio04TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("MAI", grafPlanRealPlantio05TO.getValorAcumPlan(), grafPlanRealPlantio05TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("JUN", grafPlanRealPlantio06TO.getValorAcumPlan(), grafPlanRealPlantio06TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("JUL", grafPlanRealPlantio07TO.getValorAcumPlan(), grafPlanRealPlantio07TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("AGO", grafPlanRealPlantio08TO.getValorAcumPlan(), grafPlanRealPlantio08TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("SET", grafPlanRealPlantio09TO.getValorAcumPlan(), grafPlanRealPlantio09TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("OUT", grafPlanRealPlantio10TO.getValorAcumPlan(), grafPlanRealPlantio10TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("NOV", grafPlanRealPlantio11TO.getValorAcumPlan(), grafPlanRealPlantio11TO.getValorAcumReal()));
            seriesLinePlanReal.add(new CustomDataEntry("DEZ", grafPlanRealPlantio12TO.getValorAcumPlan(), grafPlanRealPlantio12TO.getValorAcumReal()));

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

        }

        grafPlanRealPlantioList.clear();

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

        dataTratorPlantOperDia.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantOperDia.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorPlantOperMes.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantOperMes.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorPlantOperAno.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantOperAno.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorPlantCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorPlantCampoMes.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantCampoMes.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorPlantCampoAno.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorPlantCampoAno.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataCaminhaoOperDia.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoOperDia.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataCaminhaoOperMes.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoOperMes.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataCaminhaoOperAno.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoOperAno.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataCaminhaoCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataCaminhaoCampoMes.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoCampoMes.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataCaminhaoCampoAno.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataCaminhaoCampoAno.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataColhedoraOperDia.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraOperDia.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataColhedoraOperMes.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraOperMes.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataColhedoraOperAno.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraOperAno.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataColhedoraCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataColhedoraCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataColhedoraCampoAno.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataColhedoraCampoAno.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorTransbOperDia.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbOperDia.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorTransbOperMes.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbOperMes.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorTransbOperAno.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbOperAno.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorTransbCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbCampoDia.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorTransbCampoMes.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbCampoMes.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

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

        dataTratorTransbCampoAno.add(new GraficoPlantioActivity.PersonDataEntry("DISP", disp, "blue"));
        dataTratorTransbCampoAno.add(new GraficoPlantioActivity.PersonDataEntry("PAR", par, "red"));

        pieTratorTransbCampoAno.data(dataTratorTransbCampoAno);

        pieTratorTransbCampoAno.labels().position("outside");

        pieTratorTransbCampoAno.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        grafTratorTransbCampoAno.setChart(pieTratorTransbCampoAno);


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

        Button buttonSairGrafico = (Button) findViewById(R.id.buttonSairGrafico);
        buttonSairGrafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(GraficoPlantioActivity.this, MenuPrincNormalActivity.class);
                startActivity(it);
                finish();

            }
        });

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


    private class PersonDataEntry extends ValueDataEntry {
        public PersonDataEntry(String x, Number value, String fill) {
            super(x, value);
            setValue("fill", fill);
        }
    }

    private class CustomDataEntry extends ValueDataEntry {
        public CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }



}
