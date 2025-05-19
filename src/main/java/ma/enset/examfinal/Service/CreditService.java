package ma.enset.examfinal.Service;

import ma.enset.examfinal.Dto.CreditDTO;
import ma.enset.examfinal.Dto.RemboursementDTO;
import ma.enset.examfinal.Enums.StatutCredit;

import java.util.List;

public interface CreditService {
    CreditDTO createCredit(CreditDTO creditDTO);
    CreditDTO getCreditById(Long id);
    List<CreditDTO> getAllCredits();
    CreditDTO updateCredit(Long id, CreditDTO creditDTO);
    void deleteCredit(Long id);
    List<CreditDTO> getCreditsByStatus(StatutCredit statut);
    CreditDTO changeCreditStatus(Long id, StatutCredit newStatus);
    List<RemboursementDTO> getCreditRemboursements(Long creditId);
    Double calculateTotalRemboursements(Long creditId);
}