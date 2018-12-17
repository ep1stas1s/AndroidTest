package com.yjp.fragmenttest;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends Fragment implements View.OnClickListener {

    private OnColorButtonListener onColorButtonListener;

    public LeftFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        // context가 Object 처럼 최상위인듯
        super.onAttach(context);
        onColorButtonListener = (OnColorButtonListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        view.findViewById(R.id.v_red).setOnClickListener(this);
        view.findViewById(R.id.v_yellow).setOnClickListener(this);
        view.findViewById(R.id.v_blue).setOnClickListener(this);
        return view;
    }


    public void onClick(View v) {

        switch (v.getId()){
            case R.id.v_red:
                onColorButtonListener.onColorClick(0);
                break;
            case R.id.v_yellow:
                onColorButtonListener.onColorClick(1);
                break;
            case R.id.v_blue:
                onColorButtonListener.onColorClick(2);
                break;

        }
    }
}
