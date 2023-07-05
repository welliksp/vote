package br.com.wsp.vote.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Getter
@Setter
@Builder
@Table(name = "vote")
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rulingId;
    @Column(name = "ruling_name")
    private String rulingName;
    private String username;
    @NonNull
    private String result;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @NonNull
    private Timestamp createAt;

    public Vote(Long rulingId, String rulingName, String username, @NonNull String result) {
        this.rulingId = rulingId;
        this.rulingName = rulingName;
        this.result = result;
        this.username = username;
    }

    public Vote(Long id, Long rulingId, String rulingName, String username, @NonNull String result, @NonNull Timestamp createAt) {
        this.id = id;
        this.rulingId = rulingId;
        this.rulingName = rulingName;
        this.username = username;
        this.result = result;
        this.createAt = createAt;
    }

    public Vote() {
    }
}