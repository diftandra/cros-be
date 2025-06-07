package co.id.bca.cros.module.auth.controller;

import co.id.bca.cros.module.auth.dto.AuthLoginRequestDto;
import co.id.bca.cros.module.auth.dto.AuthLoginResponseDto;
import co.id.bca.cros.module.auth.service.AuthService;
import co.id.bca.cros.module.auth.uidm.dto.login.LoggedUserDetailDto;
import co.id.bca.cros.module.auth.uidm.dto.logout.LogoutOutputSchemaDto;
import co.id.bca.cros.module.auth.uidm.dto.logout.LogoutPayloadDto;
import co.id.bca.cros.shared.dto.ApiBody;
import co.id.bca.cros.shared.helper.apibody.ApiBodyHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final ApiBodyHelper apiBodyHelper;
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<ApiBody<AuthLoginResponseDto>> login(@RequestBody AuthLoginRequestDto loginRequest, HttpServletRequest request) {
        System.out.println("HIT LOGIN");
        return ResponseEntity.ok(apiBodyHelper.create(authService.login(loginRequest, request)));
    }

    @PostMapping("logout")
    public ResponseEntity<ApiBody<LogoutOutputSchemaDto>> logout(@RequestBody LogoutPayloadDto logoutPayload, HttpServletRequest request) {
        return ResponseEntity.ok(apiBodyHelper.create(authService.logout(logoutPayload, request)));
    }

    @GetMapping("user-detail")
    public ResponseEntity<ApiBody<LoggedUserDetailDto>> userDetail(HttpServletRequest request) {
        return ResponseEntity.ok(apiBodyHelper.create(authService.getUserDetail(request)));
    }

    @GetMapping("test")
    public ResponseEntity<Map<String, Object>> hello() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from JBoss EAP 8!");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("server", "JBoss EAP 8");

        return ResponseEntity.ok(response);
    }
    
}
