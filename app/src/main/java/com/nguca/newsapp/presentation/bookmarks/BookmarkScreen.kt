package com.nguca.newsapp.presentation.bookmarks

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nguca.newsapp.presentation.home.NewsListView
import com.nguca.newsapp.utils.NavRoute




@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun BookmarkScreen(navHostController: NavHostController){
    val viewModel : BookmarkViewModel = hiltViewModel()
    val bookmarks = viewModel.getBookmarks().collectAsState(initial = listOf())
    Column {
        Text(text = "Bookmarks", fontSize = 30.sp)
        Spacer(modifier = Modifier.size(16.dp))
        NewsListView(bookmarks.value, onClick = {
            navHostController.navigate(NavRoute.createNewsDetailsRoute(it, true))
        })
    }
}