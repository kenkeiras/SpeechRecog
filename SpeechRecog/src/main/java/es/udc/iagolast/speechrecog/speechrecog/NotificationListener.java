package es.udc.iagolast.speechrecog.speechrecog;

import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;
import android.service.notification.StatusBarNotification;
import android.service.notification.NotificationListenerService;

public class NotificationListener extends NotificationListenerService implements
        TextToSpeech.OnInitListener {



    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        TextToSpeech textToSpeech = new TextToSpeech(this, this);
        textToSpeech.speak("New notification", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){}

    @Override
    public void onInit(int i) {}
}
