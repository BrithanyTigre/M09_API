package cat.itb.m09.apirest.apim09.model.entitats;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuariConsultaDTO {
    private String username;
    private String avatar;
    private String rol;
}
