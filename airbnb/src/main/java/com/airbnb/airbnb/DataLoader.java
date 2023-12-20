package com.airbnb.airbnb;

import com.airbnb.airbnb.dto.StayPostDto;
import com.airbnb.airbnb.entity.Category;
import com.airbnb.airbnb.entity.Stay;
import com.airbnb.airbnb.repository.CategoryRepository;
import com.airbnb.airbnb.service.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StayService stayService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 카테고리 초기 데이터
        List<String> categoryNames = Arrays.asList(
                "Countryside", "Camping", "Amazing Views", "Boat", "Hanoks", "Beach", "Pools", "Skiing",
                "Farms", "National Parks", "Omg", "Tiny Homes", "Ryokans", "Lake", "Vineyards", "Historical Homes",
                "Island", "Off the Grid", "Design", "Bed and Breakfasts", "Tropical", "Desert", "Play", "Mansions",
                "Iconic Cities", "Treehouses", "Castles", "A Frames", "Creative Spaces", "Top of the World",
                "Cycladic Homes", "Riads", "Barns", "Minsus", "Arctic", "Caves", "Trulli", "Dammusi", "Towers",
                "Adapted", "Earth Homes", "Domes", "New", "Yurts", "Golfing", "Surfing", "Camper Vans", "Luxe",
                "Chef Kitchen", "Shapherd Huts", "Casas Particulares", "Windmills", "Containers", "Grand Pianos",
                "Houseboats"
        );

        for (String categoryName : categoryNames) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            categoryRepository.save(category);
        }

        StayPostDto stayPostDto1 = new StayPostDto("관광지와 가까운 깔끔한 호텔입니다.", "대한민국", Stay.PropertyType.HOTEL, 250000L, 2L, 1L, 1L);
        Set<Long> categories1 = new HashSet<>();
        categories1.add(3L);
        categories1.add(7L);
        stayService.createStay(stayPostDto1, categories1);

        StayPostDto stayPostDto2 = new StayPostDto("오션뷰가 아름다운 호텔입니다.", "대한민국", Stay.PropertyType.HOTEL, 400000L, 1L, 1L, 2L);
        Set<Long> categories2 = new HashSet<>();
        categories2.add(1L);
        categories2.add(3L);
        categories2.add(6L);
        stayService.createStay(stayPostDto2, categories2);

        StayPostDto stayPostDto3 = new StayPostDto("루프탑 수영장에서 바베큐 파티를 즐길 수 있는 호텔입니다.", "대한민국", Stay.PropertyType.HOTEL, 710000L, 2L, 2L, 1L);
        Set<Long> categories3 = new HashSet<>();
        categories3.add(1L);
        categories3.add(3L);
        categories3.add(7L);
        stayService.createStay(stayPostDto3, categories3);

        StayPostDto stayPostDto4 = new StayPostDto("아이들과 함께 오기 좋은 한옥 펜션입니다.", "대한민국", Stay.PropertyType.HOUSE, 320000L, 3L, 3L, 2L);
        Set<Long> categories4 = new HashSet<>();
        categories4.add(2L);
        categories4.add(4L);
        categories4.add(5L);
        stayService.createStay(stayPostDto4, categories4);

        StayPostDto stayPostDto5 = new StayPostDto("출국 전 머무르기 좋은, 공항과 가까운 호텔입니다.", "대한민국", Stay.PropertyType.GUEST_HOUSE, 110000L, 1L, 1L, 1L);
        Set<Long> categories5 = new HashSet<>();
        categories5.add(1L);
        categories5.add(6L);
        stayService.createStay(stayPostDto5, categories5);


    }
}
