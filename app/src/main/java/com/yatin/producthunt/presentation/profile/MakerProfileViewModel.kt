package com.yatin.producthunt.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yatin.producthunt.domain.entities.Maker
import com.yatin.producthunt.domain.usecase.GetMakerDetail
import com.yatin.producthunt.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakerProfileViewModel @Inject constructor(
    private val getMakerDetail: GetMakerDetail
) : BaseViewModel() {

    private val _makerDetail: MutableLiveData<MakerView> = MutableLiveData()
    val makerDetail: LiveData<MakerView> = _makerDetail

    fun loadMakerDetail(makerId: String) =
        getMakerDetail(GetMakerDetail.Params(makerId), viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleMakerDetail
            )
        }

    private fun handleMakerDetail(maker: Maker) {
        _makerDetail.value = maker.toMakerView()
    }
}