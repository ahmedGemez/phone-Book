package com.MyTask.phonebook.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MyTask.phonebook.R;
import com.MyTask.phonebook.Utils.Constants;
import com.MyTask.phonebook.Views.AddContactFragment;
import com.MyTask.phonebook.Views.ContactListFragment;
import com.MyTask.phonebook.model.PersonalDataModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<PersonalDataModel> personalDataModels;
    private OnItemClickListener onItemClickListener;
    private ContactListFragment contactListFragment;

    public ContactListAdapter(Context context, ContactListFragment contactListFragment) {
        this.layoutInflater = LayoutInflater.from(context);
        this.onItemClickListener =(OnItemClickListener) contactListFragment;
        this.contactListFragment=contactListFragment;
        mContext = context;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_contant, parent, false);
        ContactViewHolder viewHolder = new ContactViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        if (personalDataModels != null) {
            final PersonalDataModel personalDataModel = personalDataModels.get(position);
            Log.d("kjhcd", personalDataModel.getEmail() + "dddk");
            holder.setData(personalDataModel,position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(personalDataModel.getId(), view);
                }
            });

            holder.setListeners(personalDataModel.getId());


        }

    }

    @Override
    public int getItemCount() {
        Log.d("svsfv","sdcds");
        if (personalDataModels != null)
            return personalDataModels.size();
        else return 0;
    }

    public void setPersonalDataModel(List<PersonalDataModel> personalDataModels) {
        this.personalDataModels = personalDataModels;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_image)
        CircleImageView profileImage;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.edit)
        ImageView edit;
        @BindView(R.id.remove)
        ImageView remove;
        @BindView(R.id.call)
        ImageView call;
        @BindView(R.id.id)
        TextView id;

        int postion;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }



        public void setData(PersonalDataModel personalDataModel,int postion) {
            ((ImageView)profileImage).setImageResource(R.drawable.photographer);

            if(!personalDataModel.getImage().equals("")) {

                Log.d("svsfvs",personalDataModel.getImage()+"dd");
                Constants.loadimage(profileImage,personalDataModels.get(postion).getImage());
            }
            this.postion=postion;
            name.setText(personalDataModel.getFirstName()+" "+personalDataModels.get(postion).getLastName());

        }

        public void setListeners(final int id) {
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClickListener.onItemDeleteClick(personalDataModels.get(postion));

                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   int id= personalDataModels.get(postion).getId();
                    FragmentTransaction ft = contactListFragment.getActivity().getSupportFragmentManager().beginTransaction();
                    AddContactFragment productFragment = AddContactFragment.newInstance(id);
                    ft.add(R.id.content, productFragment);
                    ft.addToBackStack("backStack");
                    ft.commit();


                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemCall(personalDataModels.get(postion).getPhone());
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int id, View view);
        void onItemDeleteClick(PersonalDataModel personalDataModel);
        void onItemCall(String Phone);
        void onItemUpdate(String Phone);
    }

}
