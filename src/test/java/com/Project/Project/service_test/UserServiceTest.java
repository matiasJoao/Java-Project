package com.Project.Project.service_test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.Project.Project.business.UserService;
import com.Project.Project.data_acess.repositorys.UserInterface;
import com.Project.Project.data_acess.User;
import com.Project.Project.dto.ResponseDTO;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class UserServiceTest {
    User usuarioMockadoValido;
    User usuarioMockadoCpfInvalido;
    User usuarioMockadoUserInvalido;

    User user = mock(User.class);



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioMockadoValido = new User();

        usuarioMockadoValido.setEmail("mateu9610@uorak.com");
        usuarioMockadoValido.setCpf("74468563001");
        usuarioMockadoValido.setName("jao");
        usuarioMockadoValido.setSenha("12345678aA@");


        usuarioMockadoCpfInvalido = new User();

        usuarioMockadoCpfInvalido.setEmail("mateu9610@uorak.com");
        usuarioMockadoCpfInvalido.setCpf("7446856300");
        usuarioMockadoCpfInvalido.setName("jao");
        usuarioMockadoCpfInvalido.setSenha("12345678aA@");


        usuarioMockadoUserInvalido = new User();

        usuarioMockadoUserInvalido.setEmail("mateu9610@uorak.com");
        usuarioMockadoUserInvalido.setCpf("74468563001");
        usuarioMockadoUserInvalido.setName("jao123");
        usuarioMockadoUserInvalido.setSenha("12345678aA@");


    }

    @Autowired
    UserService userService;

    @MockBean
    UserInterface userInterface;


    @Test
    public void testListUniqClient() {

        Mockito.when(userInterface.findById(ArgumentMatchers.eq(user.getId())))
                .thenReturn(Optional.of(user));

        User userTest = userService.listUniqClient(user.getId());
        assertEquals(user.getId(), userTest.getId());
    }

    @Test
    public void testSave() {
        Mockito.when(userInterface.save(ArgumentMatchers.eq(user)))
                .thenReturn(user);

        Object testUser = userService.save(user);
        assertEquals(user, testUser);


    }

    @Test
    public void testListClients() {
        var user = mock(User.class);
        Mockito.when(userInterface.findAll()).thenReturn(List.of(user));
    }

    @org.junit.jupiter.api.Test
    public void testVerifyEmailCpfNameSenha() {



        //----------------Teste OK---------------

        ResponseDTO response = userService.verifyEmailCpfNameSenha(usuarioMockadoValido);
        ResponseDTO responseDTO = new ResponseDTO("201", "Cadastrado", HttpStatus.CREATED);
        assertEquals(responseDTO, response);

        // ----------------Teste CPF INVALIDO-----------------------


        ResponseDTO response2 = userService.verifyEmailCpfNameSenha(usuarioMockadoCpfInvalido );
      ResponseDTO responseDTO2 = new ResponseDTO("400", "CPF INVALIDO", HttpStatus.BAD_REQUEST);
      assertEquals(responseDTO2, response2);

      //---------------------Teste Usuario Invalido-------------------


        ResponseDTO response3 = userService.verifyEmailCpfNameSenha( usuarioMockadoUserInvalido );
      ResponseDTO responseDTO3 = new ResponseDTO("400", "USUARIO OU EMAIL OU SENHA INVALIDOS", HttpStatus.BAD_REQUEST);
      assertEquals(responseDTO3, response3);
     }
    @Test
    public void testFindbyEmail(){
//       User user1 = testEntityManager.persist( new User(1L, null, "Joao", "joao@email", "123", "013"));
//
//       User user2 = userInterface.findByEmail(user1.getEmail());
//       assertEquals(user1, user2);
        Mockito.when(userInterface.findByEmail(ArgumentMatchers.eq(user.getEmail())))
                .thenReturn(user);
    }
    @Test
    public void testDelete(){

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () ->
                userService.delete(user.getId()));
        Assertions.assertThat(responseStatusException.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setEmail("mateu9610@uorak.com");
        user.setCpf("74468563001");
        user.setName("jao");
        user.setSenha("12345678aA@");
        Mockito.when(userInterface.save(user)).thenReturn(user);
        User res = userService.updateById(user);
        assertEquals(user, res);
    }

    @Test
    public void testUpdateEmail(){
            Mockito.when(userInterface.findById(user.getId())).thenReturn(Optional.of(user));
            Mockito.when(userInterface.save(user)).thenReturn(user);
            User res = userService.updateEmail(user.getId(), user.getEmail());
            assertEquals(user, res);
    }


}

