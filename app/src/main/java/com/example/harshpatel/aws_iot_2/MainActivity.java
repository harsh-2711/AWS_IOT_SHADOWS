package com.example.harshpatel.aws_iot_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {

    Button on, off;
    TextView current_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);

        current_status = (TextView) findViewById(R.id.current_status);

        API api = new API();
        api.execute();

    }

    public class API extends AsyncTask<Void, Void, Void> {

        Response response;

        public String postBody="{\n" +
                "    \"state\": {\n \"desired\": {\n \"appl1_status\": \"0\""+
                "} } }";

        //public MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        public MediaType JSON = MediaType.parse("application/x-www-form-urlencoded");

        @Override
        protected Void doInBackground(Void... voids) {

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON, postBody);

            Request request = new Request.Builder()
                    .url("https://a32bv2nuces5pr-ats.iot.us-west-2.amazonaws.com/things/esp5/shadow")
                    .post(body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Host", "a32bv2nuces5pr-ats.iot.us-west-2.amazonaws.com")
                    .addHeader("Content-Length", "69")
                    .addHeader("X-Amz-Date", "20190304T102527Z")
                    .addHeader("Authorization", "AWS4-HMAC-SHA256 Credential=AKIAJTZGUW4FW5JFPJMA/20190304/us-west-2/iotdata/aws4_request, SignedHeaders=cache-control;content-length;content-type;host;postman-token;x-amz-date, Signature=8798513537b0311703bc793a8c73b7b656f218735dead38b78c820d1f8683c30")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("Postman-Token", "716e6f3c-429f-4d8d-a39a-111004abd3cb")
                    .build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.e("Response", response.toString());
           // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
