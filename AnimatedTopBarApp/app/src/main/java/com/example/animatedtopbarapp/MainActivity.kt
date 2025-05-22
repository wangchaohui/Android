package com.example.animatedtopbarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme // Ensure this is Material 3
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animatedtopbarapp.ui.composables.AnimatedTopAppBar
import com.example.animatedtopbarapp.ui.theme.AnimatedTopBarAppTheme

class MainActivity : ComponentActivity() { // Ensure this is ComponentActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedTopBarAppTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val lazyListState = rememberLazyListState()

    val scrollOffset: Float by remember {
        derivedStateOf {
            if (lazyListState.firstVisibleItemIndex > 0) {
                // If scrolled past the first item, consider it fully scrolled for the title animation
                // Using a large enough value to ensure title is minimized.
                // This will be clamped in AnimatedTopAppBar.
                Float.MAX_VALUE
            } else {
                lazyListState.firstVisibleItemScrollOffset.toFloat()
            }
        }
    }

    Scaffold(
        topBar = {
            AnimatedTopAppBar(
                title = "Animated Title",
                scrollOffset = scrollOffset // Pass the calculated offset
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues), // Apply padding from Scaffold
                state = lazyListState
            ) {
                items(100) { index -> // Increased item count to 100
                    ListItem(index = index)
                }
            }
        }
    )
}

@Composable
fun ListItem(index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp), // Padding around the Card
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Padding inside the Card
                .fillMaxWidth()
        ) {
            Text(
                text = "Item Title ${index + 1}",
                style = MaterialTheme.typography.titleMedium, // Using MaterialTheme typography
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Subtitle for item ${index + 1}",
                style = MaterialTheme.typography.bodyMedium, // Using MaterialTheme typography
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "This is some more detailed description for item ${index + 1}. " +
                        "It can span multiple lines to make the item taller and the list longer.",
                style = MaterialTheme.typography.bodySmall // Using MaterialTheme typography
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AnimatedTopBarAppTheme {
        MainScreen()
    }
}
