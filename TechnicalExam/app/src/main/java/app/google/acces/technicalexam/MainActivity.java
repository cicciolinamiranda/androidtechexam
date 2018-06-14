package app.google.acces.technicalexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import app.google.acces.technicalexam.model.ItemModel;
import app.google.acces.technicalexam.presenter.ListAdapter;
import app.google.acces.technicalexam.presenter.MainActivityPresenter;

import static app.google.acces.technicalexam.constant.AppConstants.ACTIVITY_ADD_ITEM;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainActivityPresenter.View {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.listView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab) {
            startActivityForResult(new Intent(MainActivity.this, AddActivity.class), ACTIVITY_ADD_ITEM);
        }
    }

    @Override
    public void setItems(ArrayList<ItemModel> data) {
        mAdapter = new ListAdapter(MainActivity.this, data);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_ADD_ITEM) {
            if(resultCode == RESULT_OK) {
            }
        }
    }
}
