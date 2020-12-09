package br.pucrs.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(value = "remote-ip", produces = "application/json", tags = "Remote IP",
        description = "Retorna cabeçalhos e ips remotos")
@RequestMapping(path = "/headers")
public interface IpsController {

    @ApiOperation(value = "Ips remotos")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Map<String, String>> getRemoteHeader(HttpServletRequest request,
                                                           HttpServletResponse response);
}
