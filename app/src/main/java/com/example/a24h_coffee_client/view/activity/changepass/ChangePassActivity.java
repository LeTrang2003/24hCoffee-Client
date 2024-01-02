package com.example.a24h_coffee_client.view.activity.changepass;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.ProgressDialog;
        import android.os.Bundle;

        import com.example.a24h_coffee_client.R;
        import com.example.a24h_coffee_client.databinding.ActivityChangePassBinding;

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
        mPresenter.getUser();
        setListener();
    }

    private void setListener(){
        mBinding.ivBackChange.setOnClickListener(view -> onBackPressed());
        mBinding.btnChange.setOnClickListener(view -> startChange());
    }

    private void startChange(){
        String password = mBinding.tilPassOld.getEditText().getText().toString().trim();
        String passwordNew = mBinding.tilPassNew.getEditText().getText().toString().trim();
        String passwordAgain = mBinding.tilPassAgain.getEditText().getText().toString().trim();
        if (!mPresenter.doChange(password, passwordNew, passwordAgain)) {
            return;
        }
        mPresenter.changePassword(passwordAgain);
    }

    @Override
    public void onMessage(String message) {

    }
}