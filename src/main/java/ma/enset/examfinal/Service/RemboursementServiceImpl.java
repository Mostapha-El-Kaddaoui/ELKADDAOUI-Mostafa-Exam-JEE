package ma.enset.examfinal.Service;

import jakarta.transaction.Transactional;
import ma.enset.examfinal.Dto.RemboursementDTO;
import ma.enset.examfinal.Entities.Remboursement;
import ma.enset.examfinal.Enums.TypeRemboursement;
import ma.enset.examfinal.Mappers.RemboursementMapper;
import ma.enset.examfinal.Repositories.RemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemboursementServiceImpl implements RemboursementService {
    @Autowired
    private RemboursementRepository remboursementRepository;

    @Autowired
    private RemboursementMapper remboursementMapper;

    @Override
    public RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        Remboursement savedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDto(savedRemboursement);
    }

    @Override
    public RemboursementDTO getRemboursementById(Long id) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement not found"));
        return remboursementMapper.toDto(remboursement);
    }

    @Override
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementRepository.findAll().stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO updateRemboursement(Long id, RemboursementDTO remboursementDTO) {
        Remboursement existingRemboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement not found"));

        existingRemboursement.setDate(remboursementDTO.getDate());
        existingRemboursement.setMontant(remboursementDTO.getMontant());
        existingRemboursement.setType(remboursementDTO.getType());

        Remboursement updatedRemboursement = remboursementRepository.save(existingRemboursement);
        return remboursementMapper.toDto(updatedRemboursement);
    }

    @Override
    public void deleteRemboursement(Long id) {
        remboursementRepository.deleteById(id);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCredit(Long creditId) {
        return remboursementRepository.findByCreditId(creditId).stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByType(TypeRemboursement type) {
        return remboursementRepository.findByType(type).stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }
}
