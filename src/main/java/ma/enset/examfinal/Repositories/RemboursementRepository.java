package ma.enset.examfinal.Repositories;

import ma.enset.examfinal.Entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.enset.examfinal.Enums.TypeRemboursement;

import java.util.Arrays;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    Arrays findByCreditId(Long creditId);

    Double calculateTotalRemboursementByCreditId(Long creditId);

    Arrays findByType(TypeRemboursement type);
}