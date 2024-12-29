package spring_crud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    @NotBlank(message = "이름을 입력하세요")
    private String username;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

}
