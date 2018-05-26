package br.ufop.cayque.mybabycayque;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.adapters.MamadasAdapter;
import br.ufop.cayque.mybabycayque.add.AddMamadasActivity;
import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.models.Mamadas;
import br.ufop.cayque.mybabycayque.models.TiposAtivi;


/**
 * A simple {@link Fragment} subclass.
 */
public class MamadasFragment extends Fragment {

    private FloatingActionButton fab;
    private ListView listView;
    private ArrayList<Mamadas> mamadas = new ArrayList<Mamadas>();

    public MamadasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_mamadas, container, false);
        Mamadas m1 = new Mamadas(TiposAtivi.Mamadas,1,1,1,1,1,
        1,1,1,1,1,1,1,"Direito");
        Mamadas m2 = new Mamadas(TiposAtivi.Mamadas,1,1,1,1,1,
                1,1,1,1,1,1,1,"Esquerdo");
        Mamadas m3 = new Mamadas(TiposAtivi.Mamadas,1,1,1,1,1,
                1,1,1,1,1,1,1,"Ambos");

        mamadas.add(m1);
        mamadas.add(m2);
        mamadas.add(m3);

        fab = view.findViewById(R.id.fabMamadas);
        listView = view.findViewById(R.id.listaMamadas);
        listView.setAdapter(new MamadasAdapter(mamadas,getContext()));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),AddMamadasActivity.class);
                startActivity(it);
            }
        });

        return view;
    }

}
