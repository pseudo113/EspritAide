package Entity;

public class Commentaire {
    int id ;
    int id_user ;
    int id_fichier ;
    String commentaire ;

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", id_fichier=" + id_fichier +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_fichier() {
        return id_fichier;
    }

    public void setId_fichier(int id_fichier) {
        this.id_fichier = id_fichier;
    }
}
