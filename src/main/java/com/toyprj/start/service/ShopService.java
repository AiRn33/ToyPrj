package com.toyprj.start.service;

import com.toyprj.start.entity.Shop;
import com.toyprj.start.entity.User;
import com.toyprj.start.model.ShopDto;
import com.toyprj.start.repository.ShopJpaRepostiory;
import com.toyprj.start.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopJpaRepostiory shopJpaRepostiory;
    private final UserJpaRepository userJpaRepository;

    public void createShop(User user, MultipartFile file, String shopTitle, String shopContent, String shopAmount, String shopPrice) throws IOException {

        int count = 0;
        for (int i = 0; i < file.getOriginalFilename().length(); i++) {

            if (file.getOriginalFilename().charAt(i) == '.') {
                count = i;
            }
        }

        String uuid = UUID.randomUUID() + "."
                + file.getOriginalFilename().substring(count + 1, file.getOriginalFilename().length());

        String path = "C:\\start\\src\\main\\resources\\static\\img\\" + user.getId(); //폴더 경로

        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); //폴더 생성합니다.
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        file.transferTo(new File(path + "\\" + uuid));

        shopJpaRepostiory.createShop(user.getId(), user.getUserName(), shopTitle, shopContent,
                new Date(), uuid, file.getOriginalFilename(), shopAmount, shopPrice);
    }

    public Shop getShop(Long shopNumber){

       return shopJpaRepostiory.getShop(shopNumber);
    }

    @Transactional
    public Page<Shop> getShopList(Pageable pageable) {

        return shopJpaRepostiory.findAll(pageable);
    }

    public List<Shop> getSellShopList(Long id){

        return shopJpaRepostiory.getSellShopList(id);
    }
    public int getShopPage() {

        return shopJpaRepostiory.pageNumberCheckMember();
    }

    public void deleteShop(Long shopNumber){

        shopJpaRepostiory.deleteById(shopNumber);
    }

    public void modifyShop(Shop shop,String shopTitle, String shopContent, Long shopPrice,
                           Long shopAmount, MultipartFile file) throws IOException {


        // 기존의 파일 삭제
        Path filePath = Paths.get("C:\\start\\src\\main\\resources\\static\\img\\" + shop.getId() + "\\" + shop.getFileUuid());
        try {
            // 파일 삭제
            Files.delete(filePath);
        } catch (NoSuchFileException e) {
            System.out.println("삭제하려는 이미지가 없습니다");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 파일 추가
        int count = 0;
        for (int i = 0; i < file.getOriginalFilename().length(); i++) {

            if (file.getOriginalFilename().charAt(i) == '.') {
                count = i;
            }
        }

        String uuid = UUID.randomUUID().toString();

        String path = "C:\\start\\src\\main\\resources\\static\\img\\" + shop.getId(); //폴더 경로

        file.transferTo(new File(path + "\\" + uuid + "."
                + file.getOriginalFilename().substring(count + 1, file.getOriginalFilename().length())));

        // DTO 저장
        ShopDto dto = new ShopDto();
        dto.setId(shop.getId());
        dto.setShopNumber(shop.getShopNumber());
        dto.setShopWriter(shop.getShopWriter());
        dto.setShopTitle(shopTitle);
        dto.setShopContent(shopContent);
        dto.setShopPrice(shopPrice);
        dto.setShopAmount(shopAmount);
        dto.setFileUuid(uuid + "." + file.getOriginalFilename().substring(count + 1, file.getOriginalFilename().length()));
        dto.setFileName(file.getOriginalFilename());

        shopJpaRepostiory.save(dto.toEntity());
    }

    public List<Shop> getMyShop(String name) {

        String myshop = userJpaRepository.findByuserId(name).getUserMyshop();

        // 장바구니의 글을 체크함함
       String result[] = myshop.split(",");


       List<Shop> list = new ArrayList<>();

       for(int i = 0; i < result.length; i++){
           if(!result[i].equals("")) {
               list.add(shopJpaRepostiory.findById(Long.valueOf(result[i])).orElse(null));
           }
       }

       return list;
    }


}


