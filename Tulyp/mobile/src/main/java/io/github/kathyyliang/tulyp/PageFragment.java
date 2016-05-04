package io.github.kathyyliang.tulyp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    private MyFirebase myFirebase = TulypApplication.mFirebase;
    private float[] todaysYData;

    public static final String ARG_PAGE = "ARG_PAGE";
    XAxis xAxis;
    YAxis yAxis;
    LineChart chart;
    TextView label;

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
        chart = (LineChart) view.findViewById(R.id.chart);
        label = (TextView) view.findViewById(R.id.xaxislabel);
        xAxis = chart.getXAxis();
        yAxis = chart.getAxisLeft();
        chart.getAxisRight().setEnabled(false);
        if (mPage == 1) {
//            ArrayList<String> patients = TulypApplication.mUser.getPatientIDs();

            pullSensorData(myFirebase.getUID()); //getting today's data.
//            LineData data = getData(24, 600);
//            chart.setData(data);
//            xAxis.setLabelsToSkip(1);
//            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//            xAxis.setDrawGridLines(false);
//            yAxis.setDrawGridLines(false);
//            chart.setDescription("");
//            chart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
//            label.setText("Time (Hours)");
            // day

        } else if (mPage == 2) {
            LineData data = getData(7, 2);
            chart.setData(data);
            xAxis.setLabelsToSkip(0);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            yAxis.setDrawGridLines(false);
            chart.setDescription("");
            chart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
            label.setText("Time (Days)");
            // week

        } else if (mPage == 3) {
            LineData data = getData(30, 3);
            chart.setData(data);
            xAxis.setLabelsToSkip(2);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            yAxis.setDrawGridLines(false);
            chart.setDescription("");
            chart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
            label.setText("Time (Days)");
            // month

        } else if (mPage == 4) {
            LineData data = getData(12, 4);
            chart.setData(data);
            xAxis.setLabelsToSkip(0);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            yAxis.setDrawGridLines(false);
            chart.setDescription("");
            chart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
            label.setText("Months");
            //LimitLine ll = new LimitLine(5, "Sinemet");
            //ll.setTextSize(10f);
            //xAxis.addLimitLine(ll);

            // year

        }
        return view;
    }


    private LineData getData(int count, int mPage) {

        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        float[] arr = {200, 300, 250, 233, 400, 500, 450, 460, 380, 410, 350, 320,
                200, 300, 250, 233, 400, 500, 450, 460, 380, 410, 350, 320,
                200, 300, 250, 233, 400, 500, 450, 460, 380, 410, 350, 320};
        if (mPage == 1) {
            arr = todaysYData;
        }
        for (int i = 0; i < count; i++) {
            xVals.add(Integer.toString(i));
            yVals.add(new Entry(arr[i], i));
        }
        if (mPage == 1) {
            for (int i = count; i < 24; i++) {
                xVals.add(Integer.toString(i));
            }
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

    /**
     * Fills in todaysYData with the averaged data points for today's tremor sensor data.
     * todaysYData should be size 24 with index corresponding to hour of the day.
     * @param uid of the user whose data we fetch
     */
    private void pullSensorData(String uid) {
        todaysYData = new float[24];
        Firebase mfirebase = myFirebase.getFirebaseRef();
        Firebase userRef = mfirebase.child("SensorData").child(uid);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                HashMap<String, Object> data = (HashMap<String, Object>) snapshot.getValue();
                if (data == null) {
                    Log.d("Firebase", "No Sensor data for this user");
                    todaysYData = new float[24];
                    return;
                }
                TulypApplication.setTremordata(data);
                todaysYData = MyFirebase.makeDayDataPoints(data);
                if (todaysYData == null) {
                    todaysYData = new float[24];
                }
                LineData data1 = getData(getCurrentHour() + 1, 1);
                chart.setData(data1);
                xAxis.setLabelsToSkip(1);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(false);
                yAxis.setDrawGridLines(false);
                chart.setDescription("");
                chart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
                label.setText("Time (Hours)");
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Firebase", "Failed to retrieve sensor data\n" + firebaseError);
            }
        });
    }

    private int getCurrentHour() {
        long curTime = System.currentTimeMillis();
        Calendar curDate = Calendar.getInstance();
        curDate.setTimeInMillis(curTime);
        Locale locale = Locale.getDefault();
        Integer hourint = curDate.get(Calendar.HOUR_OF_DAY) - 3;
        return hourint;
    }

}
