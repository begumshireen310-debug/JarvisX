package com.jarvisx.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Cyan = Color(0xFF00E5FF)
val Red = Color(0xFFFF1744)
val Bg = Color(0xFF0A0A0F)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Dashboard() }
    }
}

@Composable
fun Dashboard() {
    var selected by remember { mutableStateOf<String?>(null) }
    Column(
        Modifier.fillMaxSize().background(Bg).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))
        Text("JARVIS X", color = Cyan, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(32.dp))
        Orb()
        Spacer(Modifier.height(32.dp))
        val modules = listOf("Coding AI", "YouTube AI", "Quran AI", "Personal AI")
        modules.chunked(2).forEach { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                row.forEach { name ->
                    ModuleCard(name, Modifier.weight(1f)) { selected = name }
                }
            }
            Spacer(Modifier.height(12.dp))
        }
        Spacer(Modifier.height(16.dp))
        Text(
            selected?.let { "$it - jald aa raha hai (agla step)" } ?: "Ek module chuniye",
            color = Color.Gray, fontSize = 14.sp
        )
    }
}

@Composable
fun Orb() {
    val t = rememberInfiniteTransition(label = "orb")
    val s by t.animateFloat(
        0.9f, 1.1f,
        infiniteRepeatable(tween(1400, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "scale"
    )
    Box(
        Modifier.size(160.dp).scale(s).clip(CircleShape)
            .background(Brush.radialGradient(listOf(Cyan, Red.copy(alpha = 0.5f), Color.Transparent)))
    )
}

@Composable
fun ModuleCard(name: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier.height(110.dp)
            .border(1.dp, Cyan.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
            .background(Color(0xFF14141C), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) { Text(name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold) }
}
