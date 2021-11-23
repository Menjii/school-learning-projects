package pl.pwsztar.mobilerestaurant.model;

import javax.persistence.*;
import java.util.TimeZone;

@Entity
@Table(name = "platnosc", schema = "restauracja")
public class PaymentDataDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_platnosci")
    private int id;

    @Column(name = "kwota_platnosci")
    private int amount;

    @Column(name = "metoda_platnosci")
    private String method;

    @Column(name = "data_start")
    private String startData;

    @Column(name = "data_oplacenia")
    private String paymentData;

    public PaymentDataDto() {
        super();
    }

    public PaymentDataDto(int id, int amount, String method, String startData, String paymentData) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.startData = startData;
        this.paymentData = paymentData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(String paymentData) {
        this.paymentData = paymentData;
    }
}
