package com.discern.api.service;

import com.discern.api.dto.CompanyDTO;
import com.discern.api.exceptions.CompanyNotFoundException;
import com.discern.api.model.Company;
import com.discern.api.repository.CompanyRepository;
import com.discern.api.utils.mapper.MapperUtil;
import com.discern.api.utils.matcher.TiposExampleMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final MapperUtil mapperUtil;
    private final CompanyRepository companyRepository;

    public Page<CompanyDTO> getAllCompanys(CompanyDTO companyDTO, Pageable pageable) {
        var example = Example.of(mapperUtil.mapTo(companyDTO, Company.class),
                TiposExampleMatcher.exampleMatcherMatchingAny());

        return companyRepository.findAll(example, pageable)
                .map(Company -> mapperUtil.mapTo(Company, CompanyDTO.class));
    }

    public CompanyDTO findById(Long id) {
        return mapperUtil.mapTo(companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new), CompanyDTO.class);
    }

    public CompanyDTO saveOrUpdate(CompanyDTO companyDTO, Long id) {
        Company projectSave;

        if(id != null) {
            projectSave = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
            Company pDTO = mapperUtil.mapTo(companyDTO, Company.class);
            BeanUtils.copyProperties(pDTO, projectSave, "id");
        } else {
            projectSave = mapperUtil.mapTo(companyDTO, Company.class);
        }

        return mapperUtil.mapTo(companyRepository.save(projectSave), CompanyDTO.class);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
