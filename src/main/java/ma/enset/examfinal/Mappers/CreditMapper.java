package ma.enset.examfinal.Mappers;

import ma.enset.examfinal.Dto.CreditDTO;
import ma.enset.examfinal.Dto.CreditImmobilierDTO;
import ma.enset.examfinal.Dto.CreditPersonnelDTO;
import ma.enset.examfinal.Dto.CreditProfessionnelDTO;
import ma.enset.examfinal.Entities.*;
import ma.enset.examfinal.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditMapper {
    @Autowired
    private ClientRepository clientRepository;

    public CreditDTO toDto(Credit credit) {
        CreditDTO dto;

        if (credit instanceof CreditPersonnel) {
            CreditPersonnelDTO personnelDto = new CreditPersonnelDTO();
            personnelDto.setMotif(((CreditPersonnel) credit).getMotif());
            dto = personnelDto;
        } else if (credit instanceof CreditImmobilier) {
            CreditImmobilierDTO immobilierDto = new CreditImmobilierDTO();
            immobilierDto.setTypeBienFinance(((CreditImmobilier) credit).getTypeBienFinance());
            dto = immobilierDto;
        } else if (credit instanceof CreditProfessionnel) {
            CreditProfessionnelDTO proDto = new CreditProfessionnelDTO();
            proDto.setMotif(((CreditProfessionnel) credit).getMotif());
            proDto.setRaisonSociale(((CreditProfessionnel) credit).getRaisonSociale());
            dto = proDto;
        } else {
            dto = new CreditDTO();
        }

        dto.setId(credit.getId());
        dto.setDateDemande(credit.getDateDemande());
        dto.setStatut(credit.getStatut());
        dto.setDateAcception(credit.getDateAcception());
        dto.setMontant(credit.getMontant());
        dto.setDureeRemboursement(credit.getDureeRemboursement());
        dto.setTauxInteret(credit.getTauxInteret());
        dto.setClientId(credit.getClient().getId());

        return dto;
    }

    public Credit toEntity(CreditDTO dto) {
        Credit credit;

        if (dto instanceof CreditPersonnelDTO) {
            CreditPersonnel personnel = new CreditPersonnel();
            personnel.setMotif(((CreditPersonnelDTO) dto).getMotif());
            credit = personnel;
        } else if (dto instanceof CreditImmobilierDTO) {
            CreditImmobilier immobilier = new CreditImmobilier();
            immobilier.setTypeBienFinance(((CreditImmobilierDTO) dto).getTypeBienFinance());
            credit = immobilier;
        } else if (dto instanceof CreditProfessionnelDTO) {
            CreditProfessionnel pro = new CreditProfessionnel();
            pro.setMotif(((CreditProfessionnelDTO) dto).getMotif());
            pro.setRaisonSociale(((CreditProfessionnelDTO) dto).getRaisonSociale());
            credit = pro;
        } else {
            credit = new Credit();
        }

        credit.setId(dto.getId());
        credit.setDateDemande(dto.getDateDemande());
        credit.setStatut(dto.getStatut());
        credit.setDateAcception(dto.getDateAcception());
        credit.setMontant(dto.getMontant());
        credit.setDureeRemboursement(dto.getDureeRemboursement());
        credit.setTauxInteret(dto.getTauxInteret());

        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            credit.setClient(client);
        }

        return credit;
    }
}
