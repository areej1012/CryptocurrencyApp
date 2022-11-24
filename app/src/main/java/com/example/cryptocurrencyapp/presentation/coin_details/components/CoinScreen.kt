package com.example.cryptocurrencyapp.presentation.coin_details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.presentation.Screen
import com.example.cryptocurrencyapp.presentation.coin_details.CoinDetailsViewModel
import com.example.cryptocurrencyapp.presentation.coin_list.CoinListViewModel
import com.example.cryptocurrencyapp.presentation.coin_list.components.CoinListItem

@Composable
fun CoinScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { Coin ->
                CoinListItem(coin = Coin, onItemClick = {
                    navController.navigate(Screen.CoinDetailScreen.route + "/${Coin.id}")
                })
            }
        }
    }
}