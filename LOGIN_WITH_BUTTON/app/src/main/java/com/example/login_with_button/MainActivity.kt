package com.example.login_with_button

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login_with_button.ui.theme.LOGIN_WITH_BUTTONTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOGIN_WITH_BUTTONTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val username= remember {
                            mutableStateOf(TextFieldValue() )
                        }
                        val password= remember {
                            mutableStateOf(TextFieldValue() )
                        }
                        @Composable
                        fun CircleImageView() {
                            Image(
                                painter = painterResource(R.drawable.img),
                                contentDescription = "Circle Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(128.dp)
                                    .clip(CircleShape) // clip to the circle shape
                                    .border(5.dp, Color.Gray, CircleShape)//optional
                            )
                        }
                        CircleImageView()
                        Text(text = "Login" , style=MaterialTheme.typography.displayMedium)
                        Spacer(modifier = Modifier.height(60.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = username.value, onValueChange = {username.value=it},
                            label = { Text(text = "USERNAME")},
                            visualTransformation = VisualTransformation.None
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(   ),
                            value = password.value, onValueChange = {password.value=it},
                            label = { Text(text = "PASSWORD")},
                            visualTransformation = PasswordVisualTransformation()
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
                            LogoButton(
                                logoResource = R.drawable.amazon,
                                websiteUrl = "https://www.amazon.in",
                                launcher = launcher
                            )

                            LogoButton(
                                logoResource = R.drawable.flipkart,
                                websiteUrl = "https://www.flipkart.com",
                                launcher = launcher
                            )
                            LogoButton(
                                logoResource = R.drawable.myntra
                                ,
                                websiteUrl = "https://www.myntra.com",
                                launcher = launcher
                            )
                            LogoButton(
                                logoResource = R.drawable.hotstar,
                                websiteUrl = "https://www.hotstar.com",
                                launcher = launcher
                            )

                        }
                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }
            }
        }
    }
}

@Composable
fun LogoButton(logoResource: Int, websiteUrl: String, launcher: ActivityResultLauncher<Intent>) {
    val painter: Painter = painterResource(id = logoResource)
    Box(
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
            launcher.launch(intent)
        }
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(shape = MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondary)
                .padding(12.dp)
        )
    }
}