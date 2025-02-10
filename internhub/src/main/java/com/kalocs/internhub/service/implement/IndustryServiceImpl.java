package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.IndustryBusiness;
import com.kalocs.internhub.entity.Industry;
import com.kalocs.internhub.model.IndustryDTO;
import com.kalocs.internhub.service.IndustryService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class IndustryServiceImpl implements IndustryService {
    @Autowired
    private IndustryBusiness industryBusiness;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public IndustryDTO getIndustryById(Integer id) {
        log.debug("getIndustryById() IndustryServiceImpl start | id: {}", id);
        Industry industry = industryBusiness.getIndustryById(id);
        log.debug("getIndustryById() IndustryServiceImpl end | industry: {}", industry);
        return modelMapper.map(industry, IndustryDTO.class);
    }

    @Override
    public List<IndustryDTO> getAllIndustries() {
        log.debug("getAllIndustries() IndustryServiceImpl start");
        List<Industry> industryList = industryBusiness.getAllIndustries();
        log.debug("getAllIndustries() IndustryServiceImpl end");
        return industryList.stream().map(industry -> modelMapper.map(industry, IndustryDTO.class)).toList();
    }

}
