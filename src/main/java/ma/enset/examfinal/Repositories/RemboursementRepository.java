package ma.enset.examfinal.Repositories;

import ma.enset.examfinal.Entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.enset.examfinal.Enums.TypeRemboursement;

import java.util.List;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCreditId(Long creditId);

    Double calculateTotalRemboursementByCreditId(Long creditId);

    List<Remboursement> findByType(TypeRemboursement type);
}