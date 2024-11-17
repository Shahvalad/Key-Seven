package com.keyseven.developer.services.Impl;

import com.keyseven.developer.dtos.DeveloperRequest;
import com.keyseven.developer.dtos.DeveloperResponse;
import com.keyseven.developer.entites.Developer;
import com.keyseven.developer.exceptions.DeveloperNotFoundException;
import com.keyseven.developer.mappers.DeveloperMapper;
import com.keyseven.developer.repositories.DeveloperRepository;
import com.keyseven.developer.services.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;
    private final DeveloperMapper mapper;

    @Override
    public List<DeveloperResponse> getAllDevelopers() {
        return developerRepository.findByIsDeletedFalse()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DeveloperResponse getDeveloperById(Long id) {
        return developerRepository.findByIdAndIsDeletedFalse(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer with ID " + id + " not found"));
    }

    @Override
    public Long createDeveloper(DeveloperRequest developerRequest) {
        return developerRepository.save(mapper.toEntity(developerRequest)).getId();
    }

    @Override
    public void updateDeveloper(Long id, DeveloperRequest developerRequest) {
        Developer developer = developerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer with ID " + id + " not found"));
        updateDeveloperFields(developer, developerRequest);
        developerRepository.save(developer);
    }

    @Override
    public void deleteDeveloper(Long id) {
        Developer developer = developerRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer with ID " + id + " not found"));
        developer.setDeleted(true);
        developerRepository.save(developer);
    }

    @Override
    public boolean isDeveloperExist(Long id) {
        return developerRepository.findByIdAndIsDeletedFalse(id).isPresent();
    }

    private void updateDeveloperFields(Developer developer, DeveloperRequest developerRequest) {
        if (developerRequest.name() != null) {
            developer.setName(developerRequest.name());
        }
        if (developerRequest.description() != null) {
            developer.setDescription(developerRequest.description());
        }
        if (developerRequest.imageUrl() != null) {
            developer.setImageUrl(developerRequest.imageUrl());
        }
        if (developerRequest.website() != null) {
            developer.setWebsite(developerRequest.website());
        }
        if (developerRequest.country() != null) {
            developer.setCountry(developerRequest.country());
        }
    }


}

