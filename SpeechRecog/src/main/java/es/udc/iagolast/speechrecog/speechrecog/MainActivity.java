package es.udc.iagolast.speechrecog.speechrecog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import es.udc.iagolast.speechrecog.speechrecog.speechListener.Listener;
import es.udc.iagolast.speechrecog.speechrecog.speechListener.StupidCallback;

public class MainActivity extends Activity implements OnInitListener {

    private TextView textView;
    private Button recordButton;
    private Button listenButton;
    private Button readNotificationsButton;
    private SpeechRecognizer speechRecognizer;
    private TextToSpeech textToSpeech;

    /**
     * Find each ui element and and assigns it to his local variable.
     */
    private void loadUI() {
        recordButton = (Button) findViewById(R.id.button);
        listenButton = (Button) findViewById(R.id.button2);
        readNotificationsButton = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.textView);
    }

    /**
     * Assigns listeners to buttons.
     */
    private void setListeners() {
        recordButton.setOnClickListener(onRecordClick);
        listenButton.setOnClickListener(onListenClick);
        readNotificationsButton.setOnClickListener(onReadClick);
    }

    /**
     *  Creates a speech recognizer with a callback, the callback will write
     *  the speech in the textView.
     */
    private void createSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        StupidCallback stupidCallback = new StupidCallback(textView);
        speechRecognizer.setRecognitionListener(new Listener(stupidCallback, this));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUI();
        setListeners();
        createSpeechRecognizer();
        textToSpeech = new TextToSpeech(this, this);
    }

    @Override
    public void onInit(int status) {

    }


    /**
     * When clicked app starts to listen.
     */
    OnClickListener onRecordClick = new OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "voice.recognition.test");
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
            speechRecognizer.startListening(intent);
        }
    };

    /**
     * When clicked app speaks.
     */
    OnClickListener onListenClick = new OnClickListener() {
        public void onClick(View v) {
            textToSpeech.speak(textView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        }
    };

    /**
     * When notification reading service is started.
     */
    OnClickListener onReadClick = new OnClickListener() {
        public void onClick(View v){
            Context c = getApplicationContext();
            Intent i = new Intent(c, NotificationListener.class);
            getApplicationContext().startService(i);
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));

        }
    };

}
