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
@AllArgsConstructor
@NoArgsConstructor
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rulingId;
    @Column(name = "ruling_name")
    private String rulingName;
    @NonNull
    private String result;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @NonNull
    private Timestamp createAt;
}