package br.pucrs.config.documentation;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Classe de configuração do Spring para o Swagger.
 *
 * @author PUCRS
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

    private static final String API_TITLE = "ws-utils";

    private static final String API_DESCRIPTION = "Utils e ferramentas.";

    /**
     * Cria um Docket bean para injeção de dependência.
     *
     * @return docket
     */
    @Bean
    public Docket docketBean() {
        AuthorizationScope[] authScopes = new AuthorizationScope[]{
                new AuthorizationScopeBuilder().scope("global").description(
                        "full access").build()};
        SecurityReference securityReference = SecurityReference.builder().reference(
                "Authorization-Key").scopes(authScopes).build();

        ArrayList<SecurityContext> securityContexts = Lists.newArrayList(
                SecurityContext.builder().securityReferences(Lists.newArrayList(
                        securityReference)).build());

        return new Docket(DocumentationType.SWAGGER_2).select().apis(
                RequestHandlerSelectors.basePackage("br.pucrs")).paths(PathSelectors.regex(
                "/.*")).build().apiInfo(buildApiInfo()).useDefaultResponseMessages(
                false).securitySchemes(
                Lists.newArrayList(new ApiKey("Authorization-Key", "Authorization",
                        "header"))).securityContexts(securityContexts);
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version("1.0.0")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
    }

}