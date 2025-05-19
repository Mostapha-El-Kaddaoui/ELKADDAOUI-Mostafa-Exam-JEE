package ma.enset.examfinal.Repositories;

import ma.enset.examfinal.Entities.Credit;
import ma.enset.examfinal.Enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    Arrays findByClientId(Long clientId);

    Arrays findByStatut(StatutCredit statut);
}