package app.google.acces.technicalexam.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import app.google.acces.technicalexam.constant.AppConstants;
import app.google.acces.technicalexam.database.SqlLiteHelper;
import app.google.acces.technicalexam.model.ItemModel;

/**
 * Created by cicciolina on 2/1/18.
 */

public class AddItemPresenter {
    private AddItemPresenter.View addItemView;
    private Context mContext;
    private Activity mActivity;
    private SqlLiteHelper sqlLiteHelper;

    public AddItemPresenter(Context mContext, Activity mActivity, AddItemPresenter.View addItemView) {
        this.addItemView = addItemView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        sqlLiteHelper = new SqlLiteHelper(mContext);
    }

    public void save(String itemName, String description, String imagePath){
        if(checkNull(itemName, description)){
            sqlLiteHelper.addEquipment(new ItemModel(itemName, description, imagePath));
            this.addItemView.saveSuccessful();
        }
    }

    public void intentCancel(){
        this.addItemView.intentCancel();
    }

    public void intentPhotoPick(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String permission2 = android.Manifest.permission.READ_EXTERNAL_STORAGE;
            if (ActivityCompat.checkSelfPermission(this.mContext, permission2) == PackageManager.PERMISSION_GRANTED) {
                photoPickActivity();
            } else {
                this.addItemView.initPermission();
            }
        }
    }

    private boolean checkNull(String itemName, String description){
        if(itemName.equals("")){
            Toast.makeText(this.mContext, "Item name must filled up.", Toast.LENGTH_SHORT).show();
            return  false;
        }else if(description.equals("")){
            Toast.makeText(this.mContext, "Description must filled up.", Toast.LENGTH_SHORT).show();
            return  false;
        }else{
            return true;
        }
    }

    private void photoPickActivity() {
        Intent photoPickerIntent = new Intent();
        photoPickerIntent.setType("image/*");
        photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        mActivity.startActivityForResult(photoPickerIntent, AppConstants.REQUEST_PHOTO_PICK);
    }
    public interface View {

        void saveSuccessful();

        void intentCancel();

        void initPermission();
    }
}
