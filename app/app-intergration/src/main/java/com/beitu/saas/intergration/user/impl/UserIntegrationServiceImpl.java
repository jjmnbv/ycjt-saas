package com.beitu.saas.intergration.user.impl;

import com.beitu.saas.intergration.user.UserIntegrationService;

public class UserIntegrationServiceImpl implements UserIntegrationService {
    
    @Override
    public Boolean userNameMatchIdNo(String name, String identityNo) {
        return Boolean.TRUE;
    }
    
}
