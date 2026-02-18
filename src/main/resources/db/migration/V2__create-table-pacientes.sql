CREATE TABLE pacientes (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documento_identidad VARCHAR(13) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    barrio VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(12) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    estado VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL
);