package ma.enset.examfinal.Dto;

import lombok.Data;
import ma.enset.examfinal.Enums.StatutCredit;

import java.time.LocalDate;
@Data
public class CreditDTO {
    private Long id;
    private LocalDate dateDemande;
    private StatutCredit statut;
    private LocalDate dateAcception;
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;
    private Long clientId;

}