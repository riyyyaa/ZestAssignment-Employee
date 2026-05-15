package com.zest.employee_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String department;

    private Double salary;

    private String position;

    private LocalDate dateOfJoining;
}