package com.example.backend.service;

import com.example.backend.model.StatusManagement;
import java.util.List;
import java.util.Optional;

public interface StatusManagementService {
    StatusManagement createStatus(StatusManagement status);

    Optional<StatusManagement> getStatusById(Integer id);

    List<StatusManagement> getAllStatuses();

    StatusManagement updateStatus(Integer id, StatusManagement status);
}