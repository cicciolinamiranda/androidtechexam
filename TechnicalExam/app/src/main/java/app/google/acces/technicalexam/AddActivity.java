package app.google.acces.technicalexam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import app.google.acces.technicalexam.constant.AppConstants;
import app.google.acces.technicalexam.model.ImageFilePath;
import app.google.acces.technicalexam.presenter.AddItemPresenter;
import app.google.acces.technicalexam.util.Util;

import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED;

public class AddActivity extends AppCompatActivity implements View.OnClickListener, AddItemPresenter.View {

    private EditText et_itemName;
    private EditText et_description;
    private Button btnAddPhoto;
    private ImageView imagePreview;
    private Button btnSave;
    private Button btnCancel;
    private String selectedImagePath = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_itemName = (EditText) findViewById(R.id.editText_itemName);
        et_description = (EditText) findViewById(R.id.editText_desc);
        imagePreview = (ImageView) findViewById(R.id.imageView);
        btnAddPhoto = (Button) findViewById(R.id.btnAddPhoto);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnAddPhoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSave){


        }else if(view.getId() == R.id.btnCancel){


        }else if(view.getId() == R.id.btnAddPhoto){

        }
    }

    @Override
    public void saveSuccessful() {
        Intent i = new Intent();
        setResult(RESULT_OK,i);
        finish();
    }

    @Override
    public void intentCancel() {
        Intent i = new Intent();
        setResult(RESULT_CANCELED,i);
        finish();
    }

    public void setImagePath(String imagePath) {
        selectedImagePath = imagePath;
        imagePreview.setImageBitmap(Util.getInstance().resizeBitmap(imagePath , 200, 200));
    }


    @Override
    public void initPermission() {
        String[] permissions = {android.Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(AddActivity.this, permissions, AppConstants.REQUEST_CAMERA_PERMISSION_PICK);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2) {
            boolean callForRecheckPermissions = false;
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PERMISSION_DENIED) {
                    callForRecheckPermissions = true;
                }
            }
            if (!callForRecheckPermissions) {
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == AppConstants.REQUEST_PHOTO_PICK){
            if (null == data ||  data.getData() == null) return;

            Uri selectedImageUri = data.getData();
            String imagePath = ImageFilePath.getPath(this, selectedImageUri);
            setImagePath(imagePath);

        }else if(requestCode == AppConstants.REQUEST_CAMERA_PERMISSION_PICK){
        }
    }
}
