package com.haocp.declare.exception.user;

/**
 * @Auther: HPGT
 * @Date: 2021/1/5 15:17
 * @Description: 令牌过期
 */
public class InvalidTokenException extends UserException {

    private static final long serialVersionUID = 1L;

    public InvalidTokenException() {
        super("user.token.expire", null);
    }
}
