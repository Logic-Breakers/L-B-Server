package com.airbnb.airbnb;

import com.airbnb.airbnb.entity.Category;
import com.airbnb.airbnb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
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

        Long id = 1L;

        for (String categoryName : categoryNames) {
            Category category = new Category(id,categoryName);
            categoryRepository.save(category);
            id++;
        }

    }
}
