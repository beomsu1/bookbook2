package org.bs.rental.security;

import org.bs.rental.mapper.member.MemberMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final MemberMapper memberMapper;    

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        return memberMapper.memberReadOne(id);
    }
    
}
