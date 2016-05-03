package io.github.kathyyliang.tulyp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        LineChart chart = (LineChart) view.findViewById(R.id.chart);
        XAxis xAxis = chart.getXAxis();
        YAxis yAxis = chart.getAxisLeft();
        chart.getAxisRight().setEnabled(false);
        if (mPage == 1) {

            // day

        } else if (mPage == 2) {

            // week

        } else if (mPage == 3) {

            // month

        } else if (mPage == 4) {
            LineData data = getData(12, 600);
            chart.setData(data);
            xAxis.setLabelsToSkip(0);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            yAxis.setDrawGridLines(false);
            chart.setDescription("");
            chart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
            TextView label = (TextView) view.findViewById(R.id.xaxislabel);
            label.setText("Months");
            LimitLine ll = new LimitLine(5, "Sinemet");
            ll.setTextSize(10f);
            xAxis.addLimitLine(ll);

            // year

        }
        return view;
    }

    private LineData getData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            xVals.add(Integer.toString(i + 1));
            int[] arr = {200, 300, 250, 233, 400, 500, 450, 460, 380, 410, 350, 320};
            yVals.add(new Entry(arr[i], i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "Tremors");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        set1.setLineWidth(1.75f);
        set1.setCircleRadius(3.5f);
        int peach = Color.rgb(255,178,146);
        set1.setColor(peach);
        set1.setCircleColor(peach);

        set1.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        return data;
    }

}
