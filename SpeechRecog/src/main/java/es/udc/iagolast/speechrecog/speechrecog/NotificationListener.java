package es.udc.iagolast.speechrecog.speechrecog;

import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;
import android.service.notification.StatusBarNotification;
import android.service.notification.NotificationListenerService;

public class NotificationListener extends NotificationListenerService
        implements TextToSpeech.OnInitListener {

    TextToSpeech textToSpeech;
    static boolean reading = false;

    @Override
    public void onCreate(){
        textToSpeech = new TextToSpeech(this, this);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        if (reading){
            String text = sbn.getNotification().tickerText.toString();
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){}

    @Override
    public void onInit(int i) {}

    public static void setReading(boolean reading){
        NotificationListener.reading = reading;
    }

    public static boolean getReading(){
        return reading;
    }
}
