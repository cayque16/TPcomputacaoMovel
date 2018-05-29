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


import br.ufop.cayque.mybabycayque.adapters.MedicamentosAdapter;
import br.ufop.cayque.mybabycayque.add.AddMedicamentosActivity;
import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.edit.EditMedicamentosActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicamentosFragment extends Fragment {

    public FloatingActionButton fab;
    private ListView listView;

    public MedicamentosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicamentos, container, false);

        HistoricoSingleton.getInstance().loadMedicamentos(getContext());

        fab = view.findViewById(R.id.fabMedicamentos);
        listView = view.findViewById(R.id.listaMedicamentos);

        listView.setAdapter(new MedicamentosAdapter(HistoricoSingleton.getInstance().getMedicamentos(), getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getContext(), EditMedicamentosActivity.class);
                it.putExtra("position",i);
                startActivity(it);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), AddMedicamentosActivity.class);
                startActivity(it);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listView.setAdapter(new MedicamentosAdapter(HistoricoSingleton.getInstance().getMedicamentos(), getContext()));
    }
}
