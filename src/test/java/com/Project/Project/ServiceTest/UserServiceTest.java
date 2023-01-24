package com.Project.Project.ServiceTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.Project.Project.Business.UserService;
import com.Project.Project.DataAcess.Repositorys.UserInterface;
import com.Project.Project.DataAcess.User;
import com.Project.Project.ResponseHandler.ResponseJSONhandler;

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
import org.springframework.http.ResponseEntity;
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
    public void listUniqClientTest() {
        var user = mock(User.class);

        Mockito.when(userInterface.findById(ArgumentMatchers.eq(user.getId())))
                .thenReturn(Optional.of(user));

        User userTest = userService.listUniqClient(user.getId());
        assertEquals(user.getId(), userTest.getId());
    }

    @Test
    public void saveTest() {
        var user = mock(User.class);
        Mockito.when(userInterface.save(ArgumentMatchers.eq(user)))
                .thenReturn(user);

        Object testUser = userService.save(user);

        assertEquals(user, testUser);

    }

    @Test
    public void listClientsTest() {
        var user = mock(User.class);
        Mockito.when(userInterface.findAll()).thenReturn(List.of(user));
    }

    @org.junit.jupiter.api.Test
    public void verifyEmailCpfNameSenha() {


        //----------------Teste OK---------------

        ResponseEntity response = userService.verifyEmailCpfNameSenha(usuarioMockadoValido.getEmail(),
                usuarioMockadoValido.getCpf(), usuarioMockadoValido.getName(), usuarioMockadoValido.getSenha(), usuarioMockadoValido);
        ResponseJSONhandler responseJSONhandler = new ResponseJSONhandler("201", "Cadastrado", HttpStatus.CREATED);
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(responseJSONhandler), response);

        // ----------------Teste CPF INVALIDO-----------------------


      ResponseEntity response2 = userService.verifyEmailCpfNameSenha(usuarioMockadoCpfInvalido.getEmail(),
              usuarioMockadoCpfInvalido.getCpf(), usuarioMockadoCpfInvalido.getName(), usuarioMockadoCpfInvalido.getSenha(), usuarioMockadoCpfInvalido );
      ResponseJSONhandler responseJSONhandler2 = new ResponseJSONhandler("400", "CPF INVALIDO", HttpStatus.BAD_REQUEST);
      assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJSONhandler2), response2);

      //---------------------Teste Usuario Invalido-------------------


      ResponseEntity response3 = userService.verifyEmailCpfNameSenha(usuarioMockadoUserInvalido.getEmail(),
              usuarioMockadoUserInvalido.getCpf(), usuarioMockadoUserInvalido.getName(), usuarioMockadoUserInvalido.getSenha(), usuarioMockadoUserInvalido );
      ResponseJSONhandler responseJSONhandler3 = new ResponseJSONhandler("400", "USUARIO OU EMAIL OU SENHA INVALIDOS", HttpStatus.BAD_REQUEST);
      assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJSONhandler3), response3);
     }
    @Test
    public void findbyEmailTest(){
        var user = mock(User.class);
        Mockito.when(userInterface.findByEmail(ArgumentMatchers.eq(user.getEmail())))
                .thenReturn(user);
    }
    @Test
    public void deleteTest(){
        var user = mock(User.class);
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () ->
                userService.delete(user.getId()));
        Assertions.assertThat(responseStatusException.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    public void updateByIdTest(){
        var user = mock(User.class);

        User res = userService.updateById(user.getId(), user);

        assertEquals(user, res);

    }
}
