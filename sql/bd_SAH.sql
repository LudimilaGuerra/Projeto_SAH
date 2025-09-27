-- Banco de dados Sistema de Alimentação Hospitalar

CREATE DATABASE SAH;
USE SAH;


CREATE TABLE Usuario (
    ID_User INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Idade INT,
    Login VARCHAR(50) UNIQUE NOT NULL,
    Senha VARCHAR(255) NOT NULL,
    Tipo ENUM('Paciente','Nutricionista','Cozinha','Administrador','Secretario') NOT NULL
);


CREATE TABLE Quarto (
    ID_Quarto INT AUTO_INCREMENT PRIMARY KEY,
    Numero INT NOT NULL,
    Ala VARCHAR(50),
    Andar INT
);


CREATE TABLE Paciente (
    ID_Paciente INT AUTO_INCREMENT PRIMARY KEY,
    ID_User INT UNIQUE,
    Restricao_Alimentar VARCHAR(255),
    Alergias VARCHAR(255),
    Preferencias VARCHAR(255),
    ID_Quarto INT,
    FOREIGN KEY (ID_User) REFERENCES Usuario(ID_User),
    FOREIGN KEY (ID_Quarto) REFERENCES Quarto(ID_Quarto)
);

CREATE TABLE Nutricionista (
    ID_Nutricionista INT AUTO_INCREMENT PRIMARY KEY,
    ID_User INT UNIQUE,
    Turno VARCHAR(50),
    FOREIGN KEY (ID_User) REFERENCES Usuario(ID_User)
);

CREATE TABLE Cozinha (
    ID_Cozinha INT AUTO_INCREMENT PRIMARY KEY,
    ID_User INT UNIQUE,
    Turno VARCHAR(50),
    FOREIGN KEY (ID_User) REFERENCES Usuario(ID_User)
);

CREATE TABLE Administrador (
    ID_Adm INT AUTO_INCREMENT PRIMARY KEY,
    ID_User INT UNIQUE,
    FOREIGN KEY (ID_User) REFERENCES Usuario(ID_User)
);


CREATE TABLE Refeicao (
    ID_Refeicao INT AUTO_INCREMENT PRIMARY KEY,
    ID_Paciente INT NOT NULL,
    ID_Cozinha INT NOT NULL,
    Data DATE NOT NULL,
    Horario ENUM('Café da Manhã','Almoço','Jantar','Lanche') NOT NULL,
    Status ENUM('Planejada','Preparada','Entregue','Recusada') DEFAULT 'Planejada',
    Feedback VARCHAR(255),
    FOREIGN KEY (ID_Paciente) REFERENCES Paciente(ID_Paciente),
    FOREIGN KEY (ID_Cozinha) REFERENCES Cozinha(ID_Cozinha)
);



CREATE TABLE Opcao_Alimentar (
    ID_Op INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Categoria VARCHAR(50),
    Descricao VARCHAR(255)
);



CREATE TABLE Cardapio (
    ID_Cardapio INT AUTO_INCREMENT PRIMARY KEY,
    ID_Nutricionista INT NOT NULL,
    Periodo VARCHAR(50),
    Turno ENUM('Café da Manhã','Almoço','Jantar','Lanche'),
    Data_Inicio DATE,
    Data_Fim DATE,
    FOREIGN KEY (ID_Nutricionista) REFERENCES Nutricionista(ID_Nutricionista)
);


-- RELACIONAMENTOS N:N

CREATE TABLE Cardapio_Opcao (
    ID_Cardapio INT,
    ID_Op INT,
    PRIMARY KEY (ID_Cardapio, ID_Op),
    FOREIGN KEY (ID_Cardapio) REFERENCES Cardapio(ID_Cardapio),
    FOREIGN KEY (ID_Op) REFERENCES Opcao_Alimentar(ID_Op)
);

