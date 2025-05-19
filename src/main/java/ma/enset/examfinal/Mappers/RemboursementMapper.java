package ma.enset.examfinal.Mappers;

import ma.enset.examfinal.Dto.RemboursementDTO;
import ma.enset.examfinal.Entities.Credit;
import ma.enset.examfinal.Entities.Remboursement;
import ma.enset.examfinal.Repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemboursementMapper {
    @Autowired
    private CreditRepository creditRepository;

    public RemboursementDTO toDto(Remboursement remboursement) {
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        dto.setCreditId(remboursement.getCredit().getId());
        return dto;
    }

    public Remboursement toEntity(RemboursementDTO dto) {
        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(dto.getType());

        if (dto.getCreditId() != null) {
            Credit credit = creditRepository.findById(dto.getCreditId())
                    .orElseThrow(() -> new RuntimeException("Credit not found"));
            remboursement.setCredit(credit);
        }

        return remboursement;
    }
}
