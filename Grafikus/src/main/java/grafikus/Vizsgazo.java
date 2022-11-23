package grafikus;
import javax.persistence.*;

@Entity
@Table(name = "vizsgazo")
public class Vizsgazo {
    @Id
    @Column(name = "azon")
    public int azon2;
    @Column(name = "nev")
    public String nev2;
    @Column(name = "osztaly")
    public String osztaly;

    public Vizsgazo(int azon, String nev, String osztaly) {
        this.azon2 = azon;
        this.nev2 = nev;
        this.osztaly = osztaly;
    }

    public Vizsgazo() {
    }

    public int getAzon2() {
        return azon2;
    }

    public void setAzon2(int azon) {
        this.azon2 = azon;
    }

    public String getNev2() {
        return nev2;
    }

    public void setNev2(String nev) {
        this.nev2 = nev;
    }

    public String getOsztaly() {
        return osztaly;
    }

    public void setOsztaly(String osztaly) {
        this.osztaly = osztaly;
    }
}
