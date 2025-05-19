package ma.enset.examfinal.Dto;

import lombok.Data;
import ma.enset.examfinal.Enums.TypeBien;

@Data
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBien typeBienFinance;

}