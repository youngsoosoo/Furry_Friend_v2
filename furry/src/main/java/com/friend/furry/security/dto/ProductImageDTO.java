package com.friend.furry.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageDTO {
    private String imgName;
    private String uuid;
    private String path;
    //실제 이미지 경로를 리턴해주는 메서드
    public String getImageURL(){
        try {
            return URLEncoder.encode(path + "/" + uuid + imgName, "UTF-8");
        }catch (UnsupportedEncodingException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return "";
    }

    //Thumbnail 이미지 경로를 리턴하는 메서드
    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(path + "/" + uuid + imgName, "UTF-8");
        }catch (UnsupportedEncodingException e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return "";
    }
}
