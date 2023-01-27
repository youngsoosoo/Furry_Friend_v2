package com.friend.furry.security.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberJoinDTO {
    private Long mid;
    private String mpw;
    private String email;
    private String name;
    private String address;
    private String phone;
    private boolean del;
    private boolean social;
}
