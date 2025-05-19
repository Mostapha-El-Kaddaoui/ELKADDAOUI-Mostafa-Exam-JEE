package ma.enset.examfinal.Service;

import jakarta.transaction.Transactional;
import ma.enset.examfinal.Dto.*;
import ma.enset.examfinal.Entities.*;
import ma.enset.examfinal.Mappers.CreditMapper;
import ma.enset.examfinal.Mappers.RemboursementMapper;
import ma.enset.examfinal.Repositories.CreditRepository;
import ma.enset.examfinal.Repositories.RemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {
    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private RemboursementRepository remboursementRepository;

    @Autowired
    private CreditMapper creditMapper;

    @Autowired
    private RemboursementMapper remboursementMapper;

    @Override
    public CreditDTO createCredit(CreditDTO creditDTO) {
        Credit credit = creditMapper.toEntity(creditDTO);
        Credit savedCredit = creditRepository.save(credit);
        return creditMapper.toDto(savedCredit);
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        return creditMapper.toDto(credit);
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll().stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO updateCredit(Long id, CreditDTO creditDTO) {
        Credit existingCredit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found"));

        // Mise à jour des champs communs
        existingCredit.setDateDemande(creditDTO.getDateDemande());
        existingCredit.setStatut(creditDTO.getStatut());
        existingCredit.setDateAcception(creditDTO.getDateAcception());
        existingCredit.setMontant(creditDTO.getMontant());
        existingCredit.setDureeRemboursement(creditDTO.getDureeRemboursement());
        existingCredit.setTauxInteret(creditDTO.getTauxInteret());

        // Mise à jour spécifique au type de crédit
        if (existingCredit instanceof CreditPersonnel && creditDTO instanceof CreditPersonnelDTO) {
            ((CreditPersonnel) existingCredit).setMotif(((CreditPersonnelDTO) creditDTO).getMotif());
        } else if (existingCredit instanceof CreditImmobilier && creditDTO instanceof CreditImmobilierDTO) {
            ((CreditImmobilier) existingCredit).setTypeBienFinance(((CreditImmobilierDTO) creditDTO).getTypeBienFinance());
        } else if (existingCredit instanceof CreditProfessionnel && creditDTO instanceof CreditProfessionnelDTO) {
            ((CreditProfessionnel) existingCredit).setMotif(((CreditProfessionnelDTO) creditDTO).getMotif());
            ((CreditProfessionnel) existingCredit).setRaisonSociale(((CreditProfessionnelDTO) creditDTO).getRaisonSociale());
        }

        Credit updatedCredit = creditRepository.save(existingCredit);
        return creditMapper.toDto(updatedCredit);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> getCreditsById(Long clientId) {
        List<Credit> credits = creditRepository.findByClientId(clientId); // Explicit List
        return credits.stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getCreditRemboursements(Long creditId) {
        List<Remboursement> remboursements= remboursementRepository.findByCreditId(creditId);
        return remboursements.stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

}