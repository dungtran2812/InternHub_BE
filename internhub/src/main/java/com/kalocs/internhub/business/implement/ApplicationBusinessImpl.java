package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.ApplicationBusiness;
import com.kalocs.internhub.entity.Application;
import com.kalocs.internhub.repository.ApplicationRepository;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBusinessImpl extends BaseBusinessImpl<Application, ApplicationRepository> implements ApplicationBusiness {

    public ApplicationBusinessImpl(ApplicationRepository repository) {
        super(repository);
    }

}
