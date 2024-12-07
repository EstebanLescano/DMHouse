CREATE TABLE public.person (
                               person_id SERIAL PRIMARY KEY,
                               name VARCHAR(255),
                               last_name VARCHAR(255),
                               dni VARCHAR(255) UNIQUE NOT NULL,
                               country_id INTEGER,
                               type_document_id INTEGER,
                               email VARCHAR(255),
                               phone VARCHAR(255),
                               birthdate DATE
);

CREATE TABLE public.client (
                               client_id SERIAL PRIMARY KEY,
                               email VARCHAR(255) UNIQUE NOT NULL,
                               password VARCHAR(255) NOT NULL,
                               date_login TIMESTAMP,
                               person_id INTEGER NOT NULL,
                               CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES public.person(person_id) ON DELETE CASCADE
);

CREATE TABLE public.wallet (
                               wallet_id SERIAL PRIMARY KEY,
                               client_id INTEGER NOT NULL,
                               CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES public.client(client_id) ON DELETE CASCADE
);


CREATE TABLE public.account (
                                account_id SERIAL PRIMARY KEY,
                                currency VARCHAR(255) NOT NULL,
                                balance NUMERIC(19, 2) NOT NULL,
                                wallet_id INTEGER NOT NULL,
                                CONSTRAINT fk_wallet FOREIGN KEY (wallet_id) REFERENCES public.wallet(wallet_id) ON DELETE CASCADE
);

CREATE TABLE public.transaction (
                                    transaction_id SERIAL PRIMARY KEY,
                                    from_account_id INTEGER NOT NULL,
                                    to_account_id INTEGER,
                                    transaction_date TIMESTAMP NOT NULL,
                                    transaction_type VARCHAR(255) NOT NULL,
                                    transaction_concept VARCHAR(255) NOT NULL,
                                    status VARCHAR(255) NOT NULL,
                                    amount NUMERIC(19, 2) NOT NULL,
                                    currency VARCHAR(255) NOT NULL,
                                    from_user_id INTEGER,
                                    to_user_id INTEGER,
                                    details TEXT,
                                    CONSTRAINT fk_from_account FOREIGN KEY (from_account_id) REFERENCES public.account(account_id) ON DELETE CASCADE,
                                    CONSTRAINT fk_to_account FOREIGN KEY (to_account_id) REFERENCES public.account(account_id) ON DELETE CASCADE,
                                    CONSTRAINT fk_from_user FOREIGN KEY (from_user_id) REFERENCES public.client(client_id) ON DELETE CASCADE,
                                    CONSTRAINT fk_to_user FOREIGN KEY (to_user_id) REFERENCES public.client(client_id) ON DELETE CASCADE
);

-- Crear tipo ENUM para TransactionType
CREATE TYPE public.transaction_type_enum AS ENUM ('outgoing', 'incoming');

-- Crear tipo ENUM para TransactionConcept
CREATE TYPE public.transaction_concept_enum AS ENUM ('LOAD', 'SEND');

-- Crear tipo ENUM para StatusTransaction
CREATE TYPE public.status_transaction_enum AS ENUM ('PENDING', 'SEND', 'RECEIVED', 'EXECUTED', 'MISSING_FUNDS', 'GENERAL_ERROR');

ALTER TABLE public.transaction
ALTER COLUMN transaction_type SET DATA TYPE public.transaction_type_enum USING transaction_type::public.transaction_type_enum;

ALTER TABLE public.transaction
ALTER COLUMN transaction_concept SET DATA TYPE public.transaction_concept_enum USING transaction_concept::public.transaction_concept_enum;

ALTER TABLE public.transaction
ALTER COLUMN status SET DATA TYPE public.status_transaction_enum USING status::public.status_transaction_enum;
/**
Relaciones entre las tablas
Las relaciones entre las tablas parecen estar correctamente implementadas usando las claves foráneas (FOREIGN KEY) y
           las referencias adecuadas. Las tablas client, wallet, account y transaction tienen sus respectivas relaciones con las otras tablas.

Por ejemplo:

La relación entre client y person se asegura con la clave foránea person_id.
La relación entre wallet y client con la clave foránea client_id.
La relación entre account y wallet con la clave foránea wallet_id.
Las relaciones de las transacciones (transaction) con las cuentas de origen y destino también se han tratado correctamente.
3. Enum en PostgreSQL
Los tipos ENUM para transaction_type, transaction_concept y status se crearon correctamente. También se configuraron bien las columnas
           de la tabla transaction para hacer uso de esos tipos de datos ENUM.**/