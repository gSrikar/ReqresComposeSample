package com.gsrikar.reqressample.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gsrikar.reqressample.api.RetrofitProvider
import com.gsrikar.reqressample.common.ui.CircularProgressBar
import com.gsrikar.reqressample.database.ReqresDatabase
import com.gsrikar.reqressample.models.ReqresData
import com.gsrikar.reqressample.ui.theme.ReqresSampleTheme
import com.gsrikar.reqressample.users.UserCardListItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReqresSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    UserCardList()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserCardList(
    viewModel: MainViewModel = MainViewModel(
        MainRepository(
            ReqresDatabase.getdatabase(LocalContext.current.applicationContext),
            RetrofitProvider.userInterface
        )
    ),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    // Create a mutable state of data
    val reqresMutableState = remember {
        mutableStateOf(listOf<ReqresData>())
    }

    LaunchedEffect(scaffoldState.snackbarHostState) {
        // Fetch the list of users
        val response = viewModel.fetchUsers()
        println("User List Count: ${response.size}")
        // Update the mutable data
        reqresMutableState.value = response
    }

    // Show the content
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(items = reqresMutableState.value, itemContent = {
            UserCardListItem(data = it)
        })
    }


    // Show progress bar
    CircularProgressBar(viewModel.getLoading())
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReqresSampleTheme {
        UserCardList()
    }
}