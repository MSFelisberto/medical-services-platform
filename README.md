Para realizar testes de segurança:


Usuarios:

L:paciente@email.com
S:senha123

L:admin@email.com
S:senha123

L:enfermeiro@email.com
S:senha123

L:medico@email.com
S:senha123


Para realizar o login e pegar o token JWT
```
curl --location 'http://localhost:8080/autenticacao/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "paciente@email.com",
    "senha": "senha123"
}'
```

