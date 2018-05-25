package br.ufop.cayque.mybabycayque;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.ufop.cayque.mybabycayque.add.AddFraldaActivity;
import br.ufop.cayque.mybabycayque.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FraldaSujaFragment extends Fragment {

    private FloatingActionButton fab;

    public FraldaSujaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fralda_suja, container, false);

        fab = view.findViewById(R.id.fabFralda);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),AddFraldaActivity.class);
                startActivity(it);
            }
        });

        return view;
    }

}
