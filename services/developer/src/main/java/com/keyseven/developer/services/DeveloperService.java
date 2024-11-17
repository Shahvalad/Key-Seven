package com.keyseven.developer.services;

import com.keyseven.developer.dtos.DeveloperRequest;
import com.keyseven.developer.dtos.DeveloperResponse;

import java.util.List;

public interface DeveloperService {
    List<DeveloperResponse> getAllDevelopers();
    DeveloperResponse getDeveloperById(Long id);
    Long createDeveloper(DeveloperRequest developerRequest);
    void updateDeveloper(Long id, DeveloperRequest developerRequest);
    void deleteDeveloper(Long id);
    boolean isDeveloperExist(Long id);
}
