package az.ingress.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tours")
@FieldDefaults(level = PRIVATE)
public class TourEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;
    String description;
    BigDecimal price;
    LocalDate startDate;
    LocalDate endDate;

    @OneToMany(
            fetch = LAZY,
            cascade = PERSIST,
            mappedBy = "tour",
            orphanRemoval = true
    )
    @ToString.Exclude
    Set<DestinationEntity> destinations;

    @ManyToMany(
            fetch = LAZY,
            cascade = PERSIST
    )
    @JoinTable(
            name = "tour_guide",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "guide_id")
    )
    @ToString.Exclude
    Set<GuideEntity> guides;

    @ManyToMany(
            cascade = PERSIST
    )
    @JoinTable(
            name = "tour_travel",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "travel_id")
    )
    @ToString.Exclude
    Set<TravelerEntity> travelers;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TourEntity tour = (TourEntity) o;
        return getId() != null && Objects.equals(getId(), tour.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
