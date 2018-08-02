package example.com.appandroid.module.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.com.appandroid.R;
import example.com.appandroid.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button confirmBtn = (Button) findViewById(R.id.login_confirm);
        confirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_confirm:
                loginConfirm();
                break;
        }
    }

    public void loginConfirm() {

    }
}
