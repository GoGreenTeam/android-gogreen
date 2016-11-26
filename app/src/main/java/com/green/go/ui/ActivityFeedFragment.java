package com.green.go.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.green.go.gogreen.R;
import com.green.go.adapters.AdapterFeeds;
import com.green.go.interfaces.FeedInteractionListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FeedInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivityFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityFeedFragment extends Fragment {

    //layout
    private RecyclerView mRecycler;
    private FeedInteractionListener mListener;

    //data
    private ArrayList<String> mDataSet;

    public ActivityFeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ActivityFeedFragment.
     */
    public static ActivityFeedFragment newInstance(String param1, String param2) {
        ActivityFeedFragment fragment = new ActivityFeedFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
        }

        //dummy data
        mDataSet = new ArrayList<>();
        mDataSet.add("Exemplo 1");
        mDataSet.add("Exemplo 2");
        mDataSet.add("Exemplo 3");
        mDataSet.add("Exemplo 4");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        mRecycler = (RecyclerView) view.findViewById(R.id.feeds_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(new AdapterFeeds(mDataSet));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FeedInteractionListener) {
            mListener = (FeedInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
