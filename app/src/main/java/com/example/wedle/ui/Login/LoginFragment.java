package com.example.wedle.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.wedle.MainActivity;
import com.example.wedle.MainScreen;
import com.example.wedle.NavigationActivity;
import com.example.wedle.R;
import com.example.wedle.RegisterActivity;
import com.example.wedle.databinding.FragmentSlideshowBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {
    private EditText id, password;
    private  Button btn_login, bnt_register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_screen);

    }
    private FragmentSlideshowBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LoginViewModel slideshowViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        Button button = root.findViewById(R.id.button10);
        id = root.findViewById(R.id.id);
        password = root.findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() { //회원가입
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() ,RegisterActivity.class);
                startActivity(intent);
            }
        });
        Button btn = root.findViewById(R.id.button9);
        btn.setOnClickListener(new View.OnClickListener() { //로그인
            @Override
            public void onClick(View view) {
                String userID = id.getText().toString();
                String userPassword = password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success) { //로그인에 성공한 경우
//                                String userID = jsonObject.getString("userID");
//                                String userPassword = jsonObject.getString("userPassword");
//                                String userName = jsonObject.getString("userName");
                                Toast.makeText(root.getContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), NavigationActivity.class);
//                                intent.putExtra("userID", userID);
//                                intent.putExtra("userName", userName);
                                startActivity(intent);
                            } else{ //로그인에 실패한 경우
                                Toast.makeText(root.getContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(loginRequest);
            }
        });
//        View root = binding.getRoot();
//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
//    Button button = LoginFragment(R.id.button10);
//        button.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(LoginFragment.this, RegisterActivity.class);
//            startActivity(intent);
//        }
//    });

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}