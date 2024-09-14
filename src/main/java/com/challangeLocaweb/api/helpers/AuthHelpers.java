package com.challangeLocaweb.api.helpers;

import com.challangeLocaweb.api.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.challangeLocaweb.api.exceptions.UserNotAuthenticatedException;
import org.springframework.stereotype.Service;

@Service
public class AuthHelpers {

    public Long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            User user = (User) authentication.getPrincipal();
            return user.getId();
        }
        throw new UserNotAuthenticatedException("error.user.not.authenticated");
    }

    public User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (User) authentication.getPrincipal();
        }
        throw new UserNotAuthenticatedException("error.user.not.authenticated");
    }

    public boolean validateAccess(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return true;
        }
        return verifyUserIdByToken(id);

    }
    public boolean verifyUserIdByToken(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId().equals(id);
    }
}

