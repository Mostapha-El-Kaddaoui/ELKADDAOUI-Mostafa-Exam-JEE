package ma.enset.examfinal.Dto;

import lombok.Data;
import ma.enset.examfinal.Enums.TypeRemboursement;

import java.time.LocalDate;

@Data
public class RemboursementDTO {
    private Long id;
    private LocalDate date;
    private Double montant;
    private TypeRemboursement type;
    private Long creditId;

}
