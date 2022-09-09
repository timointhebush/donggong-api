ALTER TABLE account ADD created_at timestamp DEFAULT now();
ALTER TABLE account ADD updated_at timestamp DEFAULT now();