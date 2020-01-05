package com.MyTask.phonebook.Views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.MyTask.phonebook.R;
import com.MyTask.phonebook.model.CodeModel;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterDialog extends Dialog{

    Activity activity;
    @BindView(R.id.country)
    EditText country;
    @BindView(R.id.capoital)
    EditText capoital;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.filter)
    Button filter;

    public FilterDialog(Activity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.filter_dialog);
        ButterKnife.bind(this);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String query="SELECT * FROM Code ";
                Boolean flag=false;

                String valus [] =new String[3];
                int i=0;

                if (!TextUtils.isEmpty(Objects.requireNonNull(country).getText())){

                    if(!flag){
                        query+="WHERE ";
                        flag=true;
                    }
                    query+="country = ? ";
                    valus[i]=country.getText()+"";
                    i++;

                }
                if(!TextUtils.isEmpty(Objects.requireNonNull(capoital).getText())){

                    if(!flag){
                        query+="WHERE ";
                        flag=true;
                    }else{
                        query+="OR ";
                    }
                    query+="capital = ? ";
                    valus[i]=capoital.getText()+"";
                    i++;
                }

                if(!TextUtils.isEmpty(Objects.requireNonNull(code).getText())){

                    if(!flag){
                        query+="WHERE ";
                        flag=true;
                    }else{
                        query+="OR ";
                    }
                    query+="code = ? ";
                    valus[i]=code.getText()+"";
                    i++;
                }

                Log.d("iiiiiiiii",i+"");

                String valusCapy [] =new String[i];

                for (int j = 0; j <i ; j++) {
                    valusCapy[j]=valus[j];
                }


                Log.d("queryyyyy",query);

                SupportSQLiteQuery simpleSQLiteQuery=new SimpleSQLiteQuery(query,valusCapy);





                LiveData<List<CodeModel>> codeDataModel= ((codeActivity)activity).mViewModel.getfilterPersonalData(simpleSQLiteQuery);

                codeDataModel.observe(((codeActivity)activity), new Observer<List<CodeModel>>() {
                    @Override
                    public void onChanged(List<CodeModel> codeModels) {

                        Log.d("cccdscsdzc",codeModels.size()+"dd");
                        ((codeActivity)activity).adapter.setcodeDataModel(codeModels);
                    }
                });


                dismiss();

            }
        });


    }


}
