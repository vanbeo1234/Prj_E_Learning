package com.example.backend.service.implement;

import com.example.backend.model.SystemFunction;
import com.example.backend.repository.SystemFunctionRepository;
import com.example.backend.service.SystemFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemFunctionServiceImplement implements SystemFunctionService {

    private final SystemFunctionRepository systemFunctionRepository;

    @Override
    public SystemFunction createFunction(SystemFunction function) {
        if (systemFunctionRepository.findByFunctionName(function.getFunctionName()).isPresent()) {
            throw new RuntimeException("Chức năng đã tồn tại");
        }
        return systemFunctionRepository.save(function);
    }

    @Override
    public Optional<SystemFunction> getFunctionById(Integer id) {
        return systemFunctionRepository.findById(id);
    }

    @Override
    public List<SystemFunction> getAllFunctions() {
        return systemFunctionRepository.findAll();
    }

    @Override
    public SystemFunction updateFunction(Integer id, SystemFunction function) {
        SystemFunction existingFunction = systemFunctionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chức năng với ID: " + id));
        existingFunction.setFunctionName(function.getFunctionName());
        existingFunction.setDescription(function.getDescription());
        return systemFunctionRepository.save(existingFunction);
    }
}