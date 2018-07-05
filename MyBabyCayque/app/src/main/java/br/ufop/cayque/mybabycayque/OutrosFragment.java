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

import java.util.Collections;

import br.ufop.cayque.mybabycayque.adapters.OutrosAdapter;
import br.ufop.cayque.mybabycayque.add.AddOutrosActivity;
import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.edit.EditOutrosActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class OutrosFragment extends Fragment {

    private FloatingActionButton fab;
    private ListView listView;
    private TextView textVazia;

    public OutrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_outros, container, false);

        HistoricoSingleton.getInstance().loadOutros(getContext());

        fab = view.findViewById(R.id.fabOutros);
        listView = view.findViewById(R.id.listaOutros);
        textVazia = view.findViewById(R.id.textOutrosVazia);

        Collections.sort(HistoricoSingleton.getInstance().getOutros());

        if (HistoricoSingleton.getInstance().getOutros().isEmpty()) {
            textVazia.setVisibility(View.VISIBLE);
        } else {
            textVazia.setVisibility(View.INVISIBLE);
        }

        listView.setAdapter(new OutrosAdapter(HistoricoSingleton.getInstance().getOutros(), getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getContext(), EditOutrosActivity.class);
                it.putExtra("position", i);
                startActivity(it);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), AddOutrosActivity.class);
                startActivity(it);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //para atualizar o ListView quando voltar da tela de Add
        Collections.sort(HistoricoSingleton.getInstance().getOutros());
        listView.setAdapter(new OutrosAdapter(HistoricoSingleton.getInstance().getOutros(), getContext()));
        if (HistoricoSingleton.getInstance().getOutros().isEmpty()) {
            textVazia.setVisibility(View.VISIBLE);
        } else {
            textVazia.setVisibility(View.INVISIBLE);
        }
    }
}
