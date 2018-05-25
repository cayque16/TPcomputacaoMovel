package br.ufop.cayque.mybabycayque;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
