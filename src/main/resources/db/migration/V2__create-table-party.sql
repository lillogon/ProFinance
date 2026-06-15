CREATE TABLE party (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    description VARCHAR(200) NOT NULL,
    document_number VARCHAR(14),
    person_type CHAR(2),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    blocked_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    category_id UUID,
    CONSTRAINT fk_party_category FOREIGN KEY (category_id)
        REFERENCES public.category (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);