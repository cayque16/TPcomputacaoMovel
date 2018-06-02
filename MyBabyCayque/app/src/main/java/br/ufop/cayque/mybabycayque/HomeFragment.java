package br.ufop.cayque.mybabycayque;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;

import br.ufop.cayque.mybabycayque.adapters.AtividadesAdapter;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ListView listView;
    private Dialog dialog;
    private Date data1, data2;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        GeraIdSingleton.loadGeraId(getContext());

        data1 = new Date(1994, 04, 15);
        data2 = new Date(2018, 06, 02);

        HistoricoSingleton.getInstance().loadAtividades(getContext());
        dialog = new Dialog(getContext());

        listView = view.findViewById(R.id.listaAtividades);

        listView.setAdapter(new AtividadesAdapter(HistoricoSingleton.getInstance().getAtividades(), getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(dialog != null){
//                    dialog.setContentView(R.layout.dialog);
//                    dialog.show();
//                }
                    
            }
        });

        return view;
    }

    public void dialogOk(View view) {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //para atualizar o ListView quando voltar da tela de Add
        listView.setAdapter(new AtividadesAdapter(HistoricoSingleton.getInstance().getAtividades(), getContext()));
    }
}