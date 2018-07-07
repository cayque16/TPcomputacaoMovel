package br.ufop.cayque.mybabycayque;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SomUteroActivity extends AppCompatActivity {

    private ImageButton play,pause;
    private boolean isPause = true;
    private MediaPlayer somUtero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_som_utero);

        setTitle("Som de Útero");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        play = findViewById(R.id.buttonPlay);
        pause = findViewById(R.id.buttonPause);

        pause.setEnabled(false);

        somUtero = MediaPlayer.create(this, R.raw.som_de_utero_cortado);
    }


    public void Play(View view) {
        somUtero.start();
        play.setEnabled(false);
        pause.setEnabled(true);
    }

    public void Pause(View view) {
        somUtero.stop();
        play.setEnabled(true);
        pause.setEnabled(false);
        somUtero = MediaPlayer.create(this, R.raw.som_de_utero_cortado);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
