package model;

public class Membre {
    private String id, nom, prenom, profession, sexe, mail, dateAdhesion;

    public Membre() {}

    public Membre(String id, String nom, String prenom, String profession, String sexe, String mail, String dateAdhesion) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
        this.sexe = sexe;
        this.mail = mail;
        this.dateAdhesion = dateAdhesion;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getDateAdhesion() { return dateAdhesion; }
    public void setDateAdhesion(String dateAdhesion) { this.dateAdhesion = dateAdhesion; }
}
