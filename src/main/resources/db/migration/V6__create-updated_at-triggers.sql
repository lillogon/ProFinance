CREATE OR REPLACE FUNCTION update_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_category_updated_at
BEFORE UPDATE ON category
FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TRIGGER trigger_party_updated_at
BEFORE UPDATE ON party
FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TRIGGER trigger_institution_updated_at
BEFORE UPDATE ON institution
FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TRIGGER trigger_account_updated_at
BEFORE UPDATE ON account
FOR EACH ROW EXECUTE FUNCTION update_updated_at();