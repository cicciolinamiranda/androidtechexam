package app.google.acces.technicalexam.presenter;

import android.content.Context;

import java.util.ArrayList;

import app.google.acces.technicalexam.database.SqlLiteHelper;
import app.google.acces.technicalexam.model.ItemModel;

/**
 * Created by cicciolina on 2/1/18.
 */

public class MainActivityPresenter{
    private View mainView;
    private Context mContext;
    private SqlLiteHelper sqlLiteHelper;
    private ArrayList<ItemModel> dataList = new ArrayList<>();

    public MainActivityPresenter(Context mContext, View mainView) {
        this.mainView = mainView;
        this.mContext = mContext;
        sqlLiteHelper = new SqlLiteHelper(mContext);
    }

    public void getDataToDB(){
        dataList = sqlLiteHelper.getAllEquipment();
        this.mainView.setItems(dataList);
    }

    public interface View {

        void setItems(ArrayList<ItemModel> data);

    }
}
