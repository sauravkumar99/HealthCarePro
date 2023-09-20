package com.healthcarepro.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_name_address", columnNames = {"name", "address"}),
        @UniqueConstraint(name = "UK_email", columnNames = {"email"}),
        @UniqueConstraint(name = "UK_phone", columnNames = {"phone"})
})
public class Doctor {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;
    
    @NotNull
    @Size(min = 1 , max = 100)
    private String address;
    
    @NotNull
    private String city;
    
    @NotNull
    private String state;
    
    @NotNull
    private String country;
    
    @NotNull
    private String pinCode;
    
    @NotNull
    @PositiveOrZero
    private Integer experience;
    
    @NotNull
    private String specialization;
    
    @NotNull
    private String degree;
    
    @NotNull
    @Column(nullable = false)
    private String phone;
    
    @NotNull
    @Column(nullable = false)
    @Email
    private String email;
    
    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "doctor")
    private List<TimeSlot> timeSlots = new ArrayList<>();
}
