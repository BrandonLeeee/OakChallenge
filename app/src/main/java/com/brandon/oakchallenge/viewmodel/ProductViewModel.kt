package com.brandon.oakchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.brandon.oakchallenge.data.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel() : ViewModel() {

    private val _productList = MutableStateFlow<List<Item>>(emptyList())
    val productList: StateFlow<List<Item>> = _productList


    private val _isButtonEnabled = MutableStateFlow(true)
    val isButtonEnabled: StateFlow<Boolean> = _isButtonEnabled

    fun addItem(item: List<Item>) {
        _productList.value += item
    }

    fun generateItemList(): List<Item> {
        _isButtonEnabled.value = false
        return listOf(
            Item("Apple iPhone 14", "Latest smartphone with A15 Bionic chip", 999.99, true),
            Item(
                "Samsung Galaxy S23",
                "Flagship Android phone with stunning display",
                899.99,
                true
            ),
            Item("Sony WH-1000XM5", "Noise-canceling wireless headphones", 349.99, true),
            Item("Apple MacBook Air", "Lightweight laptop with M2 chip", 1249.99, true),
            Item("Dell XPS 13", "Compact and powerful ultrabook", 1399.99, true),
            Item("Logitech MX Master 3S", "Ergonomic wireless mouse", 99.99, true),
            Item("Amazon Echo Dot", "Smart speaker with Alexa support", 49.99, true),
            Item("Samsung 4K Smart TV", "Crystal UHD TV with HDR support", 649.99, true),
            Item(
                "Dyson V15 Detect",
                "Cordless vacuum cleaner with laser dust detection",
                699.99,
                true
            ),
            Item(
                "Apple AirPods Pro 2",
                "True wireless earbuds with active noise cancellation",
                249.99,
                true
            ),
            Item("Sony PlayStation 5", "Next-gen gaming console", 499.99, true),
            Item("Microsoft Xbox Series X", "High-performance gaming console", 499.99, true),
            Item("GoPro Hero 11", "Action camera with 5K video recording", 399.99, true),
            Item("Kindle Paperwhite", "E-reader with adjustable warm light", 139.99, true),
            Item("Fitbit Charge 5", "Fitness tracker with stress management tools", 149.99, true),
            Item("Nespresso Vertuo Plus", "Coffee and espresso machine", 179.99, true),
            Item("KitchenAid Stand Mixer", "All-in-one kitchen appliance", 499.99, true),
            Item("Instant Pot Duo", "7-in-1 electric pressure cooker", 129.99, true),
            Item("Bose SoundLink Flex", "Portable Bluetooth speaker", 149.99, true),
            Item("Tile Pro", "High-performance Bluetooth tracker", 34.99, true),
            Item("Razer DeathAdder V3", "Lightweight gaming mouse", 69.99, true),
            Item(
                "Samsung T7 Portable SSD",
                "Fast external storage with 1TB capacity",
                109.99,
                true
            ),
            Item("Anker PowerCore 10000", "Portable charger with high-speed charging", 29.99, true),
            Item("Canon EOS R6", "Full-frame mirrorless camera", 2499.99, true),
            Item("JBL Flip 6", "Waterproof Bluetooth speaker", 129.99, true),
            Item(
                "Garmin Forerunner 945",
                "Advanced running and triathlon smartwatch",
                499.99,
                true
            ),
            Item("Nest Learning Thermostat", "Smart thermostat with auto-schedule", 249.99, true),
            Item("Herman Miller Aeron", "Ergonomic office chair", 1449.99, true),
            Item(
                "Apple iPad Pro 11",
                "Tablet with M2 chip and Liquid Retina display",
                799.99,
                true
            ),
            Item(
                "Microsoft Surface Pro 9",
                "2-in-1 tablet and laptop with touch screen",
                999.99,
                true
            )
        )
    }

}