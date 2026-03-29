CREATE TABLE party (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    description VARCHAR(200) NOT NULL,
    document_number VARCHAR(14),
    person_type CHAR(1),
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    blocked_at TIMESTAMP,
    updated_at TIMESTAMP NOT NULL,
    category_id UUID,
    CONSTRAINT fk_party_category FOREIGN KEY (category_id)
        REFERENCES public.category (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);