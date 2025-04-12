package models;
public class Fiche {
    private int idFiche;
    public boolean isSigned;
    public String details;
    public int idCours;
    public int idCahierDeTexte;

    public Fiche(int idFiche, boolean isSigned, String details, int idCours, int idCahierDeTexte) {
        this.idCahierDeTexte = idCahierDeTexte;
        this.idCours = idCours;
        this.details = details;
        this.idFiche = idFiche;
        this.isSigned = isSigned;
    }

    // public String toString(){
    //     return this.idFiche+" "+this.isSigned+" "+this.details+" "+this.cours.toString();
    // }


    public int getIdFiche() {
        return idFiche;
    }

    public void setIdFiche(int idFiche) {
        this.idFiche = idFiche;
    }
    
}
