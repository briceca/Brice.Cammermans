package Data;

public class Order {
    private Artikel artikel;
    private int aantal;
    public Order(Artikel artikel)
    {
        this.artikel = artikel;
        aantal = 1;
    }
}
