package com.green.go.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.green.go.gogreen.R;
import com.green.go.models.Denuncia;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActivityDenunciarFragment extends Fragment {

    private static final String ARG_DENUNCIA = "DENUNCIA";

    public ActivityDenunciarFragment() {
    }

    public static ActivityDenunciarFragment newInstance(Denuncia denuncia) {
        ActivityDenunciarFragment fragment = new ActivityDenunciarFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DENUNCIA, denuncia);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_denunciar, container, false);
    }
}
