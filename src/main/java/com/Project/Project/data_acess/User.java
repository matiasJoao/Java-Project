package com.Project.Project.data_acess;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
        @JoinColumn(name = "depart_id", nullable = false)
        private Depart depart;

        @Column(name = "tb_nome", nullable = false)
        @NotBlank
        private String name;

        @Column(name = "tb_email", nullable = false)
        @NotBlank
        private String email;

        @Column(name = "tb_senha", nullable = false)
        @NotBlank
        private String senha;

        @Column(name = "tb_cpf", nullable = false)
        @NotBlank
        private String cpf;

}
