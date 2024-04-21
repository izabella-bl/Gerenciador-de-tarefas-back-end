-- Script para criar a tabela "tarefas"
CREATE TABLE tarefas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    situacao VARCHAR(50),
    usuario_id INTEGER REFERENCES usuarios(id)
);

Select * From tarefas;

INSERT INTO tarefas (titulo, descricao, situacao, usuario_id)
VALUES ('Nova Tarefa', 'Descrição da nova tarefa', 'Em Andamento', 3);

SELECT * FROM tarefas
WHERE id = 7;

UPDATE tarefas
SET titulo = 'Novo título', descricao = 'Nova descrição', situacao = 'Nova situação'
WHERE id = id_da_tarefa AND usuario_id = id_do_usuario;

DELETE FROM tarefas
WHERE id = 1 AND usuario_id = 1;
