CREATE TABLE transaction_log (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    installment_number INTEGER NOT NULL,
    event_type CHAR(2) NOT NULL,
    amount NUMERIC(15,2) NOT NULL,
    event_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    account_id UUID NOT NULL,

    CONSTRAINT fk_transaction_account FOREIGN KEY (account_id)
        REFERENCES account(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);