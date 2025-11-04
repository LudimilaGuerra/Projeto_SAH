USE SAH;

-- 1) Usuários
SELECT ID_User, Nome, Idade, Tipo
FROM Usuario
ORDER BY Nome;

-- 2) Pacientes com seus quartos
SELECT p.ID_Paciente, u.Nome AS Paciente, q.Numero AS Quarto, q.Ala, q.Andar
FROM Paciente p
JOIN Usuario u ON u.ID_User = p.ID_User
JOIN Quarto  q ON q.ID_Quarto = p.ID_Quarto
ORDER BY q.Andar, q.Numero;

-- 3) Refeições com paciente e responsável da cozinha
SELECT r.ID_Refeicao, r.Data, r.Horario, r.Status,
       u.Nome AS Paciente, uz.Nome AS ResponsavelCozinha
FROM Refeicao r
JOIN Paciente p  ON p.ID_Paciente = r.ID_Paciente
JOIN Usuario  u  ON u.ID_User = p.ID_User
JOIN Cozinha  cz ON cz.ID_Cozinha = r.ID_Cozinha
JOIN Usuario  uz ON uz.ID_User = cz.ID_User
ORDER BY r.Data DESC, r.Horario;

-- 4) Opções servidas em cada refeição
SELECT r.ID_Refeicao, r.Data, r.Horario, oa.Nome AS Opcao, oa.Categoria
FROM Refeicao r
JOIN Refeicao_Opcao ro ON ro.ID_Refeicao = r.ID_Refeicao
JOIN Opcao_Alimentar oa ON oa.ID_Op = ro.ID_Op
ORDER BY r.ID_Refeicao, oa.Nome;

-- 5) Despesa total em setembro/2025
SELECT TipoGasto, SUM(Valor) AS total_mes
FROM Despesas
WHERE Data >= '2025-09-01' AND Data < '2025-10-01'
GROUP BY TipoGasto
ORDER BY total_mes DESC;