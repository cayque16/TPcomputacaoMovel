package br.ufop.cayque.mybabycayque.add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.ufop.cayque.mybabycayque.R;

public class AddMamadeirasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mamadeiras);

        this.setTitle("Nova mamadeira");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
