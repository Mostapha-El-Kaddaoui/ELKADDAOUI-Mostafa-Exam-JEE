package ma.enset.examfinal.Service;

import ma.enset.examfinal.Dto.RemboursementDTO;
import ma.enset.examfinal.Enums.TypeRemboursement;

import java.util.List;

public interface RemboursementService {
    RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO);
    RemboursementDTO getRemboursementById(Long id);
    List<RemboursementDTO> getAllRemboursements();
    RemboursementDTO updateRemboursement(Long id, RemboursementDTO remboursementDTO);
    void deleteRemboursement(Long id);
    List<RemboursementDTO> getRemboursementsByCredit(Long creditId);
    List<RemboursementDTO> getRemboursementsByType(TypeRemboursement type);
}
