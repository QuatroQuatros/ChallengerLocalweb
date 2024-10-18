package com.challangeLocaweb.api.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blacklist")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor   // Lombok cria o construtor sem argumentos
@AllArgsConstructor  // Lombok cria o construtor com todos os argumentos
public class BlackList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long blackListId;

    @Column(nullable = false, name = "ip_address", length = 15)
    private String ipAddress;

    @Column(nullable = false, name = "reason")
    private String reason;

    @Column(nullable = false, name = "status")
    private boolean status;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_time")
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updateAt = LocalDateTime.now();
    }

}
