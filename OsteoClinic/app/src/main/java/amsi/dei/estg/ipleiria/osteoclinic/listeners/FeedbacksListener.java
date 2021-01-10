package amsi.dei.estg.ipleiria.osteoclinic.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;

public interface FeedbacksListener {
    void onRefreshListaFeedbacks(ArrayList<Feedback> listafeedback);

    void onRefreshDetalhes();
}
