CREATE TABLE transaction_log (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    installments INTEGER NOT NULL,
    event_type CHAR(2),
    amount NUMERIC(15,2) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    account_id UUID,

    CONSTRAINT fk_transaction_account FOREIGN KEY (account_id)
        REFERENCES account(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);