package com.subproject.board.service;

import com.subproject.board.common.security.UserPrincipal;
import com.subproject.board.entity.UserEntity;
import com.subproject.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        return userRepository.findOneByUserId(userId)
                .map(this::authUser)
                .orElseThrow(() -> new UsernameNotFoundException(userId + "에 해당하는 회원을 찾을 수 없습니다."));
    }

    private UserDetails authUser(UserEntity user){
        return UserPrincipal.create(user);
    }
}
