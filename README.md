# 토이프로젝트 진행 중

## 9/26 ~ 현재 진행중
## 구현중인 기능

### 1. 이력서 페이지
### 2. 기본적인 CRUD 기능을 사용하는 게시판
### 3. To do List(할 일 리스트 페이지)
### 4. 쇼핑몰


## 9/26 ~ 10/3

마이 페이지 및 기본적인 게시판 기능 구현 완료

## 10/4

페이징 처리 구현 완료

## 10/6

비밀번호 변경 구현 

## 10/7

비밀번호 찾기 구현

## 10/8
코드 리팩토링(로그인시 해당 회원의 데이터 Session에 저장할 수 있도록 변경)
@AuthenticationPrincipal 사용 예정
-- 미달성 아직 차후 적용 예정

## 10/10

TodoList 템플릿 작성

## 10/11

TodoList 기본 기능 추가 완료

## 10/12

TodoList 기능 완료

## 10/13

쇼핑몰 템플릿 구성 틀 잡기

## 10/14

쇼핑몰 사진 업로드 기능 구현

## 10/15 

이미지파일 업로드 구현중 (BLOB 방식 구현 )

## 10/17

이미지 파일 업로드 방법 변경(static 에 저장하는 방식으로 로컬에 있는 파일을 불러 올 수 있게 설정하는 것으로 변경)

## 10/18

이미지 파일 UUID를 사용하여 Static 정적 폴더를 사용하여 저장 후 불러오기 기능까지는 구현 완료, 단 STS나 Eclipse 처럼 톰캣 배포 폴더에 인텔리제이는 자동으로 배포가 불가능하여 구현에 실패함. 해당 기능은 인텔리제이 유료버전에서만 구현이 가능하다고함, 현재 서버를 내렸다가 올리면 자동으로 톰캣 배포 폴더에 이미지 파일이 등록되어서 문제없이 이미지 파일을 확인할 수 있는 모습을 볼 수 
