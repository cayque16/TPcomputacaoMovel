package br.ufop.cayque.mybabycayque.notificacao;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import br.ufop.cayque.mybabycayque.MainActivity;
import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.DadosBebe;

/**
 * Created by cayqu on 07/07/2018.
 */

public class NotificacaoActivity extends AppCompatActivity {

    public static NotificationCompat.Builder mBuilder;
    public static NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //configura um canal de notificação
        String id = "channel_id";
        CharSequence name = "channel_name";
        String description = "channel_description";

        int importance = NotificationManager.IMPORTANCE_LOW;

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);

            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100});
            mNotificationManager.createNotificationChannel(mChannel);
        }

        Intent it = new Intent(this, MainActivity.class);
        PendingIntent p = PendingIntent.getActivity(this, 0, it, 0);
        mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Medicamento")
                .setContentText("Hora do medicamento do(a) " + DadosBebe.getInstance().getNome())
                .setOngoing(false)
                .setContentIntent(p)
                .setChannelId(id);
        mNotificationManager.notify(0, mBuilder.build());
        finish();
    }
}
