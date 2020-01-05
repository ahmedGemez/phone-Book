package com.MyTask.phonebook.Views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.MyTask.phonebook.R;
import com.MyTask.phonebook.Utils.Constants;
import com.MyTask.phonebook.model.PersonalDataModel;
import com.MyTask.phonebook.viewModels.PersonalDataViewModel;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.MyTask.phonebook.Utils.Constants.RESULT_LOAD_IMAGE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.firstname)
    EditText firstname;
    @BindView(R.id.Lastname)
    EditText Lastname;
    @BindView(R.id.code)
    public TextView code;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.latitude)
    EditText latitude;
    @BindView(R.id.longitude)
    EditText longitude;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.email)
    EditText email;

    public String file = "";
    @BindView(R.id.codeButton)
    ImageView codeButton;


    private PersonalDataViewModel personalDataViewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment AddContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddContactFragment newInstance(int param1) {
        AddContactFragment fragment = new AddContactFragment();
        Bundle args = new Bundle();
        args.putInt("id", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        setMenuVisibility(false);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_person, container, false);

        ButterKnife.bind(this, view);

        personalDataViewModel = ViewModelProviders.of(this).get(PersonalDataViewModel.class);


        int id = 0;
        if (getArguments().getInt("id") != 0) {

            id = getArguments().getInt("id");

            personalDataViewModel.getPersonalData(id).observe(this, new Observer<PersonalDataModel>() {
                @Override
                public void onChanged(@Nullable PersonalDataModel personalDataModels) {

                    firstname.setText(personalDataModels.getFirstName());
                    Lastname.setText(personalDataModels.getLastName());
                    phone.setText(personalDataModels.getPhone());
                    latitude.setText(personalDataModels.getLatitude() + "");
                    longitude.setText(personalDataModels.getLongitude() + "");
                    email.setText(personalDataModels.getEmail());
                    file=personalDataModels.getImage();
                    if(!file.equals("")) {
                        Constants.loadimage(profileImage,file);
                    }
                    code.setText(personalDataModels.getCode());

                }
            });

        }


        final int finalId = id;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  public PersonalDataModel(String firstName, String lastName, String email, double latitude, double longitude, String phone, String createdAt, String updatedAt) {

                String date = Constants.getDate();

                PersonalDataModel personalDataModel = new PersonalDataModel(file, firstname.getText() + "", Lastname.getText() + "", email.getText() + "", latitude.getText() + "", longitude.getText() + "",code.getText()+"" ,phone.getText() + "", date, date);


                if (TextUtils.isEmpty(Objects.requireNonNull(personalDataModel).getFirstName())) {
                    firstname.setError("enter your first name ");
                    firstname.requestFocus();
                } else if (personalDataModel.validateFirstName()) {
                    firstname.setError("enter accorect name");
                    firstname.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(personalDataModel).getPhone())) {
                    phone.setError("enter your phone ");
                    phone.requestFocus();
                } else if (personalDataModel.validatephone()) {
                    phone.setError("enter accorect phone");
                    phone.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(personalDataModel).getEmail())) {
                    email.setError("enter your email ");
                    email.requestFocus();
                } else if (!personalDataModel.validateEmail()) {
                    email.setError("enter accorect email");
                    email.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(personalDataModel).getLatitude() + "")) {
                    latitude.setError("enter your Latitude ");
                    latitude.requestFocus();
                } else if (personalDataModel.validateLatitude()) {
                    latitude.setError("enter accorect Latitude");
                    latitude.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(personalDataModel).getLongitude() + "")) {
                    longitude.setError("enter your Longitude");
                    longitude.requestFocus();
                } else if (personalDataModel.validatelongitude()) {
                    longitude.setError("enter accorect Latitude");
                    longitude.requestFocus();
                } else {

                    if (finalId == 0) {
                        personalDataViewModel.insert(personalDataModel, finalId);

                    } else {
                        personalDataModel.setId(finalId);
                        personalDataViewModel.update(personalDataModel, finalId);

                        Log.d("updated", personalDataModel.getId() + "  " + finalId);
                    }

                    Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(profileImage);
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/jpeg");
                Uri uri = galleryIntent.getData();
                getActivity().startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

            }
        });


        codeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(profileImage);
                Intent myIntent = new Intent(getActivity(), codeActivity.class);
               // .startActivity(myIntent);
                getActivity().startActivityForResult(myIntent, 2);

            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(ImageView profile) {
        if (mListener != null) {
            mListener.onFragmentInteraction(profile, this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(ImageView profile, Fragment fragment);
    }
}
