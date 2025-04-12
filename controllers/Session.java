package controllers;

public class Session {

    private static Session instance;
    private int id;

    // Constructeur privé pour le singleton
    private Session() {
    }

    // Méthode pour obtenir l'instance
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Méthodes pour gérer les données de session
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void clearSession() {
        id = (Integer) null;
    }

    public void setUsername(String username) {
    }
}
