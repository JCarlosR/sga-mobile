package com.youtube.sorcjc.sga_mobile.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.youtube.sorcjc.sga_mobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DataFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public DataFragment() {
        // Required empty public constructor
    }


    private EditText etUsername;
    private EditText etLastName;
    private EditText etFirstName;
    private EditText etDocument;
    private EditText etAddress;
    private EditText etPhone;
    private EditText etCellphone;
    private EditText etEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        // Get control references
        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etDocument = (EditText) view.findViewById(R.id.etDocument);
        etAddress = (EditText) view.findViewById(R.id.etAddress);
        etPhone = (EditText) view.findViewById(R.id.etPhone);
        etCellphone = (EditText) view.findViewById(R.id.etCellphone);
        etEmail = (EditText) view.findViewById(R.id.etEmail);

        // Set default values
        etUsername.setText("1043300212");
        etLastName.setText("Ramos Suyón");
        etFirstName.setText("Juan Carlos");
        etDocument.setText("76474871");
        etAddress.setText("Los Rosales #136");
        etPhone.setText("");
        etCellphone.setText("966 543 777");
        etEmail.setText("juancagb.17@gmail.com");

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
        void onFragmentInteraction(Uri uri);
    }
}
