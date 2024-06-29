package com.skhuthon.caffeinebalance.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_ATTRIBUTE_TYPE_MESSAGE(HttpStatus.BAD_REQUEST, "잘못된 응답 속성 유형입니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_USER(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),

    /* 404 NOT_FOUND : 리소스를 찾을 수 없음 */
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 메뉴입니다."),
    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 브랜드입니다."),
    BRAND_AND_MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 메뉴브랜드입니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
