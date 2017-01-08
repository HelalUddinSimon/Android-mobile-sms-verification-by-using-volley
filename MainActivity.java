package com.example.solshare.test;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button btnReq, btnVer;
    public EditText editText;
    TextView txt;
    String text = "hello user :";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnReq = (Button) findViewById(R.id.button);
        btnVer = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);
        editText.setText("hello");

        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random rnd = new Random();
                int n = 100000 + rnd.nextInt(900000);
                String n2 = String.valueOf(n);
                editText.setText(n2);


                String url = "https://rest.nexmo.com/sms/json?api_key=9871c226&api_secret=136fc14c630d091f&from=SolShare&to=8801671032981&text=Your+verification+code+is+:+";
                //String endPoint="/sms/json?api_key=9871c226&api_secret=136fc14c630d091f&from=NEXMO&to=8801671032981&text=Your Verification Code:"+n2;
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        url,null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonObject = response.getJSONArray("messages");
                                    JSONObject jsonObject1 = jsonObject.getJSONObject(0);
                                    String price = jsonObject1.getString("message-price");
                                    Log.d("shemul", price.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("shemul", error.toString());
                    }


                });

// Adding request to request queue
                RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
                queue.add(jsonObjReq);

            }
        });
    }
}
