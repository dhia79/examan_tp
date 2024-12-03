package com.easylife.diary.core.designsystem.base

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.easylife.diary.core.navigation.DiaryNavigator

/**
 * Created by erenalpaslan on 11.08.2022
 */

abstract class BaseScreen<VM : BaseViewModel> {

    //protected val analyticsManager: AnalyticsManager by inject()
    protected lateinit var viewModel: VM
    protected lateinit var focusManager: FocusManager
    protected lateinit var navigator: DiaryNavigator

    @Composable
    fun Create(navigator: DiaryNavigator, viewModel: VM) {
        this@BaseScreen.viewModel = viewModel
        this@BaseScreen.focusManager = LocalFocusManager.current
        this@BaseScreen.navigator = navigator

        val error by viewModel.error.observeAsState()

        if (error != null) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.removeError()
                },
                title = {},
                text = {
                    Text(text = error ?: "")
                },
                properties = DialogProperties(),
                confirmButton = {},
                dismissButton = {
                    TextButton(onClick = { viewModel.removeError() }) {
                        //Text(text = stringResource(id = R.string.button_close))
                    }
                },
                shape = RoundedCornerShape(12.dp)
            )
        }

        val showProgress by viewModel.showProgress.observeAsState()

        Screen()
        if (showProgress == true ){
            Dialog(onDismissRequest = { /*TODO*/ }) {
                CircularProgressIndicator()
            }
        }
    }

    @Composable
    abstract fun Screen()
}
