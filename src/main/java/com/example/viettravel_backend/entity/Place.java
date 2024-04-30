package com.example.viettravel_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "places")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "description", columnDefinition = "TEXT")
    @Lob
    private String description;

    @Column(name = "price")
    @NotNull
    private Long price;

    @Column(name = "image_url")
    @NotNull
    private String imageUrl;

    @OneToMany(mappedBy = "place")
    private Set<Favorite> favorites;

    @OneToMany(mappedBy = "place")
    private Set<PlaceImage> placeImages;

    @ManyToMany(mappedBy = "places")
    @JsonIgnore
    private Set<Ticket> tickets = new HashSet<>();
}
