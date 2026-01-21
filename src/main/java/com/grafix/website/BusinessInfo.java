package com.grafix.website;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "business_info")
@Data
public class BusinessInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studioName;
    private String tagLine;

    @Column(columnDefinition = "TEXT")
    private String aboutDescription;

    private String ownerName;
    private String contactEmail;
    private String contactPhone;
    private String address;

    // Social links
    private String facebookUrl;
    private String instagramUrl;
    private String twitterUrl;
}
