-- Script para criar a tabela "usuarios"
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL
);


Select * From usuarios;


INSERT INTO usuarios (email) VALUES ('usuario@hotmail.com');

SELECT * FROM usuarios
WHERE email = 'emai@examplo.com';


