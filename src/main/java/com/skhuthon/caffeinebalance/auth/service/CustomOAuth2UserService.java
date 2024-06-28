package com.skhuthon.caffeinebalance.auth.service;

import com.skhuthon.caffeinebalance.auth.dto.CustomOAuth2User;
import com.skhuthon.caffeinebalance.auth.dto.GoogleResponse;
import com.skhuthon.caffeinebalance.auth.dto.NaverResponse;
import com.skhuthon.caffeinebalance.auth.dto.OAuth2Response;
import com.skhuthon.caffeinebalance.user.domain.SocialType;
import com.skhuthon.caffeinebalance.user.domain.User;
import com.skhuthon.caffeinebalance.user.dto.request.UserCreateRequestDTO;
import com.skhuthon.caffeinebalance.user.dto.response.JwtUserResponseDTO;
import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info(String.valueOf(oAuth2User));

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = createOAuth2Response(registrationId, oAuth2User.getAttributes());

        if (oAuth2Response == null) {
            return null; // 처리할 수 없는 등록 ID인 경우 null 반환
        }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        User existData = userRepository.findByUsername(username);

        if (existData == null) {
            UserCreateRequestDTO userRequestDto = UserCreateRequestDTO.fromOAuth2Response(oAuth2Response, username);
            User user = userRequestDto.toEntity();
            userRepository.save(user);

            JwtUserResponseDTO jwtUserResponseDto = JwtUserResponseDTO.fromEntity(user);
            return new CustomOAuth2User(jwtUserResponseDto);
        }

        updateUserFromOAuth2Response(existData, oAuth2Response);

        JwtUserResponseDTO jwtUserResponseDto = JwtUserResponseDTO.fromEntity(existData);
        return new CustomOAuth2User(jwtUserResponseDto);
    }

    private OAuth2Response createOAuth2Response(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equals(SocialType.naver.name())) {
            return new NaverResponse(attributes);
        } else if (registrationId.equals(SocialType.google.name())) {
            return new GoogleResponse(attributes);
        }
        return null;
    }

    private void updateUserFromOAuth2Response(User user, OAuth2Response oAuth2Response) {
        user.update(oAuth2Response.getName(), oAuth2Response.getEmail());
    }
}
