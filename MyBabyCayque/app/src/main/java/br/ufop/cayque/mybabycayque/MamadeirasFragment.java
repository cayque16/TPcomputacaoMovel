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

import br.ufop.cayque.mybabycayque.adapters.MamadeirasAdapter;
import br.ufop.cayque.mybabycayque.add.AddMamadeirasActivity;
import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.edit.EditMamadasActivity;
import br.ufop.cayque.mybabycayque.edit.EditMamadeirasActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MamadeirasFragment extends Fragment {

    private FloatingActionButton fab;
    private ListView listView;

    public MamadeirasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mamadeiras, container, false);

        HistoricoSingleton.getInstance().loadMamadeiras(getContext());

        fab = view.findViewById(R.id.fabMamadeiras);
        listView = view.findViewById(R.id.listaMamadeiras);

        listView.setAdapter(new MamadeirasAdapter(HistoricoSingleton.getInstance().getMamadeiras(),getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getContext(), EditMamadeirasActivity.class);
                it.putExtra("position",i);
                startActivity(it);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),AddMamadeirasActivity.class);
                startActivity(it);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //para atualizar o ListView quando voltar da tela de Add
        listView.setAdapter(new MamadeirasAdapter(HistoricoSingleton.getInstance().getMamadeiras(),getContext()));
    }
}
