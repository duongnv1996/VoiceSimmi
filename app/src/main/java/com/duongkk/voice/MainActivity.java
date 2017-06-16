package com.duongkk.voice;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.duongkk.voice.apis.ApiResponse;
import com.duongkk.voice.apis.ApiUtils;
import com.duongkk.voice.apis.SimApi;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    @BindView(R.id.btn_record)
    Button btnRecord;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.tv)
    TextView tv;
    SimApi api;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.tv_ans)
    TextView tvAns;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        api = ApiUtils.getRootApi().create(SimApi.class);
        mTTS = new TextToSpeech(this, this);
    }

    @OnClick({R.id.btn_record, R.id.btn_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_record:
                promptSpeechInput();
                break;

        }
    }

    /**
     * Showing google speech input dialog
     */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, 1000);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1000: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tv.setText(result.get(0));
                    getAnswer(result.get(0));
                }
                break;
            }
        }
    }

    private void getAnswer(String s) {
        progress.setVisibility(View.VISIBLE);
        Call<ApiResponse> call = api.getAnswer("5e73e116-50b9-461a-9ed7-a5b125090635", "vn", 1, s);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                String txt = response.body().getData();
                speakOut(txt);
                tvAns.setText(txt);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progress.setVisibility(View.GONE);

                LogUtils.e(t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

    private void speakOut(String text) {
        progress.setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = mTTS.setLanguage((new Locale("vi")));
//            int result = mTTS.setLanguage(Locale.US);

            // tts.setPitch(5); // set pitch level

            // tts.setSpeechRate(2); // set speech speed rate

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            } else {
                Log.e("TTS", "Successfully to init TTS");
                LogUtils.e("Kết nối thành công");
            }

        } else {
            Log.e("TTS", "Initilization Failed");
        }
    }

}
