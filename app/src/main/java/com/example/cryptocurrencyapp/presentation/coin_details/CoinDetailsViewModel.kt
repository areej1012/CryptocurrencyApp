package com.example.cryptocurrencyapp.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.Constants
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.use_case.get_coins_singular.GetCoinsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsDetailsUseCase,
    //bundle and it contains information about saved state
    // so ew can to restore our app from process death
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        // on each element that this flow emits on each resource
        getCoinsUseCase(coinId).onEach { res ->
            when (res) {
                is Resource.Success -> {
                    _state.value = CoinDetailsState(coin = res.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailsState(error = res.message ?: "unexpected error ")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}