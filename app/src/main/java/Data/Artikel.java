package Data;


public class Artikel {
    private String naam;
    private Double prijs;
    private Type type;
    private String id;

    public Artikel()
    {
        //default constructor
    }
    public Artikel(String naam, Double prijs, Type type)
    {
        this.naam = naam;
        this.prijs = prijs;
        this.type = type;
    }
    public Artikel(String id, String naam, Double prijs, Type type)
    {
        this.id = id;
        this.naam = naam;
        this.prijs = prijs;
        this.type = type;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
