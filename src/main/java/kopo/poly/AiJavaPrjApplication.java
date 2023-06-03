package kopo.poly;

import kopo.poly.dto.OcrDTO;
import kopo.poly.service.IOcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
// Spring 메모리에 올라온 객체(Service 등)를 변수에 넣음
@SpringBootApplication
public class AiJavaPrjApplication implements CommandLineRunner {

    private final IOcrService ocrService;
    // 메모리에 올라간 OcrService 객체를 ocrService 변수에 객체 넣어줌
    // 이미지 인식

    public static void main(String[] args) {
        SpringApplication.run(AiJavaPrjApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("자바 프로그래밍 시작!!");

        String filePath = "image";
        // 문자열을 인식할 이미지 파일 경로
        // OcrService에서 ClassPathResource를 통해 resource 파일 밑의 파일을 불러오므로
        //  <.../resource/image/"sample01.jpg">겠지만 resource 파일 이하의 주소를 입력해주면 되는듯...?
        // 입력할 주소의 폴더이름

        String fileName = "sample01.jpg";
        // Path 이하의 파일 이름 : 읽을 이미지 파일 이름

        OcrDTO pDTO = new OcrDTO();
        // OcrService 합수에 정보를 전달할 DTO를 메모리에 올리기 < 파라미터 전달 DTO : pDTO >

        pDTO.setFilePath(filePath);
        pDTO.setFileName(fileName);

        OcrDTO rDTO = ocrService.getReadfoImageText(pDTO);
        // 실행 결과를 전달할 DTO : rDTO
        // OcrService에서 result가 return된 결과(String result)를 rDTO를 통해 전달

        String result =rDTO.getResult();
        // rDTO에 this.result로 return 되어 있는 이미지에서 읽어온 텍스트인 result를 String result에 저장

        log.info("인식된 문자열");
        log.info(result);

        log.info("자바 프로그래밍 종료!!");
    }
}
