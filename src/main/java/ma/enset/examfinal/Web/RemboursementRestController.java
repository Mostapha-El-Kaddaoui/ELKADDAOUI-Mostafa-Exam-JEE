package ma.enset.examfinal.Web;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.examfinal.Dto.RemboursementDTO;
import ma.enset.examfinal.Enums.TypeRemboursement;
import ma.enset.examfinal.Service.RemboursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/remboursements")

public class RemboursementRestController {

    @Autowired
    private RemboursementService remboursementService;

    @PostMapping
    public ResponseEntity<RemboursementDTO> createRemboursement(
            @Valid @RequestBody RemboursementDTO remboursementDTO) {

        RemboursementDTO createdRemboursement = remboursementService.createRemboursement(remboursementDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdRemboursement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemboursementDTO> updateRemboursement(
            @PathVariable Long id,
            @Valid @RequestBody RemboursementDTO remboursementDTO) {

        RemboursementDTO updatedRemboursement = remboursementService.updateRemboursement(id, remboursementDTO);
        return ResponseEntity.ok(updatedRemboursement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }

    // Fonctionnalité supplémentaire: Récupérer les remboursements par type
    @GetMapping("/by-type")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByType(
            @RequestParam TypeRemboursement type) {

        List<RemboursementDTO> remboursements = remboursementService.getRemboursementsByType(type);
        return ResponseEntity.ok(remboursements);
    }
}
