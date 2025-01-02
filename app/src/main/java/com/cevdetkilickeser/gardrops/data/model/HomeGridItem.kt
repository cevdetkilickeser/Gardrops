package com.cevdetkilickeser.gardrops.data.model

sealed class HomeGridItem {
    data class Product(
        val id: Int,
        val sellerName: String,
        val sellerImage: String,
        val productImage: String,
        val productName: String,
        val productPrice: Float,
        val productBrand: String,
        val productCondition: Boolean,
        val productFavCount: Int = 0,
        val productSize: String? = null
    )

    data class NavigateItem(
        val id: Int,
        val title: String,
        val image: String
    )
}

val homeGridItems = listOf(
    // Promosyon öğesi (yönlendiren öğe)
    HomeGridItem.NavigateItem(
        id = 0,
        title = "",
        image = "https://images.gardrops.com/images/promo_box/main_feed_v7.jpg"
    ),
    // İlk ürün (vintage deri ceket)
    HomeGridItem.Product(
        id = 1,
        sellerName = "azra01azra",
        sellerImage = "https://images.gardrops.com/uploads/10871394/avatar_photo/10871394-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/10871394/user_items/108713943-s1-1730399877180-6723ce8637bd2.jpeg",
        productName = "Vintage Deri Ceket",
        productPrice = 500f,
        productBrand = "Derimod",
        productSize = "XL",
        productCondition = true // Durum bilgisi HTML'de verilmemiş, true varsaydım
    ),
    // İkinci ürün (söz elbisesi)
    HomeGridItem.Product(
        id = 2,
        sellerName = "bellaragzdress",
        sellerImage = "https://images.gardrops.com/uploads/10622095/avatar_photo/10622095-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/10622095/user_items/1062209564-s1-1729526413371-67167a8e2c97d.jpeg",
        productName = "Söz Elbisesi",
        productPrice = 5500f,
        productBrand = "Alfabeta",
        productSize = "M",
        productCondition = false
    ),
    // Üçüncü ürün (trençkot)
    HomeGridItem.Product(
        id = 3,
        sellerName = "hafsakyn",
        sellerImage = "https://images.gardrops.com/uploads/857809/avatar_photo/857809-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/857809/user_items/85780911-s1-file-6727a433029b7.jpeg",
        productName = "Trençkot",
        productPrice = 1000f,
        productBrand = "Stradivarius",
        productSize = "L",
        productCondition = true // Durum bilgisi verilmemiş
    ),
    HomeGridItem.Product(
        id = 4,
        sellerName = "gdnzss",
        sellerImage = "https://images.gardrops.com/uploads/8051872/avatar_photo/8051872-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/8051872/user_items/80518722-s1-file-6722158bea198.jpeg",
        productName = "Paulmark yüksek bel jean",
        productPrice = 400f,
        productBrand = "Paulmark",
        productSize = "s",
        productCondition = true
    ),
    HomeGridItem.Product(
        id = 5,
        sellerName = "eylulekilic",
        sellerImage = "https://images.gardrops.com/uploads/3632572/avatar_photo/3632572-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/3632572/user_items/363257230-s1-file-6746cdd53229d.jpeg",
        productName = "Stradivarius long straight fit pantolon 34 beden",
        productPrice = 800f,
        productBrand = "Stradivarius",
        productSize = "34",
        productCondition = false
    ),
    HomeGridItem.Product(
        id = 6,
        sellerName = "aabutik04",
        sellerImage = "https://images.gardrops.com/uploads/5246319/avatar_photo/5246319-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/5246319/user_items/524632728-s1-1729856430515-671b83af2b99a.jpeg",
        productName = "Polo garage trıko",
        productPrice = 50f,
        productBrand = "Polo Garage",
        productSize = "l",
        productCondition = true
    ),

    HomeGridItem.Product(
        id = 7,
        sellerName = "Pelingardrop35",
        sellerImage = "https://images.gardrops.com/uploads/4077507/avatar_photo/4077507-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/4077507/user_items/407751130-s1-1729949138569-671cedd5f3d7b.jpeg",
        productName = "Zara Muadili Yün Hırka",
        productPrice = 90f,
        productBrand = "Zara",
        productSize = "m",
        productCondition = true
    ),
    HomeGridItem.NavigateItem(
        id = 8,
        title = "Tüm Pantolonlar 49 TL",
        image = "https://images.gardrops.com/campaign_uploads/images/2018/3/camp_img_5abbbf6ab8b47.jpg"
    ),
    HomeGridItem.Product(
        id = 9, // id 9 olarak belirledim
        sellerName = "Shoppingdiariesist",
        sellerImage = "https://images.gardrops.com/uploads/13005605/avatar_photo/13005605-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/13005605/user_items/130056051-s1-file-6714a2e228f07.jpeg",
        productName = "Zara Şort Etek",
        productPrice = 750f,
        productBrand = "Zara",
        productSize = "xs",
        productCondition = false
    ),
    HomeGridItem.Product(
        id = 10, // id 10 olarak belirledim
        sellerName = "eryilgiyim66",
        sellerImage = "https://images.gardrops.com/uploads/6459540/avatar_photo/6459540-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/6459540/user_items/645954047-s1-file-6714a386c177a.jpeg",
        productName = "Tommy Hilfiger T-shirt",
        productPrice = 225f,
        productBrand = "Tommy Hilfiger",
        productSize = "m",
        productCondition = true
    ),
    HomeGridItem.Product(
        id = 11,
        sellerName = "gdnzss",
        sellerImage = "https://images.gardrops.com/uploads/8051872/avatar_photo/8051872-avatar-mid.jpg",
        productImage = "https://images.gardrops.com/uploads/8051872/user_items/80518722-s1-file-6722158bea198.jpeg",
        productName = "Paulmark yüksek bel jean",
        productPrice = 400f,
        productBrand = "Paulmark",
        productSize = "s",
        productCondition = true
    )
)
