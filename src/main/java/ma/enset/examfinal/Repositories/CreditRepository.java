package ma.enset.examfinal.Repositories;

import ma.enset.examfinal.Entities.Credit;
import ma.enset.examfinal.Enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClientId(Long clientId);

}