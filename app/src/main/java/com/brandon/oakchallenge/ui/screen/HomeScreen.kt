package com.brandon.oakchallenge.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.brandon.oakchallenge.R
import com.brandon.oakchallenge.data.model.Item
import com.brandon.oakchallenge.ui.navigation.Routes
import com.brandon.oakchallenge.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    navController: NavController,
    viewModel: ProductViewModel
) {

    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productIsActive by remember { mutableStateOf("") }
    val radioOptions = listOf("Sim", "Não")
    val context = LocalContext.current


    fun createItem() {
        if (productName.isEmpty() || productPrice.isEmpty() || productIsActive.isEmpty()) {
            Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        } else {
            val newProduct = listOf(
                Item(
                    name = productName,
                    description = productDescription,
                    price = productPrice.toDoubleOrNull() ?: 0.00,
                    isActive = if (productIsActive == "Sim") true else false
                )
            )

            viewModel.addItem(newProduct)

            Toast.makeText(context, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show()

            // Navigate to ListScreen after successfully adding the product
            navController.navigate(Routes.ListScreen.route)

            productName = ""
            productDescription = ""
            productPrice = ""
            productIsActive = ""
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.oak_logo),
            contentDescription = "Oak logo",
            modifier = Modifier
                .size(200.dp)
        )

        Text(
            text = "Cadastro de Produto",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it.replaceFirstChar { it.uppercase() } },
            label = { Text(text = "Nome do Produto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productDescription,
            onValueChange = { productDescription = it },
            minLines = 3,
            label = { Text(text = "Descrição do Produto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = productPrice,
            onValueChange = { input ->
                if (input.isEmpty() || input.toDoubleOrNull() != null) {
                    productPrice = input
                }
            },
            label = { Text(text = "Preço do Produto") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Disponível para venda?")

        Row(
            modifier = Modifier
                .selectableGroup(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            radioOptions.forEach { option ->
                RadioButton(
                    selected = (productIsActive == option),
                    onClick = { productIsActive = option })
                Text(text = option)

            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { createItem() })
        {
            Text(text = "Cadastrar Produto")
        }
    }

}



