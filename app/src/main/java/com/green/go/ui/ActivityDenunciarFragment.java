package com.green.go.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.green.go.enums.VIEW_MODE_DENUNCIA;
import com.green.go.gogreen.R;
import com.green.go.interfaces.DenunciarInterfaceListener;
import com.green.go.models.Denuncia;
import com.green.go.util.Util;

import static com.green.go.util.Util.ARG_DENUNCIA;
import static com.green.go.util.Util.ARG_MODO;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActivityDenunciarFragment extends Fragment implements View.OnClickListener {

    //data
    private Denuncia mDenuncia;
    private DenunciarInterfaceListener mListener;

    //layout
    private EditText mTitulo;
    private EditText mDescricao;
    private Spinner mSpinner;
    private FloatingActionButton mFab;
    private String mMode;

    public ActivityDenunciarFragment() {
    }

    public static ActivityDenunciarFragment newInstance(Denuncia denuncia, String modo) {
        ActivityDenunciarFragment fragment = new ActivityDenunciarFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_DENUNCIA, denuncia);
        args.putString(Util.ARG_MODO, modo);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDenuncia = getArguments().getParcelable(ARG_DENUNCIA);
        mMode = getArguments().getString(ARG_MODO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_denunciar, container, false);

        mTitulo = (EditText) view.findViewById(R.id.denunciar_title);
        mDescricao = (EditText) view.findViewById(R.id.denunciar_descricao);
        mSpinner = (Spinner) view.findViewById(R.id.denunciar_concelho_spinner);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

        if (mMode.equals(Util.ARG_MODO_CRIAR)) {
            mFab.setOnClickListener(this);
            mFab.setVisibility(View.VISIBLE);
        } else {
            mFab.setVisibility(View.GONE);
        }

        if (mDenuncia != null) {
            mTitulo.setText(mDenuncia.getmTitulo());
            mDescricao.setText(mDenuncia.getmDescricao());
        } else {
            populateSpinner();
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DenunciarInterfaceListener) {
            mListener = (DenunciarInterfaceListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement DenunciarInterfaceListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void populateSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.concelhos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mFab.getId()) {
            mListener.sendDenuncia(mTitulo.getText().toString(), mDescricao.getText().toString(), mSpinner.getSelectedItem().toString(), "111", "222");
        }
    }
}
