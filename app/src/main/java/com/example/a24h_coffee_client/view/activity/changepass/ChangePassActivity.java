package com.example.a24h_coffee_client.view.activity.changepass;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.widget.Toast;

        import com.example.a24h_coffee_client.R;
        import com.example.a24h_coffee_client.constant.AppConstants;
        import com.example.a24h_coffee_client.databinding.ActivityChangePassBinding;
        import com.example.a24h_coffee_client.model.User;
        import com.example.a24h_coffee_client.utils.FormatUtils;
        import com.google.gson.Gson;

public class ChangePassActivity extends AppCompatActivity implements ChangePassContract.View {
    private ActivityChangePassBinding mBinding;
    private ChangePassContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityChangePassBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mPresenter = new ChangePassPresenter(this);
        setListener();
    }

    private void setListener(){
        mBinding.ivBackChange.setOnClickListener(view -> onBackPressed());
        mBinding.btnChange.setOnClickListener(view -> startChange());
    }

    private void startChange(){
        mProgressDialog = ProgressDialog.show(this, "", "Đang thực hiện ...");
        String password = mBinding.tilPassOld.getEditText().getText().toString().trim();
        String passwordNew = mBinding.tilPassNew.getEditText().getText().toString().trim();
        String passwordAgain = mBinding.tilPassAgain.getEditText().getText().toString().trim();
        if (!mPresenter.doChange(password, passwordNew, passwordAgain, this)) {
            return;
        }
        mPresenter.changePassword(passwordNew, getUsername());
    }

    public String getUsername() {
        SharedPreferences prefs = getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(AppConstants.KEY_USERNAME, "");
    }

    @Override
    public void onMessage(String message) {
         mProgressDialog.dismiss();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUser(User user) {
        String userJson = new Gson().toJson(user);
        SharedPreferences.Editor editor = this.getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(AppConstants.KEY_USERNAME, user.getUserName());
        editor.putString(AppConstants.KEY_USER, userJson);
        editor.apply();
        mBinding.tvPassAgain.setText("");
        mBinding.tvPassNew.setText("");
        mBinding.tvPassOld.setText("");
    }
}