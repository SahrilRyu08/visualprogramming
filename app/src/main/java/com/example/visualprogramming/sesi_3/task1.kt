package com.example.visualprogramming.sesi_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header("Profile")
        ProfileInfo()
        Status()
        ButtonSection()
        StatusInput()
        Notif()
        Save()
    }
}

@Composable
fun Save() {
    Button(
        onClick = {},
        modifier = Modifier.
        fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff6a1b9a)
        )
        ) {
        Text("Simpan")
    }
}

@Composable
fun Notif() {
    var isEnable by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Notification : ", fontWeight = FontWeight.Bold)
        Switch(
            checked = isEnable,
            onCheckedChange = {
                isEnable = it
            }
        )
    }
}

@Composable
fun StatusInput() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Status: ", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {
                Text("Tulis Status..")
            },
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
fun ButtonSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff6a1b9a)
            )
        ) {
            Text("Follow")
        }

        Spacer(modifier = Modifier.width(12.dp))

        OutlinedButton(
            onClick = {},
            modifier = Modifier.weight(1f)
        ) {
            Text("message")
        }
    }
}

@Composable
fun Status() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(paddingValues =
                    PaddingValues(start = 108.dp, end = 30.dp))
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(
                    paddingValues =
                        PaddingValues(start = 20.dp)),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ItemData("Post", "12")
                ItemData("Follower", "340")
                ItemData("Following", "100")
            }
        }
    }
}

@Composable
fun ItemData(title: String, value: String) {
    Column(
        modifier = Modifier.padding(
            paddingValues = PaddingValues(
                start = 10.dp,
                end = 10.dp
            )
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, color = Color.Gray)
        Text(value, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ProfileInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Spacer(
            modifier = Modifier.width(16.dp)
        )
        Column {
            Text("Alya Pratama",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp)
            Text(
                text = "Android Developer - UI/UX Enthusiast",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun Header(value: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xff8e2de2),
                        Color(0xff4a00e0)
                    )
                )
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(
                text = value,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}