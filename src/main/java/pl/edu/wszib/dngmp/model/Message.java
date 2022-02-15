package pl.edu.wszib.dngmp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    @CreatedDate
    private LocalDateTime createdAt;
    private String message;
    @ManyToOne
    private User user;
    @ManyToOne
    private Auction auction;

    public Message(String message, User user, Auction auction) {
        this.message = message;
        this.user = user;
        this.auction = auction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Message message = (Message) o;
        return uuid != null && Objects.equals(uuid, message.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
