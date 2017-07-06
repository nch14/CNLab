package cn.chenhaonee.cnlab;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.chenhaonee.cnlab.dao.HttpHelper;
import cn.chenhaonee.cnlab.view.LoadingDiaolog;
import cn.chenhaonee.cnlab.vo.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameView;
    private EditText passwordView;

    private Button loginButton;

    private TextView sendPassword;

    private LoadingDiaolog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();//高版本可以换成 ActionBar actionBar = getActionBar();
        actionBar.hide();


        showLogin();
    }

    private void showLogin() {
        userNameView = (EditText) findViewById(R.id.input_id);
        passwordView = (EditText) findViewById(R.id.input_password);

        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new DoLogin());

        sendPassword = (TextView) findViewById(R.id.link_signup);
        sendPassword.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "我才不告诉你这个功能不在这个APP支持", Toast.LENGTH_LONG).show();
        });
    }

    private class DoLogin implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            boolean checkId = checkId();
            boolean checkPwd = checkPassword();
            if (checkId && checkPwd) {
                login();
            }
        }
    }

    private boolean checkId() {
        String s = userNameView.getText().toString();
        if (s == null) {
            userNameView.setError("账号不能为空");
            return false;
        }
        return true;
    }

    private boolean checkPassword() {
        String s = passwordView.getText().toString();
        if (s == null) {
            passwordView.setError("密码不能为空");
            return false;
        }
        return true;
    }

    private void showLoadingDialog() {
        FragmentManager fm = getFragmentManager();
        dialog = new LoadingDiaolog();
        dialog.show(fm, "");
    }

    private void hideLoadingDialog() {
        if (dialog != null)
            dialog.dismiss();
    }


    private void login() {
        loginButton.requestFocus();
        showLoadingDialog();

        new LoginAsyncTask().execute(new String[]{userNameView.getText().toString(), passwordView.getText().toString()});
    }

    /**
     * 完成异步登陆操作
     */
    class LoginAsyncTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {

            JSONObject params = new JSONObject();
            try {
                params.put("username", strings[0]);
                params.put("password", strings[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(HttpHelper.JSON, params.toString());

            Request request = new Request.Builder().url(HttpHelper.getLoginUrl()).post(body).build();
            Response response = HttpHelper.takeTask(request);

            JSONObject responseInJson = null;
            if (response.isSuccessful()) {
                String result;
                try {
                    result = response.body().string();
                    responseInJson = new JSONObject(result);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return responseInJson;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            if (dialog != null)
                hideLoadingDialog();

            if (json == null) {
                String words;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    words = "服务器异常，无法连接";
                } else {
                    words = "请检查您的网络";
                }
                Toast.makeText(LoginActivity.this, words, Toast.LENGTH_SHORT).show();
            } else {
                User user = parseUser(json);
                if (user == null) {
                    Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                    passwordView.setText("");
                } else {
                    HttpHelper.addHead(userNameView.getText().toString(), passwordView.getText().toString());
                    if (user.getType().equals("student"))
                        startActivity(new Intent(LoginActivity.this, StuMainActivity.class));
                    else
                        startActivity(new Intent(LoginActivity.this, TeaMainActivity.class));
                    finish();
                }

            }
        }
    }

    private User parseUser(JSONObject response) {
        User user = null;
        try {
            String username = response.getString("username");
            String name = response.getString("name");
            String type = response.getString("type");
            String avatar = response.getString("avatar");
            String gender = response.getString("gender");
            String email = response.getString("email");

            switch (type) {
                case "student":
                    int gitId = response.getInt("gitId");
                    String number = response.getString("number");
                    user = new User(username, name, type, avatar, gender, email, gitId, number);
                    break;
                default:
                    String authority = response.getString("authority");
                    user = new User(username, name, type, avatar, gender, email, authority);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
