package com.vacation.backend.util;

import com.vacation.backend.model.Notice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class FileUtil {
    public static String uploadImagePath;

    @Value("${spring.servlet.multipart.location}")
    public void setKey(String value) {
        uploadImagePath = value;
    }

    public static String uploadFile(MultipartFile multipartFile) throws IOException {
        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());
        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = uploadImagePath + "/upload/" + current_date;
        File file = new File(path);
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if(!file.exists()){
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        // jpeg, png, gif 파일들만 받아서 처리할 예정
        String contentType = multipartFile.getContentType();
        String originalFileExtension = null;

        if(contentType.contains("image/jpeg")){
            originalFileExtension = ".jpg";
        }
        else if(contentType.contains("image/png")){
            originalFileExtension = ".png";
        }
        else if(contentType.contains("image/gif")){
            originalFileExtension = ".gif";
        }

        // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
        String new_file_name = System.nanoTime() + originalFileExtension;
        String detailPath = "upload/" + current_date + "/" + new_file_name;

        file = new File("/" + detailPath);
        multipartFile.transferTo(file);

        return detailPath;
    }

    public static void deleteFile(Notice notice){
        // 삭제 경로를 지정한다.
        String path = uploadImagePath + "/" +  notice.getNotiFileUrl();
        // 현재 게시판에 존재하는 파일객체를 만듬
        File file = new File(path);
        if(file.exists()) { // 파일이 존재하면
            file.delete(); // 파일 삭제
        }
    }
}
