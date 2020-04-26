package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    int RC_SIGN_IN=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestServerAuthCode("38299970165-l5hghb1ds0b8rgnt66vjcd1ekip07eus.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("error","Request code"+requestCode);
        Log.e("error","Result code"+resultCode);
        Log.e("error","data"+data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private String postRequest(final String idToken) {
        RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
        String url="http://35.222.249.89:8001/api/v1/google/verifyAccessToken";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //let's parse json data
                try {
                    TextView text = (TextView) findViewById(R.id.token_view);
                    text.setText(response);
                    Log.e("error","Post Data : Success");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error","Post Data : Response Failed");
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<String, String>();
                Log.e("error","Post Data :"+ idToken);
                params.put("access_token",idToken);
                return params;
            }

            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                //params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Accept","application/json");
                return params;
            }
        };

        requestQueue.add(stringRequest);
        return null;
    }


    private String get_access_token(final String auth_code){
        RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
        String url="https://oauth2.googleapis.com/token";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //let's parse json data
                try {
                    TextView text = (TextView) findViewById(R.id.token_view);
                    text.setText(response);
                    Log.e("Error","Resp"+response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "Post Data : Response Failed");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("code", auth_code);
                params.put("client_id", "38299970165-l5hghb1ds0b8rgnt66vjcd1ekip07eus.apps.googleusercontent.com");
                params.put("client_secret", "7Gxal5CEbEnuzKMh-SNVMR_F");
                params.put("redirect_uri", "http://localhost");
                params.put("grant_type", "authorization_code");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Accept", "application/json");
                return params;
            }
        };

        requestQueue.add(stringRequest);
        //Log.e("error","string req"+ stringRequest[0]);
        return null;
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String auth_code = account.getServerAuthCode();
            get_access_token(auth_code);
            final TextView text = (TextView) findViewById(R.id.token_view);


            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String access_token = text.getText().toString();
                    String[] arrOfStr = access_token.split(":", 3);
                    String arrOfStr2 = arrOfStr[1].split(",", 2)[0];
                    String arrOfStr3 = arrOfStr2.substring(2, arrOfStr2.length()-1);
                    Log.e("error","done i guess :"+ arrOfStr3);

                    postRequest(arrOfStr3);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final TextView text = (TextView) findViewById(R.id.token_view);
                            String access_token = text.getText().toString();

                            JSONObject reader = null;
                            try {
                                reader = new JSONObject(access_token);
                                JSONObject sys  = reader.getJSONObject("data");
                                String main_token = sys.getString("access_token");
                                Log.e("error", "Final Resp : ------------------------------" + main_token);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("main_token", main_token);
                                startActivity(intent);

                            } catch (JSONException e) {
                                Log.e("error", "Final Resp : Fail--------------------------" );
                                e.printStackTrace();
                            }
                        }
                    }, 2000);
                }
            }, 3000);

            Log.e("error","auth :"+ auth_code);
            //Log.e("error","got token :"+ access_token);
            //Log.e("error","Token :"+ idToken);
            //String token = postRequest(access_token);
            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w( "Error", "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }
*/

}
