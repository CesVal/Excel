package pe.com.access.control.AccesControl.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private String id;
    private String name;
    private String description;
}
