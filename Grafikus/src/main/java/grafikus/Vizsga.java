package grafikus;
import javax.persistence.*;
@Entity
@Table(name = "vizsga")
public class Vizsga {
    @Id
    @Column(name = "vizsgazoaz")
    public int vizsgazoaz;
    @Column(name = "vizsgatargyaz")
    public String vizsgatargyaz;
    @Column(name = "szobeli")
    public int szobeli;
    @Column(name = "irasbeli")
    public int irasbeli;

    public int getVizsgazoaz() {
        return vizsgazoaz;
    }

    public void setVizsgazoaz(int vizsgazoaz) {
        this.vizsgazoaz = vizsgazoaz;
    }

    public String getVizsgatargyaz() {
        return vizsgatargyaz;
    }

    public void setVizsgatargyaz(String vizsgatargyaz) {
        this.vizsgatargyaz = vizsgatargyaz;
    }

    public int getSzobeli() {
        return szobeli;
    }

    public void setSzobeli(int szobeli) {
        this.szobeli = szobeli;
    }

    public double getIrasbeli() {
        return irasbeli;
    }

    public void setIrasbeli(int irasbeli) {
        this.irasbeli = irasbeli;
    }

    public Vizsga() {
    }

    public Vizsga(int vizsgazoaz, String vizsgatargyaz, int szobeli, int irasbeli) {
        this.vizsgazoaz = vizsgazoaz;
        this.vizsgatargyaz = vizsgatargyaz;
        this.szobeli = szobeli;
        this.irasbeli = irasbeli;
    }
}
