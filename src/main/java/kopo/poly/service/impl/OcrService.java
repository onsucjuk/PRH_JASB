package kopo.poly.service.impl;

import kopo.poly.dto.OcrDTO;
import kopo.poly.service.IOcrService;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Slf4j
// log.info()를 사용할 수 있게 해줌(println()랑 비슷한 기능)
@Service
public class OcrService implements IOcrService {

    @Override
    public OcrDTO getReadfoImageText(OcrDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getReadforImageText start!");
        //시작부분 실행 됐는지 확인용 : 오류 지점 파악을 위한 장치 가능하면 항상 기입해두자

        ClassPathResource resource = new ClassPathResource(pDTO.getFilePath() + "/" + pDTO.getFileName());
        //resource 폴더 밑에 존재하는 파일을 활용하기 위한 객체(ClassPathResource)
        //resource라는 변수명으로 ClassPathResource를 선언 ==> 메모리에 올림
        // pDTO에 set된 filePath와 fileName을 불러옴

        ITesseract instance = new Tesseract();
        // 이미지 얽어주는 라이브러리?(Tess)
        // instance라는 변수명으로 Tess를 선언 ==> 메모리에 올림

        instance.setDatapath(IOcrService.modelFile);
        // instance의 메서드 .setDatapath(tess쪽 라이브러리에 존재하는 명령어...?)
        // instance에서 이미지 분석을 위해 활용할 데이터 파일 주소
        // IOcrService의 modelFile 변수에 저장해둔 것을 불러옴.

        instance.setLanguage("kor");
        // 한국어 설정

        String result = instance.doOCR(resource.getFile());
        // 이미지 파일로부터 텍스트 읽기
        // ** ClassPathResource 관련 메서드인 것 같은데 뭐하는건지 모르겠음 ㅋㅋㅋㅋ
        // 아마 ClassPathResource위에 파라미터로 입력값으로 설정한 절대주소의 파일을 읽어오는 것 같음..
        // 그걸 instance(tess)에서 활용해서 텍스트를 읽어오고 String 타입으로 저장(result변수에)한다는 의미 같음

        pDTO.setResult(result);
        // DTO에 이미지에서 읽어 온 텍스트는 String(result)에 저장되어 있음.
        // 이를 OcrDTO에 선언해둔 result에 setResult로 pDTO로 선언된 result로 리턴함
        // 수식화 -> pDTO에서 this.result = result(String result에 저장된 이미지의 텍스트) ??

        return pDTO;
        // pDTO에 전달 정보를 리턴함 <전송 데이터 설정>??
        // 이미지에서 tess(이미지 텍스트 분석 라이브러리)를 통해 텍스트로 읽어들여 오는 service 구현
    }
}
