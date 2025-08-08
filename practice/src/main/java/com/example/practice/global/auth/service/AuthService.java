package com.example.practice.global.auth.service;

import com.example.practice.domain.user.entity.User;
import com.example.practice.domain.user.repository.UserRepository;
import com.example.practice.global.auth.dto.request.LoginRequestDto;
import com.example.practice.global.auth.dto.request.SignupRequestDto;
import com.example.practice.global.auth.dto.response.SignupResponseDto;
import com.example.practice.global.auth.dto.response.TokenResponseDto;
import com.example.practice.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }
        if (userRepository.existsByNickname(requestDto.getNickname())) {
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }
        // 비밀번호 암호화
        // requestDto를 통해 비밀번호를 가져온 후 passwordEncoder의 encode 메서드를 통해 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        // 암호화 한 비밀번호를 repository에 넘겨 DB에 저장해야 하니 toEntity로 바꾸고 user로 저장
        User user = requestDto.toEntity(encodedPassword);
        // user data를 DB에 저장 후 responseDto에 매칭되는 데이터 변환
        return SignupResponseDto.from(userRepository.save(user));
    }


    public TokenResponseDto login(LoginRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                requestDto.getUsername(),
                requestDto.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);
        return new TokenResponseDto(jwt);
    }
}
