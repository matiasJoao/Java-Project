package com.Project.Project.ServiceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

import com.Project.Project.Business.UserService;
import com.Project.Project.DataAcess.Repositorys.UserInterface;
import com.Project.Project.DataAcess.User;
import com.Project.Project.ResponseHandler.ResponseJSONhandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserInterface userInterface;

    @Test
    public void listUniqClientTest(){
       var user = mock(User.class);

       Mockito.when(userInterface.findById(ArgumentMatchers.eq(user.getId())))
               .thenReturn(Optional.of(user));

     Optional<User> userTest = userService.ListUniqClient(user.getId());
     assertEquals(user.getId(), userTest.get().getId());
    }
    @Test
    public void saveTest(){
        var user = mock(User.class);
        Mockito.when(userInterface.save(ArgumentMatchers.eq(user)))
                .thenReturn(user);

        Object testUser = userService.save(user);

        assertEquals(user, testUser);

    }
    @Test
    public void listClientsTest(){
        var user = mock(User.class);
        Mockito.when(userInterface.findAll()).thenReturn(List.of(user));
    }

  @Test
  public void verifyEmailCpfNameSenha(){

    //----------------Teste OK---------------

      User user = new User();
      user.setEmail("mateu9610@uorak.com");
      user.setCpf("74468563001");
      user.setName("jao");
      user.setSenha("12345678aA@");

    ResponseEntity response = userService.verifyEmailCpfNameSenha(user.getEmail(),
            user.getCpf(), user.getName(), user.getSenha(), user );
      ResponseJSONhandler responseJSONhandler = new ResponseJSONhandler("201", "Cadastrado", HttpStatus.CREATED);
    assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(responseJSONhandler), response);

    // ----------------Teste CPF INVALIDO-----------------------


      User user2 = new User();
      user.setEmail("mateu9610@uorak.com");
      user.setCpf("7446856300");
      user.setName("jao");
      user.setSenha("12345678aA@");
      ResponseEntity response2 = userService.verifyEmailCpfNameSenha(user.getEmail(),
              user.getCpf(), user.getName(), user.getSenha(), user );
      ResponseJSONhandler responseJSONhandler2 = new ResponseJSONhandler("400", "CPF INVALIDO", HttpStatus.BAD_REQUEST);
      assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJSONhandler2), response2);

      //---------------------Teste Usuario Invalido-------------------

      User user3 = new User();
      user.setEmail("mateu9610@uorak.com");
      user.setCpf("74468563001");
      user.setName("jao123");
      user.setSenha("12345678aA@");
      ResponseEntity response3 = userService.verifyEmailCpfNameSenha(user.getEmail(),
              user.getCpf(), user.getName(), user.getSenha(), user );
      ResponseJSONhandler responseJSONhandler3 = new ResponseJSONhandler("400", "USUARIO OU EMAIL OU SENHA INVALIDOS", HttpStatus.BAD_REQUEST);
      assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJSONhandler3), response3);
  }


}
