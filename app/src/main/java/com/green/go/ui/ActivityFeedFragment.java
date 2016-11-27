package com.green.go.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.green.go.dataparser.DenunciaDataParser;
import com.green.go.enums.REQUEST_TYPE;
import com.green.go.gogreen.R;
import com.green.go.interfaces.FeedInteractionListener;
import com.green.go.models.Denuncia;
import com.green.go.server.Request;
import com.green.go.util.Util;

import org.json.JSONObject;

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
    private SwipeRefreshLayout mSwipe;


    public ActivityFeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param denuncia Parameter 1.
     * @return A new instance of fragment ActivityFeedFragment.
     */
    public static ActivityFeedFragment newInstance(Denuncia denuncia) {
        ActivityFeedFragment fragment = new ActivityFeedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        mRecycler = (RecyclerView) view.findViewById(R.id.feeds_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        mSwipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipe.setRefreshing(false);
                callGetPublicacoes();
            }
        });

        callGetPublicacoes();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FeedInteractionListener) {
            mListener = (FeedInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FeedInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void callGetPublicacoes() {
        new Request(REQUEST_TYPE.GET, getContext(), null) {
            @Override
            public void onPostExecute(JSONObject data) {
                super.onPostExecute(data);
                mSwipe.setRefreshing(false);
                if (data != null) {
                    new DenunciaDataParser(getContext(), mRecycler).execute(data);
                }
            }
        }.execute(Util.REQUEST_GET_PUBLICACOES);
    }
}
