package br.pucrs.api.impl;

import br.pucrs.api.IpsController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IpsControllerImpl implements IpsController {

    @Override
    public ResponseEntity<Map<String, String>> getRemoteHeader(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> hashMap = new HashMap<>();

        var headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            var headerName = headerNames.nextElement();
            hashMap.put(headerName, request.getHeader(headerName));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(hashMap);
    }

}
