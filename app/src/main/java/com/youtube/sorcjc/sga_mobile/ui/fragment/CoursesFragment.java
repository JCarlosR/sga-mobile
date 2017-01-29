package com.youtube.sorcjc.sga_mobile.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youtube.sorcjc.sga_mobile.R;
import com.youtube.sorcjc.sga_mobile.domain.Course;
import com.youtube.sorcjc.sga_mobile.domain.CourseNote;
import com.youtube.sorcjc.sga_mobile.domain.CycleNote;
import com.youtube.sorcjc.sga_mobile.domain.User;
import com.youtube.sorcjc.sga_mobile.io.SgaApiAdapter;
import com.youtube.sorcjc.sga_mobile.io.response.NotesResponse;
import com.youtube.sorcjc.sga_mobile.ui.Global;
import com.youtube.sorcjc.sga_mobile.ui.adapter.CourseAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CoursesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CoursesFragment extends Fragment implements Callback<NotesResponse> {

    private OnFragmentInteractionListener mListener;

    public CoursesFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private CourseAdapter coursesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        coursesAdapter = new CourseAdapter(getActivity());

        // context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        // Setting the recycler view
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(coursesAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // loadFakeCourses();
        loadCourses();
    }

    private void loadFakeCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Elab. proy tesis", "Juan Santos", 5, "Obligatorio"));
        courses.add(new Course("Cálculo II", "Guibar Obeso", 4, "Obligatorio"));
        courses.add(new Course("TAIS II", "Edwin Cieza", 4, "Obligatorio"));
        courses.add(new Course("Ingeniería web", "Ricardo Mendoza", 4, "Electivo"));
        //coursesAdapter.setAll(courses);
    }

    // TODO: Test in Juarez's Laptop
    private void loadCourses() {
        // Hardcoded enrollment => This code has to be replaced
        User usuario = Global.getFromSharedPreferences(getActivity(),"user_login");
        Call<NotesResponse> call = SgaApiAdapter.getApiService().getNotesResponse(usuario.getLogin());
        call.enqueue(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    @Override
    public void onResponse(Call<NotesResponse> call, Response<NotesResponse> response) {
        // What we have
        ArrayList<CycleNote> cycleNotes = response.body().getNotes();

        //aca solo toma el primero, pero debe tomar todos en caso sea varios ciclos
        CycleNote firstCycle = cycleNotes.get(0);
        ArrayList<CourseNote> courseNotes = firstCycle.getCourses();

        // What we need
        ArrayList<Course> courses = new ArrayList<>();

        // How we adapt
        for (CourseNote courseNote : courseNotes) {
            Course course = new Course(courseNote.getName(), courseNote.getDocente(), courseNote.getCreditos(), courseNote.getTipo());
            courses.add(course);
        }

        // Finally
        coursesAdapter.setAll(courses,courseNotes);
    }

    @Override
    public void onFailure(Call<NotesResponse> call, Throwable t) {
        Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
