package com.example.studentregistrationapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentregistrationapp.data.local.entity.Siswa
import com.example.studentregistrationapp.ui.component.FormInput
import com.example.studentregistrationapp.ui.component.StudentItem
import com.example.studentregistrationapp.viewmodel.StudentViewModel
import androidx.compose.material3.HorizontalDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: StudentViewModel) {
    val siswaList by viewModel.siswaList.collectAsState()

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var kelas by remember { mutableStateOf("") }
    var selectedSiswa by remember { mutableStateOf<Siswa?>(null) }

    Scaffold(
        containerColor = Color(0xFFF4FAF9),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Registrasi Siswa",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF00897B)
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4FAF9))
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = if (selectedSiswa == null) "Tambah Siswa Baru" else "Edit Data Siswa",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1B4D47)
                        )
                        Text(
                            text = if (selectedSiswa == null)
                                "Isi form di bawah untuk mendaftarkan siswa."
                            else
                                "Ubah data siswa yang dipilih.",
                            fontSize = 13.sp,
                            color = Color(0xFF78909C),
                            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                        )

                        HorizontalDivider(color = Color(0xFFE0F2F1), thickness = 1.dp)
                        Spacer(modifier = Modifier.height(12.dp))

                        FormInput(
                            nama = nama,
                            email = email,
                            kelas = kelas,
                            buttonText = if (selectedSiswa == null) "Simpan" else "Perbarui",
                            onNamaChange = { nama = it },
                            onEmailChange = { email = it },
                            onKelasChange = { kelas = it },
                            onSubmit = {
                                if (selectedSiswa == null) {
                                    viewModel.tambahSiswa(nama, email, kelas)
                                } else {
                                    viewModel.editSiswa(selectedSiswa!!.copy(nama = nama, email = email, kelas = kelas))
                                    selectedSiswa = null
                                }
                                nama = ""; email = ""; kelas = ""
                            }
                        )

                        if (selectedSiswa != null) {
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedButton(
                                onClick = {
                                    selectedSiswa = null
                                    nama = ""; email = ""; kelas = ""
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFB2DFDB))
                            ) {
                                Text("Batal", fontSize = 13.sp, color = Color(0xFF00897B))
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Daftar Siswa (${siswaList.size})",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color(0xFF37474F),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            if (siswaList.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1))
                    ) {
                        Text(
                            text = "Belum ada siswa terdaftar.",
                            color = Color(0xFF00695C),
                            fontSize = 13.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            } else {
                items(siswaList) { siswa ->
                    StudentItem(
                        siswa = siswa,
                        onEdit = {
                            selectedSiswa = siswa
                            nama = siswa.nama
                            email = siswa.email
                            kelas = siswa.kelas
                        },
                        onDelete = { viewModel.hapusSiswa(siswa) }
                    )
                }
            }
        }
    }
}