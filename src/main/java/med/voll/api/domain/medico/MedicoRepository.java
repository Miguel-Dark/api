package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
     Page<Medico> findAllByActivoTrue(Pageable paginacion);

     @Query("""
             SELECT medico FROM Medico medico
             WHERE medico.activo = 1
             AND
             medico.especialidad = :especialidad
             AND medico.id NOT IN(
                SELECT consulta.medico.id FROM  Consulta consulta WHERE consulta.fecha = :fecha
             )
             ORDER BY  RAND()
             LIMIT 1
             """)
    Medico elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad especialidad, @NotNull @Future LocalDateTime fecha);
}
