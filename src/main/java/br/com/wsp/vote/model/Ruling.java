package br.com.wsp.vote.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ruling")
public class Ruling implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Column(name = "created_by")
    @NonNull
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @NonNull
    private Timestamp createAt;

}