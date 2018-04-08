package Entity;

public class Cours {

    int id ;
    int id_user ;
    int id_class ;
    String type ;
    String nom ;
    String image_name ;
    String module ;

    public Cours( int id_user, int id_class, String type, String nom, String image_name, String module) {
      
        this.id_user = id_user;
        this.id_class = id_class;
        this.type = type;
        this.nom = nom;
        this.image_name = image_name;
        this.module = module;
    }

    
    
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }


}
