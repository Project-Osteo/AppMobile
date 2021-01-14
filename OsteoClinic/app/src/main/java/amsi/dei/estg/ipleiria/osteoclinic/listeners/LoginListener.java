package amsi.dei.estg.ipleiria.osteoclinic.listeners;

public interface LoginListener {
    void onValidateLogin(String token, String email);
    void onValidateRegisto(long id, String email);
}
