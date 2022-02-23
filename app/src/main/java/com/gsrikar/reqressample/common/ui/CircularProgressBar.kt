package com.gsrikar.reqressample.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.gsrikar.reqressample.ui.theme.ReqresSampleTheme


/**
 * Circular Progress bar when based on the passed variable
 */
@Composable
fun CircularProgressBar(show: Boolean) {
    if (show) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            CircularProgressIndicator()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReqresSampleTheme {
        CircularProgressBar(
            true
        )
    }
}