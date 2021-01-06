package amsi.dei.estg.ipleiria.osteoclinic.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.R;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.FeedbacksListener;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;

public class ListaFeedbackFragment extends Fragment implements FeedbacksListener {

    private ListView listafeedback;

    public ListaFeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_feedback, container, false);
    }

    @Override
    public void onRefreshListaFeedbacks(ArrayList<Feedback> listafeedback) {

    }
}