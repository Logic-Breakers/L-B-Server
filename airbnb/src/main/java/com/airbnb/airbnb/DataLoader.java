//package com.airbnb.airbnb;
//
//import com.airbnb.airbnb.stay.entity.Category;
//import com.airbnb.airbnb.stay.repository.CategoryRepository;
//import com.airbnb.airbnb.stay.service.StayService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @Autowired
//    private StayService stayService;
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        // 카테고리 초기 데이터
//        List<String> categoryNames = Arrays.asList(
//            "인기_급상승", "국립공원", "농장", "한적한_시골", "통나무집", "방", "해변_근처", "한옥", "멋진_수영장", "최고의_전망", "캠핑장", "스키", "캐슬", "기상천외한_숙소", "해변_바로_앞", "창작_공간", "속세를_벗어난_숙소", "료칸", "호수근처", "디자인", "열대_지역", "동굴", "북극", "와인농장", "신규", "풍차", "트리하우스", "B&B", "돔하우스", "보트", "키클라데스_주택", "유서_깊은_주택", "세상의_꼭대기", "A자형_주택", "저택", "Luxe", "호숫가", "섬", "상징적_도시", "키즈", "캠핑카", "서핑", "골프장", "복토_주택", "전문가급_주방", "유르트", "민수", "스키_타고_출입", "카사_파르티쿨라르", "마차", "컨테이너하우스", "그랜드_피아노", "타워", "사막", "헛간", "하우스보트", "무장애", "담무소", "리아드", "트룰로"
//        );
//
//        for (String categoryName : categoryNames) {
//            Category category = new Category();
//            category.setCategoryName(categoryName);
//            categoryRepository.save(category);
//        }

//        //stay 더미 데이터
//        StayPostDto stayPostDto1 = new StayPostDto("관광지와 가까운 깔끔한 호텔입니다.", "대한민국", 2L, 4.5, Stay.PropertyType.HOTEL, 250000L, 2L, 1L, 1L);
//        Set<Long> categories1 = new HashSet<>();
//        categories1.add(3L);
//        categories1.add(7L);
//        stayService.createStay(stayPostDto1, categories1);
//
//        StayPostDto stayPostDto2 = new StayPostDto("오션뷰가 아름다운 호텔입니다.", "대한민국", 2L, 4.8, Stay.PropertyType.HOTEL, 400000L, 1L, 1L, 2L);
//        Set<Long> categories2 = new HashSet<>();
//        categories2.add(1L);
//        categories2.add(3L);
//        categories2.add(6L);
//        stayService.createStay(stayPostDto2, categories2);
//
//        StayPostDto stayPostDto3 = new StayPostDto("루프탑 수영장에서 바베큐 파티를 즐길 수 있는 호텔입니다.", "대한민국", 4L, 4.7, Stay.PropertyType.HOTEL, 710000L, 2L, 2L, 1L);
//        Set<Long> categories3 = new HashSet<>();
//        categories3.add(1L);
//        categories3.add(3L);
//        categories3.add(7L);
//        stayService.createStay(stayPostDto3, categories3);
//
//        StayPostDto stayPostDto4 = new StayPostDto("아이들과 함께 오기 좋은 한옥 펜션입니다.", "대한민국", 6L, 4.8, Stay.PropertyType.HOUSE, 320000L, 3L, 3L, 2L);
//        Set<Long> categories4 = new HashSet<>();
//        categories4.add(2L);
//        categories4.add(4L);
//        categories4.add(5L);
//        stayService.createStay(stayPostDto4, categories4);
//
//        StayPostDto stayPostDto5 = new StayPostDto("출국 전 머무르기 좋은, 공항과 가까운 게스트 하우스입니다.", "대한민국", 1L, 4.1, Stay.PropertyType.GUEST_HOUSE, 110000L, 1L, 1L, 1L);
//        Set<Long> categories5 = new HashSet<>();
//        categories5.add(1L);
//        categories5.add(6L);
//        stayService.createStay(stayPostDto5, categories5);
//
//
//    }
//}
