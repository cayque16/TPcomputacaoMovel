package br.ufop.cayque.mybabycayque;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SomUteroActivity extends AppCompatActivity {

    private ImageButton playPause;
    private boolean isPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_som_utero);

        setTitle("Som de Útero");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        playPause = findViewById(R.id.buttonPlayPause);
        playPause.setImageResource(R.drawable.play);
    }

    public void playPause(View view) {
        if(isPause){
            isPause = false;
            playPause.setImageResource(R.drawable.pause);
        } else {
            isPause = true;
            playPause.setImageResource(R.drawable.play);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
