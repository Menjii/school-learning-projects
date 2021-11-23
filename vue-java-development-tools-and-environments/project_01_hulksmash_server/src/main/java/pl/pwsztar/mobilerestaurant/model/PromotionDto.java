package pl.pwsztar.mobilerestaurant.model;

import javax.persistence.*;

@Entity
@Table(name = "promocja", schema = "restauracja")
public class PromotionDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promocji")
    private int id;

    @Column(name = "czy_aktywna")
    private boolean isActive;

    public PromotionDto() {
        super();
    }

  public PromotionDto(int id, boolean isActive) {
    this.id = id;
    this.isActive = isActive;
  }

  public int getId() {
    return id;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @Override
  public String toString() {
    return "PromotionDto{" +
      "id=" + id +
      ", isActive=" + isActive +
      '}';
  }
}

