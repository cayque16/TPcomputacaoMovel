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

import br.ufop.cayque.mybabycayque.adapters.AtividadesAdapter;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ListView listView;
    private Dialog dialog;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        GeraIdSingleton.loadGeraId(getContext());

//        HistoricoSingleton.getInstance().getAtividades().remove(0);
//        HistoricoSingleton.getInstance().getAtividades().remove(1);
//        HistoricoSingleton.getInstance().getAtividades().remove(2);
//        HistoricoSingleton.getInstance().getAtividades().remove(3);


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