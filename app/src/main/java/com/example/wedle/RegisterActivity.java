package com.example.wedle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.wedle.databinding.FragmentSlideshowBinding;
import com.example.wedle.ui.Login.LoginFragment;
import com.example.wedle.ui.Login.LoginViewModel;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class RegisterActivity extends AppCompatActivity {
    private EditText ID,PASSWORD, USERNAME, USERAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //엑티비티 시작시 처음으로 실행되는 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ID = findViewById(R.id.ID);
        PASSWORD = findViewById(R.id.PASSWORD);
        USERNAME = findViewById(R.id.USERNAME);
        USERAGE = findViewById(R.id.USERAGE);
//        L_fragment = (LinearLayout)findViewById(R.id.nav_slideshow);
        Button button = findViewById(R.id.button8); //취소
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, NavigationActivity.class);
                startActivity(intent);
            }
        });
        Button bnt = findViewById(R.id.button7); //가입
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //EditText에 현재 입력되어있는 값을 get해온다.
                String userID = ID.getText().toString();
                String userPassword = PASSWORD.getText().toString();
                String userName = USERNAME.getText().toString();
                int userAge = Integer.parseInt(USERAGE.getText().toString());
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success) { //회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원등록에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, NavigationActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "회원등록에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //서버로 Volley를 이용해서 요청을 함.
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}