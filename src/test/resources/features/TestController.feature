# language: pt

Funcionalidade: Testando End-Points do Controller
  Cenario: Cadastro de usuario
    Dado Que eu tenho um usuario qualquer com role de admin
    Quando Faco um requisicao de cadastro
    Entao Devo receber o status "201"

