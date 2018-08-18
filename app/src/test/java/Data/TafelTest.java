package Data;

import org.junit.Test;

import static org.junit.Assert.*;

public class TafelTest {

    @Test
    public void getNummer() {
        int input = 5;
       Tafel tafel = new Tafel("id", 100,200, true, "123AB", input );

       assertEquals(input, tafel.getNummer());
    }

    @Test
    public void setNummer() {
        int input = 5;
        Tafel tafel = new Tafel("id", 100,200, true, "123AB", 4 );
        tafel.setNummer(input);
        assertEquals(input, tafel.getNummer());
    }

    @Test
    public void getId() {
        String input = "id";
        Tafel tafel = new Tafel("id", 100,200, true, "123AB", 5 );

        assertEquals(input, tafel.getId());
    }

    @Test
    public void setId() {
        String input = "test";
        Tafel tafel = new Tafel("id", 100,200, true, "123AB", 5 );
        tafel.setId(input);
        assertEquals(input, tafel.getId());
    }

    @Test
    public void getX_as() {
    }

    @Test
    public void setX_as() {
    }

    @Test
    public void getY_as() {
    }

    @Test
    public void setY_as() {
    }

    @Test
    public void getBezet() {
    }

    @Test
    public void setBezet() {
    }

    @Test
    public void getTransactieId() {
    }

    @Test
    public void setTransactieId() {
    }
}