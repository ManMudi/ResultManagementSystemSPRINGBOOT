package org.result.ResultManagementSystem.service.impl;

import lombok.AllArgsConstructor;
import org.result.ResultManagementSystem.dto.YearDto;
import org.result.ResultManagementSystem.entity.Years;
import org.result.ResultManagementSystem.exception.ResourceNotFoundException;
import org.result.ResultManagementSystem.mapper.YearMapper;
import org.result.ResultManagementSystem.repository.YearRepository;
import org.result.ResultManagementSystem.service.YearService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class YearServiceImple implements YearService {

    private YearRepository yearRepository;
    @Override
    public YearDto createYear(YearDto yearDto) {
        Years years= YearMapper.mapToYear(yearDto);
        Years years1=yearRepository.save(years);
        return YearMapper.mapToYearDto(years1);
    }

    @Override
    public YearDto findById(Long id) {
        Years years=yearRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Year is not exist with given id : "+ id));
        return YearMapper.mapToYearDto(years);
    }

    @Override
    public List<YearDto> findAll() {
        List<Years> years=yearRepository.findAll();
        return years.stream().map( (years1)->YearMapper.mapToYearDto(years1))
                .collect(Collectors.toList());
    }

    @Override
    public YearDto updateYear(Long id, YearDto yearDto) {
        Years years=yearRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Year is not exist with given id : "+ id));
                years.setYearValue(yearDto.getYearValue());
                Years years1=yearRepository.save(years);
        return YearMapper.mapToYearDto(years1);
    }

    @Override
    public void deleteById(Long id) {
        Years years=yearRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Year is not exist with given id : "+ id));
        yearRepository.deleteById(id);
    }


}
