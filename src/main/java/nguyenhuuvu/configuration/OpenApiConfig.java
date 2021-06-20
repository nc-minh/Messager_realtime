package nguyenhuuvu.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Thiết lập các server dùng để test api
                .servers(
                        Stream.of(
                                new Server().url("http://localhost:8080"),
                                new Server().url("https://apidevchat.sp.skdrive.net"),
                                new Server().url("https://apidevchat.herokuapp.com"))
                                .collect(Collectors.toList()))
                // info
                .info(new Info().title("Danh sách API Chat Dev <3")
                        .description("API được gen tự động")
                        .contact(new Contact()
                                .email("contact.chatdevteam@yahoo.com")
                                .name("ChatDev")
                                .url("https://apidevchat.sp.skdrive.net"))
                        .license(new License()
                                .name("ChatDev Team")
                                .url("https://apidevchat.sp.skdrive.net"))
                        .version("1.0")
                        .title("API ChatDev"));
    }
}
