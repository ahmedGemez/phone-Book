package com.MyTask.phonebook.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.MyTask.phonebook.R;
import com.MyTask.phonebook.model.CodeModel;
import com.MyTask.phonebook.model.PersonalDataModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CodeListAdapter extends RecyclerView.Adapter<CodeListAdapter.CodeViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<CodeModel> codeModels;
    private CodeListAdapter.OnItemClickListener onItemClickListener;

    public CodeListAdapter(Context context, CodeListAdapter.OnItemClickListener onItemClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
        mContext = context;
    }


    @NonNull
    @Override
    public CodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_code, parent, false);
        CodeViewHolder viewHolder = new CodeViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CodeViewHolder holder, int position) {

        if (codeModels!= null) {
            final CodeModel codeModel = codeModels.get(position);
            holder.setData(codeModel, position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // onItemClickListener.onItemClick(personalDataModel.getId(), view);
                }
            });

            holder.setListeners(codeModel.getId());


        }

    }

    @Override
    public int getItemCount() {
        Log.d("svsfv", "sdcds");
        if (codeModels != null)
            return codeModels.size();
        else return 0;
    }

    public void setcodeDataModel(List<CodeModel> codeModels) {
        this.codeModels = codeModels;
        notifyDataSetChanged();
    }


    class CodeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.code)
        TextView code;
        @BindView(R.id.country)
        TextView country;
        @BindView(R.id.capital)
        TextView capital;
        View itemView;
        int postion;

        public CodeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            ButterKnife.bind(this, itemView);

        }


        public void setData(CodeModel codeModel, int postion) {

            code.setText(codeModel.getCode());
            country.setText(codeModel.getCountry());
            capital.setText(codeModel.getCapital());

        }

        public void setListeners(final int id) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(id,code.getText()+"");
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int id, String code);

    }



}
