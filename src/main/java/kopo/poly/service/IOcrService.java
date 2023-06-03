package kopo.poly.service;

import kopo.poly.dto.OcrDTO;

public interface IOcrService {
    String modelFile = "C:/model/tessdata";
    // 학습 모델 파일이 존재하는 폴더
    // tess 가동 파일 폴더 위치[해당 폴더에 tess가 이미지 분석을 하기 위한 파일이 존재함]

    OcrDTO getReadfoImageText(OcrDTO pDTO) throws Exception;
    // 이미지 파일로부터 문자 읽어오기
    // ** 읽어오는데 사용하는 DTO라는건가..?
    // OcrDTO에서 데이터를 읽어오는 DTO 이름을 pDTO로 설정했다는 의미인가..?
}
