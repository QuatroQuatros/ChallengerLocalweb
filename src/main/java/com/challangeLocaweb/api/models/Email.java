package com.challangeLocaweb.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "emails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sender", nullable = false, length = 255)
    private String sender;

    @Column(name = "subject", nullable = false, length = 255)
    private String subject;

    @Column(name = "content_html", length = 255)
    private String contentHtml;

    @Column(name = "content_plain", length = 255)
    private String contentPlain;

    @Column(name = "is_confidential", nullable = false)
    private Boolean isConfidential = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
