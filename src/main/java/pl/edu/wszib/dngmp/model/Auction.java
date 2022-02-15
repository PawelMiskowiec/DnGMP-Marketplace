package pl.edu.wszib.dngmp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Auction {
    @Id
    @GeneratedValue
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    private Set<Message> messages;
    @CreatedDate
    private LocalDateTime createdAt;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private AuctionCategory category;
    private BigDecimal price;

    public Auction(User user, String title, String description, AuctionCategory category, BigDecimal price) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Auction auction = (Auction) o;
        return uuid != null && Objects.equals(uuid, auction.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
