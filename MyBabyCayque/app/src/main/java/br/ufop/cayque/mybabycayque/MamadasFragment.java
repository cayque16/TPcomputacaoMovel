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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import br.ufop.cayque.mybabycayque.adapters.MamadasAdapter;
import br.ufop.cayque.mybabycayque.add.AddMamadasActivity;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.edit.EditMamadasActivity;
import br.ufop.cayque.mybabycayque.models.Mamadas;


/**
 * A simple {@link Fragment} subclass.
 */
public class MamadasFragment extends Fragment {

    private FloatingActionButton fab;
    private ListView listView;
    private TextView textVazia;

    public MamadasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mamadas, container, false);

        HistoricoSingleton.getInstance().loadMamadas(getContext());

        fab = view.findViewById(R.id.fabMamadas);
        listView = view.findViewById(R.id.listaMamadas);
        textVazia = view.findViewById(R.id.textMamadasVazia);

        Collections.sort(HistoricoSingleton.getInstance().getMamadas());

        if (HistoricoSingleton.getInstance().getMamadas().isEmpty()) {
            textVazia.setVisibility(View.VISIBLE);
        } else {
            textVazia.setVisibility(View.INVISIBLE);
        }

        listView.setAdapter(new MamadasAdapter(HistoricoSingleton.getInstance().getMamadas(), getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getContext(), EditMamadasActivity.class);
                it.putExtra("position",i);
                startActivity(it);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), AddMamadasActivity.class);
                startActivity(it);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //para atualizar o ListView quando voltar da tela de Add
        Collections.sort(HistoricoSingleton.getInstance().getMamadas());
        listView.setAdapter(new MamadasAdapter(HistoricoSingleton.getInstance().getMamadas(), getContext()));
        if (HistoricoSingleton.getInstance().getMamadas().isEmpty()) {
            textVazia.setVisibility(View.VISIBLE);
        } else {
            textVazia.setVisibility(View.INVISIBLE);
        }
    }

}
