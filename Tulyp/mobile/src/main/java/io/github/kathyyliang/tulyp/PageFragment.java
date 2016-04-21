package io.github.kathyyliang.tulyp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


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
        ImageView graphKey = (ImageView) view.findViewById(R.id.graphkey);
        ImageView graph = (ImageView) view.findViewById(R.id.graph);
        TextView text = (TextView) view.findViewById(R.id.scale);
        if (mPage == 1) {
            graphKey.setImageResource(R.drawable.daykey);
            graph.setImageResource(R.drawable.daygraph);
            text.setText("Time (hours)");
        } else if (mPage == 4) {
            graphKey.setImageResource(R.drawable.yearkey);
            graph.setImageResource(R.drawable.yeargraph);
            text.setText("Month");
        }
        return view;
    }

}
