package com.weiren.zhang.movie_composeui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fragment.project.ui.web.WebViewNavGraph
import com.example.fragment.project.ui.web.rememberWebViewNavigator
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.viewmodel.WebViewViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen(
    viewModel: WebViewViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val homeMode by viewModel.videoMode.collectAsStateWithLifecycle()

    var sheetValue by rememberSaveable { mutableStateOf(SheetValue.PartiallyExpanded) }
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = sheetValue,
        confirmValueChange = {
            sheetValue = it
            true
        },
        skipHiddenState = false
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState)
    val navigator = rememberWebViewNavigator()

    Scaffold(topBar = {
        TopAppBar(colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text(homeMode.title, maxLines = 1)
        }, navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        })
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BottomSheetScaffold(
                sheetContent = {},
                scaffoldState = scaffoldState,
                sheetPeekHeight = 0.dp,
                sheetShape = RoundedCornerShape(0.dp),
                sheetShadowElevation = 10.dp,
                sheetDragHandle = null,
                sheetSwipeEnabled = false
            ) { padding ->
                WebViewNavGraph(
                    url = homeMode.href,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .fillMaxSize()
                        .padding(padding),
                    navigator = navigator,
                    navigateUp = onBackClick,
                )
            }
            AnimatedVisibility(visible = (navigator.progress > 0f && navigator.progress < 1f)) {
                LinearProgressIndicator(
                    progress = { navigator.progress },
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface,
                    trackColor = MaterialTheme.colorScheme.surfaceContainer
                )
            }
        }
    }
}