package com.healthcarepro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

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
public class Patient {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;
    
    @NotNull
    @Digits(integer = 3, fraction = 1)
    private Integer weight;
    
    @NotNull
    @Past
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dob;
    
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
    @Column(nullable = false)
    private String phone;
    
    @NotNull
    @Column(nullable = false)
    @Email
    private String email;
}
