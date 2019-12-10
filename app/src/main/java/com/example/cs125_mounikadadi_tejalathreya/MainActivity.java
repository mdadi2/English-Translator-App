package com.example.cs125_mounikadadi_tejalathreya;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private TextView translationTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.translateButton);
        translationTextView = findViewById(R.id.translation);
        final EditText input = findViewById(R.id.textInput);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String translationString = input.getText().toString();
                Http.post(translationString, "en", new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            JSONObject serverResp = new JSONObject(response.toString());
                            JSONObject jsonObject = serverResp.getJSONObject("data");
                            JSONArray transObject = jsonObject.getJSONArray(
                                    "translations");
                            JSONObject transObject2 =  transObject.getJSONObject(0);
                            translationTextView.setText(transObject2.getString("translatedText"));
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}