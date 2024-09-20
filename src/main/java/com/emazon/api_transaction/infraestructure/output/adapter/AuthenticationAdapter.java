package com.emazon.api_transaction.infraestructure.output.adapter;

import com.emazon.api_transaction.domain.spi.IAthenticationPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class AuthenticationAdapter implements IAthenticationPersistencePort {

    @Override
    public String getUserName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
