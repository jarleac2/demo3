//package edu.co.jhair.reactiva.modulo1.demo.controller;

/*import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OpaqueController {

    @GetMapping("/opaque")
    public Mono<String> endpointTestOpaque(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal){
        return Mono.just(principal.getAttribute("sub") + " valor")
    }

    @GetMapping("/opaquev2")
    public Mono<String> endpointTestOpaqueV2(BearerTokenAuthentication authentication){
        return Mono.just(authentication.getTokenAttributes.("sub") + " valor")
    }
}*/
