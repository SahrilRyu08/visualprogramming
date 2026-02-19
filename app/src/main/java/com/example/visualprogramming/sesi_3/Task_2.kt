package com.example.visualprogramming.sesi_3

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Purple = Color(0xFF5A00FF)
private val BackgroundLight = Color(0xFFF5F5F5)
private val ChipBackground = Color(0xFFE8E0F5)

@Composable
fun Tugas2CheckoutScreen() {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var selectedShipping by remember { mutableStateOf("Regular") }
    var agreeTerms by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val digitsOnly = phone.filter { it.isDigit() }
    val isPhoneValid = digitsOnly.length in 10..15 && Patterns.PHONE.matcher(phone).matches()
    val phoneHasError = phone.isNotBlank() && !isPhoneValid

    val isFormValid = name.isNotBlank() &&
            address.isNotBlank() &&
            isPhoneValid &&
            agreeTerms

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                color = Purple,
                shadowElevation = 0.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Checkout",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundLight)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Nama Penerima",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text(text = "Nama lengkap") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Telepon",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = { Text(text = "08xxxxxxxxxx") },
                    singleLine = true,
                    isError = phoneHasError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                )
                val helperColor = if (phoneHasError) Color(0xFFD32F2F) else Color(0xFF777777)
                val helperText = if (phoneHasError) {
                    "Nomor telepon tidak valid (10–15 digit, hanya angka)"
                } else {
                    "Hanya digit, 10–15"
                }
                Text(
                    text = helperText,
                    fontSize = 12.sp,
                    color = helperColor,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Alamat",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    placeholder = { Text(text = "Jalan, No, RT/RW, Kelurahan, Kecamatan...") },
                    singleLine = false,
                    maxLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Metode Pengiriman",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ShippingChipTugas2(
                        label = "Regular",
                        selected = selectedShipping == "Regular",
                        onClick = { selectedShipping = "Regular" }
                    )
                    ShippingChipTugas2(
                        label = "Express",
                        selected = selectedShipping == "Express",
                        onClick = { selectedShipping = "Express" }
                    )
                    ShippingChipTugas2(
                        label = "Same Day",
                        selected = selectedShipping == "Same Day",
                        onClick = { selectedShipping = "Same Day" }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Catatan (opsional)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = agreeTerms,
                        onCheckedChange = { agreeTerms = it }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Saya setuju dengan Syarat & Ketentuan",
                        fontSize = 14.sp,
                        color = Color(0xFF333333)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (isFormValid) {
                            showDialog = true
                        }
                    },
                    enabled = isFormValid,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple,
                        contentColor = Color.White,
                        disabledContainerColor = Purple.copy(alpha = 0.4f),
                        disabledContentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = "Buat Pesanan",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }

    if (showDialog) {
        val displayNote = if (note.isBlank()) "(kosong)" else note
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text(
                        text = "OK",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },
            title = {
                Text(
                    text = "Ringkasan Pesanan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            text = {
                Text(
                    text = buildString {
                        appendLine("Nama: $name")
                        appendLine("Telepon: $phone")
                        appendLine("Alamat: $address")
                        appendLine("Pengiriman: $selectedShipping")
                        append("Catatan: $displayNote")
                    },
                    fontSize = 14.sp
                )
            }
        )
    }
}

@Composable
private fun ShippingChipTugas2(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val background = if (selected) Purple else ChipBackground
    val textColor = if (selected) Color.White else Color(0xFF5B5B5B)

    Box(
        modifier = Modifier
            .height(36.dp)
            .width(90.dp)
            .background(background, shape = RoundedCornerShape(100.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Tugas2CheckoutScreenPreview() {
    Tugas2CheckoutScreen()
}

