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

import br.ufop.cayque.mybabycayque.adapters.FraldasAdapter;
import br.ufop.cayque.mybabycayque.add.AddFraldaActivity;
import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.edit.EditFraldasActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FraldaSujaFragment extends Fragment {

    private FloatingActionButton fab;
    private ListView listView;

    public FraldaSujaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fralda_suja, container, false);

        HistoricoSingleton.getInstance().loadFraldas(getContext());

        fab = view.findViewById(R.id.fabFralda);
        listView = view.findViewById(R.id.listaFraldas);

        listView.setAdapter(new FraldasAdapter(HistoricoSingleton.getInstance().getFraldas(), getContext()));
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getContext(), EditFraldasActivity.class);
                it.putExtra("position",i);
                startActivity(it);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),AddFraldaActivity.class);
                startActivity(it);
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        //para atualizar o ListView quando voltar da tela de Add
        listView.setAdapter(new FraldasAdapter(HistoricoSingleton.getInstance().getFraldas(), getContext()));
    }
}
