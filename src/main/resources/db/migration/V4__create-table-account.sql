CREATE TABLE account (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    transaction_type CHAR(1) NOT NULL,
    status CHAR(2) NOT NULL,
    payment_method CHAR(2) NOT NULL,
    installments INTEGER NOT NULL,
    total_amount NUMERIC(15,2) NOT NULL,
    discount_amount NUMERIC(15,2),
    amount NUMERIC(15,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    settled_at TIMESTAMP,
    canceled_at TIMESTAMP,
    updated_at TIMESTAMP NOT NULL,
    institution_id UUID NOT NULL,
    party_id UUID NOT NULL,
    category_id UUID,
    CONSTRAINT fk_account_institution FOREIGN KEY (institution_id)
        REFERENCES institution(id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    CONSTRAINT fk_account_party FOREIGN KEY (party_id)
        REFERENCES party(id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    CONSTRAINT fk_account_category FOREIGN KEY (category_id)
        REFERENCES category(id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);