CREATE TABLE Refeicao_Opcao (
    ID_Refeicao INT,
    ID_Op INT,
    PRIMARY KEY (ID_Refeicao, ID_Op),
    FOREIGN KEY (ID_Refeicao) REFERENCES Refeicao(ID_Refeicao),
    FOREIGN KEY (ID_Op) REFERENCES Opcao_Alimentar(ID_Op)
);


CREATE TABLE Despesas (
    ID_Gasto INT AUTO_INCREMENT PRIMARY KEY,
    ID_User INT NOT NULL,
    Valor DECIMAL(10,2) NOT NULL,
    Data DATE NOT NULL,
    TipoGasto VARCHAR(100),
    FOREIGN KEY (ID_User) REFERENCES Usuario(ID_User)
);


CREATE TABLE Estoque (
    ID_Item INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Quantidade DECIMAL(10,2) NOT NULL,
    Unidade VARCHAR(20),
    Validade DATE,
    ID_Cozinha INT,
    FOREIGN KEY (ID_Cozinha) REFERENCES Cozinha(ID_Cozinha)
);


-- INSERÇÕES PARA EXEMPLO
INSERT INTO Usuario (Nome, Idade, Login, Senha, Tipo) VALUES
('João Silva', 35, 'joaos', '123456', 'Paciente'),
('Maria Souza', 29, 'marias', '654321', 'Paciente'),
('Carlos Lima', 42, 'carlosnutri', 'abc123', 'Nutricionista'),
('Ana Paula', 31, 'anacozinha', 'cozinha123', 'Cozinha'),
('Pedro Santos', 40, 'pedroadm', 'adm123', 'Administrador');


INSERT INTO Quarto (Numero, Ala, Andar) VALUES
(101, 'A', 1),
(202, 'B', 2);

INSERT INTO Paciente (ID_User, Restricao_Alimentar, Alergias, Preferencias, ID_Quarto) VALUES
(1, 'Dieta Hipossódica', 'Nenhuma', 'Prefere frango', 1),
(2, 'Dieta Hipocalórica', 'Lactose', 'Prefere peixe', 2);

INSERT INTO Nutricionista (ID_User, Turno) VALUES
(3, 'Manhã');

INSERT INTO Cozinha (ID_User, Turno) VALUES
(4, 'Integral');


INSERT INTO Administrador (ID_User) VALUES
(5);


INSERT INTO Opcao_Alimentar (Nome, Categoria, Descricao) VALUES
('Arroz Integral', 'Cereal', 'Arroz integral cozido'),
('Frango Grelhado', 'Proteína', 'Peito de frango grelhado'),
('Peixe Assado', 'Proteína', 'Peixe temperado assado');


INSERT INTO Cardapio (ID_Nutricionista, Periodo, Turno, Data_Inicio, Data_Fim) VALUES
(1, 'Semanal', 'Almoço', '2025-09-15', '2025-09-21');


INSERT INTO Cardapio_Opcao (ID_Cardapio, ID_Op) VALUES
(1, 1),
(1, 2),
(1, 3);


INSERT INTO Refeicao (ID_Paciente, ID_Cozinha, Data, Horario, Status, Feedback) VALUES
(1, 1, '2025-09-19', 'Almoço', 'Entregue', 'Gostou da refeição'),
(2, 1, '2025-09-19', 'Almoço', 'Entregue', 'Achou sem sal');


INSERT INTO Refeicao_Opcao (ID_Refeicao, ID_Op) VALUES
(1, 1), (1, 2),
(2, 1), (2, 3);


INSERT INTO Estoque (Nome, Quantidade, Unidade, Validade, ID_Cozinha) VALUES
('Arroz Integral', 50, 'Kg', '2025-12-31', 1),
('Frango', 30, 'Kg', '2025-09-30', 1);


INSERT INTO Despesas (ID_User, Valor, Data, TipoGasto) VALUES
(5, 1200.50, '2025-09-10', 'Compra de alimentos'),
(5, 800.00, '2025-09-15', 'Manutenção de equipamentos');
