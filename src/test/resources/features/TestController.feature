# language: pt

  Funcionalidade: Testando End-Points do Controller
    @PostControllerTest
     Cenario: Cadastro de usuario
      Dado : Que eu dou um post na api com o valor do usuario
      Entao : Retorna o status "401"