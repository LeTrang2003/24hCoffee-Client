package com.example.a24h_coffee_client.view.activity.updateinfor;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ActivityUpdateInforBinding;
import com.example.a24h_coffee_client.model.User;
import com.example.a24h_coffee_client.utils.FormatUtils;
import com.example.a24h_coffee_client.utils.RealPathUtil;
import com.example.a24h_coffee_client.utils.ValidateUtils;
import com.example.a24h_coffee_client.view.activity.search.SearchActivity;
import com.google.gson.Gson;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UpdateAccountActivity extends AppCompatActivity implements UpdateAccountContract.View{

    private ActivityUpdateInforBinding mBinding;
    private UpdateAccountContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;
    private Uri mUri;
    private  User user;
    private static final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUpdateInforBinding.inflate(getLayoutInflater());
        mPresenter = new UpdateAccountPresenter(this);
        setContentView(mBinding.getRoot());
        onClick();
        setValue();
    }

    private void setValue() {
        SharedPreferences sharedPreferences  = getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE);
        user = new Gson().fromJson(sharedPreferences.getString(AppConstants.KEY_USER,""), User.class) ;
        mBinding.tvNameInformation.setText(user.getName());
        mBinding.tvPhoneInformation.setText(user.getPhone());
        mBinding.tvDateInformation.setText(FormatUtils.formatDateCreate(user.getDate()));
        mBinding.tvSexInformation.setText(user.getSex());
        Glide.with(this).load(user.getImage()).centerCrop().into(mBinding.circleImageView);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onClick(){
        mBinding.ivBackInfor.setOnClickListener(view -> onBackPressed());
        mBinding.btnSaveUpInfor.setOnClickListener(view -> startChange());
        mBinding.openGalleryButton.setOnClickListener(view -> clickRequestPermission());
        mBinding.tvDateInformation.setOnTouchListener((view1, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                showDialogPicker();
            }
            return false;
        });
        mBinding.radioSex.setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton radioButton = mBinding.getRoot().findViewById(i);
            if (radioButton != null) {
                String selectedText = radioButton.getText().toString();
                mBinding.tvSexInformation.setText(selectedText);
            }
        });
    }

    private void showDialogPicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
               this,
                R.style.CustomDatePickerDialog,
                (datePicker, selectedYear, selectedMonth, selectedDay) -> {
                    // Tạo một Calendar object từ ngày được chọn
                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(selectedYear, selectedMonth, selectedDay);
                    if (isDateAfterToday(selectedCalendar.getTime())){
                        Toast.makeText(this, "Ngày chọn lớn hơn ngày hiện tại", Toast.LENGTH_SHORT).show();
                    }else {
                        mBinding.tvDateInformation.setText(FormatUtils.formatDateCreate(selectedCalendar.getTime()));
                    }
                },
                year,
                month,
                dayOfMonth
        );

        datePickerDialog.show();
    }

    private void startChange() {
        if (!ValidateUtils.validateUpdateAccountIsEmpty(mBinding.tvNameInformation.getText().toString().trim(),
                mBinding.tvPhoneInformation.getText().toString().trim(),
                mBinding.tvDateInformation.getText().toString().trim(),
                mBinding.tvSexInformation.getText().toString().trim()
                )) {
            Toast.makeText(this, AppConstants.ENTER_INFO, Toast.LENGTH_SHORT).show();
            return;
        }
        mProgressDialog = ProgressDialog.show(this, "", "Đang thực hiện ...");
        if (mUri == null){
            try {
                mPresenter.updateInFor(getUsername(),
                        mBinding.tvNameInformation.getText().toString().trim(),
                        user.getImage(),
                        mBinding.tvPhoneInformation.getText().toString().trim(),
                        FormatUtils.formatDateOfBirth(FormatUtils.parseDateCreate(mBinding.tvDateInformation.getText().toString().trim())),
                        mBinding.tvSexInformation.getText().toString().trim()
                        );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else {
            callApiUpdateInformation();
        }
    }

    private void callApiUpdateInformation() {
        RequestBody requestBodyUserName = RequestBody.create(MediaType.parse("multipart/form-data"),  getUsername());
        RequestBody requestBodyName = RequestBody.create(MediaType.parse("multipart/form-data"),  mBinding.tvNameInformation.getText().toString().trim());
        RequestBody requestBodyPhone = RequestBody.create(MediaType.parse("multipart/form-data"),  mBinding.tvPhoneInformation.getText().toString().trim());
        RequestBody requestBodyDate = null;
        try {
            requestBodyDate = RequestBody.create(MediaType.parse("multipart/form-data"), FormatUtils.formatDateOfBirth(FormatUtils.parseDateCreate(mBinding.tvDateInformation.getText().toString().trim())));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        RequestBody requestBodySex = RequestBody.create(MediaType.parse("multipart/form-data"),  mBinding.tvSexInformation.getText().toString().trim());
        String strPath = RealPathUtil.getRealPath(this, mUri);
        File file = new File(strPath);
        RequestBody requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image",file.getName(), requestBodyImage);

        mPresenter.updateInForFile(requestBodyUserName, requestBodyName, image, requestBodyPhone, requestBodyDate, requestBodySex);
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
        onBackPressed();
    }

    public void choseImgFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Chọn ảnh"));
    }

    // nhận uri khi chọn ảnh từ thư viện
    private final ActivityResultLauncher<Intent> mActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            if (data == null){
                                return;
                            }
                            mUri = data.getData();
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mUri);
                                mBinding.circleImageView.setImageBitmap(bitmap);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });

    private void clickRequestPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
            choseImgFromGallery();
        }else {
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    // nhan phan hoi nguoi dung bang onRequestPermissionsResult()
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE ){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                choseImgFromGallery();
            }else {
                Toast.makeText(this, "Quyền đã bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isDateAfterToday(Date date) {
        Calendar calInput = Calendar.getInstance();
        calInput.setTime(date);

        Calendar calToday = Calendar.getInstance();

        return calInput.get(Calendar.YEAR) > calToday.get(Calendar.YEAR) ||
                (calInput.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                        calInput.get(Calendar.MONTH) > calToday.get(Calendar.MONTH)) ||
                (calInput.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                        calInput.get(Calendar.MONTH) == calToday.get(Calendar.MONTH) &&
                        calInput.get(Calendar.DAY_OF_MONTH) > calToday.get(Calendar.DAY_OF_MONTH));
    }
}