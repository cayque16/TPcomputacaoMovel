package br.ufop.cayque.mybabycayque;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.ufop.cayque.mybabycayque.adapters.SonecasAdapter;
import br.ufop.cayque.mybabycayque.add.AddSonecaActivity;
import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.edit.EditSonecasActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SonecaFragment extends Fragment {

    private FloatingActionButton fab;
    private ListView listView;

    public SonecaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_soneca, container, false);

        HistoricoSingleton.getInstance().loadSoneca(getContext());

        fab = view.findViewById(R.id.fabSoneca);
        listView = view.findViewById(R.id.listaSonecas);

        listView.setAdapter(new SonecasAdapter(HistoricoSingleton.getInstance().getSonecas(), getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getContext(), EditSonecasActivity.class);
                it.putExtra("position",i);
                startActivity(it);
            }
        });

        fab = view.findViewById(R.id.fabSoneca);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),AddSonecaActivity.class);
                startActivity(it);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listView.setAdapter(new SonecasAdapter(HistoricoSingleton.getInstance().getSonecas(), getContext()));
    }
}
