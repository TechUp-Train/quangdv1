package com.example.kmptraining

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.apero.composetraining.common.sessions
import com.example.kmptraining.migrate.base.BaseListScreen
import com.example.kmptraining.migrate.common.Session
import com.example.kmptraining.migrate.common.SubSession
import com.example.kmptraining.migrate.navigation.SessionNavigationKey
import com.example.kmptraining.migrate.session1.session1_1.GreetingCard
import com.example.kmptraining.migrate.ui.theme.ComposeTrainingTheme
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val navConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(
                    SessionNavigationKey.SessionList::class,
                    SessionNavigationKey.SessionList.serializer()
                )
                subclass(
                    SessionNavigationKey.Session::class,
                    SessionNavigationKey.Session.serializer()
                )
                subclass(
                    SessionNavigationKey.SubSession::class,
                    SessionNavigationKey.SubSession.serializer()
                )

            }
        }
    }
    val backStack = rememberNavBackStack(navConfig, SessionNavigationKey.SessionList)
    val currentKey = backStack.lastOrNull()
    val appBarTitle = when (currentKey) {
        is SessionNavigationKey.SessionList -> "Sessions"
        is SessionNavigationKey.Session -> currentKey.sessionName
        is SessionNavigationKey.SubSession -> currentKey.subSessionName
        else -> "Unknown"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(
                        appBarTitle,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    AnimatedVisibility(
                        visible = currentKey !is SessionNavigationKey.SessionList,
                        content = {
                            IconButton(onClick = { if (backStack.size > 1) backStack.removeLastOrNull() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                )
                            }
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavDisplay(
                modifier = Modifier.fillMaxSize(),
                backStack = backStack,
                onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<SessionNavigationKey.SessionList> {
                        val sessionTitles = remember { sessions.map { it.title } }
                        BaseListScreen(
                            list = sessionTitles,
                            onClick = { sessionName ->
                                backStack.add(SessionNavigationKey.Session(sessionName))
                            }
                        )
                    }
                    entry<SessionNavigationKey.Session> { key ->
                        val subSessionNames = remember(key.sessionName) {
                            sessions.find { it.title == key.sessionName }?.subSessions?.map { it.name }
                                ?: emptyList()
                        }
                        BaseListScreen(
                            list = subSessionNames,
                            onClick = { subSessionName ->
                                backStack.add(SessionNavigationKey.SubSession(subSessionName))
                            }
                        )
                    }
                    entry<SessionNavigationKey.SubSession> { key ->
                        val subSessionContent = remember(key.subSessionName) {
                            sessions.flatMap { it.subSessions }
                                .find { it.name == key.subSessionName }?.content
                        }
                        subSessionContent?.invoke()
                    }
                }
            )
        }
    }
}