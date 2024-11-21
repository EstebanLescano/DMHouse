CREATE TABLE user (
    user_id SERIAL PRIMARY KEY,             -- Identificador único auto-incremental
    user_name VARCHAR(255) NOT NULL,        -- Nombre de usuario
    password VARCHAR(255) NOT NULL,         -- Contraseña
    email VARCHAR(255) UNIQUE NOT NULL,     -- Correo electrónico (único)
    date_login TIMESTAMP,                   -- Fecha y hora del último inicio de sesión
    person_id INTEGER,                      -- Clave foránea hacia la tabla person
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE
);


CREATE TABLE person (
    person_id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    country_id INTEGER,
    type_documen_id INTEGER,
    documen VARCHAR(255),
    fecha_nacimiento DATE
);

CREATE TABLE wallet (
    wallet_id SERIAL PRIMARY KEY,           -- Identificador único auto-incremental
    person_id INTEGER,                      -- Clave foránea hacia la tabla person
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE
);


CREATE TABLE acount (
    acount_id SERIAL PRIMARY KEY,         -- Identificador único auto-incremental
    balance NUMERIC(19, 2),                 -- Saldo con precisión para manejar decimales
    money VARCHAR(255),                  -- Moneda representada como texto
    wallet_id INTEGER,                    -- Clave foránea hacia la tabla wallet
    CONSTRAINT fk_wallet FOREIGN KEY (wallet_id) REFERENCES wallet(wallet_id) ON DELETE CASCADE
);

CREATE TABLE transaction (
    transaction_id SERIAL PRIMARY KEY,                -- Identificador único auto-incremental
    acount_id INTEGER,                                -- Clave foránea hacia la tabla acount
    date_transaction TIMESTAMP,                      -- Fecha y hora de la transacción
    state_id INTEGER,                                 -- Estado de la transacción
    amount NUMERIC(19, 2),                            -- Monto de la transacción
    currency VARCHAR(255),                            -- Moneda utilizada
    transaction_type VARCHAR(255),                   -- Tipo de transacción (como enum representado por texto)
    transaction_concept VARCHAR(255),                -- Concepto de la transacción (enum como texto)
    details TEXT,                                     -- Detalles adicionales de la transacción
    from_user_id INTEGER,                             -- ID del usuario remitente
    to_user_id INTEGER,                               -- ID del usuario destinatario
    from_account_id INTEGER,                          -- ID de la cuenta remitente
    to_account_id INTEGER,                            -- ID de la cuenta destinataria
    CONSTRAINT fk_acount FOREIGN KEY (acount_id) REFERENCES acount(acount_id) ON DELETE CASCADE
);



