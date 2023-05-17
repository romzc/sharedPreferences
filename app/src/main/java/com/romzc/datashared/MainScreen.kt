package com.romzc.datashared

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(pref: PreferenceManager) {

    // It gets data if it exists, otherwise return defValue.
    val text = remember { mutableStateOf(pref.getUser()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (text.value.isNotEmpty()) {
            Profile(userName = pref.getUser()) {
                pref.putUser("")
                text.value = pref.getUser()
            }
        }
        else {
            Login {
                pref.putUser(it)
                text.value = pref.getUser()
            }
        }
    }
}



@Composable
fun Login( logIn: (name: String) -> Unit) {

    val userName = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            label = { Text("Username") },
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Button(onClick = { logIn(userName.value) }) { Text(text = "Log in") }
    }
}

@Composable
fun Profile(userName: String, logOut: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome $userName", modifier = Modifier.padding(vertical = 16.dp))
        Button(onClick = { logOut() }) { Text(text="Log out") }
    }
}
