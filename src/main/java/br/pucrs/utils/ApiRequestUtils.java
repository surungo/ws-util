package br.pucrs.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

public class ApiRequestUtils {
    public static String getClientIpFromRequest(HttpServletRequest request) {
        final var xForwardedForHeader = request.getHeader("X-Forwarded-For");

        return StringUtils.isBlank(xForwardedForHeader) ?
                request.getRemoteAddr() :
                new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
    }

}
