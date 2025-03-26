package com.example.backend.service;

import com.example.backend.model.SystemFunction;
import java.util.List;
import java.util.Optional;

public interface SystemFunctionService {
    SystemFunction createFunction(SystemFunction function);

    Optional<SystemFunction> getFunctionById(Integer id);

    List<SystemFunction> getAllFunctions();

    SystemFunction updateFunction(Integer id, SystemFunction function);
}