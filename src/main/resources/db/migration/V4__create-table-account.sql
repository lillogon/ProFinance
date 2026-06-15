CREATE TABLE account (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    transaction_type CHAR(2) NOT NULL,
    status CHAR(2) NOT NULL,
    payment_method CHAR(2) NOT NULL,
    installments INTEGER NOT NULL,
    total_amount NUMERIC(15,2) NOT NULL,
    discount_amount NUMERIC(15,2),
    amount NUMERIC(15,2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    settled_at TIMESTAMP WITH TIME ZONE,
    canceled_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
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