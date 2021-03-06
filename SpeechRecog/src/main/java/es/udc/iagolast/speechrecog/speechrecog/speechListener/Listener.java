package es.udc.iagolast.speechrecog.speechrecog.speechListener;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;

import es.udc.iagolast.speechrecog.speechrecog.R;

/**
 * Created by iagolast on 21/02/14.
 */
public class Listener implements RecognitionListener {
    private final int NOTIFICATION_ID = 02356;
    private SpeechCallback speechCallback;
    private Context context;
    NotificationManager notificationManager;

    public Listener(SpeechCallback callback, Context context) {
        this.speechCallback = callback;
        this.context = context;
        // Gets an instance of the NotificationManager service
        notificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
    }
    
    /**
     * When starting to listen, add a notification.
     */
    public void onReadyForSpeech(Bundle params) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.abc_ic_voice_search)
                        .setContentTitle("Escuchando Ordenes ")
                        .setContentText("");
        // Builds the notification and issues it.
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }


    public void onBeginningOfSpeech() {

    }

    public void onRmsChanged(float rmsdB) {

    }

    public void onBufferReceived(byte[] buffer) {

    }

    /**
     * Dismiss notification when speech ends.
     */
    public void onEndOfSpeech() {
        notificationManager.cancel(NOTIFICATION_ID);
    }

    /**
     * Dismis notification on error.
     * @param error
     */
    public void onError(int error) {
        notificationManager.cancel(NOTIFICATION_ID);
    }

    /**
     * Gets the first String from the results and passes it to the callback.
     * @param results
     */
    public void onResults(Bundle results) {
        ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String speech = data.get(0);
        speechCallback.processSpeech(speech);
    }

    public void onPartialResults(Bundle partialResults) {

    }

    public void onEvent(int eventType, Bundle params) {

    }
}

