package com.example.backend.service.implement;

import com.example.backend.model.StatusManagement;
import com.example.backend.repository.StatusManagementRepository;
import com.example.backend.service.StatusManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusManagementServiceImplement implements StatusManagementService {

    private final StatusManagementRepository statusManagementRepository;

    @Override
    public StatusManagement createStatus(StatusManagement status) {
        if (statusManagementRepository.findByStatusCodeAndType(status.getStatusCode(), status.getType()).isPresent()) {
            throw new RuntimeException("Trạng thái đã tồn tại");
        }
        return statusManagementRepository.save(status);
    }

    @Override
    public Optional<StatusManagement> getStatusById(Integer id) {
        return statusManagementRepository.findById(id);
    }

    @Override
    public List<StatusManagement> getAllStatuses() {
        return statusManagementRepository.findAll();
    }

    @Override
    public StatusManagement updateStatus(Integer id, StatusManagement status) {
        StatusManagement existingStatus = statusManagementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy trạng thái với ID: " + id));
        existingStatus.setStatusCode(status.getStatusCode());
        existingStatus.setType(status.getType());
        existingStatus.setDescription(status.getDescription());
        return statusManagementRepository.save(existingStatus);
    }
}