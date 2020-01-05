package com.MyTask.phonebook.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.MyTask.phonebook.R;
import com.MyTask.phonebook.adapters.CodeListAdapter;
import com.MyTask.phonebook.model.CodeModel;
import com.MyTask.phonebook.viewModels.CodeViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MyTask.phonebook.Utils.Constants.RESULT_LOAD_Code;

public class codeActivity extends AppCompatActivity implements CodeListAdapter.OnItemClickListener {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;

    CodeListAdapter adapter;
    public CodeViewModel mViewModel;
    @BindView(R.id.layout_filter)
    LinearLayout layoutFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        ButterKnife.bind(this);


        adapter = new CodeListAdapter(this, this);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycleview.setLayoutManager(gridLayoutManager);
        recycleview.setAdapter(adapter);


        mViewModel = ViewModelProviders.of(this).get(CodeViewModel.class);

        //

        mViewModel.codeDataModel.observe(this, new Observer<List<CodeModel>>() {
            @Override
            public void onChanged(List<CodeModel> codeModels) {

                if (codeModels.size() == 0) {
                    mViewModel.Downloadcodes();
                }

                adapter.setcodeDataModel(codeModels);


            }
        });

        layoutFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterDialog filterDialog=new FilterDialog(codeActivity.this);
                filterDialog.show();
            }
        });

    }

    @Override
    public void onItemClick(int id, String code) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("code", code);
        setResult(RESULT_LOAD_Code, returnIntent);
        finish();

    }

    public interface OnItemClickListener {
        void onItemClick(int id, View view);
    }
}
