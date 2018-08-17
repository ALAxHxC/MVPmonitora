package ponny.org.monitora.presenters.chart;

import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;

public class LineChartMuestrasProvider {

    private LineChart lineChart;
    private List<Muestra> muestraList;
    public LineChartMuestrasProvider(View view,List<Muestra> muestras){
        this.lineChart=(LineChart)view;
        this.muestraList=muestras;
        axisX(this.lineChart);
    }
    public LineData loadDataSetPulso(String text,int color,int fill){
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        LineDataSet lineDataSet=new LineDataSet(listaDeDatosMuestrasPulso(),text);
        lineDataSet.setColor(color);
        lineDataSet.setFillColor(fill);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSets.add(lineDataSet);

        LineData lineData = new LineData(dataSets);

        return  lineData;
    }
    public LineData loadDataSetSpo2(String text,int color,int fill){
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        LineDataSet lineDataSet=new LineDataSet(listaDeDatosMuestrasSpo2(),text);
        lineDataSet.setColor(color);
        lineDataSet.setFillColor(fill);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSets.add(lineDataSet);
        LineData lineData = new LineData(dataSets);
        return  lineData;
    }
    public List<Entry> listaDeDatosMuestrasPulso(){
        List<Entry> entries = new ArrayList<Entry>();
        for(int i=0;i<muestraList.size();i++){
            Entry entry=new Entry(i,muestraList.get(i).getData().getOximeter().getPulse());
            entries.add(entry);
        }
        return entries;
    }

    public List<Entry> listaDeDatosMuestrasSpo2(){
        List<Entry> entries = new ArrayList<Entry>();
        for(int i=0;i<muestraList.size();i++){
            Entry entry=new Entry(i,muestraList.get(i).getData().getOximeter().getSpo2());
            entries.add(entry);
        }
        return entries;
    }
    public void  axisX(LineChart lineChart){
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGridColor(Color.BLACK);
    }
}
