package com.Project.Project.DataAcess;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;


        @ManyToOne
        @JoinColumn(name = "depart_id")
        private Depart depart;

        @Column(name = "tb_nome", nullable = false)
        private String name;

        @Column(name = "tb_email", nullable = false)
        private String email;

        @Column(name = "tb_senha")
        private String senha;

        @Column(name = "tb_cpf", unique=true)
        private String cpf;

}
