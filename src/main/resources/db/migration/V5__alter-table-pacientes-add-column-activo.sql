ALTER TABLE pacientes ADD COLUMN activo BOOLEAN;
UPDATE pacientes SET activo = TRUE;
ALTER TABLE pacientes ALTER COLUMN activo SET NOT NULL;