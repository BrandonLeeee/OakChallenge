package com.brandon.oakchallenge.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.brandon.oakchallenge.data.model.Item
import com.brandon.oakchallenge.ui.navigation.Routes
import com.brandon.oakchallenge.viewmodel.ProductViewModel
import java.text.DecimalFormat

@Composable
fun ListScreen(
    innerPadding: PaddingValues,
    navController: NavController,
    viewModel: ProductViewModel
) {

    val productList by viewModel.productList.collectAsState()
    val isEnabled by viewModel.isButtonEnabled.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Lista de Produtos",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (productList.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Nenhum produto cadastrado", fontSize = 20.sp)
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Produto",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Text(
                        text = "Valor",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 72.dp)
                ) {
                    items(productList.sortedBy { it.price }) { product ->
                        Item(product)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FloatingButton(
                onClick = { navController.navigate(Routes.HomeScreen.route) },
                icon = Icons.Default.Add,
                contentDescription = "Add",
                text = "Adicionar Produto",
            )

            // Disable the Generate List button after triggered
            if (isEnabled) {
                FloatingButton(
                    onClick = {
                        val fakeList = viewModel.generateItemList()
                        viewModel.addItem(fakeList)
                    },
                    icon = Icons.Default.List,
                    contentDescription = "Generate List",
                    text = "Gerar lista",
                )
            }

        }

    }
}

@Composable
fun FloatingButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    text: String
) {

    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(icon, contentDescription) },
        text = { Text(text) }
    )
}

@Composable
fun Item(item: Item) {
    val decimalFormat = DecimalFormat("#,##0.00")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(if (item.isActive) Color.Green else Color.Red)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = item.name,
                fontSize = 18.sp
            )
        }

        Text(
            text = "R$: ${decimalFormat.format(item.price)}",
            fontSize = 18.sp
        )
    }
}